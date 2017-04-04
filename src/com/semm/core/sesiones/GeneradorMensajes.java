package com.semm.core.sesiones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.semm.core.bd.*;
import com.semm.core.conexiones.Mensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.conexiones.ExtendedTransData;
import com.semm.core.conexiones.TransData;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.servicios.*;


public class GeneradorMensajes {
	
	private HashMap<String,String> replacemets;
	private int tipo;
	private boolean report;
	private static Pattern pat = Pattern.compile("[0|0?4][1|2][2|4|6]\\D*\\d{3}\\D*\\d{2}\\D*\\d{2}");
	private static Pattern pat2 = Pattern.compile("0?2\\d{2}\\D*\\d{3}\\D*\\d{2}\\D*\\d{2}");
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 */
	public GeneradorMensajes() {
		replacemets = new HashMap<String, String>(3);
	}
	
	public List<NuevoMensaje> generar(String[] recips,String[] data,String client, String svc,boolean locales){
		List<NuevoMensaje> msgs = new ArrayList<NuevoMensaje>();
		
		for(String rec:recips){
			String tlf = verificarRecip(rec,locales);
			if(tlf!=null){
				UserTransData td= getTipoTransData();
				td.setRecips(new ArrayList<String>(1));
				td.getRecips().add(tlf);
				td.setData(new ArrayList<String>(Arrays.asList(data)));			
				td.setServicio(svc);
				td.setCliente(client);
				msgs.add(td);
			}
		}
		
		return msgs;
		
	}

	public List<NuevoMensaje> generar(String[] recips,String body,String client, String svc,boolean locales){
		List<NuevoMensaje> msgs = new ArrayList<NuevoMensaje>();
		
		for(String rec:recips){
			String tlf = verificarRecip(rec,locales);
			if(tlf!=null){
				UserTransData td= getTipoTransData();
				td.setRecips(new ArrayList<String>(1));
				td.getRecips().add(tlf);
				td.setData(new ArrayList<String>(1));
				td.getData().add(body);
				td.setServicio(svc);
				td.setCliente(client);
				msgs.add(td);
			}
		}
		
		return msgs;
		
	}
	
	private UserTransData getTipoTransData() {
		
		if(this.tipo > 0){
			ExtendedTransData ptd = new ExtendedTransData();
			ptd.setTipo(this.tipo);
			ptd.setReporte(this.report);
			return ptd;
		} else {
			return new UserTransData();
		}
		
	}

	public List<NuevoMensaje> generarLista(long id,String body,String cliente, String svc,boolean locales){
		List<NuevoMensaje> msgs = new ArrayList<NuevoMensaje>();
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		ListaDAO ldao = fab.getListaDAO();
		tx.beginTx();
			Lista l = ldao.buscarPorId(new Long(id),false);
			for(Cliente c:l.getClientes()){	
				String tlf = verificarRecips(c,locales);
				if(tlf!=null){
					UserTransData td = getTipoTransData();
					td.setRecips(new ArrayList<String>(1));
					td.getRecips().add(tlf);
					td.setData(new ArrayList<String>(1));
					td.getData().add(armar(body,c));
					td.setServicio(svc);
					td.setCliente(cliente);
					msgs.add(td);
				}
				tx.evict(c);
			}
		
		tx.commit();
		tx.evict(l);
		return msgs;
	}
	
	public List<NuevoMensaje> generarListaDinamica(long id,String body,String cliente, String svc,boolean locales){
		List<NuevoMensaje> msgs = new ArrayList<NuevoMensaje>();
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		ListaDinamicaDAO ldao = fab.getListaDinamicaDAO();
		tx.beginTx();
		
			ListaDinamica l = ldao.buscarPorId(new Long(id),false);
			tx.commit();
			l.llenarDinamica();
			for(Cliente c:l.getClientes()){	
				String tlf = verificarRecips(c,locales);
				if(tlf!=null){
					UserTransData td = getTipoTransData();
					td.setRecips(new ArrayList<String>(1));
					td.getRecips().add(tlf);
					td.setData(new ArrayList<String>(1));
					td.getData().add(armar(body,c));
					td.setServicio(svc);
					td.setCliente(cliente);
					msgs.add(td);
				}
			}
		

		return msgs;
		
	}
	
