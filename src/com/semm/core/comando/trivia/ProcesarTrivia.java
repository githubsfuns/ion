package com.semm.core.comando.trivia;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.trivia.PreguntaDAO;
import com.semm.core.comando.Comando;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ConexionModemGSM;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.trivia.Pregunta;

public class ProcesarTrivia extends  Comando{

	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	
	
	@Override
	public void ejecutar(NuevoMensaje m) {
		// TODO Auto-generated method stub
		UserTransData td = (UserTransData)m;
		String tlf,id_preg;

		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		PreguntaDAO pdao = fab.getPreguntaDAO();
		TxDAO tx = fab.getTx();
		
		tlf = td.getRecips().get(0);
		id_preg = td.getData().get(0);
		
		tx.beginTx();
		
		Pregunta pregunta = pdao.buscarPorId(Long.parseLong(id_preg), true);
		
		tx.commit();
		
		this.s.getAttribs().put("id_pregunta", id_preg);
		
		CnxMensaje reply = new CnxMensaje();
		reply.setPara(tlf);
		//reply.setReport(true);
		ContenidoSMS cont = new ContenidoSMS(pregunta.getTexto());
		cont.setTemp_msg(cont.getMsg());
		reply.setContenido(cont);
		reply.setServicio(m.getServicio());
		reply.setCliente(m.getCliente());
		reply.setOwner(m.getOwner());
		reply.setBlocked(s.getBlocked());
		reply.setGrpcnx(s.getSvc().getGrpcnx());

		mcnx.enviarRoundRobin(reply);
	}

}
