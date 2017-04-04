package com.semm.core.comando.trivia;

import java.util.Hashtable;
import java.util.regex.Pattern;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.trivia.ParticipanteDAO;
import com.semm.core.bd.trivia.PreguntaDAO;
import com.semm.core.comando.Comando;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ConexionModemGSM;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.trivia.Participante;
import com.semm.core.servicios.trivia.Pregunta;


public class ProcesarRespuesta extends Comando{

	Hashtable<String, Integer> respuestas = new Hashtable<String, Integer>();
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	
	public ProcesarRespuesta() {
		respuestas.put(".*\\s*[1|a|A]\\s*.*",0);
		respuestas.put(".*\\s*[2|b|B]\\s*.*",1);
		respuestas.put(".*\\s*[3|c|C]\\s*.*",2);
	}



	@Override
	public void ejecutar(NuevoMensaje m) {
		
		CnxMensaje sms = (CnxMensaje)m;
		ContenidoSMS cont2 = null;
		Pregunta preg = buscarPregunta();
		Participante part = getParticipante(sms.getPara());
		
		if(part==null)
			return;

		if(verificarRespuesta(sms, preg)){
			System.out.println("Respuesta Correcta: " + preg.getPuntos());
			part.setPuntos(part.getPuntos() + preg.getPuntos());
			part.setCorrectas(part.getCorrectas() + 1);
			cont2 = new ContenidoSMS("Correcto! Ganaste " + preg.getPuntos()  + " puntos. Llevas acumulado " + part.getPuntos() + " sigue respondiendo las proximas preguntas para ganar muchos premios!");
			cont2.setTemp_msg(cont2.getMsg());
			
		}else {
			cont2 = new ContenidoSMS("Tu respuesta no es correcta, vuelve a intentarlo para que acumules mas puntos!");
			cont2.setTemp_msg(cont2.getMsg());
			part.setMalas(part.getMalas()+1);
	
		}
		
		part.agregarMensajes(1);
		
		actualizar(part);
		
		CnxMensaje reply2 = new CnxMensaje();
		reply2.setPara(sms.getPara());
		reply2.setCnx(sms.getCnx());
		reply2.setReport(false);
		reply2.setProgramado(sms.isProgramado());
		reply2.setContenido(cont2);
		reply2.setServicio(m.getServicio());
		reply2.setCliente(m.getCliente());
		reply2.setOwner(m.getOwner());
		
		
		mcnx.enviaraCola(reply2,m.getCnx());
		
	}

	private Pregunta buscarPregunta() {

		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		PreguntaDAO pdao = fab.getPreguntaDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		
		String id_preg = (String)this.s.getAttribs().get("id_pregunta");
		Pregunta pregunta = pdao.buscarPorId(Long.parseLong(id_preg), true);

		tx.commit();
		return pregunta;
	}



	private boolean verificarRespuesta(CnxMensaje m,Pregunta preg) {	
		String resp = m.getContenido().getMsg();
		for(String key:respuestas.keySet()){
			if(Pattern.matches(key, resp))
				return (preg.getCorrecta() == respuestas.get(key).intValue());
		}
		return false;
	}
	
	private void actualizar(Participante part){
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ParticipanteDAO pdao = fab.getParticipanteDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
			pdao.guardar(part);
		tx.commit();
	}
	
	private Participante getParticipante(String tlf){
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ParticipanteDAO pdao = fab.getParticipanteDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		Participante part =  pdao.buscarPorId(tlf, true);
		try {
			part.getTlf();
		}catch (RuntimeException e) {
			part = null;
		}
		tx.commit();
		return part;
	}
		
		

}
