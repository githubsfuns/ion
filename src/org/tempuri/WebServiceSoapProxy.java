package org.tempuri;

public class WebServiceSoapProxy implements org.tempuri.WebServiceSoap {
  private String _endpoint = null;
  private org.tempuri.WebServiceSoap webServiceSoap = null;
  
  public WebServiceSoapProxy() {
    _initWebServiceSoapProxy();
  }
  
  public WebServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceSoapProxy();
  }
  
  private void _initWebServiceSoapProxy() {
    try {
      webServiceSoap = (new org.tempuri.WebServiceLocator()).getWebServiceSoap();
      if (webServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceSoap != null)
      ((javax.xml.rpc.Stub)webServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.WebServiceSoap getWebServiceSoap() {
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap;
  }
  
  public org.tempuri.Formulario cargarMensaje(java.lang.String p_Mensaje, java.lang.String p_IdMedio, java.lang.String p_MedioTransmision, java.lang.String idUsuario) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.cargarMensaje(p_Mensaje, p_IdMedio, p_MedioTransmision, idUsuario);
  }
  
  public org.tempuri.Formulario cargarDesdeMensajeOriginal(org.tempuri.MensajeOriginal mensaje) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.cargarDesdeMensajeOriginal(mensaje);
  }
  
  public int cargarMensajeIntegrador(java.lang.String p_Mensaje, java.lang.String p_IdMedio, java.lang.String p_MedioTransmision, java.lang.String idUsuario) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.cargarMensajeIntegrador(p_Mensaje, p_IdMedio, p_MedioTransmision, idUsuario);
  }
  
  public org.tempuri.Formulario cargarMensajeCallCenter(java.lang.String p_Mensaje, java.lang.String p_IdCentro, java.lang.String p_IdMesa, java.lang.String p_IdObservador, java.lang.String telefono, java.lang.String idUsuario) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.cargarMensajeCallCenter(p_Mensaje, p_IdCentro, p_IdMesa, p_IdObservador, telefono, idUsuario);
  }
  
  public org.tempuri.Pregunta[] buscarPlantillaDeFormulario(java.lang.String id) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.buscarPlantillaDeFormulario(id);
  }
  
  public org.tempuri.MensajeOriginal buscarMensajeOriginal(int id) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.buscarMensajeOriginal(id);
  }
  
  public org.tempuri.MensajeAuditado[] buscarMensajeAuditado(java.lang.String idMesa, java.lang.String idCentro) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.buscarMensajeAuditado(idMesa, idCentro);
  }
  
  public org.tempuri.MensajeAuditado[] buscarMensajeAuditadoPorTelefono(java.lang.String telefono) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.buscarMensajeAuditadoPorTelefono(telefono);
  }
  
  public org.tempuri.MensajeAuditado[] buscarMensajeAuditadoFiltrado(org.tempuri.FiltroBusquedaMensajes filtro) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.buscarMensajeAuditadoFiltrado(filtro);
  }
  
  public org.tempuri.MensajeAuditado[] buscarMensajeAuditadoSOSFiltrado(org.tempuri.FiltroBusquedaMensajes filtro) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.buscarMensajeAuditadoSOSFiltrado(filtro);
  }
  
  public int actualizarMensajeAuditado(org.tempuri.MensajeAuditado mensaje) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.actualizarMensajeAuditado(mensaje);
  }
  
  public org.tempuri.Respuesta[] buscarRespuestas(org.tempuri.FiltroBusquedaMensajes filtro) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.buscarRespuestas(filtro);
  }
  
  public org.tempuri.MensajeRespuesta[] buscarMensajeRespuesta(org.tempuri.FiltroBusquedaMensajes filtro) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.buscarMensajeRespuesta(filtro);
  }
  
  public int actualizarMensajeRespuesta(org.tempuri.MensajeRespuesta mensaje) throws java.rmi.RemoteException{
    if (webServiceSoap == null)
      _initWebServiceSoapProxy();
    return webServiceSoap.actualizarMensajeRespuesta(mensaje);
  }
  
  
}