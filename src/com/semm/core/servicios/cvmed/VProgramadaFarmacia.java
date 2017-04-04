package com.semm.core.servicios.cvmed;

import java.io.Serializable;
import java.util.Date;

public class VProgramadaFarmacia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1675777812796629628L;
	private Farmacia farmacia;
	private RepresentanteFarmacia rep;
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



	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}

	public RepresentanteFarmacia getRep() {
		return rep;
	}

	public void setRep(RepresentanteFarmacia rep) {
		this.rep = rep;
	}
	
	
	
}