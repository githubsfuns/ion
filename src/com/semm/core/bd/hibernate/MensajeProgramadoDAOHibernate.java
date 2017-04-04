package com.semm.core.bd.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.ClienteDAO;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.MensajeProgramadoDAO;
import com.semm.core.servicios.Cliente;
import com.semm.core.servicios.MensajeProgramado;
import com.semm.core.servicios.Usuario;

public class MensajeProgramadoDAOHibernate extends HibernateGenericoDAO<MensajeProgramado, Long>
implements MensajeProgramadoDAO{

	@SuppressWarnings("unchecked")
	public List<MensajeProgramado> buscarActivos() {
		Query q = getSession().getNamedQuery(MensajeProgramadoDAO.QUERY_ACTIVOS);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	public List<MensajeProgramado> buscarMensajes(Usuario u,int row,int max) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("cliente",u.getUsername())).addOrder(Order.asc("hora"));
		crit.setFirstResult(row);
		crit.setMaxResults(max);
		return crit.list();
	}
	

}
