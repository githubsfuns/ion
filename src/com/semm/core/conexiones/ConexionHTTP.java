package com.semm.core.conexiones;

import java.io.IOException;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class ConexionHTTP extends Conexion {
	
	private HttpClient http = new HttpClient();
	private String url;
	private String proxyHost;
	private int proxyPort;

	@Override
	public void agregarMsgCola(NuevoMensaje m) throws ConexionMuertaException,
			ConexionFullException {
		// TODO Auto-generated method stub

	}

	@Override
	public void conectar() throws Exception {
		if(proxyHost!=null && proxyHost.length()>0){
			HostConfiguration hconf=  http.getHostConfiguration();
			hconf.setProxy(proxyHost, proxyPort);
		}
	}

	@Override
	public void desconectar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviar(NuevoMensaje m) {
		// TODO Auto-generated method stub
		UserTransData tdmsg= (UserTransData)m;
		PostMethod method = new PostMethod(url);
		method.addParameter("recips",tdmsg.getRecips().get(0));
		String dataarray = "";
		for(String data : tdmsg.getData()){
			System.err.println(data);
			log.debug("DATA: " + data);
			dataarray += data + "|";
		}
		dataarray = (dataarray.length()>0) ? dataarray.substring(0, dataarray.length()-1) : "";
		log.debug("DATA ARRAY: " + dataarray);
		System.err.println(dataarray);
		method.addParameter("data", dataarray);
		method.addParameter("username",tdmsg.getCliente());
		method.addParameter("passwd",tdmsg.getPasswd());
		method.addParameter("svc", tdmsg.getServicio());
		method.addParameter("method","enviartd");
		
		try {
			log.debug("Enviando mensaje: " + method.getURI());
			int respose = http.executeMethod(method);
			log.debug("Respuesta HTTP: " + respose);
			log.debug("Mensaje de vuelta: " + method.getResponseBodyAsString());
			method.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

	}

	@Override
	public void recargar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recuperar() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean verificarFalla() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

}
