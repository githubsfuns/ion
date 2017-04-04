package com.semm.core.conexiones.bb;

import com.semm.core.conexiones.CnxMensaje;

public class BlackberryMessage extends CnxMensaje {
	
	private String bbid;
	private int resposeconde;
	private String serviceId,passwd;
	public String getBbid() {
		return bbid;
	}
	public void setBbid(String bbid) {
		this.bbid = bbid;
	}
	public int getResposeconde() {
		return resposeconde;
	}
	public void setResposeconde(int resposeconde) {
		this.resposeconde = resposeconde;
	}
	@Override
	public String getPara() {
		String para = "";
		for(String pin : getRecips())
			para += pin + " - "; 
		return para;
	}
	
	

}
