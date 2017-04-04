package com.semm.core.conexiones;

import java.io.Serializable;
import java.util.List;

import com.semm.core.servicios.Usuario;
import com.semm.core.sesiones.SesionVisitor;

public abstract class NuevoMensaje implements Serializable {

	protected List<String> recips;
	protected Conexion cnx;
	protected String cliente,servicio;
	protected Usuario owner;
	
	
	
	
	
	public abstract void accept(SesionVisitor visita);
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public List<String> getRecips() {
		return recips;
	}
	public void setRecips(List<String> recips) {
		this.recips = recips;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	

	public Usuario getOwner() {
		return owner;
	}
	public void setOwner(Usuario owner) {
		this.owner = owner;
	}
	public void removefromUserQueue(int sub) {
		synchronized (this.owner){
			this.owner.setOnqueue(this.owner.getOnqueue()-sub);
		}
	}
	public void removeUserCredit() {
		synchronized (this.owner){
				this.owner.setCredits(this.owner.getCredits()-1);
		}
	}
	
	public void addtoUserQueue(int add) {
		synchronized (this.owner){
			this.owner.setOnqueue(this.owner.getOnqueue()+add);
		}
	}
	public int getSaldo() {
		synchronized (this.owner){
			return owner.getCredits()- owner.getOnqueue();
		}
	}
}
