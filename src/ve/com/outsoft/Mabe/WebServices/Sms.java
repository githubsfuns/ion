/**
 * Sms.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ve.com.outsoft.Mabe.WebServices;

public interface Sms extends javax.xml.rpc.Service {
    public java.lang.String getsmsSoap12Address();

    public ve.com.outsoft.Mabe.WebServices.SmsSoap getsmsSoap12() throws javax.xml.rpc.ServiceException;

    public ve.com.outsoft.Mabe.WebServices.SmsSoap getsmsSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getsmsSoapAddress();

    public ve.com.outsoft.Mabe.WebServices.SmsSoap getsmsSoap() throws javax.xml.rpc.ServiceException;

    public ve.com.outsoft.Mabe.WebServices.SmsSoap getsmsSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
