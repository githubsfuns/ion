package com.semm.core.comando;

import java.util.Map.Entry;

import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.Conexion;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.ExtendedTransData;
import com.semm.core.conexiones.TransData;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.conexiones.contenido.Contenido;
import com.semm.core.servicios.NotificacionSalida;

public class NotificarBloques extends Comando {
	

	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	private NotificacionSalida out;
	private boolean report;
	

	public boolean isReport() {
		return report;
	}


	public void setReport(boolean report) {
		this.report = report;
	}


	@Override
	public void ejecutar(NuevoMensaje m) {
		UserTransData td = (UserTransData)m;
		
		boolean prio = (td instanceof ExtendedTransData);

		CnxMensaje outmsg;
		
		for(Entry<Integer,Conexion> i: out.getRoutes().entrySet()){
			outmsg = new CnxMensaje();
			
			if(prio){
				ExtendedTransData ptd = (ExtendedTransData)td;
			    outmsg.setProgramado((ptd.getTipo() > 1));
				outmsg.setReport(ptd.isReporte());
			} else {
				outmsg.setReport(this.report);
			}
			
			//Buscar recipiente correspondiente a la conexion
			String recip = td.getRecips().get(i.getKey().intValue());
			outmsg.setPara(recip);

			
			Contenido outcont = out.getContenido();
			Contenido pcont = outcont.getContenido();
			
			int line = 1;
			for(Entry<Integer,String> i1: out.getDatareplace().entrySet()){
				String data = td.getData().get(i1.getKey().intValue()*line);
				pcont.replace(i1.getValue(),data);
				pcont.addline();
				line++;
			}
			
			outmsg.setContenido(pcont);
			outmsg.setServicio(td.getServicio());
			outmsg.setCliente(td.getCliente());
			outmsg.setOwner(td.getOwner());
			outmsg.setBlocked(s.getBlocked());
			outmsg.setGrpcnx(s.getSvc().getGrpcnx());
			
			mcnx.enviarRoundRobin(outmsg);
		}
		

	}


	public NotificacionSalida getOut() {
		return out;
	}


	public void setOut(NotificacionSalida out) {
		this.out = out;
	}

}
