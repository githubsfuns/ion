package com.semm.core.sesiones.spamutils;


import java.util.regex.Pattern;

import com.semm.core.conexiones.CnxMensaje;

public class DetectaUnsolicited extends InputDetector {

public DetectaUnsolicited() { super(); }

	protected DetectaUnsolicited(InputDetector id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private  Pattern dont = Pattern.compile("(\\bNo\\b|D[eé]j[ae]).*(en[vb]i|mand|recibir).*",Pattern.CASE_INSENSITIVE);
	private Pattern out = Pattern.compile("(sa[ckq][aeu]|remov|elim).*(lista|sistema)?",Pattern.CASE_INSENSITIVE);
	private  Pattern autorizo = Pattern.compile("\\babus|spam",Pattern.CASE_INSENSITIVE);

	@Override
	public void detect(CnxMensaje sms) {
		String content = sms.getContenido().getMsg();
		if(dont.matcher(content).find() || out.matcher(content).find() || autorizo.matcher(content).find() ) {
			sms.setBlocked(CnxMensaje.SPAM);
		}
		
		if(id!=null)
			id.detect(sms);
	}

}
