package com.semm.core.bd.hibernate;

import java.util.List;

import org.hibernate.Query;

import com.semm.core.bd.BloqueadoDAO;
import com.semm.core.servicios.NumeroBloqueado;
import com.semm.core.servicios.Usuario;

public class BloqueadoDAOHibernate extends HibernateGenericoDAO <NumeroBloqueado,NumeroBloqueado>
implements BloqueadoDAO{

	@SuppressWarnings("unchecked")
	public List<NumeroBloqueado> buscar(Usuario user) {
		Query q = getSession().getNamedQuery(BloqueadoDAO.QUERY_LISTA);
		q.setParameter("user", user);
		return q.list();
	}

}
