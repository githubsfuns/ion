package com.semm.core.comando.cvmed;

import java.util.Date;

import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.NuevoMensaje;

public class Ayuda extends Comando {

	@Override
	public void ejecutar(NuevoMensaje m) {
		Responder responder = new Responder();
		responder.setResp("AYUDA: Debes enviar: M(Cod. Medico 4 Digitos) A(Acompañante), para visitas fallidas: M(Cod. Medico 4 Digitos) F");
		responder.ejecutar(m);
	}

}
