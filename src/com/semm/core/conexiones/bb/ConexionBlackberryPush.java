package com.semm.core.conexiones.bb;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Collection;
import java.util.Random;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.smslib.OutboundMessage;
import org.xml.sax.InputSource;

import sun.misc.BASE64Encoder;


import com.semm.core.conexiones.CnxMensaje;
import com.semm.core.conexiones.Conexion;
import com.semm.core.conexiones.ConexionFullException;
import com.semm.core.conexiones.ConexionModemGSM;
import com.semm.core.conexiones.ConexionMuertaException;
import com.semm.core.conexiones.NuevoMensaje;
import com.semm.core.servicios.MensajeLog;

public class ConexionBlackberryPush extends Conexion {

	private static final String NOTIFICATION_URL = "/";
	private String _serviceId;
	private String _password;
	private String _bisServer;
	private Random _random;
	private String _pushTemplate;
	private String requestTemplate;
	private Logger log = Logger.getLogger(ConexionBlackberryPush.class.getName());
	private int port;
	private String proxyh; 


	
	public String get_serviceId() {
		return _serviceId;
	}

	public void set_serviceId(String serviceId) {
		_serviceId = serviceId;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String password) {
		_password = password;
	}

	public String get_bisServer() {
		return _bisServer;
	}

	public void set_bisServer(String bisServer) {
		_bisServer = bisServer;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getProxyh() {
		return proxyh;
	}

	public void setProxyh(String proxyh) {
		this.proxyh = proxyh;
	}

	static boolean isValidPin(String recip){
		return Pattern.matches("[a-f|A-F|0-9]{8}", recip);
	}
	
	@Override 
	public void agregarMsgCola(NuevoMensaje m) throws ConexionFullException, ConexionMuertaException {
		
		if(this.outbox.size() >= (int)this.transrate + DELAY)
			throw new ConexionFullException();
		
		if(verificarFalla())
			throw new ConexionMuertaException();
		
		Collection<NuevoMensaje> via =this.outbox;
		CnxMensaje bbp = (CnxMensaje)m;
		via.add(bbp);

	}

	@Override
	public void conectar() throws Exception {
		_random = new Random();
		requestTemplate = readPapTemplate("pap_push.template");
		log.debug("Conexion " + this.nombre + " activa");
	}

	@Override
	public void desconectar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviar(NuevoMensaje m) {
			BlackberryMessage bbmsg = (BlackberryMessage)m;
			
			if(bbmsg.getBlocked()>0){
				bbmsg.setTipo(2);
				bbmsg.setRetries(6);
				bbmsg.setSubtipo(bbmsg.getBlocked());
				log.debug("Mensaje Bloqueado! Razon:" + bbmsg.getBlocked() + " Para:" + bbmsg.getPara() + " MSG:" + bbmsg.getContenido().getMsg());
				return;
			} else if (!conectado){
				bbmsg.setTipo(2);
				bbmsg.setSubtipo(CnxMensaje.APAGANDO);
				bbmsg.setRetries(6);
				log.debug("Mensaje No Enviado: Conexion apagandose... Para:" + bbmsg.getPara() + " MSG:" + bbmsg.getContenido().getMsg());
				return;
			} 
			
		 	HttpURLConnection mdsConn = null;
	        InputStream ins = null;
	        OutputStream outs = null;
	  
			String pushId = ""+ Math.abs(_random.nextInt());
			
	        String errorCode = null;
	        try
	        {
	            URL mdsUrl = new URL(_bisServer);

	            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyh, port));
	            
	            mdsConn = (HttpURLConnection) mdsUrl.openConnection(proxy);

	            String boundary = "";
	            boundary = "asdlfkjiurwghasf";
	            mdsConn.setRequestProperty("Content-Type", "multipart/related; type=\"application/xml\"; boundary="
	                    + boundary);

	            String username = _serviceId;
	            String password = _password;
	            String auth = "Basic " + new BASE64Encoder().encode((username + ":" + password).getBytes());
	            mdsConn.setRequestProperty("Authorization", auth);
	            mdsConn.setRequestMethod("POST");

	            mdsConn.setAllowUserInteraction(false);
	            mdsConn.setDoInput(true);
	            mdsConn.setDoOutput(true);

	            String output = requestTemplate.replaceAll("\\$\\(pushid\\)", pushId);
	            output = output.replaceAll("\\$\\(username\\)", _serviceId);
	            output = output.replaceAll("\\$\\(boundary\\)", boundary);
	            output = output.replaceAll("\\$\\(notifyURL\\)", NOTIFICATION_URL);
	            output = output.replaceAll("\\$\\(recips\\)", generarBlackberryRecips(bbmsg));

	            String deliveryMethod = "confirmed";

	            output = output.replaceAll("\\$\\(deliveryMethod\\)", deliveryMethod);

	            output = output.replaceAll("\\$\\(headers\\)", "Content-Type: text/plain");
	            output = output.replaceAll("\\$\\(content\\)", bbmsg.getContenido().getMsg());

	            output = output.replaceAll("\r\n", "EOL");
	            output = output.replaceAll("\n", "EOL");
	            output = output.replaceAll("EOL", "\r\n");
	            
	            log.debug("Enviando a BB: "+output);
	            outs = mdsConn.getOutputStream();
	            copyStreams(new ByteArrayInputStream(output.getBytes()), outs);
	            //Medir el Tiempo...
	            long start = System.currentTimeMillis();
				
			
		
	            mdsConn.connect();

	            ins = mdsConn.getInputStream();

	            ByteArrayOutputStream response = new ByteArrayOutputStream();
	            copyStreams(ins, response);

	            int httpCode = mdsConn.getResponseCode();
	            
	    		long res = (System.currentTimeMillis()-start);
				float tx = 60000/((res>0)?res:1);
				
				this.setTransrate(tx);	

	            if( httpCode != HttpURLConnection.HTTP_ACCEPTED && httpCode != HttpURLConnection.HTTP_OK )
	            {
	               log.warn("Push Failed\nHTTP status: " + httpCode + "\n");
	            }
	            else
	            {
	            	//System.out.println(new String(response.toByteArray()));
	                // Now that we know that the HTTP connection was successful,
	                // parse the server response to ensure it was successful.
	                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
	                PAPResponseHandler handler = new PAPResponseHandler();
	                parser.parse(new InputSource(new StringReader(new String(response.toByteArray()))), handler);

	                if( "1000".equals(handler.getResponseCode()) || "1001".equals(handler.getResponseCode()) )
	                {

	                    log.debug("Push to BIS Succeeded\n" + pushId + "\n");
	                	
	        			
	        			bbmsg.setTipo(0);
	        			
	        			if(!this.extramsgs)
	        				this.pagados--;

	        			if(bbmsg.getOwner().getTipo() >0)
	        				bbmsg.removeUserCredit(1);
	        			
	        			this.fallos = 0;
	        			
	        		
	                }
	                else
	                {
	                    log.warn("Push to BIS Failed - " + pushId + ":\n" + "(" + handler.getResponseCode()
	                            + ") " + handler.getResponseDescription() + "\n");
	                    bbmsg.setTipo(2);
	        			bbmsg.setRetries(bbmsg.getRetries()+1);
	        			this.fallos+=5;
	        			log.error("Hubo error al enviar el mensaje para: " + bbmsg.getPara() + " cliente:" + m.getOwner().getUsername() + " intentos: " + bbmsg.getRetries());
	        			
	                }
	            }
	        }
	        catch( Exception ex )
	        {
	        	ex.printStackTrace();
	            if( errorCode == null )
	            {
	                errorCode = ex.getClass().getName();
	            }

	           log.error("Push Error (" + errorCode + "):\n" + ex.toString());
	        }
	        finally
	        {
	            if( ins != null )
	            {
	                try
	                {
	                    ins.close();
	                }
	                catch( IOException e )
	                {
	                    // Safe to ignore
	                }
	            }

	            if( outs != null )
	            {
	                try
	                {
	                    outs.close();
	                }
	                catch( IOException e )
	                {
	                    // Safe to ignore.
	                }
	            }

	            if( mdsConn != null )
	            {
	                mdsConn.disconnect();
	            }
	        }
	}
	
	 private String readPapTemplate(String papFilename)
	    {
            BufferedReader in = null;
            PrintWriter out = null;
            StringWriter sw = new StringWriter();
            try
            {
            	String path = getClass().getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            	log.debug("Template: " + path + "\\"+papFilename);
            	String pfile= path + "\\" + papFilename;
	    
                in = new BufferedReader(new FileReader(pfile));
                out = new PrintWriter(sw, true);
                String line;
                while( (line = in.readLine()) != null )
                {
                    out.println(line);
                }
                out.close();
                out = null;
                log.debug("Lectura Plantilla PUSH OK");
                this._pushTemplate = sw.toString();
            }
            catch( Exception ex )
            {
                ex.printStackTrace();
            }
            finally
            {
                if( in != null )
                {
                    try
                    {
                        in.close();
                    }
                    catch( Exception ex )
                    {
                        // Safe to ignore.
                    }
                }
                if( out != null )
                {
                    try
                    {
                        out.close();
                    }
                    catch( Exception ex )
                    {
                        // Safe to ignore.
                    }
                }
            }

        return _pushTemplate;
	    }
	

	 protected MensajeLog convertirMensajeLog(CnxMensaje sms) {
			MensajeLog mlog = new MensajeLog(sms.getPara(),sms.getContenido().getMsg(),sms.getServicio(),sms.getOwner(),sms.getCnx(),sms.getTipo(),sms.getSubtipo());
			return mlog;
	}
	 
	private String generarBlackberryRecips(BlackberryMessage bbmsg) {
		String pinlist = "";
		for(String pin : bbmsg.getRecips()){
			if(isValidPin(pin))
				pinlist += "<address address-value=\""+pin+"\"/>\n";
		}
		return pinlist;
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
	
	  private void copyStreams(InputStream ins, OutputStream outs) throws IOException
	    {

	        byte[] buffer = new byte[ 1024 ];
	        int bytesRead;

	        for( ;; )
	        {
	            bytesRead = ins.read(buffer);
	            if( bytesRead <= 0 )
	            {
	                break;
	            }
	            outs.write(buffer, 0, bytesRead);
	        }
	    }

}
