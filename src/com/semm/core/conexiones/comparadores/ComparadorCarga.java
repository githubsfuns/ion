package com.semm.core.conexiones.comparadores;

import java.io.Serializable;
import java.util.Comparator;

import com.semm.core.conexiones.Conexion;

public class ComparadorCarga implements Comparator<Conexion>, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int compare(Conexion c1, Conexion c2) {
		return (c1.getLoad()<= c2.getLoad()) ? -1 : 1;
	}



}


