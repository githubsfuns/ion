package com.semm.core.bd.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.ClienteDAO;
import com.semm.core.servicios.Cliente;
import com.semm.core.servicios.Usuario;

public class ClienteDAOHibernate extends HibernateGenericoDAO<Cliente, Long>
implements ClienteDAO {

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarCliente(Usuario u,int start, int max) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("owner",u)).addOrder(Order.asc("nombre"));
		if (start>=0 ){
			crit.setFirstResult(start);}
		if (max>=0 ){
			crit.setMaxResults(max);}
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarCliente(String query, Usuario u,int start, int max) {
		String qrys[] = query.split(" ");
		
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("owner",u));
		
		for(String q : qrys)
			crit.add(Restrictions.or(Restrictions.or(Restrictions.ilike("nombre","%" + q + "%"), Restrictions.ilike("apellido","%" + q + "%")),Restrictions.or(Restrictions.ilike("tlf","%" + q + "%"), Restrictions.ilike("tlf2","%" + q + "%"))));

		crit.addOrder(Order.asc("nombre"));
		crit.setFirstResult(start);
		crit.setMaxResults(max);
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> porCelular(String query, Usuario u,int start, int max) {
		String qrys[] = query.split(" ");
		
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("owner",u));
		
		for(String q : qrys)
			crit.add(Restrictions.or(Restrictions.ilike("tlf","%" + q + "%"), Restrictions.ilike("tlf2","%" + q + "%")));

		crit.addOrder(Order.asc("nombre"));
		crit.setFirstResult(start);
		crit.setMaxResults(max);
		return crit.list();
	}

	public Object cantidad(Usuario u) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("owner",u));
		crit.setProjection(Projections.rowCount());
		return crit.uniqueResult();
	}

	public Cliente buscarClientePorId(Long id, Usuario u) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("owner",u));
		crit.add(Restrictions.eq("id",id));
		return null;
	}
	
	
	
}
