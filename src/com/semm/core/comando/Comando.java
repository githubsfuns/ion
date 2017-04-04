package com.semm.core.comando;

import com.semm.core.conexiones.GrupoConexiones;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.sesiones.Sesion;

public abstract class Comando {
	
	private Transicion transicion;
//	protected int blocked;
//	protected GrupoConexiones grp;
	protected Sesion s;
	
/*	public GrupoConexiones getGrp() {
		return grp;
	}
	public void setGrp(GrupoConexiones grp) {
		this.grp = grp;
	}
	public int getBlocked() {
		return blocked;
	}
	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}*/
	public abstract void ejecutar(NuevoMensaje m);
	public Transicion getTransicion() {
		return transicion;
	}
	public void setTransicion(Transicion transicion) {
		this.transicion = transicion;
	}
	public Sesion getS() {
		return s;
	}
	public void setS(Sesion s) {
		this.s = s;
	}
	
	


}
