package com.semm.core.comando.trivia;

import java.util.HashSet;

import com.semm.core.bd.ClienteDAO;
import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.ListaDAO;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.trivia.ParticipanteDAO;
import com.semm.core.comando.Comando;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ConexionModemGSM;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.Cliente;
import com.semm.core.servicios.Lista;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.trivia.Participante;


public class ProcesarRegistro extends  Comando{

	private static final long PARTICIPANTES = 381L;
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	@Override
	public void ejecutar(NuevoMensaje m) {

		CnxMensaje sms = (CnxMensaje)m;
		String line = "Felicidades! Ya eres miembro de LG CLUB. Una vez que tu registro este verificado recibiras tu clave de acceso a tu cuenta en www.lgclub.com.ve";
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ParticipanteDAO pdao = fab.getParticipanteDAO();
		Participante part;
		
		try {
			TxDAO tx = fab.getTx();
			tx.beginTx();
			part = pdao.buscarPorIdGet(sms.getPara(),true);
			
			if(part!=null){
				
				line = "Este numero ya ha sido registrado en LGCLUB. Si tienes algun problema envianos un mensaje con tu pregunta y te responderemos";
			
			} 
			else {
				
				ClienteDAO cdao = fab.getClienteDAO();
				ListaDAO ldao = fab.getListaDAO();
				Lista l = ldao.buscarPorId(PARTICIPANTES, false);
				String datos = sms.getContenido().getMsg().replaceAll("(?i)lgclub", "");
				Cliente cli = new Cliente();
				cli.setTlf(sms.getPara());
				cli.setListas(new HashSet<Lista>());
				cli.getListas().add(l);
				cli.setNombre(datos);
				cli.setOwner(sms.getOwner());
				cdao.guardar(cli);
				
				part = new Participante();
				part.setTlf(sms.getPara());
				part.setDatos(datos);
				part.setCnx(sms.getCnx());
			}
			part.agregarMensajes(1);
			pdao.guardar(part);
			MensajeDAO mdao = fab.getMensajeDAO();
			MensajeLog mlog = new MensajeLog(sms.getPara(),sms.getContenido().getMsg(),sms.getServicio(),sms.getOwner(),sms.getCnx(),sms.getTipo(),sms.getBlocked());
			mdao.guardar(mlog);
			
			tx.commit();
			
		}catch (RuntimeException rte) {
		
			rte.printStackTrace();
		}
		
		CnxMensaje reply = new CnxMensaje();
		reply.setPara(sms.getPara());
		reply.setCnx(sms.getCnx());
		reply.setReport(false);
		reply.setProgramado(sms.isProgramado());
		
		ContenidoSMS cont = new ContenidoSMS(line);
		cont.setTemp_msg(cont.getMsg());
		reply.setContenido(cont);
		reply.setServicio(m.getServicio());
		reply.setCliente(m.getCliente());
		reply.setOwner(m.getOwner());
		
		mcnx.enviaraCola(reply,(ConexionModemGSM) m.getCnx());
		

	}
	private Cliente agregarCliente(String tlf) {
		Cliente cli = new Cliente();
		cli.setTlf(tlf);
		return cli;
		
	}

}
