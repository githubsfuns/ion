package com.semm.core.bd.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.CompraDAO;
import com.semm.core.servicios.Compra;
import com.semm.core.servicios.Usuario;

public class CompraDAOHibernate 
	    extends HibernateGenericoDAO<Compra, Long>
	    implements CompraDAO {

	@SuppressWarnings("unchecked")
	public List<Compra> buscarCompras(Usuario u, int off, int max) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("usuario",u));
		crit.addOrder(Order.desc("fecha"));
		crit.setFirstResult(off);
		crit.setMaxResults(max);
		return crit.list();
	}
	
	
	
	
	
}

