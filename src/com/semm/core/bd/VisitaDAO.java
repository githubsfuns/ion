
package com.semm.core.bd;


import java.util.Date;
import java.util.List;

import com.semm.core.servicios.cvmed.*;




public interface VisitaDAO extends GenericoDao<Visita,Long> {
	
	public static final String QUERY_CANTIDAD= "VisitaDAO.Cantidad";
	public static final String QUERY_VP_ESP = "VisitaDAO.Vpe";
	public static final String QUERY_VR_ESP = "VisitaDAO.Vre";
	public static final String QUERY_VDelMes = "VisitaDAO.VisitasDelMes";
	public static final String QUERY_HojaMedica = "VisitaDAO.HojaMedica";
	public static final String QUERY_CANTIAD_MEDICO = "VisitaDAO.CantidadMedico";
	public static final String QUERY_ACOMP = "VisitaDAO.Acomp";
	public static final String QUERY_ULTIMA_DIA = "VisitaDAO.UltimoDia";
	
	
	public List<Object[]> buscarCantidad(Representante rep, Date fecha, int estado);
	//Visitas Por Especialidad
	public List<Object[]> vistasProgramadasEsp(Representante rep);
	public List<Object[]> vistasRelizadasEsp(Representante rep,int estado, Date fecha);
	public Visita buscarUltimaVisita(Representante rep,Medico med);
	
	public Integer buscarCantidadProgramadas(Representante rep);
	public Integer buscarCantidadAcompanadas(Representante rep, Date fecha);
	public List<Visita> buscarVisitasDelMes(Representante rep,Date fecha);
	
	//public List<VProgramada> buscarProgramadas(Representante rep);
	public List<VProgramada> visitasProgramadas(Representante rep);
	
	public int visitaAutorizada(Medico m,Representante rep,Date fecha);
	public long cantidadPorMedico(Medico m,Representante rep,Date fecha,int status);
	public Visita ultimaDia(Medico m,Representante rep,Date fecha,int status);
}
