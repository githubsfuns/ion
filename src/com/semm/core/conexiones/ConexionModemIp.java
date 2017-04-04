package com.semm.core.conexiones;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.smslib.GatewayException;
import org.smslib.ICallNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;

import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.Message.MessageEncodings;
import org.smslib.Message.MessageTypes;
import org.smslib.modem.IPModemGateway;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.UsuarioDAO;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.Usuario;
import com.semm.core.sesiones.GeneradorMensajes;



public class ConexionModemIp extends Conexion implements IInboundMessageNotification, ICallNotification{
	
	
	transient  Logger log = Logger.getLogger(ConexionModemIp.class.getName()); 
	private Service srv;
	private String host,device,model;
	private int port,maxlength;
	

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}



	@Override
	public void conectar() throws Exception {
		
			log.debug("Inicializando Conexion GSM...");
			srv = new Service();
			srv.S.DEBUG = true;
			srv.S.SERIAL_POLLING = true;
			srv.S.SERIAL_TIMEOUT = 6000;
			
			IPModemGateway gateway = new IPModemGateway(this.nombre,host,port,device,model);
			gateway.setInbound(true);
			gateway.setOutbound(true);
			gateway.setInboundNotification(this);
			gateway.setCallNotification(this);
			gateway.setSimPin("0000");
			
			srv.addGateway(gateway);
			
			log.debug("Conectando Con Modem...");
			srv.startService();
			
		
			
			// Lets get info about the GSM device...
			log.debug("Mobile Device Information: ");
			log.debug("Modem Information:");
			log.debug("  Manufacturer: " + gateway.getManufacturer());
			log.debug("  Model: " + gateway.getModel());
			log.debug("  Serial No: " + gateway.getSerialNo());
			log.debug("  SIM IMSI: " + gateway.getImsi());
			log.debug("  Signal Level: " + gateway.getSignalLevel() + "%");
			log.debug("  Battery Level: " + gateway.getBatteryLevel() + "%");
			log.debug("Conexion " + this.nombre + " activa");


	}

	@Override
	public void desconectar() {
//		 Disconnect - Don't forget to disconnect!
		log.debug("Desconectando: " + this.getNombre());
		try {
			srv.stopService();
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

	@Override
	public void enviar(NuevoMensaje m1) {
		
	
		CnxMensaje m = (CnxMensaje)m1;
//		 Lets create a message for dispatch.
		// A message needs the recipient's number and the text. Recipient's number should always
		// be defined in international format.	
		
		
		String recip  = GeneradorMensajes.verificarRecip(m.getPara(),m.getOwner().isLocales());
		
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
		} else if (recip == null){
			m.setTipo(2);
			m.setSubtipo(CnxMensaje.FORMATO_INVALIDO);
			m.setRetries(6);
			log.debug("Mensaje No Enviado: Formato del TLF invalido... Para:" + m.getPara() + " MSG:" + m.getContenido().getMsg());
			return;
		}
		
		log.debug("Para: " + m.getPara() + " MSG:" + m.getContenido().getMsg());
		OutboundMessage msg = new OutboundMessage(recip, m.getContenido().getMsg());

		msg.setEncoding(MessageEncodings.ENC7BIT) ;
		msg.setStatusReport(m.isReport());
		msg.setValidityPeriod(24);
		msg.setGatewayId(this.nombre);
		msg.setFlashSms(true);


		try {
			long start = System.currentTimeMillis();
			srv.sendMessage(msg);
				
			long res = (System.currentTimeMillis()-start);
			this.setTransrate(60000/((res>0)?res:1));
			float tx = 60000/((res>0)?res:1);
			tx = (tx>20)? 20 : tx;
			this.setTransrate(tx);
			
			if(msg.getMessageStatus() != OutboundMessage.MessageStatuses.SENT)
				throw new Exception();
			
			m.setTipo(0);
			
			if(!this.extramsgs)
				this.pagados--;

			if(m.getOwner().getTipo() >0)
				m.removeUserCredit();
			
			this.fallos = 0;
			
		}catch (RuntimeException rte){
			m.setTipo(2);
			m.setRetries(m.getRetries()+1);
			this.fallos+=5;
			log.error("Hubo error al enviar el mensaje para: " + m.getPara() + " cliente:" + m.getOwner().getUsername() + " intentos: " + m.getRetries());
			log.error(rte);
		}  catch (Exception e) {
			m.setTipo(2);
			m.setRetries(m.getRetries()+1);
			this.fallos++;
			log.error("Hubo error al enviar el mensaje para: " + m.getPara() + " cliente:" + m.getOwner().getUsername() + " intentos: " + m.getRetries());
			log.error(e);
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
	public void recargar() {
		
		
	}

	@Override
	public void agregarMsgCola(NuevoMensaje m1) throws ConexionFullException, ConexionMuertaException {
		
		if(this.outbox.size() >= (int)this.transrate + DELAY)
			throw new ConexionFullException();
		
		if(verificarFalla())
			throw new ConexionMuertaException();
		
		Collection<NuevoMensaje> via =this.outbox;
		CnxMensaje m = (CnxMensaje)m1;

		if(m.getContenido().getMsg().length() > maxlength){
			ArrayList<String> parts = new ArrayList<String>(2);
			splitMsg(parts, m.getContenido().getMsg());
			int i = 1;
			for(String part : parts){
				ContenidoSMS cont = new ContenidoSMS(part + "("+(i++)+"/"+parts.size()+")");
				CnxMensaje mpart = new CnxMensaje(m.getPara(),m.getDe(),m.getCnx(),cont);
				mpart.setCliente(m.getCliente());
				mpart.setServicio(m.getServicio());
				mpart.setOwner(m.getOwner());
				mpart.setBlocked(m.getBlocked());
				mpart.setReport(m.isReport());
				mpart.setProgramado(m.isProgramado());
				via.add(mpart);
			}
		} else 
			via.add(m);
	
	}

	private void splitMsg(ArrayList<String> l,String msg) {
		int size = maxlength-5;
		
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

	public int getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	
	public void recuperar() {
		desconectar();
		try {
			conectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
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
		*/
	}

	@Override
	public boolean verificarFalla() {
		return this.fallos >= NUMFALLOS;
	}

	

	public void process(String gtwId, String callerId) {
		DetectaMensaje detect = getDtect();
		Llamada call = new Llamada(callerId,new Date(),this);
		detect.llamadaEntrante(call);
	}

	public void procesar(InboundMessage message){
		DetectaMensaje detect = getDtect();
		String from = message.getOriginator().trim();
		log.debug("Llego sms: " + from);
		
		from = from.replaceAll("\\+","");

		from = from.replaceAll("^584","04");
		from = from.replaceAll("^158","0416");
		from = from.replaceAll("^412","0412");
		
		log.debug("Remplazando todo: " + from);
		
		ReporteEntrega rep = esReporteEntrega(message.getText(),from);
		if(rep != null){
			rep.setCnx(this);
			detect.reporteEntrante(rep);
			
		}else {
			CnxMensaje in = new CnxMensaje(from,message.getGatewayId(),this,new ContenidoSMS(message.getText()));
			in.setTipo(1);
			in.setCnx(this);
			detect.mensajeEntrante(in);
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void process(String gtwId, MessageTypes msgType, InboundMessage msg) {
		// TODO Auto-generated method stub
		
	}


	
}
