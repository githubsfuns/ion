package com.semm.core.comando;

import org.apache.log4j.Logger;

import com.semm.core.conexiones.NuevoMensaje;

public class EjecutarTransicion extends Comando {
	Logger log = Logger.getLogger(EjecutarTransicion.class);
	Transicion tr;
	@Override
	public void ejecutar(NuevoMensaje m) {
		
		tr.guardarMensaje(m);
		
		Comando cmd = tr.getNxtCmd(m);
		
		log.debug("Comando a Ejecutar: " + cmd);
		log.debug("Es nulo? " + (cmd!=null));
		if(cmd!=null){
			cmd.setS(s);
			cmd.ejecutar(m);
			setTransicion(cmd.getTransicion());
		}
		
		
	}
	public Transicion getTr() {
		return tr;
	}
	public void setTr(Transicion tr) {
		this.tr = tr;
	}

}
