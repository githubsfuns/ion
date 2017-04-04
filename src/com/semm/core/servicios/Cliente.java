package com.semm.core.servicios;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombre,apellido,email,ciudad,data1,data2,data3;
	private String tlf,tlf2,tlf3;
	public String getTlf3() {
		return tlf3;
	}
	public void setTlf3(String tlf3) {
		this.tlf3 = tlf3;
	}
	

	private short sexo;
	private Date cumpleanos;
	private Usuario owner;
	private Set<Lista> listas;
	private short activo;
	
	
	public short getActivo() {
		return activo;
	}
	public void setActivo(short activo) {
		this.activo = activo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Set<Lista> getListas() {
		return listas;
	}
	public void setListas(Set<Lista> listas) {
		this.listas = listas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Usuario getOwner() {
		return owner;
	}
	public void setOwner(Usuario owner) {
		this.owner = owner;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCumpleanos() {
		return cumpleanos;
	}
	public void setCumpleanos(Date cumpleanos) {
		this.cumpleanos = cumpleanos;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public short getSexo() {
		return sexo;
	}
	public void setSexo(short sexo) {
		this.sexo = sexo;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTlf2() {
		return tlf2;
	}
	public void setTlf2(String tlf2) {
		this.tlf2 = tlf2;
	}
	public String getData3() {
		return data3;
	}
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	
	public String getNombrecompleto() {
		String completo = (apellido !=null) ? apellido+ " " : " ";
		completo += (nombre!=null) ? nombre : ""; 
		return completo;
	}
}
