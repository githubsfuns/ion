package com.semm.core.conexiones;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;

import com.semm.core.bd.ConexionDAO;
import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.GrupoConexionesDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.UsuarioDAO;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.Usuario;


public class ManejadorConexiones implements DetectaMensaje{
	
	public static Logger log = Logger.getLogger(ManejadorConexiones.class);
	private LinkedList<Conexion> conexiones_activas;
	private boolean activo = true;
	private ConcurrentLinkedQueue<NuevoMensaje> inbox;
	private ConcurrentLinkedQueue<ReporteEntrega> reports;
	private ConcurrentLinkedQueue<Llamada> calls;
	private Enrutador router;
	public String url,in,out,port,baud,device,model,max;
	private LinkedList<Conexion> conexiones_messenger;
	private static ManejadorConexiones instance = null;
	private long tempsize = 0,time=0,alarmtime = 0;
	protected String[] alarmrecips = {"04141262787","04141330820","04143321015"};


	private ManejadorConexiones() {
		inbox = new ConcurrentLinkedQueue<NuevoMensaje>();
		reports = new ConcurrentLinkedQueue<ReporteEntrega>();
		calls = new ConcurrentLinkedQueue<Llamada>();
		conexiones_activas = new LinkedList<Conexion>();
		conexiones_messenger = new LinkedList<Conexion>();
	}
	
	public void getStatusActivas(){
		int leftotal=0;
		int cola=0;
		int falta=0;
		
		float trtotal = 0;
		for(Conexion c: conexiones_activas){

			if(c.isConectado() && c.isActiva()){
				int online = c.getEncola();
				int left = c.getPagados() - online;
				int faltatemp = Math.round(online/c.getTransrate());
				falta = (faltatemp > falta)? faltatemp:falta;
				log.debug("Conexion: " + c.getNombre() + " - " + ((c.extramsgs)? "POSTPAGO" : "PREPAGO Disp: " + left) + " - En Cola:" +online+   " - TX Rate: " 
						+ c.getTransrate() + " sms/min - Faltan: " + faltatemp + " min");
				leftotal += (c.extramsgs)? 0: left;
				trtotal += c.getTransrate();
				cola += online;
				if(online > 0 && c.outbox.peek() != null && c.outbox.peek() == c.getTemp_peek()){
					if(c.getTemp_out()!=0) {
						if(System.currentTimeMillis()- c.getTemp_out() > 3600000){
							log.fatal("Conexion ATRACADA Verificar!!!");
							
							prenderalarmas(c,"ALERTA AMARILLA! " + c.nombre + " Esta Trancada Verificar! Solucion Inactivar en BD (CNXID: " +c.getId() +") y Revisar Fecha: " + new Date());
						}
					} else 
						c.setTemp_out(System.currentTimeMillis());
				} else 
					c.setTemp_out(0);
				
			 	c.setTemp_peek(c.outbox.peek());
			 	
			 	//Reviso si no ha recibido nada en 15 min
			 	
			 	if (c instanceof ConexionModemGSM){
			 		
			 		if (((ConexionModemGSM) c).getUltimorecibido() == 900000){
			 		
			 			try {
							((ConexionModemGSM) c).limpiarlinea();
						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GatewayException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 		}
			 		
			 	}
			 	
			} else if (c.isConectado() && !c.isActiva()){
				log.info("Conexion " + c.nombre + " Inactiva o en Mantenimiento!.");
				c.apagar();
				log.info("Conexion " + c.nombre + " En proceso de desactivacion.");
			} else if (!c.isConectado() && c.isActiva()){
				log.info("Conexion " + c.nombre + " Nuevamente Activa.");
				Thread tcnx = new Thread(c);
				tcnx.start();
				log.info("Conexion " + c.nombre + " En proceso de Conexion...");
			} else {
				log.info("Conexion " + c.nombre + " Descativada.");
			}
			
		}
		log.info("ION En Cola: " + cola +" - Disp. PREPAGO: " + leftotal + " - TX Rate Total:" + trtotal + " sms/min - Faltan:" + falta + " min");
		log.info("Por procesar en sesion: " + this.inbox.size()); 
		if(this.inbox.size() > 0 && this.inbox.size() >= tempsize ){
			if(time !=0) {
				if(System.currentTimeMillis()- time > 600000){
					log.fatal("SISTEMA TRANCADO RESETEAR!!!");
					prenderalarmas();
				}
			}
			else 
				time = System.currentTimeMillis();
		} else 
			time = 0;
		
		tempsize = inbox.size();
		
	}
	


	private void prenderalarmas() {
		prenderalarmas(null,"ALERTA ROJA!: el Hilo de Sesiones esta colgado! Solucion: Reiniciar el ION. Fecha:" + new Date());
	}

	private void prenderalarmas(Conexion c,String msg) {

		if(System.currentTimeMillis() - alarmtime < 600000)
			return;
		
		Usuario ow = new Usuario();
		ow.setTipo(0);
		ow.setUsername("semm");
		
		for(String recip:alarmrecips){
			CnxMensaje alarma = new CnxMensaje();
			alarma.setPara(recip);
			alarma.setContenido(new ContenidoSMS(msg));
			alarma.setServicio("alarma_semm");
			alarma.setOwner(ow);
			
			for(Conexion c2: conexiones_activas){
				if(c2 == c)
					continue;
				try {
					alarma.setCnx(c2);
					c2.agregarMsgCola(alarma);
					alarmtime = System.currentTimeMillis();
					break;
				} catch (Exception e){
					log.debug("Conexion: " + c2.nombre + " IMPOSIBLE COLOCAR MAS MENSAJES...");
					e.printStackTrace();
				}		
			}
		}
	}

	public static ManejadorConexiones getInstancia(){
		if(instance == null){
			instance = new ManejadorConexiones();
		}
		return instance;
	}
	
	public void inicializar() throws Exception {
		log.debug("Inicializando Conexiones...");
		initConexiones();
		initRouter();
	}
	
	public void initConexiones() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Conexion> confs = getCnxs();
		for(Conexion cnx:confs){
			cnx.setDtect(this);

			/*if(cnx.isActiva()){
				Thread tcnx = new Thread(cnx);
				tcnx.start();
			}*/
			
			if(cnx.getTipocnx()==Conexion.MODEMS){
				log.debug("Agregando: " + cnx.getNombre() + " al lote de Modems");
				conexiones_activas.add(cnx);
			}else if(cnx.getTipocnx()==Conexion.MESSENGER) {
				Thread tcnx = new Thread(cnx);
				tcnx.start();
				log.debug("Agregando: " + cnx.getNombre() + " al lote de Messenger");
				conexiones_messenger.add(cnx);
			}
		}

		
	}
	
