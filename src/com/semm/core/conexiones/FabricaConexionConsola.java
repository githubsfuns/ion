package com.semm.core.conexiones;

public class FabricaConexionConsola extends FabricaConexion {

	@Override
	public Conexion crearConexion(String... params) {
		return new ConexionConsola();
	}

}
