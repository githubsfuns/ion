package com.semm.core.comando;

import java.util.Date;
import java.util.List;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;

public class ConsultarHibernate extends Comando{

	@Override
	public void ejecutar(NuevoMensaje m) {
		
		CnxMensaje sms = (CnxMensaje)m;
		//Mosca solo pruebas
		String para = sms.getPara().replaceAll("\\+","");
		para = para.replaceAll("^58412","");
		
		CnxMensaje reply = new CnxMensaje();
		reply.setPara(para);
		reply.setCnx(sms.getCnx());
		
		
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		MensajeDAO mdao = fab.getMensajeDAO();
		tx.beginTx();
		
		List<Object[]> reporte = mdao.reporte(new Date(),0);
		
		String resp = "";
		
		for(Object[] cli : reporte){
			
		}
		
		
		
		ContenidoSMS cont = new ContenidoSMS(resp);
		cont.setTemp_msg(cont.getMsg());
		reply.setContenido(cont);
		reply.setServicio(m.getServicio());
		reply.setCliente(m.getCliente());
		//mcnx.buscarRutaMaxGanancias().agregarMsgCola(reply);
		//m.getCnx().agregarMsgCola(reply);
		
	}

}
