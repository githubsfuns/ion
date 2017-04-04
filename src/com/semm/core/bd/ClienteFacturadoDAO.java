package com.semm.core.bd;

import java.util.List;

import com.semm.core.servicios.ClienteFacturado;
import com.semm.core.servicios.Usuario;

public interface ClienteFacturadoDAO extends GenericoDao<ClienteFacturado,Long> {
	public List<ClienteFacturado> buscarClienteFacturado(int start, int max);
	public List<ClienteFacturado> buscarClienteFacturado(String query, int start, int max);
	//public Object cantidad(Usuario user);
}