package com.semm.core.bd.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import com.semm.core.bd.GenericoDao;



public abstract class HibernateGenericoDAO<T,ID extends Serializable> implements GenericoDao<T,ID>{
	
	 	private Class<T> persistentClass;
	    private Session session;

	    public HibernateGenericoDAO() {
	        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
	                                .getGenericSuperclass()).getActualTypeArguments()[0];
	     }

	    @SuppressWarnings("unchecked")
	    public void setSession(Session s) {
	        this.session = s;
	    }

	    protected Session getSession() {
	        if (session == null)
	            throw new IllegalStateException("Session has not been set on DAO before usage");
	        return session;
	    }

	    public Class<T> getPersistentClass() {
	        return persistentClass;
	    }

	    @SuppressWarnings("unchecked")
	    public T buscarPorId(ID id, boolean lock) {
	        T entity;
	        if (lock)
	            entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
	        else
	            entity = (T) getSession().load(getPersistentClass(), id);

	        return entity;
	    }
	    
	    @SuppressWarnings("unchecked")
	    public T buscarPorIdGet(ID id, boolean lock) {
	        T entity;
	        if (lock)
	            entity = (T) getSession().get(getPersistentClass(), id, LockMode.UPGRADE);
	        else
	            entity = (T) getSession().get(getPersistentClass(), id);

	        return entity;
	    }


	    @SuppressWarnings("unchecked")
	    public List<T> buscarTodos() {
	        return buscarPorCriterio();
	    }

	    @SuppressWarnings("unchecked")
	    public List<T> buscarPorEjemplo(T ejmInstancia, String... excludeProperty) {
	        Criteria crit = getSession().createCriteria(getPersistentClass());
	        Example example =  Example.create(ejmInstancia);
	        for (String exclude : excludeProperty) {
	            example.excludeProperty(exclude);
	     
	        }
	        crit.add(example);
	        return crit.list();
	    }

	    @SuppressWarnings("unchecked")
	    public T guardar(T entity) {
	        getSession().saveOrUpdate(entity);
	        return entity;
	    }

	    public void borrar(T entity) {
	        getSession().delete(entity);
	    }

	    public void flush() {
	        getSession().flush();
	    }

	    public void clear() {
	        getSession().clear();
	    }
	    
	    public void refresh(T entity){
	    	getSession().refresh(entity);
	    }

	    /**
	     * Use this inside subclasses as a convenience method.
	     */
	    @SuppressWarnings("unchecked")
	    protected List<T> buscarPorCriterio(Criterion... criterion) {
	        Criteria crit = getSession().createCriteria(getPersistentClass());
	        for (Criterion c : criterion) {
	            crit.add(c);
	        }
	        return crit.list();
	   }
	    
	    @SuppressWarnings("unchecked")
	    public List<T> buscarPorListaCriterios(List<Criterion> criterion) {
	        Criteria crit = getSession().createCriteria(getPersistentClass());
	        for (Criterion c : criterion) {
	            crit.add(c);
	        }
	        return crit.list();
	   }
	
	
}