package com.semm.core.sesiones.spamutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.semm.core.conexiones.CnxMensaje;

public class DetectorLenguajeOfensivo extends InputDetector {

	protected DetectorLenguajeOfensivo(InputDetector id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private  Pattern lo =  Pattern.compile("\\bculo\\b|\\bcoño\\b|\\bputa\\b|\\bmojon|guebo|huevon|\\bchupa|mamate|\\bcuca\\b|cabron|pendejo|\\bmaric|\\bmasturba|\\bladilla|mie[rl]da|cagada|verga|pene|carajo|vagina|\\bcono\\b|\\bjode|\\bpaju[ao]",Pattern.CASE_INSENSITIVE);

	@Override
	public void detect(CnxMensaje sms) {
		String content = sms.getContenido().getMsg();

		Matcher matcher = lo.matcher(content);
		
		while(matcher.find()) {
			sms.setBlocked(CnxMensaje.LENGUAJE_OFENSIVO);
			String palabrota = matcher.group();
			
			String replacement = "";
			for(char c :palabrota.toCharArray()){
				replacement += "*";
			}
			 
			sms.getContenido().setMsg(content.replaceAll(palabrota, replacement));
		} 
		
		id.detect(sms);
	}

}
