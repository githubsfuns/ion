package com.semm.core;

import java.util.Date;

import org.apache.log4j.Logger;

import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.servicios.ManejadorServicios;
import com.semm.core.sesiones.ManejadorSesiones;

public class ControlCentral extends Thread {
	
	public static Logger log = Logger.getLogger(ControlCentral.class);
	
	private ManejadorConexiones mcnx;
	private ManejadorSesiones mses;
	private ManejadorServicios mnot;
	
	
	public void inicializarSemm() throws Exception {
		log.debug("Inicializando ION");
		
		mcnx = ManejadorConexiones.getInstancia();
		mses = ManejadorSesiones.getInstancia();
		mnot = ManejadorServicios.getInstancia();

		
		try {
			
			mcnx.inicializar();	
			mses.init();
			mnot.init();
			
		} catch (RuntimeException e) {
			log.error(e);
			e.printStackTrace();
		}catch (Exception e){
			log.error(e);
			e.printStackTrace();
		}
		
		log.debug("ION Listo...");
	}
	
	public void run(){
		log.debug("Apagando ION");
		finalizarSemm();
		log.debug("ION APAGADO @ : " + new Date());
	}
	

	public void finalizarSemm(){
		mses.apagar();
		mcnx.terminar();
	}
	

}
