package com.semm.core.comando.cvmed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.MedicoDAO;
import com.semm.core.bd.RepresentanteDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.VisitaDAO;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.cvmed.Medico;
import com.semm.core.servicios.cvmed.Representante;
import com.semm.core.servicios.cvmed.Visita;

public class Anular extends Comando {
	Pattern regex_anul = Pattern.compile("ANUL\\w*\\s*(\\d{4})",Pattern.CASE_INSENSITIVE);
	
	@Override
	public void ejecutar(NuevoMensaje m) {
		Responder responder = new Responder();
		
		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			Matcher match = regex_anul.matcher(sms.getContenido().getMsg());
			
			if(match.find()){
				Representante r = verificarRep(sms.getPara());
				
				if(r==null){
					AnularFarmacia anulf = new AnularFarmacia();
					anulf.ejecutar(m);
					return;
				} else {
				
					String medico = match.group(1);
					Medico med = verificarMedico(medico);
					
					if(med!=null){	
						responder.setResp(anularVisita(r, med));
	
					}else {
						responder.setResp("Disculple el Codigo del Medico es Invalido (" + medico +"). Por favor intente de nuevo.");
					}
				}
			} else {
				responder.setResp("Disculpe formato invalido. Ayuda para anular una visita: ANULAR (Cod. Medico. 4 Digitos)");
			}
		
			responder.ejecutar(sms);
		}

	}
	
	private Medico verificarMedico(String medico){
		Medico medic = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		MedicoDAO mdao = fab.getMedicoDAO();
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
	
	private Representante verificarRep(String rep) {
		Representante r = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		RepresentanteDAO rdao = fab.getRepresentanteDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			r = rdao.buscarPorId(rep, false);
			r.getTlf();
		} catch (RuntimeException e) {
			r = null;
		}
		tx.commit();
		return r;
	}
	
	private String anularVisita(Representante rep , Medico med){
		String res="";
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VisitaDAO vdao = fab.getVisitaDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {	
			Visita v = vdao.buscarUltimaVisita(rep, med);
			if(v!=null){
				v.setEstado(Visita.ANULADA);
				vdao.guardar(v);
				res = "Visita al Dr. " + v.getMedico().getNombre() + " fue anulada exitosamente! Por favor ahora envie la Visita Correcta";
			} else {
				res = "No existen visitas para el medico: " + med.getNombre() + ". Por favor intente de nuevo";
				
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
