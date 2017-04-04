package com.semm.core.sesiones;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.semm.core.bd.ManejadorBd;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ReporteEntrega;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.servicios.MensajePorProcesar;
import com.semm.core.servicios.SesionLog;
import com.semm.core.servicios.UserService;
import com.semm.core.sesiones.spamutils.ManejadorSpam;

public class FabricaSesionVisitor implements SesionVisitor {
	
	private Sesion s;
	private ManejadorBd mbd = ManejadorBd.getInstancia();

	public Sesion getS() {
		return s;
	}

	public void setS(Sesion s) {
		this.s = s;
	}

	private Logger log = Logger.getLogger(FabricaSesionVisitor.class);
	
	public void visitar(CnxMensaje sms) {
		SesionLog slog;
		try {
			slog = mbd.buscarSesion(sms);
			if(slog!=null){
			String user = slog.getUsuario().getUsername();
			String svc = slog.getServicio_pred();
			
				if(svc.equals("incoming") || svc.indexOf("inbox")>-1){
					getDetectKW(sms,svc);
					mbd.setOwner(sms);
				} else {
					sms.setCliente(slog.getUsuario().getUsername());
					sms.setOwner(slog.getUsuario());
					//Cableo momentaneo para Mabe - LG
					if(sms.getCnx().getId()==5 && slog.getServicio_pred().equalsIgnoreCase("inbox_og")){
						sms.setServicio("inbox_trivia");
					} else {
						sms.setServicio(slog.getServicio_pred());
					}
					s.setExclusiva(slog.isRevocable());
				}
			}	
			else {
				getDetectKW(sms,"incoming");
				mbd.setOwner(sms);
			}
		}catch (RuntimeException rte){
			mbd.cerrarSesion();
			
		}
		
		ManejadorSpam.getInstancia().detectar(sms);		

	}

	public void visitar(UserTransData utd) {

		mbd.setOwner(utd);
		if(utd.getOwner()!=null){
			log.debug("Verificando usuario: " + utd.getOwner().getUsername());
			utd.getOwner().getPassword();
			s.setLastData(utd.getData());
			MensajePorProcesar generador = mbd.guardarMensaje(utd);
			s.setGenerador(generador);
		} else {
			log.debug("Usuario no autorizado: " + utd.getCliente());
			utd.setServicio("");
		}
		/* 
		 * Verificar Password ION Messenger
		if(utd.getOwner()!= null && utd.getPasswd() !=null){
				GeneradorMD5 gmd5 = new GeneradorMD5();
				String passwd = gmd5.generarMD5(utd.getOwner().getPassword());
				if(passwd.compareTo(utd.getPasswd()) != 0)
					utd.setServicio(null);	
		}
		*/
	}
	
	private void getDetectKW(CnxMensaje sms,String def){
		Map<String, UserService> map = mbd.buscarKeywordSet(sms);
		
		for(Entry<String,UserService> s: map.entrySet()){
			log.debug("Comprobando : " + s.getKey());
			 if(Pattern.compile(s.getKey()).matcher(sms.getContenido().getMsg()).find()){
				 log.debug("Hizo match : " + s.getKey());
				 UserService us = s.getValue();
				 sms.setOwner(us.getUsuario());
				 sms.setCliente(us.getUsuario().getUsername());
				 sms.setServicio(us.getSvc());
				 break;
			 }
		}
		
		if(sms.getOwner()== null){
			sms.setCliente("semm");
			sms.setServicio(def);
		}
	}

	public void visitar(ReporteEntrega rep) {
		log.debug("Reporte de Entrega Inicializando:" + rep.getRecip());
		
	}



}
