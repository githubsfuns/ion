package com.semm.core.servicios;

import java.util.HashMap;
import java.util.Map;

import com.semm.core.conexiones.Conexion;
import com.semm.core.conexiones.contenido.Contenido;

public class NotificacionSalida {
	private Map<Integer,Conexion> routes;
	private Map<Integer,String> datareplace;
	
	private Contenido contenido;

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public Map<Integer, String> getDatareplace() {
		return datareplace;
	}

	public void setDatareplace(Map<Integer, String> datareplace) {
		this.datareplace = datareplace;
	}

	public Map<Integer, Conexion> getRoutes() {
		return routes;
	}

	public void setRoutes(Map<Integer, Conexion> routes) {
		this.routes = routes;
	}
	
	
	
}
