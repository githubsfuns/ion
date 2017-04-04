package com.semm.core.servicios.cvmed;

import java.util.Set;

public class Gpt {
	private String tlf,nombre,zona;
	private Set<Representante> reps;
	private Set<RepresentanteFarmacia> repsFarmacia;
	

	public Set<Representante> getReps() {
		return reps;
	}

	public void setReps(Set<Representante> reps) {
		this.reps = reps;
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

	public Set<RepresentanteFarmacia> getRepsFarmacia() {
		return repsFarmacia;
	}

	public void setRepsFarmacia(Set<RepresentanteFarmacia> repsFarmacia) {
		this.repsFarmacia = repsFarmacia;
	}

	
	
}
