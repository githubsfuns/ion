package com.semm.core.servicios;

import com.semm.core.comando.Comando;
import com.semm.core.conexiones.GrupoConexiones;

public class Servicio {

	private Usuario owner;
	private Comando rootcmd;
	private GrupoConexiones grpcnx;
	private String nombre,pred;
	private boolean predeterminado;
	private long timeout;
	private int horai,horaf;
	private String rootcmdlight;
	private String grupoConxs;
	
	
	
	
	public String getPred() {
		return pred;
	}

	public void setPred(String pred) {
		this.pred = pred;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Comando getRootcmd() {
		return rootcmd;
	}

	public void setRootcmd(Comando rootcmd) {
		this.rootcmd = rootcmd;
	}

	public Usuario getOwner() {
		return owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public GrupoConexiones getGrpcnx() {
		return grpcnx;
	}

	public void setGrpcnx(GrupoConexiones grpcnx) {
		this.grpcnx = grpcnx;
	}

	public boolean isPredeterminado() {
		return predeterminado;
	}

	public void setPredeterminado(boolean predeterminado) {
		this.predeterminado = predeterminado;
	}


}
