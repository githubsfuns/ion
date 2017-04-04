package com.semm.core.bd.hibernate;

import org.hibernate.Query;


import com.semm.core.bd.UsuarioDAO;
import com.semm.core.servicios.Usuario;

public class UsuarioDAOHibernate extends HibernateGenericoDAO<Usuario, String>
implements UsuarioDAO {

	public int restarSaldo(Usuario u) {
		
		Query q = getSession().getNamedQuery(UsuarioDAO.QUERY_SALDO);
		q.setInteger(0, u.getCredits());
		q.setString(1, u.getUsername());
		return q.executeUpdate();
		
	}

}
