package com.semm.core.servicios.trivia;

import java.util.List;

public class Trivia {
	private long id;
	private int indexpregunta;
	private List<Pregunta> preguntas;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	public int getIndexpregunta() {
		return indexpregunta;
	}
	public void setIndexpregunta(int indexpregunta) {
		this.indexpregunta = indexpregunta;
	}
	
	
}
