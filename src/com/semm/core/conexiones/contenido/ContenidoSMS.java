package com.semm.core.conexiones.contenido;

public class ContenidoSMS extends Contenido {
		

		public ContenidoSMS(String msg) {
			super(msg);
		}

		@Override
		public void replace(String regex,String data) {
			setMsg(getMsg().replaceAll(regex,data));
		}

		@Override
		public Contenido getContenido() {
			return new ContenidoSMS(getMsg());
		}

		@Override
		public void addline() {
			int beg = getMsg().indexOf("[");
			int end = getMsg().indexOf("]");
			String line = getMsg().substring(beg, ++end);
			setMsg(getMsg()+line);
		}



}
