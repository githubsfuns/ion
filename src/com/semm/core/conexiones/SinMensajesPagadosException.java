package com.semm.core.conexiones;

public class SinMensajesPagadosException extends Exception {

	public SinMensajesPagadosException(String nombre) {
		super(nombre + " no tiene mensajes!!!");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728676719679263205L;

}
