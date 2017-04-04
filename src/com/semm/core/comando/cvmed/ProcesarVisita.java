package com.semm.core.comando.cvmed;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.MedicoDAO;
import com.semm.core.bd.ProductoDAO;
import com.semm.core.bd.RepresentanteDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.VisitaDAO;
import com.semm.core.bd.trivia.ParticipanteDAO;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ConexionModemGSM;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.ManejadorServicios;
import com.semm.core.servicios.cvmed.Aceptacion;
import com.semm.core.servicios.cvmed.Medico;
import com.semm.core.servicios.cvmed.Producto;
import com.semm.core.servicios.cvmed.Representante;
import com.semm.core.servicios.cvmed.Visita;
import com.semm.core.servicios.trivia.Participante;

public class ProcesarVisita extends Comando {
	public static Logger log = Logger.getLogger(ProcesarVisita.class);
	
	//Pattern regex_visita = Pattern.compile("M\\s*(\\d{3})\\s*((A[J|C|M])?\\s*((\\w{3})\\s(\\d)\\s*)((\\w{3})\\s(\\d)\\s*)?((\\w{3})\\s(\\d)\\s*)?|F\\s*(\\w*))",Pattern.CASE_INSENSITIVE);
	//Pattern regex_visita = Pattern.compile("M\\s*(\\d{4})\\s*((A[J|C|M])?|F\\s*(\\w*))",Pattern.CASE_INSENSITIVE);
	Pattern regex_visita = Pattern.compile("M\\s*(\\d{4})\\s*((A[J|C|M|G|D]))?(F)?\\s*",Pattern.CASE_INSENSITIVE);
	@Override
	public void ejecutar(NuevoMensaje m) {
		String medico,acomp;
		String prods[][] = new String[3][2];
		String falla;
		Matcher match;
		Responder responder = new Responder();
		
		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			match = regex_visita.matcher(sms.getContenido().getMsg());
			
			if(match.find()){
				
				medico = match.group(1);
				acomp = match.group(3);
				// TODO: Se acorto la expresion regular y ahora no contemplafallas
				falla = match.group(4);
				log.debug("Formato de Visita OK ... Procesando Visita");
				
				responder.setResp(procesar(sms.getPara(),medico,acomp,prods,falla));
				log.debug("Visita Done");
			
			} else {
				responder.setResp("Disculpe el formato del mensaje es invalido. AYUDA:\n M(Cod. Medico) A(Acompañante)");
			}
			responder.setReporte(true);
			responder.ejecutar(sms);

		}
		
		
	}
	private String procesar(String rep, String medico, String acomp,String[][] prods, String falla) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		String resp = "";
		//Generar una visita vacia
		Visita visit = new Visita();
		visit.setAceptacion(new HashSet<Aceptacion>(3));
		
		Calendar cal = Calendar.getInstance();
		
		if(cal.get(Calendar.HOUR_OF_DAY) < 6){
			cal.add(Calendar.DATE, -1);
		} 
		
		visit.setFechahora(cal.getTime());
		
		log.debug("Verficando Representante");
		Representante r = verificarRep(rep);
		log.debug("OK");
		if(r!=null)
			visit.setRep(r);
		log.debug("Verficando Medico");
		Medico m = verificarMedico(medico);
		log.debug("OK");
		if(m==null)
			return "Disculple el Codigo del Medico es Invalido (" + medico +"). Por favor intente de nuevo.";
		
		visit.setMedico(m);
		
		log.debug("Verficando Estado");
		int estado = verificarEstado(m,r);
		log.debug("OK");
		visit.setEstado(estado);
		
		
		
		//Verificar si es fallida o no
		
		if(falla == null) {
			log.debug("Visita no fallida");
			acomp = (acomp!=null) ? acomp.toUpperCase() : "";
			/*
			//Argrego la aceptacion de cada produtcto a la visita
			for(int i = 0; i < prods.length; i++){
				if (prods[i][0]==null) continue;
				Producto prod = verificarProducto(prods[i][0].toUpperCase());
				
				if(prod !=null && prods[i][1]!=null){
					int na = Integer.parseInt(prods[i][1]);
					Aceptacion acept = new Aceptacion(visit,prod,na);
					visit.getAceptacion().add(acept);
				} else {
					return "Disculpe el Codigo del Producto  (" + prods[i][0].toUpperCase() + ") no existe. Por favor intente de nuevo";
				}
				
			}
		*/
			if(acomp.equals("AC"))
				visit.setAcomp(Visita.AC);
			else if (acomp.equals("AJ"))
				visit.setAcomp(Visita.AJ);
			else if (acomp.equals("AM"))
				visit.setAcomp(Visita.AM);
			else if (acomp.equals("AD"))
				visit.setAcomp(Visita.ADC);
			else if (acomp.equals("AG"))
				visit.setAcomp(Visita.AGV);
			
			log.debug("Calculando respuesta por estado...");
			
			if(estado == Visita.EXCESO)
				resp = "Disculpe usted ha reportado mas de 2 visitas al doctor " +
						 m.getNombre() + " durante el ciclo. Por favor verifique";
			else if (estado == Visita.EXITOSA_NOPROG)
				resp = "Disculpe usted no tiene registrado al doctor " +
				 m.getNombre() + " en su hoja medica. Por favor verifique";
			else 
				resp = "Visita Exitosa! Doctor: " + m.getNombre() + " (" + m.getCodigo() +")\nEl dia: " + df.format(visit.getFechahora()) + ". Siga adelante!";
			
			
		} else {
			
			if (estado == Visita.EXITOSA_NOPROG){
				visit.setEstado(Visita.FALLIDA_NOPROG);
				resp = "Disculpe usted no tiene registrado al doctor " +
				 m.getNombre() + " en su hoja medica. Por favor verifique";
				
			}else {
			
				visit.setEstado(Visita.FALLIDA);
				resp = "Usted ha reportado una visita fallida con el doctor: " + m.getNombre() + " (" + m.getCodigo() +")\nEl dia: " + df.format(visit.getFechahora()) + ". Siga adelante!";
			}
		}
		log.debug("Guardando Visita en BD");
		guardarVisita(visit);
		log.debug("Guardada en BD");
		return resp;
		
		

	}
	
	private int verificarEstado(Medico m,Representante rep) {
		int estado;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VisitaDAO vdao = fab.getVisitaDAO();
		
		TxDAO tx = fab.getTx();
		tx.beginTx();
		int programadas = vdao.visitaAutorizada(m, rep, new Date());

		if(programadas>0){
			long cantmed =  vdao.cantidadPorMedico(m, rep, new Date(),Visita.EXITOSA_PROG);
			
			if (cantmed > 2)  
				estado = Visita.EXCESO;
			else {
				Visita v = vdao.ultimaDia(m, rep, new Date(),Visita.EXITOSA_PROG);
				if(v!=null){
					v.setEstado(Visita.ACTUALIZADA);
					vdao.guardar(v);
				}
				estado = Visita.EXITOSA_PROG; 
			}
			
		}else 
			estado = Visita.EXITOSA_NOPROG;
		
		tx.commit();

		return estado;
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
	
	private Representante verificarRep(String rep){
		Representante r = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		RepresentanteDAO rdao = fab.getRepresentanteDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			r = rdao.buscarPorIdGet(rep, false);
			
		}
		catch (RuntimeException e) {
			// TODO: handle exception
		}
		tx.commit();
		return r;
	}
	
	private Producto verificarProducto(String prod){
		Producto pro = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ProductoDAO mdao = fab.getProductoDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			pro = mdao.buscarPorIdGet(prod, false);
			
		}
		catch (RuntimeException e) {
			// TODO: handle exception
		}
		tx.commit();
		return pro;
	}
	
	
	private void guardarVisita(Visita visit){
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VisitaDAO vdao = fab.getVisitaDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		log.debug("Empezando a Guardar Visita");
		vdao.guardar(visit);
		tx.commit();
		tx.evict(visit);
	}

}
