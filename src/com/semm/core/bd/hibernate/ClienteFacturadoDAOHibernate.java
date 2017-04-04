package com.semm.core.bd.hibernate;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.ClienteFacturadoDAO;

import com.semm.core.servicios.Cliente;
import com.semm.core.servicios.ClienteFacturado;
import com.semm.core.servicios.Usuario;

public class ClienteFacturadoDAOHibernate extends HibernateGenericoDAO<ClienteFacturado, Long>
implements ClienteFacturadoDAO {
	
	public List<ClienteFacturado> buscarClienteFacturado(int start, int max) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.addOrder(Order.asc("razon"));
		crit.setFirstResult(start);
		crit.setMaxResults(max);
		return crit.list();
	}
	
	public List<ClienteFacturado> buscarClienteFacturado(String query, int start, int max) {
		String qrys[] = query.split(" ");
		
		Criteria crit = getSession().createCriteria(getPersistentClass());
				
		for(String q : qrys)
			crit.add(Restrictions.ilike("razon","%" + q + "%"));

		crit.addOrder(Order.asc("razon"));
		crit.setFirstResult(start);
		crit.setMaxResults(max);
		return crit.list();
	}
	
	public Object cantidad() {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.addOrder(Order.asc("username"));
		crit.setProjection(Projections.rowCount());
		return crit.uniqueResult();
	}
}
