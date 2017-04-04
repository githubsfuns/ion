package com.semm.core;

public class Ion {

	
	
	public static void main(String[] args) throws Exception {
		ControlCentral cc = new ControlCentral();
		cc.inicializarSemm();
		Runtime.getRuntime().addShutdownHook(cc);
	}
	
	

}

