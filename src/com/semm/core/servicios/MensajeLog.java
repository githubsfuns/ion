package com.semm.core.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.semm.core.conexiones.Conexion;

public class MensajeLog {
	private String mobile,msg,servicio;
	private Usuario cliente;
	private Date fechahora,fechaentrega;
	private Conexion cnx;
	private boolean entregado;
	private int tipo,subtipo;
	private int partes;
	private long id;
	private SimpleDateFormat format;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public MensajeLog(){
		format = new  SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

	}
	/**
	 * @param de
	 * @param para
	 * @param msg
	 * @param servicio
	 * @param cliente
	 * @param cnx
	 * @param fecha
	 * @param tipo
	 */
	public MensajeLog(String mobile, String msg, String servicio, Usuario cliente, Conexion cnx,  int tipo,int subtipo) {
		// TODO Auto-generated constructor stub
		this.mobile = mobile;
		this.msg = msg;
		this.servicio = servicio;
		this.cliente = cliente;
		this.cnx = cnx;
		this.fechahora = new Date();
		this.tipo = tipo;
		this.subtipo = subtipo;
		format = new  SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	public Date getFechahora() {
		return fechahora;
	}
	public void setFechahora(Date fecha) {
		this.fechahora = fecha;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	public String getFormatfechahora() {
		return format.format(fechahora);
	}
	public int getSubtipo() {
		return subtipo;
	}
	public void setSubtipo(int subtipo) {
		this.subtipo = subtipo;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	public Usuario getCliente() {
		return cliente;
	}
	public boolean isEntregado() {
		return entregado;
	}
	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}
	public Date getFechaentrega() {
		return fechaentrega;
	}
	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}
	
}
