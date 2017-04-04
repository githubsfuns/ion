package com.semm.core.bd;

import java.util.List;

import com.semm.core.servicios.NumeroBloqueado;
import com.semm.core.servicios.Usuario;

public interface BloqueadoDAO extends GenericoDao <NumeroBloqueado,NumeroBloqueado> {
	public static final String QUERY_LISTA = "BloqueadoDAO.Listar";
	public List<NumeroBloqueado> buscar(Usuario user);
}
