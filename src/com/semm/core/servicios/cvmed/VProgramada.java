package com.semm.core.servicios.cvmed;

import java.io.Serializable;
import java.util.Date;

public class VProgramada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1675777812796629628L;
	private Medico medico;
	private Representante rep;
	private Date fecha;
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Representante getRep() {
		return rep;
	}

	public void setRep(Representante rep) {
		this.rep = rep;
	}
	
	
	
}