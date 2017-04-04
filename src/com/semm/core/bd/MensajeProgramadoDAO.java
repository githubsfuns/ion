package com.semm.core.bd;

import java.util.List;

import com.semm.core.servicios.MensajeProgramado;
import com.semm.core.servicios.Usuario;

public interface MensajeProgramadoDAO extends GenericoDao<MensajeProgramado,Long> {
	public static final String QUERY_ACTIVOS = "MensajeProgramadoDAO.Activos";
	public List<MensajeProgramado> buscarMensajes(Usuario u,int row,int max);
	public List<MensajeProgramado> buscarActivos();
	 

}
