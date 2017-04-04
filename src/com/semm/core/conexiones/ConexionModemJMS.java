package com.semm.core.conexiones;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.semm.core.conexiones.contenido.ContenidoSMS;
import com.semm.core.servicios.MensajeLog;
import com.semm.ionmodemjms.EstadoCnxJMS;
import com.semm.ionmodemjms.LlamadaJMS;
import com.semm.ionmodemjms.MensajeLogJMS;
import com.semm.ionmodemjms.ReporteEntregaJMS;

public class ConexionModemJMS  extends Conexion implements MessageListener {
    private static int ackMode = Session.AUTO_ACKNOWLEDGE;
    
    private transient Map<String,MensajeLog> outbox_jms;

    private boolean transacted = false;
    private MessageProducer producer;
    private int maxlength;
    private String qname,qnameout,url;
    private Session session;
    private Destination tempDest;
    private Random random = new Random(System.currentTimeMillis());
    private boolean multipart;
   

    

    public boolean isMultipart() {
		return multipart;
	}

	public void setMultipart(boolean multipart) {
		this.multipart = multipart;
	}

	public ConexionModemJMS() {
    	outbox_jms = new ConcurrentHashMap<String, MensajeLog>();
    }

    private void sendJMS(Message msg) throws JMSException {
    	  //Now create the actual message you want to send
        

        //Set the reply to field to the temp queue you created above, this is the queue the server
        //will respond to
        msg.setJMSReplyTo(tempDest);

        //Set a correlation ID so when you get a response you know which sent message the response is for
        //If there is never more than one outstanding message to the server then the
        //same correlation ID can be used for all the messages...if there is more than one outstanding
        //message to the server you would presumably want to associate the correlation ID with this
        //message somehow...a Map works good
        String correlationId = this.createRandomString();
        msg.setJMSCorrelationID(correlationId);
        this.producer.send(msg);
    }

    private String createRandomString() {
     
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }

    public void onMessage(Message jms) {
        try {
	        if (jms instanceof ObjectMessage) {
				
				ObjectMessage omsg = (ObjectMessage)jms;
				Object obj = omsg.getObject();
				
				if (obj instanceof MensajeLogJMS) {
					
					
					MensajeLogJMS mlogjms = (MensajeLogJMS) obj;
					log.info("Mensaje LOG Recibido: " + mlogjms.getMobile() + " - TIPO:" + mlogjms.getTipo() + " - SUBTIPO:" + mlogjms.getSubtipo() + " - " + mlogjms.getFechahora());
					mensajeRecibido(omsg.getJMSCorrelationID(), mlogjms);
					
				} else if (obj instanceof EstadoCnxJMS) {
					log.info("Mensaje Estatus Recibido: " + obj);
					EstadoCnxJMS mlogjms = (EstadoCnxJMS) obj;
					actualizarEstado(mlogjms);
					
				}else if (obj instanceof ReporteEntregaJMS) {
					log.info("Mensaje Reporte Recibido: " + obj);
					ReporteEntregaJMS repj = (ReporteEntregaJMS) obj;
					reporteEntregaJMS(repj);
					
				}else if (obj instanceof LlamadaJMS) {
					log.info("Mensaje Llamada Recibido: " + obj);
					LlamadaJMS call = (LlamadaJMS) obj;
					procesarLLamadaJMS(call);
					
				}

			}
        }catch (Exception e) {
        	e.printStackTrace();
			log.error(e);
		}
    }



	private void procesarLLamadaJMS(LlamadaJMS call) {
		DetectaMensaje detect = getDtect();
		Llamada call2 = new Llamada(call.getTlf(),call.getFechahora(),this);
		log.debug(">> Llamada entrante de: " + call2.getTlf() + " Linea: " + call2.getCnx().getNombre());
		detect.llamadaEntrante(call2);
		
	}

