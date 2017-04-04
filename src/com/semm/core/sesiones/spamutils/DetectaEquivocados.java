package com.semm.core.sesiones.spamutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.semm.core.conexiones.CnxMensaje;

public class DetectaEquivocados extends InputDetector {
	
	private  Pattern equivoc =  Pattern.compile("\\berrad|\\berror|e[kq]u?i[vb]o[ck]|confund",Pattern.CASE_INSENSITIVE);
	
	protected DetectaEquivocados(InputDetector id) {
		super(id);
		
	}

	
	public void detect(CnxMensaje sms) {
		String content = sms.getContenido().getMsg();

		Matcher matcher = equivoc.matcher(content);
		
		if(matcher.find()) {
			sms.setBlocked(CnxMensaje.EQUIVOCADO);
		} 
		
		if(id!=null)
			id.detect(sms);
		

	}

}
