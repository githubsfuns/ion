
package com.semm.core.bd;

import com.semm.core.servicios.trivia.Venta;







public interface VentaDAO extends GenericoDao<Venta,Long> {
	public Venta buscarFactura(String fact);
}
