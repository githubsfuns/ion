package com.semm.core.bd;

import java.util.List;

import com.semm.core.conexiones.Llamada;
import com.semm.core.servicios.Usuario;

public interface LlamadaDAO extends GenericoDao<Llamada,Long> {
	
public List<Llamada> buscar(Usuario u,int off,int max);
}
