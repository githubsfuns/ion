package com.semm.core.sesiones;

import org.apache.log4j.Logger;

import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.ManejadorServicios;
import com.semm.core.servicios.Servicio;
import com.semm.core.sesiones.spamutils.ManejadorSpam;

public class FabricaSesionSvc extends FabricaSesiones {
	public static Logger log = Logger.getLogger(FabricaSesionSvc.class);
	ManejadorServicios msvc = ManejadorServicios.getInstancia();

	
	@Override
	public Sesion getSesion(NuevoMensaje m,String recip) {
		Sesion s = new Sesion();
		
		s.setTlf(recip);
		
		FabricaSesionVisitor fsv = new FabricaSesionVisitor();	
		fsv.setS(s);
		m.accept(fsv);
		
		s.setCliente(m.getCliente());
		s.setOwner(m.getOwner());
		
		ManejadorSpam.getInstancia().verificarBloqueos(s);
		
		Servicio svc = msvc.buscarServicio(m.getServicio());
		
		if(svc!=null){
			log.debug("Servicio encontrado " + svc.getNombre());
		}
		else{ 
			log.debug("Servicio NO encontrado " + m.getServicio());
		}	
		
		s.setSvc(svc);
		return s;
		
	}


	
	


}
