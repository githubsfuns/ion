package com.semm.core.conexiones;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.UsuarioDAO;
import com.semm.core.conexiones.bb.ConexionBlackberryPush;
import com.semm.core.conexiones.comparadores.ComparadorCarga;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.Usuario;

public class Enrutador implements Runnable{
	
	private List<GrupoConexiones> activas;
	private LinkedList<Conexion> allcnx;
	private Comparator<Conexion> trans;
	private ConcurrentHashMap<Usuario,ConcurrentLinkedQueue<CnxMensaje>>  prioriy_queue,queue;
	private ConcurrentHashMap<GrupoConexiones,HashSet<Usuario>>  grupousuario;
	private HashMap<GrupoConexiones, Integer> bloques;
	private HashMap<GrupoConexiones, Iterator<Conexion>> cnx_grps;
	private LinkedList<CnxMensaje> buffer;
	private boolean activo;
	public static Logger log = Logger.getLogger(Enrutador.class);
	
	public Enrutador(){}
	/**
	 * 
	 */
	public Enrutador(List<GrupoConexiones> active){
		
	}
	public Enrutador(List<GrupoConexiones> active,LinkedList<Conexion> allcnx){
		this.activas = active;
		this.allcnx = allcnx;
		trans = new ComparadorCarga();
		prioriy_queue = new ConcurrentHashMap<Usuario, ConcurrentLinkedQueue<CnxMensaje>>();
		queue = new ConcurrentHashMap<Usuario, ConcurrentLinkedQueue<CnxMensaje>>();
		grupousuario = new ConcurrentHashMap<GrupoConexiones, HashSet<Usuario>>();
		bloques = new HashMap<GrupoConexiones, Integer>();
		cnx_grps = new HashMap<GrupoConexiones, Iterator<Conexion>>(activas.size());
		buffer = new LinkedList<CnxMensaje>();
	}
	
	public void initOrden() {
		for(GrupoConexiones grp: activas){
			initOrden(grp);
			for(Conexion cnx :grp.getConexiones())
				cnx.setRouter(this);
		}
	}
	
	private void initOrden(GrupoConexiones grp){
		//Collections.sort(grp.getConexiones(),trans);
		cnx_grps.put(grp, grp.getConexiones().iterator());
	}
	
	private float calcularCupo(GrupoConexiones gcnx){
		int cupo = 0;
		for(Conexion cnx : gcnx.getConexiones()){
			
			cupo += cnx.getTransrate();
		}
		return cupo;
	}
	

	private float calcularCupoTotal(){
		int cupo = 0;
		for(Conexion cnx : allcnx){
			
			cupo += cnx.getTransrate();
		}
		return cupo;
	}
	
