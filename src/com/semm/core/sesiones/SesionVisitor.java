package com.semm.core.sesiones;

import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.ReporteEntrega;
import com.semm.core.conexiones.UserTransData;

public interface SesionVisitor {

	public void visitar(CnxMensaje sms);
	public void visitar(UserTransData utd);
	public void visitar(ReporteEntrega rep);

}
