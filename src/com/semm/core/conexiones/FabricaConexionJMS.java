package com.semm.core.conexiones;

public class FabricaConexionJMS extends FabricaConexion {

	@Override
	public Conexion crearConexion(String... params) {
		ConexionJMS jms = new ConexionJMS();
		jms.setUri(params[0]);
		jms.setCola_in(params[1]);
		jms.setCola_out(params[2]);
		return jms;
	}

}
