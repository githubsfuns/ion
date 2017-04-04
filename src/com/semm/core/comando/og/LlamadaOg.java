package com.semm.core.comando.og;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import ve.com.outsoft.Mabe.WebServices.SmsLocator;
import ve.com.outsoft.Mabe.WebServices.SmsSoap;

import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;

public class LlamadaOg extends Comando {
	
	private static Logger log = Logger.getLogger(LlamadaOg.class);
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();

	@Override
	public void ejecutar(NuevoMensaje m) {
		
		 CnxMensaje cnx = (CnxMensaje)m;
		 SmsLocator smsloc = new SmsLocator();
	     SmsSoap smssoap;
	     boolean ok = false;
	     int tries = 0;
	     
	     while(!ok && tries < 3){
	    	tries++;
			try {
				log.debug("Intento: " + tries);
				smssoap = smsloc.getsmsSoap();
				ok = smssoap.recibir(cnx.getPara(),cnx.getContenido().getMsg());
				log.debug("Llamada a OG: " + cnx.getPara() + " - " + cnx.getContenido().getMsg() + " - " + ok);
				
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
				if(tries >= 3){
					CnxMensaje reply2 = new CnxMensaje();
					reply2.setPara("0412915");
					reply2.setCnx(cnx.getCnx());
					reply2.setReport(false);
					reply2.setProgramado(cnx.isProgramado());
					reply2.setContenido(new ContenidoSMS("Erro con el WS de OG: " + e.getMessage()));
					reply2.setServicio(cnx.getServicio());
					reply2.setCliente(m.getCliente());
					reply2.setOwner(m.getOwner());
					mcnx.enviaraCola(reply2,cnx.getCnx());
				}
			}
	     } 
	      
	}

}
