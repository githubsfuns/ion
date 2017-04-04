package com.semm.core.conexiones;

import java.util.Date;

import com.semm.core.servicios.Usuario;

public class Llamada {
	private long id;
	private String tlf;
	private Date fechahora;
	private Usuario usuario;
	private Conexion cnx;
	
	
	public Llamada() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Llamada(String tlf, Date fechahora, Conexion cnx) {
		super();
		this.tlf = tlf;
		this.fechahora = fechahora;
		this.cnx = cnx;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public Date getFechahora() {
		return fechahora;
	}
	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
		
}
