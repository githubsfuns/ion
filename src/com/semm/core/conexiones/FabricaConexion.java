package com.semm.core.conexiones;

public abstract class FabricaConexion {
	
	public static String JMS  = "com.semm.core.conexiones.ConexionJMS";
	public static String Consola  = "com.semm.core.conexiones.ConexionConsola";
	
	public static FabricaConexion getFabrica(String clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return (FabricaConexion) (Class.forName(clazz).newInstance());
	}
	
	public abstract Conexion crearConexion(String...params);
}
