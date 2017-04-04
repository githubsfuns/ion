package com.semm.core.bd.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.ListaDAO;
import com.semm.core.servicios.Lista;
import com.semm.core.servicios.Usuario;

public class ListaDAOHibernate  extends HibernateGenericoDAO<Lista, Long>
implements ListaDAO {

	@SuppressWarnings("unchecked")
	public List<Lista> buscarListas(Usuario u) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("owner",u)).addOrder(Order.asc("nombre"));
		return crit.list();
	}

	public Lista buscarLista(String nombre, Usuario u) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("owner",u));
		crit.add(Restrictions.eq("nombre",nombre));
		return (Lista)crit.uniqueResult();
	}
	
	
}
