package com.semm.core.servicios;

public class SesionLog {
	
	private String tlf,servicio,servicio_pred;
	private boolean revocable;
	private Usuario usuario;
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isRevocable() {
		return revocable;
	}
	public void setRevocable(boolean revocable) {
		this.revocable = revocable;
	}
	public String getServicio_pred() {
		return servicio_pred;
	}
	public void setServicio_pred(String servicio_pred) {
		this.servicio_pred = servicio_pred;
	}


}
