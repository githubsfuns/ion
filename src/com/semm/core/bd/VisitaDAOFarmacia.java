
package com.semm.core.bd;


import java.util.Date;
import java.util.List;

import com.semm.core.servicios.cvmed.*;




public interface VisitaDAOFarmacia extends GenericoDao<VisitaFarmacia,Long> {
	
	public static final String QUERY_CANTIDAD= "VisitaDAOFarmacia.Cantidad";
	public static final String QUERY_VP_ESP = "VisitaDAOFarmacia.Vpe";
	public static final String QUERY_VR_ESP = "VisitaDAOFarmacia.Vre";
	public static final String QUERY_VDelMes = "VisitaDAOFarmacia.VisitasDelMes";
	public static final String QUERY_HojaMedica = "VisitaDAOFarmacia.HojaMedica";
	public static final String QUERY_CANTIAD_FAR = "VisitaDAOFarmacia.CantidadFarmacia";
	public static final String QUERY_ULTIMA_DIA = "VisitaDAOFarmacia.UltimoDia";
	
	public List<Object[]> buscarCantidad(RepresentanteFarmacia rep, Date fecha, int estado);
	//Visitas Por Especialidad
	public List<Object[]> vistasProgramadasEsp(RepresentanteFarmacia rep);
	public List<Object[]> vistasRelizadasEsp(RepresentanteFarmacia rep,int estado, Date fecha);
	public VisitaFarmacia buscarUltimaVisita(RepresentanteFarmacia rep,Farmacia far);
	
	public Integer buscarCantidadProgramadas(RepresentanteFarmacia rep);
	public Integer buscarCantidadAcompanadas(RepresentanteFarmacia rep);
	public List<VisitaFarmacia> buscarVisitasDelMes(RepresentanteFarmacia rep,Date fecha);
	
	//public List<VProgramada> buscarProgramadas(Representante rep);
	public List<VProgramadaFarmacia> visitasProgramadas(RepresentanteFarmacia rep);
	
	public int visitaAutorizada(Farmacia far,RepresentanteFarmacia rep,Date fecha);
	public int cantidadPorFarmacia(Farmacia far,RepresentanteFarmacia rep,Date fecha,int status);
	public VisitaFarmacia ultimaDia(Farmacia far,RepresentanteFarmacia rep,Date fecha,int status);
}
