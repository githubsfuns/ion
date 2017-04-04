package com.semm.core.conexiones;

import java.util.ArrayList;
import java.util.Date;

import com.semm.core.sesiones.SesionVisitor;

public class ReporteEntrega extends NuevoMensaje{
	private long id;
	private String recip;
	private Date fechahora;
	private Conexion cnx;
	
	
	public ReporteEntrega(String recip, Date fechahora) {
		super();
		this.recip = recip;
		this.fechahora = fechahora;
		this.recips = new ArrayList<String>(1);
		this.recips.add(0,recip);
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public Date getFechahora() {
		return fechahora;
	}
	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRecip() {
		return recip;
	}
	public void setRecip(String recip) {
		this.recip = recip;
	}
	@Override
	public void accept(SesionVisitor visita) {
		visita.visitar(this);	
	}


}
