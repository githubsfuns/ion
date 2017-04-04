package com.semm.core.comando.cvmed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.FarmaciaDAO;
import com.semm.core.bd.MedicoDAO;
import com.semm.core.bd.RepresentanteDAO;
import com.semm.core.bd.RepresentanteFarmaciaDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.VisitaDAO;
import com.semm.core.bd.VisitaDAOFarmacia;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.cvmed.Farmacia;
import com.semm.core.servicios.cvmed.Medico;
import com.semm.core.servicios.cvmed.Representante;
import com.semm.core.servicios.cvmed.RepresentanteFarmacia;
import com.semm.core.servicios.cvmed.Visita;
import com.semm.core.servicios.cvmed.VisitaFarmacia;

public class AnularFarmacia extends Comando {
	Pattern regex_anul = Pattern.compile("ANUL\\s*(\\d{4})",Pattern.CASE_INSENSITIVE);
	
	@Override
	public void ejecutar(NuevoMensaje m) {
		Responder responder = new Responder();
		
		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			Matcher match = regex_anul.matcher(sms.getContenido().getMsg());
			
			if(match.find()){
				RepresentanteFarmacia r = verificarRep(sms.getPara());
				
				String medico = match.group(1);
				Farmacia med = verificarFarmacia(medico);
				
				if(med!=null){	
					responder.setResp(anularVisita(r, med));

				}else {
					responder.setResp("Disculple el Codigo de la Farmacia es Invalido (" + medico +"). Por favor intente de nuevo.");
				}
			} else {
				responder.setResp("Disculpe formato invalido. Ayuda para anular una visita: ANULAR (Cod. Farmacia. 4 Digitos)");
			}
		
			responder.ejecutar(sms);
		}

	}
	
	private Farmacia verificarFarmacia(String medico){
		Farmacia medic = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		FarmaciaDAO mdao = fab.getFarmaciaDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			
			medic = mdao.buscarPorId(Integer.parseInt(medico), false);
			medic.getNombre(); 
		}
		catch (RuntimeException e) {
			medic = null;
			
		}
		tx.commit();
		return medic;
	}
	
	private RepresentanteFarmacia verificarRep(String rep){
		RepresentanteFarmacia r = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		RepresentanteFarmaciaDAO rdao = fab.getRepresentanteFarmaciaDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			r = rdao.buscarPorId(rep, false);
			
		}
		catch (RuntimeException e) {
			// TODO: handle exception
		}
		tx.commit();
		return r;
	}
	
	private String anularVisita(RepresentanteFarmacia rep , Farmacia med){
		String res="";
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VisitaDAOFarmacia vdao = fab.getVisitaDAOFarmacia();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {	
			VisitaFarmacia v = vdao.buscarUltimaVisita(rep, med);
			if(v!=null){
				v.setEstado(VisitaFarmacia.ANULADA);
				vdao.guardar(v);
				res = "Visita a la farmacia: " + v.getFarmacia().getNombre() + " fue anulada exitosamente! Por favor ahora envie la Visita Correcta";
			} else {
				res = "No existen visitas para la farmacia: " + med.getNombre() + ". Por favor intente de nuevo";
				
			}
			
		}
		catch (RuntimeException e) {
			e.printStackTrace();
			res = "No existen visitas para el medico: " + med.getNombre() + ". Por favor intente de nuevo";
		
		}
		
		tx.commit();
		return res;
	}

}
