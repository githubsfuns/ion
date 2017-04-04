package com.semm.core.comando.trivia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.ProductoDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.VentaDAO;
import com.semm.core.bd.VisitaDAO;
import com.semm.core.bd.trivia.ParticipanteDAO;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.comando.cvmed.ProcesarVisita;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.cvmed.Producto;
import com.semm.core.servicios.cvmed.Visita;
import com.semm.core.servicios.trivia.Participante;
import com.semm.core.servicios.trivia.Venta;

public class ProcesarVenta extends Comando {
	public static Logger log = Logger.getLogger(ProcesarVenta.class);
	Pattern regex_visita = Pattern.compile("ventas?\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)\\s+(\\d{6,8})",Pattern.CASE_INSENSITIVE);
	
	@Override
	public void ejecutar(NuevoMensaje m) {
		String sku,prod,cantidad,fecha;
		CnxMensaje sms = (CnxMensaje)m;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ProductoDAO pdao = fab.getProductoDAO();
		Responder responder = new Responder();
		Matcher match;
		String mclean = sms.getContenido().getMsg().replaceAll("[-/\\.]", "");
		match = regex_visita.matcher(mclean);
		
		if(match.find()){
			
			sku = match.group(1);
			prod = match.group(2);
			// TODO: Se acorto la expresion regular y ahora no contemplafallas
			cantidad = match.group(3);
			fecha = match.group(4);
			log.debug("Formato de Visita OK ... Procesando Venta: " + sku + " " + prod + " " + cantidad +" "+fecha);
			
			responder.setResp(procesar(sms.getPara(),sku,prod,cantidad,fecha));
			log.debug("Venta Done");
		
		} else {
			responder.setResp("Disculpe el formato del mensaje es invalido. VENTA (FACTURA) (MODELO) (CANTIDAD) (FECHA)");
		}

		
			responder.ejecutar(sms);
	}
	
	private String procesar(String tlf,String sku, String prod,  String cant,String fecha) {
		
		String resp = "";
		
		
		Participante part = verificarParticipante(tlf);
		
		if(part==null)
			return "Venta rechazada. El Participante no esta registrado en la promocion LGCLUB. Envia la palabra LGCLUB para participar.";
		
		Venta vent = verificarFactura(sku);
		
		if(vent != null)
			return "Esta venta ya ha sido registrada en nuestro sistema. Contacte al equipo de soporte para mayor informacion.";
		
		Producto modelo = verificarProducto(prod.toUpperCase());
		
		if(modelo==null){
			Producto pdf = new Producto();
			pdf.setCodigo("GENERICO");
			modelo = pdf;
		}
			//return "Venta Rechazada. El modelo ingresado no participa en la promocion de LG CLUB";
		
		//VERIFICAR PERIODO DE LA VENTA
		int cantidad = 1;
		try {
			cantidad = Integer.parseInt(cant);
			
		}catch (NumberFormatException e) {
			return "Venta Rechazada. La cantidad vendida debe ser mayor que cero (0)";
		}
		SimpleDateFormat fech = new SimpleDateFormat("ddMMyyyy");
		Date f;
		try {
			f = fech.parse(fecha);
			
		}catch (ParseException e) {
			return "Venta Rechazada. Formato de fecha invalida  Ej. 01062010";
		} 
		
		
			//Generar una venta 
			Venta venta = new Venta();
			venta.setParticipante(part);
			venta.setSku(sku);
			venta.setFecha(f);
			venta.setProducto(modelo);
			venta.setCantidad(cantidad);
			venta.setPuntosgen(cantidad*modelo.getPuntos());
			guardarVenta(venta);
			
			resp = "Felicidades! Su venta ha quedado registrada y pendiente por aprobacion de LG";
		
		
		
		return resp;
	}
	

	private Participante verificarParticipante(String rep){
		Participante r = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ParticipanteDAO rdao = fab.getParticipanteDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			r = rdao.buscarPorIdGet(rep, false);
			
		}
		catch (RuntimeException e) {
			// TODO: handle exception
		}
		tx.commit();
		return r;
	}
	
	private Producto verificarProducto(String prod){
		Producto pro = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		ProductoDAO mdao = fab.getProductoDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			pro = mdao.buscarPorIdGet(prod, false);
			
		}
		catch (RuntimeException e) {
			// TODO: handle exception
		}
		tx.commit();
		return pro;
	}
	
	private Venta verificarFactura(String sku){
		Venta pro = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VentaDAO mdao = fab.getVentaDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			pro = mdao.buscarFactura(sku);
			
		}
		catch (RuntimeException e) {
			// TODO: handle exception
		}
		tx.commit();
		return pro;
	}
	
	private void guardarVenta(Venta visit){
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		VentaDAO vdao = fab.getVentaDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		log.debug("Empezando a Guardar Venta");
		vdao.guardar(visit);
		tx.commit();
		tx.evict(visit);
	}


}
