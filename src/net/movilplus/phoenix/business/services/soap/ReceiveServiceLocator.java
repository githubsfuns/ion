/**
 * ReceiveServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.movilplus.phoenix.business.services.soap;

public class ReceiveServiceLocator extends org.apache.axis.client.Service implements net.movilplus.phoenix.business.services.soap.ReceiveService {

    public ReceiveServiceLocator() {
    }


    public ReceiveServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ReceiveServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }
   
    // Use to get a proxy class for ReceivePortType
    private java.lang.String ReceivePortType_address = "http://200.73.197.59:8080/phoenix.services/receiver";

    public java.lang.String getReceivePortTypeAddress() {
        return ReceivePortType_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ReceivePortTypeWSDDServiceName = "ReceivePortType";

    public java.lang.String getReceivePortTypeWSDDServiceName() {
        return ReceivePortTypeWSDDServiceName;
    }

    public void setReceivePortTypeWSDDServiceName(java.lang.String name) {
        ReceivePortTypeWSDDServiceName = name;
    }

    public net.movilplus.phoenix.core.services.IReceive getReceivePortType() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ReceivePortType_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getReceivePortType(endpoint);
    }

    public net.movilplus.phoenix.core.services.IReceive getReceivePortType(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.movilplus.phoenix.core.services.IReceiveBindingStub _stub = new net.movilplus.phoenix.core.services.IReceiveBindingStub(portAddress, this);
            _stub.setPortName(getReceivePortTypeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setReceivePortTypeEndpointAddress(java.lang.String address) {
        ReceivePortType_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.movilplus.phoenix.core.services.IReceive.class.isAssignableFrom(serviceEndpointInterface)) {
                net.movilplus.phoenix.core.services.IReceiveBindingStub _stub = new net.movilplus.phoenix.core.services.IReceiveBindingStub(new java.net.URL(ReceivePortType_address), this);
                _stub.setPortName(getReceivePortTypeWSDDServiceName());
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
        if ("ReceivePortType".equals(inputPortName)) {
            return getReceivePortType();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.services.business.phoenix.movilplus.net/", "ReceiveService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.services.business.phoenix.movilplus.net/", "ReceivePortType"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ReceivePortType".equals(portName)) {
            setReceivePortTypeEndpointAddress(address);
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
