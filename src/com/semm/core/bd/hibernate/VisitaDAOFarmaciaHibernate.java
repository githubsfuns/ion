package com.semm.core.bd.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.VisitaDAO;
import com.semm.core.bd.VisitaDAOFarmacia;
import com.semm.core.servicios.cvmed.Farmacia;
import com.semm.core.servicios.cvmed.Medico;
import com.semm.core.servicios.cvmed.Representante;
import com.semm.core.servicios.cvmed.RepresentanteFarmacia;
import com.semm.core.servicios.cvmed.VProgramada;
import com.semm.core.servicios.cvmed.VProgramadaFarmacia;
import com.semm.core.servicios.cvmed.Visita;
import com.semm.core.servicios.cvmed.VisitaFarmacia;

public class VisitaDAOFarmaciaHibernate extends HibernateGenericoDAO<VisitaFarmacia, Long>
implements VisitaDAOFarmacia {

	@SuppressWarnings("unchecked")
	public List<Object[]> buscarCantidad(RepresentanteFarmacia rep, Date fecha, int estado) {
		Query q = getSession().getNamedQuery(VisitaDAOFarmacia.QUERY_CANTIDAD);
		q.setParameter("rep", rep);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		q.setParameter("estado",estado);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> vistasProgramadasEsp(RepresentanteFarmacia  rep) {
		Query q = getSession().getNamedQuery(VisitaDAOFarmacia.QUERY_VP_ESP);
		q.setParameter("rep", rep);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> vistasRelizadasEsp(RepresentanteFarmacia  rep, int estado,Date fecha) {
		Query q = getSession().getNamedQuery(VisitaDAOFarmacia.QUERY_VR_ESP);
		q.setParameter("rep", rep);
		q.setParameter("estado", estado);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes", cal.get(Calendar.MONTH)+1);
		return q.list();
	}

	public Integer buscarCantidadProgramadas(RepresentanteFarmacia  rep) {
		Criteria crit = getSession().createCriteria(VProgramada.class);
		crit.add(Restrictions.eq("rep", rep));
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();
	}

	public VisitaFarmacia buscarUltimaVisita(RepresentanteFarmacia  rep, Farmacia far) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("rep", rep));
		crit.add(Restrictions.eq("farmacia", far));
		crit.add(Restrictions.eq("estado", VisitaFarmacia.EXITOSA_PROG));
		crit.addOrder(Order.desc("fechahora"));
		crit.setMaxResults(1);
		return (VisitaFarmacia) crit.uniqueResult();
	}

	public Integer buscarCantidadAcompanadas(RepresentanteFarmacia  rep) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("rep", rep));
		crit.add(Restrictions.gt("acomp", 0));
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<VisitaFarmacia> buscarVisitasDelMes(RepresentanteFarmacia  rep,Date fecha) {
		Query q = getSession().getNamedQuery(VisitaDAOFarmacia.QUERY_VDelMes);
		q.setParameter("rep", rep);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<VProgramadaFarmacia> visitasProgramadas(RepresentanteFarmacia  rep) {
		Query q = getSession().getNamedQuery(VisitaDAOFarmacia.QUERY_HojaMedica);
		q.setParameter("rep", rep);
		return q.list();
	}

	public int cantidadPorFarmacia(Farmacia far, RepresentanteFarmacia  rep, Date fecha, int status) {
		Query q = getSession().getNamedQuery(VisitaDAOFarmacia.QUERY_CANTIAD_FAR);
		
		q.setParameter("rep", rep);
		q.setParameter("far", far);
		q.setParameter("stat", status); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		return (Integer)q.uniqueResult();
	}
	
	public VisitaFarmacia ultimaDia(Farmacia far, RepresentanteFarmacia  rep, Date fecha, int status) {
		Query q = getSession().getNamedQuery(VisitaDAOFarmacia.QUERY_ULTIMA_DIA);
		
		q.setParameter("rep", rep);
		q.setParameter("far", far);
		q.setParameter("stat", status); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		q.setParameter("dia", cal.get(Calendar.DATE));
		return (VisitaFarmacia)q.uniqueResult();
	}

	public int visitaAutorizada(Farmacia far, RepresentanteFarmacia rep, Date fecha) {
		Criteria crit = getSession().createCriteria(VProgramadaFarmacia.class);
		crit.add(Restrictions.eq("rep", rep));
		crit.add(Restrictions.eq("farmacia", far));
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();
	}


}
