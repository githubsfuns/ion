/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.semm.core.servicios;

/**
 *
 * @author ruchango
 */
public class Item {
    
    private long id;
    private String nombre;
    private String descripcion;
       
    
    public long getId(){
        return this.id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    
}
