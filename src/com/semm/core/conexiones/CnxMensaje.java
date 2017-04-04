package com.semm.core.conexiones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.semm.core.conexiones.contenido.Contenido;
import com.semm.core.servicios.MensajeLog;
import com.semm.core.servicios.MensajePorProcesar;
import com.semm.core.sesiones.SesionVisitor;

public class CnxMensaje extends NuevoMensaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2963755592387271682L;
	public static final int NORMAL = 0,EQUIVOCADO = 2, LENGUAJE_OFENSIVO = 1, SPAM = 3,POR_ENVIAR = 3, REMOVER = 4,APAGANDO=5,FORMATO_INVALIDO=6;
	private String de,reply_dest;
	private Contenido contenido;
	private int tipo,subtipo,blocked,reply_req,retries,partes=1;
	private boolean programado,report,multipart;
	private Date enviado;
	private GrupoConexiones grpcnx;
	private MensajePorProcesar mpp;
	
	



	public MensajePorProcesar getMpp() {
		return mpp;
	}

	public void setMpp(MensajePorProcesar mpp) {
		this.mpp = mpp;
	}

	public GrupoConexiones getGrpcnx() {
		return grpcnx;
	}

	public void setGrpcnx(GrupoConexiones grpcnx) {
		this.grpcnx = grpcnx;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public CnxMensaje(){
		this.recips = new ArrayList<String>(1);
	}
	
	/**
	 * @param cnx
	 * @param para
	 * @param de
	 * @param contenido
	 */
	public CnxMensaje(String para, String de,Conexion cnx,Contenido contenido) {
		// TODO Auto-generated constructor stub
		this.cnx = cnx;
		this.recips = new ArrayList<String>(1);
		this.recips.add(0,para);
		this.de = de;
		this.contenido = contenido;
	}
	public Conexion getCnx() {
		return cnx;
	}
	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}
	public Contenido getContenido() {
		return contenido;
	}
	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getPara() {
		return this.recips.get(0);
	}
	public void setPara(String para) {
		this.recips.add(0,para);
	}

	public boolean isProgramado() {
		return programado;
	}

	public void setProgramado(boolean programado) {
		this.programado = programado;
	}

	@Override
	public void accept(SesionVisitor visita) {
		visita.visitar(this);
	}

	public int getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(int subtipo) {
		this.subtipo = subtipo;
	}

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	public Date getEnviado() {
		return enviado;
	}

	public void setEnviado(Date enviado) {
		this.enviado = enviado;
	}

	public String getReply_dest() {
		return reply_dest;
	}

	public void setReply_dest(String reply_dest) {
		this.reply_dest = reply_dest;
	}

	public int getReply_req() {
		return reply_req;
	}

	public void setReply_req(int reply_req) {
		this.reply_req = reply_req;
	}

	public boolean isReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	public int getPartes() {
		return partes;
	}

	public void setPartes(int partes) {
		this.partes = partes;
	}
	
	public void removeUserCredit(int credits) {
		synchronized (this.owner){
				this.owner.setCredits(this.owner.getCredits()-credits);
		}
	}

	public boolean isMultipart() {
		return multipart;
	}

	public void setMultipart(boolean multipart) {
		this.multipart = multipart;
	}
}
