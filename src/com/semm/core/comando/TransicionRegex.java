package com.semm.core.comando;

import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.ManejadorServicios;
import com.semm.core.servicios.NotificacionEntrada;

public class TransicionRegex extends Transicion {

	private NotificacionEntrada in;
	public static Logger log = Logger.getLogger(TransicionRegex.class);


	@Override
	public Comando getNxtCmd(NuevoMensaje msg) {
		
		if (msg instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) msg;
			
			for(Entry<String,Comando> s : in.getTransitions().entrySet()){
				log.debug("Comprobando : " + s.getKey());
				 if(Pattern.compile(s.getKey()).matcher(sms.getContenido().getMsg()).find()){
					 log.debug("Hizo match : " + s.getKey());
					 return s.getValue();
				 }
			}		 	
			log.debug("Devolviendo Default: " + in.getDef());
			return in.getDef();
		}
		return null;
	}

	public NotificacionEntrada getIn() {
		return in;
	}

	public void setIn(NotificacionEntrada in) {
		this.in = in;
	}


}
