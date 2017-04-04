package com.semm.core.comando;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.MensajeLog;

public abstract class Transicion {
	
	public static Logger log = Logger.getLogger(Transicion.class);

	
	
	/**
	 * 
	 */
	public Transicion() {
		
	}


	public void guardarMensaje(NuevoMensaje m){
		
		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			MensajeLog mlog = new MensajeLog(sms.getPara(),sms.getContenido().getMsg(),sms.getServicio(),sms.getOwner(),sms.getCnx(),sms.getTipo(),sms.getBlocked());
			log.debug("Guardando mensaje entrante " + mlog.getMobile() + " " + mlog.getCnx().getNombre() + " " + mlog.getMsg() + " " + mlog.getCliente().getUsername());
			FabricaDAO fab =  FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
			MensajeDAO mdao = fab.getMensajeDAO();
			TxDAO tx = fab.getTx();
			try {
				tx.beginTx();
				
				mdao.guardar(mlog);
				
				tx.commit();
				tx.evict(mlog);
			} catch (RuntimeException e){
				log.error(e);
				e.printStackTrace();
				tx.rollback();
				tx.cerrarSesion();
				try {
					//m.getCnx().enviar(new CnxMensaje("04141330820","SEMM",m.getCnx(),new ContenidoSMS("Alerta: "+e.getMessage() + " Para:" + sms.getPara())));
				} catch (Exception e1) {
					log.fatal(e1);
				}
			} finally {
				
			}
		} 
		
	}

	
	public abstract Comando getNxtCmd(NuevoMensaje m);
}
