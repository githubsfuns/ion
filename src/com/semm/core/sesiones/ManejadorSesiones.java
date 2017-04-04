package com.semm.core.sesiones;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.ManejadorBd;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.MensajeProgramadoDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.UsuarioDAO;
import com.semm.core.conexiones.Llamada;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.ReporteEntrega;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.MensajePorProcesar;
import com.semm.core.servicios.MensajeProgramado;
import com.semm.core.servicios.Servicio;
import com.semm.core.servicios.ServicioBroadCast;
import com.semm.core.servicios.SesionLog;
import com.semm.core.servicios.Usuario;


public class ManejadorSesiones {
	
	private static final int MAXREPORTS = 100,MAXCALLS = 200;

	public static Logger log = Logger.getLogger(ManejadorSesiones.class);
	
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	
	private boolean activo = true;
	private ConcurrentHashMap <String,Sesion> sesiones_activas;
	private static ManejadorSesiones instance = null;
    private GeneradorMensajes gen;
    private ConcurrentLinkedQueue<Usuario> mpg;
	
	public static ManejadorSesiones getInstancia(){
		if(instance == null){
			instance = new ManejadorSesiones();
		}
		return instance;
	}
	/**
	 * @param sesiones_activas
	 */
	private ManejadorSesiones() {
		this.sesiones_activas = new ConcurrentHashMap<String, Sesion>();
		this.gen = new GeneradorMensajes();
		this.gen.setTipo(2);
		this.mpg = new ConcurrentLinkedQueue<Usuario>();
	}
	

	public void procesarMensaje(NuevoMensaje m){
		Sesion sesion = null;
		boolean multiprocess = false;
		for(String recip:m.getRecips()) {
			log.debug("Buscando recip " + recip);
			sesion = this.sesiones_activas.get(recip);
			if((sesion == null && !(m instanceof ReporteEntrega))||(  m.getServicio() !=null && !m.getServicio().equalsIgnoreCase(sesion.getSvc().getNombre()))){
				//|| (!m.getCliente().equalsIgnoreCase(sesion.getCliente()))
				log.debug((sesion == null) + " - " + (((sesion!=null)?sesion.getCliente():"") != m.getCliente()) +" - " + (  m.getServicio() !=null && !m.getServicio().equalsIgnoreCase((sesion!=null)?sesion.getSvc().getNombre():"")));
				log.debug("Sesion: "+ sesion + " Cliente: " + m.getCliente()+ " - " + ((sesion!=null)? sesion.getCliente():"-" )+ "Recip:" + recip + " Servicio: " + m.getServicio() + " - " + ((sesion !=null) ? sesion.getSvc().getNombre() : "" ));
				sesion = crearSesion(m,recip); 
			}

			if(sesion!=null && sesion.getSvc()!=null){
				log.debug("Procesando Mensaje en Sesion. State " +sesion.getState() + " Servicio: "+ sesion.getSvc());
				sesion.procesar(m);
				if(sesion.getSvc() instanceof ServicioBroadCast)
					multiprocess = true;
			}
		}	
	}



	private Sesion crearSesion(NuevoMensaje m,String recip) {
		log.debug("Creando session");
		FabricaSesiones fses = FabricaSesiones.getFabrica(FabricaSesiones.SVC);
		Sesion ses = fses.getSesion(m,recip);
		if(ses != null && ses.getSvc() !=null){
			log.debug("Sesion Creada: " + recip + " - " + m.getOwner().getUsername() + " - " + ses.getSvc().getNombre());
			sesiones_activas.put(recip,ses);
		}
		return ses;
	}


	public void init() {
		
		log.debug("Recuperando mensajes en proceso...");
		recuperar();
		log.debug("Recuperado OK...");
		log.debug("Manejador sesiones inicializando lector de mensajes");

		Thread mensajes = new Thread(){	
			public void run() {
				while(activo){
					try {
						log.debug("Procesando mensajes nuevos:");
						buscarMensajes(mcnx.getInbox());
						log.debug("Procesando reportes nuevos: ");
						buscarReportes(mcnx.getReports());
						log.debug("Procesando llamadas nuevas: ");
						buscarLlamadas(mcnx.getCalls());
						log.debug("Procesando mensajes programados");
						revisarMensajes();
						log.debug("Actualizando Saldo");
						actualizarSaldo();
						Thread.sleep(5000);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("Error procesando mensajes inbox: " + e);
						
					}
				}
			}
		};
		mensajes.setPriority(7);
		mensajes.start();

		//Limpia las sesiones expiradas
		
		Thread sesiones = new Thread(){	
			public void run() {
				while(activo){
					try {
							log.debug("Limpiando sesiones...");
							Iterator<Entry<String,Sesion>> it = sesiones_activas.entrySet().iterator();
							while(it.hasNext()){
								Entry<String,Sesion> me = it.next();
								Sesion s = me.getValue();
								log.debug("Sesion activa: "+ me.getKey() +" -  " + s.getSvc().getNombre());
								long time = s.getSvc().getTimeout() + s.getUltima_actividad();
								if((time <= System.currentTimeMillis())	|| s.getState() instanceof Finalizada){
									try {
										log.debug("Sesion expirada..."+  me.getKey() + " SVC: " + s.getSvc().getNombre());	
										s.expired();
									} catch(Exception e){
										log.error(e);
									}
									it.remove();
								}
							}
							log.debug("ION : " + sesiones_activas.size() + " Sesiones Activas");
							mcnx.getStatusActivas();
							
						Thread.sleep(60000);
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e);
					}
				}
			}
		};
		
