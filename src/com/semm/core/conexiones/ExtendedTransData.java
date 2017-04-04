package com.semm.core.conexiones;

public class ExtendedTransData extends UserTransData {
	private int prioridad;
	private int tipo;
	private String jmsId;
	public ExtendedTransData(UserTransData utd) {
	
		setCliente(utd.getCliente());
		setServicio(utd.getServicio());
		setRecips(utd.getRecips());
		setData(utd.getData());
		setCnx(utd.getCnx());
		
	}
	public ExtendedTransData() {
		// TODO Auto-generated constructor stub
	}
	public String getJmsId() {
		return jmsId;
	}
	public void setJmsId(String jmsId) {
		this.jmsId = jmsId;
	}
	private boolean reporte,programado;
	
	public boolean isReporte() {
		return reporte;
	}
	public void setReporte(boolean reporte) {
		this.reporte = reporte;
	}
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public boolean isProgramado() {
		return programado;
	}
	public void setProgramado(boolean programado) {
		this.programado = programado;
	}
}
