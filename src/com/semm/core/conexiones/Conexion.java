package com.semm.core.conexiones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.semm.core.bd.ConexionDAO;
import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.MensajePorProcesarDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.MensajePorProcesar;
import com.semm.core.sesiones.ManejadorSesiones;

public abstract class Conexion  implements Serializable, Runnable{
	
	public static final int MESSENGER=1,MODEMS=0;
	
	 
	
	protected long id;
	
	protected transient boolean conectado,apagar,recuperando,activa;
	protected ManejadorSesiones mses = ManejadorSesiones.getInstancia();
	protected boolean recarga,extramsgs;
	protected GrupoConexiones grup;
	protected transient DetectaMensaje dtect;
	protected long fallos,temp_out;
	protected long ultimafalla;
	protected final static int DELAY = 3,NUMFALLOS = 12,PISO=3,DBRETRIES=2;
	protected final static long WAITLIMIT = 1920000;
	public Enrutador router;
	protected String[] alarmrecips = {"04143321015","04141262787","04141330820"};
	
	
	protected int tipocnx,pagados,renovados,encola;
	
	protected String nombre;
	
	protected float costpermsg,costperextra,transrate;
	
	protected Date expdate;
	
	protected transient Queue<NuevoMensaje> outbox;
	private NuevoMensaje temp_peek;
	transient  Logger log = Logger.getLogger(Conexion.class.getName());

	/**
	 * 
	 */
	public Conexion() {
		outbox = new ConcurrentLinkedQueue<NuevoMensaje>();

	}
	public float getCostperextra() {
		return costperextra;
	}
	public void setCostperextra(float costperextra) {
		this.costperextra = costperextra;
	}
	public float getCostpermsg() {
		return costpermsg;
	}
	public void setCostpermsg(float costpermsg) {
		this.costpermsg = costpermsg;
	}
	public boolean isExtramsgs() {
		return extramsgs;
	}
	public void setExtramsgs(boolean extramsgs) {
		this.extramsgs = extramsgs;
	}
	
	public int getFaltan(){
		return (int)(getEncola()/getTransrate());
	}
	
	public boolean isRecarga() {
		return recarga;
	}
	public void setRecarga(boolean recarga) {
		this.recarga = recarga;
	}
	public float getTransrate() {
		return transrate;
	}
	public void setTransrate(float transrate) {
		this.transrate = transrate;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isConectado() {
		return conectado;
	}
	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	public DetectaMensaje getDtect() {
		return dtect;
	}
	public void setDtect(DetectaMensaje dtect) {
		this.dtect = dtect;
	}
	public GrupoConexiones getGrup() {
		return grup;
	}
	public void setGrup(GrupoConexiones grup) {
		this.grup = grup;
	}
	
	public int tamanoOutbox(){
		return this.outbox.size();
	}
	

	public float getLoad(){
		return (outbox.size()/transrate);
	}
	public int getTipocnx() {
		return tipocnx;
	}
	public void setTipocnx(int tipocnx) {
		this.tipocnx = tipocnx;
	}
	
	public abstract void conectar() throws Exception;
	public abstract void desconectar();
	public abstract void enviar(NuevoMensaje m);
	public abstract void recargar();
	public abstract void recuperar();
	public abstract boolean verificarFalla();
	
	
	public abstract void agregarMsgCola(NuevoMensaje m) throws ConexionMuertaException, ConexionFullException;
	
	public int getPagados() {
		return pagados;
	}
	public void setPagados(int pagados) {
		this.pagados = pagados;
	}
	

	
	protected void enviarMensajes() {
		Iterator<NuevoMensaje> i2 = outbox.iterator();
		while(i2.hasNext()){
			
			if(verificarFalla()){
				log.debug("Conexion Muerta: " + this.getNombre() + " Recuperando...");
				recuperando = true;
				return;
			}
			
			log.debug("Enviando... CNX: "+ getNombre() + " En cola:" + outbox.size() + " " + ((this.extramsgs)? "" : "Disp: " + this.pagados));
			NuevoMensaje m = i2.next();
			enviar(m);
			this.encola = outbox.size() -1;
			i2.remove();
			
			if(reintentar(m)){
				outbox.add(m);
			}else {
				guardarMensaje(m);
				
				
				m.removefromUserQueue(1);
				if(outbox.size()< PISO){
					log.debug("Conexion llego a su piso! tamano: "  + outbox.size() );
					synchronized (router) {
						router.notifyAll();
					}
				}
				
			}
			
		}
		
		
	}
	
	
	
	public void run() {
		

		while(!conectado && !apagar) {
			try{
				conectar();
				conectado = true;
			} catch(Exception jme ){
				jme.printStackTrace();
				log.error(jme);
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					log.warn(e);
				}
			}
		}
		
		long timewait = 15000;
		
		while(conectado){
			if(!recuperando){
				try {
					timewait = 15000;
					enviarMensajes();
					//refrescar(this);
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
					log.warn(e);
				}
			}else {
				try {
					recuperar();
					Thread.sleep(timewait);
					timewait = (timewait < WAITLIMIT) ? timewait * 2: timewait;
					log.debug("Tiempo de Espera " + this.nombre + ": " + timewait);
				} catch (InterruptedException e) {
					log.warn(e);
					
				}
			}
		}
		
		log.debug("Llamada a Desconexion..." + this.nombre);
		desconectar();
		
	}
	
	private void refrescar(Conexion c) {
		FabricaDAO fab =  FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ConexionDAO cdao = fab.getConexionDAO();
		cdao.refresh(c);
		
	}
	
	public void apagar(){
		this.apagar = true;
		this.conectado = false;
		
	}
	
