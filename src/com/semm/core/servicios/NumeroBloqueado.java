package com.semm.core.servicios;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NumeroBloqueado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7759780755059254202L;
	private String tlf;
	private Usuario user;
	private Date fecha;
	private int status;
	private SimpleDateFormat format;
	
	public NumeroBloqueado(){
		format = new  SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getFormatfecha(){
		return format.format(fecha);
	}
}
