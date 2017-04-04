package com.semm.core.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.semm.core.conexiones.Conexion;

public class MensajeNombrado {
	
	
	private String nombre,apellido;
	private String mobile,msg,servicio;
	private Usuario cliente;
	private Date fechahora,fechaentrega;
	private int tipo,subtipo;
	private long id;
	private SimpleDateFormat format;
	private boolean entregado;
	
	public MensajeNombrado(){
		format = new  SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

	}
	
	public Date getFechahora() {
		return fechahora;
	}
	public void setFechahora(Date fecha) {
		this.fechahora = fecha;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Usuario getCliente() {
		return cliente;
	}
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMsg() {
		return msg;
	}
	public String getMsgSinLineas() {
		
		return msg.replaceAll("(\\n|\\r)", " ");
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public int getSubtipo() {
		return subtipo;
	}
	public void setSubtipo(int subtipo) {
		this.subtipo = subtipo;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getFormatfechahora() {
		return format.format(fechahora);
	}
	
	public String getReporttime(){
		String dif = "";
		if(fechaentrega!=null){
			return diferencia(fechaentrega, fechahora);
		}
		return dif;
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
	
	public String diferencia(Date a,Date b){
//		suppose you already have two Date objects, date1 and date2.
		long d1 = a.getTime();
		long d2 = b.getTime();
		long result = Math.abs(d1 - d2);  //Abs value in case.
		 
//		long represents millisecond.
		long min_term  = 60 * 1000;
		long hour_term = 60 * min_term;
		long day_term  = 24 * hour_term;
		 
		int day  = (int)(result / day_term);
		int hour = (int)((result % day_term) / hour_term);
		int min  = (int)((result % hour_term) / min_term);
		
		if(day > 0)
			return day + " dias";
		else if (hour > 0)
			return hour + " horas";
		else 
			return min + " mins";
		
	}
}
