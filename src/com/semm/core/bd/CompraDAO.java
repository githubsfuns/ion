package com.semm.core.bd;

import java.util.List;

import com.semm.core.servicios.Compra;
import com.semm.core.servicios.Usuario;



public interface CompraDAO extends GenericoDao<Compra,Long>{
	public List<Compra> buscarCompras(Usuario u ,int off,int max);
}