	private void actualizarEstado(EstadoCnxJMS state) {
		if(state.getTipo()== EstadoCnxJMS.REPLY){
//			if(!state.isConectado())
//				//desconectar();
//			if(state.isReparando())
//				//recuperar();
		} else if (state.getTipo()== EstadoCnxJMS.INICIALIZADO) {
			for(MensajeLog mlog : outbox_jms.values()){
				mlog.setTipo(2);
				mlog.setSubtipo(CnxMensaje.APAGANDO);
				guardarMensaje(mlog);
			}
			outbox_jms.clear();
		}
	}

	private void mensajeRecibido(String correlationID, MensajeLogJMS mjms) {
		
		if(mjms.getTipo() == 1){
			actualizarMensajeJMS(mjms);
		}
		else {
			MensajeLog mlog = outbox_jms.get(correlationID);
			if(mlog!=null){
				
				this.encola = outbox_jms.size() - 1;
				
				actualizarMensajeJMS(mjms, mlog);
				List<MensajeLog> logs = convertirMensajeLogMulti(mlog);
				for(MensajeLog m : logs)
					guardarMensaje(m);
				
				synchronized (mlog.getCliente()){
					mlog.getCliente().setOnqueue(mlog.getCliente().getOnqueue()-1);
					mlog.getCliente().setCredits(mlog.getCliente().getCredits() -1);
				}
				
				outbox_jms.remove(correlationID);
				
				if(outbox_jms.size()< PISO){
					log.debug("Conexion llego a su piso! tamano: "  + outbox_jms.size() );
					synchronized (router) {
						router.notifyAll();
					}
				}
			}
		}
	}
	



	@Override
	public void enviarMensajes()  {
		Iterator<NuevoMensaje> i2 = outbox.iterator();
		while(i2.hasNext()){

			log.debug("Enviando... CNX: "+ getNombre() + " En cola:" + outbox.size() + " " + ((this.extramsgs)? "" : "Disp: " + this.pagados));
			NuevoMensaje m = i2.next();
			enviar(m);
			//this.encola = outbox.size() -1;
			i2.remove();

		}

	}
	
	@Override
	public void agregarMsgCola(NuevoMensaje m1) throws ConexionMuertaException,
			ConexionFullException {
		log.info("JMS SIZE: " + outbox_jms.size());

		if(this.outbox_jms.size() >= (int)this.transrate + DELAY || this.outbox.size() >= (int)this.transrate + DELAY )
			throw new ConexionFullException();
		
		if(verificarFalla())
			throw new ConexionMuertaException();
		
		Collection<NuevoMensaje> via =this.outbox;
		CnxMensaje m = (CnxMensaje)m1;

		if(m.getContenido().getMsg().length() > maxlength && !this.isMultipart() && !m.isMultipart()){
			ArrayList<String> parts = new ArrayList<String>(2);
			splitMsg(parts, m.getContenido().getMsg());
			int i = 1;
			for(String part : parts){
				ContenidoSMS cont = new ContenidoSMS(part + "("+(i++)+"/"+parts.size()+")");
				CnxMensaje mpart = new CnxMensaje(m.getPara(),m.getDe(),m.getCnx(),cont);
				mpart.setCliente(m.getCliente());
				mpart.setServicio(m.getServicio());
				mpart.setOwner(m.getOwner());
				mpart.setBlocked(m.getBlocked());
				mpart.setReport(m.isReport());
				mpart.setProgramado(m.isProgramado());
				via.add(mpart);
			}
		} else 
			via.add(m);
		
	}
	


	@Override
	public void conectar() throws Exception {
		 ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	        Connection connection;
	        try {
	        	
	            connection = connectionFactory.createConnection();
	            connection.start();
	            session = connection.createSession(transacted, ackMode);

	            Destination adminQueue = session.createQueue(qname);

	            //Setup a message producer to send message to the queue the server is consuming from
	            this.producer = session.createProducer(adminQueue);
	            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

	            //Create a temporary queue that this client will listen for responses on then create a consumer
	            //that consumes message from this temporary queue...for a real application a client should reuse
	            //the same temp queue for each message to the server...one temp queue per client
	            tempDest = session.createQueue(qnameout);
	            MessageConsumer responseConsumer = session.createConsumer(tempDest);

	            //This class will handle the messages to the temp queue as well
	            responseConsumer.setMessageListener(this);
	            
	            //enviarEstado();
	            
	            log.debug("Conexion "+ this.nombre + " activa");
	         
	        } catch (JMSException e) {
	            log.error(e);
	            e.printStackTrace();
	        }
	}

