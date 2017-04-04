package com.semm.core.servicios.cvmed;

public class Medico {
	private int codigo;
	private String nombre,especialidad;
	private String minSanidad,colegioDeMedicos,institucion,direccion,region,estado,ranking,email;
	private String tlfParticular,tlfLaboral,tlfMovil;
	
	public int getCodigo() {
		return codigo;
	} 
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getColegioDeMedicos() {
		return colegioDeMedicos;
	}
	public void setColegioDeMedicos(String colegioDeMedicos) {
		this.colegioDeMedicos = colegioDeMedicos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public String getMinSanidad() {
		return minSanidad;
	}
	public void setMinSanidad(String minSanidad) {
		this.minSanidad = minSanidad;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTlfLaboral() {
		return tlfLaboral;
	}
	public void setTlfLaboral(String tlfLaboral) {
		this.tlfLaboral = tlfLaboral;
	}
	public String getTlfMovil() {
		return tlfMovil;
	}
	public void setTlfMovil(String tlfMovil) {
		this.tlfMovil = tlfMovil;
	}
	public String getTlfParticular() {
		return tlfParticular;
	}
	public void setTlfParticular(String tlfParticular) {
		this.tlfParticular = tlfParticular;
	}

}
