package com.semm.core.bd;

import java.util.List;
import java.util.Date;

import com.semm.core.servicios.ClienteFacturado;
import com.semm.core.servicios.Factura;

public interface FacturaDAO extends GenericoDao<Factura,Long> {
	public List<Factura> buscarFactura(ClienteFacturado cli, int max);
	//public List<Factura> buscarFactura(String query,Usuario u,int start, int max);
	//public Object cantidad(Usuario user);
        public Object fecha(Date fecha);
}
