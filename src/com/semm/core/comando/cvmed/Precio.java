package com.semm.core.comando.cvmed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.semm.core.bd.*;
import com.semm.core.comando.Comando;
import com.semm.core.comando.Responder;
import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.cvmed.*;

public class Precio extends Comando {
	Pattern regex_anul = Pattern.compile("P\\w*\\s*(\\w{3})",
			Pattern.CASE_INSENSITIVE);

	@Override
	public void ejecutar(NuevoMensaje m) {
		Responder responder = new Responder();

		if (m instanceof CnxMensaje) {
			CnxMensaje sms = (CnxMensaje) m;
			Matcher match = regex_anul.matcher(sms.getContenido().getMsg());

			if (match.find()) {
				Representante r = verificarRep(sms.getPara());
				String producto = match.group(1);
				Producto prod = verificarProducto(producto);

				if (prod != null) {
					responder.setResp(buscarPrecio(prod));

				} else {
					responder
							.setResp("Disculple el Codigo del Medicamento es Invalido ("
									+ producto
									+ " nombre  "+prod.getNombre() + "). Por favor intente de nuevo.");
				}
			} else {
				responder
						.setResp("Disculpe formato invalido. Ayuda: P (Cod. Producto. 3 letras Ej: P CLO)");
			}

			responder.ejecutar(sms);
		}

	}

	private Producto verificarProducto(String producto) {
		Producto prod = new Producto();
		prod.setCodigo(producto.trim());
		return prod;
	}

	private Representante verificarRep(String rep) {
		Representante r = null;
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		RepresentanteDAO rdao = fab.getRepresentanteDAO();
		TxDAO tx = fab.getTx();
		tx.beginTx();
		try {
			r = rdao.buscarPorId(rep, false);

		} catch (RuntimeException e) {
			// TODO: handle exception
		}
		tx.commit();
		return r;
	}

	private String buscarPrecio( Producto prod) {
		float precio = 0.0F;
		
		// TODO: Estos IFs anidados se cambian por una consulta en BD Hecho para la presentacion de coche
		if (prod.getCodigo().equalsIgnoreCase("CAR")) {
			prod.setNombre ( "Carvedil");
			precio = 20;

		}
		else if (prod.getCodigo().equalsIgnoreCase("ANT")) {
			prod.setNombre ( "Antip");
			precio = 22;
		}
		else if (prod.getCodigo().equalsIgnoreCase("RAB")) {
			prod.setNombre ( "Rabebloc");
			precio = 22.5F;
		}
		else if (prod.getCodigo().equalsIgnoreCase("mel")) {
			prod.setNombre ( "Melocox");
			precio = 5;
		}
		else if (prod.getCodigo().equalsIgnoreCase("clo")) {
			prod.setNombre ( "Clopid" );
			precio = 100;
		}
		else if (prod.getCodigo().equalsIgnoreCase("GLY")) {
			prod.setNombre ( "Glybandyl" );
			precio = 5;
		}
		else if (prod.getCodigo().equalsIgnoreCase("FLE")) {
			prod.setNombre ( "Flexitine" );
			precio = 6;
		}
		else if (prod.getCodigo().equalsIgnoreCase("FIZ")) {
			prod.setNombre ( "Fizoil");
			precio = 5;
		}
		else if (prod.getCodigo().equalsIgnoreCase("FLA")) {
			prod.setNombre ( "Flaxol" );
			precio = 45;
		}
		else if (prod.getCodigo().equalsIgnoreCase("FLG")) {
			prod.setNombre ( "Flaxol con Liganos" );
			precio = 55;
		}
		else if (prod.getCodigo().equalsIgnoreCase("HIV")) {
			prod.setNombre ( "Flexitine" );
			precio = 75;
		}
		else if (prod.getCodigo().equalsIgnoreCase("DOL")) {
			prod.setNombre ( "Dolreum" );
			precio = 35;
		}
		else if (prod.getCodigo().equalsIgnoreCase("NEU")) {
			prod.setNombre ( "Neukob" );
			precio = 25;
		}
		else if (prod.getCodigo().equalsIgnoreCase("RED")) {
			prod.setNombre ( "Redugras" );
			precio = 85;
		} else {

			return "No existe ningun producto con el codigo: "
					+ prod.getNombre() + ". Por favor intente de nuevo";

		}
		// TODO: LA informacion de precios puede incluir varias presentaciones
		return "El Precio actual de " + prod.getNombre() + " es de " + precio + "Bs F";
	}
	

}
