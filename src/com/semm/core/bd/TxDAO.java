package com.semm.core.bd;

public interface TxDAO {
	public void beginTx();
	
	public void commit();
	public void rollback();
	public void cerrarSesion();
	public void limpiarSesion();
	public void evict(Object o);
}
