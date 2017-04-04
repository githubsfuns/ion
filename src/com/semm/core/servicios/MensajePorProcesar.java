package com.semm.core.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.semm.core.conexiones.Conexion;
import com.semm.core.conexiones.UserTransData;

public class MensajePorProcesar {
	private String servicio;
	private String recips;
	private String data;
	private String cliente;
	private Date fechahora;
	private Conexion cnx;
	private int tipo;
	private long id;
	private SimpleDateFormat format;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public MensajePorProcesar(){
		format = new  SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getRecips() {
		return recips;
	}
	public void setRecips(String recips) {
		this.recips = recips;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Date getFechahora() {
		return fechahora;
	}
	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public SimpleDateFormat getFormat() {
		return format;
	}
	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}
	
}
