package com.semm.core.conexiones.comparadores;

import java.util.Comparator;

import com.semm.core.conexiones.Conexion;

public class ComparadorMenorCosto implements Comparator<Conexion>{

	public int compare(Conexion c1, Conexion c2) {
		return (c1.getCostpermsg()<= c2.getCostpermsg()) ? -1 : 1;
	}


	

}
