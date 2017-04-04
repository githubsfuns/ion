package com.semm.core.servicios;

import java.util.Map;

import com.semm.core.conexiones.Conexion;

public class KeywordSet {
	private Long id;
	private Conexion cnx;
	private Map<String,UserService> keywords;
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public Map<String, UserService> getKeywords() {
		return keywords;
	}
	public void setKeywords(Map<String,  UserService> keywords) {
		this.keywords = keywords;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
