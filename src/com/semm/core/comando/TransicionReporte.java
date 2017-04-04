package com.semm.core.comando;

import org.apache.log4j.Logger;

import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.ReporteEntrega;

public class TransicionReporte extends Transicion {

	
	public static Logger log = Logger.getLogger(TransicionReporte.class);
	public Comando cmdnext;

	@Override
	public Comando getNxtCmd(NuevoMensaje msg) {
		
		if (msg instanceof ReporteEntrega) {
			ReporteEntrega sms = (ReporteEntrega) msg;
			
			return cmdnext;		 	
			
		}
		log.debug("Reporte Invalido");
		return null;
	}

	public Comando getCmdnext() {
		return cmdnext;
	}

	public void setCmdnext(Comando cmdnext) {
		this.cmdnext = cmdnext;
	}
	
	




}
