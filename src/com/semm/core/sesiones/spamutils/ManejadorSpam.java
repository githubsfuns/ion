package com.semm.core.sesiones.spamutils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.semm.core.bd.BloqueadoDAO;
import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.servicios.NumeroBloqueado;
import com.semm.core.sesiones.Sesion;



public  class ManejadorSpam  {
	
	private static ManejadorSpam instance;
	private InputDetector filtros;
	public static Logger log = Logger.getLogger(ManejadorSpam.class);
	
	public static ManejadorSpam getInstancia(){
		if(instance == null){
			instance = new ManejadorSpam();
		}
		return instance;
	}
	
	public ManejadorSpam(){
		filtros = new DetectorLenguajeOfensivo(new DetectaEquivocados(new DetectaUnsolicited(new  DetectorNumPrivados())));
	}
	
			
	public  void detectar(CnxMensaje sms){
		log.debug("Revisando mensaje: " + sms.getContenido().getMsg() + " De:" + sms.getPara());
		filtros.detect(sms);
		if(sms.getBlocked() > CnxMensaje.NORMAL){
			String in = "";
			switch(sms.getBlocked()){
			case 1:
				in = "Lenguaje Hostil";
				break;
			case 2:
				in = "Equivocado";
				break;
			case 3:
				in = "Removido de la lista";
				break;
			}
			log.debug("Detecto: " + in); 
			agregarNumero(sms);
		}
	}
	
	private  void agregarNumero(CnxMensaje sms){
		NumeroBloqueado nb = new NumeroBloqueado();
		nb.setTlf(sms.getPara());
		nb.setUser(sms.getOwner());
		
		
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		BloqueadoDAO bdao = fab.getBloqueadoDAO();
		NumeroBloqueado block;
		tx.beginTx();
		
		try {
			block = bdao.buscarPorIdGet(nb, false);
			if(block!=null){
				block.setStatus(sms.getBlocked());
				block.setFecha(new Date());
			} else {
				block = nb;
				block.setStatus(sms.getBlocked());
				block.setFecha(new Date());
				log.debug("Insertando nuevo bloqueo: " + nb.getTlf() + " - " + nb.getUser().getUsername() + " - " + nb.getStatus());	
			}
		}catch (RuntimeException rte){
			tx.rollback();
			tx.cerrarSesion();
		}	
		bdao.guardar(nb);
		
		tx.commit();
		tx.evict(nb);
	}
	
	public  void verificarBloqueos(Sesion s) {
		NumeroBloqueado nb = new NumeroBloqueado();
		
		nb.setTlf(s.getTlf());
		nb.setUser(s.getOwner());
		
		
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		BloqueadoDAO bdao = fab.getBloqueadoDAO();
		NumeroBloqueado block;
		tx.beginTx();
		
		try {
			block = bdao.buscarPorIdGet(nb, false);
			if(block!=null){		
				s.setBlocked(block.getStatus());
			}
			else{		
				s.setBlocked(0);	
			}
			tx.evict(block);
		}catch (RuntimeException rte){
			tx.rollback();
			tx.cerrarSesion();
		}	
		tx.commit();
		
		log.debug("Detectando numero privado: " + s.getTlf());
		Matcher matcher =  Pattern.compile("^0412808|^04126088542",Pattern.CASE_INSENSITIVE).matcher(s.getTlf());
		
		
		if(matcher.find()){
			log.debug("NUMERO PRIVADO: " + s.getTlf());
			s.setBlocked(CnxMensaje.SPAM);
		}
		
	}
	
}
