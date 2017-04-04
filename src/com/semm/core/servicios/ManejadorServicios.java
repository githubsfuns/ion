package com.semm.core.servicios;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.semm.core.bd.FabricaDAO;
import com.semm.core.bd.GrupoConexionesDAO;
import com.semm.core.bd.TxDAO;
import com.semm.core.bd.UsuarioDAO;
import com.semm.core.comando.Comando;
import com.semm.core.comando.EjecutarTransicion;
import com.semm.core.comando.LlamadaOjo;
import com.semm.core.comando.Notificar;
import com.semm.core.comando.NotificarContinuado;
import com.semm.core.comando.NotificarPaginado;
import com.semm.core.comando.NotificarUnaSolaVez;
import com.semm.core.comando.TransicionReporte;
import com.semm.core.comando.mp.LlamadaMp;
import com.semm.core.comando.mud.LlamadaMUD;
import com.semm.core.comando.og.LlamadaOg;
import com.semm.core.comando.og.LlamadaOg2;
import com.semm.core.comando.trivia.ProcesarRegistro;
import com.semm.core.comando.trivia.ProcesarRespuesta;
import com.semm.core.comando.trivia.ProcesarTrivia;
import com.semm.core.comando.trivia.ProcesarVenta;
import com.semm.core.comando.Responder;
import com.semm.core.comando.TransicionRegex;
import com.semm.core.comando.bb.NotificarBlackberry;
import com.semm.core.comando.cvmed.*;
import com.semm.core.conexiones.Conexion;
import com.semm.core.conexiones.GrupoConexiones;
import com.semm.core.conexiones.ManejadorConexiones;
import com.semm.core.conexiones.UserTransData;
import com.semm.core.conexiones.contenido.Contenido;
import com.semm.core.conexiones.contenido.ContenidoSMS;


public class ManejadorServicios {
	
	private ManejadorConexiones mcnx = ManejadorConexiones.getInstancia();
	private SortedMap<String,Servicio> servicios_activos;
	
	public static Logger log = Logger.getLogger(ManejadorServicios.class);
	
	private static ManejadorServicios instance = null;


	private ManejadorServicios() {
		servicios_activos =  new TreeMap<String, Servicio>();
	}
	
	public static ManejadorServicios getInstancia(){
		if(instance == null){
			instance = new ManejadorServicios();
		}
		return instance;
	}
	
	public Servicio buscarServicio(String nombre){
		return servicios_activos.get(nombre);
	}
	
