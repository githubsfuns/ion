package com.semm.core.sesiones.spamutils;

import com.semm.core.conexiones.CnxMensaje;

public abstract class InputDetector {
	protected InputDetector id;
	
	protected InputDetector(){
		
	}
	
	protected InputDetector(InputDetector id){
		this.id = id;
	}
	
	public abstract void detect(CnxMensaje sms);
	
}
