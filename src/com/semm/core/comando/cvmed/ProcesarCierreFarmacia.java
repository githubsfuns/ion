package com.semm.core.comando.cvmed;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.RepresentanteDAO;
import com.semm.core.bd.RepresentanteFarmaciaDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.VisitaDAO;
import com.semm.core.bd.VisitaDAOFarmacia;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Notificar;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.cvmed.Representante;
import com.semm.core.servicios.cvmed.RepresentanteFarmacia;
import com.semm.core.servicios.cvmed.Visita;
import com.semm.core.servicios.cvmed.VisitaFarmacia;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class ProcesarCierreFarmacia extends Comando {

	
	@Override
	public void ejecutar(NuevoMensaje m) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

		Responder responder = new Responder();

		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;

			RepresentanteFarmacia r = verificarRep(sms.getPara());
			if(r==null){
				responder.setResp("Disculpe el numero telefonico es no esta autorizado para realizar visitas. Por favor intente de nuevo con otro telefono.");
			} else {
				
				Calendar cal = Calendar.getInstance();
				if(cal.get(Calendar.HOUR_OF_DAY) < 6){
					cal.add(Calendar.DATE, -1);
				} 
				String resp = verificarVisitas(r,cal);
				responder.setResp("Cierre del dia: " + sdf.format(cal.getTime())
						+ "\n  Has completado  en este ciclo:\n" + resp
						+ " Sigue adelante!");
				responder.ejecutar(sms);
	
				String msg = "Cierre de " + r.getNombre() + " dia: "
						+ sdf.format(cal.getTime()) + "\n Resultados hasta ahora: \n"
						+ resp;
	
				enviarGpt(r, sms, msg);
			}

		}

	}

	private void enviarGpt(RepresentanteFarmacia r, CnxMensaje sms, String msg) {
		if (r.getGpt() != null) {
			ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
			CnxMensaje outmsg = new CnxMensaje();
			ContenidoSMS cont = new ContenidoSMS(msg);
			outmsg.setPara(r.getGpt().getTlf());
			outmsg.setContenido(cont);
			outmsg.setServicio(sms.getServicio());
			outmsg.setCliente(sms.getCliente());
			outmsg.setOwner(sms.getOwner());
			outmsg.setBlocked(s.getBlocked());
			outmsg.setGrpcnx(s.getSvc().getGrpcnx());
			mcnx.enviarRoundRobin(outmsg);
		}
	}

	private RepresentanteFarmacia verificarRep(String rep) {
		RepresentanteFarmacia r = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		RepresentanteFarmaciaDAO rdao = fab.getRepresentanteFarmaciaDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			r = rdao.buscarPorId(rep, false);
			r.getNombre();
		} catch (RuntimeException e) {
			r = null;
		}
		tx.commit();
		return r;
	}

	private String verificarVisitas(RepresentanteFarmacia rep, Calendar cal) {
		String r = "";
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VisitaDAOFarmacia vdao = fab.getVisitaDAOFarmacia();
		TxDAO tx = fab.getTx();
		tx.beginTx();

		List<Object[]> prorep = vdao.vistasProgramadasEsp(rep);
		HashMap<String, Integer[]> hmres = cargarHash(prorep);
		
		List<Object[]> resrep = vdao.vistasRelizadasEsp(rep, VisitaFarmacia.EXITOSA_PROG, cal.getTime());

		System.out.println("Listas PROG: " + prorep.size() + " RES: "
				+ resrep.size());
		try {
			for (Object[] res : resrep) {
				if (hmres.containsKey(res[0])) {
					System.out
							.println("Hash Hecho: " + res[0] + " - " + res[1]);
					Integer[] values = hmres.get(res[0]);
					values[1] = (Integer) res[1];
				}
				//r += res[0] + " " + res[1] + "/ N \n";
			}

			for (String s : hmres.keySet()) {
				Integer[] vals = hmres.get(s);
				System.out.println("Hash Programado: " + vals[1] + " - "
						+ vals[0]);
				r += s + " " + vals[1] + "/" + vals[0] + " ";
			}

		} catch (RuntimeException e) {
			r = "Error en la aplicacion: No se pueden calcular las visitas.";
		}
		tx.commit();
		return r;
	}

	private HashMap<String, Integer[]> cargarHash(List<Object[]> resrep) {
		HashMap<String, Integer[]> hmres = new HashMap<String, Integer[]>(
				resrep.size());
		for (Object[] res : resrep) {
			System.out.println("Hash Programado: " + res[0].toString() + " - "
					+ res[1]);
			hmres.put(res[0].toString(), new Integer[] { (Integer) res[1], 0 });
		}
		return hmres;
	}

}
