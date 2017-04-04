/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.semm.core.servicios;


import java.util.Set;
import java.util.Date;
/**
 *
 * @author ruchango
 */
public class Factura {
    
    private Set<ItemFactura> items;
    private long id;
    private ClienteFacturado cliente;
    private Date fecha;
    
    public Set<ItemFactura> getItems(){
        return this.items;
    }
    
    public void setItems(Set<ItemFactura> items){
        this.items = items;
    }
    
    public long getId(){
        return this.id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public ClienteFacturado getCliente(){
        return this.cliente;
    }
    
    public void setCliente(ClienteFacturado cliente){
        this.cliente = cliente;
    }
    
    public Date getFecha(){
        return this.fecha;
    }
    
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }

}
