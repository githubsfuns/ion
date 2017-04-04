
package com.semm.core.bd;


import java.util.Date;
import java.util.List;

import com.semm.core.servicios.cvmed.*;




public interface VProgramadaDAO extends GenericoDao<VProgramada,Long> {
	

	public static final String QUERY_VP_ESP = "VisitaDAO.Vpe";
	public static final String QUERY_HojaMedica = "VisitaDAO.HojaMedica";

	
	

	//Visitas Por Especialidad
	public List<Object[]> vistasProgramadasEsp(Representante rep);

	
	public Integer buscarCantidadProgramadas(Representante rep);

	
	//public List<VProgramada> buscarProgramadas(Representante rep);
	public List<VProgramada> visitasProgramadas(Representante rep);
	
}
