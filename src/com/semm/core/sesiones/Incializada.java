package com.semm.core.sesiones;

import org.apache.log4j.Logger;

import com.semm.core.comando.Comando;
import com.semm.core.comando.Transicion;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;

public class Incializada extends Estado {
	static Logger log =  Logger.getLogger(Incializada.class);
	@Override
	public void handleMensaje(NuevoMensaje m, Sesion s) {

		if(s.getSvc()== null){
			log.debug("No se encontro servicio");
			s.setState(new Finalizada());
			return;
		}

		Comando cmd = s.getSvc().getRootcmd();
		
		log.debug("Estado de Bloquedo: " + (s.getBlocked()));
		cmd.setS(s);
		log.debug("Ejecutando comando: " + cmd);
		cmd.ejecutar(m);
		log.debug("Ejecutando Done");
		Transicion next = cmd.getTransicion();
		if(next!=null ){
			s.setTrans(next);
			s.setState(new Activa());
		} else {
			s.setState(new Finalizada());
		}
		
	}


}
