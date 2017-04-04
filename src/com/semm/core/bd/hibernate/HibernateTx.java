package com.semm.core.bd.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.semm.core.bd.TxDAO;

public class HibernateTx implements TxDAO {

	private Logger log = Logger.getLogger(HibernateTx.class);
	private Transaction tx;

	public void beginTx() {
		Session ses = HibernateSessionFactory.currentSession();
		//log.debug("Sesion: " + ses);
		tx = ses.beginTransaction();
		//log.debug("TX: " + tx);
	}

	public void commit() {
			//log.debug("TX COMITIEADO : " + tx);
			tx.commit();
			//log.debug("TX COMMITED");
	}
	
	public void rollback(){
			log.debug("TX ROLLBACK : " + tx);
			tx.rollback();
			log.debug("TX ROLLBACKED");
	}

	public void cerrarSesion() {
		HibernateSessionFactory.closeSession();

	}

	public void limpiarSesion() {
		Session ses = HibernateSessionFactory.currentSession();
		ses.clear();
		
		
	}

	public void evict(Object o) {
		Session ses = HibernateSessionFactory.currentSession();
		ses.evict(o);
	}

}