	private void enviarEstado() {
		 EstadoCnxJMS edo = new EstadoCnxJMS(EstadoCnxJMS.INICIALIZADO,true,false);
         edo.setJmssize(outbox_jms.size());
         Message msg;
		try {
			msg = session.createObjectMessage(edo);
			 sendJMS(msg);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	@Override
	public void desconectar() {
		try {
			producer.close();
			session.close();
			conectado=false;
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void enviar(NuevoMensaje m) {
		
		CnxMensaje sms = (CnxMensaje)m;
		
		ObjectMessage om;
		try {
			MensajeLogJMS mjms = new MensajeLogJMS(sms.getPara(),sms.getContenido().getMsg(),sms.getCliente());
			mjms.setBlocked(sms.getBlocked());
			mjms.setReport(sms.isReport());
			om = session.createObjectMessage(mjms);
			sendJMS(om);
			log.info("Agregando mensaje a lista JMS: "+ om.getJMSCorrelationID() );
			outbox_jms.put(om.getJMSCorrelationID(), convertirMensajeLog(sms));
		  
		} catch (JMSException e) {
			log.error(e);
		}

	}
	private void actualizarMensajeJMS(MensajeLogJMS mjms){
		DetectaMensaje detect = getDtect();
		CnxMensaje in = new CnxMensaje(mjms.getMobile(),this.nombre,this,new ContenidoSMS(mjms.getMsg()));
		in.setTipo(mjms.getTipo());
		in.setCnx(this);
		detect.mensajeEntrante(in);
		
	}
	
	private void reporteEntregaJMS(ReporteEntregaJMS repj){
		DetectaMensaje detect = getDtect();
		ReporteEntrega rep = new ReporteEntrega(repj.getRecip(),repj.getFechahora());
		rep.setCnx(this);
		detect.reporteEntrante(rep);
	}
	
	private void actualizarMensajeJMS(MensajeLogJMS mjms,MensajeLog mlog){
		
		log.debug("Convirtiendo MENSAJES" );
		//Calculo de TX
		long start = mlog.getFechahora().getTime();
		long res = (mjms.getFechahora().getTime()-start);
		float tx = 60000/((res>0)?res:1);
		tx = (tx>20)? 20 : tx;
		this.setTransrate(12);	
		
		//Actualizamos hora
		mlog.setFechahora(mjms.getFechahora());
		mlog.setTipo(mjms.getTipo());
		mlog.setSubtipo(mjms.getSubtipo());
	
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
		
		return false;
	}
	

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMaxlength() {
		return maxlength;
	}
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}
	
	private void splitMsg(ArrayList<String> l,String msg) {
		int size = maxlength-5;
		
		if(msg.length()>size){
			String shorthead = "";
			
			String head = msg.substring(0,size);
			String tail = msg.substring(size);
			int lispc = head.lastIndexOf(' ');
			int ispc = tail.indexOf(' ');
			
			if(lispc == (size-1) || ispc == 0){
				shorthead = head.trim();
				tail = tail.trim();
			} else if (lispc < (size-1) && (ispc > 0) || (ispc == -1)){
				shorthead = head.substring(0,lispc);
				tail = head.substring(lispc+1) + tail;
				
			} 

			l.add(shorthead);
			splitMsg(l, tail);
			
		} else {
			l.add(msg);
		}
		
	
	}

	public String getQnameout() {
		return qnameout;
	}

	public void setQnameout(String qnameout) {
		this.qnameout = qnameout;
	}
	

}
