package com.semm.core.servicios.trivia;

import com.semm.core.conexiones.Conexion;

public class Participante {
	private String tlf,datos;
	private Conexion cnx;
		int partid;
	 int puntos;
	 int correctas,mensajes,malas;
	
	 public void agregarMensajes(int i){
		 mensajes += i;
	 }
	 
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getCorrectas() {
		return correctas;
	}
	public void setCorrectas(int correctas) {
		this.correctas = correctas;
	}
	public int getMensajes() {
		return mensajes;
	}
	public void setMensajes(int mensajes) {
		this.mensajes = mensajes;
	}
	public int getMalas() {
		return malas;
	}
	public void setMalas(int malas) {
		this.malas = malas;
	}

	public Conexion getCnx() {
		return cnx;
	}

	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}

	public int getPartid() {
		return partid;
	}

	public void setPartid(int partid) {
		this.partid = partid;
	}
}
