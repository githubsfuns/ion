package com.semm.core.bd;

import com.semm.core.servicios.Usuario;



public interface UsuarioDAO extends GenericoDao<Usuario, String>{
	public static final String QUERY_SALDO = "UsuarioDAO.Saldo";
	public int restarSaldo(Usuario u);
}
