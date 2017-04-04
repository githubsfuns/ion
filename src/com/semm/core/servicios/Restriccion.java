package com.semm.core.servicios;

import java.io.Serializable;

public class Restriccion implements Serializable{

	private static final long serialVersionUID = 56494L;
	private long id;
	String propiedad,relacion,valor;
	ListaDinamica lista;
	
	public ListaDinamica getLista() {
		return lista;
	}

	public void setLista(ListaDinamica lista) {
		this.lista = lista;
	}

	public boolean valido(){
		boolean prop=false,rel=false,val=false;
		
		if (propiedad ==null || relacion==null || valor==null) {return false;}
		
		if( propiedad.equals("nombre") ||
			propiedad.equals("apellido") ||
			propiedad.equals("ciudad") ||
			propiedad.equals("data1") ||
			propiedad.equals("data2") ||
			propiedad.equals("data3"))
		{prop = true;}
		
		if(relacion.equals("igual") || relacion.equals("noigual")){rel=true;}
		
		if(true){val=true;}
		return prop && rel && val;
	}
	
	public boolean equals(Object o){
		if (o instanceof Restriccion){
			Restriccion r2 = (Restriccion) o;
			return this.id == r2.id;
		}
		return false;
	}
	
	public String getPropiedad() {
		return propiedad;
	}
	public void setPropiedad(String propiedad) {
		this.propiedad = propiedad;
	}
	public String getRelacion() {
		return relacion;
	}
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
