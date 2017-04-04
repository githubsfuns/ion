package com.semm.core.servicios;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MensajeProgramado  implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6365477745694511248L;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	/*
	 * 
	 */
	
	//Recipientes
	private String tlfs;
	private long idlist;
	private String filename;
	
	//Cuerpo
	private String body;
	
	// Rango
	private long id;
	private Date desde,hasta;

	
	//Hora
	private Date hora;
	private int cada;
	private int intervalo;
	
	//Recurrencia
	private int recurrencia,cada_rec,numsemana,times;
	private boolean l,m,mie,j,v,s,d,reporte,locales;
	
	//Identificacion
	private String cliente,servicio;
	
	
	public String getTime() {
		return sdf.format(hora);
	}
	public void setTime(String time) {
		try {
			setHora(sdf.parse(time));
		} catch (ParseException e) {
			setHora(new Date());
			e.printStackTrace();
		}
	}
	
	
	
	
	public String getCliente() {
		return cliente;
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
	public int getCada() {
		return cada;
	}
	public void setCada(int cada) {
		this.cada = cada;
	}
	public int getCada_rec() {
		return cada_rec;
	}
	public void setCada_rec(int cada_rec) {
		this.cada_rec = cada_rec;
	}
	public boolean isD() {
		return d;
	}
	public void setD(boolean d) {
		this.d = d;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getIdlist() {
		return idlist;
	}
	public void setIdlist(long idlist) {
		this.idlist = idlist;
	}
	public int getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}
	public boolean isJ() {
		return j;
	}
	public void setJ(boolean j) {
		this.j = j;
	}
	public boolean isL() {
		return l;
	}
	public void setL(boolean l) {
		this.l = l;
	}
	public boolean isM() {
		return m;
	}
	public void setM(boolean m) {
		this.m = m;
	}
	public boolean isMie() {
		return mie;
	}
	public void setMie(boolean mie) {
		this.mie = mie;
	}
	public int getNumsemana() {
		return numsemana;
	}
	public void setNumsemana(int numsemana) {
		this.numsemana = numsemana;
	}
	public int getRecurrencia() {
		return recurrencia;
	}
	public void setRecurrencia(int recurrencia) {
		this.recurrencia = recurrencia;
	}
	public boolean isS() {
		return s;
	}
	public void setS(boolean s) {
		this.s = s;
	}
	public String getTlfs() {
		return tlfs;
	}
	public void setTlfs(String tlfs) {
		this.tlfs = tlfs;
	}
	public boolean isV() {
		return v;
	}
	public void setV(boolean v) {
		this.v = v;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public boolean isReporte() {
		return reporte;
	}
	public void setReporte(boolean report) {
		this.reporte = report;
	}
	public boolean isLocales() {
		return locales;
	}
	public void setLocales(boolean locales) {
		this.locales = locales;
	}
	
	
	

}
