package com.semm.core.sesiones;

import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;

public abstract class FabricaSesiones {
	
	public static final Class SVC = FabricaSesionSvc.class;
	
	public static FabricaSesiones getFabrica(Class clazz){
		try {
			return (FabricaSesiones)clazz.newInstance();
	     } catch (Exception ex) {
	            throw new RuntimeException("No se pudo crear: " + clazz);
	    }
	}
	public abstract Sesion getSesion(NuevoMensaje m,String recip);
}
