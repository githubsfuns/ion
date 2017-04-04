package com.semm.core.sesiones;

import org.apache.log4j.Logger;

import com.semm.core.bd.ManejadorBd;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Transicion;
import com.semm.core.comando.TransicionReporte;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.ReporteEntrega;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.servicios.MensajePorProcesar;
import com.semm.core.sesiones.spamutils.ManejadorSpam;

public class SesionActivaVisitor implements SesionVisitor {

	
	private Sesion s;
	private Logger log;
	
	
	public void visitar(CnxMensaje sms) {
		sms.setCliente(s.getCliente());
		sms.setOwner(s.getOwner());
		ManejadorSpam.getInstancia().detectar(sms);
		s.setBlocked(sms.getBlocked());
		this.log.debug("Guardando mensaje..." + sms.getServicio());
		this.s.getTrans().guardarMensaje(sms);
		this.log.debug("Guardado ");
		this.log.debug("Buscando siguiente comando");
		Comando cmd = s.getTrans().getNxtCmd(sms);
		ejecutar(cmd,sms);
	}



	public void visitar(UserTransData utd) {
		if(utd.getCliente().compareTo(this.s.getCliente()) == 0 && s.getSvc().getNombre().equals(utd.getServicio())){

			utd.setOwner(s.getOwner());
			
			if((s.getLastData()!= null) && 
					(s.getLastData().size() == 0 || s.getLastData().containsAll(utd.getData())))
					return;

			log.debug("Volviendo al root Cmd del SVC: " + utd.getServicio());
			s.setLastData(utd.getData());
			ManejadorBd mbd = ManejadorBd.getInstancia();
			MensajePorProcesar generador = mbd.guardarMensaje(utd);
			s.setGenerador(generador);
			
			Comando cmd = s.getSvc().getRootcmd();
			ejecutar(cmd,utd);
			
		} else {
			FabricaSesiones fses = FabricaSesiones.getFabrica(FabricaSesiones.SVC);
			Sesion s2 = fses.getSesion(utd,s.getTlf());
			s.clone(s2);
			s.procesar(utd);	
		}
	}
	
	private void ejecutar(Comando cmd,NuevoMensaje m){
		
		if(cmd!=null){
			cmd.setS(s);
			log.debug("Ejecutando comando de transicion...");
			cmd.ejecutar(m);
			log.debug("Done Transicion..." + cmd);
			Transicion next = cmd.getTransicion();
			log.debug("Transicion nueva: " + next);
			if(next!=null){
				s.setTrans(next);
				
			} else {
				s.setState(new Finalizada());
				
			}
		}else {
			log.debug("NO hay mas comandos de transicion...");
			s.setState(new Finalizada());
		}
		
	}



	public void setLog(Logger log) {
		this.log = log;
	}

	public void setS(Sesion s) {
		this.s = s;
	}



	public void visitar(ReporteEntrega rep) {
		this.log.debug("Buscando siguiente comando por Reporte Entrante");
		if(s.getTrans() instanceof TransicionReporte){
			Comando cmd = s.getTrans().getNxtCmd(rep);
			ejecutar(cmd,rep);
		}else 
			this.log.debug("Trasicion de Regex... Do Nothing");
		
	}

}
