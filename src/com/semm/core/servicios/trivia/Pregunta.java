package com.semm.core.servicios.trivia;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pregunta {
	private long id;
	private String texto;
	private Date fecha;
	private int puntos,dificultad;
	private int correcta;
	private boolean enviada;
	private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getDificultad() {
		return dificultad;
	}
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public int getCorrecta() {
		return correcta;
	}
	public void setCorrecta(int correcta) {
		this.correcta = correcta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getFechaweb() {
		if(fecha!=null)
			return df.format(fecha);
		else 
			return "";
	}
	public void setFechaweb(String fechaweb) throws Exception {
		this.fecha = df.parse(fechaweb);
	}
	public boolean isEnviada() {
		return enviada;
	}
	public void setEnviada(boolean enviada) {
		this.enviada = enviada;
	}
	
}
