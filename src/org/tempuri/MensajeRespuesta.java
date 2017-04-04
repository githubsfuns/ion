/**
 * MensajeRespuesta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MensajeRespuesta  implements java.io.Serializable {
    private int id;

    private java.lang.String destino;

    private java.lang.String mensaje;

    private int idMensajeAuditado;

    private java.util.Calendar fechaEnvio;

    private java.lang.String idCentro;

    private java.lang.String idMesa;

    private java.util.Calendar fechaRecepcion;

    private java.lang.String tipoMensaje;

    public MensajeRespuesta() {
    }

    public MensajeRespuesta(
           int id,
           java.lang.String destino,
           java.lang.String mensaje,
           int idMensajeAuditado,
           java.util.Calendar fechaEnvio,
           java.lang.String idCentro,
           java.lang.String idMesa,
           java.util.Calendar fechaRecepcion,
           java.lang.String tipoMensaje) {
           this.id = id;
           this.destino = destino;
           this.mensaje = mensaje;
           this.idMensajeAuditado = idMensajeAuditado;
           this.fechaEnvio = fechaEnvio;
           this.idCentro = idCentro;
           this.idMesa = idMesa;
           this.fechaRecepcion = fechaRecepcion;
           this.tipoMensaje = tipoMensaje;
    }


    /**
     * Gets the id value for this MensajeRespuesta.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this MensajeRespuesta.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the destino value for this MensajeRespuesta.
     * 
     * @return destino
     */
    public java.lang.String getDestino() {
        return destino;
    }


    /**
     * Sets the destino value for this MensajeRespuesta.
     * 
     * @param destino
     */
    public void setDestino(java.lang.String destino) {
        this.destino = destino;
    }


    /**
     * Gets the mensaje value for this MensajeRespuesta.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this MensajeRespuesta.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }


    /**
     * Gets the idMensajeAuditado value for this MensajeRespuesta.
     * 
     * @return idMensajeAuditado
     */
    public int getIdMensajeAuditado() {
        return idMensajeAuditado;
    }


    /**
     * Sets the idMensajeAuditado value for this MensajeRespuesta.
     * 
     * @param idMensajeAuditado
     */
    public void setIdMensajeAuditado(int idMensajeAuditado) {
        this.idMensajeAuditado = idMensajeAuditado;
    }


    /**
     * Gets the fechaEnvio value for this MensajeRespuesta.
     * 
     * @return fechaEnvio
     */
    public java.util.Calendar getFechaEnvio() {
        return fechaEnvio;
    }


    /**
     * Sets the fechaEnvio value for this MensajeRespuesta.
     * 
     * @param fechaEnvio
     */
    public void setFechaEnvio(java.util.Calendar fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }


    /**
     * Gets the idCentro value for this MensajeRespuesta.
     * 
     * @return idCentro
     */
    public java.lang.String getIdCentro() {
        return idCentro;
    }


    /**
     * Sets the idCentro value for this MensajeRespuesta.
     * 
     * @param idCentro
     */
    public void setIdCentro(java.lang.String idCentro) {
        this.idCentro = idCentro;
    }


    /**
     * Gets the idMesa value for this MensajeRespuesta.
     * 
     * @return idMesa
     */
    public java.lang.String getIdMesa() {
        return idMesa;
    }


    /**
     * Sets the idMesa value for this MensajeRespuesta.
     * 
     * @param idMesa
     */
    public void setIdMesa(java.lang.String idMesa) {
        this.idMesa = idMesa;
    }


    /**
     * Gets the fechaRecepcion value for this MensajeRespuesta.
     * 
     * @return fechaRecepcion
     */
    public java.util.Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }


    /**
     * Sets the fechaRecepcion value for this MensajeRespuesta.
     * 
     * @param fechaRecepcion
     */
    public void setFechaRecepcion(java.util.Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }


    /**
     * Gets the tipoMensaje value for this MensajeRespuesta.
     * 
     * @return tipoMensaje
     */
    public java.lang.String getTipoMensaje() {
        return tipoMensaje;
    }


    /**
     * Sets the tipoMensaje value for this MensajeRespuesta.
     * 
     * @param tipoMensaje
     */
    public void setTipoMensaje(java.lang.String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MensajeRespuesta)) return false;
        MensajeRespuesta other = (MensajeRespuesta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.destino==null && other.getDestino()==null) || 
             (this.destino!=null &&
              this.destino.equals(other.getDestino()))) &&
            ((this.mensaje==null && other.getMensaje()==null) || 
             (this.mensaje!=null &&
              this.mensaje.equals(other.getMensaje()))) &&
            this.idMensajeAuditado == other.getIdMensajeAuditado() &&
            ((this.fechaEnvio==null && other.getFechaEnvio()==null) || 
             (this.fechaEnvio!=null &&
              this.fechaEnvio.equals(other.getFechaEnvio()))) &&
            ((this.idCentro==null && other.getIdCentro()==null) || 
             (this.idCentro!=null &&
              this.idCentro.equals(other.getIdCentro()))) &&
            ((this.idMesa==null && other.getIdMesa()==null) || 
             (this.idMesa!=null &&
              this.idMesa.equals(other.getIdMesa()))) &&
            ((this.fechaRecepcion==null && other.getFechaRecepcion()==null) || 
             (this.fechaRecepcion!=null &&
              this.fechaRecepcion.equals(other.getFechaRecepcion()))) &&
            ((this.tipoMensaje==null && other.getTipoMensaje()==null) || 
             (this.tipoMensaje!=null &&
              this.tipoMensaje.equals(other.getTipoMensaje())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getId();
        if (getDestino() != null) {
            _hashCode += getDestino().hashCode();
        }
        if (getMensaje() != null) {
            _hashCode += getMensaje().hashCode();
        }
        _hashCode += getIdMensajeAuditado();
        if (getFechaEnvio() != null) {
            _hashCode += getFechaEnvio().hashCode();
        }
        if (getIdCentro() != null) {
            _hashCode += getIdCentro().hashCode();
        }
        if (getIdMesa() != null) {
            _hashCode += getIdMesa().hashCode();
        }
        if (getFechaRecepcion() != null) {
            _hashCode += getFechaRecepcion().hashCode();
        }
        if (getTipoMensaje() != null) {
            _hashCode += getTipoMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MensajeRespuesta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MensajeRespuesta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destino");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Destino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Mensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMensajeAuditado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMensajeAuditado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCentro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdCentro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMesa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMesa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaRecepcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaRecepcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
