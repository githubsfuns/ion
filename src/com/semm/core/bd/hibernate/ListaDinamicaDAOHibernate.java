package com.semm.core.bd.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.semm.core.bd.*;
import com.semm.core.servicios.*;

public class ListaDinamicaDAOHibernate  extends HibernateGenericoDAO<ListaDinamica, Long>
implements ListaDinamicaDAO {

	@SuppressWarnings("unchecked")
	public List<ListaDinamica> buscarListas(Usuario u) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("owner",u)).addOrder(Order.asc("nombre"));
		return crit.list();
	}
	
	
}