		sesiones.start();
		

	}
	
	private void recuperar() {
		ManejadorBd mbd = ManejadorBd.getInstancia();
		List<MensajePorProcesar> lmpp = mbd.buscarMensajesPorProcesar();
		for(MensajePorProcesar mpp : lmpp){
			UserTransData utd = mbd.generar(mpp);
			mcnx.mensajeEntrante(utd);
			mbd.borrar(mpp);
		}
		
		
	}
	protected void buscarLlamadas(ConcurrentLinkedQueue<Llamada> calls) {
	   ManejadorBd mbd = ManejadorBd.getInstancia();
		Iterator<Llamada> in = calls.iterator();
		int max = 0;
		while(in.hasNext() && max < MAXCALLS){
			Llamada m = in.next();
			
			if(this.sesiones_activas.containsKey(m.getTlf()))
				m.setUsuario(this.sesiones_activas.get(m.getTlf()).getOwner());
			else{
				SesionLog slog = mbd.buscarSesion(m);
				if(slog!=null)
					m.setUsuario(slog.getUsuario());
				else
					m.setUsuario(mbd.getOwner("semm"));
			
			}
			
			mbd.guardarLlamada(m);
			in.remove();
		}
		max++;
	}
		
	public void apagar(){
		if(mcnx.getInbox().size() > 0){
			log.debug("Apagando ... quedan mensajes por procesar...");
			log.debug("Procesando mensajes nuevos:");
			buscarMensajes(mcnx.getInbox());
			log.debug("Procesando reportes nuevos: ");
			buscarReportes(mcnx.getReports());
			log.debug("Procesando llamadas nuevas: ");
			buscarLlamadas(mcnx.getCalls());
			log.debug("Procesando mensajes programados");
			revisarMensajes();
			log.debug("Actualizando Saldo");
			actualizarSaldo();
		}
		this.activo=false;
	}
	
	public void agregarParaActualizar(Usuario u){
		log.debug("Actualizando Saldo de: " + u.getUsername() + " a " + u.getCredits() + " ObjID: "  + u);
		if(!mpg.contains(u))
			mpg.add(u);
	}
	
	private void actualizarSaldo() {
		Iterator<Usuario> i = mpg.iterator();
		while(i.hasNext()){
			actualizarUsuario(i.next());
			i.remove();
		}
		
	}
	
	private void actualizarUsuario(Usuario user){
			log.debug("Usuario: " + user.getUsername() + " Tiene saldo hasta ahora de : " + user.getCredits() + " ObjID: " + user);
			FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
			TxDAO tx = fab.getTx();
			try {
				UsuarioDAO udao = fab.getUsuarioDAO();
				tx.beginTx();
				udao.guardar(user);
				tx.commit();
			} catch (RuntimeException e){
				log.error(e);
				e.printStackTrace();
				tx.rollback();
				tx.cerrarSesion();
			}
	}
	
	public void buscarMensajes(ConcurrentLinkedQueue<NuevoMensaje> box){
		Iterator<NuevoMensaje> in = box.iterator();
			while(in.hasNext()){
				NuevoMensaje m = in.next();
				try {
					log.debug("Procesando Mensaje..." + m.toString());
					procesarMensaje(m);
				} catch(RuntimeException rte){
					rte.printStackTrace();
					log.error(rte,rte);
				}finally {
					in.remove();
				}
			}
	}
	
	private void buscarReportes(ConcurrentLinkedQueue<ReporteEntrega> box) {
		FabricaDAO fabdao = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		MensajeDAO mdao = fabdao.getMensajeDAO();
		TxDAO tx = fabdao.getTx();
		Iterator<ReporteEntrega> in = box.iterator();
		int max = 0;
		while(in.hasNext() && max < MAXREPORTS ){
			ReporteEntrega m = in.next();
			try {
				tx.beginTx();
				log.debug("Procesando Reporte..." + m.getRecip());
				
				MensajeLog mlog = mdao.buscarEnviadoPorMobile(m.getRecip());
				if(!mlog.isEntregado()){
					
					mlog.setFechaentrega(m.getFechahora());
					mlog.setEntregado(true);
					mdao.guardar(mlog);
					log.debug("Guardado Reporte..." + m.getRecip() + " - " + m.getFechahora());
				}
				
				tx.commit();
				tx.evict(mlog);
				
				log.debug("Procesado..." + m.getRecip() + " - " + mlog.getFechaentrega());
				
				if(sesiones_activas.get(m.getRecip())!=null ){
					log.debug("Agregando Reporte Entrante a Sesion");
					mcnx.mensajeEntrante(m);
				}
				
				
			} catch(RuntimeException rte){
				log.warn(rte,rte);
				tx.rollback();
				tx.cerrarSesion();
				
			}finally {
				in.remove();
			}
			max++;
		}
		
	}
	
	private void revisarMensajes() {
		FabricaDAO fab =  FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		MensajeProgramadoDAO mdao = fab.getMensajeProgramadoDAO();
		TxDAO tx = fab.getTx();
		try {
			
			
			List<MensajeProgramado> activos = mdao.buscarActivos();
			
			
			for(MensajeProgramado act : activos){
				
				String[] msgs = act.getTlfs().split(",|;|-| ");
				long id = act.getIdlist();
				String body = act.getBody();
				gen.setReport(act.isReporte());
				
				List<NuevoMensaje> webmsg = new ArrayList<NuevoMensaje>();
				try {
					webmsg.addAll(gen.generar(msgs,body,act.getCliente(),act.getServicio(),act.isLocales()));
				}catch (RuntimeException e){
					log.error(e);
				}
				if(id>0)
					webmsg.addAll(gen.generarLista(id,body,act.getCliente(),act.getServicio(),act.isLocales()));
				
				
				
				for(NuevoMensaje m: webmsg)
					mcnx.mensajeEntrante(m);
				
				tx.beginTx();
				
				if(act.getRecurrencia()>=0 && act.getCada_rec() > 0){
					calcularProximo(act);
					act.setTimes(act.getTimes()+1);
					mdao.guardar(act);
				}else {
					mdao.borrar(act);
				}
				
				tx.commit();
				tx.evict(act);
			}
			
			
		} 
		catch (RuntimeException e){
			e.printStackTrace();
			log.error(e);
			tx.rollback();
			tx.cerrarSesion();
		} 
	}
	
	
	private void calcularProximo(MensajeProgramado mp){
		
		Calendar c = Calendar.getInstance();
		c.setLenient(true);
		c.setTime(mp.getHora());
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		

		int days;
		
		switch (mp.getRecurrencia()) {
			case 0:
				c.add(Calendar.DATE, mp.getCada_rec());
				break;
			case 1:
				
				c.add(Calendar.WEEK_OF_YEAR, mp.getCada_rec());
				
				/*
				 
				 log.debug("CASE: 1 Buscando proximo dia se la semana:");
				days = getDays(c,mp);
				
				if(days == -1) {
					log.debug("Se acabo la semana entonces nos movemos :" + mp.getCada_rec() + " semanas");
					c.add(Calendar.WEEK_OF_YEAR,mp.getCada_rec());
					c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
					days = getDays(c,mp);
				}
				log.debug("EL Proximo dia se la semana es:" + days);
				c.set(Calendar.DAY_OF_WEEK, days);
				*/
				
				break;
	
			case 2:
	
				
				
					//log.debug("Se acabo el mes entonces nos movemos :" + mp.getCada_rec() + " del mes siguiente");
					c.add(Calendar.MONTH,mp.getCada_rec());
					//Should be obvious
					//c.set(Calendar.DAY_OF_MONTH,);
				/*
					log.debug("Buscamos otro dia de esta semana #" +mp.getNumsemana()+" del mes");
					days = getDays(c,mp);
					
					if(days == -1) {
						log.debug("No hay mas dias esta semana vamos al mes siguiente");
						c.add(Calendar.MONTH,1);
						c.set(Calendar.DAY_OF_MONTH, 1);
						
						days = getDays(c,mp);
						log.debug("Nos vamos para el dia: " + days);
						
						c.set(Calendar.DAY_OF_WEEK, days);
						
						if(mp.getNumsemana()==5){
							
							int maxdays = c.getActualMaximum(Calendar.DAY_OF_WEEK_IN_MONTH);	
							log.debug("La cantidad de dias:" + maxdays);
							
							c.set(Calendar.DAY_OF_WEEK_IN_MONTH,maxdays);
						} else {
							log.debug("Nos vamos para el :" + mp.getNumsemana() + " del mes");
							c.set(Calendar.DAY_OF_WEEK_IN_MONTH,mp.getNumsemana());
						}
					} else {
						c.set(Calendar.DAY_OF_WEEK, days);
					}
					
				}
				*/
				break;
			}
		
		log.debug("NEXT DAY es:" + c.getTime());
		mp.setHora(c.getTime());
	}
	
	
	
	private int getDays(Calendar c,MensajeProgramado mp){

		for(int day = c.get(Calendar.DAY_OF_WEEK);day<9;day++){
			log.debug("Proximo Dia: " + day);
			
			switch (day){			
				case Calendar.MONDAY:
					if(mp.isL())
						return day;
					break;
				case Calendar.TUESDAY:
					if(mp.isM())
						return day;
					break;
				case Calendar.WEDNESDAY:
					if(mp.isMie())
						return day;
					break;
				case Calendar.THURSDAY:
					if(mp.isJ())
						return day;
					break;
				case Calendar.FRIDAY:
					if(mp.isV())
						return day;
					break;
				case Calendar.SATURDAY:
					if(mp.isS())
						return day;
					break;
				case 8:
					if(mp.isD())
						return Calendar.SUNDAY;
					break;
			}
		}
		return -1;	
	}
	
	
	
}
