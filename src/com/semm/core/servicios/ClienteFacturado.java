package com.semm.core.servicios;

import java.util.Set;

public class ClienteFacturado {

	private long id;
    private String razon;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private Set<Usuario> usuarios;
    private Set<Factura> factura;
        
        
    public long getId(){
        return this.id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public String getRazon(){
        return this.razon;
    }

    public void setRazon(String razon){
        this.razon = razon;
    }
    
    public String getDireccion(){
        return this.direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    public String getTelef1(){
        return this.telefono1;
    }

    public void setTelef1(String telef1){
        this.telefono1 = telef1;
    }
    
    public String getTelef2(){
        return this.telefono2;
    }

    public void setTelef2(String telef2){
        this.telefono2 = telef2;
    }
    
    public Set<Usuario> getUsuarios(){
        return this.usuarios;
    }

    public void setUsuarios(Usuario usuarios){
        this.usuarios.add(usuarios);
    }
    
    public Set getFactura(){
        return this.factura;
    }

    public void setFacturas(Factura factura){
        this.factura.add(factura);
    }
}
