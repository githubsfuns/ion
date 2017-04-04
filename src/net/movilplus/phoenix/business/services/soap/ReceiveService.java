/**
 * ReceiveService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.movilplus.phoenix.business.services.soap;

public interface ReceiveService extends javax.xml.rpc.Service {
    public java.lang.String getReceivePortTypeAddress();

    public net.movilplus.phoenix.core.services.IReceive getReceivePortType() throws javax.xml.rpc.ServiceException;

    public net.movilplus.phoenix.core.services.IReceive getReceivePortType(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
