package com.semm.core.conexiones;

public class FabricaConexionHTTP extends FabricaConexion {

	@Override
	public Conexion crearConexion(String... params) {
		ConexionHTTP http = new ConexionHTTP();
		http.setUrl(params[0]);
		if(params.length > 2){
			http.setProxyHost(params[1]);
			http.setProxyPort(Integer.parseInt(params[2]));
		}
		return http;
	}

}
