package com.semm.core.bd;

import java.util.List;

import com.semm.core.conexiones.Conexion;
import com.semm.core.servicios.KeywordSet;
import com.semm.core.servicios.MensajeLog;



public interface KeywordSetDAO extends GenericoDao<KeywordSet,Long> { 
	public List<KeywordSet> buscarPorCnx(Conexion cnx);
}