	public void addMensaje(CnxMensaje sms){
		ConcurrentHashMap<Usuario, ConcurrentLinkedQueue<CnxMensaje>> q = queue;
		ConcurrentLinkedQueue<CnxMensaje> lista;
		HashSet<Usuario> users;
		
		if(sms.isProgramado()){
			//q = prioriy_queue;
		}
		if (q.containsKey(sms.getOwner())){
			lista = q.get(sms.getOwner());
			log.debug("Ya existe cola de: " + sms.getOwner().getUsername() + " Por entregar: " + lista.size());
			lista.add(sms);
		} else{
			log.debug("Creando cola de: " + sms.getOwner().getNombre());
			lista = new ConcurrentLinkedQueue<CnxMensaje>();
			lista.add(sms);
			q.put(sms.getOwner(),lista);
			
		}
		
		
		GrupoConexiones grp = sms.getGrpcnx();
		if(grupousuario.containsKey(grp)){
			log.debug("Ya GrupoCnx de: " + grp.getNombre());
			users = grupousuario.get(grp);
			users.add(sms.getOwner());
		} else {
			log.debug("Creando GrupoCnx de: " + grp.getNombre());
			users = new HashSet<Usuario>();
			users.add(sms.getOwner());
			grupousuario.put(grp, users);
		}
		
		if(lista.size() <= 10){
			log.debug("Lista de " + sms.getOwner().getUsername() + " baja activando buffer");
			synchronized (this) {
				this.notifyAll();
			}
		}
		
		//guardarMensaje(sms);
	}
	/*
	private void guardarMensaje(CnxMensaje sms) {
		MensajeLog mlog = new MensajeLog(sms.getPara(),sms.getContenido().getMsg(),sms.getServicio(),sms.getOwner(),sms.getCnx(),CnxMensaje.POR_ENVIAR,sms.getSubtipo());
		log.debug("Guardando Mensaje Por Enviar: " + mlog.getMobile() + " - "   + mlog.getCnx().getNombre() + " \n" + mlog.getMsg() + " TIPO:" + mlog.getTipo() + " SUBTIPO: " + mlog.getSubtipo());
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		try {
			MensajeDAO 	mdao = fab.getMensajeDAO();
			tx.beginTx();
			mdao.guardar(mlog);
			tx.commit();
			log.debug("Mensaje ID:" + mlog.getId());
			
			tx.evict(mlog);
			
		} catch (RuntimeException e){
			log.error(e);
			log.debug(e.getCause());
			tx.rollback();
			tx.cerrarSesion();
		} 
		
	}*/
	private void distribuirPorCliente(){
		
		float txtotal = calcularCupoTotal();
		
		
		for(GrupoConexiones g : grupousuario.keySet()){
			float txgro =  calcularCupo(g) ;
			int cajita = (int)txtotal - buffer.size();
			
			float share = (cajita * txgro/txtotal);
			int usuarios = grupousuario.get(g).size();

			int cupodisp = usuarios > 0 ? (int)(share/usuarios) : (int)share ; 
			if(cupodisp <= 0)
				continue;
			
			bloques.put(g, cupodisp);
			log.debug("TX total del grupo: " + txgro);
			log.debug("Cupo del Grupo Buffer: " + cupodisp  + " Cajita Disp: " + cajita + "Mensajes en Buffer: " + buffer.size());
			log.debug("Clientes del grupo: " + usuarios );

			
		}
		
		distribuir(queue);

	}
	
	private void distribuir(ConcurrentHashMap<Usuario,ConcurrentLinkedQueue<CnxMensaje>> cola){
		for(ConcurrentLinkedQueue<CnxMensaje>  lm: cola.values()){
			int i = 0;
			CnxMensaje last = null;
			int bloque = 10;
			
			Iterator<CnxMensaje> it = lm.iterator();
			while(it.hasNext() && i < bloque){
				last = it.next();
				bloque = bloques.get(last.getGrpcnx());
				log.debug("BLoque " + last.getGrpcnx().getId() + " - " + bloque + " SMS");
				buffer.add(last);
				it.remove();
				i++;
				
			} 
			
			if(!it.hasNext()){
				log.debug("Cliente " + last.getOwner().getUsername() + " ingreso TODO: " + i + "SMS");
				cola.remove(last.getOwner());
			}
			if(last!=null)
				log.debug("Cliente " + last.getOwner().getUsername() + " ingresando al buffer: "  + i + "SMS");
			
		}
	}
	
	private void enviarMensajes(){
		Iterator<CnxMensaje> bit = buffer.iterator();
		int fails=0;
		while(bit.hasNext()){
			boolean xcnx = false;
			CnxMensaje msg = bit.next();
			
			try {
				if(msg.getCnx() != null){
					xcnx = true;
					enviaraCola(msg,msg.getCnx());
				} else {
					enviarRoundRobin(msg);
				}
				fails=0;
				bit.remove();
				
			} catch (SinMensajesPagadosException e) {
				log.error(e);
				bit.remove();
				fails=0;
				
			} catch (UsuarioSinSaldoException e) {
				log.error(e);
				bit.remove();
				fails=0;
				
			} catch (ConexionMuertaException e) {
				log.error(e);
				log.debug("Conexion: " + msg.getCnx().getNombre() + " Esta Dead. En cola: " + msg.getCnx().outbox.size() + " SMS");
				if(!xcnx){
					msg.setCnx(null);
					fails++;
				}
			
			} catch (ConexionFullException e) {
				log.debug("Conexion: " + msg.getCnx().getNombre() + " Esta FULL. En cola: " + msg.getCnx().outbox.size() + " SMS");
				if(!xcnx){
					msg.setCnx(null);
					fails++;
				}
			} 
			
			/*if(fails > allcnx.size()){
				log.debug("Todas las conexiones estan llenas o muertas! Fallaron: " + fails + " veces");
				break;
			}*/
			
			
		}
	}
	
