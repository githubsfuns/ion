package com.semm.core.conexiones;

import java.io.Serializable;
import java.util.List;

public class TransData extends Mensaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7219180626790959522L;
	private List<String> data;
	private String cliente,servicio;
	private transient Conexion cnx;
	
	public String getCliente() {
		return cliente;
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}


	
}
