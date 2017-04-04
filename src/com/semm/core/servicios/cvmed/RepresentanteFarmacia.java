package com.semm.core.servicios.cvmed;

public class RepresentanteFarmacia {
	private String tlf,nombre,alias,zona;
	private Gpt gpt;

	public Gpt getGpt() {
		return gpt;
	}

	public void setGpt(Gpt gpt) {
		this.gpt = gpt;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
