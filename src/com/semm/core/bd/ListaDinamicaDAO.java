package com.semm.core.bd;

import java.util.List;

import com.semm.core.servicios.*;

public interface ListaDinamicaDAO extends GenericoDao<ListaDinamica,Long> {
	public List<ListaDinamica> buscarListas(Usuario u);
}