	public void run(){
		log.debug("Inicializando Enrutador...");
		
		while(activo){
			
			distribuirPorCliente();
			enviarMensajes();
			reporteEnvios();
			synchronized (this) {
				try {
					wait();
				} catch (Exception e) {
					log.error(e);
				}	
			}
			
		}
		
	}

	
	public void enviarRoundRobin(CnxMensaje m) throws SinMensajesPagadosException, UsuarioSinSaldoException, ConexionMuertaException, ConexionFullException {
		
		Iterator<Conexion> fast = cnx_grps.get(m.getGrpcnx());
		Conexion bestcnx = null;
		
		
		while(fast.hasNext()){
			bestcnx = fast.next();
			
			if(bestcnx.isRecuperando() || !bestcnx.isActiva())
				continue;
			
			enviaraCola(m,bestcnx);
			return;
		}
		
		initOrden(m.getGrpcnx());
		enviarRoundRobin(m);
		
	}
	
	public void enviaraCola(CnxMensaje m, Conexion bestcnx) throws SinMensajesPagadosException, UsuarioSinSaldoException, ConexionMuertaException, ConexionFullException {
		int partes = 1;
		if (bestcnx instanceof ConexionModemGSM) {
			ConexionModemGSM bcnx = (ConexionModemGSM) bestcnx;
			log.debug("Tamano del mensaje: " + m.getContenido().getMsg().length() + " char(s)");
			log.debug("MaxLength: " + bcnx.getMaxlength() + " char(s)");
			partes = (m.getContenido().getMsg().length()/bcnx.getMaxlength());
			partes = (partes*bcnx.getMaxlength() < m.getContenido().getMsg().length()) ? ++partes : partes;
			log.debug("Calculando partes del mensaje: " + partes + " parte(s)");
		}
		
		if(bestcnx instanceof ConexionBlackberryPush){
			
		}

		if(m.getOwner().getTipo() > 1 && m.getSaldo() < partes){
			log.info("Usuario: " + m.getOwner().getUsername() + " SIN SALDO! Creditos:" + m.getOwner().getCredits() + " Por salir:" + m.getOwner().getOnqueue());
			throw new UsuarioSinSaldoException("Usuario: " + m.getOwner().getUsername());
		}

		log.debug("Enviando mensaje a la conexion : " + bestcnx.getNombre() + " TLF:" + m.getPara() + " SVC:" + m.getServicio());
		
		int left = bestcnx.getPagados()-bestcnx.outbox.size();
		m.setCnx(bestcnx);

		if(bestcnx.isExtramsgs() || left>20){
			bestcnx.agregarMsgCola(m);
			m.addtoUserQueue(partes);
		}else if (left>10){
			log.debug("Conexion: "+ m.getCnx().getNombre() + " Con poco saldo! Quedan:" + left);
		} else {
			throw new SinMensajesPagadosException(bestcnx.getNombre());
		}
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public void reporteEnvios(){
		
		for(Map.Entry<Usuario,ConcurrentLinkedQueue<CnxMensaje>> entr:queue.entrySet()){
			log.debug("Usuario: " + entr.getKey().getUsername() +" - Faltan: " + entr.getValue().size() + " SMS");
		}
	}
	
/*
	public Conexion buscarRutaMaxGanancias(){
		Conexion bestcnx = null;				
		Iterator<Conexion> i = prepago.iterator();
		while(i.hasNext()){
			bestcnx = i.next(); 
			if(bestcnx.getPagados()>0){
				return bestcnx;
			}else if (bestcnx.isRecarga()){
				bestcnx.recargar();
				
				continue;	
			}else if(bestcnx.isExtramsgs()){
				postpago.add(bestcnx);
				Collections.sort(postpago,pagados);
			}
			i.remove();
		}
		
		return postpago.getFirst();

	}
	
	public Conexion buscarRutaRapida(){
		Conexion bestcnx = null;
		/*while(fast.hasNext()){
			bestcnx = fast.next(); 
			if(bestcnx.getPagados()>0){
				return bestcnx;
			}else if (bestcnx.isRecarga()){
				bestcnx.recargar();			
				continue;	
			}else if(bestcnx.isExtramsgs()){
				postpago.add(bestcnx);
				Collections.sort(postpago,pagados);
			}
			fast.remove();
		}
		
		return postpago.getFirst();

	}
 	*/
	
	
}
