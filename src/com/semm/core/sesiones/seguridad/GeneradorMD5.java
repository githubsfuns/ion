package com.semm.core.sesiones.seguridad;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeneradorMD5 {

	public String generarMD5(String pwd){
		
		MessageDigest mdAlgorithm;
		StringBuffer hexString= new StringBuffer();
		
		try {
			mdAlgorithm = MessageDigest.getInstance("MD5");
			mdAlgorithm.update(pwd.getBytes());
	
			byte[] digest = mdAlgorithm.digest();
	
			String passwd;
	
			for (int i = 0; i < digest.length; i++) {
			    passwd = Integer.toHexString(0xFF & digest[i]);	
			    if (passwd.length() < 2) {
			    	passwd = "0" + passwd;
			    }
			    hexString.append(passwd);
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}

		return hexString.toString();

	}
	
}
