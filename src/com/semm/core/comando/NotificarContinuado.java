package com.semm.core.comando;

import org.apache.log4j.Logger;

import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.NuevoMensaje;

public class NotificarContinuado extends Comando {
	

	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	public static Logger log = Logger.getLogger(NotificarContinuado.class);
 
	public NotificarContinuado() {

	}


	@Override
	public void ejecutar(NuevoMensaje m) {
		
		
		String pagina=	(String)s.getAttribs().get("pag");
		log.debug("Buscando Pagina: " + pagina);
		CnxMensaje cnxsms = (CnxMensaje)s.getAttribs().get(pagina);
		log.debug("Pagina " + pagina + " Msg:" + cnxsms.getContenido().getMsg());

		mcnx.enviaraCola(cnxsms, m.getCnx());
		log.debug("Borrando pagina: " + pagina);
		s.getAttribs().remove(pagina);
		
		int next = Integer.parseInt(pagina) + 1;
		log.debug("Proxima Pagina: " + next);
		s.getAttribs().put("pag", next);

	}





}
