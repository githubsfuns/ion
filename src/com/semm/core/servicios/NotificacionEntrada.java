package com.semm.core.servicios;

import java.util.HashMap;

import com.semm.core.comando.Comando;

public class NotificacionEntrada {
	private HashMap <String,Comando> transitions;
	private Comando def;

	public Comando getDef() {
		return def;
	}

	public void setDef(Comando def) {
		this.def = def;
	}

	public HashMap<String, Comando> getTransitions() {
		return transitions;
	}

	public void setTransitions(HashMap<String, Comando> transitions) {
		this.transitions = transitions;
	}
	
	
}
