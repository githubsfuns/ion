package com.semm.core.comando.mud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.Normalizer.Form;

import org.apache.log4j.Logger;
import org.tempuri.Formulario;
import org.tempuri.WebServiceLocator;
import org.tempuri.WebServiceSoap;

import com.semm.core.comando.Comando;
import com.semm.core.comando.mp.LlamadaMp;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.NuevoMensaje;

public class LlamadaMUD extends Comando {

	private static Logger log = Logger.getLogger(LlamadaMUD.class);
	@Override
	public void ejecutar(NuevoMensaje m) {
		// TODO Auto-generated method stub
		CnxMensaje cnx = (CnxMensaje)m;
		WebServiceLocator wsl = new WebServiceLocator();
		
		 boolean ok = false;
	     int tries = 0;
	     
	     while(!ok && tries < 3){
	    	tries++;
			try {
				log.debug("Intento MUD: " + tries);
				
				WebServiceSoap smssoap = wsl.getWebServiceSoap();
				
				// Create a URL for the desired page URL
				String tlfOrigen= cnx.getPara();
			
				int indexkw = cnx.getContenido().getMsg().indexOf(" ");
				String msg = cnx.getContenido().getMsg();
				String restoMensaje ="",palabraClave="";
				if(indexkw > -1){
					palabraClave= msg.substring(0,indexkw);
					//restoMensaje=msg.substring(indexkw+1,msg.length());
					// Para que envie todo el mensaje
					restoMensaje = msg;
						
				} else
					palabraClave = msg;
				
				
				int f = smssoap.cargarMensajeIntegrador(restoMensaje, tlfOrigen, "SMS", "");
					
					
				
				ok = true;
				log.debug("Mensaje para MUD: " + cnx.getPara() + " - " + cnx.getContenido().getMsg() + " - f: " + f);
				
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				if(tries >= 3){

				}
			}
	     } 
	      
	}

}
