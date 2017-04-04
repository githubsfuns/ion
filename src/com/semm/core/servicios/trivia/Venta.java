package com.semm.core.servicios.trivia;

import java.util.Date;

import com.semm.core.servicios.cvmed.Producto;

public class Venta {
	
	long id;
	Date fecha;
	Producto producto;
	Participante participante;
	String sku;
	int cantidad;
	int puntosgen;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPuntosgen() {
		return puntosgen;
	}
	public void setPuntosgen(int puntosgen) {
		this.puntosgen = puntosgen;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
}
