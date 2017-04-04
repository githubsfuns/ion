package com.semm.core.bd.hibernate;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.LlamadaDAO;
import com.semm.core.conexiones.Llamada;
import com.semm.core.servicios.Usuario;

public  class LlamadaDAOHibernate extends HibernateGenericoDAO<Llamada, Long>  implements LlamadaDAO {

	@SuppressWarnings("unchecked")
	public List<Llamada> buscar(Usuario u, int off, int max) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("usuario",u));
		crit.addOrder(Order.desc("fechahora"));
		crit.setFirstResult(off);
		crit.setMaxResults(max);
		return crit.list();
	}
	
	
}