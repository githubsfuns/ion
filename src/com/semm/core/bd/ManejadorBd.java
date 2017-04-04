package com.semm.core.bd;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;

import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.Llamada;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.servicios.KeywordSet;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.MensajePorProcesar;
import com.semm.core.servicios.SesionLog;
import com.semm.core.servicios.UserService;
import com.semm.core.servicios.Usuario;
import com.semm.core.sesiones.ManejadorSesiones;


public class ManejadorBd {

	private static ManejadorBd instance = null;
	public static Logger log = Logger.getLogger(ManejadorBd.class);
	private FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);

	private ManejadorBd() {}
	
	public static ManejadorBd getInstancia(){
		if(instance == null){
			instance = new ManejadorBd();
		}
		return instance;
	}
	
	public SesionLog buscarSesion(CnxMensaje sms){
		return buscarSesion(sms.getPara());
	}
	
	public SesionLog buscarSesion(Llamada call){
		return buscarSesion(call.getTlf());
	}
	
	private SesionLog buscarSesion(String tlf){
		SesionLog slog = null;
		TxDAO tx = fab.getTx();
		SesionLogDAO slogdao = fab.getSesionLogDAO();
		tx.beginTx();
		slog = slogdao.buscarPorIdGet(tlf, false);
		tx.commit();
		tx.evict(slog);
		return slog;
	}
	
	public void cerrarSesion(){
		TxDAO tx = fab.getTx();
		tx.cerrarSesion();
		
	}
	
	public Map<String, UserService> buscarKeywordSet(CnxMensaje sms){
		KeywordSet kws = null;
		TxDAO tx = fab.getTx();
		KeywordSetDAO kwdao = fab.getKeywordSetDAO();
		tx.beginTx();
	
		kws = kwdao.buscarPorIdGet(new Long(1), false);
		tx.commit();
		return kws.getKeywords();
	}
	
	public void setOwner(NuevoMensaje m){
		m.setOwner(getOwner(m.getCliente()));
	}

	
	public Usuario getOwner(String cliente){
		log.debug(" Buscando User: " + cliente);
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		UsuarioDAO udao = fab.getUsuarioDAO();
		tx.beginTx();
		Usuario u = udao.buscarPorIdGet(cliente, false);
		tx.commit();
		
		if(u!=null){
			log.debug("Usuario: " + u.getUsername() + " ObjID: " + u);	
		} 
		return u;
		
	}
	
	public void guardarLlamada(Llamada call){
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		LlamadaDAO ldao = fab.getLlamadaDAO();
		tx.beginTx();
		ldao.guardar(call);
		tx.commit();
	}
	
	public void guardarMensaje(NuevoMensaje m){
		
		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			MensajeLog mlog = new MensajeLog(sms.getPara(),sms.getContenido().getMsg(),sms.getServicio(),sms.getOwner(),sms.getCnx(),sms.getTipo(),sms.getSubtipo());
			log.debug("Guardando mensaje " + mlog.getMobile() + " - " + mlog.getCnx().getNombre() + " \n" + mlog.getMsg() + " TIPO:" + sms.getTipo() + " SUBTIPO:" + sms.getSubtipo());
			FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
			MensajeDAO 	mdao = fab.getMensajeDAO();
			TxDAO tx = fab.getTx();
			try {
				tx.beginTx();
				mdao.guardar(mlog);
				tx.commit();
			} catch (RuntimeException e){
				log.error(e);
				e.printStackTrace();
				tx.rollback();
				tx.cerrarSesion();			
			} 
		
		}
	}
	
	public void borrar(MensajePorProcesar mpp){
			FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
			MensajePorProcesarDAO 	mdao = fab.getMensajePorProcesarDAO();
			TxDAO tx = fab.getTx();
			try {
				tx.beginTx();
				mdao.borrar(mpp);	
				tx.commit();
				log.debug("Borrado Mensaje Generador: " + mpp.getId());
				tx.evict(mpp);
			
			} catch (RuntimeException e){
				log.error(e);
				e.printStackTrace();
				tx.rollback();
				tx.cerrarSesion();
				
			} 
				
	}
	
	public MensajePorProcesar guardarMensaje(UserTransData utd){
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		MensajePorProcesarDAO 	mdao = fab.getMensajePorProcesarDAO();
		TxDAO tx = fab.getTx();
		try {
			tx.beginTx();
			
			MensajePorProcesar mpp = generar(utd);
			
			mdao.guardar(mpp);
			tx.commit();
			log.debug("Mensaje Generador ID: " + mpp.getId());
			tx.evict(mpp);
			return mpp;
		} catch (RuntimeException e){
			log.error(e);
			e.printStackTrace();
			tx.rollback();
			tx.cerrarSesion();
			
		} 
		return null;
	}
	
	public MensajePorProcesar generar(UserTransData utd) {
		MensajePorProcesar mpp = new MensajePorProcesar();
		mpp.setCliente(utd.getCliente());
		mpp.setServicio(utd.getServicio());
		mpp.setCnx(utd.getCnx());
		String alldata = "";
		for(String data: utd.getData()){
			alldata = data + "|";
		}
		alldata = alldata.substring(0,alldata.length()-1);
		mpp.setData(alldata);
		String allrecips = "";
		for(String recip: utd.getRecips()){
			allrecips = recip + "|";
		}
		allrecips = allrecips.substring(0,allrecips.length()-1);
		mpp.setRecips(allrecips);
		
		return mpp;
	}
	
	public List<MensajePorProcesar> buscarMensajesPorProcesar(){
		
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		MensajePorProcesarDAO 	mdao = fab.getMensajePorProcesarDAO();
		TxDAO tx = fab.getTx();
		try {
			tx.beginTx();
			
			List<MensajePorProcesar> mpp = mdao.buscarTodos();
			
			
			tx.commit();
			
			
			return mpp;
		} catch (RuntimeException e){
			log.error(e);
			e.printStackTrace();
			tx.rollback();
			tx.cerrarSesion();
			
		} 
		return null;
	}
	
	public UserTransData generar(MensajePorProcesar utd){
		UserTransData mpp = new UserTransData();
		mpp.setCliente(utd.getCliente());
		mpp.setServicio(utd.getServicio());
		mpp.setCnx(utd.getCnx());
		
		String data[] = utd.getData().split("|");
		mpp.setData(Arrays.asList(data));
		String recips[] = utd.getRecips().split("|");
		mpp.setRecips(Arrays.asList(recips));
		
		return mpp;
	}

	public void init(){
		Thread sesiones = new Thread(){	
			public void run() {
			
			
			}
			
		};
	}
	
	
}
