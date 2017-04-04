package com.semm.core.comando;

import java.util.Calendar;
import java.util.Date;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.Conexion;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.ExtendedTransData;
import com.semm.core.conexiones.TransData;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.conexiones.contenido.Contenido;
import com.semm.core.servicios.NotificacionSalida;

public class NotificarUnaSolaVez extends Comando {
	

	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	private NotificacionSalida out;
	private boolean report;
	private FabricaDAO fab;
	private int interval;
	private String temprecip;
	public static Logger log = Logger.getLogger(NotificarUnaSolaVez.class);

	public NotificarUnaSolaVez(int interval) {
		 fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		 this.interval = interval;
	}


	public boolean isReport() {
		return report;
	}


	public void setReport(boolean report) {
		this.report = report;
	}


	@Override
	public void ejecutar(NuevoMensaje m) {
		UserTransData td = (UserTransData)m;
		
		try {
		boolean prio = (td instanceof ExtendedTransData);

		CnxMensaje outmsg;
		
		for(Entry<Integer,Conexion> i: out.getRoutes().entrySet()){
			outmsg = new CnxMensaje();
			
			if(prio){
				ExtendedTransData ptd = (ExtendedTransData)td;
			    outmsg.setProgramado((ptd.getTipo() > 1));
				outmsg.setReport(ptd.isReporte());
			} else {
				outmsg.setReport(this.report);
			}
			
			//Buscar recipiente correspondiente a la conexion
			String recip = td.getRecips().get(i.getKey().intValue());
			outmsg.setPara(recip);

			
			Contenido outcont = out.getContenido();
			Contenido pcont = outcont.getContenido();
			
			if(!verificarRecip(recip,td))
				return;
			
			log.debug("Verificado puede hacer envio.");
			
			for(Entry<Integer,String> i1: out.getDatareplace().entrySet()){
				String data = td.getData().get(i1.getKey().intValue());
				pcont.replace(i1.getValue(),data);
			}
			
			outmsg.setContenido(pcont);
			outmsg.setServicio(td.getServicio());
			outmsg.setCliente(td.getCliente());
			outmsg.setOwner(td.getOwner());
			outmsg.setBlocked(s.getBlocked());
			outmsg.setGrpcnx(s.getSvc().getGrpcnx());
			
			
			
			mcnx.enviarRoundRobin(outmsg);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private boolean verificarRecip(String recip,UserTransData td) {
		
		if(getS().getAttribs().containsKey("tlfqp")){
			String temp =(String) getS().getAttribs().get("tlfqp");
			if(temp.equalsIgnoreCase(recip))
				return false;
		}else {
			getS().getAttribs().put("tlfqp", recip);
		}
		
		
		
		MensajeDAO mdao = fab.getMensajeDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		log.debug("Verificando cantidad de mensajes para: " + recip + " del servicio: " + td.getServicio());
		int cant = (Integer) mdao.cantidad(td.getOwner(), 0,recip,td.getServicio(),cal.getTime() , new Date());
		log.debug("Cantidad: " + cant);
		tx.commit();
		
		return (cant==0);
	}


	public NotificacionSalida getOut() {
		return out;
	}


	public void setOut(NotificacionSalida out) {
		this.out = out;
	}

}
