/**
 * MensajeOriginal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MensajeOriginal  implements java.io.Serializable {
    private int idMensajeOriginal;

    private java.lang.String idMedio;

    private java.lang.String texto;

    private java.util.Calendar fechaEnvio;

    private java.util.Calendar fechaRecepcionIntegrador;

    private java.util.Calendar fechaRecepcionTotalizador;

    private org.tempuri.MedioTipo medio;

    private int idIntegrador;

    private java.lang.String idUsuario;

    private java.lang.String idObservador;

    private java.lang.String idCentro;

    private java.lang.String idMesa;

    public MensajeOriginal() {
    }

    public MensajeOriginal(
           int idMensajeOriginal,
           java.lang.String idMedio,
           java.lang.String texto,
           java.util.Calendar fechaEnvio,
           java.util.Calendar fechaRecepcionIntegrador,
           java.util.Calendar fechaRecepcionTotalizador,
           org.tempuri.MedioTipo medio,
           int idIntegrador,
           java.lang.String idUsuario,
           java.lang.String idObservador,
           java.lang.String idCentro,
           java.lang.String idMesa) {
           this.idMensajeOriginal = idMensajeOriginal;
           this.idMedio = idMedio;
           this.texto = texto;
           this.fechaEnvio = fechaEnvio;
           this.fechaRecepcionIntegrador = fechaRecepcionIntegrador;
           this.fechaRecepcionTotalizador = fechaRecepcionTotalizador;
           this.medio = medio;
           this.idIntegrador = idIntegrador;
           this.idUsuario = idUsuario;
           this.idObservador = idObservador;
           this.idCentro = idCentro;
           this.idMesa = idMesa;
    }


    /**
     * Gets the idMensajeOriginal value for this MensajeOriginal.
     * 
     * @return idMensajeOriginal
     */
    public int getIdMensajeOriginal() {
        return idMensajeOriginal;
    }


    /**
     * Sets the idMensajeOriginal value for this MensajeOriginal.
     * 
     * @param idMensajeOriginal
     */
    public void setIdMensajeOriginal(int idMensajeOriginal) {
        this.idMensajeOriginal = idMensajeOriginal;
    }


    /**
     * Gets the idMedio value for this MensajeOriginal.
     * 
     * @return idMedio
     */
    public java.lang.String getIdMedio() {
        return idMedio;
    }


    /**
     * Sets the idMedio value for this MensajeOriginal.
     * 
     * @param idMedio
     */
    public void setIdMedio(java.lang.String idMedio) {
        this.idMedio = idMedio;
    }


    /**
     * Gets the texto value for this MensajeOriginal.
     * 
     * @return texto
     */
    public java.lang.String getTexto() {
        return texto;
    }


    /**
     * Sets the texto value for this MensajeOriginal.
     * 
     * @param texto
     */
    public void setTexto(java.lang.String texto) {
        this.texto = texto;
    }


    /**
     * Gets the fechaEnvio value for this MensajeOriginal.
     * 
     * @return fechaEnvio
     */
    public java.util.Calendar getFechaEnvio() {
        return fechaEnvio;
    }


    /**
     * Sets the fechaEnvio value for this MensajeOriginal.
     * 
     * @param fechaEnvio
     */
    public void setFechaEnvio(java.util.Calendar fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }


    /**
     * Gets the fechaRecepcionIntegrador value for this MensajeOriginal.
     * 
     * @return fechaRecepcionIntegrador
     */
    public java.util.Calendar getFechaRecepcionIntegrador() {
        return fechaRecepcionIntegrador;
    }


    /**
     * Sets the fechaRecepcionIntegrador value for this MensajeOriginal.
     * 
     * @param fechaRecepcionIntegrador
     */
    public void setFechaRecepcionIntegrador(java.util.Calendar fechaRecepcionIntegrador) {
        this.fechaRecepcionIntegrador = fechaRecepcionIntegrador;
    }


    /**
     * Gets the fechaRecepcionTotalizador value for this MensajeOriginal.
     * 
     * @return fechaRecepcionTotalizador
     */
    public java.util.Calendar getFechaRecepcionTotalizador() {
        return fechaRecepcionTotalizador;
    }


    /**
     * Sets the fechaRecepcionTotalizador value for this MensajeOriginal.
     * 
     * @param fechaRecepcionTotalizador
     */
    public void setFechaRecepcionTotalizador(java.util.Calendar fechaRecepcionTotalizador) {
        this.fechaRecepcionTotalizador = fechaRecepcionTotalizador;
    }


    /**
     * Gets the medio value for this MensajeOriginal.
     * 
     * @return medio
     */
    public org.tempuri.MedioTipo getMedio() {
        return medio;
    }


    /**
     * Sets the medio value for this MensajeOriginal.
     * 
     * @param medio
     */
    public void setMedio(org.tempuri.MedioTipo medio) {
        this.medio = medio;
    }


    /**
     * Gets the idIntegrador value for this MensajeOriginal.
     * 
     * @return idIntegrador
     */
    public int getIdIntegrador() {
        return idIntegrador;
    }


    /**
     * Sets the idIntegrador value for this MensajeOriginal.
     * 
     * @param idIntegrador
     */
    public void setIdIntegrador(int idIntegrador) {
        this.idIntegrador = idIntegrador;
    }


    /**
     * Gets the idUsuario value for this MensajeOriginal.
     * 
     * @return idUsuario
     */
    public java.lang.String getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the idUsuario value for this MensajeOriginal.
     * 
     * @param idUsuario
     */
    public void setIdUsuario(java.lang.String idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Gets the idObservador value for this MensajeOriginal.
     * 
     * @return idObservador
     */
    public java.lang.String getIdObservador() {
        return idObservador;
    }


    /**
     * Sets the idObservador value for this MensajeOriginal.
     * 
     * @param idObservador
     */
    public void setIdObservador(java.lang.String idObservador) {
        this.idObservador = idObservador;
    }


    /**
     * Gets the idCentro value for this MensajeOriginal.
     * 
     * @return idCentro
     */
    public java.lang.String getIdCentro() {
        return idCentro;
    }


    /**
     * Sets the idCentro value for this MensajeOriginal.
     * 
     * @param idCentro
     */
    public void setIdCentro(java.lang.String idCentro) {
        this.idCentro = idCentro;
    }


    /**
     * Gets the idMesa value for this MensajeOriginal.
     * 
     * @return idMesa
     */
    public java.lang.String getIdMesa() {
        return idMesa;
    }


    /**
     * Sets the idMesa value for this MensajeOriginal.
     * 
     * @param idMesa
     */
    public void setIdMesa(java.lang.String idMesa) {
        this.idMesa = idMesa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MensajeOriginal)) return false;
        MensajeOriginal other = (MensajeOriginal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMensajeOriginal == other.getIdMensajeOriginal() &&
            ((this.idMedio==null && other.getIdMedio()==null) || 
             (this.idMedio!=null &&
              this.idMedio.equals(other.getIdMedio()))) &&
            ((this.texto==null && other.getTexto()==null) || 
             (this.texto!=null &&
              this.texto.equals(other.getTexto()))) &&
            ((this.fechaEnvio==null && other.getFechaEnvio()==null) || 
             (this.fechaEnvio!=null &&
              this.fechaEnvio.equals(other.getFechaEnvio()))) &&
            ((this.fechaRecepcionIntegrador==null && other.getFechaRecepcionIntegrador()==null) || 
             (this.fechaRecepcionIntegrador!=null &&
              this.fechaRecepcionIntegrador.equals(other.getFechaRecepcionIntegrador()))) &&
            ((this.fechaRecepcionTotalizador==null && other.getFechaRecepcionTotalizador()==null) || 
             (this.fechaRecepcionTotalizador!=null &&
              this.fechaRecepcionTotalizador.equals(other.getFechaRecepcionTotalizador()))) &&
            ((this.medio==null && other.getMedio()==null) || 
             (this.medio!=null &&
              this.medio.equals(other.getMedio()))) &&
            this.idIntegrador == other.getIdIntegrador() &&
            ((this.idUsuario==null && other.getIdUsuario()==null) || 
             (this.idUsuario!=null &&
              this.idUsuario.equals(other.getIdUsuario()))) &&
            ((this.idObservador==null && other.getIdObservador()==null) || 
             (this.idObservador!=null &&
              this.idObservador.equals(other.getIdObservador()))) &&
            ((this.idCentro==null && other.getIdCentro()==null) || 
             (this.idCentro!=null &&
              this.idCentro.equals(other.getIdCentro()))) &&
            ((this.idMesa==null && other.getIdMesa()==null) || 
             (this.idMesa!=null &&
              this.idMesa.equals(other.getIdMesa())));
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
        _hashCode += getIdMensajeOriginal();
        if (getIdMedio() != null) {
            _hashCode += getIdMedio().hashCode();
        }
        if (getTexto() != null) {
            _hashCode += getTexto().hashCode();
        }
        if (getFechaEnvio() != null) {
            _hashCode += getFechaEnvio().hashCode();
        }
        if (getFechaRecepcionIntegrador() != null) {
            _hashCode += getFechaRecepcionIntegrador().hashCode();
        }
        if (getFechaRecepcionTotalizador() != null) {
            _hashCode += getFechaRecepcionTotalizador().hashCode();
        }
        if (getMedio() != null) {
            _hashCode += getMedio().hashCode();
        }
        _hashCode += getIdIntegrador();
        if (getIdUsuario() != null) {
            _hashCode += getIdUsuario().hashCode();
        }
        if (getIdObservador() != null) {
            _hashCode += getIdObservador().hashCode();
        }
        if (getIdCentro() != null) {
            _hashCode += getIdCentro().hashCode();
        }
        if (getIdMesa() != null) {
            _hashCode += getIdMesa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MensajeOriginal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MensajeOriginal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMensajeOriginal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMensajeOriginal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMedio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMedio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("texto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Texto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaEnvio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaEnvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaRecepcionIntegrador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaRecepcionIntegrador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaRecepcionTotalizador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaRecepcionTotalizador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Medio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MedioTipo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idIntegrador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdIntegrador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idObservador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdObservador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
