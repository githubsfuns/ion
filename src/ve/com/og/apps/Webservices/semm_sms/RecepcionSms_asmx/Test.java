package ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
 RecepcionSmsLocator recp = new RecepcionSmsLocator();
		
		 
	     RecepcionSmsSoap smssoap;
		try {
			smssoap = recp.getRecepcionSmsSoap();
			smssoap.recibirSms("Prueba Interconexion SMS con OG", "04141330820","WWW_cocacola");
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
