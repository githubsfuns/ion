/**
 * RecepcionSmsLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx;

public class RecepcionSmsLocator extends org.apache.axis.client.Service implements ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSms {

    public RecepcionSmsLocator() {
    }


    public RecepcionSmsLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RecepcionSmsLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RecepcionSmsSoap
    private java.lang.String RecepcionSmsSoap_address = "http://SmsWebService:Znbj2hxkwjq03h3@apps.og.com.ve/Webservices/semm-sms/RecepcionSms.asmx";

    public java.lang.String getRecepcionSmsSoapAddress() {
        return RecepcionSmsSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RecepcionSmsSoapWSDDServiceName = "RecepcionSmsSoap";

    public java.lang.String getRecepcionSmsSoapWSDDServiceName() {
        return RecepcionSmsSoapWSDDServiceName;
    }

    public void setRecepcionSmsSoapWSDDServiceName(java.lang.String name) {
        RecepcionSmsSoapWSDDServiceName = name;
    }

    public ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap getRecepcionSmsSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RecepcionSmsSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRecepcionSmsSoap(endpoint);
    }

    public ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap getRecepcionSmsSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoapStub _stub = new ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoapStub(portAddress, this);
            _stub.setPortName(getRecepcionSmsSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRecepcionSmsSoapEndpointAddress(java.lang.String address) {
        RecepcionSmsSoap_address = address;
    }


    // Use to get a proxy class for RecepcionSmsSoap12
    private java.lang.String RecepcionSmsSoap12_address = "http://apps.og.com.ve/Webservices/semm-sms/RecepcionSms.asmx";

    public java.lang.String getRecepcionSmsSoap12Address() {
        return RecepcionSmsSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RecepcionSmsSoap12WSDDServiceName = "RecepcionSmsSoap12";

    public java.lang.String getRecepcionSmsSoap12WSDDServiceName() {
        return RecepcionSmsSoap12WSDDServiceName;
    }

    public void setRecepcionSmsSoap12WSDDServiceName(java.lang.String name) {
        RecepcionSmsSoap12WSDDServiceName = name;
    }

    public ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap getRecepcionSmsSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RecepcionSmsSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRecepcionSmsSoap12(endpoint);
    }

    public ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap getRecepcionSmsSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap12Stub _stub = new ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap12Stub(portAddress, this);
            _stub.setPortName(getRecepcionSmsSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRecepcionSmsSoap12EndpointAddress(java.lang.String address) {
        RecepcionSmsSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoapStub _stub = new ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoapStub(new java.net.URL(RecepcionSmsSoap_address), this);
                _stub.setPortName(getRecepcionSmsSoapWSDDServiceName());
                return _stub;
            }
            if (ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap12Stub _stub = new ve.com.og.apps.Webservices.semm_sms.RecepcionSms_asmx.RecepcionSmsSoap12Stub(new java.net.URL(RecepcionSmsSoap12_address), this);
                _stub.setPortName(getRecepcionSmsSoap12WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RecepcionSmsSoap".equals(inputPortName)) {
            return getRecepcionSmsSoap();
        }
        else if ("RecepcionSmsSoap12".equals(inputPortName)) {
            return getRecepcionSmsSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://apps.og.com.ve/Webservices/semm-sms/RecepcionSms.asmx", "RecepcionSms");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://apps.og.com.ve/Webservices/semm-sms/RecepcionSms.asmx", "RecepcionSmsSoap"));
            ports.add(new javax.xml.namespace.QName("http://apps.og.com.ve/Webservices/semm-sms/RecepcionSms.asmx", "RecepcionSmsSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RecepcionSmsSoap".equals(portName)) {
            setRecepcionSmsSoapEndpointAddress(address);
        }
        else 
if ("RecepcionSmsSoap12".equals(portName)) {
            setRecepcionSmsSoap12EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
