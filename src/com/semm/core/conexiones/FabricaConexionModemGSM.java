package com.semm.core.conexiones;

public class FabricaConexionModemGSM extends FabricaConexion {

	@Override
	public Conexion crearConexion(String... params) {
		ConexionModemGSM gsm = new ConexionModemGSM();
		gsm.setPort(params[0]);
		gsm.setBaud(Integer.parseInt(params[1]));
		gsm.setDevice(params[2]);
		gsm.setModel(params[3]);
		gsm.setMaxlength(Integer.parseInt(params[4]));
		gsm.setPagados(Integer.parseInt(params[5]));
		gsm.setTransrate(10);
		return gsm;
	}

}