	public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {
		log.debug("Cargando Servicios");
		

		
		
		//Otra parte para la capa de persistencia frenes!
		
		Servicio svc = new Servicio();
		svc.setTimeout(14400000);
		svc.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida not = new NotificacionSalida();
		Map<Integer,Conexion> hm = new HashMap<Integer,Conexion>();
		Map<Integer,String> dm = new HashMap<Integer,String>();
		hm.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dm.put(new Integer(1),"##NOMBRE##");
		dm.put(new Integer(0),"##NUM##");
		//Contenido cont = new ContenidoSMS("Buen dia!\nQuickPress El Marques le informa que su orden ##NUM## esta lista para ser retirada\nIrina Ramos\nGerente");
		Contenido cont = new ContenidoSMS("Estimado cliente,\nTiene ordenes listas con mas de 3 meses. Por favor retirelas y evitenos traslado a almacen. Gracias!\nQuickPress El Marques");
		
		
		not.setContenido(cont);
		not.setDatareplace(dm);
		not.setRoutes(hm);
		Notificar nfy = new Notificar();
		nfy.setOut(not);
		

		Servicio svcvs = new Servicio();
		svcvs.setTimeout(14400000);
		svcvs.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notvs = new NotificacionSalida();
		Map<Integer,Conexion> hmvs = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmvs = new HashMap<Integer,String>();
		hmvs.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmvs.put(new Integer(1),"##NOMBRE##");
		dmvs.put(new Integer(0),"##NUM##");
		//Contenido contvs = new ContenidoSMS("Buen dia!\nQuickPress Valet Service le informa que su orden ##NUM## esta lista para ser retirada\nElvis Canizales\nGerente");
		Contenido contvs = new ContenidoSMS("Estimado cliente,\nTiene ordenes listas con mas de 3 meses. Por favor retirelas y evitenos traslado a almacen. Gracias!\nQuickPress VS El Marques");
		notvs.setContenido(contvs);
		notvs.setDatareplace(dmvs);
		notvs.setRoutes(hmvs);
		Notificar nfyvs = new Notificar();
		nfyvs.setOut(notvs);
		
		
		Servicio svcln = new Servicio();
		svcln.setTimeout(14400000);
		svcln.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notln = new NotificacionSalida();
		Map<Integer,Conexion> hmln = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmln = new HashMap<Integer,String>();
		hmln.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmln.put(new Integer(1),"##NOMBRE##");
		dmln.put(new Integer(0),"##NUM##");
		//Contenido contln = new ContenidoSMS("Buen dia!\nQuickPress Los Naranjos le informa que su orden ##NUM## esta lista para ser retirada\nChelimar Marquez\nGerente");
		Contenido contln = new ContenidoSMS("Estimado cliente,\nTiene ordenes listas con mas de 3 meses. Por favor retirelas y evitenos traslado a almacen. Gracias!\nQuickPress Los Naranjos");
		 
		
		//Contenido contln = new ContenidoSMS("Estimado Cliente, las prendas que nos trajo en FEBRERO estan disponibles para su retiro. Muchas Gracias! QuickPress Los Naranjos");
		
		notln.setContenido(contln);
		notln.setDatareplace(dmln);
		notln.setRoutes(hmln);
		Notificar nfyln = new Notificar();
		nfyln.setOut(notln);
		
		Servicio svcer = new Servicio();
		svcer.setTimeout(14400000);
		svcer.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida noter = new NotificacionSalida();
		Map<Integer,Conexion> hmer = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmer = new HashMap<Integer,String>();
		hmer.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmer.put(new Integer(1),"##NOMBRE##");
		dmer.put(new Integer(0),"##NUM##");
		Contenido conter = new ContenidoSMS("Quick Press El Rosal,\nSu orden ##NUM## esta lista. Por cierre de negocio agradecemos agilice su retiro. Gracias,\nR. Valbuena\nGerente");
		noter.setContenido(conter);
		noter.setDatareplace(dmer);
		noter.setRoutes(hmer);
		Notificar nfyer = new Notificar();
		nfyer.setOut(noter);
		
		Servicio svcpde = new Servicio();
		svcpde.setGrpcnx(getGrupoConexion(new Long(1)));
		svcpde.setTimeout(14400000);
		NotificacionSalida notpde = new NotificacionSalida();
		Map<Integer,Conexion> hmpde = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmpde = new HashMap<Integer,String>();
		hmpde.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmpde.put(new Integer(1),"##NOMBRE##");
		dmpde.put(new Integer(0),"##NUM##");
		//Contenido contpde = new ContenidoSMS("Buen dia!\nQuickPress Prados del Este le informa que su orden ##NUM## esta lista para ser retirada\nChelimar Marquez\nGerente");
		Contenido contpde = new ContenidoSMS("Estimado cliente\nTiene ordenes listas con mas de 3 meses. Por favor retirelas y evitenos traslado a almacen. Gracias\nQuickPress Prados del Este");
		notpde.setContenido(contpde);
		notpde.setDatareplace(dmpde);
		notpde.setRoutes(hmpde);
		Notificar nfypde = new Notificar();
		nfypde.setOut(notpde);
		
		
		Servicio svclb = new Servicio();
		svclb.setTimeout(14400000);
		svclb.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notlb = new NotificacionSalida();
		Map<Integer,Conexion> hmlb = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmlb = new HashMap<Integer,String>();
		hmlb.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmlb.put(new Integer(1),"##NOMBRE##");
		dmlb.put(new Integer(0),"##NUM##");
		//Contenido contlb = new ContenidoSMS("Buen dia!\nQuickPress La Boyera le informa que su orden ##NUM## esta lista para ser retirada\nSantiago Matheus\nGerente");
		Contenido contlb = new ContenidoSMS("Estimado cliente,\nTiene ordenes listas con mas de 3 meses. Por favor retirelas y evitenos traslado a almacen. Gracias!\nQuickPress La Boyera");
		notlb.setContenido(contlb);
		notlb.setDatareplace(dmlb);
		notlb.setRoutes(hmlb);
		Notificar nfylb = new Notificar();
		nfylb.setOut(notlb);
		
		Servicio svcld = new Servicio();
		svcld.setTimeout(14400000);
		svcld.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notld = new NotificacionSalida();
		Map<Integer,Conexion> hmld = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmld = new HashMap<Integer,String>();
		hmld.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmld.put(new Integer(1),"##NOMBRE##");
		dmld.put(new Integer(0),"##NUM##");
		//Contenido contld = new ContenidoSMS("Buen dia!\nQuickPress Las Delicias le informa que su orden ##NUM## esta lista para ser retirada\nCarlos Bello\nGerente");
		Contenido contld = new ContenidoSMS("Estimado cliente,\nTiene ordenes listas con mas de 3 meses. Por favor retirelas y evitenos traslado a almacen. Gracias!\nQuickPress Las Delicias");
		notld.setContenido(contld);
		notld.setDatareplace(dmld);
		notld.setRoutes(hmld);
		Notificar nfyld = new Notificar();
		nfyld.setOut(notld);
		
		
		Servicio svcsb = new Servicio();
		svcsb.setTimeout(14400000);
		svcsb.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notsb = new NotificacionSalida();
		Map<Integer,Conexion> hmsb = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsb = new HashMap<Integer,String>();
		hmsb.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsb.put(new Integer(1),"##NOMBRE##");
		dmsb.put(new Integer(0),"##NUM##");
		//Contenido contsb = new ContenidoSMS("Buen dia!\nQuickPress San Bernardino I le informa que su orden ##NUM## esta lista para ser retirada\nJanet Manrique\nGerente");
		Contenido contsb = new ContenidoSMS("Estimado cliente\nTiene ordenes listas con mas de 3 meses. Por favor retirelas y evitenos traslado a almacen. Gracias!\nQuickPress San Bernardino");
		notsb.setContenido(contsb);
		notsb.setDatareplace(dmsb);
		notsb.setRoutes(hmsb);
		Notificar nfysb = new Notificar();
		nfysb.setOut(notsb);
		
		Servicio svcsb2 = new Servicio();
		svcsb2.setTimeout(14400000);
		svcsb2.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notsb2 = new NotificacionSalida();
		Map<Integer,Conexion> hmsb2 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsb2 = new HashMap<Integer,String>();
		hmsb2.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsb2.put(new Integer(1),"##NOMBRE##");
		dmsb2.put(new Integer(0),"##NUM##");
		//Contenido contsb2 = new ContenidoSMS("Buen dia!\nQuickPress San Bernardino II le informa que su orden ##NUM## esta lista para ser retirada\nElvis Canizales\nGerente");
		Contenido contsb2 = new ContenidoSMS("Estimado cliente\nTiene ordenes listas con mas de 3 meses. Por favor retirelas y evitenos traslado a almacen. Gracias!\nQuickPress San Bernardino");
		notsb2.setContenido(contsb2);
		notsb2.setDatareplace(dmsb2);
		notsb2.setRoutes(hmsb2);
		Notificar nfysb2 = new Notificar();
		nfysb2.setOut(notsb2);
		
		
		Servicio svcvssm = new Servicio();
		svcvssm.setGrpcnx(getGrupoConexion(new Long(1)));
		svcvssm.setTimeout(14400000);
		NotificacionSalida notvssm = new NotificacionSalida();
		Map<Integer,Conexion> hmvssm = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmvssm = new HashMap<Integer,String>();
		hmvssm.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmvssm.put(new Integer(1),"##NOMBRE##");
		dmvssm.put(new Integer(0),"##NUM##");
		Contenido contvssm = new ContenidoSMS("Buen dia!\nQuickPress ValetService Santa Monica le informa que su orden ##NUM## esta lista para ser retirada\nRosa Gonzalez\nGerente");
		notvssm.setContenido(contvssm);
		notvssm.setDatareplace(dmvssm);
		notvssm.setRoutes(hmvssm);
		Notificar nfyvssm = new Notificar();
		nfyvssm.setOut(notvssm);
		
		
		Servicio svccm = new Servicio();
		svccm.setTimeout(14400000);
		svccm.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcm = new NotificacionSalida();
		Map<Integer,Conexion> hmcm = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcm = new HashMap<Integer,String>();
		hmcm.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcm.put(new Integer(1),"##NOMBRE##");
		dmcm.put(new Integer(0),"##NUM##");
		Contenido contcm = new ContenidoSMS("Buen dia!\nQuickPress Las Mercedes le informa que su orden ##NUM## esta lista para ser retirada\nDennys Rechider\nGerente");
		notcm.setContenido(contcm);
		notcm.setDatareplace(dmcm);
		notcm.setRoutes(hmcm);
		Notificar nfycm = new Notificar();
		nfycm.setOut(notcm);
		
		Servicio svcccct = new Servicio();
		svcccct.setTimeout(14400000);
		svcccct.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notccct = new NotificacionSalida();
		Map<Integer,Conexion> hmccct = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmccct = new HashMap<Integer,String>();
		hmccct.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmccct.put(new Integer(1),"##NOMBRE##");
		dmccct.put(new Integer(0),"##NUM##");
		Contenido contccct = new ContenidoSMS("Buen dia!\nQuickPress C.C.C.T le informa que su orden ##NUM## esta lista para ser retirada\nFanny Iriarte\nGerente");
		notccct.setContenido(contccct);
		notccct.setDatareplace(dmccct);
		notccct.setRoutes(hmccct);
		Notificar nfyccct = new Notificar();
		nfyccct.setOut(notccct);
		
		// La Tahona
		Servicio svctahona = new Servicio();
		svctahona.setTimeout(14400000);
		svctahona.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottahona = new NotificacionSalida();
		Map<Integer,Conexion> hmtahona = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtahona = new HashMap<Integer,String>();
		hmtahona.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtahona.put(new Integer(1),"##NOMBRE##");
		dmtahona.put(new Integer(0),"##NUM##");
		Contenido conttahona = new ContenidoSMS("Buen dia!\nQuick Press La Tahona le informa que su orden ##NUM## esta lista para ser retirada\nGracias");
		nottahona.setContenido(conttahona);
		nottahona.setDatareplace(dmtahona);
		nottahona.setRoutes(hmtahona);
		Notificar nfytahona = new Notificar();
		nfytahona.setOut(nottahona);
		
		Servicio svcsanta = new Servicio();
		svcsanta.setTimeout(14400000);
		svcsanta.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notsanta = new NotificacionSalida();
		Map<Integer,Conexion> hmsanta = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsanta = new HashMap<Integer,String>();
		hmsanta.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsanta.put(new Integer(1),"##NOMBRE##");
		dmsanta.put(new Integer(0),"##NUM##");
		Contenido contsanta = new ContenidoSMS("Buen dia!\nQuick Press Sta. Rosa le informa que su orden ##NUM## esta lista para ser retirada\nMaria Fernandez\nGerente");
		notsanta.setContenido(contsanta);
		notsanta.setDatareplace(dmsanta);
		notsanta.setRoutes(hmsanta);
		Notificar nfysanta = new Notificar();
		nfysanta.setOut(notsanta);
		
		Servicio svcparaiso  = new Servicio();
		svcparaiso.setTimeout(14400000);
		svcparaiso.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notparaiso = new NotificacionSalida();
		Map<Integer,Conexion> hmparaiso = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmparaiso = new HashMap<Integer,String>();
		hmparaiso.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmparaiso.put(new Integer(1),"##NOMBRE##");
		dmparaiso.put(new Integer(0),"##NUM##");
		Contenido contparaiso = new ContenidoSMS("Buen dia!\nQuick Press El Paraiso le informa que su orden ##NUM## esta lista para ser retirada\nMariela Gandica\nGerente");
		notparaiso.setContenido(contparaiso);
		notparaiso.setDatareplace(dmparaiso);
		notparaiso.setRoutes(hmparaiso);
		Notificar nfyparaiso = new Notificar();
		nfyparaiso.setOut(notparaiso);
		
		Servicio svclf  = new Servicio();
		svclf.setTimeout(14400000);
		svclf.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notlf = new NotificacionSalida();
		Map<Integer,Conexion> hmlf = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmlf = new HashMap<Integer,String>();
		hmlf.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmlf.put(new Integer(1),"##NOMBRE##");
		dmlf.put(new Integer(0),"##NUM##");
		Contenido contlf = new ContenidoSMS("Buen dia!\nQuickPress Las Fuentes le informa que su orden ##NUM## esta lista para ser retirada\nGracias");
		notlf.setContenido(contlf);
		notlf.setDatareplace(dmlf);
		notlf.setRoutes(hmlf);
		Notificar nfylf = new Notificar();
		nfylf.setOut(notlf);
		
		Servicio svccasona  = new Servicio();
		svccasona.setTimeout(14400000);
		svccasona.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcasona = new NotificacionSalida();
		Map<Integer,Conexion> hmcasona = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcasona = new HashMap<Integer,String>();
		hmcasona.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcasona.put(new Integer(1),"##NOMBRE##");
		dmcasona.put(new Integer(0),"##NUM##");
		Contenido contcasona = new ContenidoSMS("Buen dia!\nQuickPress La Casona II le informa que su orden ##NUM## esta lista para ser retirada\nGracias\nLa Gerencia");
		notcasona.setContenido(contcasona);
		notcasona.setDatareplace(dmcasona);
		notcasona.setRoutes(hmcasona);
		Notificar nfycasona = new Notificar();
		nfycasona.setOut(notcasona);
		
		Servicio svctrinidad  = new Servicio();
		svctrinidad.setTimeout(14400000);
		svctrinidad.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottrinidad = new NotificacionSalida();
		Map<Integer,Conexion> hmtrinidad = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtrinidad = new HashMap<Integer,String>();
		hmtrinidad.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtrinidad.put(new Integer(1),"##NOMBRE##");
		dmtrinidad.put(new Integer(0),"##NUM##");
		Contenido conttrinidad = new ContenidoSMS("Buen dia!\nQuick Press La Trinidad le informa que su orden ##NUM## esta lista para ser retirada\nLuis Sequin\nGerente");
		nottrinidad.setContenido(conttrinidad);
		nottrinidad.setDatareplace(dmtrinidad);
		nottrinidad.setRoutes(hmtrinidad);
		Notificar nfytrinidad = new Notificar();
		nfytrinidad.setOut(nottrinidad);
		
		
		Servicio svcsamanes  = new Servicio();
		svcsamanes.setTimeout(14400000);
		svcsamanes.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notsamanes = new NotificacionSalida();
		Map<Integer,Conexion> hmsamanes = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsamanes = new HashMap<Integer,String>();
		hmsamanes.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsamanes.put(new Integer(1),"##NOMBRE##");
		dmsamanes.put(new Integer(0),"##NUM##");
		Contenido contsamanes = new ContenidoSMS("Buen dia!\nQuick Press Los Samanes le informa que su orden ##NUM## esta lista para ser retirada\nBrigitte Betancourt\nGerente");
		notsamanes.setContenido(contsamanes);
		notsamanes.setDatareplace(dmsamanes);
		notsamanes.setRoutes(hmsamanes);
		Notificar nfysamanes = new Notificar();
		nfysamanes.setOut(notsamanes);
		
		
		Servicio svcurbina  = new Servicio();
		svcurbina.setTimeout(14400000);
		svcurbina.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida noturbina = new NotificacionSalida();
		Map<Integer,Conexion> hmurbina = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmurbina = new HashMap<Integer,String>();
		hmurbina.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmurbina.put(new Integer(1),"##NOMBRE##");
		dmurbina.put(new Integer(0),"##NUM##");
		Contenido conturbina = new ContenidoSMS("Ultimos dias para retirar sus prendas de Quick Press La Urbina. Recuerde que Ud tiene la orden ##NUM##, si no la retira sus prendas seran donadas.");
		noturbina.setContenido(conturbina);
		noturbina.setDatareplace(dmurbina);
		noturbina.setRoutes(hmurbina);
		Notificar nfyurbina = new Notificar();
		nfyurbina.setOut(noturbina);
		
		// 4 de Mayo
		Servicio svc4dm  = new Servicio();
		svc4dm.setTimeout(14400000);
		svc4dm.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida not4dm = new NotificacionSalida();
		Map<Integer,Conexion> hm4dm = new HashMap<Integer,Conexion>();
		Map<Integer,String> dm4dm = new HashMap<Integer,String>();
		hm4dm.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dm4dm.put(new Integer(1),"##NOMBRE##");
		dm4dm.put(new Integer(0),"##NUM##");
		Contenido cont4dm = new ContenidoSMS("Buen dia! QuickPress Av. 4 de Mayo le informa que su orden ##NUM## esta lista para ser retirada Antonio Gomez Encargado");
		not4dm.setContenido(cont4dm);
		not4dm.setDatareplace(dm4dm);
		not4dm.setRoutes(hm4dm);
		Notificar nfy4dm = new Notificar();
		nfy4dm.setOut(not4dm);
		
		// El Valle
		Servicio svcev  = new Servicio();
		svcev.setTimeout(14400000);
		svcev.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notev = new NotificacionSalida();
		Map<Integer,Conexion> hmev = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmev = new HashMap<Integer,String>();
		hmev.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmev.put(new Integer(1),"##NOMBRE##");
		dmev.put(new Integer(0),"##NUM##");
		Contenido contev = new ContenidoSMS("Buen dia! QuickPress El Valle le informa que su orden ##NUM## esta lista para ser retirada");
		notev.setContenido(contev);
		notev.setDatareplace(dmev);
		notev.setRoutes(hmev);
		Notificar nfyev = new Notificar();
		nfyev.setOut(notev);
		
		// CC La Redoma Margarita
		Servicio svccclr  = new Servicio();
		svccclr.setTimeout(14400000);
		svccclr.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcclr = new NotificacionSalida();
		Map<Integer,Conexion> hmcclr = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcclr = new HashMap<Integer,String>();
		hmcclr.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcclr.put(new Integer(1),"##NOMBRE##");
		dmcclr.put(new Integer(0),"##NUM##");
		Contenido contcclr = new ContenidoSMS("Buen dia! QuickPress C.C. La Redoma le informa que su orden ##NUM## esta lista para ser retirada\nGracias");
		notcclr.setContenido(contcclr);
		notcclr.setDatareplace(dmcclr);
		notcclr.setRoutes(hmcclr);
		Notificar nfycclr = new Notificar();
		nfycclr.setOut(notcclr);
		
		// Playa el Angel
		Servicio svcpea  = new Servicio();
		svcpea.setTimeout(14400000);
		svcpea.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notpea = new NotificacionSalida();
		Map<Integer,Conexion> hmpea = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmpea = new HashMap<Integer,String>();
		hmpea.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmpea.put(new Integer(1),"##NOMBRE##");
		dmpea.put(new Integer(0),"##NUM##");
		Contenido contpea = new ContenidoSMS("Buen dia! QuickPress Playa El Angel le informa que su orden ##NUM## esta lista para ser retirada\nRossanaliz Marin\nEncargada");
		notpea.setContenido(contpea);
		notpea.setDatareplace(dmpea);
		notpea.setRoutes(hmpea);
		Notificar nfypea = new Notificar();
		nfypea.setOut(notpea);
		
		//San Cristobal I
		Servicio svcsc1  = new Servicio();
		svcsc1.setTimeout(14400000);
		svcsc1.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notsc1 = new NotificacionSalida();
		Map<Integer,Conexion> hmsc1 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsc1 = new HashMap<Integer,String>();
		hmsc1.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsc1.put(new Integer(1),"##NOMBRE##");
		dmsc1.put(new Integer(0),"##NUM##");
		Contenido contsc1 = new ContenidoSMS("Buen dia! QuickPress Av. Ferrero Tamayo le informa que su orden ##NUM## esta lista para ser retirada\nGracias");
		notsc1.setContenido(contsc1);
		notsc1.setDatareplace(dmsc1);
		notsc1.setRoutes(hmsc1);
		Notificar nfysc1 = new Notificar();
		nfysc1.setOut(notsc1);
		

		//San Cristobal II
		Servicio svcsc2  = new Servicio();
		svcsc2.setTimeout(14400000);
		svcsc2.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notsc2 = new NotificacionSalida();
		Map<Integer,Conexion> hmsc2 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsc2 = new HashMap<Integer,String>();
		hmsc2.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsc2.put(new Integer(1),"##NOMBRE##");
		dmsc2.put(new Integer(0),"##NUM##");
		Contenido contsc2 = new ContenidoSMS("Buen dia! QuickPress Sambil San Cristobal le informa que su orden ##NUM## esta lista para ser retirada\nGracias");
		notsc2.setContenido(contsc2);
		notsc2.setDatareplace(dmsc2);
		notsc2.setRoutes(hmsc2);
		Notificar nfysc2 = new Notificar();
		nfysc2.setOut(notsc2);
		
		//Cumana
		Servicio svccumana  = new Servicio();
		svccumana.setTimeout(14400000);
		svccumana.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumana = new NotificacionSalida();
		Map<Integer,Conexion> hmcumana = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumana = new HashMap<Integer,String>();
		hmcumana.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumana.put(new Integer(1),"##NOMBRE##");
		dmcumana.put(new Integer(0),"##NUM##");
		Contenido contcumana = new ContenidoSMS("Buen dia! QuickPress Cumana le informa que su orden ##NUM## esta lista para ser retirada\nHector Caraballo\nGerente");
		notcumana.setContenido(contcumana);
		notcumana.setDatareplace(dmcumana);
		notcumana.setRoutes(hmcumana);
		Notificar nfycumana = new Notificar();
		nfycumana.setOut(notcumana);
		
		//Guatire Buenaventura
		Servicio svcbuena  = new Servicio();
		svcbuena.setTimeout(14400000);
		svcbuena.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notbuena = new NotificacionSalida();
		Map<Integer,Conexion> hmbuena = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmbuena = new HashMap<Integer,String>();
		hmbuena.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmbuena.put(new Integer(1),"##NOMBRE##");
		dmbuena.put(new Integer(0),"##NUM##");
		Contenido contbuena = new ContenidoSMS("Buen dia! QuickPress Buenaventura le informa que su orden ##NUM## esta lista para ser retirada\nJenny Angola\nGerente");
		notbuena.setContenido(contbuena);
		notbuena.setDatareplace(dmbuena);
		notbuena.setRoutes(hmbuena);
		Notificar nfybuena = new Notificar();
		nfybuena.setOut(notbuena);
		
		//Quickpress SANTA MONICA TIENDA
		Servicio svcstmon  = new Servicio();
		svcstmon.setTimeout(14400000);
		svcstmon.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notstmon = new NotificacionSalida();
		Map<Integer,Conexion> hmstmon = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmstmon = new HashMap<Integer,String>();
		hmstmon.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmstmon.put(new Integer(1),"##NOMBRE##");
		dmstmon.put(new Integer(0),"##NUM##");
		Contenido contstmon = new ContenidoSMS("Buen dia! QuickPress Santa Monica le informa que su orden ##NUM## esta lista para ser retirada\nDaiana Merlo\nSub Gerente");
		notstmon.setContenido(contstmon);
		notstmon.setDatareplace(dmstmon);
		notstmon.setRoutes(hmstmon);
		Notificar nfystmon = new Notificar();
		nfystmon.setOut(notstmon);
		
		// Sambil Margarita
		Servicio svcsm  = new Servicio();
		svcsm.setTimeout(14400000);
		svcsm.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notsm = new NotificacionSalida();
		Map<Integer,Conexion> hmsm = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsm = new HashMap<Integer,String>();
		hmsm.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsm.put(new Integer(1),"##NOMBRE##");
		dmsm.put(new Integer(0),"##NUM##");
		Contenido contsm = new ContenidoSMS("Buen dia! QuickPress C.C. Sambil Margarita le informa que su orden ##NUM## esta lista para ser retirada\nJose Luis Hernandez\nEncargado");
		notsm.setContenido(contsm);
		notsm.setDatareplace(dmsm);
		notsm.setRoutes(hmsm);
		Notificar nfysm = new Notificar();
		nfysm.setOut(notsm);
		
		// GAMA PLUS LA TRINIDAD
		Servicio svcgamaplus  = new Servicio();
		svcgamaplus.setTimeout(14400000);
		svcgamaplus.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notgamaplus = new NotificacionSalida();
		Map<Integer,Conexion> hmgamaplus = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmgamaplus = new HashMap<Integer,String>();
		hmgamaplus.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmgamaplus.put(new Integer(1),"##NOMBRE##");
		dmgamaplus.put(new Integer(0),"##NUM##");
		Contenido contgamaplus = new ContenidoSMS("Buen dia! Quick Press Gama le informa que su orden ##NUM## esta lista para ser retirada\nLa Gerencia.\nGracias");
		notgamaplus.setContenido(contgamaplus);
		notgamaplus.setDatareplace(dmgamaplus);
		notgamaplus.setRoutes(hmgamaplus);
		Notificar nfygamaplus = new Notificar();
		nfygamaplus.setOut(notgamaplus);
		
		
		// Maracaibo I
		Servicio svcmcbo  = new Servicio();
		svcmcbo.setTimeout(14400000);
		svcmcbo.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notmcbo = new NotificacionSalida();
		Map<Integer,Conexion> hmmcbo = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmmcbo = new HashMap<Integer,String>();
		hmmcbo.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmmcbo.put(new Integer(1),"##NOMBRE##");
		dmmcbo.put(new Integer(0),"##NUM##");
		Contenido contmcbo = new ContenidoSMS("Buen dia! QuickPress Canta Claro le informa que su orden ##NUM## esta lista para ser retirada\nNuria Lopez\nGerente");
		notmcbo.setContenido(contmcbo);
		notmcbo.setDatareplace(dmmcbo);
		notmcbo.setRoutes(hmmcbo);
		Notificar nfymcbo = new Notificar();
		nfymcbo.setOut(notmcbo);
		
		// Maracaibo Bellas Artes
		Servicio svcbamcbo  = new Servicio();
		svcbamcbo.setTimeout(14400000);
		svcbamcbo.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notbamcbo = new NotificacionSalida();
		Map<Integer,Conexion> hmbamcbo = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmbamcbo = new HashMap<Integer,String>();
		hmbamcbo.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmbamcbo.put(new Integer(1),"##NOMBRE##");
		dmbamcbo.put(new Integer(0),"##NUM##");
		Contenido contbamcbo = new ContenidoSMS("Buen dia! QuickPress Bellas Artes le informa que su orden ##NUM## esta lista para ser retirada\nGracias\nLa Gerencia");
		notbamcbo.setContenido(contbamcbo);
		notbamcbo.setDatareplace(dmbamcbo);
		notbamcbo.setRoutes(hmbamcbo);
		Notificar nfybamcbo = new Notificar();
		nfybamcbo.setOut(notbamcbo);
		
		//Cumpleanos Maracaibo I
		
		Servicio svccumpleanosmcbo = new Servicio();
		svccumpleanosmcbo.setTimeout(14400000);
		svccumpleanosmcbo.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanosmcbo = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanosmcbo = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanosmcbo = new HashMap<Integer,String>();
		hmcumpleanosmcbo.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanosmcbo.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanosmcbo = new ContenidoSMS("Quickpress Canta Claro lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanosmcbo.setContenido(contcumpleanosmcbo);
		notcumpleanosmcbo.setDatareplace(dmcumpleanosmcbo);
		notcumpleanosmcbo.setRoutes(hmcumpleanosmcbo);
		Notificar nfycumpleanosmcbo = new Notificar();
		nfycumpleanosmcbo.setOut(notcumpleanosmcbo);

		
		//Cumpleanos CCCT
		
		Servicio svccumpleanos = new Servicio();
		svccumpleanos.setTimeout(14400000);
		svccumpleanos.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanos = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanos = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanos = new HashMap<Integer,String>();
		hmcumpleanos.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanos.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanos = new ContenidoSMS("Quickpress CCCT lo felicita en el mes de su cumpleaÃ±os, ademas le regalamos un 5% descuento en todos nuestros servicios!");
		notcumpleanos.setContenido(contcumpleanos);
		notcumpleanos.setDatareplace(dmcumpleanos);
		notcumpleanos.setRoutes(hmcumpleanos);
		Notificar nfycumpleanos = new Notificar();
		nfycumpleanos.setOut(notcumpleanos);
		
		//Cumpleanos Cumana
		
		Servicio svccumplecumana = new Servicio();
		svccumplecumana.setTimeout(14400000);
		svccumplecumana.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumplecumana = new NotificacionSalida();
		Map<Integer,Conexion> hmcumplecumana = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumplecumana = new HashMap<Integer,String>();
		hmcumplecumana.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumplecumana.put(new Integer(0),"##NOMBRE##");
		Contenido contcumplecumana = new ContenidoSMS("Quickpress Cumana lo felicita en el mes de su cumpleanos, ademas le regalamos un 5% descuento en todos nuestros servicios!");
		notcumplecumana.setContenido(contcumplecumana);
		notcumplecumana.setDatareplace(dmcumplecumana);
		notcumplecumana.setRoutes(hmcumplecumana);
		Notificar nfycumplecumana = new Notificar();
		nfycumplecumana.setOut(notcumplecumana);
		
		//Cumpleanos Paraiso
		
		Servicio svccumpleanosparaiso = new Servicio();
		svccumpleanosparaiso.setTimeout(14400000);
		svccumpleanosparaiso.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanosparaiso = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanosparaiso = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanosparaiso = new HashMap<Integer,String>();
		hmcumpleanosparaiso.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanosparaiso.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanosparaiso = new ContenidoSMS("Quickpress El Paraiso lo felicita en el mes de su cumpleano, ademas le regalamos un 10% descuento en todos nuestros servicios!");
		notcumpleanosparaiso.setContenido(contcumpleanosparaiso);
		notcumpleanosparaiso.setDatareplace(dmcumpleanosparaiso);
		notcumpleanosparaiso.setRoutes(hmcumpleanosparaiso);
		Notificar nfycumpleanosparaiso = new Notificar();
		nfycumpleanosparaiso.setOut(notcumpleanosparaiso);
		
		//CUmplea nos LN
		Servicio svccumpleanosln = new Servicio();
		svccumpleanosln.setTimeout(14400000);
		svccumpleanosln.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanosln = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanosln = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanosln = new HashMap<Integer,String>();
		hmcumpleanosln.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanosln.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanosln = new ContenidoSMS("Buen Dia! QuickPress Los Naranjos lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanosln.setContenido(contcumpleanosln);
		notcumpleanosln.setDatareplace(dmcumpleanosln);
		notcumpleanosln.setRoutes(hmcumpleanosln);
		Notificar nfycumpleanosln = new Notificar();
		nfycumpleanosln.setOut(notcumpleanosln);
		
		
		//CUmplea nos Rosal
		Servicio svccumpleanoser = new Servicio();
		svccumpleanoser.setTimeout(14400000);
		svccumpleanoser.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanoser = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanoser = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanoser = new HashMap<Integer,String>();
		hmcumpleanoser.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanoser.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanoser = new ContenidoSMS("Buen Dia! QuickPress El Rosal lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanoser.setContenido(contcumpleanoser);
		notcumpleanoser.setDatareplace(dmcumpleanoser);
		notcumpleanoser.setRoutes(hmcumpleanoser);
		Notificar nfycumpleanoser = new Notificar();
		nfycumpleanoser.setOut(notcumpleanoser);
		
		//CUmplea nos EM
		Servicio svccumpleanosem = new Servicio();
		svccumpleanosem.setTimeout(14400000);
		svccumpleanosem.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanosem = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanosem = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanosem = new HashMap<Integer,String>();
		hmcumpleanosem.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanosem.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanosem = new ContenidoSMS("Buen Dia! QuickPress El Marques lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanosem.setContenido(contcumpleanosem);
		notcumpleanosem.setDatareplace(dmcumpleanosem);
		notcumpleanosem.setRoutes(hmcumpleanosem);
		Notificar nfycumpleanosem = new Notificar();
		nfycumpleanosem.setOut(notcumpleanosem);
		
		//CUmplea nos LB
		Servicio svccumpleanoslb = new Servicio();
		svccumpleanoslb.setTimeout(14400000);
		svccumpleanoslb.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanoslb = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanoslb = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanoslb = new HashMap<Integer,String>();
		hmcumpleanoslb.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanoslb.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanoslb = new ContenidoSMS("Buen Dia! QuickPress La Boyera lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanoslb.setContenido(contcumpleanoslb);
		notcumpleanoslb.setDatareplace(dmcumpleanoslb);
		notcumpleanoslb.setRoutes(hmcumpleanoslb);
		Notificar nfycumpleanoslb = new Notificar();
		nfycumpleanoslb.setOut(notcumpleanoslb);
		
		
		//CUmplea nos LD
		Servicio svccumpleanosld = new Servicio();
		svccumpleanosld.setTimeout(14400000);
		svccumpleanosld.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanosld = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanosld = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanosld = new HashMap<Integer,String>();
		hmcumpleanosld.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanosld.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanosld = new ContenidoSMS("Buen Dia! QuickPress Las Delicias lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanosld.setContenido(contcumpleanosld);
		notcumpleanosld.setDatareplace(dmcumpleanosld);
		notcumpleanosld.setRoutes(hmcumpleanosld);
		Notificar nfycumpleanosld = new Notificar();
		nfycumpleanosld.setOut(notcumpleanosld);
		
		
		//CUmplea nos SB
		Servicio svccumpleanossb = new Servicio();
		svccumpleanossb.setTimeout(14400000);
		svccumpleanossb.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanossb = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanossb = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanossb = new HashMap<Integer,String>();
		hmcumpleanossb.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanossb.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanossb = new ContenidoSMS("Buen Dia QuickPress San Bernardino lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanossb.setContenido(contcumpleanossb);
		notcumpleanossb.setDatareplace(dmcumpleanossb);
		notcumpleanossb.setRoutes(hmcumpleanossb);
		Notificar nfycumpleanossb = new Notificar();
		nfycumpleanossb.setOut(notcumpleanossb);
		
		
//		Cumplea nos SB2
		Servicio svccumpleanossb2 = new Servicio();
		svccumpleanossb2.setTimeout(14400000);
		svccumpleanossb2.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanossb2 = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanossb2 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanossb2 = new HashMap<Integer,String>();
		hmcumpleanossb2.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanossb2.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanossb2 = new ContenidoSMS("Buen Dia QuickPress San Bernardino lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanossb2.setContenido(contcumpleanossb2);
		notcumpleanossb2.setDatareplace(dmcumpleanossb2);
		notcumpleanossb2.setRoutes(hmcumpleanossb2);
		Notificar nfycumpleanossb2 = new Notificar();
		nfycumpleanossb2.setOut(notcumpleanossb2);
		
		// Cumpleanos Valet Service
		Servicio svccumpleanosvs = new Servicio();
		svccumpleanosvs.setTimeout(14400000);
		svccumpleanosvs.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanosvs = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanosvs = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanosvs = new HashMap<Integer,String>();
		hmcumpleanosvs.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanosvs.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanosvs = new ContenidoSMS("Buen Dia! QuickPress ValetService El Marques lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanosvs.setContenido(contcumpleanosvs);
		notcumpleanosvs.setDatareplace(dmcumpleanosvs);
		notcumpleanosvs.setRoutes(hmcumpleanosvs);
		Notificar nfycumpleanosvs = new Notificar();
		nfycumpleanosvs.setOut(notcumpleanosvs);
		
		// Cumpleanos Prados del Este
		Servicio svccumpleanospde = new Servicio();
		svccumpleanospde.setTimeout(14400000);
		svccumpleanospde.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcumpleanospde = new NotificacionSalida();
		Map<Integer,Conexion> hmcumpleanospde = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcumpleanospde = new HashMap<Integer,String>();
		hmcumpleanospde.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcumpleanospde.put(new Integer(0),"##NOMBRE##");
		Contenido contcumpleanospde = new ContenidoSMS("Buen Dia QuickPress Prados del Este lo felicita en el mes de su cumpleanos y le obsequia 10% de descuento en todos sus servicios durante este mes");
		notcumpleanospde.setContenido(contcumpleanospde);
		notcumpleanospde.setDatareplace(dmcumpleanospde);
		notcumpleanospde.setRoutes(hmcumpleanospde);
		Notificar nfycumpleanospde = new Notificar();
		nfycumpleanospde.setOut(notcumpleanospde);
		
		
		
		
		
		
		
		// Respuestas genericas
		Responder resp = new Responder();
		resp.setResp("Gracias por su comentario, con gusto lo tomaremos en cuenta\nQuickPress");
		Responder respeq = new Responder();
		respeq.setResp("Disculpe la molestia, pronto actualizaremos nuestros datos\nGracias\nQuick Press");
		Responder respdo = new Responder();
		respdo.setResp("Para solicitar el servicio a domicilio comuniquese con el 0212-8300107\nGracias\nQuickPress");
		Responder respquien = new Responder();
		respquien.setResp("Le estamos contactado desde la tintoreria Quick Press para notificarle que su prenda esta lista.\nGracias");
		
		
		//Palabras Claves
		String domicilio,horario,equivocado,quien;
		domicilio = "(?i)casa|oficina|domicili|apartamento|trae";
		horario = "(?i)hora|abre|abiert|trabaja|corrido|hasta|cerra|cierra";
		equivocado = "(?i)error|equi[vb]o[ck]|confund";
		quien = "(?i)quien";

		//Respuestas casona
		NotificacionEntrada horariocasona= new NotificacionEntrada();
		
		
		Responder reshoracasona= new Responder();
		reshoracasona.setResp("QuickPress La Casona II: Lunes a Sabado de 8am a 8pm. Domingos de 10am a 5pm\nGracias\nCarmen Hernandez\nGerente");
		
		
		HashMap<String,Comando> cmhorariocasona= new HashMap<String,Comando>();
		
		cmhorariocasona.put(equivocado,respeq);
		cmhorariocasona.put(quien,respquien);
		cmhorariocasona.put(horario,reshoracasona);
		
		horariocasona.setTransitions(cmhorariocasona);
		horariocasona.setDef(null);
		TransicionRegex trcasona = new TransicionRegex();
		trcasona.setIn(horariocasona);
		nfycasona.setTransicion(trcasona);

//		Respuestas trinidad
		NotificacionEntrada horariotrinidad= new NotificacionEntrada();
		
		
		Responder reshoratrinidad= new Responder();
		reshoratrinidad.setResp("Quick Press La Trinidad: Lunes a Sabado de 7am a 7pm\nGracias\nLuis Sequin\nGerente");
		
		
		HashMap<String,Comando> cmhorariotrinidad= new HashMap<String,Comando>();
	
		cmhorariotrinidad.put(equivocado,respeq);
		cmhorariotrinidad.put(quien,respquien);
		cmhorariotrinidad.put(horario,reshoratrinidad);
		
		horariotrinidad.setTransitions(cmhorariotrinidad);
		horariotrinidad.setDef(null);
		TransicionRegex trtrinidad = new TransicionRegex();
		trtrinidad.setIn(horariotrinidad);
		nfytrinidad.setTransicion(trtrinidad);

//		Respuestas GAMA PLUS
		NotificacionEntrada horariogamaplus= new NotificacionEntrada();
		
		
		Responder reshoragamaplus= new Responder();
		reshoragamaplus.setResp("Quick Press Gama: Lunes a Doming de 8am a 7pm\nGracias\nSergio Pinto\nGerente");
		
		
		HashMap<String,Comando> cmhorariogamaplus= new HashMap<String,Comando>();
	
		cmhorariogamaplus.put(equivocado,respeq);
		cmhorariogamaplus.put(quien,respquien);
		cmhorariogamaplus.put(horario,reshoragamaplus);
		
		horariogamaplus.setTransitions(cmhorariogamaplus);
		horariogamaplus.setDef(null);
		TransicionRegex trgamaplus = new TransicionRegex();
		trgamaplus.setIn(horariogamaplus);
		nfygamaplus.setTransicion(trgamaplus);
		
//		Respuestas urbina
		NotificacionEntrada horariourbina= new NotificacionEntrada();
		
		
		Responder reshoraurbina= new Responder();
		reshoraurbina.setResp("Quick Press La Urbina: Lunes a Sabado de 7am a 7pm, Domingo 8am a 2pm\nGracias\nLuis Sequin\nGerente");
		
		
		HashMap<String,Comando> cmhorariourbina= new HashMap<String,Comando>();
	
		cmhorariourbina.put(equivocado,respeq);
		cmhorariourbina.put(quien,respquien);
		cmhorariourbina.put(horario,reshoraurbina);
		
		horariourbina.setTransitions(cmhorariourbina);
		horariourbina.setDef(null);
		TransicionRegex trurbina = new TransicionRegex();
		trurbina.setIn(horariourbina);
		nfyurbina.setTransicion(trurbina);
		
		//Respuestas 4 d mayo
		
		NotificacionEntrada horario4dm= new NotificacionEntrada();
		
		
		Responder reshora4dm= new Responder();
		reshora4dm.setResp("QuickPress Av. 4 de Mayo: Lunes a Sabado de 7am a 7pm\nGracias\nAntonio Gomez\nEncargado");
		
		
		HashMap<String,Comando> cmhorario4dm= new HashMap<String,Comando>();
	
		cmhorario4dm.put(equivocado,respeq);
		cmhorario4dm.put(quien,respquien);
		cmhorario4dm.put(horario,reshora4dm);
		
		horario4dm.setTransitions(cmhorario4dm);
		horario4dm.setDef(null);
		TransicionRegex tr4dm = new TransicionRegex();
		tr4dm.setIn(horario4dm);
		nfy4dm.setTransicion(tr4dm);

		//Respuestas El valle
		
		NotificacionEntrada horarioev= new NotificacionEntrada();
		
		
		Responder reshoraev= new Responder();
		reshoraev.setResp("QuickPress El Valle: Lunes a Sabado de 7am a 7pm\nGracias");
		
		
		HashMap<String,Comando> cmhorarioev= new HashMap<String,Comando>();
	
		cmhorarioev.put(equivocado,respeq);
		cmhorarioev.put(quien,respquien);
		cmhorarioev.put(horario,reshoraev);
		
		horarioev.setTransitions(cmhorarioev);
		horarioev.setDef(null);
		TransicionRegex trev = new TransicionRegex();
		trev.setIn(horarioev);
		nfyev.setTransicion(trev);
		
		//Respuestas C.C. La Redoma
		
		NotificacionEntrada horariocclr= new NotificacionEntrada();
		
		
		Responder reshoracclr= new Responder();
		reshoracclr.setResp("QuickPress C.C. La Redoma: Lunes a Sabado de 8:00 AM a 8:00 PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariocclr= new HashMap<String,Comando>();
	
		cmhorariocclr.put(equivocado,respeq);
		cmhorariocclr.put(quien,respquien);
		cmhorariocclr.put(horario,reshoracclr);
		
		horariocclr.setTransitions(cmhorariocclr);
		horariocclr.setDef(null);
		TransicionRegex trcclr = new TransicionRegex();
		trcclr.setIn(horariocclr);
		nfycclr.setTransicion(trcclr);
		
	//Respuestas Playa El Angel
		
		NotificacionEntrada horariopea= new NotificacionEntrada();
		
		
		Responder reshorapea= new Responder();
		reshorapea.setResp("QuickPress C.C. La Redoma: Lunes a Sabado de 8:00 AM a 8:00 PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariopea= new HashMap<String,Comando>();
	
		cmhorariopea.put(equivocado,respeq);
		cmhorariopea.put(quien,respquien);
		cmhorariopea.put(horario,reshorapea);
		
		horariopea.setTransitions(cmhorariopea);
		horariopea.setDef(null);
		TransicionRegex trpea = new TransicionRegex();
		trpea.setIn(horariopea);
		nfypea.setTransicion(trpea);
		
//Respuestas San Cristobal I 
		
		NotificacionEntrada horariosc1= new NotificacionEntrada();
		
		
		Responder reshorasc1= new Responder();
		reshorasc1.setResp("QuickPress Av. Ferrero Tamayo: Lunes a Sabado de 7:30 AM a 7:00 PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariosc1= new HashMap<String,Comando>();
	
		cmhorariosc1.put(equivocado,respeq);
		cmhorariosc1.put(quien,respquien);
		cmhorariosc1.put(horario,reshorasc1);
		
		horariosc1.setTransitions(cmhorariosc1);
		horariosc1.setDef(null);
		TransicionRegex trsc1 = new TransicionRegex();
		trsc1.setIn(horariosc1);
		nfysc1.setTransicion(trsc1);
		
		
//Respuestas San Cristobal II 
		
		NotificacionEntrada horariosc2= new NotificacionEntrada();
		
		
		Responder reshorasc2= new Responder();
		reshorasc2.setResp("QuickPress Sambil San Cristobal: Lunes a Sabado de 10:00 AM a 9:00 PM. Domingos de 12:00 M a 8:00PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariosc2= new HashMap<String,Comando>();
	
		cmhorariosc2.put(equivocado,respeq);
		cmhorariosc2.put(quien,respquien);
		cmhorariosc2.put(horario,reshorasc2);
		
		horariosc2.setTransitions(cmhorariosc2);
		horariosc2.setDef(null);
		TransicionRegex trsc2 = new TransicionRegex();
		trsc2.setIn(horariosc2);
		nfysc2.setTransicion(trsc2);
		
		
			//Respuestas Buenaventura
		
		NotificacionEntrada horariobuena= new NotificacionEntrada();
		
		
		Responder reshorabuena= new Responder();
		reshorabuena.setResp("QuickPress Buenaventura: Lunes a Sabado de 08:00 AM a 8:00 PM. Domingos de 11:00 AM a 6:00PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariobuena= new HashMap<String,Comando>();
	
		cmhorariobuena.put(equivocado,respeq);
		cmhorariobuena.put(quien,respquien);
		cmhorariobuena.put(horario,reshorabuena);
		
		horariobuena.setTransitions(cmhorariobuena);
		horariobuena.setDef(null);
		TransicionRegex trbuena = new TransicionRegex();
		trbuena.setIn(horariobuena);
		nfybuena.setTransicion(trbuena);
		
	//Respuestas SANTAMONICA TIENDA
		
		NotificacionEntrada horariostmon= new NotificacionEntrada();
		
		
		Responder reshorastmon= new Responder();
		reshorastmon.setResp("QuickPress Santa Monica: Lunes a Sabado de 07:00 AM a 6:30 PM.\nGracias");
		
		
		HashMap<String,Comando> cmhorariostmon= new HashMap<String,Comando>();
	
		cmhorariostmon.put(equivocado,respeq);
		cmhorariostmon.put(quien,respquien);
		cmhorariostmon.put(horario,reshorastmon);
		
		horariostmon.setTransitions(cmhorariostmon);
		horariostmon.setDef(null);
		TransicionRegex trstmon = new TransicionRegex();
		trstmon.setIn(horariostmon);
		nfystmon.setTransicion(trstmon);
		
		
//Respuestas Cumana 
		
		NotificacionEntrada horariocumana= new NotificacionEntrada();
		
		
		Responder reshoracumana= new Responder();
		reshoracumana.setResp("QuickPress Cumana: Lunes a Viernes de 07:00 AM a 7:00 PM. Sabados de 8:00 AM a 6:00PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariocumana= new HashMap<String,Comando>();
	
		cmhorariocumana.put(equivocado,respeq);
		cmhorariocumana.put(quien,respquien);
		cmhorariocumana.put(horario,reshoracumana);
		
		horariocumana.setTransitions(cmhorariocumana);
		horariocumana.setDef(null);
		TransicionRegex trcumana = new TransicionRegex();
		trcumana.setIn(horariocumana);
		nfycumana.setTransicion(trcumana);
		
		//Respuestas C.C.Sambil Margarita
		
		NotificacionEntrada horariosm= new NotificacionEntrada();
		
		
		Responder reshorasm= new Responder();
		reshorasm.setResp("QuickPress C.C. Sambil Margarita: Lunes a Sabado de 10:00 AM a 9:00 PM y Domingos de 12:00 M a 8:00 PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariosm= new HashMap<String,Comando>();
	
		cmhorariosm.put(equivocado,respeq);
		cmhorariosm.put(quien,respquien);
		cmhorariosm.put(horario,reshorasm);
		
		horariosm.setTransitions(cmhorariosm);
		horariosm.setDef(null);
		TransicionRegex trsm = new TransicionRegex();
		trsm.setIn(horariosm);
		nfysm.setTransicion(trsm);
		
		//Respuestas Maracaibo I
		
		NotificacionEntrada horariomcbo= new NotificacionEntrada();
		
		
		Responder reshoramcbo= new Responder();
		reshoramcbo.setResp("QuickPress Canta Claro: Lunes a Viernes de 7:00 AM a 7:00 PM, Sabados de 7:00 AM a 5:00 PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariomcbo= new HashMap<String,Comando>();
	
		cmhorariomcbo.put(equivocado,respeq);
		cmhorariomcbo.put(quien,respquien);
		cmhorariomcbo.put(horario,reshoramcbo);
		
		horariomcbo.setTransitions(cmhorariomcbo);
		horariomcbo.setDef(null);
		TransicionRegex trmcbo = new TransicionRegex();
		trmcbo.setIn(horariomcbo);
		nfymcbo.setTransicion(trmcbo);
		
		//Respuestas Maracaibo Bellas Artes 
		
		NotificacionEntrada horariobamcbo= new NotificacionEntrada();
		
		
		Responder reshorabamcbo= new Responder();
		reshorabamcbo.setResp("QuickPress Bellas Artes: Lunes a Viernes de 7:00 AM a 7:00 PM, Sabados de 8:00 AM a 4:00 PM\nGracias");
		
		
		HashMap<String,Comando> cmhorariobamcbo= new HashMap<String,Comando>();
	
		cmhorariobamcbo.put(equivocado,respeq);
		cmhorariobamcbo.put(quien,respquien);
		cmhorariobamcbo.put(horario,reshorabamcbo);
		
		horariobamcbo.setTransitions(cmhorariobamcbo);
		horariobamcbo.setDef(null);
		TransicionRegex trbamcbo = new TransicionRegex();
		trbamcbo.setIn(horariobamcbo);
		nfybamcbo.setTransicion(trbamcbo);
		
//		Respuestas samanes
		NotificacionEntrada horariosamanes= new NotificacionEntrada();
		
		
		Responder reshorasamanes= new Responder();
		reshorasamanes.setResp("Quick Press Los Samanes: Lunes a Sabado de 7am a 8pm. Domingo 8am a 2pm\nGracias\nBrigitte Betancourt\nGerente");
		
		
		HashMap<String,Comando> cmhorariosamanes= new HashMap<String,Comando>();
		
		cmhorariosamanes.put(equivocado,respeq);
		cmhorariosamanes.put(quien,respquien);
		cmhorariosamanes.put(horario,reshorasamanes);
		
		horariosamanes.setTransitions(cmhorariosamanes);
		horariosamanes.setDef(null);
		TransicionRegex trsamanes = new TransicionRegex();
		trsamanes.setIn(horariosamanes);
		nfysamanes.setTransicion(trsamanes);
				
		
//		Respuestas Paraiso
		NotificacionEntrada horarioparaiso= new NotificacionEntrada();
		
		
		Responder reshoraparaiso= new Responder();
		reshoraparaiso.setResp("QuickPress El Paraiso: Lunes a Sabado de 7am a 7pm\nGracias\nMariela Gandica\nGerente");
		
		
		HashMap<String,Comando> cmhorarioparaiso= new HashMap<String,Comando>();
		
		cmhorarioparaiso.put(equivocado,respeq);
		cmhorarioparaiso.put(quien,respquien);
		cmhorarioparaiso.put(horario,reshoraparaiso);
		
		horarioparaiso.setTransitions(cmhorarioparaiso);
		horarioparaiso.setDef(null);
		TransicionRegex trparaiso = new TransicionRegex();
		trparaiso.setIn(horarioparaiso);
		nfyparaiso.setTransicion(trparaiso);
		
		//	Respuestas La Tahona
		NotificacionEntrada horariotahona = new NotificacionEntrada();
		
		
		Responder reshoratahona = new Responder();
		reshoratahona.setResp("Quick Press La Tahona: Lunes a Sabado de 7am a 7pm\nGracias");
		
		
		HashMap<String,Comando> cmhorariotahona = new HashMap<String,Comando>();
		
		cmhorariotahona.put(equivocado,respeq);
		cmhorariotahona.put(quien,respquien);
		cmhorariotahona.put(horario,reshoratahona);
		
		horariotahona.setTransitions(cmhorariotahona);
		horariotahona.setDef(null);
		TransicionRegex trtahona = new TransicionRegex();
		trtahona.setIn(horariotahona);
		nfytahona.setTransicion(trtahona);
		
		//		Respuestas Sta Rosa
		NotificacionEntrada horariosanta = new NotificacionEntrada();
		
		
		Responder reshorasanta = new Responder();
		reshorasanta.setResp("Quick Press Sta. Rosa: Lunes a Sabado de 7am a 7pm\nGracias\nMaria Fernandez\nGerente");
		
		
		HashMap<String,Comando> cmhorariosanta = new HashMap<String,Comando>();
		
		cmhorariosanta.put(equivocado,respeq);
		cmhorariosanta.put(quien,respquien);
		cmhorariosanta.put(horario,reshorasanta);
		
		horariosanta.setTransitions(cmhorariosanta);
		horariosanta.setDef(null);
		TransicionRegex trsanta = new TransicionRegex();
		trsanta.setIn(horariosanta);
		nfysanta.setTransicion(trsanta);
		
		//Respuestas CCCT
		NotificacionEntrada horarioccct = new NotificacionEntrada();
		
		
		Responder reshoraccct = new Responder();
		reshoraccct.setResp("QuickPress C.C.C.T: Lunes a Sabado de 7am a 7pm\nGracias\nMarley Munoz\nGerente");
		
		
		HashMap<String,Comando> cmhorarioccct = new HashMap<String,Comando>();
	
		cmhorarioccct.put(equivocado,respeq);
		cmhorarioccct.put(quien,respquien);
		cmhorarioccct.put(horario,reshoraccct);
		
		horarioccct.setTransitions(cmhorarioccct);
		horarioccct.setDef(null);
		TransicionRegex trccct = new TransicionRegex();
		trccct.setIn(horarioccct);
		nfyccct.setTransicion(trccct);
		

		//Respuestas Sta Monica Valet Service
		NotificacionEntrada horariovssm = new NotificacionEntrada();
		
		
		Responder reshoravssm = new Responder();
		reshoravssm.setResp("QuickPress Santa Monica ValetService: Lunes a Viernes de 7am a 7pm; Sabado 7:30am a 6:35pm\nGracias\nRosa Gonzalez\nGerente");
		
		
		HashMap<String,Comando> cmhorariovssm = new HashMap<String,Comando>();
	
		cmhorariovssm.put(equivocado,respeq);
		cmhorariovssm.put(quien,respquien);
		cmhorariovssm.put(horario,reshoravssm);
		
		horariovssm.setTransitions(cmhorariovssm);
		horariovssm.setDef(resp);
		TransicionRegex trvssm = new TransicionRegex();
		trvssm.setIn(horariovssm);
		nfyvssm.setTransicion(trvssm);
		
		
		//Respuestas Las merecedes
		NotificacionEntrada horariocm = new NotificacionEntrada();
		
		
		Responder reshoracm = new Responder();
		reshoracm.setResp("QuickPress Las Mercedes: Lunes a SÃ¡bado de 7am a 7pm \nGracias\nDennys Rechider\nGerente");
		
		
		
		HashMap<String,Comando> cmhorariocm = new HashMap<String,Comando>();
		
		cmhorariocm.put(equivocado,respeq);
		cmhorariocm.put(quien,respquien);
		cmhorariocm.put(horario,reshoracm);
		
		horariocm.setTransitions(cmhorariocm);
		horariocm.setDef(resp);
		TransicionRegex trcm = new TransicionRegex();
		trcm.setIn(horariocm);
		nfycm.setTransicion(trcm);
		
		
	    //El respuestas marques
		NotificacionEntrada horarioem = new NotificacionEntrada();
		
	
		Responder reshoraem = new Responder();
		reshoraem.setResp("QuickPress El Marques: LUNES A SABADO DE 7 00 AM A 8 00 PM Y DOMINGOS DE 1 PM A 7 PM\nGracias\nIrina Ramos\nGerente");
		
		
		
		HashMap<String,Comando> cmhorarioem = new HashMap<String,Comando>();
		cmhorarioem.put(domicilio,respdo);
		cmhorarioem.put(horario,reshoraem);
		cmhorarioem.put(equivocado,respeq);
		cmhorarioem.put(quien,respquien);
		
		
		
		horarioem.setTransitions(cmhorarioem);
		horarioem.setDef(null);
		TransicionRegex trem = new TransicionRegex();
		trem.setIn(horarioem);
		nfy.setTransicion(trem);
		
		NotificacionEntrada horariovs = new NotificacionEntrada();
		
		// Respuetsa El MArques ValetService
		Responder reshoravs = new Responder();
		reshoravs.setResp("QuickPress ValetService El Marques: LUNES A SABADO DE 7 00 AM A 8 00 PM Y DOMINGOS DE 1 PM A 7 PM\nGracias\nElvis Canizales\nGerente");
		
		
		
		HashMap<String,Comando> cmhorariovs = new HashMap<String,Comando>();
		cmhorariovs.put(domicilio,respdo);
		cmhorariovs.put(horario,reshoravs);
		cmhorariovs.put(equivocado,respeq);
		cmhorariovs.put(quien,respquien);
		
		
		
		horariovs.setTransitions(cmhorariovs);
		horariovs.setDef(null);
		TransicionRegex trvs = new TransicionRegex();
		trvs.setIn(horariovs);
		nfyvs.setTransicion(trvs);
		
		
	    //El respuestas los naranjos
		NotificacionEntrada horarioln = new NotificacionEntrada();
		
	
		Responder reshoraln = new Responder();
		reshoraln.setResp("QuickPress Los Naranjos: LUNES A SABADO DE 7 00 AM A 8 00 PM Y DOMINGOS DE 9 00 AM A 2 00 PM\nChelimar Marquez\nGerente");
		
		
		HashMap<String,Comando> cmhorarioln = new HashMap<String,Comando>();
		cmhorarioln.put(domicilio,respdo);
		cmhorarioln.put(horario,reshoraln);
		cmhorarioln.put(equivocado,respeq);
		cmhorarioln.put(quien,respquien);
		
		
		
		horarioln.setTransitions(cmhorarioln);
		horarioln.setDef(null);
		TransicionRegex trln = new TransicionRegex();
		trln.setIn(horarioln);
		nfyln.setTransicion(trln);
		
		
		
	    //El respuestas el rosal
		NotificacionEntrada horarioer = new NotificacionEntrada();
		
	
		Responder reshoraer = new Responder();
		reshoraer.setResp("QuickPress El Rosal: LUNES A SABADO DE 7 00 AM A 7 00 PM Y DOMINGOS DE 9 00 AM A 2 00 PM\nCarlos Bello\nGerente");
		
		
		HashMap<String,Comando> cmhorarioer = new HashMap<String,Comando>();
		cmhorarioer.put(domicilio,respdo);
		cmhorarioer.put(horario,reshoraer);
		cmhorarioer.put(equivocado,respeq);
		cmhorarioer.put(quien,respquien);
		
		
		
		horarioer.setTransitions(cmhorarioer);
		horarioer.setDef(null);
		TransicionRegex trer = new TransicionRegex();
		trer.setIn(horarioer);
		nfyer.setTransicion(trer);
		
	    //El respuestas prados del este
		NotificacionEntrada horariopde = new NotificacionEntrada();
		
	
		Responder reshorapde = new Responder();
		reshorapde.setResp("QuickPress Prados del Este: LUNES A SABADO DE 7 00 AM A 7 00 PM Y DOMINGOS DE 8 00 AM A 3 00 PM\nValerie Aguilera\nGerente");
		
		
		HashMap<String,Comando> cmhorariopde = new HashMap<String,Comando>();
		cmhorariopde.put(domicilio,respdo);
		cmhorariopde.put(horario,reshorapde);
		cmhorariopde.put(equivocado,respeq);
		cmhorariopde.put(quien,respquien);
		//cmhorariopde.put(".*",resp);
		
		
		horariopde.setTransitions(cmhorariopde);
		horariopde.setDef(null);
		TransicionRegex trpde = new TransicionRegex();
		trpde.setIn(horariopde);
		nfypde.setTransicion(trpde);
		
		
	    //El respuestas la boyera
		NotificacionEntrada horariolb = new NotificacionEntrada();
		
	
		Responder reshoralb = new Responder();
		reshoralb.setResp("QuickPress La Boyera: LUNES A SABADO DE 8 30 AM A 7 00 PM\nSantiago Matheus\nGerente");
		
		
		HashMap<String,Comando> cmhorariolb = new HashMap<String,Comando>();
		cmhorariolb.put(domicilio,respdo);
		cmhorariolb.put(horario,reshoralb);
		cmhorariolb.put(equivocado,respeq);
		cmhorariolb.put(quien,respquien);

		
		
		horariolb.setTransitions(cmhorariolb);
		horariolb.setDef(null);
		TransicionRegex trlb = new TransicionRegex();
		trlb.setIn(horariolb);
		nfylb.setTransicion(trlb);
		
		
	    //El respuestas las delicias
		NotificacionEntrada horariold = new NotificacionEntrada();
		
	
		Responder reshorald = new Responder();
		reshorald.setResp("QuickPress Las Delicias: LUNES A SABADO DE 8 00 AM A 6 00 PM\nCarlos Bello\nGerente");
		
		
		HashMap<String,Comando> cmhorariold = new HashMap<String,Comando>();
		cmhorariold.put(domicilio,respdo);
		cmhorariold.put(horario,reshorald);
		cmhorariold.put(equivocado,respeq);
		cmhorariold.put(quien,respquien);

		
		
		horariold.setTransitions(cmhorariold);
		horariold.setDef(null);
		TransicionRegex trld = new TransicionRegex();
		trld.setIn(horariold);
		nfyld.setTransicion(trld);
		
		
	    //El respuestas san bernardino
		NotificacionEntrada horariosb = new NotificacionEntrada();
		
	
		Responder reshorasb = new Responder();
		reshorasb.setResp("Quickpress San Bernardino I:  LUNES A SÃ�BADO DE 8 00 AM A 6 00 PM\nJanet Manrique\nGerente");
		
		
		HashMap<String,Comando> cmhorariosb = new HashMap<String,Comando>();
		cmhorariosb.put(domicilio,respdo);
		cmhorariosb.put(horario,reshorasb);
		cmhorariosb.put(equivocado,respeq);
		cmhorariosb.put(quien,respquien);

		
		horariosb.setTransitions(cmhorariosb);
		horariosb.setDef(null);
		TransicionRegex trsb = new TransicionRegex();
		trsb.setIn(horariosb);
		nfysb.setTransicion(trsb);
		
		  //El respuestas san bernardino 2
		NotificacionEntrada horariosb2 = new NotificacionEntrada();
		
	
		Responder reshorasb2 = new Responder();
		reshorasb2.setResp("Quickpress San Bernardino II:  LUNES A SÃ�BADO DE 8 00 AM A 6 00 PM\nIvan Laguado\nGerente");
		
		
		HashMap<String,Comando> cmhorariosb2 = new HashMap<String,Comando>();
		cmhorariosb2.put(domicilio,respdo);
		cmhorariosb2.put(horario,reshorasb2);
		cmhorariosb2.put(equivocado,respeq);
		cmhorariosb2.put(quien,respquien);

		
		horariosb2.setTransitions(cmhorariosb2);
		horariosb2.setDef(null);
		TransicionRegex trsb2 = new TransicionRegex();
		trsb.setIn(horariosb2);
		nfysb.setTransicion(trsb2);

		//Servicio Camuri Estado de Cuenta
		Servicio svcedocta = new Servicio();
		svcedocta.setTimeout(14400000);
		svcedocta.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notedocta = new NotificacionSalida();
		Map<Integer,Conexion> hmedocta = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmedocta = new HashMap<Integer,String>();
		hmedocta.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmedocta.put(new Integer(0),"##TIPO##");
		dmedocta.put(new Integer(1),"##ACCION##");
		dmedocta.put(new Integer(2),"##SALDO##");
		Contenido contedocta = new ContenidoSMS("Estimado Socio El Club Camuri Grande informa que su accion ##TIPO##-##ACCION## presenta una saldo de Bs. ##SALDO##.\n Gracias Cobranzas.");
		notedocta.setContenido(contedocta);
		notedocta.setDatareplace(dmedocta);
		notedocta.setRoutes(hmedocta);
		Notificar nfyedocta = new Notificar();
		nfyedocta.setOut(notedocta);

		NotificacionEntrada entedocta = new NotificacionEntrada();
		
		Responder respedocta = new Responder();
		respedocta.setResp("Para mayor informacion comuniquese por los tlfs: 7538520/7538568 Ã³ ingrese a nuestra pagina web www.clubcamuri.com \nGracias");

		
		entedocta.setTransitions(new HashMap<String, Comando>());
		entedocta.setDef(respedocta);
		
		TransicionRegex tredocta = new TransicionRegex();
		tredocta.setIn(entedocta);
		nfyedocta.setTransicion(tredocta);
		
//		Servicio LCC Estado de Cuenta
		Servicio svcedoctalcc = new Servicio();
		svcedoctalcc.setTimeout(14400000);
		svcedoctalcc.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notedoctalcc = new NotificacionSalida();
		Map<Integer,Conexion> hmedoctalcc = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmedoctalcc = new HashMap<Integer,String>();
		hmedoctalcc.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmedoctalcc.put(new Integer(0),"##ACCION##");
		dmedoctalcc.put(new Integer(1),"##SALDO##");
		Contenido contedoctalcc = new ContenidoSMS("Estimado miembro: Lagunita Country Club le informa que el saldo a la fecha de su cuenta de participacion N.##ACCION## es de Bs. ##SALDO##");
		notedoctalcc.setContenido(contedoctalcc);
		notedoctalcc.setDatareplace(dmedoctalcc);
		notedoctalcc.setRoutes(hmedoctalcc);
		Notificar nfyedoctalcc = new Notificar();
		nfyedoctalcc.setOut(notedoctalcc);

		NotificacionEntrada entedoctalcc = new NotificacionEntrada();
		
		Responder respedoctalcc = new Responder();
		respedoctalcc.setResp("Para mayor informacion comuniquese por los tlfs: 9061000 / 9061027 Ã³ ingrese a nuestra pagina web www.clublagunita.com \nGracias");

		
		entedoctalcc.setTransitions(new HashMap<String, Comando>());
		entedoctalcc.setDef(respedoctalcc);
		
		TransicionRegex tredoctalcc = new TransicionRegex();
		tredoctalcc.setIn(entedoctalcc);
		nfyedoctalcc.setTransicion(tredoctalcc);
		
		//Servicio Playa Azul Estado de Cuenta
		Servicio svcedoctapa = new Servicio();
		svcedoctapa.setTimeout(14400000);
		svcedoctapa.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notedoctapa = new NotificacionSalida();
		Map<Integer,Conexion> hmedoctapa = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmedoctapa = new HashMap<Integer,String>();
		hmedoctapa.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmedoctapa.put(new Integer(0),"##ACCION##");
		dmedoctapa.put(new Integer(1),"##CTA##");
		dmedoctapa.put(new Integer(2),"##SALDO##");
		dmedoctapa.put(new Integer(3),"##MES##");
		Contenido contedoctapa = new ContenidoSMS("Playa Azul le informa que su factura (Accion ##ACCION##) de ##MES## es por Bs. ##SALDO##");
		notedoctapa.setContenido(contedoctapa);
		notedoctapa.setDatareplace(dmedoctapa);
		notedoctapa.setRoutes(hmedoctapa);
		Notificar nfyedoctapa = new Notificar();
		nfyedoctapa.setOut(notedoctapa);

		NotificacionEntrada entedoctapa = new NotificacionEntrada();
		
		Responder respedoctapa = new Responder();
		respedoctapa.setResp("Para mayor informacion comuniquese por los tlfs: 9517616 o ingrese a nuestra pagina web www.clubplayaazul.com.ve \nGracias");

		
		entedoctapa.setTransitions(new HashMap<String, Comando>());
		entedoctapa.setDef(respedoctapa);
		
		TransicionRegex tredoctapa = new TransicionRegex();
		tredoctapa.setIn(entedoctapa);
		nfyedoctapa.setTransicion(tredoctapa);
		
		
		
		
		//Armar los servicios y agregarlos a la lista
		
		svc.setRootcmd(nfy);
		svc.setNombre("prendalista");
		
		svcvs.setRootcmd(nfyvs);
		svcvs.setNombre("prendalistavs");
		
		svcln.setRootcmd(nfyln);
		svcln.setNombre("prendalistaln");
		
		svclb.setRootcmd(nfylb);
		svclb.setNombre("prendalistalb");
		
		svcer.setRootcmd(nfyer);
		svcer.setNombre("prendalistaer");
		
		svcpde.setRootcmd(nfypde);
		svcpde.setNombre("prendalistapde");
		
		svcld.setRootcmd(nfyld);
		svcld.setNombre("prendalistald");
		
		svcsb.setRootcmd(nfysb);
		svcsb.setNombre("prendalistasb");
		
		svcsb2.setRootcmd(nfysb2);
		svcsb2.setNombre("prendalistasb2");
		
		svcvssm.setRootcmd(nfyvssm);
		svcvssm.setNombre("prendalistasmvs");
		
		svccm.setRootcmd(nfycm);
		svccm.setNombre("prendalistacm");
		
		svcsc1.setRootcmd(nfysc1);
		svcsc1.setNombre("prendalistasc1");
		
		svcsc2.setRootcmd(nfysc2);
		svcsc2.setNombre("prendalistasc2");
		
		svccumana.setRootcmd(nfycumana);
		svccumana.setNombre("prendalistacumana");
		
		svcbuena.setRootcmd(nfybuena);
		svcbuena.setNombre("prendalistabuena");
		
		svcstmon.setRootcmd(nfystmon);
		svcstmon.setNombre("prendalistastmon");
		
		svcccct.setRootcmd(nfyccct);
		svcccct.setNombre("prendalistaccct");
		
		svctahona.setRootcmd(nfytahona);
		svctahona.setNombre("prendalistatahona");
		
		svcsanta.setRootcmd(nfysanta);
		svcsanta.setNombre("prendalistasanta");
		
		svcparaiso.setRootcmd(nfyparaiso);
		svcparaiso.setNombre("prendalistaparaiso");
		
		svclf.setRootcmd(nfylf);
		svclf.setNombre("prendalistalf");
		
		svccasona.setRootcmd(nfycasona);
		svccasona.setNombre("prendalistacasona");
		
		svcgamaplus.setRootcmd(nfygamaplus);
		svcgamaplus.setNombre("prendalistagamaplus");

		svctrinidad.setRootcmd(nfytrinidad);
		svctrinidad.setNombre("prendalistalt");
		
		svcurbina.setRootcmd(nfyurbina);
		svcurbina.setNombre("prendalistaurbina");
		
		svc4dm.setRootcmd(nfy4dm);
		svc4dm.setNombre("prendalista4dm");
		
		svcev.setRootcmd(nfyev);
		svcev.setNombre("prendalistaev");
		
		svcmcbo.setRootcmd(nfymcbo);
		svcmcbo.setNombre("prendalistamcbo");
		
		svcbamcbo.setRootcmd(nfybamcbo);
		svcbamcbo.setNombre("prendalistabamcbo");
		
		svccclr.setRootcmd(nfycclr);
		svccclr.setNombre("prendalistacclr");
		
		svcpea.setRootcmd(nfypea);
		svcpea.setNombre("prendalistapea");
		
		
		svcsm.setRootcmd(nfysm);
		svcsm.setNombre("prendalistasm");

		svcsamanes.setRootcmd(nfysamanes);
		svcsamanes.setNombre("prendalistals");
		
		svccumpleanos.setRootcmd(nfycumpleanos);
		svccumpleanos.setNombre("cumpleanos");
		
		svccumplecumana.setRootcmd(nfycumplecumana);
		svccumplecumana.setNombre("cumpleanoscumana");
		
		svccumpleanosparaiso.setRootcmd(nfycumpleanosparaiso);
		svccumpleanosparaiso.setNombre("cumpleanosparaiso");
		
		svccumpleanosln.setRootcmd(nfycumpleanosln);
		svccumpleanosln.setNombre("cumpleanosln");
		
		svccumpleanosem.setRootcmd(nfycumpleanosem);
		svccumpleanosem.setNombre("cumpleanosem");
		
		//svccumpleanoser.setRootcmd(nfycumpleanoser);
		//svccumpleanoser.setNombre("cumpleanoser");
		
		svccumpleanosld.setRootcmd(nfycumpleanosld);
		svccumpleanosld.setNombre("cumpleanosld");
		
		svccumpleanoslb.setRootcmd(nfycumpleanoslb);
		svccumpleanoslb.setNombre("cumpleanoslb");
		
		svccumpleanossb.setRootcmd(nfycumpleanossb);
		svccumpleanossb.setNombre("cumpleanossb");
		
		svccumpleanossb2.setRootcmd(nfycumpleanossb2);
		svccumpleanossb2.setNombre("cumpleanossb2");

		svccumpleanosvs.setRootcmd(nfycumpleanosvs);
		svccumpleanosvs.setNombre("cumpleanosvs");
		
		svccumpleanospde.setRootcmd(nfycumpleanospde);
		svccumpleanospde.setNombre("cumpleanospde");
		
		
		svcedocta.setRootcmd(nfyedocta);
		svcedocta.setNombre("estado_de_cuenta");
		
		svcedoctalcc.setRootcmd(nfyedoctalcc);
		svcedoctalcc.setNombre("estado_de_cuenta_lcc");
		
		svcedoctapa.setRootcmd(nfyedoctapa);
		svcedoctapa.setNombre("estado_de_cuenta_pa");
		
		
		servicios_activos.put(svc.getNombre(),svc);
		servicios_activos.put(svcvs.getNombre(),svcvs);
		servicios_activos.put(svcln.getNombre(),svcln);
		servicios_activos.put(svclb.getNombre(),svclb);
		servicios_activos.put(svcpde.getNombre(),svcpde);
		servicios_activos.put(svcer.getNombre(),svcer);
		servicios_activos.put(svcld.getNombre(),svcld);
		servicios_activos.put(svcsb.getNombre(),svcsb);
		servicios_activos.put(svcsb2.getNombre(),svcsb2);
		servicios_activos.put(svcvs.getNombre(),svcvs);
		servicios_activos.put(svcvssm.getNombre(),svcvssm);
		servicios_activos.put(svccm.getNombre(),svccm);
		servicios_activos.put(svcccct.getNombre(),svcccct);
		servicios_activos.put(svctahona.getNombre(),svctahona);
		servicios_activos.put(svcsanta.getNombre(),svcsanta);
		servicios_activos.put(svcparaiso.getNombre(),svcparaiso);
		servicios_activos.put(svclf.getNombre(),svclf);
		servicios_activos.put(svcmcbo.getNombre(),svcmcbo);
		servicios_activos.put(svcbamcbo.getNombre(),svcbamcbo);
		servicios_activos.put(svcgamaplus.getNombre(),svcgamaplus);
		servicios_activos.put(svc4dm.getNombre(),svc4dm);
		servicios_activos.put(svccclr.getNombre(),svccclr);
		servicios_activos.put(svcpea.getNombre(),svcpea);
		servicios_activos.put(svcsm.getNombre(),svcsm);
		servicios_activos.put(svcsc1.getNombre(),svcsc1);
		servicios_activos.put(svcsc2.getNombre(),svcsc2);
//		servicios_activos.put(svccumpleanospde.getNombre(), svccumpleanospde);
		servicios_activos.put(svccasona.getNombre(),svccasona);
		servicios_activos.put(svctrinidad.getNombre(),svctrinidad);
		servicios_activos.put(svcurbina.getNombre(),svcurbina);
		servicios_activos.put(svcsamanes.getNombre(),svcsamanes);
//		servicios_activos.put(svccumpleanosvs.getNombre(),svccumpleanosvs);
		servicios_activos.put(svccumplecumana.getNombre(),svccumplecumana);
		servicios_activos.put(svccumana.getNombre(),svccumana);
		servicios_activos.put(svcbuena.getNombre(),svcbuena);
		servicios_activos.put(svcstmon.getNombre(),svcstmon);
		//servicios_activos.put(svccumpleanosparaiso.getNombre(),svccumpleanosparaiso);

//		servicios_activos.put(svccumpleanosln.getNombre(),svccumpleanosln);
//		servicios_activos.put(svccumpleanosem.getNombre(),svccumpleanosem);
		//servicios_activos.put(svccumpleanoser.getNombre(),svccumpleanoser);
//		servicios_activos.put(svccumpleanossb.getNombre(),svccumpleanossb);
//		servicios_activos.put(svccumpleanossb2.getNombre(),svccumpleanossb2);
//		servicios_activos.put(svccumpleanosld.getNombre(),svccumpleanosld);
//		servicios_activos.put(svccumpleanoslb.getNombre(),svccumpleanoslb);

		servicios_activos.put(svcedocta.getNombre(),svcedocta);
		servicios_activos.put(svcedoctalcc.getNombre(),svcedoctalcc);
		servicios_activos.put(svcedoctapa.getNombre(),svcedoctapa);
		
		Servicio svc2 = new Servicio();
		svc2.setTimeout(14400000);
		svc2.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida not2 = new NotificacionSalida();
		Map<Integer,Conexion> hm2 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dm2 = new HashMap<Integer,String>();
		hm2.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dm2.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido cont2 = new ContenidoSMS("##MSG##");
		not2.setContenido(cont2);
		not2.setDatareplace(dm2);
		not2.setRoutes(hm2);
		Notificar nfy2 = new Notificar();
		nfy2.setOut(not2);
		svc2.setRootcmd(nfy2);
		svc2.setNombre("WWW");
		
		NotificacionEntrada newww = new NotificacionEntrada();
		TransicionRegex trwww = new TransicionRegex();
		trwww.setIn(newww);
		HashMap<String,Comando> cmwww = new HashMap<String,Comando>();
		cmwww.put(".*",null);
		newww.setTransitions(cmwww);
		
		nfy2.setTransicion(trwww);
		//Tambien el del CCCT CUmpleanos
		nfycumpleanos.setTransicion(trwww);
		nfycumpleanosparaiso.setTransicion(trwww);
		
		servicios_activos.put(svc2.getNombre(),svc2);
		
		Servicio svcprg = new Servicio();
		svcprg.setTimeout(28800000);
		svcprg.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notprg = new NotificacionSalida();
		Map<Integer,Conexion> hmprg = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmprg = new HashMap<Integer,String>();
		hmprg.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmprg.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contprg = new ContenidoSMS("##MSG##");
		notprg.setContenido(contprg);
		notprg.setDatareplace(dmprg);
		notprg.setRoutes(hmprg);
		Notificar nfyprg = new Notificar();
		nfyprg.setOut(notprg);
		svcprg.setRootcmd(nfyprg);
		svcprg.setNombre("Programado");
		
		NotificacionEntrada neprg = new NotificacionEntrada();
		TransicionRegex trprg = new TransicionRegex();
		trprg.setIn(neprg);
		HashMap<String,Comando> cmprg = new HashMap<String,Comando>();
		cmprg.put(".*",null);
		neprg.setTransitions(cmprg);
		
		nfyprg.setTransicion(trprg);

		nfycumpleanos.setTransicion(trprg);
		nfycumpleanosparaiso.setTransicion(trprg);
		
		
		servicios_activos.put("Programado",svcprg);
		
		Servicio bigott = new Servicio();
		bigott.setTimeout(14400000);
		bigott.setGrpcnx(getGrupoConexion(new Long(1)));

		NotificacionSalida notbigott = new NotificacionSalida();
		Map<Integer,Conexion> hmbigott = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmbigott = new HashMap<Integer,String>();
		hmbigott.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmbigott.put(new Integer(0),"##MSG##");
		Contenido contbigott = new ContenidoSMS("##MSG##");
		notbigott.setContenido(contbigott);
		notbigott.setDatareplace(dmbigott);
		notbigott.setRoutes(hmbigott);
		
		
		Notificar nfybigott = new Notificar();
		nfybigott.setOut(notbigott);
		//nfybigott.setLarge(true);
		
		bigott.setRootcmd(nfybigott);
		bigott.setNombre("WWW_bigott");
		
		
				
		NotificacionEntrada nebigott = new NotificacionEntrada();
		TransicionRegex trbigott = new TransicionRegex();
		trbigott.setIn(nebigott);
		HashMap<String,Comando> cmbigott = new HashMap<String,Comando>();
		nebigott.setTransitions(cmbigott);
		nebigott.setDef(null);
		nfybigott.setTransicion(trbigott);
	
		
		
		servicios_activos.put(bigott.getNombre(), bigott);
		
		
		Servicio ojo = new Servicio();
		ojo.setTimeout(0);
		ojo.setGrpcnx(getGrupoConexion(new Long(11)));
		//log.debug("Servicio WWW Grupo: "  + ojo.getGrpcnx().getNombre());
		NotificacionSalida notojo = new NotificacionSalida();
		Map<Integer,Conexion> hmojo = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmojo = new HashMap<Integer,String>();
		hmojo.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmojo.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contojo = new ContenidoSMS("##MSG##");
		notojo.setContenido(contojo);
		notojo.setDatareplace(dmojo);
		notojo.setRoutes(hmojo);
		Notificar nfyojo = new Notificar();
		nfyojo.setOut(notojo);
		
		ojo.setRootcmd(nfyojo);
		ojo.setNombre("WWW_ojo");
		
		//NotificacionEntrada neojo = new NotificacionEntrada();
		//TransicionRegex trojo = new TransicionRegex();
		//trojo.setIn(neojo);
		//HashMap<String,Comando> cmojo = new HashMap<String,Comando>();
		//neojo.setDef(null);
		//nfyojo.setTransicion(trojo);
		//neojo.setTransitions(cmojo);
		
		servicios_activos.put(ojo.getNombre(), ojo);
		
		Servicio tele = new Servicio();
		tele.setTimeout(14400000);
		tele.setGrpcnx(getGrupoConexion(new Long(7)));
		//log.debug("Servicio WWW Grupo: "  + tele.getGrpcnx().getNombre());
		NotificacionSalida nottele = new NotificacionSalida();
		Map<Integer,Conexion> hmtele = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtele = new HashMap<Integer,String>();
		hmtele.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtele.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido conttele = new ContenidoSMS("##MSG##");
		nottele.setContenido(conttele);
		nottele.setDatareplace(dmtele);
		nottele.setRoutes(hmtele);
		Notificar nfytele = new Notificar();
		nfytele.setOut(nottele);
		
		tele.setRootcmd(nfytele);
		tele.setNombre("WWW_tele");
		
		NotificacionEntrada netele = new NotificacionEntrada();
		TransicionRegex trtele = new TransicionRegex();
		trtele.setIn(netele);
		HashMap<String,Comando> cmtele = new HashMap<String,Comando>();
		netele.setDef(null);
		nfytele.setTransicion(trtele);
		netele.setTransitions(cmtele);
		
		servicios_activos.put(tele.getNombre(), tele);
		
		Servicio og = new Servicio();
		og.setTimeout(14400000);
		og.setGrpcnx(getGrupoConexion(new Long(5)));
		og.setPredeterminado(true);
		og.setPred("inbox_og");
		//log.debug("Servicio WWW Grupo: "  + og.getGrpcnx().getNombre());
		NotificacionSalida notog = new NotificacionSalida();
		Map<Integer,Conexion> hmog = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmog = new HashMap<Integer,String>();
		hmog.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmog.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contog = new ContenidoSMS("##MSG##");
		notog.setContenido(contog);
		notog.setDatareplace(dmog);
		notog.setRoutes(hmog);
		Notificar nfyog = new Notificar();
		nfyog.setOut(notog);
		
		
		
		og.setRootcmd(nfyog);
		og.setNombre("WWW_og");
		
		LlamadaOg2 llog = new LlamadaOg2();
		NotificacionEntrada neog = new NotificacionEntrada();
		TransicionRegex trog = new TransicionRegex();
		trog.setIn(neog);
		HashMap<String,Comando> cmog = new HashMap<String,Comando>();
		neog.setDef(llog);
		nfyog.setTransicion(trog);
		neog.setTransitions(cmog);
		llog.setTransicion(trog);
		
		servicios_activos.put(og.getNombre(), og);
		servicios_activos.put("WWW_cacique", og);
		servicios_activos.put("WWW_promoparrilla",og);


		
		// fin de movil plus
		
		Servicio ogin = new Servicio();
		ogin.setTimeout(0);
		ogin.setPredeterminado(true);
		ogin.setGrpcnx(getGrupoConexion(new Long(5)));
		EjecutarTransicion etogin = new EjecutarTransicion();
		etogin.setTr(trog);
		ogin.setRootcmd(etogin);
		ogin.setNombre("inbox_og");
		
		servicios_activos.put(ogin.getNombre(),ogin);
		servicios_activos.put("inbox_cocacola",ogin);
    	servicios_activos.put("inbox_promoparrilla",ogin);
		
		Servicio ogcoca = new Servicio();
		ogcoca.setTimeout(14400000);
		ogcoca.setGrpcnx(getGrupoConexion(new Long(8)));
		ogcoca.setPredeterminado(true);
		ogcoca.setPred("inbox_cocacola");
		//log.debug("Servicio WWW Grupo: "  + ogcoca.getGrpcnx().getNombre());
		NotificacionSalida notogcoca = new NotificacionSalida();
		Map<Integer,Conexion> hmogcoca = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmogcoca = new HashMap<Integer,String>();
		hmogcoca.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmogcoca.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contogcoca = new ContenidoSMS("##MSG##");
		notogcoca.setContenido(contogcoca);
		notogcoca.setDatareplace(dmogcoca);
		notogcoca.setRoutes(hmogcoca);
		Notificar nfyogcoca = new Notificar();
		nfyogcoca.setOut(notogcoca);
		
		ogcoca.setRootcmd(nfyogcoca);
		ogcoca.setNombre("WWW_cocacola");
		
		
		nfyogcoca.setTransicion(trog);
		
		
		
		servicios_activos.put(ogcoca.getNombre(), ogcoca);
		// OG -- parrillero
		
		Servicio ogparrilla = new Servicio();
		ogparrilla.setTimeout(14400000);
		ogparrilla.setGrpcnx(getGrupoConexion(new Long(10)));
		ogparrilla.setPredeterminado(true);
		ogparrilla.setPred("inbox_promoparrilla");
		//log.debug("Servicio WWW Grupo: "  + ogparrilla.getGrpcnx().getNombre());
		NotificacionSalida notogparrilla = new NotificacionSalida();
		Map<Integer,Conexion> hmogparrilla = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmogparrilla = new HashMap<Integer,String>();
		hmogparrilla.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmogparrilla.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contogparrilla = new ContenidoSMS("##MSG##");
		notogparrilla.setContenido(contogparrilla);
		notogparrilla.setDatareplace(dmogparrilla);
		notogparrilla.setRoutes(hmogparrilla);
		Notificar nfyogparrilla = new Notificar();
		nfyogparrilla.setOut(notogparrilla);
		
		ogparrilla.setRootcmd(nfyogparrilla);
		ogparrilla.setNombre("WWW_promoparrilla");
		
		
		nfyogparrilla.setTransicion(trog);
		
		
		
		servicios_activos.put(ogparrilla.getNombre(), ogparrilla);
		
		//---------------------- OG Regional
		
		Servicio ogregional = new Servicio();
		ogregional.setTimeout(14400000);
		ogregional.setGrpcnx(getGrupoConexion(new Long(1)));
		//log.debug("Servicio WWW Grupo: "  + ogcoca.getGrpcnx().getNombre());
		NotificacionSalida notogregional = new NotificacionSalida();
		Map<Integer,Conexion> hmogregional = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmogregional = new HashMap<Integer,String>();
		hmogregional.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmogregional.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contogregional = new ContenidoSMS("##MSG##");
		notogregional.setContenido(contogregional);
		notogregional.setDatareplace(dmogregional);
		notogregional.setRoutes(hmogregional);
		Notificar nfyogregional = new Notificar();
		nfyogregional.setOut(notogregional);
		
		ogregional.setRootcmd(nfyogcoca);
		ogregional.setNombre("WWW_regional");
		
		

		
		NotificacionEntrada neogregional = new NotificacionEntrada();
		TransicionRegex trogregional = new TransicionRegex();
		trogregional.setIn(neogregional);
		HashMap<String,Comando> cmogregional = new HashMap<String,Comando>();
		neogregional.setTransitions(cmogregional);
		neogregional.setDef(null);
		
		nfyogregional.setTransicion(trogregional);
		
		
		
		servicios_activos.put(ogregional.getNombre(), ogregional);
		
		
		
		
		
		
		
		//-------------------------------
		
		Servicio sumate = new Servicio();
		sumate.setTimeout(7200000);
		sumate.setGrpcnx(getGrupoConexion(new Long(3)));
		//log.debug("Servicio WWW Grupo: "  + sumate.getGrpcnx().getNombre());
		NotificacionSalida notsumate = new NotificacionSalida();
		Map<Integer,Conexion> hmsumate = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsumate = new HashMap<Integer,String>();
		hmsumate.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsumate.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contsumate = new ContenidoSMS("##MSG##");
		notsumate.setContenido(contsumate);
		notsumate.setDatareplace(dmsumate);
		notsumate.setRoutes(hmsumate);
		Notificar nfysumate = new Notificar();
		nfysumate.setOut(notsumate);
		
		sumate.setRootcmd(nfysumate);
		sumate.setNombre("WWW_sumate");
		
		NotificacionEntrada nesumate = new NotificacionEntrada();
		TransicionRegex trsumate = new TransicionRegex();
		trsumate.setIn(nesumate);
		HashMap<String,Comando> cmsumate = new HashMap<String,Comando>();
		nesumate.setDef(null);
		nfysumate.setTransicion(trsumate);
		nesumate.setTransitions(cmsumate);
		
		servicios_activos.put(sumate.getNombre(), sumate);
		
		// Alertas Meridiano Television
		
		Servicio mtv = new Servicio();
		mtv.setTimeout(14400000);
		mtv.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notmtv = new NotificacionSalida();
		Map<Integer,Conexion> hmmtv = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmmtv = new HashMap<Integer,String>();
		hmmtv.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmmtv.put(new Integer(0),"##MSG##");

		Contenido contmtv = new ContenidoSMS("##MSG##");
		notmtv.setContenido(contmtv);
		notmtv.setDatareplace(dmmtv);
		notmtv.setRoutes(hmmtv);
		Notificar nfymtv = new Notificar();
		nfymtv.setOut(notmtv);
		
		mtv.setRootcmd(nfymtv);
		mtv.setNombre("registro_mtv");
		
		NotificacionEntrada nemtv = new NotificacionEntrada();
		TransicionRegex trmtv = new TransicionRegex();
		trmtv.setIn(nemtv);
		HashMap<String,Comando> cmmtv = new HashMap<String,Comando>();
		Responder respmtv = new Responder();
		respmtv.setResp("Tu servicio de alertas Meridiano TV ha sido completamente activado, pronto recibiras la mejor informacion deportiva totalmente gratis!");
		cmmtv.put(".*",respmtv);
		nemtv.setDef(respmtv);
		nfymtv.setTransicion(trmtv);
		nemtv.setTransitions(cmmtv);
		
		servicios_activos.put(mtv.getNombre(), mtv);
		
		// Alertas Globovision Television
		
		Servicio globo = new Servicio();
		globo.setTimeout(14400000);
		globo.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notglobo = new NotificacionSalida();
		Map<Integer,Conexion> hmglobo = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmglobo = new HashMap<Integer,String>();
		hmglobo.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmglobo.put(new Integer(0),"##MSG##");

		Contenido contglobo = new ContenidoSMS("##MSG##");
		notglobo.setContenido(contglobo);
		notglobo.setDatareplace(dmglobo);
		notglobo.setRoutes(hmglobo);
		Notificar nfyglobo = new Notificar();
		nfyglobo.setOut(notglobo);
		
		globo.setRootcmd(nfyglobo);
		globo.setNombre("registro_globo");
		
		NotificacionEntrada neglobo = new NotificacionEntrada();
		TransicionRegex trglobo = new TransicionRegex();
		trglobo.setIn(neglobo);
		HashMap<String,Comando> cmglobo = new HashMap<String,Comando>();
		Responder respglobo = new Responder();
		respglobo.setResp("Tu suscripcion al servicio de Alertas de Globovision Movil es ahora oficial. A partir de ahora comenzaras a recibir noticias en tu celular.");
		cmglobo.put(".*",respglobo);
		neglobo.setDef(respglobo);
		nfyglobo.setTransicion(trglobo);
		neglobo.setTransitions(cmglobo);
		
		servicios_activos.put(globo.getNombre(), globo);			
		//INcomming
		
		Servicio svc3 = new Servicio();
		svc3.setTimeout(3600000);
		svc3.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionEntrada ne3 = new NotificacionEntrada();
		HashMap<String,Comando> cm3 = new HashMap<String,Comando>();
		EjecutarTransicion et = new EjecutarTransicion();
		TransicionRegex tr3 = new TransicionRegex();
		cm3.put(".*",null);
		ne3.setTransitions(cm3);
		tr3.setIn(ne3);
		et.setTr(tr3);
		svc3.setRootcmd(et);
		svc3.setNombre("incoming");
		
		servicios_activos.put(svc3.getNombre(),svc3);
		
		Servicio svctrivia = new Servicio();
		svctrivia.setTimeout(3600000);
		svctrivia.setGrpcnx(getGrupoConexion(new Long(9)));
		NotificacionEntrada netrivia = new NotificacionEntrada();
		HashMap<String,Comando> cmtrivia = new HashMap<String,Comando>();
		
		TransicionRegex trtrivia = new TransicionRegex();
		Responder resplg = new Responder();
		resplg.setResp("Bienvenido a LGCLUB, Envia tu primer nombre, primer apellido y nombre de tienda todo separado por un espacio en blanco");
		resplg.setTransicion(trtrivia);
		ProcesarRegistro ptrivia = new ProcesarRegistro();
		netrivia.setTransitions(cmtrivia);
		cmtrivia.put("(?i)registro", resplg);
		
		netrivia.setDef(ptrivia);
		trtrivia.setIn(netrivia);
		
		//ptrivia.setTransicion(trtrivia);
		svctrivia.setRootcmd(resplg);
		svctrivia.setNombre("trivia");
		svctrivia.setPredeterminado(true);
		svctrivia.setPred("inbox_trivia");
		
		servicios_activos.put(svctrivia.getNombre(),svctrivia);
		
		Servicio svcpregunta_trivia = new Servicio();
		svcpregunta_trivia.setTimeout(28800000);
		svcpregunta_trivia.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionEntrada nepregunta_trivia = new NotificacionEntrada();
		HashMap<String,Comando> cmpregunta_trivia = new HashMap<String,Comando>();
		TransicionRegex trpregunta_trivia = new TransicionRegex();		
		ProcesarTrivia pptrivia = new ProcesarTrivia();
		ProcesarRespuesta presptrivia = new ProcesarRespuesta();
		
		
		
		nepregunta_trivia.setTransitions(cmpregunta_trivia);
		nepregunta_trivia.setDef(presptrivia);
		trpregunta_trivia.setIn(nepregunta_trivia);
		
		pptrivia.setTransicion(trpregunta_trivia);
		
		presptrivia.setTransicion(trpregunta_trivia);

		svcpregunta_trivia.setRootcmd(pptrivia);
		svcpregunta_trivia.setNombre("pregunta_trivia");
		servicios_activos.put(svcpregunta_trivia.getNombre(),svcpregunta_trivia);
		
		
		// TRIVIA ENVIO DE REGISTRO
		Servicio masivotrivia = new Servicio();
		masivotrivia.setTimeout(1800000);
		masivotrivia.setGrpcnx(getGrupoConexion(new Long(9)));
		masivotrivia.setPredeterminado(false);

		//log.debug("Servicio WWW Grupo: "  + masivotrivia.getGrpcnx().getNombre());
		NotificacionSalida mtnotog = new NotificacionSalida();
		Map<Integer,Conexion> mthmog = new HashMap<Integer,Conexion>();
		Map<Integer,String> mtdmog = new HashMap<Integer,String>();
		mthmog.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		mtdmog.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido mtcontog = new ContenidoSMS("##MSG##");
		mtnotog.setContenido(mtcontog);
		mtnotog.setDatareplace(mtdmog);
		mtnotog.setRoutes(hmog);
		Notificar mtnfyog = new Notificar();
		mtnfyog.setOut(mtnotog);

		
		mtnfyog.setTransicion(trtrivia);
		
		masivotrivia.setRootcmd(mtnfyog);
		masivotrivia.setNombre("WWW_trivia");
		//masivotrivia.setPredeterminado(true);
		//masivotrivia.setPred("trivia");
		servicios_activos.put(masivotrivia.getNombre(),masivotrivia);
		
		//TRIVIA BITTIME
		
		Servicio ventas = new Servicio();
		ventas.setTimeout(0);
		ventas.setGrpcnx(getGrupoConexion(new Long(9)));
		NotificacionEntrada neventas = new NotificacionEntrada();
		HashMap<String,Comando> cmventas = new HashMap<String,Comando>();
		EjecutarTransicion etventas = new EjecutarTransicion();
		TransicionRegex trventas = new TransicionRegex();
		ProcesarVenta pventa = new ProcesarVenta();
		neventas.setDef(pventa);
		neventas.setTransitions(cmventas);
		trventas.setIn(neventas);
		etventas.setTr(trventas);
		pventa.setTransicion(trtrivia);
		ventas.setRootcmd(etventas);
		ventas.setNombre("inbox_trivia");
		mtnfyog.setTransicion(trventas);
		//cmtrivia.put("(?i)venta", pventa);
		
		servicios_activos.put(ventas.getNombre(),ventas);
		
		Servicio concurso = new Servicio();
		concurso.setTimeout(0);
		concurso.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionEntrada neconcurso = new NotificacionEntrada();
		HashMap<String,Comando> cmconcurso = new HashMap<String,Comando>();
		EjecutarTransicion etconcurso = new EjecutarTransicion();
		TransicionRegex trconcurso = new TransicionRegex();
		Responder respconcurso = new Responder();
		respconcurso.setResp("Gracias por recomendar SIBUTRAM");
		neconcurso.setDef(respconcurso);
		neconcurso.setTransitions(cmconcurso);
		trconcurso.setIn(neconcurso);
		etconcurso.setTr(trconcurso);
		respconcurso.setTransicion(trconcurso);
		concurso.setRootcmd(etconcurso);
		concurso.setNombre("concurso_behrens");
		
		servicios_activos.put(concurso.getNombre(),concurso);
		
		
		
		Servicio eycos = new Servicio();
		eycos.setTimeout(3600000);
		eycos.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida eycosout = new NotificacionSalida();
		Map<Integer,Conexion> hme = new HashMap<Integer,Conexion>();
		Map<Integer,String> dme = new HashMap<Integer,String>();
		hme.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dme.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido conte = new ContenidoSMS("##MSG##");
		eycosout.setContenido(conte);
		eycosout.setDatareplace(dme);
		eycosout.setRoutes(hme);
		Notificar eycosnfy = new Notificar();
		eycosnfy.setOut(eycosout);
		
		NotificacionEntrada neycos = new NotificacionEntrada();
		Responder respe = new Responder();
		respe.setResp("Gracias por su comentario, con gusto lo tomaremos en cuenta\nEycos");
		HashMap<String,Comando> cme = new HashMap<String,Comando>();
		cme.put(".*",respe);
		neycos.setTransitions(cme);
		TransicionRegex tre = new TransicionRegex();
		tre.setIn(neycos);
		eycosnfy.setTransicion(tre);
		

		
		eycos.setRootcmd(eycosnfy);
		eycos.setNombre("copialista");
		
		servicios_activos.put(eycos.getNombre(),eycos);
		
		Servicio netosfera = new Servicio();
		netosfera.setTimeout(3600000);
		netosfera.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notnetosfera = new NotificacionSalida();
		Map<Integer,Conexion> hmnetosfera = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmnetosfera = new HashMap<Integer,String>();
		hmnetosfera.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmnetosfera.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contnetosfera = new ContenidoSMS("##MSG##");
		notnetosfera.setContenido(contnetosfera);
		notnetosfera.setDatareplace(dmnetosfera);
		notnetosfera.setRoutes(hmnetosfera);
		Notificar nfynetosfera = new Notificar();
		nfynetosfera.setOut(not2);
		netosfera.setRootcmd(nfy2);
		netosfera.setNombre("netosfera_std");
		
		NotificacionEntrada nenetosfera = new NotificacionEntrada();
		TransicionRegex trnetosfera = new TransicionRegex();
		trnetosfera.setIn(nenetosfera);
		HashMap<String,Comando> cmnetosfera = new HashMap<String,Comando>();
		cmnetosfera.put(".*",null);
		nenetosfera.setTransitions(cmnetosfera);
		
		nfynetosfera.setTransicion(trnetosfera);
		
		servicios_activos.put(netosfera.getNombre(),netosfera);
		
		Servicio abacus = new Servicio();
		abacus.setTimeout(3600000);
		abacus.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notabacus = new NotificacionSalida();
		Map<Integer,Conexion> hmabacus = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmabacus = new HashMap<Integer,String>();
		hmabacus.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmabacus.put(new Integer(0),"##FECHA##");
		dmabacus.put(new Integer(1),"##LN##");
		dmabacus.put(new Integer(2),"##ER##");
		dmabacus.put(new Integer(3),"##EM##");
		dmabacus.put(new Integer(4),"##PDE##");
		dmabacus.put(new Integer(5),"##LB##");
		dmabacus.put(new Integer(6),"##LD##");
		dmabacus.put(new Integer(7),"##SB##");
		dmabacus.put(new Integer(8),"##SB2##");
		dmabacus.put(new Integer(9),"##VS##");
		Contenido contabacus = new ContenidoSMS("Reporte Grupo Abacus dia ##FECHA##:\nLN:##LN## ER:##ER## EM:##EM## PDE:##PDE## LB:##LB## LD:##LD## SB:##SB## SB2:##SB2## VSEM:##VS##\n");
		notabacus.setContenido(contabacus);
		notabacus.setDatareplace(dmabacus);
		notabacus.setRoutes(hmabacus);
		Notificar nfyabacus = new Notificar();
		nfyabacus.setOut(notabacus);
		abacus.setRootcmd(nfyabacus);
		abacus.setNombre("abacus");
		
		NotificacionEntrada neabacus = new NotificacionEntrada();
		TransicionRegex trabacus = new TransicionRegex();
		trabacus.setIn(neabacus);
		HashMap<String,Comando> cmabacus = new HashMap<String,Comando>();
		neabacus.setTransitions(cmabacus);
		neabacus.setDef(null);
		
		nfyabacus.setTransicion(trabacus);
		
		servicios_activos.put(abacus.getNombre(),abacus);
		
		Servicio interbank = new Servicio();
		interbank.setTimeout(3600000);
		interbank.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notinterbank = new NotificacionSalida();
		Map<Integer,Conexion> hminterbank = new HashMap<Integer,Conexion>();
		Map<Integer,String> dminterbank = new HashMap<Integer,String>();
		hminterbank.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dminterbank.put(new Integer(0),"##MSG##");
		Contenido continterbank = new ContenidoSMS("##MSG##");
		notinterbank.setContenido(continterbank);
		notinterbank.setDatareplace(dminterbank);
		notinterbank.setRoutes(hminterbank);
		Notificar nfyinterbank = new Notificar();
		nfyinterbank.setOut(notinterbank);
		interbank.setRootcmd(nfyinterbank);
		interbank.setNombre("auto_interbank");
		
		NotificacionEntrada neinterbank = new NotificacionEntrada();
		TransicionRegex trinterbank = new TransicionRegex();
		trinterbank.setIn(neinterbank);
		HashMap<String,Comando> cminterbank = new HashMap<String,Comando>();
		neinterbank.setTransitions(cminterbank);
		neinterbank.setDef(null);
		
		nfyinterbank.setTransicion(trinterbank);
		
		servicios_activos.put(interbank.getNombre(),interbank);
		servicios_activos.put("sms",interbank);
		
		Servicio auximed = new Servicio();
		auximed.setTimeout(3600000);
		auximed.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notauximed = new NotificacionSalida();
		Map<Integer,Conexion> hmauximed = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmauximed = new HashMap<Integer,String>();
		hmauximed.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmauximed.put(new Integer(0),"##MSG##");
		//Contenido contauximed = new ContenidoSMS("Buenos dias Sr(a) ##APELLIDO##, AUXIMED le recuerda solicitar su orden para retirar su tratamiento manana en nuestro consultorio, gracias");
				//"Buenos dias Sr(a). ##APELLIDO##, AUXIMED le recuerda que debe venir a retirar su tratamiento el dia ##FECHA##. Gracias y Buen Dia.");
		Contenido contauximed = new ContenidoSMS("##MSG##");
		notauximed.setContenido(contauximed); 
		notauximed.setDatareplace(dmauximed);
		notauximed.setRoutes(hmauximed);
		Notificar nfyauximed = new Notificar();
		nfyauximed.setOut(notauximed);
		auximed.setRootcmd(nfyauximed);
		auximed.setNombre("auximed_recordatorios");
		
		NotificacionEntrada neauximed = new NotificacionEntrada();
		TransicionRegex trauximed = new TransicionRegex();
		trauximed.setIn(neauximed);
		HashMap<String,Comando> cmauximed = new HashMap<String,Comando>();
		neauximed.setTransitions(cmauximed);
		neauximed.setDef(null);
		
		nfyauximed.setTransicion(trauximed);
		
		servicios_activos.put(auximed.getNombre(),auximed);
		
		
		Servicio auximed2 = new Servicio();
		auximed2.setTimeout(3600000);
		auximed2.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notauximed2 = new NotificacionSalida();
		Map<Integer,Conexion> hmauximed2 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmauximed2 = new HashMap<Integer,String>();
		hmauximed2.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmauximed2.put(new Integer(0),"##MSG##");
		//Contenido contauximed2 = new ContenidoSMS("Buenos dias Sr(a) ##APELLIDO##, AUXIMED le recuerda que no ha retirado su tratamiento desde hace ##DIAS## dias. Muchas Gracias" );
				//Buenos dias Sr(a) ##APELLIDO##, AUXIMED le recuerda que no ha consumido su tratamiento desde hace ##DIAS## dias. Muchas Gracias");
		Contenido contauximed2 = new ContenidoSMS("##MSG##" );
		notauximed2.setContenido(contauximed2); 
		notauximed2.setDatareplace(dmauximed2);
		notauximed2.setRoutes(hmauximed2);
		Notificar nfyauximed2 = new Notificar();
		nfyauximed2.setOut(notauximed2);
		auximed2.setRootcmd(nfyauximed2);
		auximed2.setNombre("auximed_recordatorio2");
		
		NotificacionEntrada neauximed2 = new NotificacionEntrada();
		TransicionRegex trauximed2 = new TransicionRegex();
		trauximed2.setIn(neauximed2);
		HashMap<String,Comando> cmauximed2 = new HashMap<String,Comando>();
		neauximed2.setTransitions(cmauximed2);
		neauximed2.setDef(null);
		
		nfyauximed2.setTransicion(trauximed2);
		
		servicios_activos.put(auximed2.getNombre(),auximed2);
		
		Servicio auximed3 = new Servicio();
		auximed3.setTimeout(3600000);
		auximed3.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notauximed3 = new NotificacionSalida();
		Map<Integer,Conexion> hmauximed3 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmauximed3 = new HashMap<Integer,String>();
		hmauximed3.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmauximed3.put(new Integer(0),"##APELLIDO##");
		Contenido contauximed3 = new ContenidoSMS("Buenos dias Sr(a) ##APELLIDO##, AUXIMED le recuerda que consulte su medico para renovar su tratamiento. Muchas Gracias");
		notauximed3.setContenido(contauximed3); 
		notauximed3.setDatareplace(dmauximed3);
		notauximed3.setRoutes(hmauximed3);
		Notificar nfyauximed3 = new Notificar();
		nfyauximed3.setOut(notauximed3);
		auximed3.setRootcmd(nfyauximed3);
		auximed3.setNombre("auximed_vencimiento");
		
		NotificacionEntrada neauximed3 = new NotificacionEntrada();
		TransicionRegex trauximed3 = new TransicionRegex();
		trauximed3.setIn(neauximed3);
		HashMap<String,Comando> cmauximed3 = new HashMap<String,Comando>();
		neauximed3.setTransitions(cmauximed3);
		neauximed3.setDef(null);
		
		nfyauximed3.setTransicion(trauximed3);
		
		servicios_activos.put(auximed3.getNombre(),auximed3);
		
		//Servicio 1000KM
		
		Servicio toyoclub = new Servicio();
		toyoclub.setTimeout(3600000);
		toyoclub.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub = new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub = new HashMap<Integer,String>();
		hmtoyoclub.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub.put(new Integer(0),"##CONS##");
		dmtoyoclub.put(new Integer(1),"##PLACA##");
		Contenido conttoyoclub = new ContenidoSMS("##CONS##, le recuerda la 1era., inspecciÃ³n gratuita de 1000kms., exigida por T.D.V. (no tendra que solicitar Cita)");
		nottoyoclub.setContenido(conttoyoclub);
		nottoyoclub.setDatareplace(dmtoyoclub);
		nottoyoclub.setRoutes(hmtoyoclub);
		Notificar nfytoyoclub = new Notificar();
		nfytoyoclub.setOut(nottoyoclub);
		toyoclub.setRootcmd(nfytoyoclub);
		toyoclub.setNombre("toyota_servicio1k");
		
		NotificacionEntrada netoyoclub = new NotificacionEntrada();
		TransicionRegex trtoyoclub = new TransicionRegex();
		trtoyoclub.setIn(netoyoclub);
		HashMap<String,Comando> cmtoyoclub = new HashMap<String,Comando>();
		netoyoclub.setTransitions(cmtoyoclub);
		netoyoclub.setDef(null);
		
		nfytoyoclub.setTransicion(trtoyoclub);
		
		servicios_activos.put(toyoclub.getNombre(),toyoclub);
		
		//Servicio 5000KM
		
		Servicio toyoclub2= new Servicio();
		toyoclub2.setTimeout(3600000);
		toyoclub2.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub2= new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub2 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub2 = new HashMap<Integer,String>();
		hmtoyoclub2.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub2.put(new Integer(0),"##CONS##");
		dmtoyoclub2.put(new Integer(1),"##PLACA##");
		dmtoyoclub2.put(new Integer(2),"##TLFS##");
		Contenido conttoyoclub2 = new ContenidoSMS("##CONS## le recuerda la revision de su vehiculo cada 5mil Kms. o tres meses; solicite su Cita por los Telfs. ##TLFS##");
		nottoyoclub2.setContenido(conttoyoclub2);
		nottoyoclub2.setDatareplace(dmtoyoclub2);
		nottoyoclub2.setRoutes(hmtoyoclub2);
		Notificar nfytoyoclub2 = new Notificar();
		nfytoyoclub2.setOut(nottoyoclub2);
		toyoclub2.setRootcmd(nfytoyoclub2);
		toyoclub2.setNombre("toyota_servicio5k");
		
		NotificacionEntrada netoyoclub2 = new NotificacionEntrada();
		TransicionRegex trtoyoclub2 = new TransicionRegex();
		trtoyoclub2.setIn(netoyoclub2);
		HashMap<String,Comando> cmtoyoclub2 = new HashMap<String,Comando>();
		netoyoclub2.setTransitions(cmtoyoclub2);
		netoyoclub2.setDef(null);
		
		nfytoyoclub2.setTransicion(trtoyoclub2);
		
		servicios_activos.put(toyoclub2.getNombre(),toyoclub2);

		//Servicio 10.000KM
		
		Servicio toyoclub3= new Servicio();
		toyoclub3.setTimeout(3600000);
		toyoclub3.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub3= new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub3 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub3 = new HashMap<Integer,String>();
		hmtoyoclub3.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub3.put(new Integer(0),"##CONS##");
		dmtoyoclub3.put(new Integer(1),"##PLACA##");
		dmtoyoclub3.put(new Integer(2),"##TLFS##");
		Contenido conttoyoclub3 = new ContenidoSMS("##CONS##, le recuerda la revision de 10.000kms, exigida por TDV. Mano de Obra gratuita solo cancelara lubricante/filtro. Citas por Tlf. ##TLFS## ");
		nottoyoclub3.setContenido(conttoyoclub3);
		nottoyoclub3.setDatareplace(dmtoyoclub3);
		nottoyoclub3.setRoutes(hmtoyoclub3);
		Notificar nfytoyoclub3 = new Notificar();
		nfytoyoclub3.setOut(nottoyoclub3);
		toyoclub3.setRootcmd(nfytoyoclub3);
		toyoclub3.setNombre("toyota_servicio10k");
		
		NotificacionEntrada netoyoclub3 = new NotificacionEntrada();
		TransicionRegex trtoyoclub3 = new TransicionRegex();
		trtoyoclub3.setIn(netoyoclub3);
		HashMap<String,Comando> cmtoyoclub3 = new HashMap<String,Comando>();
		netoyoclub3.setTransitions(cmtoyoclub3);
		netoyoclub3.setDef(null);
		
		nfytoyoclub3.setTransicion(trtoyoclub3);
		
		servicios_activos.put(toyoclub3.getNombre(),toyoclub3);
		
		//Servicio Los Sabados
		
		Servicio toyoclub4= new Servicio();
		toyoclub4.setTimeout(3600000);
		toyoclub4.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub4= new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub4 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub4 = new HashMap<Integer,String>();
		hmtoyoclub4.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub4.put(new Integer(0),"##CONS##");
		dmtoyoclub4.put(new Integer(1),"##PLACA##");
		dmtoyoclub4.put(new Integer(2),"##TLFS##");
		Contenido conttoyoclub4 = new ContenidoSMS("##CONS## le ofrece el Mtto., para sus vehiculos los dias sabado de 8:00 a 12:m. con previa cita por los Tlfs.##TLFS##");
		nottoyoclub4.setContenido(conttoyoclub4);
		nottoyoclub4.setDatareplace(dmtoyoclub4);
		nottoyoclub4.setRoutes(hmtoyoclub4);
		Notificar nfytoyoclub4 = new Notificar();
		nfytoyoclub4.setOut(nottoyoclub4);
		toyoclub4.setRootcmd(nfytoyoclub4);
		toyoclub4.setNombre("toyota_sabados");
		
		NotificacionEntrada netoyoclub4 = new NotificacionEntrada();
		TransicionRegex trtoyoclub4 = new TransicionRegex();
		trtoyoclub4.setIn(netoyoclub4);
		HashMap<String,Comando> cmtoyoclub4 = new HashMap<String,Comando>();
		netoyoclub4.setTransitions(cmtoyoclub4);
		netoyoclub4.setDef(null);
		
		nfytoyoclub4.setTransicion(trtoyoclub4);
		
		servicios_activos.put(toyoclub4.getNombre(),toyoclub4);
		
		//Servicio Opinion Servicio 72h
		
		Servicio toyoclub5= new Servicio();
		toyoclub5.setTimeout(3600000);
		toyoclub5.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub5= new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub5 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub5 = new HashMap<Integer,String>();
		hmtoyoclub5.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub5.put(new Integer(0),"##CONS##");
		dmtoyoclub5.put(new Integer(1),"##PLACA##");
		Contenido conttoyoclub5 = new ContenidoSMS("##CONS##, quiere saber su opiniÃ³n del Servicio efectuado, responda:Excelente, Bueno, Regular, Malo");
		nottoyoclub5.setContenido(conttoyoclub5);
		nottoyoclub5.setDatareplace(dmtoyoclub5);
		nottoyoclub5.setRoutes(hmtoyoclub5);
		Notificar nfytoyoclub5 = new Notificar();
		nfytoyoclub5.setOut(nottoyoclub5);
		toyoclub5.setRootcmd(nfytoyoclub5);
		toyoclub5.setNombre("toyota_opinion72");
		
		NotificacionEntrada netoyoclub5 = new NotificacionEntrada();
		TransicionRegex trtoyoclub5 = new TransicionRegex();
		trtoyoclub5.setIn(netoyoclub5);
		HashMap<String,Comando> cmtoyoclub5 = new HashMap<String,Comando>();
		netoyoclub5.setTransitions(cmtoyoclub5);
		netoyoclub5.setDef(null);
		
		nfytoyoclub5.setTransicion(trtoyoclub5);
		
		servicios_activos.put(toyoclub5.getNombre(),toyoclub5);
		
		//Servicio Opinion Venta
		
		Servicio toyoclub6= new Servicio();
		toyoclub6.setTimeout(3600000);
		toyoclub6.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub6= new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub6 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub6 = new HashMap<Integer,String>();
		hmtoyoclub6.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub6.put(new Integer(0),"##CONS##");
		dmtoyoclub6.put(new Integer(1),"##PLACA##");
		Contenido conttoyoclub6 = new ContenidoSMS("##CONS##, quiere saber su opinion respecto a la atenciÃ³n en la compra de su vehiculo, responda:Excelente, Bueno, Regular, Malo");
		nottoyoclub6.setContenido(conttoyoclub6);
		nottoyoclub6.setDatareplace(dmtoyoclub6);
		nottoyoclub6.setRoutes(hmtoyoclub6);
		Notificar nfytoyoclub6 = new Notificar();
		nfytoyoclub6.setOut(nottoyoclub6);
		toyoclub6.setRootcmd(nfytoyoclub6);
		toyoclub6.setNombre("toyota_opinionventa");
		
		NotificacionEntrada netoyoclub6 = new NotificacionEntrada();
		TransicionRegex trtoyoclub6 = new TransicionRegex();
		trtoyoclub6.setIn(netoyoclub6);
		HashMap<String,Comando> cmtoyoclub6 = new HashMap<String,Comando>();
		netoyoclub6.setTransitions(cmtoyoclub6);
		netoyoclub6.setDef(null);
		
		nfytoyoclub6.setTransicion(trtoyoclub6);
		
		servicios_activos.put(toyoclub6.getNombre(),toyoclub6);
		
		//Servicio Cumpleanos Cliente
		
		Servicio toyoclub7= new Servicio();
		toyoclub7.setTimeout(3600000);
		toyoclub7.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub7= new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub7 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub7 = new HashMap<Integer,String>();
		hmtoyoclub7.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub7.put(new Integer(0),"##CONS##");
		Contenido conttoyoclub7 = new ContenidoSMS("Hoy es un dia especial para usted, por ello,  La familia ##CONS##,C.A. le desea feliz cumpleaÃ±os.");
		nottoyoclub7.setContenido(conttoyoclub7);
		nottoyoclub7.setDatareplace(dmtoyoclub7);
		nottoyoclub7.setRoutes(hmtoyoclub7);
		Notificar nfytoyoclub7 = new Notificar();
		nfytoyoclub7.setOut(nottoyoclub7);
		toyoclub7.setRootcmd(nfytoyoclub7);
		toyoclub7.setNombre("toyota_cumpleanos");
		
		NotificacionEntrada netoyoclub7 = new NotificacionEntrada();
		TransicionRegex trtoyoclub7 = new TransicionRegex();
		trtoyoclub7.setIn(netoyoclub7);
		HashMap<String,Comando> cmtoyoclub7 = new HashMap<String,Comando>();
		netoyoclub7.setTransitions(cmtoyoclub7);
		netoyoclub7.setDef(null);
		
		nfytoyoclub7.setTransicion(trtoyoclub7);
		
		servicios_activos.put(toyoclub7.getNombre(),toyoclub7);

		//Servicio Cumpleanos Vehiculo
		
		Servicio toyoclub8= new Servicio();
		toyoclub8.setTimeout(3600000);
		toyoclub8.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub8= new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub8 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub8 = new HashMap<Integer,String>();
		hmtoyoclub8.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub8.put(new Integer(1),"##PLACA##");
		dmtoyoclub8.put(new Integer(0),"##CONS##");
		Contenido conttoyoclub8 = new ContenidoSMS("##CONS##, C.A. quiere celebrar con usted el cumpleaÃ±os de su vehiculo Toyota. ##PLACA##");
		nottoyoclub8.setContenido(conttoyoclub8);
		nottoyoclub8.setDatareplace(dmtoyoclub8);
		nottoyoclub8.setRoutes(hmtoyoclub8);
		Notificar nfytoyoclub8 = new Notificar();
		nfytoyoclub8.setOut(nottoyoclub8);
		toyoclub8.setRootcmd(nfytoyoclub8);
		toyoclub8.setNombre("toyota_cumpleanoscarro");
		
		NotificacionEntrada netoyoclub8 = new NotificacionEntrada();
		TransicionRegex trtoyoclub8 = new TransicionRegex();
		trtoyoclub8.setIn(netoyoclub8);
		HashMap<String,Comando> cmtoyoclub8 = new HashMap<String,Comando>();
		netoyoclub8.setTransitions(cmtoyoclub8);
		netoyoclub8.setDef(null);
		
		nfytoyoclub8.setTransicion(trtoyoclub8);
		
		servicios_activos.put(toyoclub8.getNombre(),toyoclub8);
		
		//Servicio Citas Vehiculo
		
		Servicio toyoclub9= new Servicio();
		toyoclub9.setTimeout(3600000);
		toyoclub9.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida nottoyoclub9= new NotificacionSalida();
		Map<Integer,Conexion> hmtoyoclub9 = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmtoyoclub9 = new HashMap<Integer,String>();
		hmtoyoclub9.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmtoyoclub9.put(new Integer(2),"##PLACA##");
		dmtoyoclub9.put(new Integer(1),"##FECHA##");
		dmtoyoclub9.put(new Integer(0),"##CONS##");
		dmtoyoclub9.put(new Integer(3),"##TLFS##");
		Contenido conttoyoclub9 = new ContenidoSMS("##CONS##, le recuerda que la proxima cita de su vehiculo placa ##PLACA## es el dia ##FECHA##, confirmelas por los tlfs ##TLFS##");
		nottoyoclub9.setContenido(conttoyoclub9);
		nottoyoclub9.setDatareplace(dmtoyoclub9);
		nottoyoclub9.setRoutes(hmtoyoclub9);
		Notificar nfytoyoclub9 = new Notificar();
		nfytoyoclub9.setOut(nottoyoclub9);
		toyoclub9.setRootcmd(nfytoyoclub9);
		toyoclub9.setNombre("toyota_citas");
		
		NotificacionEntrada netoyoclub9 = new NotificacionEntrada();
		TransicionRegex trtoyoclub9 = new TransicionRegex();
		trtoyoclub9.setIn(netoyoclub9);
		HashMap<String,Comando> cmtoyoclub9 = new HashMap<String,Comando>();
		netoyoclub9.setTransitions(cmtoyoclub9);
		netoyoclub9.setDef(null);
		
		nfytoyoclub9.setTransicion(trtoyoclub9);
		
		servicios_activos.put(toyoclub9.getNombre(),toyoclub9);
		
		Servicio cnocitas = new Servicio();
		cnocitas.setTimeout(3600000);
		cnocitas.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcnocitas = new NotificacionSalida();
		Map<Integer,Conexion> hmcnocitas = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcnocitas = new HashMap<Integer,String>();
		hmcnocitas.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcnocitas.put(new Integer(0),"##FECHA##");
		dmcnocitas.put(new Integer(1),"##HORA##");
		Contenido contcnocitas = new ContenidoSMS("Buen dia\nCentro Nacional de Ortodoncia le recuerda que su proxima cita es el ##FECHA## a las ##HORA##\n-Ortodoncia en Boca de Todos-");
		notcnocitas.setContenido(contcnocitas);
		notcnocitas.setDatareplace(dmcnocitas);
		notcnocitas.setRoutes(hmcnocitas);
		Notificar nfycnocitas = new Notificar();
		nfycnocitas.setOut(notcnocitas);
		cnocitas.setRootcmd(nfycnocitas);
		cnocitas.setNombre("cno_citas");
		
		NotificacionEntrada necnocitas = new NotificacionEntrada();
		TransicionRegex trcnocitas = new TransicionRegex();
		trcnocitas.setIn(necnocitas);
		HashMap<String,Comando> cmcnocitas = new HashMap<String,Comando>();
		necnocitas.setTransitions(cmcnocitas);
		necnocitas.setDef(null);
		
		nfycnocitas.setTransicion(trcnocitas);
		
		servicios_activos.put(cnocitas.getNombre(),cnocitas);
		servicios_activos.put("cno_citas_m",cnocitas);
		servicios_activos.put("cno_citas_t",cnocitas);
		
		Servicio cnolmcitas = new Servicio();
		cnolmcitas.setTimeout(3600000);
		cnolmcitas.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcnolmcitas = new NotificacionSalida();
		Map<Integer,Conexion> hmcnolmcitas = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcnolmcitas = new HashMap<Integer,String>();
		hmcnolmcitas.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcnolmcitas.put(new Integer(0),"##FECHA##");
		dmcnolmcitas.put(new Integer(1),"##HORA##");
		Contenido contcnolmcitas = new ContenidoSMS("Buen dia\nCentro Nacional de Ortodoncia sede las Mercedes le recuerda que su proxima cita es el ##FECHA## a las ##HORA##\n-Lo Esperamos-");
		notcnolmcitas.setContenido(contcnolmcitas);
		notcnolmcitas.setDatareplace(dmcnolmcitas);
		notcnolmcitas.setRoutes(hmcnolmcitas);
		Notificar nfycnolmcitas = new Notificar();
		nfycnolmcitas.setOut(notcnolmcitas);
		cnolmcitas.setRootcmd(nfycnolmcitas);
		cnolmcitas.setNombre("cnolm_citas_or");
		
		NotificacionEntrada necnolmcitas = new NotificacionEntrada();
		TransicionRegex trcnolmcitas = new TransicionRegex();
		trcnolmcitas.setIn(necnolmcitas);
		HashMap<String,Comando> cmcnolmcitas = new HashMap<String,Comando>();
		necnolmcitas.setTransitions(cmcnolmcitas);
		necnolmcitas.setDef(null);
		
		nfycnolmcitas.setTransicion(trcnolmcitas);
		servicios_activos.put(cnolmcitas.getNombre(),cnolmcitas);
		servicios_activos.put("cnolm_citas_m_or",cnolmcitas);
		servicios_activos.put("cnolm_citas_t_or",cnolmcitas);
		
		servicios_activos.put("cnolm_citas_od",cnolmcitas);
		servicios_activos.put("cnolm_citas_cm",cnolmcitas);
		

		
		/*Servicio cnolmcitas_od = new Servicio();
		cnolmcitas_od.setTimeout(3600000);
		cnolmcitas_od.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcnolmcitas_od = new NotificacionSalida();
		Map<Integer,Conexion> hmcnolmcitas_od = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcnolmcitas_od = new HashMap<Integer,String>();
		hmcnolmcitas_od.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcnolmcitas_od.put(new Integer(0),"##FECHA##");
		dmcnolmcitas_od.put(new Integer(1),"##HORA##");
		Contenido contcnolmcitas_od = new ContenidoSMS("Buen dia\nCentro Nacional de Ortodoncia sede las Mercedes le recuerda que su proxima cita es el ##FECHA## a las ##HORA##\n-Lo Esperamos-");
		notcnolmcitas_od.setContenido(contcnolmcitas_od);
		notcnolmcitas_od.setDatareplace(dmcnolmcitas_od);
		notcnolmcitas_od.setRoutes(hmcnolmcitas_od);
		Notificar nfycnolmcitas_od = new Notificar();
		nfycnolmcitas_od.setOut(notcnolmcitas_od);
		cnolmcitas_od.setRootcmd(nfycnolmcitas_od);
		cnolmcitas_od.setNombre("cnolm_citas");
		
		NotificacionEntrada necnolmcitas_od = new NotificacionEntrada();
		TransicionRegex trcnolmcitas_od = new TransicionRegex();
		trcnolmcitas_od.setIn(necnolmcitas_od);
		HashMap<String,Comando> cmcnolmcitas_od = new HashMap<String,Comando>();
		necnolmcitas_od.setTransitions(cmcnolmcitas_od);
		necnolmcitas_od.setDef(null);
		
		nfycnolmcitas_od.setTransicion(trcnolmcitas_od);
		
		servicios_activos.put(cnolmcitas_od.getNombre(),cnolmcitas_od);
		servicios_activos.put("cnolm_citas_m",cnolmcitas_od);
		servicios_activos.put("cnolm_citas_t",cnolmcitas_od);
		
		Servicio cnolmcitas_cm = new Servicio();
		cnolmcitas_cm.setTimeout(3600000);
		cnolmcitas_cm.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcnolmcitas_cm = new NotificacionSalida();
		Map<Integer,Conexion> hmcnolmcitas_cm = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcnolmcitas_cm = new HashMap<Integer,String>();
		hmcnolmcitas_cm.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcnolmcitas_cm.put(new Integer(0),"##FECHA##");
		dmcnolmcitas_cm.put(new Integer(1),"##HORA##");
		Contenido contcnolmcitas_cm = new ContenidoSMS("Buen dia\nCentro Nacional de Ortodoncia sede las Mercedes le recuerda que su proxima cita es el ##FECHA## a las ##HORA##\n-Lo Esperamos-");
		notcnolmcitas_cm.setContenido(contcnolmcitas_cm);
		notcnolmcitas_cm.setDatareplace(dmcnolmcitas_cm);
		notcnolmcitas_cm.setRoutes(hmcnolmcitas_cm);
		Notificar nfycnolmcitas_cm = new Notificar();
		nfycnolmcitas_cm.setOut(notcnolmcitas_cm);
		cnolmcitas_cm.setRootcmd(nfycnolmcitas_cm);
		cnolmcitas_cm.setNombre("cnolm_citas_cm");
		
		NotificacionEntrada necnolmcitas_cm = new NotificacionEntrada();
		TransicionRegex trcnolmcitas_cm = new TransicionRegex();
		trcnolmcitas_cm.setIn(necnolmcitas_cm);
		HashMap<String,Comando> cmcnolmcitas_cm = new HashMap<String,Comando>();
		necnolmcitas_cm.setTransitions(cmcnolmcitas_cm);
		necnolmcitas_cm.setDef(null);
		
		nfycnolmcitas_cm.setTransicion(trcnolmcitas_cm);
		
		servicios_activos.put(cnolmcitas_cm.getNombre(),cnolmcitas_cm);*/

		
		Servicio cnocuotas = new Servicio();
		cnocuotas.setTimeout(3600000);
		cnocuotas.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcnocuotas = new NotificacionSalida();
		Map<Integer,Conexion> hmcnocuotas = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcnocuotas = new HashMap<Integer,String>();
		hmcnocuotas.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcnocuotas.put(new Integer(0),"##N##");
		dmcnocuotas.put(new Integer(1),"##MONTO##");
		Contenido contcnocuotas = new ContenidoSMS("Centro Nacional de Ortodoncia le recuerda que UD posee##N## cuotas vencidas por Bs.F##MONTO##. Por favor comuniquese con nosotros al (212) 952-1259.");
		notcnocuotas.setContenido(contcnocuotas);
		notcnocuotas.setDatareplace(dmcnocuotas);
		notcnocuotas.setRoutes(hmcnocuotas);
		Notificar nfycnocuotas = new Notificar();
		nfycnocuotas.setOut(notcnocuotas);
		cnocuotas.setRootcmd(nfycnocuotas);
		cnocuotas.setNombre("cno_cuotas");
		
		NotificacionEntrada necnocuotas = new NotificacionEntrada();
		TransicionRegex trcnocuotas = new TransicionRegex();
		trcnocuotas.setIn(necnocuotas);
		HashMap<String,Comando> cmcnocuotas = new HashMap<String,Comando>();
		necnocuotas.setTransitions(cmcnocuotas);
		necnocuotas.setDef(null);
		
		nfycnocuotas.setTransicion(trcnocuotas);
		
		servicios_activos.put(cnocuotas.getNombre(),cnocuotas);
		
		Servicio semm = new Servicio();
		semm.setTimeout(3600000);
		semm.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notsemm = new NotificacionSalida();
		Map<Integer,Conexion> hmsemm = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmsemm = new HashMap<Integer,String>();
		hmsemm.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmsemm.put(new Integer(0),"##FECHA##");
		dmsemm.put(new Integer(1),"##PRE##");
		dmsemm.put(new Integer(2),"##POST##");
		dmsemm.put(new Integer(3),"##TPRE##");
		dmsemm.put(new Integer(4),"##TPOST##");
		dmsemm.put(new Integer(5),"##VENTA##");
		dmsemm.put(new Integer(6),"##META##");
		Contenido contsemm = new ContenidoSMS("Reporte AdsMedia dia ##FECHA##:\nPre:##PRE## Post:##POST##\n Tot.Pre:##TPRE## Tot.Post:##TPOST## VentasPre:##VENTA##\n Meta: ##META##%\nPowered by ION");
		notsemm.setContenido(contsemm);
		notsemm.setDatareplace(dmsemm);
		notsemm.setRoutes(hmsemm);
		Notificar nfysemm = new Notificar();
		nfysemm.setOut(notsemm);
		semm.setRootcmd(nfysemm);
		semm.setNombre("semmreport");
		
		NotificacionEntrada nesemm = new NotificacionEntrada();
		TransicionRegex trsemm = new TransicionRegex();
		trsemm.setIn(nesemm);
		HashMap<String,Comando> cmsemm = new HashMap<String,Comando>();
		nesemm.setTransitions(cmsemm);
		nesemm.setDef(null);
		
		nfysemm.setTransicion(trsemm);
		
		servicios_activos.put(semm.getNombre(),semm);
		
		Servicio cnoreport = new Servicio();
		cnoreport.setTimeout(3600000);
		cnoreport.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcnoreport = new NotificacionSalida();
		Map<Integer,Conexion> hmcnoreport = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcnoreport = new HashMap<Integer,String>();
		hmcnoreport.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcnoreport.put(new Integer(0),"##FECHA##");
		dmcnoreport.put(new Integer(1),"##CM##");
		dmcnoreport.put(new Integer(2),"##CT##");
		dmcnoreport.put(new Integer(3),"##C2##");
		dmcnoreport.put(new Integer(4),"##CUOTAS##");
		Contenido contcnoreport = new ContenidoSMS("Reporte Mensajes CNO dia ##FECHA##:\nCitas hoy M:##CM## Citas hoy T:##CT##\n Citas dia sig:##C2## Cuotas:##CUOTAS##\nPowered by AdsMedia");
		notcnoreport.setContenido(contcnoreport);
		notcnoreport.setDatareplace(dmcnoreport);
		notcnoreport.setRoutes(hmcnoreport);
		Notificar nfycnoreport = new Notificar();
		nfycnoreport.setOut(notcnoreport);
		cnoreport.setRootcmd(nfycnoreport);
		cnoreport.setNombre("cnoreport");
		
		NotificacionEntrada necnoreport = new NotificacionEntrada();
		TransicionRegex trcnoreport = new TransicionRegex();
		trcnoreport.setIn(necnoreport);
		HashMap<String,Comando> cmcnoreport = new HashMap<String,Comando>();
		necnoreport.setTransitions(cmcnoreport);
		necnoreport.setDef(null);
		
		nfycnoreport.setTransicion(trcnoreport);
		
		servicios_activos.put(cnoreport.getNombre(),cnoreport);
		
		Servicio cnoreportlm = new Servicio();
		cnoreportlm.setTimeout(3600000);
		cnoreportlm.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notcnoreportlm = new NotificacionSalida();
		Map<Integer,Conexion> hmcnoreportlm = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmcnoreportlm = new HashMap<Integer,String>();
		hmcnoreportlm.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmcnoreportlm.put(new Integer(0),"##FECHA##");
		dmcnoreportlm.put(new Integer(1),"##CM##");
		dmcnoreportlm.put(new Integer(2),"##CT##");
		dmcnoreportlm.put(new Integer(3),"##C2##");
		dmcnoreportlm.put(new Integer(4),"##COD##");
		dmcnoreportlm.put(new Integer(5),"##CCM##");
		Contenido contcnoreportlm = new ContenidoSMS("Reporte Mensajes CNO Las Mercedes dia ##FECHA##:\nCitas hoy M:##CM## Citas hoy T:##CT##\n Citas dia sig:##C2## \n Citas dia sig:##COD## (OD) \n Citas dia sig:##CCM## (CM)");
		notcnoreportlm.setContenido(contcnoreportlm);
		notcnoreportlm.setDatareplace(dmcnoreportlm);
		notcnoreportlm.setRoutes(hmcnoreportlm);
		Notificar nfycnoreportlm = new Notificar();
		nfycnoreportlm.setOut(notcnoreportlm);
		cnoreportlm.setRootcmd(nfycnoreportlm);
		cnoreportlm.setNombre("cnoreportlm");
		
		NotificacionEntrada necnoreportlm = new NotificacionEntrada();
		TransicionRegex trcnoreportlm = new TransicionRegex();
		trcnoreportlm.setIn(necnoreportlm);
		HashMap<String,Comando> cmcnoreportlm = new HashMap<String,Comando>();
		necnoreportlm.setTransitions(cmcnoreportlm);
		necnoreportlm.setDef(null);
		
		nfycnoreportlm.setTransicion(trcnoreportlm);
		
		servicios_activos.put(cnoreportlm.getNombre(),cnoreportlm);
		
		Servicio prendalistacentral = new Servicio();
		prendalistacentral.setTimeout(3600000);
		prendalistacentral.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notprendalistacentral = new NotificacionSalida();
		Map<Integer,Conexion> hmprendalistacentral = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmprendalistacentral = new HashMap<Integer,String>();
		hmprendalistacentral.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmprendalistacentral.put(new Integer(0),"##TIENDA##");
		dmprendalistacentral.put(new Integer(1),"##GERENTE##");
		dmprendalistacentral.put(new Integer(2),"##ODS##");
		Contenido contprendalistacentral = new ContenidoSMS("Buen Dia!\nQuickPress ##TIENDA## le informa que su orden esta lista para ser retirada!\nGracias por preferirnos!");
		notprendalistacentral.setContenido(contprendalistacentral);
		notprendalistacentral.setDatareplace(dmprendalistacentral);
		notprendalistacentral.setRoutes(hmprendalistacentral);
		NotificarUnaSolaVez nfyprendalistacentral = new NotificarUnaSolaVez(0);
		nfyprendalistacentral.setOut(notprendalistacentral);
		prendalistacentral.setRootcmd(nfyprendalistacentral);
		prendalistacentral.setNombre("prendalistacentral");
		
		NotificacionEntrada neprendalistacentral = new NotificacionEntrada();
		TransicionRegex trprendalistacentral = new TransicionRegex();
		trprendalistacentral.setIn(neprendalistacentral);
		HashMap<String,Comando> cmprendalistacentral = new HashMap<String,Comando>();
		neprendalistacentral.setTransitions(cmprendalistacentral);
		neprendalistacentral.setDef(null);
		
		nfyprendalistacentral.setTransicion(trprendalistacentral);
		
		servicios_activos.put(prendalistacentral.getNombre(),prendalistacentral);
		
		
		
		Servicio cvmed = new Servicio();
		cvmed.setTimeout(21600000);
		cvmed.setGrpcnx(getGrupoConexion(new Long(1)));
		
		NotificacionEntrada necvmed = new NotificacionEntrada();
		
		HashMap<String,Comando> comandoscvmed = new HashMap<String,Comando>();
		
		TransicionRegex trcvmed = new TransicionRegex();
		
		EjecutarTransicion execcvmed = new EjecutarTransicion();
		
		ProcesarVisita visitacvmed = new ProcesarVisita();
		
		ProcesarVisitaFarmacia visitafar = new ProcesarVisitaFarmacia();
		
		visitafar.setTransicion(trcvmed);
		
		visitacvmed.setTransicion(trcvmed);
		
		ProcesarCierre cierrecvmed = new ProcesarCierre();

		Ayuda ayudacvmed = new Ayuda();

		ayudacvmed.setTransicion(trcvmed);
		
		Anular anulacvmed = new Anular();
		
		anulacvmed.setTransicion(trcvmed);
		
		Precio preciocvmed = new Precio();
		
		anulacvmed.setTransicion(trcvmed);
		
		comandoscvmed.put("(?i)CIERRE", cierrecvmed);
		comandoscvmed.put("(?i)AYUDA", ayudacvmed);
		comandoscvmed.put("(?i)ANUL", anulacvmed);
		comandoscvmed.put("(?i)P", preciocvmed);
		comandoscvmed.put("(?i)F\\s*(\\d{4})\\s*((A[J|C|M|G|D]))?(F)?\\s*", visitafar);
		necvmed.setTransitions(comandoscvmed);
		necvmed.setDef(visitacvmed);
		trcvmed.setIn(necvmed);
		execcvmed.setTr(trcvmed);
		cvmed.setRootcmd(execcvmed);
		cvmed.setNombre("cvmed");
		
		servicios_activos.put(cvmed.getNombre(),cvmed);
		
		Servicio alarma_gpt = new Servicio();
		alarma_gpt.setTimeout(3600000);
		alarma_gpt.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notalarma_gpt = new NotificacionSalida();
		Map<Integer,Conexion> hmalarma_gpt = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmalarma_gpt = new HashMap<Integer,String>();
		hmalarma_gpt.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmalarma_gpt.put(new Integer(0),"##NOMBRE##");
		dmalarma_gpt.put(new Integer(1),"##NUM##");
		Contenido contalarma_gpt = new ContenidoSMS("");
		notalarma_gpt.setContenido(contalarma_gpt);
		notalarma_gpt.setDatareplace(dmalarma_gpt);
		notalarma_gpt.setRoutes(hmalarma_gpt);
		Notificar nfyalarma_gpt = new Notificar();
		nfyalarma_gpt.setOut(notalarma_gpt);
		alarma_gpt.setRootcmd(nfyalarma_gpt);
		alarma_gpt.setNombre("alarma_gpt");
		
		
		servicios_activos.put(alarma_gpt.getNombre(),alarma_gpt);
		
		Servicio alarma_rep = new Servicio();
		alarma_rep.setTimeout(3600000);
		alarma_rep.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notalarma_rep = new NotificacionSalida();
		Map<Integer,Conexion> hmalarma_rep = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmalarma_rep = new HashMap<Integer,String>();
		hmalarma_rep.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmalarma_rep.put(new Integer(0),"##NUM##");
		Contenido contalarma_rep = new ContenidoSMS("Llevas ##NUM##/10 medicos. Para no olvidar reportar\ntu Neukob debes tomar\ncon Hivit recarga tus pilas\ny medicos ve a visitar...");
		notalarma_rep.setContenido(contalarma_rep);
		notalarma_rep.setDatareplace(dmalarma_rep);
		notalarma_rep.setRoutes(hmalarma_rep);
		Notificar nfyalarma_rep = new Notificar();
		nfyalarma_rep.setOut(notalarma_rep);
		alarma_rep.setRootcmd(nfyalarma_rep);
		alarma_rep.setNombre("alarma_rep");
		
		
		
		servicios_activos.put(alarma_rep.getNombre(),alarma_rep);
		
		
		//Quickylub
		Servicio quickylub = new Servicio();
		quickylub.setTimeout(3600000);
		quickylub.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notquickylub = new NotificacionSalida();
		Map<Integer,Conexion> hmquickylub = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmquickylub = new HashMap<Integer,String>();
		hmquickylub.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmquickylub.put(new Integer(0),"##NOMBRE##");
		dmquickylub.put(new Integer(1),"##CARRO##");
		Contenido contquickylub = new ContenidoSMS("Sr.(a) ##NOMBRE## Quicky-Lub le recuerda que su vehiculo placa ##CARRO## debe estar proximo para un nuevo cambio de aceite y filtro");
		notquickylub.setContenido(contquickylub);
		notquickylub.setDatareplace(dmquickylub);
		notquickylub.setRoutes(hmquickylub);
		Notificar nfyquickylub = new Notificar();
		nfyquickylub.setOut(notquickylub);
		quickylub.setRootcmd(nfyquickylub);
		quickylub.setNombre("quickylub90");
		
		NotificacionEntrada nequickylub = new NotificacionEntrada();
		TransicionRegex trquickylub = new TransicionRegex();
		trquickylub.setIn(nequickylub);
		HashMap<String,Comando> cmquickylub = new HashMap<String,Comando>();
		nequickylub.setTransitions(cmquickylub);
		nequickylub.setDef(null);
		
		nfyquickylub.setTransicion(trquickylub);
		
		
		servicios_activos.put(quickylub.getNombre(),quickylub);
		
		
		/////////////////////////////////////////////////////
		//Gimnasio Struktura
		
		//Struktura Aprobada Domiciliacion Tarjeta
		
		Servicio strukaprobado = new Servicio();
		strukaprobado.setTimeout(3600000);
		strukaprobado.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notstrukaprobado = new NotificacionSalida();
		Map<Integer,Conexion> hmstrukaprobado = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmstrukaprobado = new HashMap<Integer,String>();
		hmstrukaprobado.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmstrukaprobado.put(new Integer(0),"##APELLIDO##");
		dmstrukaprobado.put(new Integer(1),"##TARJETA##");
		dmstrukaprobado.put(new Integer(2),"##DESCRIPCION##");
		dmstrukaprobado.put(new Integer(3),"##CODIGO##");
		dmstrukaprobado.put(new Integer(4),"##MES##");
		
		Contenido contstrukaprobado = new ContenidoSMS("Estimado Sr.(a) ##APELLIDO##, STRUKTURA le informa que se ha realizado el cargo a su tarjeta #**##TARJETA## por concepto ##DESCRIPCION##  ##MES##. Recuerde retirar su Factura.");
		notstrukaprobado.setContenido(contstrukaprobado);
		notstrukaprobado.setDatareplace(dmstrukaprobado);
		notstrukaprobado.setRoutes(hmstrukaprobado);
		Notificar nfystrukaprobado = new Notificar();
		nfystrukaprobado.setOut(notstrukaprobado);
		strukaprobado.setRootcmd(nfystrukaprobado);
		strukaprobado.setNombre("strukaprobado");
		
		NotificacionEntrada nestrukaprobado = new NotificacionEntrada();
		TransicionRegex trstrukaprobado = new TransicionRegex();
		trstrukaprobado.setIn(nestrukaprobado);
		HashMap<String,Comando> cmstrukaprobado = new HashMap<String,Comando>();
		nestrukaprobado.setTransitions(cmstrukaprobado);
		nestrukaprobado.setDef(null);
		
		nfystrukaprobado.setTransicion(trstrukaprobado);
		
		
		servicios_activos.put(strukaprobado.getNombre(),strukaprobado);
		
		//Struktura Rechazo Domiciliacion Tarjeta

		Servicio strukrechazo = new Servicio();
		strukrechazo.setTimeout(3600000);
		strukrechazo.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notstrukrechazo = new NotificacionSalida();
		Map<Integer,Conexion> hmstrukrechazo = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmstrukrechazo = new HashMap<Integer,String>();
		hmstrukrechazo.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmstrukrechazo.put(new Integer(0),"##APELLIDO##");
		dmstrukrechazo.put(new Integer(1),"##TARJETA##");
		dmstrukrechazo.put(new Integer(2),"##DESCRIPCION##");
		dmstrukrechazo.put(new Integer(3),"##CODIGO##");
		dmstrukrechazo.put(new Integer(4),"##MES##");
		
	
		
		Contenido contstrukrechazo = new ContenidoSMS("Estimado Sr.(a) ##APELLIDO##, STRUKTURA le informa que el cargo a su tarjeta #**##TARJETA## correspondiente ##DESCRIPCION## ##MES##. NO FUE AUTORIZADA. Por favor cancele recepcion.");
		notstrukrechazo.setContenido(contstrukrechazo);
		notstrukrechazo.setDatareplace(dmstrukrechazo);
		notstrukrechazo.setRoutes(hmstrukrechazo);
		Notificar nfystrukrechazo = new Notificar();
		nfystrukrechazo.setOut(notstrukrechazo);
		strukrechazo.setRootcmd(nfystrukrechazo);
		strukrechazo.setNombre("strukrechazo");
		
		NotificacionEntrada nestrukrechazo = new NotificacionEntrada();
		TransicionRegex trstrukrechazo = new TransicionRegex();
		trstrukrechazo.setIn(nestrukrechazo);
		HashMap<String,Comando> cmstrukrechazo = new HashMap<String,Comando>();
		nestrukrechazo.setTransitions(cmstrukrechazo);
		nestrukrechazo.setDef(null);
		
		nfystrukrechazo.setTransicion(trstrukrechazo);
		
		
		servicios_activos.put(strukrechazo.getNombre(),strukrechazo);
		
		
		
		//Struktura Tarjeta Por Vencer
		
		Servicio strukporvencer = new Servicio();
		strukporvencer.setTimeout(3600000);
		strukporvencer.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notstrukporvencer = new NotificacionSalida();
		Map<Integer,Conexion> hmstrukporvencer = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmstrukporvencer = new HashMap<Integer,String>();
		hmstrukporvencer.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmstrukporvencer.put(new Integer(0),"##APELLIDO##");
		dmstrukporvencer.put(new Integer(1),"##TARJETA##");
		
	
		
		Contenido contstrukporvencer = new ContenidoSMS("Estimado Sr.(a) ##APELLIDO##, STRUKTURA le informa que debe actualizar en recepcion los datos de su tarjeta #**##TARJETA## la cual esta proxima a vencerse. Feliz dia!");
		notstrukporvencer.setContenido(contstrukporvencer);
		notstrukporvencer.setDatareplace(dmstrukporvencer);
		notstrukporvencer.setRoutes(hmstrukporvencer);
		Notificar nfystrukporvencer = new Notificar();
		nfystrukporvencer.setOut(notstrukporvencer);
		strukporvencer.setRootcmd(nfystrukporvencer);
		strukporvencer.setNombre("strukporvencer");
		
		NotificacionEntrada nestrukporvencer = new NotificacionEntrada();
		TransicionRegex trstrukporvencer = new TransicionRegex();
		trstrukporvencer.setIn(nestrukporvencer);
		HashMap<String,Comando> cmstrukporvencer = new HashMap<String,Comando>();
		nestrukporvencer.setTransitions(cmstrukporvencer);
		nestrukporvencer.setDef(null);
		
		nfystrukporvencer.setTransicion(trstrukporvencer);
		
		
		servicios_activos.put(strukporvencer.getNombre(),strukporvencer);
		
		
		//Struktura Tarjeta Vencida
		
		Servicio strukvencida = new Servicio();
		strukvencida.setTimeout(3600000);
		strukvencida.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notstrukvencida = new NotificacionSalida();
		Map<Integer,Conexion> hmstrukvencida = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmstrukvencida = new HashMap<Integer,String>();
		hmstrukvencida.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmstrukvencida.put(new Integer(0),"##APELLIDO##");
		dmstrukvencida.put(new Integer(1),"##TARJETA##");
		
	
		
		Contenido contstrukvencida = new ContenidoSMS("Estimado Sr.(a) ##APELLIDO##, STRUKTURA le informa que debe actualizar en recepcion los datos de su tarjeta #**##TARJETA## la cual esta vencida. Feliz dia!");
		notstrukvencida.setContenido(contstrukvencida);
		notstrukvencida.setDatareplace(dmstrukvencida);
		notstrukvencida.setRoutes(hmstrukvencida);
		Notificar nfystrukvencida = new Notificar();
		nfystrukvencida.setOut(notstrukvencida);
		strukvencida.setRootcmd(nfystrukvencida);
		strukvencida.setNombre("strukvencida");
		
		NotificacionEntrada nestrukvencida = new NotificacionEntrada();
		TransicionRegex trstrukvencida = new TransicionRegex();
		trstrukvencida.setIn(nestrukvencida);
		HashMap<String,Comando> cmstrukvencida = new HashMap<String,Comando>();
		nestrukvencida.setTransitions(cmstrukvencida);
		nestrukvencida.setDef(null);
		
		nfystrukvencida.setTransicion(trstrukvencida);
		
		
		servicios_activos.put(strukvencida.getNombre(),strukvencida);
		
		
		
		//Struktura Mensaje Cumpleaños
		
		Servicio strukcumple = new Servicio();
		strukcumple.setTimeout(3600000);
		strukcumple.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notstrukcumple = new NotificacionSalida();
		Map<Integer,Conexion> hmstrukcumple = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmstrukcumple = new HashMap<Integer,String>();
		hmstrukcumple.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmstrukcumple.put(new Integer(0),"##APELLIDO##");
		
	
		
		Contenido contstrukcumple = new ContenidoSMS("Querido Sr.(a) ##APELLIDO##, STRUKTURA le desea Felicidad, Salud y Bienestar hoy en su dia... Feliz Cumpleaños!!!");
		notstrukcumple.setContenido(contstrukcumple);
		notstrukcumple.setDatareplace(dmstrukcumple);
		notstrukcumple.setRoutes(hmstrukcumple);
		Notificar nfystrukcumple = new Notificar();
		nfystrukcumple.setOut(notstrukcumple);
		strukcumple.setRootcmd(nfystrukcumple);
		strukcumple.setNombre("strukcumple");
		

		
		NotificacionEntrada nestrukcumple = new NotificacionEntrada();
		TransicionRegex trstrukcumple = new TransicionRegex();
		trstrukcumple.setIn(nestrukcumple);
		HashMap<String,Comando> cmstrukcumple = new HashMap<String,Comando>();
		nestrukcumple.setTransitions(cmstrukcumple);
		nestrukcumple.setDef(null);
		
		nfystrukcumple.setTransicion(trstrukcumple);
		
		
		servicios_activos.put(strukcumple.getNombre(),strukcumple);
		
		
		
		
		
		
		
		///////////////////////////////////////////////////
		

		
//Servicio Mascotas Market SMS Registro
		
		Servicio mascotalistaR = new Servicio();
		mascotalistaR.setTimeout(3600000);
		mascotalistaR.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notmascotalistaR = new NotificacionSalida();
		Map<Integer,Conexion> hmmascotalistaR = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmmascotalistaR = new HashMap<Integer,String>();
		hmmascotalistaR.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmmascotalistaR.put(new Integer(0),"##MASCOTA##");
		
	
		
		Contenido contmascotalistaR = new ContenidoSMS("##MASCOTA## ha ingresado a Mascotas Market, al culminar el servicio le sera comunicado por esta via. Gracias por confiar en nosotros UFFF AMO A MI MASCOTA!");
		notmascotalistaR.setContenido(contmascotalistaR);
		notmascotalistaR.setDatareplace(dmmascotalistaR);
		notmascotalistaR.setRoutes(hmmascotalistaR);
		Notificar nfymascotalistaR = new Notificar();
		nfymascotalistaR.setOut(notmascotalistaR);
		mascotalistaR.setRootcmd(nfymascotalistaR);
		mascotalistaR.setNombre("mascotalistaR");
		

		
		NotificacionEntrada nemascotalistaR = new NotificacionEntrada();
		TransicionRegex trmascotalistaR = new TransicionRegex();
		trmascotalistaR.setIn(nemascotalistaR);
		HashMap<String,Comando> cmmascotalistaR = new HashMap<String,Comando>();
		nemascotalistaR.setTransitions(cmmascotalistaR);
		nemascotalistaR.setDef(null);
		
		nfymascotalistaR.setTransicion(trmascotalistaR);
		
		
		servicios_activos.put(mascotalistaR.getNombre(),mascotalistaR);
		
		///////////////////////////////////////////////////////////////////
		
//Servicio Mascotas Market SMS Listo
		
		Servicio mascotalistaL = new Servicio();
		mascotalistaL.setTimeout(3600000);
		mascotalistaL.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notmascotalistaL = new NotificacionSalida();
		Map<Integer,Conexion> hmmascotalistaL = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmmascotalistaL = new HashMap<Integer,String>();
		hmmascotalistaL.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmmascotalistaL.put(new Integer(0),"##MASCOTA##");
		
	
		
		Contenido contmascotalistaL = new ContenidoSMS("El servicio en Mascotas Market para ##MASCOTA## ha culminado. Para informacion llame al 02129874324 Santa Paula o 02129446066 Trinidad. UFFF AMO A MI MASCOTA!");
		notmascotalistaL.setContenido(contmascotalistaL);
		notmascotalistaL.setDatareplace(dmmascotalistaL);
		notmascotalistaL.setRoutes(hmmascotalistaL);
		Notificar nfymascotalistaL = new Notificar();
		nfymascotalistaL.setOut(notmascotalistaL);
		mascotalistaL.setRootcmd(nfymascotalistaL);
		mascotalistaL.setNombre("mascotalistaL");
		

		
		NotificacionEntrada nemascotalistaL = new NotificacionEntrada();
		TransicionRegex trmascotalistaL = new TransicionRegex();
		trmascotalistaL.setIn(nemascotalistaL);
		HashMap<String,Comando> cmmascotalistaL = new HashMap<String,Comando>();
		nemascotalistaL.setTransitions(cmmascotalistaL);
		nemascotalistaL.setDef(null);
		
		nfymascotalistaL.setTransicion(trmascotalistaL);
		
		
		servicios_activos.put(mascotalistaL.getNombre(),mascotalistaL);
		
		///////////////////////////////////////////////////////////////////
		
//Servicio Mascotas Market SMS Registro Santa Paula
		
		Servicio mascotalistaRS = new Servicio();
		mascotalistaRS.setTimeout(3600000);
		mascotalistaRS.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notmascotalistaRS = new NotificacionSalida();
		Map<Integer,Conexion> hmmascotalistaRS = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmmascotalistaRS = new HashMap<Integer,String>();
		hmmascotalistaRS.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmmascotalistaRS.put(new Integer(0),"##MASCOTA##");
		
	
		
		Contenido contmascotalistaRS = new ContenidoSMS("##MASCOTA## ha ingresado a Mascotas Market, al culminar el servicio le sera comunicado por esta via. Gracias por confiar en nosotros UFFF AMO A MI MASCOTA!");
		notmascotalistaRS.setContenido(contmascotalistaRS);
		notmascotalistaRS.setDatareplace(dmmascotalistaRS);
		notmascotalistaRS.setRoutes(hmmascotalistaRS);
		Notificar nfymascotalistaRS = new Notificar();
		nfymascotalistaRS.setOut(notmascotalistaRS);
		mascotalistaRS.setRootcmd(nfymascotalistaRS);
		mascotalistaRS.setNombre("mascotalistaRS");
		

		
		NotificacionEntrada nemascotalistaRS = new NotificacionEntrada();
		TransicionRegex trmascotalistaRS = new TransicionRegex();
		trmascotalistaRS.setIn(nemascotalistaRS);
		HashMap<String,Comando> cmmascotalistaRS = new HashMap<String,Comando>();
		nemascotalistaRS.setTransitions(cmmascotalistaRS);
		nemascotalistaRS.setDef(null);
		
		nfymascotalistaRS.setTransicion(trmascotalistaRS);
		
		
		servicios_activos.put(mascotalistaRS.getNombre(),mascotalistaRS);
		
		///////////////////////////////////////////////////////////////////
		
//Servicio Mascotas Market SMS Listo Santa Paula
		
		Servicio mascotalistaLS = new Servicio();
		mascotalistaLS.setTimeout(3600000); 
		mascotalistaLS.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notmascotalistaLS = new NotificacionSalida();
		Map<Integer,Conexion> hmmascotalistaLS = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmmascotalistaLS = new HashMap<Integer,String>();
		hmmascotalistaLS.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmmascotalistaLS.put(new Integer(0),"##MASCOTA##");
		
	
		
		Contenido contmascotalistaLS = new ContenidoSMS("El servicio en Mascotas Market para ##MASCOTA## ha culminado. Para informacion llame al 02129874324 Santa Paula o 02129446066 Trinidad. UFFF AMO A MI MASCOTA!");
		notmascotalistaLS.setContenido(contmascotalistaLS);
		notmascotalistaLS.setDatareplace(dmmascotalistaLS);
		notmascotalistaLS.setRoutes(hmmascotalistaLS);
		Notificar nfymascotalistaLS = new Notificar();
		nfymascotalistaLS.setOut(notmascotalistaLS);
		mascotalistaLS.setRootcmd(nfymascotalistaLS);
		mascotalistaLS.setNombre("mascotalistaLS");
		

		
		NotificacionEntrada nemascotalistaLS = new NotificacionEntrada();
		TransicionRegex trmascotalistaLS = new TransicionRegex();
		trmascotalistaLS.setIn(nemascotalistaLS);
		HashMap<String,Comando> cmmascotalistaLS = new HashMap<String,Comando>();
		nemascotalistaLS.setTransitions(cmmascotalistaLS);
		nemascotalistaLS.setDef(null);
		
		nfymascotalistaLS.setTransicion(trmascotalistaLS);
		
		
		servicios_activos.put(mascotalistaLS.getNombre(),mascotalistaLS);
		
		///////////////////////////////////////////////////////////////////
		// Movil Plus
		Servicio movilplus = new Servicio();
		movilplus.setTimeout(0);
		movilplus.setGrpcnx(getGrupoConexion(new Long(1)));
		movilplus.setPredeterminado(false);

		//log.debug("Servicio WWW Grupo: "  + movilplus.getGrpcnx().getNombre());
		NotificacionSalida mpnotog = new NotificacionSalida();
		Map<Integer,Conexion> mphmog = new HashMap<Integer,Conexion>();
		Map<Integer,String> mpdmog = new HashMap<Integer,String>();
		mphmog.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		mpdmog.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido mpcontog = new ContenidoSMS("##MSG##");
		mpnotog.setContenido(mpcontog);
		mpnotog.setDatareplace(mpdmog);
		mpnotog.setRoutes(hmog);
		Notificar mpnfyog = new Notificar();
		mpnfyog.setOut(mpnotog);
		
		
		
		movilplus.setRootcmd(mpnfyog);
		movilplus.setNombre("WWW_mp");
		
		LlamadaMp llamadaMp = new LlamadaMp("http://74.53.72.146:8080");
		LlamadaMp llamadaMpdev = new LlamadaMp("http://207.44.230.111:8080");
		NotificacionEntrada mpneog = new NotificacionEntrada();
		TransicionRegex mptrog = new TransicionRegex();
		mptrog.setIn(mpneog);
		HashMap<String,Comando> mpcmog = new HashMap<String,Comando>();
		mpcmog.put("(?i)^GUIA", llamadaMp);
		mpcmog.put("(?i)^DOCUMENTOS", llamadaMp);
		mpcmog.put("(?i)^APROBACION", llamadaMp);
		mpcmog.put("(?i)^APRO", llamadaMp);
		mpcmog.put("(?i)^GIRO", llamadaMp);
		mpcmog.put("(?i)^ENTREGA", llamadaMp);
		mpcmog.put("(?i)^TASA", llamadaMp);
		mpcmog.put("(?i)^DOC", llamadaMp);
		mpcmog.put("(?i)^SALDO", llamadaMpdev);
		mpcmog.put("(?i)^PAY", llamadaMpdev);
		mpcmog.put("(?i)^POS", llamadaMpdev);
		mpcmog.put("(?i)^MOV", llamadaMpdev);
		
		mpneog.setDef(llamadaMpdev);
		mpnfyog.setTransicion(mptrog);
		mpneog.setTransitions(mpcmog);
		llamadaMp.setTransicion(mptrog);
		
		servicios_activos.put(movilplus.getNombre(), movilplus);
		
		Servicio mpogin = new Servicio();
		mpogin.setTimeout(0);
		mpogin.setPredeterminado(true);
		mpogin.setGrpcnx(getGrupoConexion(new Long(1)));
		EjecutarTransicion mpetogin = new EjecutarTransicion();
		mpetogin.setTr(mptrog);
		mpogin.setRootcmd(mpetogin);
		mpogin.setNombre("inbox_mp");
		
		servicios_activos.put(mpogin.getNombre(),mpogin);
		
		//Inicio de servicio MUD
		Servicio mudformin = new Servicio();
		mudformin.setTimeout(0);
		//mudformin.setPredeterminado(true);
		mudformin.setGrpcnx(getGrupoConexion(new Long(11)));
	
		EjecutarTransicion etmud = new EjecutarTransicion();
		
		
		LlamadaMUD llamadamud = new LlamadaMUD();
		
		NotificacionEntrada mudneog = new NotificacionEntrada();
		TransicionRegex mudtrog = new TransicionRegex();
		mudtrog.setIn(mudneog);
		HashMap<String,Comando> mudcmog = new HashMap<String,Comando>();
		
		
		mudneog.setDef(llamadamud);
		mudneog.setTransitions(mudcmog);
		llamadamud.setTransicion(mudtrog);
		
		etmud.setTr(mudtrog);
		mudformin.setRootcmd(etmud);
		mudformin.setNombre("inbox_mud");
		
//		NotificacionSalida notmud = new NotificacionSalida();
//		Map<Integer,Conexion> hmmud = new HashMap<Integer,Conexion>();
//		Map<Integer,String> dmmud = new HashMap<Integer,String>();
//		hmmud.put(new Integer(0),mcnx.getConexiones_activas().get(0));
//		dmmud.put(new Integer(0),"##MSG##");
//
//	
//		Contenido contmud = new ContenidoSMS("##MSG##");
//		notmud.setContenido(contmud);
//		notmud.setDatareplace(dmmud);
//		notmud.setRoutes(hmmud);
//		Notificar nfymud = new Notificar();
//		nfymud.setOut(notmud);

		
		servicios_activos.put(mudformin.getNombre(), mudformin);
		
		//WWW_MUD
		
		Servicio mud = new Servicio();
		mud.setTimeout(0);
		mud.setGrpcnx(getGrupoConexion(new Long(11)));
		//log.debug("Servicio WWW Grupo: "  + ojo.getGrpcnx().getNombre());
		NotificacionSalida notmudwww = new NotificacionSalida();
		Map<Integer,Conexion> hmmudwww = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmmudwww = new HashMap<Integer,String>();
		hmmudwww.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmmudwww.put(new Integer(0),"##MSG##");
		//dm.put(new Integer(1),"##MONTO##");
		Contenido contmudwww = new ContenidoSMS("##MSG##");
		notmudwww.setContenido(contmudwww);
		notmudwww.setDatareplace(dmmudwww);
		notmudwww.setRoutes(hmmudwww);
		Notificar nfymudwww = new Notificar();
		nfymudwww.setOut(notmudwww);
		nfymudwww.setTransicion(mudtrog);
		mud.setRootcmd(nfymudwww);
		mud.setNombre("WWW_mud");
		servicios_activos.put(mud.getNombre(), mud);
		
		//FIn de servicio MUD
		
		Servicio ojoformin = new Servicio();
		ojoformin.setTimeout(0);
		ojoformin.setPredeterminado(true);
		ojoformin.setGrpcnx(getGrupoConexion(new Long(1)));
	
		EjecutarTransicion etojo = new EjecutarTransicion();
		
		TransicionRegex transojo = new TransicionRegex();
		
		LlamadaOjo llamadaojo = new LlamadaOjo("http://205.186.130.68/totalizacion");
		NotificacionEntrada ne2ojo = new NotificacionEntrada();
		transojo.setIn(ne2ojo);
		HashMap<String,Comando> cm2ojo = new HashMap<String,Comando>();
		ne2ojo.setDef(llamadaojo);
		
		ne2ojo.setTransitions(cm2ojo);
		
		etojo.setTr(transojo);


		ojoformin.setRootcmd(etojo);
		ojoformin.setNombre("inbox_ojo");
		
		servicios_activos.put(ojoformin.getNombre(),ojoformin);
		
		
		
		
		//ALERTA_OJO
		
		Servicio alerta_ojo = new Servicio();
		alerta_ojo.setTimeout(0);
		alerta_ojo.setGrpcnx(getGrupoConexion(new Long(11)));
		NotificacionSalida notalerta_ojo = new NotificacionSalida();
		Map<Integer,Conexion> hmalerta_ojo = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmalerta_ojo = new HashMap<Integer,String>();
		hmalerta_ojo.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmalerta_ojo.put(new Integer(0),"##LINEA1E##");
		dmalerta_ojo.put(new Integer(1),"##LINEA1S##");
		dmalerta_ojo.put(new Integer(2),"##LINEA2E##");
		dmalerta_ojo.put(new Integer(3),"##LINEA2S##");
		dmalerta_ojo.put(new Integer(4),"##LINEA3E##");
		dmalerta_ojo.put(new Integer(5),"##LINEA3S##");
		dmalerta_ojo.put(new Integer(6),"##LINEA4E##");
		dmalerta_ojo.put(new Integer(7),"##LINEA4S##");
		dmalerta_ojo.put(new Integer(8),"##LINEA5E##");
		dmalerta_ojo.put(new Integer(9),"##LINEA5S##");
		dmalerta_ojo.put(new Integer(10),"##LINEA6E##");
		dmalerta_ojo.put(new Integer(11),"##LINEA6S##");
		dmalerta_ojo.put(new Integer(12),"##LINEA7E##");
		dmalerta_ojo.put(new Integer(13),"##LINEA7S##");
		dmalerta_ojo.put(new Integer(14),"##LINEA8E##");
		dmalerta_ojo.put(new Integer(15),"##LINEA8S##");
		Contenido contalerta_ojo = new ContenidoSMS("Linea: Entrantes/Salientes\n1592295: ##LINEA1E##/##LINEA1S##,\n1592275: ##LINEA2E##/##LINEA2S##,\n1991984: ##LINEA3E##/##LINEA3S##,\n" +
				"1592299: ##LINEA4E##/##LINEA4S##,\n1422345: ##LINEA5E##/##LINEA5S##,\n1991985: ##LINEA6E##/##LINEA6S##,\n1422348: ##LINEA7E##/##LINEA7S##,\n" +
				"1422346: ##LINEA8E##/##LINEA8S##, ");
		notalerta_ojo.setContenido(contalerta_ojo);
		notalerta_ojo.setDatareplace(dmalerta_ojo);
		notalerta_ojo.setRoutes(hmalerta_ojo);
		Notificar nfyalerta_ojo = new Notificar();
		nfyalerta_ojo.setOut(notalerta_ojo);
		alerta_ojo.setRootcmd(nfyalerta_ojo);
		alerta_ojo.setNombre("alerta_ojo");
		
		NotificacionEntrada nealerta_ojo = new NotificacionEntrada();
		TransicionRegex tralerta_ojo = new TransicionRegex();
		tralerta_ojo.setIn(nealerta_ojo);
		HashMap<String,Comando> cmalerta_ojo = new HashMap<String,Comando>();
		nealerta_ojo.setTransitions(cmalerta_ojo);
		nealerta_ojo.setDef(nfyalerta_ojo);
		
		nfyalerta_ojo.setTransicion(tralerta_ojo);
		
		servicios_activos.put(alerta_ojo.getNombre(),alerta_ojo);
		
		//Prendalista para tintoreria la Castellana (Separada de quickpress)
		
		Servicio prendalistacastellana = new Servicio();
		prendalistacastellana.setTimeout(3600000);
		prendalistacastellana.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notprendalistacastellana = new NotificacionSalida();
		Map<Integer,Conexion> hmprendalistacastellana = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmprendalistacastellana = new HashMap<Integer,String>();
		hmprendalistacastellana.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmprendalistacastellana.put(new Integer(0),"##ORDEN##");
		Contenido contprendalistacastellana = new ContenidoSMS("Buen Dia!\nLa Tintoreria de La Castellana le informa que su orden esta lista para ser retirada!\nGracias!");
		notprendalistacastellana.setContenido(contprendalistacastellana);
		notprendalistacastellana.setDatareplace(dmprendalistacastellana);
		notprendalistacastellana.setRoutes(hmprendalistacastellana);
		NotificarUnaSolaVez nfyprendalistacastellana = new NotificarUnaSolaVez(0);
		nfyprendalistacastellana.setOut(notprendalistacastellana);
		prendalistacastellana.setRootcmd(nfyprendalistacastellana);
		prendalistacastellana.setNombre("prendalistacastellana");
		
		NotificacionEntrada neprendalistacastellana = new NotificacionEntrada();
		TransicionRegex trprendalistacastellana = new TransicionRegex();
		trprendalistacastellana.setIn(neprendalistacastellana);
		HashMap<String,Comando> cmprendalistacastellana = new HashMap<String,Comando>();
		neprendalistacastellana.setTransitions(cmprendalistacastellana);
		neprendalistacastellana.setDef(null);
		
		nfyprendalistacastellana.setTransicion(trprendalistacastellana);
		
		servicios_activos.put(prendalistacastellana.getNombre(),prendalistacastellana);
		
		
		
		
		//////
		
		
//Prendalista para tintoreria de galeria los naranjos(Separada de quickpress)
		
		Servicio prendalistanaranjos = new Servicio();
		prendalistanaranjos.setTimeout(3600000);
		prendalistanaranjos.setGrpcnx(getGrupoConexion(new Long(1)));
		NotificacionSalida notprendalistanaranjos = new NotificacionSalida();
		Map<Integer,Conexion> hmprendalistanaranjos = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmprendalistanaranjos = new HashMap<Integer,String>();
		hmprendalistanaranjos.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmprendalistanaranjos.put(new Integer(0),"##ORDEN##");
		Contenido contprendalistanaranjos = new ContenidoSMS("Buen Dia!\nLa Tintoreria ClothExpress de Los Naranjos le informa que su orden Nro. ##ORDEN## ya esta lista para ser retirada");
		notprendalistanaranjos.setContenido(contprendalistanaranjos);
		notprendalistanaranjos.setDatareplace(dmprendalistanaranjos);
		notprendalistanaranjos.setRoutes(hmprendalistanaranjos);
		NotificarUnaSolaVez nfyprendalistanaranjos = new NotificarUnaSolaVez(0);
		nfyprendalistanaranjos.setOut(notprendalistanaranjos);
		prendalistanaranjos.setRootcmd(nfyprendalistanaranjos);
		prendalistanaranjos.setNombre("prendalistanaranjos");
		
		NotificacionEntrada neprendalistanaranjos = new NotificacionEntrada();
		TransicionRegex trprendalistanaranjos = new TransicionRegex();
		trprendalistanaranjos.setIn(neprendalistanaranjos);
		HashMap<String,Comando> cmprendalistanaranjos = new HashMap<String,Comando>();
		neprendalistanaranjos.setTransitions(cmprendalistanaranjos);
		neprendalistanaranjos.setDef(null);
		
		nfyprendalistanaranjos.setTransicion(trprendalistanaranjos);
		
		servicios_activos.put(prendalistanaranjos.getNombre(),prendalistanaranjos);
		
		
		
		
		//////

/*		
		ServicioBroadCast bbpush = new ServicioBroadCast();
		bbpush.setTimeout(60000);
		bbpush.setGrpcnx(getGrupoConexion(new Long(8)));
		NotificacionSalida notbb= new NotificacionSalida();
		Map<Integer,Conexion> hmbb = new HashMap<Integer,Conexion>();
		Map<Integer,String> dmbb = new HashMap<Integer,String>();
		hmbb.put(new Integer(0),mcnx.getConexiones_activas().get(0));
		dmbb.put(new Integer(0),"##PUSH##");
		Contenido contbb = new ContenidoSMS("##PUSH##");
		notbb.setContenido(contbb); 
		notbb.setDatareplace(dmbb);
		notbb.setRoutes(hmbb);
		NotificarBlackberry nfybb = new NotificarBlackberry();
		nfybb.setOut(notbb);
		bbpush.setRootcmd(nfybb);
		bbpush.setNombre("BBPUSH");
		
		NotificacionEntrada nebb = new NotificacionEntrada();
		TransicionRegex trbb = new TransicionRegex();
		trbb.setIn(nebb);
		HashMap<String,Comando> cmbb = new HashMap<String,Comando>();
		nebb.setTransitions(cmbb);
		nebb.setDef(null);
		
		nfybb.setTransicion(trbb);
		
		servicios_activos.put(bbpush.getNombre(),bbpush);
		*/

	}

	private GrupoConexiones getGrupoConexion(Long i) {
		GrupoConexiones grp;
		
		FabricaDAO fab = FabricaDAO.getInstancia(FabricaDAO.HIBERNATE);
		TxDAO tx = fab.getTx();
		
		GrupoConexionesDAO grdao = fab.getGrupoConexionesDAO();
		tx.beginTx();
		
		grp = grdao.buscarPorIdGet(i,false);	

		tx.commit();
		
		return grp;
		
	}
	
	

}
