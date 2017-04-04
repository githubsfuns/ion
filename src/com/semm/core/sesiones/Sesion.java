package com.semm.core.sesiones;




import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.SesionLogDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.comando.Transicion;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.MensajePorProcesar;
import com.semm.core.servicios.Servicio;
import com.semm.core.servicios.SesionLog;
import com.semm.core.servicios.Usuario;


public class Sesion {
	
	
	protected Servicio svc;
	protected Transicion trans;
	protected String cliente,tlf;
	protected Usuario owner;
	protected Hashtable<String,Object> attribs = new Hashtable<String, Object>();
	protected int blocked;
	protected List<String> lastData;
	protected boolean exclusiva;
	private static Logger log = Logger.getLogger(Sesion.class);
	protected MensajePorProcesar generador;
	
	public MensajePorProcesar getGenerador() {
		return generador;
	}

	public void setGenerador(MensajePorProcesar generador) {
		this.generador = generador;
	}

	protected long ultima_actividad;
	
	private Estado state = new Incializada();
	
	public void procesar(NuevoMensaje m){
		state.handleMensaje(m,this);
		this.ultima_actividad = System.currentTimeMillis();
	}

	public Servicio getSvc() {
		return svc;
	}

	public void setSvc(Servicio svc) {
		this.svc = svc;
	}

	public Transicion getTrans() {
		return trans;
	}

	public void setTrans(Transicion trans) {
		this.trans = trans;
	}

	public Estado getState() {
		return state;
	}

	public void setState(Estado state) {
		this.state = state;
	}



	public long getUltima_actividad() {
		return ultima_actividad;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Usuario getOwner() {
		return owner;
	}

	public void setOwner(Usuario owner) {
		this.owner = owner;
	}

	public void expired(){
		
		SesionLog slog = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		SesionLogDAO slogdao = fab.getSesionLogDAO();
		tx.beginTx();
		try {
			slog = slogdao.buscarPorIdGet(this.tlf, true);
			log.debug("Sesion: "+ this.tlf + " - " + slog);
			if(slog!=null){			
				if(!slog.isRevocable()){
					slog.setUsuario(this.owner);
					slog.setServicio(this.svc.getNombre());
				}
			}else {
				slog = new SesionLog();
				slog.setTlf(this.tlf);
				slog.setUsuario(this.owner);
				slog.setServicio(this.svc.getNombre());
				slog.setRevocable(false);
				String svcpred;
				
				if(this.svc.isPredeterminado()) 
					svcpred = (this.svc.getPred()==null) ? this.svc.getNombre() : this.svc.getPred();
				else 
					svcpred = "incoming";
				
				
				slog.setServicio_pred(svcpred);
			}
			
			log.debug("Sesion Guardando: "+ this.tlf + " Last:" +slog.getServicio()+" Pred: " + slog.getServicio_pred());
			
			slogdao.guardar(slog);
			
			tx.commit();
			tx.evict(slog);
		
		} catch (RuntimeException rte){
			tx.rollback();
			tx.cerrarSesion();
		}	

	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	
	public void clone(Sesion s2){
		this.state = s2.state;
		this.svc = s2.svc;
		this.trans = s2.trans;

		
	}

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	

	public List<String> getLastData() {
		return lastData;
	}

	public void setLastData(List<String> lastData) {
		this.lastData = lastData;
	}

	public Hashtable<String, Object> getAttribs() {
		return attribs;
	}

	public boolean isExclusiva() {
		return exclusiva;
	}

	public void setExclusiva(boolean exclusiva) {
		this.exclusiva = exclusiva;
	}






}
