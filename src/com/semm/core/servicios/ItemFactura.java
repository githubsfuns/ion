/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.semm.core.servicios;

/**
 *
 * @author ruchango
 */
public class ItemFactura {
    
      
    private Item servicio;
    private int cantidad;
    private float precio_final;
    
    
    public Item getServicio(){
        return this.servicio;
    }
    
    public void setServicio(Item servicio){
        this.servicio = servicio;
    }
    
    public int getCantidad(){
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public float getPrecio(){
        return this.precio_final;
    }
    
    public void setPrecio(float precio){
        this.precio_final = precio;
    }

}