package com.semm.core.bd.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.semm.core.bd.MensajeDAO;
import com.semm.core.servicios.MensajeNombrado;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.Usuario;

public class MensajeDAOHibernate extends HibernateGenericoDAO<MensajeLog,Long> implements
		MensajeDAO {

		@SuppressWarnings("unchecked")
		public List<MensajeLog> buscarPorFecha(Usuario user,int tipo,int max){
			Criteria crit = getSession().createCriteria(getPersistentClass());
			crit.add(Restrictions.eq("cliente",user));
			crit.add(Restrictions.eq("tipo",tipo));
			crit.addOrder(Order.desc("fechahora"));
			crit.setMaxResults(max);
			return crit.list();
		}

		public Object cantidad(Usuario user,int tipo,Date fechai, Date fechaf) {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			crit.add(Restrictions.eq("cliente",user));
			crit.add(Restrictions.eq("tipo",tipo));
			crit.add(Restrictions.between("fechahora",fechai,fechaf));
			crit.setProjection(Projections.rowCount());
			return crit.uniqueResult();
		}
		
		public Object cantidad(Usuario user,int tipo,String mobile,String svc,Date fechai, Date fechaf) {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			crit.add(Restrictions.eq("cliente",user));
			crit.add(Restrictions.eq("tipo",tipo));
			crit.add(Restrictions.eq("servicio",svc));
			crit.add(Restrictions.eq("mobile",mobile));
			crit.add(Restrictions.between("fechahora",fechai,fechaf));
			crit.setProjection(Projections.rowCount());
			return crit.uniqueResult();
		}

		public Object cantidad(Usuario user,int tipo) {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			crit.add(Restrictions.eq("cliente",user));
			crit.add(Restrictions.eq("tipo",tipo));
			crit.setProjection(Projections.rowCount());

			return crit.uniqueResult();
		}

		@SuppressWarnings("unchecked")
		public List<Object[]> reporte(Date mes,int tipo) {
			Query q = getSession().getNamedQuery(MensajeDAO.QUERY_REPORTE);
			Calendar cal = new GregorianCalendar();
			cal.setTime(mes);
			q.setParameter("tip", tipo);
			q.setParameter("dia", cal.get(Calendar.DAY_OF_MONTH));
			q.setParameter("mes", cal.get(Calendar.MONTH));
			q.setParameter("ano", cal.get(Calendar.YEAR));

			return q.list();
		}

		@SuppressWarnings("unchecked")
		public List<MensajeNombrado> buscarPorMobile(Usuario user, String mobile,int start, int max) {
			SQLQuery q = getSession().createSQLQuery("select msg.*,cli.nombre,cli.apellido " +
					"from (select * from mensaje where mobile=:mobile and cliente = :user order by fechahora desc offset :off limit :lim) as msg " +
					"left Join (select tlf,nombre,apellido from cliente where owner = :user) cli on (cli.tlf=msg.mobile) order by fechahora desc");
					q.setString("mobile", mobile); 
					q.setString("user", user.getUsername());
					q.setInteger("off", start);
					q.setInteger("lim", max);
					q.addEntity(com.semm.core.servicios.MensajeNombrado.class);
			return q.list();
		}

		@SuppressWarnings("unchecked")
		public List<MensajeNombrado> buscar(Usuario user, String query,int start, int max) {
			SQLQuery q = getSession().createSQLQuery("select msg.*,cli.nombre,cli.apellido " +
					"from (select * from mensaje where cliente = :user and (msg like '%:query%' or mobile '%:query%') order by fechahora desc offset :off limit :lim) as msg " +
					"left Join (select tlf,nombre,apellido from cliente where owner = :user) cli on (cli.tlf=msg.mobile) order by fechahora desc");
					q.setString("query",query); 
					q.setString("user", user.getUsername());
					q.setInteger("off", start);
					q.setInteger("lim", max);
					q.addEntity(com.semm.core.servicios.MensajeNombrado.class);
			return q.list();
			/*
			String qrys[] = query.split(" ");
			
			Criteria crit = getSession().createCriteria(com.semm.core.servicios.MensajeNombrado.class);
			crit.add(Restrictions.eq("cliente",user));
			for(String q : qrys)
				crit.add(Restrictions.or(Restrictions.or(Restrictions.ilike("msg","%" + q + "%"),Restrictions.ilike("mobile","%" + q + "%")),Restrictions.or(Restrictions.ilike("nombre","%" + q + "%"),Restrictions.ilike("apellido","%" + q + "%"))));
			
			crit.addOrder(Order.desc("fechahora"));
			crit.setMaxResults(max);
			return crit.list();*/
		}

		@SuppressWarnings("unchecked")
		public List<MensajeNombrado> listar(Usuario user, int tipo,int start, int max) {
			SQLQuery q = getSession().createSQLQuery("select msg.*,cli.nombre,cli.apellido from " +
					"(select * from mensaje where tipo=:tipo and cliente = :user order by fechahora desc offset :off limit :lim) as msg " +
					"left Join (select tlf,nombre,apellido from cliente where owner = :user) cli on (cli.tlf=msg.mobile) order by fechahora desc");
			q.setInteger("tipo", tipo); 
			q.setString("user", user.getUsername());
			if (start>=0){q.setInteger("off", start);}
			if (max>=0) {q.setInteger("lim", max);}
			q.addEntity(com.semm.core.servicios.MensajeNombrado.class);
			return q.list();
		}

		public MensajeLog buscarEnviadoPorMobile(String mobile) {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			crit.add(Restrictions.eq("mobile",mobile));
			crit.add(Restrictions.eq("tipo",0));
			crit.add(Restrictions.eq("entregado",false));
			crit.addOrder(Order.desc("fechahora"));
			crit.setMaxResults(1);
			return (MensajeLog)crit.uniqueResult();
		}

		@SuppressWarnings("unchecked")
		public List<MensajeLog> listar(int tipo) {
			Criteria crit = getSession().createCriteria(getPersistentClass());
			crit.add(Restrictions.eq("tipo",tipo));
			return crit.list();
		}
	



}
