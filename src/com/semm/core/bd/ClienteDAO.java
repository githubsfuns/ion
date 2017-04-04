package com.semm.core.bd;

import java.util.List;

import com.semm.core.servicios.Cliente;
import com.semm.core.servicios.Usuario;

public interface ClienteDAO extends GenericoDao<Cliente,Long> {
	public List<Cliente> buscarCliente(Usuario u,int start, int max);
	public List<Cliente> buscarCliente(String query,Usuario u,int start, int max);
	public List<Cliente> porCelular(String query,Usuario u,int start, int max);
	public Cliente buscarClientePorId(Long id,Usuario u);
	public Object cantidad(Usuario user);
}
