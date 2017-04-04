package com.semm.core.bd.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.VentaDAO;
import com.semm.core.servicios.trivia.Venta;

public class VentaDAOHibernate extends HibernateGenericoDAO<Venta, Long>
implements VentaDAO {
	
	public Venta buscarFactura(String fact){
		Criteria crit = getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("sku",fact));
		return (Venta)crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List generarReporteVentas() {
		SQLQuery sql = getSession().createSQLQuery("select cliente.id,participante.tlf,replace(datos,'\n', ' ') as datos,sku,producto,cantidad,puntosgen,fecha,replace(mm.msg,'\n', ' ') as msg from cliente, listacliente, venta, participante, (select distinct mobile,msg from mensaje where tipo=1 and fechahora > '2010-05-01' and mensaje.cliente='bittime' and msg like '%2010') as mm where cliente.tlf=venta.participante and participante.tlf=venta.participante and (owner='bittime' or owner='semm') and listacliente.lista = 381 and listacliente.cliente = cliente.id and msg like '%'||sku||'%2010' and mm.mobile = venta.participante");
		sql.addScalar("id",Hibernate.LONG);
		sql.addScalar("tlf",Hibernate.STRING);
		sql.addScalar("datos",Hibernate.STRING);
		sql.addScalar("sku",Hibernate.STRING);
		sql.addScalar("producto",Hibernate.STRING);
		sql.addScalar("cantidad",Hibernate.INTEGER);
		sql.addScalar("puntosgen",Hibernate.INTEGER);
		sql.addScalar("fecha",Hibernate.DATE);
		sql.addScalar("msg",Hibernate.STRING);
		return sql.list();
	}
	
	@SuppressWarnings("unchecked")
	public List generarReporteParticipante() {
		SQLQuery sql = getSession().createSQLQuery("select cliente.id,mobile,replace(datos,'\n', ' ')as datos,fechahora from mensaje, cliente, listacliente, participante where cliente.tlf=participante.tlf and (owner='bittime' or owner='semm') and listacliente.lista = 381 and listacliente.cliente = cliente.id and servicio='trivia' and mobile = participante.tlf and tipo = 0 and fechahora > '2010-05-01'  and msg like '%lgclub.com%' order by fechahora;");
		sql.addScalar("id",Hibernate.LONG);
		sql.addScalar("mobile",Hibernate.STRING);
		sql.addScalar("datos",Hibernate.STRING);
		sql.addScalar("fechahora",Hibernate.DATE);
		return sql.list();
	}
    
}