	private void initRouter() {
		router = new Enrutador(getGrpsCnxs(),conexiones_activas);
		router.initOrden();	
		router.setActivo(true);
		Thread trouter = new Thread(router);
		trouter.start();
	}

	public void terminar(){
		log.debug("Terminando las Conexiones...");
		desconectarTodas();
		activo = false;
		router.setActivo(false);
	}
		
	private void desconectarTodas() {
		for(Conexion cnx : conexiones_messenger){
			log.debug("Desconectando..." + cnx.getNombre());
			cnx.desconectar();
		}
		
		for(Conexion cnx : conexiones_activas){
			log.debug("Guardando mensajes en cola..." + cnx.getNombre());
			cnx.apagar();
			int i = 0,siz;
			while((siz = cnx.outbox.size()) > 0 && i < 20){
				log.debug("Guardando..." + siz + "SMS - Intento: " + i);
				i++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					log.error(e);
				}
			}
			cnx.apagarForzado();
		}
	}

	private List<Conexion> getCnxs() {
		FabricaDAO fab =  FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ConexionDAO cdao = fab.getConexionDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		List<Conexion> lcfg = cdao.buscarTodos();
		tx.commit();	
		return lcfg;
	}
	
	private List<GrupoConexiones> getGrpsCnxs() {
		FabricaDAO fab =  FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		GrupoConexionesDAO cdao = fab.getGrupoConexionesDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		List<GrupoConexiones> lcfg = cdao.buscarTodos();
		tx.commit();
	//tx.cerrarSesion();
		return lcfg;
	}

	public void desconectar(Conexion id){
		int index = conexiones_activas.indexOf(id);
		if(index > 0){
			Conexion cnx = conexiones_activas.get(index);
			cnx.desconectar();
		}
	}

	public void mensajeEntrante(NuevoMensaje in) {
			log.debug("Agregando mensaje a cola...");
			inbox.add(in);
			log.debug("Mensaje agregado. Tamano: " + inbox.size());
	}

	public ConcurrentLinkedQueue<NuevoMensaje> getInbox() {
		return inbox;
	}

	public void setInbox(ConcurrentLinkedQueue<NuevoMensaje> inbox) {
		this.inbox = inbox;
	}

	public List<Conexion> getConexiones_activas() {
		return conexiones_activas;
	}

	public void setConexiones_activas(LinkedList<Conexion> conexiones_activas) {
		this.conexiones_activas = conexiones_activas;
	}

	public void enviarRoundRobin(CnxMensaje m) {
		log.debug("Enviando el mensaje  a RR: ");
		router.addMensaje(m);
	}

	public void enviaraCola(CnxMensaje m, Conexion bestcnx) {
		log.debug("Enviando el mensaje a: " + bestcnx.getNombre());
		m.setCnx(bestcnx);
		router.addMensaje(m);
	}

	public void reporteEntrante(ReporteEntrega in) {
		log.debug("Agregando Reporte de Entrega a cola...");
		reports.add(in);
	}
	
	public void llamadaEntrante(Llamada in) {
		log.debug("Agregando Llamada Entrante a cola...");
		calls.add(in);
	}

	public ConcurrentLinkedQueue<ReporteEntrega> getReports() {
		return reports;
	}

	public ConcurrentLinkedQueue<Llamada> getCalls() {
		return calls;
	}

	public void setCalls(ConcurrentLinkedQueue<Llamada> calls) {
		this.calls = calls;
	}

	






	
}