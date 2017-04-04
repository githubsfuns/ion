package com.semm.core.bd.hibernate;

import java.util.List;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.ClienteDAO;
import com.semm.core.bd.FacturaDAO;
import com.semm.core.servicios.Cliente;
import com.semm.core.servicios.Factura;
import com.semm.core.servicios.ClienteFacturado;

public class FacturaDAOHibernate extends HibernateGenericoDAO<Factura, Long>
implements FacturaDAO {

    public List<Factura> buscarFactura(ClienteFacturado cli, int max) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("id",cli)).addOrder(Order.desc("fecha"));
		crit.setMaxResults(max);
		return crit.list();
        
    }

   /* public List<Factura> buscarFactura(String query, Usuario u, int max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }*/

    
    public Object fecha( Date fecha) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add(Restrictions.eq("fecha",fecha)).addOrder(Order.asc("razon"));
		return crit.list();
    }

	
}
