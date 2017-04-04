package com.semm.core.servicios;

import java.util.Date;


public class Usuario {
	
	private String username,password,nombre,ip,logo,ccs;
	private int  credits,bbcredits,tipo,onqueue;

	private boolean accept,locales,inter,bbactivated;
	public boolean isBbactivated() {
		return bbactivated;
	}
	
	public boolean isInter() {
		return inter;
	}

	public void setInter(boolean inter) {
		this.inter = inter;
	}


	public void setBbactivated(boolean bbactivated) {
		this.bbactivated = bbactivated;
	}

	private Date lastlogin;


	public int getBbcredits() {
		return bbcredits;
	}

	public void setBbcredits(int bbcredits) {
		this.bbcredits = bbcredits;
	}

	public int getOnqueue() {
		return onqueue;
	}

	public void setOnqueue(int onqueue) {
		this.onqueue = onqueue;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCcs() {
		return ccs;
	}

	public void setCcs(String ccs) {
		this.ccs = ccs;
	}

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public boolean isLocales() {
		return locales;
	}

	public void setLocales(boolean locales) {
		this.locales = locales;
	}



	
}