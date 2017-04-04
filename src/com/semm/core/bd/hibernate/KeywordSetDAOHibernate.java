package com.semm.core.bd.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.KeywordSetDAO;
import com.semm.core.conexiones.Conexion;
import com.semm.core.servicios.KeywordSet;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.Usuario;

public class KeywordSetDAOHibernate extends HibernateGenericoDAO<KeywordSet, Long>
implements KeywordSetDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<KeywordSet> buscarPorCnx(Conexion cnx){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("cnx",cnx));
		return crit.list();
		
	}
	
	
}
  
    

