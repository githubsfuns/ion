package com.semm.core.conexiones;

public class ConfiguracionConexion {
	private String factoryClass;
	private String conexClass;
	private String[] params;
	private String nombre;
	private int type;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getConexClass() {
		return conexClass;
	}
	public void setConexClass(String conexClass) {
		this.conexClass = conexClass;
	}
	public String getFactoryClass() {
		return factoryClass;
	}
	public void setFactoryClass(String factoryClass) {
		this.factoryClass = factoryClass;
	}
	public String[] getParams() {
		return params;
	}
	public void setParams(String[] params) {
		this.params = params;
	}
}
