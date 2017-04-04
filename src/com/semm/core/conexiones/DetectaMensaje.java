package com.semm.core.conexiones;

public interface DetectaMensaje {
	public abstract void mensajeEntrante(NuevoMensaje in);
	public abstract void reporteEntrante(ReporteEntrega in);
	public abstract void llamadaEntrante(Llamada in);
}
