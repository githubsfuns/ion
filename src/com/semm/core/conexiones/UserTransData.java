package com.semm.core.conexiones;

import java.io.Serializable;
import java.util.List;

import com.semm.core.sesiones.SesionVisitor;



public class UserTransData extends NuevoMensaje implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> data;
	private String cliente,servicio;
	private String passwd;
	private transient Conexion cnx;
	
	public String getCliente() {
		return cliente;
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	
	public UserTransData(){
		
	}
	
	public UserTransData(TransData td){
		this.cliente = td.getCliente();
		this.servicio = td.getServicio();
		this.recips = td.getRecips();
		this.data = td.getData();
		this.cnx = td.getCnx();
	}
	@Override
	public void accept(SesionVisitor visita) {
		visita.visitar(this);
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
