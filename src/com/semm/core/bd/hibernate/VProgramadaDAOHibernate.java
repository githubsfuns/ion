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

import com.semm.core.bd.*;
import com.semm.core.servicios.cvmed.Medico;
import com.semm.core.servicios.cvmed.Representante;
import com.semm.core.servicios.cvmed.VProgramada;
import com.semm.core.servicios.cvmed.Visita;

public class VProgramadaDAOHibernate extends HibernateGenericoDAO<VProgramada, Long>
implements VProgramadaDAO {



	@SuppressWarnings("unchecked")
	public List<Object[]> vistasProgramadasEsp(Representante rep) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_VP_ESP);
		q.setParameter("rep", rep);
		return q.list();
	}



	public Integer buscarCantidadProgramadas(Representante rep) {
		Criteria crit = getSession().createCriteria(VProgramada.class);
		crit.add(Restrictions.eq("rep", rep));
		crit.setProjection(Projections.rowCount());
		return (Integer) crit.uniqueResult();
	}



	@SuppressWarnings("unchecked")
	public List<VProgramada> visitasProgramadas(Representante rep) {
		Query q = getSession().getNamedQuery(VisitaDAO.QUERY_HojaMedica);
		q.setParameter("rep", rep);
		return q.list();
	}


	





}
