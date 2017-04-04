package com.semm.core.comando.mp;

import java.io.*;
import java.net.*;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsLocator;
import ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap;
import ve.com.outsoft.Mabe.WebServices.SmsLocator;
import ve.com.outsoft.Mabe.WebServices.SmsSoap;

import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;

public class LlamadaMp extends Comando {
	
	private static Logger log = Logger.getLogger(LlamadaMp.class);
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	private String servidor;
	

	public LlamadaMp(String servidor) {
		super();
		this.servidor = servidor;
	}


	@Override
	public void ejecutar(NuevoMensaje m) {
		
		 CnxMensaje cnx = (CnxMensaje)m;
		 RecepcionSmsLocator recp = new RecepcionSmsLocator();
		
		 
	     RecepcionSmsSoap smssoap;
	     boolean ok = false;
	     int tries = 0;
	     
	     while(!ok && tries < 3){
	    	tries++;
			try {
				log.debug("Intento: " + tries);
				
				smssoap = recp.getRecepcionSmsSoap();
				
				// Create a URL for the desired page URL
				String tlfOrigen= cnx.getPara();
			
				int indexkw = cnx.getContenido().getMsg().indexOf(" ");
				String msg = cnx.getContenido().getMsg();
				String restoMensaje ="",palabraClave="";
				if(indexkw > -1){
					palabraClave= msg.substring(0,indexkw);
					restoMensaje= URLEncoder.encode(msg.substring(indexkw+1,msg.length()));
						
				} else
					palabraClave = msg;
				
				
				
					URL url = new URL(servidor+"/movilplus.phoenix.services.http/receiver.do?" +
							"operator=100&j_username=@ds01_&j_password=qwerty&content=" +
							"<ProcessMORequest><source>"+tlfOrigen+"</source><destination>0800MOVIL</destination>" +
							"<keyword>"+palabraClave+"</keyword>" +
							"<parameters>"+restoMensaje+"</parameters></ProcessMORequest>");
					log.debug("Request de MP: " + url.toString()+"   -");
					// Read all the text returned by the server 
//					URLConnection conn = url.openConnection();
//					conn.connect();
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
					String str;
					while ((str = in.readLine()) != null) { 
						// str is one line of text; readLine() strips the newline character(s)
						log.debug("Respuesta de MP: " + cnx.getPara() + " - " + str + " - ");
						}
					in.close(); 
					//fin
					
				
				ok = true;
				log.debug("Llamada a MP: " + cnx.getPara() + " - " + cnx.getContenido().getMsg() + " - " + ok);
				
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				if(tries >= 3){
//					CnxMensaje reply2 = new CnxMensaje();
//					reply2.setPara("0412915");
//					reply2.setCnx(cnx.getCnx());
//					reply2.setReport(false);
//					reply2.setProgramado(cnx.isProgramado());
//					reply2.setContenido(new ContenidoSMS("Erro con el WS de MP: " + e.getMessage()));
//					reply2.setServicio(cnx.getServicio());
//					reply2.setCliente(m.getCliente());
//					reply2.setOwner(m.getOwner());
//					mcnx.enviaraCola(reply2,cnx.getCnx());
				}
			}
	     } 
	      
	}

}
