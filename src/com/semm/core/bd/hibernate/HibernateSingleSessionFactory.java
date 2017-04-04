package com.semm.core.bd.hibernate;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateSingleSessionFactory {

    private static final SessionFactory sessionFactory;
    private static Session session;
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public synchronized static Session getSession() {
    	

		if (session == null || !session.isOpen()) {
		
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;

		}

        return session;

    }
    
    public synchronized static void closeSession() throws HibernateException {

        if (session != null) {
            session.close();
        }
    }

}