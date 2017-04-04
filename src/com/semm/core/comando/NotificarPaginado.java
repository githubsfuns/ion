package com.semm.core.comando;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.MensajeDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.Conexion;
import com.semm.core.conexiones.GrupoConexiones;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.ExtendedTransData;
import com.semm.core.conexiones.TransData;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.conexiones.contenido.Contenido;
import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.NotificacionSalida;

public class NotificarPaginado extends Comando {
	
	private static int maxlength = 160;
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	private NotificacionSalida out;
	private GrupoConexiones segundo_grupo;
	private boolean report;

	public static Logger log = Logger.getLogger(NotificarPaginado.class);

	public NotificarPaginado() {
		
	}


	public boolean isReport() {
		return report;
	}


	public void setReport(boolean report) {
		this.report = report;
	}


	@Override
	public void ejecutar(NuevoMensaje m) {
		UserTransData td = (UserTransData)m;
		
		try {
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
			
			
			for(Entry<Integer,String> i1: out.getDatareplace().entrySet()){
				String data = td.getData().get(i1.getKey().intValue());
				pcont.replace(i1.getValue(),data);
			}
			
			
			outmsg.setContenido(pcont);
			outmsg.setServicio(td.getServicio());
			outmsg.setCliente(td.getCliente());
			outmsg.setOwner(td.getOwner());
			outmsg.setBlocked(s.getBlocked());
			outmsg.setGrpcnx(s.getSvc().getGrpcnx());
			
			int paginas = paginas(outmsg);
			
			enviarPrimeraPagina(paginas);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private void enviarPrimeraPagina(int paginas) {
		CnxMensaje cnxmsg = (CnxMensaje)s.getAttribs().get("1");
		log.debug("Enviando Primera Pagina: " + cnxmsg.getPara() + " MSG: " + cnxmsg.getContenido().getMsg());
		mcnx.enviarRoundRobin(cnxmsg);
		
		log.debug("Borrando de Sesion Primera Pagina");
		s.getAttribs().remove("1");
		
		if(paginas > 1) {
			log.debug("Colocando Siguiente pagina 2");
			s.getAttribs().put("pag","2");
		}
	}


	public int paginas(CnxMensaje m){
		if(m.getContenido().getMsg().length() > maxlength){
			ArrayList<String> parts = new ArrayList<String>(2);
			splitMsg(parts, m.getContenido().getMsg());
			int i = 1;
			
			for(String part : parts){
				ContenidoSMS cont = new ContenidoSMS(part);
				CnxMensaje mpart = new CnxMensaje(m.getPara(),m.getDe(),m.getCnx(),cont);
				mpart.setCliente(m.getCliente());
				mpart.setServicio(m.getServicio());
				mpart.setOwner(m.getOwner());
				mpart.setBlocked(m.getBlocked());
				mpart.setReport(true);
				mpart.setProgramado(m.isProgramado());
				mpart.setGrpcnx(m.getGrpcnx());
				s.getAttribs().put(Integer.toString(i++), mpart);
				
			}
			return parts.size();
		}
		return 1;
	
	}
	private void splitMsg(ArrayList<String> l,String msg) {
		int size = maxlength;
		
		if(msg.length()>size){
			String shorthead = "";
			
			String head = msg.substring(0,size);
			String tail = msg.substring(size);
			int lispc = head.lastIndexOf(' ');
			int ispc = tail.indexOf(' ');
			
			if(lispc == (size-1) || ispc == 0){
				shorthead = head.trim();
				tail = tail.trim();
			} else if (lispc < (size-1) && (ispc > 0) || (ispc == -1)){
				shorthead = head.substring(0,lispc);
				tail = head.substring(lispc+1) + tail;
				
			} 

			l.add(shorthead);
			splitMsg(l, tail);
			
		} else {
			l.add(msg);
		}
		
	
	}

	public NotificacionSalida getOut() {
		return out;
	}


	public void setOut(NotificacionSalida out) {
		this.out = out;
	}


	public GrupoConexiones getSegundo_grupo() {
		return segundo_grupo;
	}


	public void setSegundo_grupo(GrupoConexiones segundo_grupo) {
		this.segundo_grupo = segundo_grupo;
	}

}
