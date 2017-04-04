package com.semm.core.comando;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsLocator;
import ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap;
import ve.com.outsoft.Mabe.WebServices.SmsLocator;
import ve.com.outsoft.Mabe.WebServices.SmsSoap;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.ProductoDAO;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;

public class LlamadaOjo extends Comando {
	
	private static Logger log = Logger.getLogger(LlamadaOjo.class);
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	private String servidor;
	Pattern regex_visita = Pattern.compile("f\\s*(\\d+)\\s*(.*)",Pattern.CASE_INSENSITIVE);
	

	public LlamadaOjo(String servidor) {
		super();
		this.servidor = servidor;
	}


	@Override
	public void ejecutar(NuevoMensaje m) {
		
		final NuevoMensaje nm = m;
		
		Thread tojo = new Thread(){	
			public void run() {

		log.debug("Nuevo Thread Ojo Electoral");
		String sku,msg;
		CnxMensaje sms = (CnxMensaje)nm;
	
		Responder responder = new Responder();
		Matcher match;
		match = regex_visita.matcher(sms.getContenido().getMsg());
		try {
		if(match.find()){
			
			sku = match.group(1);
			msg = match.group(2);
			// TODO: Se acorto la expresion regular y ahora no contemplafallas
		
			log.debug("Formato Formulatio OK ... Procesando Formulario: " + sku + " - " + msg + " ");
			
		
			URL url = new URL(servidor+"/registroMovil.php?celular="+URLEncoder.encode(sms.getPara())+
					"&id=" + sku + "&mensaje="+URLEncoder.encode(msg));
			log.debug("Request de MP: " + url.toString()+"   -");
			// Read all the text returned by the server 
//			URLConnection conn = url.openConnection();
//			conn.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str,res="";
			while ((str = in.readLine()) != null) { 
				// str is one line of text; readLine() strips the newline character(s)
				log.debug("Respuesta de OJO: " + sms.getPara() + " - " + str + " - ");
				res+=str;
				}
			in.close(); 
			
			if (res.indexOf("coordinador") > -1){
				
				CnxMensaje alarma = new CnxMensaje();
				alarma.setPara("04163044506");
				alarma.setContenido(new ContenidoSMS(res));
				alarma.setServicio(sms.getServicio());
				alarma.setCliente(sms.getCliente());
				alarma.setOwner(sms.getOwner());
				alarma.setCnx(sms.getCnx());
				alarma.setGrpcnx(sms.getGrpcnx());
				mcnx.enviaraCola(alarma, sms.getCnx());
				
			}
			
			
			responder.setResp(res);
			
			log.debug("Formulario Done");
		
			} else {
				responder.setResp("Disculpe el formato del mensaje es invalido. F(Numero formulario) (Preguntas y Respuestas)");
			}
		} catch (Exception e) {
			responder.setResp("Ha ocurrido un error. Por favor intente de nuevo");
		}
		
			responder.ejecutar(sms);
			
			}
		};
		
		tojo.start();
	      
	}

}