	public List<NuevoMensaje> generarListaData(long id,List<String> data,String cliente, String svc,boolean locales){
		List<NuevoMensaje> msgs = new ArrayList<NuevoMensaje>();
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		ListaDAO ldao = fab.getListaDAO();
		tx.beginTx();
			Lista l = ldao.buscarPorId(new Long(id),false);
			for(Cliente c:l.getClientes()){	
				String tlf = verificarRecips(c,locales);
				if(tlf!=null){
					UserTransData td = getTipoTransData();
					td.setRecips(new ArrayList<String>(1));
					td.getRecips().add(tlf);
					td.setData(new ArrayList<String>(1));
					td.setData(data);
					td.setServicio(svc);
					td.setCliente(cliente);
					msgs.add(td);
				}
			}
		tx.commit();
		return msgs;
		
	}
	
	public List<NuevoMensaje> generarListaTemp(String body,String usuario,List<Cliente> clients,boolean unique, String svc,boolean locales){
		
		List<NuevoMensaje> msgs = new ArrayList<NuevoMensaje>();
		HashMap<String,NuevoMensaje> msgshm = new HashMap<String,NuevoMensaje>();
		int i = 0;
		for(Cliente c: clients){
			String tlf = verificarRecips(c,locales);
			if(tlf!=null){
				UserTransData td = getTipoTransData();
				td.setRecips(new ArrayList<String>(1));
				td.getRecips().add(tlf);
				td.setData(new ArrayList<String>(1));
				td.getData().add(armar(body,c));
				td.setServicio(svc);
				td.setCliente(usuario);				
				if(unique){
					msgshm.put(tlf, td);
				} else 
					msgs.add(td);
			}
		
		}
		if(unique)
			msgs.addAll(msgshm.values());
		return msgs;
	}

	private String verificarRecips(Cliente c,boolean locales) {
		String tlf = null;
	
		tlf = verificarRecip(c.getTlf(),locales);
		
		if(tlf!=null)
			return tlf;
		
		tlf = verificarRecip(c.getTlf2(),locales);
		
		return tlf;
	}

	private String armar(String body,Cliente c) {
		String copy = new String(body);
		replacemets.put("##NOMBRE##",c.getNombre());
		replacemets.put("##APELLIDO##",c.getApellido());
		replacemets.put("##NOMBREA##",c.getNombre()+" "+c.getApellido());
		replacemets.put("##DATA1##",c.getData1());
		replacemets.put("##DATA2##",c.getData2());
		replacemets.put("##DATA3##",c.getData3());
		Set<Entry<String, String>> es = replacemets.entrySet();
		for(Entry<String,String> e : es){
			String valor = (e.getValue()==null)? "":e.getValue();
			copy = copy.replaceAll(e.getKey(), valor);
		}
		return copy;
		
	}
	
	public static String verificarRecip(String rec,boolean locales){
		if(rec!=null){
			String clean = null;
			Matcher matcher = pat.matcher(rec);
			
			if(matcher.find()){
				clean = matcher.group().replaceAll("\\D","");
				clean = clean.replaceAll(" ","");
				clean = clean.replaceAll("^01","041");
				clean = clean.replaceAll("^4","04");
				
				if(Pattern.matches("^04[1|2][2|4|6]\\d{7}",clean)){
					return clean;
				} 
			}
			
			if(locales){
				Matcher matcher2 = pat2.matcher(rec);
				
				if(matcher2.find()){
					clean = matcher2.group().replaceAll("\\D","");
					clean = clean.replaceAll(" ","");
					clean = clean.replaceAll("^2","02");
					
					if(Pattern.matches("^02\\d{9}",clean)){
						return clean;
					} 
				}
			}
				
		}
		return null;
	}

	public void setReport(boolean report) {
		this.report = report;
	}


	
	
}
