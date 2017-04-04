package com.semm.core.bd;

import java.util.Date;
import java.util.List;

import com.semm.core.servicios.MensajeNombrado;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.Usuario;

public interface MensajeDAO extends GenericoDao<MensajeLog, Long>{
	
	public static final String QUERY_REPORTE = "MensajeDAO.Reporte";
	public static final String QUERY_LISTA_NOMBRE = "MensajeDAO.Listar";
	public static final String QUERY_LISTA_MOBILE_NOMBRE = "MensajeDAO.ListarMobile";
	public List<MensajeLog> buscarPorFecha(Usuario user,int tipo,int max);
	public List<MensajeNombrado> listar(Usuario user,int tipo,int start, int max);
	public List<MensajeLog> listar(int tipo);
	public List<MensajeNombrado> buscarPorMobile(Usuario user,String mobile,int start,int max);
	public MensajeLog buscarEnviadoPorMobile(String mobile);
	
	public List<MensajeNombrado> buscar(Usuario user,String query,int start,int max);
	public Object cantidad(Usuario user,int tipo,Date fechai,Date fechaf);
	public Object cantidad(Usuario user,int tipo,String mobile,String svc,Date fechai,Date fechaf);
	public Object cantidad(Usuario user,int tipo);
	public List<Object[]> reporte(Date mes, int tipo);
	 
	
}
