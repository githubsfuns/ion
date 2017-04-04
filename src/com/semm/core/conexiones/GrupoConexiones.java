package com.semm.core.conexiones;


import java.util.List;
import java.util.Set;


public class GrupoConexiones {
	private long id;
	private String nombre;
	private int cupos;
	private Set<Conexion> conexiones;

	
	public Set<Conexion> getConexiones() {
		return conexiones;
	}

	public void setConexiones(Set<Conexion> conexiones) {
		this.conexiones = conexiones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
