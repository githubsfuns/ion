package com.semm.core.sesiones.spamutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.semm.core.conexiones.CnxMensaje;

public class DetectorNumPrivados extends InputDetector {
	
	public DetectorNumPrivados() { super(); };
	
	protected DetectorNumPrivados(InputDetector id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private  Pattern lo =  Pattern.compile("^0412808|^04126088542",Pattern.CASE_INSENSITIVE);

	@Override
	public void detect(CnxMensaje sms) {
		String para = sms.getPara();

		Matcher matcher = lo.matcher(para);
		
		if(matcher.find()) {
			sms.setBlocked(CnxMensaje.EQUIVOCADO);
		} 
		if(id!=null)
		id.detect(sms);
	}
}
