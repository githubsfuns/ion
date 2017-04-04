package com.semm.core.bd;
import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface GenericoDao<T, ID extends Serializable> {

    public T buscarPorId(ID id, boolean lock);
    
    public T buscarPorIdGet(ID id, boolean lock);

    public List<T> buscarTodos();

    public List<T> buscarPorEjemplo(T ejmInstancia,String... excludeProperty);

    public T guardar(T entidad);

    public void borrar(T entidad);
    
    public List<T> buscarPorListaCriterios(List<Criterion> criterion);
    
    public void refresh(T entidad);
    
    
}
