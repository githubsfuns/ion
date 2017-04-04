package com.semm.core.conexiones;

import javax.jms.Connection;

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.log4j.Logger;

public class ConexionJMSTwoWay extends Conexion implements MessageListener{
   
   transient  Logger log = Logger.getLogger(ConexionJMSTwoWay.class.getName()); 
	 // Create a ConnectionFactory
   private ActiveMQConnectionFactory connectionFactory;
   private Connection connection;
   // Create a Session
   private Session session_in,session_out;
   // Create the destination (Topic or Queue)
   private Destination destination_in,destination_out;
   // Create a MessageConsumer from the Session to the Topic or Queue
   private MessageConsumer consumer;
   private MessageProducer producer;
   private boolean conectado = false;
   
   private String uri,cola_in,cola_out;

	

	@Override
	public void conectar() throws JMSException {
		
		  connectionFactory = new ActiveMQConnectionFactory(uri);
	        // Create a Connection
		  /*connectionFactory.setAsyncDispatch(true);
		  connectionFactory.setOptimizeAcknowledge(true);
		  connectionFactory.setOptimizedMessageDispatch(true);*/
		   
		   
	      connection = connectionFactory.createConnection();
	       
	      connection.start();
	      session_in = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);    
	      session_out = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);    
	      	     
	      destination_in = session_in.createQueue(cola_in);
	    
	      destination_out = session_out.createQueue(cola_out);
	      
	      consumer = session_in.createConsumer(destination_in);
	      consumer.setMessageListener(this);
	      

	       producer = session_out.createProducer(destination_out);
	       producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);	     
	 
	}

	@Override
	public void desconectar() {
		try {
			producer.close();
			consumer.close();
			
			session_in.close();
			session_out.close();
			connection.close();
			conectado=false;
		} catch (JMSException e) {
			log.warn(e);
		}


	}

	@Override
	public void enviar(NuevoMensaje m) {
		ObjectMessage om;
		try {
			
		
			om = session_out.createObjectMessage(m);

			producer.send(om);
	      
		  
		} catch (JMSException e) {
			log.error(e);
		}
	}

	public void run() {
		
		while(getDtect()==null){
			log.error("No existe un procesador de mensajes asignado");
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				log.warn(e);
			}
		}
		
		while(!conectado) {
			try{
				log.debug("Inicializando Conexion JMS...");
				conectar();
				conectado = true;
			} catch(JMSException jme ){
				log.error(jme);
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					log.warn(e);
				}
			}
		}
		log.debug("Conexion JMS activa");

	}

	public void onMessage(Message jms) {
		
	
		if (jms instanceof ObjectMessage) {
			ObjectMessage om = (ObjectMessage) jms;
        	Object ob;
			try {
				ob = om.getObject();
				UserTransData mjms = null;
				if (ob instanceof TransData) {
					TransData td = (TransData) ob;
					mjms = new UserTransData(td);
					
				} else {
				
                   mjms = (UserTransData)ob;
				}
            	log.info("Mensaje Recibido: Cliente: " + mjms.getCliente() + " Servicio: " + mjms.getServicio() + " Recips: " + mjms.getRecips().size() + " Datas: "+ mjms.getData().size());
            	getDtect().mensajeEntrante(mjms);
			}catch (Exception e) {
				log.error(e);
			}
		}
	}

	public String getCola_in() {
		return cola_in;
	}

	public void setCola_in(String cola_in) {
		this.cola_in = cola_in;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getCola_out() {
		return cola_out;
	}

	public void setCola_out(String cola_out) {
		this.cola_out = cola_out;
	}

	@Override
	public void recargar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarMsgCola(NuevoMensaje m) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean verificarFalla() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recuperar() {
		// TODO Auto-generated method stub
		
	}

}
