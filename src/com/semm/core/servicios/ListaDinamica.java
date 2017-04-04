package com.semm.core.servicios;

import java.io.Serializable;
import java.util.*;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.*;


public class ListaDinamica implements Serializable {

	private static final long serialVersionUID = 599994L;

	private long id;

	private String nombre;

	private Set<Restriccion> restricciones;

	private Usuario owner;
	
	private Set<Cliente> clientes;

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Set<Cliente> getClientes() {
		if(clientes==null){clientes = new HashSet<Cliente>();}
		return clientes;
	}

	public Usuario getOwner() {
		return owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Restriccion> getRestricciones() {
		return restricciones;
	}

	public void setRestricciones(Set<Restriccion> restricciones) {
		this.restricciones = restricciones;
	}

	public void addRestriccion(Restriccion res) {
		this.restricciones.add(res);
	}
	
	public void llenarDinamica() {
		List<Criterion> criterios = new ArrayList<Criterion>();

		 for (Restriccion r : this.getRestricciones()) {
		 if (r.getRelacion().equals("igual")) {
		 Criterion crit = Restrictions.eq(r.getPropiedad(), r.getValor());
		 criterios.add(crit);
		 }
		
		 }

		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ClienteDAO cdao = fab.getClienteDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		Criterion crit = Restrictions.eq("owner", this.getOwner());
		criterios.add(crit);

		HashSet<Cliente> cl = new HashSet<Cliente>();
		this.getClientes().addAll((cdao.buscarPorListaCriterios(criterios)));
		
		tx.commit();
		tx.cerrarSesion();
	}
}
