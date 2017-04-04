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
import com.semm.core.servicios.cvmed.Medico;
import com.semm.core.servicios.cvmed.Representante;
import com.semm.core.servicios.cvmed.VProgramada;
import com.semm.core.servicios.cvmed.Visita;

public class VisitaDAOHibernate extends HibernateGenericoDAO<Visita, Long>
implements VisitaDAO {

	@SuppressWarnings("unchecked")
	public List<Object[]> buscarCantidad(Representante rep, Date fecha, int estado) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_CANTIDAD);
		q.setParameter("rep", rep);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		q.setParameter("ano",cal.get(Calendar.YEAR));
		q.setParameter("estado",estado);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> vistasProgramadasEsp(Representante rep) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_VP_ESP);
		q.setParameter("rep", rep);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> vistasRelizadasEsp(Representante rep, int estado,Date fecha) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_VR_ESP);
		q.setParameter("rep", rep);
		q.setParameter("estado", estado);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes", cal.get(Calendar.MONTH)+1);
		return q.list();
	}

	public Integer buscarCantidadProgramadas(Representante rep) {
		Criteria crit = getSession().createCriteria(VProgramada.class);
		crit.add(Restrictions.eq("rep", rep));
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();
	}

	public Visita buscarUltimaVisita(Representante rep, Medico med) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("rep", rep));
		crit.add(Restrictions.eq("medico", med));
		crit.add(Restrictions.eq("estado", Visita.EXITOSA_PROG));
		crit.addOrder(Order.desc("fechahora"));
		crit.setMaxResults(1);
		return (Visita) crit.uniqueResult();
	}

	public Integer buscarCantidadAcompanadas(Representante rep,Date fecha) {
//		Criteria crit = getSession().createCriteria(getPersistentClass());
//		crit.add(Restrictions.eq("rep", rep));
//		crit.add(Restrictions.gt("acomp", 0));
//		crit.setProjection(Projections.rowCount());
//		return (Integer) crit.uniqueResult();
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_ACOMP);
		q.setParameter("rep", rep);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		return (Integer)q.uniqueResult();
	}
	
	public List<Visita> buscarVisitasDelMes(Representante rep,Date fecha) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_VDelMes);
		q.setParameter("rep", rep);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<VProgramada> visitasProgramadas(Representante rep) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_HojaMedica);
		q.setParameter("rep", rep);
		return q.list();
	}

	public long cantidadPorMedico(Medico med, Representante rep, Date fecha, int status) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_CANTIAD_MEDICO);
		q.setParameter("rep", rep);
		q.setParameter("med", med);
		q.setParameter("stat", status); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		q.setParameter("ano",cal.get(Calendar.YEAR));
		return (Long)q.uniqueResult();
	}
	
	public Visita ultimaDia(Medico med, Representante rep, Date fecha, int status) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_ULTIMA_DIA);
		q.setParameter("rep", rep);
		q.setParameter("med", med);
		q.setParameter("stat", status); 
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		q.setParameter("mes",cal.get(Calendar.MONTH)+1);
		q.setParameter("dia",cal.get(Calendar.DATE));
		return (Visita)q.uniqueResult();
	}

	public int visitaAutorizada(Medico med, Representante rep, Date fecha) {
		Criteria crit = getSession().createCriteria(VProgramada.class);
		crit.add(Restrictions.eq("rep", rep));
		crit.add(Restrictions.eq("medico", med));
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();
	}


}
