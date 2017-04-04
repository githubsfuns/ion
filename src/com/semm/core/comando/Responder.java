package com.semm.core.comando;

import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ConexionModemGSM;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;

public class Responder extends Comando {
	
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	private String resp;
	private boolean reporte = false;
	
	public void setReporte(boolean reporte) {
		this.reporte = reporte;
	}

	@Override
	public void ejecutar(NuevoMensaje m) {
		CnxMensaje sms = (CnxMensaje)m;
		
		
		CnxMensaje reply = new CnxMensaje();
		reply.setPara(sms.getPara());
		reply.setCnx(sms.getCnx());
		reply.setReport(reporte);
		reply.setProgramado(sms.isProgramado());
		ContenidoSMS cont = new ContenidoSMS(resp);
		cont.setTemp_msg(cont.getMsg());
		reply.setContenido(cont);
		reply.setServicio(m.getServicio());
		reply.setCliente(m.getCliente());
		reply.setOwner(m.getOwner());
		if(sms.getBlocked() == CnxMensaje.EQUIVOCADO){
			reply.setBlocked(CnxMensaje.NORMAL);
		}
		
		mcnx.enviaraCola(reply, m.getCnx());

	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}


}