	public void apagarForzado(){
		Iterator<NuevoMensaje> i2 = outbox.iterator();
		while(i2.hasNext()){
			
			NuevoMensaje m = i2.next();
			CnxMensaje sms = (CnxMensaje)m;
			log.debug("Guardando Forzado: " + sms.getPara() + " - " + sms.getContenido().getMsg());
			sms.setTipo(2);
			sms.setSubtipo(CnxMensaje.APAGANDO);
			guardarMensaje(sms);
			this.encola = outbox.size() -1;
			i2.remove();
		}
		
		
	}
	
	protected boolean reintentar(NuevoMensaje m){
		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			return (sms.getTipo()==0) ? false:(sms.getRetries() <= 5);
		}
		else
			return false;
	}
	
	protected void guardarMensaje(NuevoMensaje m){
		
		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			List<MensajeLog> logs = convertirMensajeLogMulti(sms);
			for(MensajeLog mlog : logs)
				guardarMensaje(mlog);
			if(sms.getMpp()!=null)
				removerMensajeGenerador(sms.getMpp());
		}
		
	}
	
	
	
	private void removerMensajeGenerador(MensajePorProcesar mpp) {
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
	
			try {
				MensajePorProcesarDAO 	mdao = fab.getMensajePorProcesarDAO();
				tx.beginTx();
				log.debug("Borrando Mensaje Generador ID:" + mpp.getId());
				mdao.borrar(mpp);
				tx.commit();
				tx.evict(mpp);
			}
			catch (RuntimeException e){
				log.error(e);
				log.debug(e.getCause());
				tx.rollback();
				tx.cerrarSesion();
			}
		
	}
	protected List<MensajeLog> convertirMensajeLogMulti(CnxMensaje sms){
		ArrayList<MensajeLog> mlogs = new ArrayList<MensajeLog>();
		String contenido = sms.getContenido().getMsg();
		int begin = 0, end = contenido.length();
		int partsize = 160;
		if(sms.isMultipart()){
			for(int i=partsize;i<=end;i*=2){
				String part= contenido.substring(begin, i);
				MensajeLog mlog = new MensajeLog(sms.getPara(),part,sms.getServicio(),sms.getOwner(),sms.getCnx(),sms.getTipo(),sms.getSubtipo());
				begin = i;
				mlogs.add(mlog);
			}
		}
		MensajeLog mlogfinal = new MensajeLog(sms.getPara(),contenido.substring(begin, end),sms.getServicio(),sms.getOwner(),sms.getCnx(),sms.getTipo(),sms.getSubtipo());
		mlogs.add(mlogfinal);
		return mlogs;
	}
	
	protected List<MensajeLog> convertirMensajeLogMulti(MensajeLog sms){
		ArrayList<MensajeLog> mlogs = new ArrayList<MensajeLog>();
		String contenido = sms.getMsg();
		int begin = 0, end = contenido.length();
		int partsize = 160;
		for(int i=partsize;i<=end;i*=2){
			String part= contenido.substring(begin, i);
			MensajeLog mlog = new MensajeLog(sms.getMobile(),part,sms.getServicio(),sms.getCliente(),sms.getCnx(),sms.getTipo(),sms.getSubtipo());
			begin = i;
			mlogs.add(mlog);
		}
		MensajeLog mlogfinal = new MensajeLog(sms.getMobile(),contenido.substring(begin, end),sms.getServicio(),sms.getCliente(),sms.getCnx(),sms.getTipo(),sms.getSubtipo());
		mlogs.add(mlogfinal);
		return mlogs;
	}
	
	protected MensajeLog convertirMensajeLog(CnxMensaje sms) {
		MensajeLog mlog = new MensajeLog(sms.getPara(),sms.getContenido().getMsg(),sms.getServicio(),sms.getOwner(),sms.getCnx(),sms.getTipo(),sms.getSubtipo());
		return mlog;
	}
	protected void guardarMensaje(MensajeLog mlog){
		log.debug("Actualizando Mensaje " + mlog.getMobile() + " - "   + mlog.getCnx().getNombre() + " \n" + mlog.getMsg() + " TIPO:" + mlog.getTipo() + " SUBTIPO: " + mlog.getSubtipo());
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		for(int i=0;i<DBRETRIES;i++){
			try {
				MensajeDAO 	mdao = fab.getMensajeDAO();
				tx.beginTx();
				mdao.guardar(mlog);
				tx.commit();
				log.debug("Mensaje ID:" + mlog.getId());
				tx.evict(mlog);
				break;
			} catch (RuntimeException e){
				log.error(e);
				log.debug(e.getCause());
				tx.rollback();
				tx.cerrarSesion();
			} 
		}
		
		if(mlog.getCliente().getTipo()>0){
			
			mses.agregarParaActualizar(mlog.getCliente());
			
		}
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRenovados() {
		return renovados;
	}
	public void setRenovados(int renovados) {
		this.renovados = renovados;
	}

	
	public int getEncola() {
		return encola;
	}
	public void setEncola(int encola) {
		this.encola = encola;
	}
	public long getUltimafalla() {
		return ultimafalla;
	}
	public void setUltimafalla(long ultimafalla) {
		this.ultimafalla = ultimafalla;
	}
	public boolean isRecuperando() {
		return recuperando;
	}
	public void setRecuperando(boolean recuperando) {
		this.recuperando = recuperando;
	}
	public Enrutador getRouter() {
		return router;
	}
	public void setRouter(Enrutador router) {
		this.router = router;
	}
	public long getTemp_out() {
		return temp_out;
	}
	public void  setTemp_out(long temp_out) {
		this.temp_out = temp_out;
	}
	public NuevoMensaje getTemp_peek() {
		return temp_peek;
	}
	public void setTemp_peek(NuevoMensaje temp_peek) {
		this.temp_peek = temp_peek;
	}
	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	

	
}
