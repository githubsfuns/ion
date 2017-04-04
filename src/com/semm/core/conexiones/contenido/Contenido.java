package com.semm.core.conexiones.contenido;

public abstract class Contenido {
	private String msg,temp_msg;

	public abstract Contenido getContenido();
	
	public void setMsg(String msg) {
		this.msg = msg;
		
	}

	/**
	 * @param msg
	 */
	public Contenido(String msg) {
		// TODO Auto-generated constructor stub
		this.msg = msg;
	}
	

	public String getMsg() {
		return msg;
	}

	public abstract void replace(String regex,String data);
	public abstract void addline();

	public String getTemp_msg() {
		return temp_msg;
	}

	public void setTemp_msg(String temp_msg) {
		this.temp_msg = temp_msg;
	}
	
	
}
