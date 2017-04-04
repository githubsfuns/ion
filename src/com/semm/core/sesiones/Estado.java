package com.semm.core.sesiones;




import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;


public abstract class Estado {
	
	public abstract void handleMensaje(NuevoMensaje m,Sesion s);

	
}
