package com.semm.core.servicios.cvmed;

import java.io.Serializable;

public class Aceptacion implements Serializable {
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -6201323142693971789L;
	private  Visita visita;
	private  Producto producto;
	private int na;
	public int getNa() {
		return na;
	}
	public void setNa(int na) {
		this.na = na;
	}
	public Producto getProducto() {
		return producto;
	}
	public Aceptacion(){
		
	}
	/**
	 * @param visita
	 * @param producto
	 * @param na
	 */
	public Aceptacion(Visita visita, Producto producto, int na) {
		this.visita = visita;
		this.producto = producto;
		this.na = na;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Visita getVisita() {
		return visita;
	}
	public void setVisita(Visita visita) {
		this.visita = visita;
	}

}
