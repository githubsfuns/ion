package com.semm.core.servicios.cvmed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class VisitaFarmacia {

	public static final int AJ = 1, AM = 2, AC = 3,ADC = 4,AGV =5, FALLIDA = 0,
			EXITOSA_PROG = 1, EXITOSA_NOPROG = 2,EXCESO =3,FALLIDA_NOPROG = 5,ANULADA = 6,ACTUALIZADA=7;;

	private long id;

	private RepresentanteFarmacia rep;

	private Farmacia farmacia;

	private int acomp, estado;

	private String coment;

	private Set<Aceptacion> aceptacion;

	private Date fechahora;

	public int getAcomp() {

		return acomp;
	}

	public void setAcomp(int acomp) {
		this.acomp = acomp;
	}

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}



	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	public RepresentanteFarmacia getRep() {
		return rep;
	}

	public void setRep(RepresentanteFarmacia rep) {
		this.rep = rep;
	}

	public Set<Aceptacion> getAceptacion() {
		return aceptacion;
	}

	public void setAceptacion(Set<Aceptacion> aceptacion) {
		this.aceptacion = aceptacion;
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

	public String getAcompTexto() {
		switch (acomp) {
		case AJ:
			return "Jefe";
		case AC:
			return "Compa&ntilde;ro";
		case AM:
			return "Mercadeo";
		default:
			return "-";
		}
	}

	public String getEstadoTexto() {
		switch (estado) {
		case FALLIDA:
			return "Fallida";
		case EXITOSA_PROG:
			return "Exitosa Programada";
		case EXITOSA_NOPROG:
			return "Exitosa No programada";
		default:
			return "-";
		}
	}

	public String getFechahoraTexto() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		return df.format(fechahora);
	}

}
