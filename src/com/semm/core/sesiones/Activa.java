package com.semm.core.sesiones;

import org.apache.log4j.Logger;

import com.semm.core.comando.Comando;
import com.semm.core.comando.Transicion;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.TransData;
import com.semm.core.conexiones.UserTransData;

public class Activa extends Estado{
	
	public static Logger log =  Logger.getLogger(Activa.class);

	@Override
	public void handleMensaje(NuevoMensaje m, Sesion s) {	
		SesionActivaVisitor sav = new SesionActivaVisitor();
		sav.setS(s);
		sav.setLog(log);
		m.setServicio(s.getSvc().getNombre());
		m.accept(sav);
	}

}
