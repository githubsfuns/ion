package com.semm.core.conexiones;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.UsuarioDAO;
import com.semm.core.conexiones.bb.ConexionBlackberryPush;
import com.semm.core.conexiones.comparadores.ComparadorCarga;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.Usuario;

public class EnrutadorBroadcast extends Enrutador implements Runnable{
	
	private List<GrupoConexiones> activas;
	private LinkedList<Conexion> allcnx;
	private Comparator<Conexion> trans;
	private ConcurrentHashMap<Usuario,ConcurrentLinkedQueue<CnxMensaje>>  prioriy_queue,queue;
	private HashMap<GrupoConexiones, Iterator<Conexion>> cnx_grps;
	private LinkedList<CnxMensaje> buffer;
	private boolean activo;
	public static Logger log = Logger.getLogger(EnrutadorBroadcast.class);
	

	/**
	 * 
	 */
	public EnrutadorBroadcast(List<GrupoConexiones> active){
		
	}
	public EnrutadorBroadcast(List<GrupoConexiones> active,LinkedList<Conexion> allcnx){
		this.activas = active;
		this.allcnx = allcnx;
		trans = new ComparadorCarga();
		prioriy_queue = new ConcurrentHashMap<Usuario, ConcurrentLinkedQueue<CnxMensaje>>();
		queue = new ConcurrentHashMap<Usuario, ConcurrentLinkedQueue<CnxMensaje>>();
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
	
	private int calcularCupo(){
		int cupo = 0;
		for(Conexion cnx : allcnx){
			cupo += (int)cnx.getTransrate();
		}
		return cupo;
	}
	
	public void addMensaje(CnxMensaje sms){
		ConcurrentHashMap<Usuario, ConcurrentLinkedQueue<CnxMensaje>> q = queue;
		ConcurrentLinkedQueue<CnxMensaje> lista;
		
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
		
		if(lista.size() <= 1){
			log.debug("Lista de " + sms.getOwner().getUsername() + " baja activando buffer");
			synchronized (this) {
				this.notifyAll();
			}
		}
		
		//guardarMensaje(sms);
	}
	
	private void distribuirPorCliente(){
		int cupo =  calcularCupo();
		int cupodisp = cupo - buffer.size();
		int clientes = queue.keySet().size();
		int clientesprio = prioriy_queue.keySet().size();
		log.debug("TX total: " + cupo);
		log.debug("Cupo del Bcast Buffer: " + cupodisp  + " Mensajes en Broadcast Buffer: " + buffer.size());
		log.debug("Clientes: " + clientes + " Clientes prioritarios: " + clientesprio);

		int bloque = (clientes>0 ) ? cupodisp/clientes : cupodisp;
		int bloqueprio = (clientesprio>0 ) ? cupodisp/clientesprio : cupodisp;
		
		log.debug("Bloque: " + bloque + " Bloque Prioritario:" + bloqueprio);
		
		if(bloque <= 0 || bloqueprio <= 0)
				return;
			
		if(clientesprio > 0)
			distribuir(bloqueprio, prioriy_queue);
		 else
			distribuir(bloque, queue);

	}
	
	private void distribuir(int bloque, ConcurrentHashMap<Usuario,ConcurrentLinkedQueue<CnxMensaje>> cola){
		for(ConcurrentLinkedQueue<CnxMensaje>  lm: cola.values()){
			
			int i = 0;
			CnxMensaje last = null;
			Iterator<CnxMensaje> it = lm.iterator();
			while(it.hasNext() && i < bloque){
				last = it.next();
				buffer.add(last);
				it.remove();
				i++;
			}
			if(!it.hasNext()){
				log.debug("Cliente " + last.getOwner().getUsername() + " ingreso TODO: " + i + "Broadcast MSG");
				cola.remove(last.getOwner());
			}
			if(last!=null)
				log.debug("Cliente " + last.getOwner().getUsername() + " ingresando al Buffer: "  + i + "Broadcast MSG");
			
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
			
			if(fails > this.allcnx.size()){
				log.debug("Todas las conexiones estan llenas o muertas! Fallaron: " + fails + " veces");
				break;
			}
			
			
		}
	}
	
	public void run(){
		log.debug("Inicializando Enrutador Broadcast...");
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
			
			if(bestcnx.isRecuperando())
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
