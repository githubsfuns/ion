/**
 * Respuesta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Respuesta  implements java.io.Serializable {
    private int idRespuesta;

    private int idFormulario;

    private java.lang.String tipoFormulario;

    private int idPregunta;

    private int posTag;

    private java.lang.String textoRespuesta;

    private java.lang.String idMesa;

    private java.util.Calendar fechaRegistro;

    private java.util.Calendar fechaRecepcion;

    private java.lang.String flagEstado;

    private java.lang.String descTag;

    private java.lang.String idObservador;

    private java.lang.String idCentroElectoral;

    private int idMensaje;

    public Respuesta() {
    }

    public Respuesta(
           int idRespuesta,
           int idFormulario,
           java.lang.String tipoFormulario,
           int idPregunta,
           int posTag,
           java.lang.String textoRespuesta,
           java.lang.String idMesa,
           java.util.Calendar fechaRegistro,
           java.util.Calendar fechaRecepcion,
           java.lang.String flagEstado,
           java.lang.String descTag,
           java.lang.String idObservador,
           java.lang.String idCentroElectoral,
           int idMensaje) {
           this.idRespuesta = idRespuesta;
           this.idFormulario = idFormulario;
           this.tipoFormulario = tipoFormulario;
           this.idPregunta = idPregunta;
           this.posTag = posTag;
           this.textoRespuesta = textoRespuesta;
           this.idMesa = idMesa;
           this.fechaRegistro = fechaRegistro;
           this.fechaRecepcion = fechaRecepcion;
           this.flagEstado = flagEstado;
           this.descTag = descTag;
           this.idObservador = idObservador;
           this.idCentroElectoral = idCentroElectoral;
           this.idMensaje = idMensaje;
    }


    /**
     * Gets the idRespuesta value for this Respuesta.
     * 
     * @return idRespuesta
     */
    public int getIdRespuesta() {
        return idRespuesta;
    }


    /**
     * Sets the idRespuesta value for this Respuesta.
     * 
     * @param idRespuesta
     */
    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }


    /**
     * Gets the idFormulario value for this Respuesta.
     * 
     * @return idFormulario
     */
    public int getIdFormulario() {
        return idFormulario;
    }


    /**
     * Sets the idFormulario value for this Respuesta.
     * 
     * @param idFormulario
     */
    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }


    /**
     * Gets the tipoFormulario value for this Respuesta.
     * 
     * @return tipoFormulario
     */
    public java.lang.String getTipoFormulario() {
        return tipoFormulario;
    }


    /**
     * Sets the tipoFormulario value for this Respuesta.
     * 
     * @param tipoFormulario
     */
    public void setTipoFormulario(java.lang.String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }


    /**
     * Gets the idPregunta value for this Respuesta.
     * 
     * @return idPregunta
     */
    public int getIdPregunta() {
        return idPregunta;
    }


    /**
     * Sets the idPregunta value for this Respuesta.
     * 
     * @param idPregunta
     */
    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }


    /**
     * Gets the posTag value for this Respuesta.
     * 
     * @return posTag
     */
    public int getPosTag() {
        return posTag;
    }


    /**
     * Sets the posTag value for this Respuesta.
     * 
     * @param posTag
     */
    public void setPosTag(int posTag) {
        this.posTag = posTag;
    }


    /**
     * Gets the textoRespuesta value for this Respuesta.
     * 
     * @return textoRespuesta
     */
    public java.lang.String getTextoRespuesta() {
        return textoRespuesta;
    }


    /**
     * Sets the textoRespuesta value for this Respuesta.
     * 
     * @param textoRespuesta
     */
    public void setTextoRespuesta(java.lang.String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }


    /**
     * Gets the idMesa value for this Respuesta.
     * 
     * @return idMesa
     */
    public java.lang.String getIdMesa() {
        return idMesa;
    }


    /**
     * Sets the idMesa value for this Respuesta.
     * 
     * @param idMesa
     */
    public void setIdMesa(java.lang.String idMesa) {
        this.idMesa = idMesa;
    }


    /**
     * Gets the fechaRegistro value for this Respuesta.
     * 
     * @return fechaRegistro
     */
    public java.util.Calendar getFechaRegistro() {
        return fechaRegistro;
    }


    /**
     * Sets the fechaRegistro value for this Respuesta.
     * 
     * @param fechaRegistro
     */
    public void setFechaRegistro(java.util.Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


    /**
     * Gets the fechaRecepcion value for this Respuesta.
     * 
     * @return fechaRecepcion
     */
    public java.util.Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }


    /**
     * Sets the fechaRecepcion value for this Respuesta.
     * 
     * @param fechaRecepcion
     */
    public void setFechaRecepcion(java.util.Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }


    /**
     * Gets the flagEstado value for this Respuesta.
     * 
     * @return flagEstado
     */
    public java.lang.String getFlagEstado() {
        return flagEstado;
    }


    /**
     * Sets the flagEstado value for this Respuesta.
     * 
     * @param flagEstado
     */
    public void setFlagEstado(java.lang.String flagEstado) {
        this.flagEstado = flagEstado;
    }


    /**
     * Gets the descTag value for this Respuesta.
     * 
     * @return descTag
     */
    public java.lang.String getDescTag() {
        return descTag;
    }


    /**
     * Sets the descTag value for this Respuesta.
     * 
     * @param descTag
     */
    public void setDescTag(java.lang.String descTag) {
        this.descTag = descTag;
    }


    /**
     * Gets the idObservador value for this Respuesta.
     * 
     * @return idObservador
     */
    public java.lang.String getIdObservador() {
        return idObservador;
    }


    /**
     * Sets the idObservador value for this Respuesta.
     * 
     * @param idObservador
     */
    public void setIdObservador(java.lang.String idObservador) {
        this.idObservador = idObservador;
    }


    /**
     * Gets the idCentroElectoral value for this Respuesta.
     * 
     * @return idCentroElectoral
     */
    public java.lang.String getIdCentroElectoral() {
        return idCentroElectoral;
    }


    /**
     * Sets the idCentroElectoral value for this Respuesta.
     * 
     * @param idCentroElectoral
     */
    public void setIdCentroElectoral(java.lang.String idCentroElectoral) {
        this.idCentroElectoral = idCentroElectoral;
    }


    /**
     * Gets the idMensaje value for this Respuesta.
     * 
     * @return idMensaje
     */
    public int getIdMensaje() {
        return idMensaje;
    }


    /**
     * Sets the idMensaje value for this Respuesta.
     * 
     * @param idMensaje
     */
    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Respuesta)) return false;
        Respuesta other = (Respuesta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idRespuesta == other.getIdRespuesta() &&
            this.idFormulario == other.getIdFormulario() &&
            ((this.tipoFormulario==null && other.getTipoFormulario()==null) || 
             (this.tipoFormulario!=null &&
              this.tipoFormulario.equals(other.getTipoFormulario()))) &&
            this.idPregunta == other.getIdPregunta() &&
            this.posTag == other.getPosTag() &&
            ((this.textoRespuesta==null && other.getTextoRespuesta()==null) || 
             (this.textoRespuesta!=null &&
              this.textoRespuesta.equals(other.getTextoRespuesta()))) &&
            ((this.idMesa==null && other.getIdMesa()==null) || 
             (this.idMesa!=null &&
              this.idMesa.equals(other.getIdMesa()))) &&
            ((this.fechaRegistro==null && other.getFechaRegistro()==null) || 
             (this.fechaRegistro!=null &&
              this.fechaRegistro.equals(other.getFechaRegistro()))) &&
            ((this.fechaRecepcion==null && other.getFechaRecepcion()==null) || 
             (this.fechaRecepcion!=null &&
              this.fechaRecepcion.equals(other.getFechaRecepcion()))) &&
            ((this.flagEstado==null && other.getFlagEstado()==null) || 
             (this.flagEstado!=null &&
              this.flagEstado.equals(other.getFlagEstado()))) &&
            ((this.descTag==null && other.getDescTag()==null) || 
             (this.descTag!=null &&
              this.descTag.equals(other.getDescTag()))) &&
            ((this.idObservador==null && other.getIdObservador()==null) || 
             (this.idObservador!=null &&
              this.idObservador.equals(other.getIdObservador()))) &&
            ((this.idCentroElectoral==null && other.getIdCentroElectoral()==null) || 
             (this.idCentroElectoral!=null &&
              this.idCentroElectoral.equals(other.getIdCentroElectoral()))) &&
            this.idMensaje == other.getIdMensaje();
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
        _hashCode += getIdRespuesta();
        _hashCode += getIdFormulario();
        if (getTipoFormulario() != null) {
            _hashCode += getTipoFormulario().hashCode();
        }
        _hashCode += getIdPregunta();
        _hashCode += getPosTag();
        if (getTextoRespuesta() != null) {
            _hashCode += getTextoRespuesta().hashCode();
        }
        if (getIdMesa() != null) {
            _hashCode += getIdMesa().hashCode();
        }
        if (getFechaRegistro() != null) {
            _hashCode += getFechaRegistro().hashCode();
        }
        if (getFechaRecepcion() != null) {
            _hashCode += getFechaRecepcion().hashCode();
        }
        if (getFlagEstado() != null) {
            _hashCode += getFlagEstado().hashCode();
        }
        if (getDescTag() != null) {
            _hashCode += getDescTag().hashCode();
        }
        if (getIdObservador() != null) {
            _hashCode += getIdObservador().hashCode();
        }
        if (getIdCentroElectoral() != null) {
            _hashCode += getIdCentroElectoral().hashCode();
        }
        _hashCode += getIdMensaje();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Respuesta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Respuesta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdFormulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoFormulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPregunta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdPregunta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("posTag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PosTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textoRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TextoRespuesta"));
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
        elemField.setFieldName("fechaRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaRecepcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaRecepcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FlagEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descTag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DescTag"));
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
        elemField.setFieldName("idCentroElectoral");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdCentroElectoral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMensaje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
