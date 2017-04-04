package com.semm.core.conexiones;


import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.UsuarioDAO;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.Usuario;
import com.semm.core.sesiones.GeneradorMensajes;

public class ConexionConsola extends Conexion {

	
	PrintStream out;
	Console cons;
	
	/**
	 * 
	 */
	public ConexionConsola() {
		this.out = System.out;
		
	}

	@Override
	public void conectar() throws Exception {
		log.debug("Inicializando Conexion " + nombre +"...");
		this.cons = Console.getInstancia();
		if(cons!=null){
			Lector lect = new Lector();
			lect.start();
		}
		log.debug("Conexion Consola Activa.");
		
	}

	@Override
	public void desconectar() {

	}

	@Override
	public void enviar(NuevoMensaje m1) {
		
		CnxMensaje m = (CnxMensaje)m1;
		//		 Lets create a message for dispatch.
		// A message needs the recipient's number and the text. Recipient's number should always
		// be defined in international format.	
		
		long start = System.currentTimeMillis();
		long millis = (long)(Math.random()*2000) + 1000;
		try { 
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int rand = (int)(Math.random()*5);
		log.debug("Numero ramdom:" + rand);
		log.debug("Enviando mensaje para: " + m.getPara() + " msg:" + m.getContenido().getMsg());
		
		if(rand == 3){
				this.fallos++;
				m.setTipo(2);
				m.setRetries(m.getRetries()+1);
				log.error("Hubo error al enviar el mensaje para: " + m.getPara() + " cliente:" + m.getOwner().getUsername() + " intentos: " + m.getRetries());
						
				
		}
			
		
		
		if(m.getBlocked()>0){
			m.setTipo(2);
			m.setRetries(6);
			m.setSubtipo(m.getBlocked());
			log.debug("Mensaje Bloqueado! Razon:" + m.getBlocked() + " Para:" + m.getPara() + " MSG:" + m.getContenido().getMsg());
			return;
		} else if (!conectado){
			m.setTipo(2);
			m.setSubtipo(CnxMensaje.APAGANDO);
			m.setRetries(6);
			log.debug("Mensaje No Enviado: Conexion apagandose... Para:" + m.getPara() + " MSG:" + m.getContenido().getMsg());
			return;
		}
		
		out.println(m.getPara() + "|" + m.getContenido().getMsg() + "|" + m.getServicio() + "|" + m.getCliente() + "|" + m.isReport());
		
		
		m.setTipo(0);
		this.fallos = 0;
		
		if(!this.extramsgs)
			this.pagados--;
		
		if(m.getOwner().getTipo() >0)
			m.removeUserCredit();
		
		long res = (System.currentTimeMillis()-start);
		this.setTransrate(60000/((res>0)?res:1));
	}

	@Override
	public void recargar() {
		// TODO Auto-generated method stub

	}
	
	public void procesar(String from,String to,String msg){
		DetectaMensaje detect = getDtect();
		
		log.debug("Llego sms: " + from);
		
		from = from.replaceAll("\\+","");

		from = from.replaceAll("^584","04");
		from = from.replaceAll("^158","0416");
		from = from.replaceAll("^412","0412");
		
		log.debug("Remplazando todo: " + from);
		
		ReporteEntrega rep = esReporteEntrega(msg,from);
		if(rep != null){
			rep.setCnx(this);
			detect.reporteEntrante(rep);
			
		}else {
			CnxMensaje in = new CnxMensaje(from,this.nombre,this,new ContenidoSMS(msg));
			in.setTipo(1);
			in.setCnx(this);
			detect.mensajeEntrante(in);
		}

	}
	
	private ReporteEntrega esReporteEntrega(String text,String from) {
		ReporteEntrega rep = null;
		Pattern regex = Pattern.compile("Mensaje Entregado\\s*al\\s*\\d+\\s*a las\\s*(\\d{2}:\\d{2})\\s*del\\s*(\\d{2}/\\d{2}/\\d{4})");
		Matcher match = regex.matcher(text);
		if(match.find()){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String fecha = match.group(2) + " " + match.group(1);
			try {
				Date f = sdf.parse(fecha);
				rep = new ReporteEntrega(from,f);
			} catch (ParseException e) {
				log.error(e);
			}
			
		}
		return rep;	
	}

	@Override
	public void agregarMsgCola(NuevoMensaje m1) throws ConexionFullException, ConexionMuertaException {
		
		if(this.outbox.size() >= (int)this.transrate + DELAY)
			throw new ConexionFullException();
		
		if(this.fallos > NUMFALLOS)
			throw new ConexionMuertaException();
		
		Collection<NuevoMensaje> via = this.outbox;
		CnxMensaje m = (CnxMensaje)m1;
	
		
		if(m.getContenido().getMsg().length() > 145){
			
			ArrayList<String> parts = new ArrayList<String>(2);
			splitMsg(parts, m.getContenido().getMsg());
			int i = 1;
			for(String part : parts){
				ContenidoSMS cont = new ContenidoSMS(part + "("+(i++)+"/"+parts.size()+")");
				CnxMensaje mpart = new CnxMensaje(m.getPara(),m.getDe(),m.getCnx(),cont);
				mpart.setCliente(m.getCliente());
				mpart.setServicio(m.getServicio());
				mpart.setOwner(m.getOwner());
				via.add(mpart);
			}
		} else 
		via.add(m);
		
		
	}
	
	private void splitMsg(ArrayList<String> l,String msg) {
		int size = 140-5;
		
		if(msg.length()>size){
			String shorthead = "";
			
			String head = msg.substring(0,size);
			String tail = msg.substring(size);
			int lispc = head.lastIndexOf(' ');
			int ispc = tail.indexOf(' ');
			
			if(lispc == (size-1) || ispc == 0){
				shorthead = head.trim();
				tail = tail.trim();
			} else if (lispc < (size-1) && (ispc > 0) || (ispc == -1)){
				shorthead = head.substring(0,lispc);
				tail = head.substring(lispc+1) + tail;
				
			} 

			l.add(shorthead);
			splitMsg(l, tail);
		} else {
			l.add(msg);
		}
		
	
	}



	@Override
	public boolean verificarFalla() {
		return this.fallos >= NUMFALLOS;
	}

	@Override
	public void recuperar() {
		desconectar();
		try {
			conectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		UsuarioDAO udao = fab.getUsuarioDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		Usuario ow = udao.buscarPorId("semm", false);
		tx.commit();
		
		
		for(String recip:alarmrecips){
			CnxMensaje alarma = new CnxMensaje();
			alarma.setPara(recip);
			alarma.setContenido(new ContenidoSMS("Conexion: " + this.nombre + " se ha recuperado! Fecha: " + new Date()));
			alarma.setServicio("alarma_semm");
			alarma.setOwner(ow);
			alarma.setCnx(this);
			enviar(alarma);
			guardarMensaje(alarma);
			if(alarma.getTipo() == 0){
				recuperando=false;
				fallos = 0;
				return;
			}	
		}
		
		
	}

	class Lector extends Thread
	{
		public void run() {
			while(conectado){
				try {
					String from = Console.readString(nombre + " Ingrese TLF: ");
					from = GeneradorMensajes.verificarRecip(from,true);
					String msg = Console.readString(nombre + " Ingrese MSG: ");
					if(from!=null && msg !=null)
						procesar(from, nombre, msg);
					
				} catch (Exception e) {
					e.printStackTrace();
					log.error("Error procesando mensajes inbox: " + e);
					
				}
			}
		}
	}
}
