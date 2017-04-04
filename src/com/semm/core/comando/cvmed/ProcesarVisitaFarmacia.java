package com.semm.core.comando.cvmed;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.FarmaciaDAO;
import com.semm.core.bd.ProductoDAO;
import com.semm.core.bd.RepresentanteFarmaciaDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.VisitaDAOFarmacia;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.cvmed.Aceptacion;
import com.semm.core.servicios.cvmed.Farmacia;
import com.semm.core.servicios.cvmed.Producto;
import com.semm.core.servicios.cvmed.RepresentanteFarmacia;
import com.semm.core.servicios.cvmed.Visita;
import com.semm.core.servicios.cvmed.VisitaFarmacia;

public class ProcesarVisitaFarmacia extends Comando {
	public static Logger log = Logger.getLogger(ProcesarVisita.class);
	//Pattern regex_visita = Pattern.compile("M\\s*(\\d{3})\\s*((A[J|C|M])?\\s*((\\w{3})\\s(\\d)\\s*)((\\w{3})\\s(\\d)\\s*)?((\\w{3})\\s(\\d)\\s*)?|F\\s*(\\w*))",Pattern.CASE_INSENSITIVE);
	//Pattern regex_visita = Pattern.compile("M\\s*(\\d{4})\\s*((A[J|C|M])?|F\\s*(\\w*))",Pattern.CASE_INSENSITIVE);
	Pattern regex_visita = Pattern.compile("F\\s*(\\d{4})\\s*((A[J|C|M|G|D]))?(F)?\\s*",Pattern.CASE_INSENSITIVE);
	@Override
	public void ejecutar(NuevoMensaje m) {
		String farma,acomp;
		String prods[][] = new String[3][2];
		String falla;
		Matcher match;
		Responder responder = new Responder();
		
		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			match = regex_visita.matcher(sms.getContenido().getMsg());
			
			if(match.find()){
				
				farma = match.group(1);
				acomp = match.group(3);
				// TODO: Se acorto la expresion regular y ahora no contemplafallas
				falla = match.group(4);

				
				responder.setResp(procesar(sms.getPara(),farma,acomp,prods,falla));
				
			
			} else {
				responder.setResp("Disculpe el formato del mensaje es invalido. AYUDA:\n F(Cod. Farmacia) A(Acompañante)");
			}
			
			responder.ejecutar(sms);

		}
		
		
	}
	private String procesar(String rep, String farma, String acomp,String[][] prods, String falla) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String resp = "";
		//Generar una visita vacia
		VisitaFarmacia visit = new VisitaFarmacia();
		visit.setAceptacion(new HashSet<Aceptacion>(3));
		Calendar cal = Calendar.getInstance();
		
		if(cal.get(Calendar.HOUR_OF_DAY) < 6){
			cal.add(Calendar.DATE, -1);
		} 
		
		visit.setFechahora(cal.getTime());
		
		RepresentanteFarmacia r = verificarRep(rep);
		
		if(r!=null)
			visit.setRep(r);
		
		Farmacia m = verificarFarmacia(farma);

		if(m==null)
			return "Disculple el Codigo de la Farmacia es Invalido (" + farma +"). Por favor intente de nuevo.";
		
		visit.setFarmacia(m);
		
		
		int estado = verificarEstado(m,r);
		
		visit.setEstado(estado);
		
		
		
		//Verificar si es fallida o no
		
		if(falla == null) {
			acomp = (acomp!=null) ? acomp.toUpperCase() : "";
			
			//Argrego la aceptacion de cada produtcto a la visita
			/*
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
				
			}*/
		
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
			
			
			
			if(estado == Visita.EXCESO)
				resp = "Disculpe usted ha reportado mas de 2 visitas a la farmacia: " +
						 m.getNombre() + " durante el ciclo. Por favor verifique";
			else if (estado == Visita.EXITOSA_NOPROG)
				resp = "Disculpe usted no tiene registrado la farmacia: " +
				 m.getNombre() + " en su hoja. Por favor verifique";
			else 
				resp = "Visita Exitosa! Farmacia: " + m.getNombre() + "\nEl dia: " + df.format(visit.getFechahora()) + ". Siga adelante!";
			
			
		} else {
			
			if (estado == Visita.EXITOSA_NOPROG){
				visit.setEstado(Visita.FALLIDA_NOPROG);
				resp = "Disculpe usted no tiene registrado la farmacia: " +
				 m.getNombre() + " en su hoja. Por favor verifique";
				
			}else {
			
				visit.setEstado(Visita.FALLIDA);
				resp = "Usted ha reportado una visita fallida en la farmacia: " + m.getNombre() + "\nEl dia: " + df.format(visit.getFechahora()) + ". Siga adelante!";
			}
		}
		
		guardarVisitaFarmacia(visit);
		
		return resp;
		
		

	}
	
	private int verificarEstado(Farmacia m,RepresentanteFarmacia rep) {
		int estado;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VisitaDAOFarmacia vdao = fab.getVisitaDAOFarmacia();

		TxDAO tx = fab.getTx();
		tx.beginTx();
		int programadas = vdao.visitaAutorizada(m, rep, new Date());
			
		if(programadas>0){
			int cantmed = vdao.cantidadPorFarmacia(m, rep, new Date(),VisitaFarmacia.EXITOSA_PROG);
			
			if (cantmed > 2)  
				estado = Visita.EXCESO;
			else {
				VisitaFarmacia v = vdao.ultimaDia(m, rep, new Date(),VisitaFarmacia.EXITOSA_PROG);
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
	
	private Producto verificarProducto(String prod){
		Producto pro = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ProductoDAO mdao = fab.getProductoDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			pro = mdao.buscarPorId(prod, false);
			
		}
		catch (RuntimeException e) {
			// TODO: handle exception
		}
		tx.commit();
		return pro;
	}
	
	
	private void guardarVisitaFarmacia(VisitaFarmacia visit){
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VisitaDAOFarmacia vdao = fab.getVisitaDAOFarmacia();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		vdao.guardar(visit);
		tx.commit();
	}

}
