package com.semm.core.conexiones;

import java.io.Serializable;
import java.util.List;


public abstract class Mensaje implements Serializable {

	protected List<String> recips;
	protected Conexion cnx;
	protected String cliente,servicio;
	
	
	
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public List<String> getRecips() {
		return recips;
	}
	public void setRecips(List<String> recips) {
		this.recips = recips;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
