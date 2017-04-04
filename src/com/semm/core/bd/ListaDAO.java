package com.semm.core.bd;

import java.util.List;

import com.semm.core.servicios.Lista;
import com.semm.core.servicios.Usuario;

public interface ListaDAO extends GenericoDao<Lista,Long> {
	public List<Lista> buscarListas(Usuario u);
	public Lista buscarLista(String nombre,Usuario u);
}
