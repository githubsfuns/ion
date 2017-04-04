/**
 * WebServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface WebServiceSoap extends java.rmi.Remote {
    public org.tempuri.Formulario cargarMensaje(java.lang.String p_Mensaje, java.lang.String p_IdMedio, java.lang.String p_MedioTransmision, java.lang.String idUsuario) throws java.rmi.RemoteException;
    public org.tempuri.Formulario cargarDesdeMensajeOriginal(org.tempuri.MensajeOriginal mensaje) throws java.rmi.RemoteException;
    public int cargarMensajeIntegrador(java.lang.String p_Mensaje, java.lang.String p_IdMedio, java.lang.String p_MedioTransmision, java.lang.String idUsuario) throws java.rmi.RemoteException;
    public org.tempuri.Formulario cargarMensajeCallCenter(java.lang.String p_Mensaje, java.lang.String p_IdCentro, java.lang.String p_IdMesa, java.lang.String p_IdObservador, java.lang.String telefono, java.lang.String idUsuario) throws java.rmi.RemoteException;
    public org.tempuri.Pregunta[] buscarPlantillaDeFormulario(java.lang.String id) throws java.rmi.RemoteException;
    public org.tempuri.MensajeOriginal buscarMensajeOriginal(int id) throws java.rmi.RemoteException;
    public org.tempuri.MensajeAuditado[] buscarMensajeAuditado(java.lang.String idMesa, java.lang.String idCentro) throws java.rmi.RemoteException;
    public org.tempuri.MensajeAuditado[] buscarMensajeAuditadoPorTelefono(java.lang.String telefono) throws java.rmi.RemoteException;
    public org.tempuri.MensajeAuditado[] buscarMensajeAuditadoFiltrado(org.tempuri.FiltroBusquedaMensajes filtro) throws java.rmi.RemoteException;
    public org.tempuri.MensajeAuditado[] buscarMensajeAuditadoSOSFiltrado(org.tempuri.FiltroBusquedaMensajes filtro) throws java.rmi.RemoteException;
    public int actualizarMensajeAuditado(org.tempuri.MensajeAuditado mensaje) throws java.rmi.RemoteException;
    public org.tempuri.Respuesta[] buscarRespuestas(org.tempuri.FiltroBusquedaMensajes filtro) throws java.rmi.RemoteException;
    public org.tempuri.MensajeRespuesta[] buscarMensajeRespuesta(org.tempuri.FiltroBusquedaMensajes filtro) throws java.rmi.RemoteException;
    public int actualizarMensajeRespuesta(org.tempuri.MensajeRespuesta mensaje) throws java.rmi.RemoteException;
}
