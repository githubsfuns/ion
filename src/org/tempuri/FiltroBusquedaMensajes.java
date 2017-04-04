/**
 * FiltroBusquedaMensajes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class FiltroBusquedaMensajes  implements java.io.Serializable {
    private java.util.Calendar fechaDesde;

    private java.util.Calendar fechaHasta;

    private java.lang.String idEstado;

    private java.lang.String idMunicipio;

    private java.lang.String idParroquia;

    private java.lang.String idMesa;

    private java.lang.String idCentro;

    private java.lang.String idObservador;

    private int idPregunta;

    private int idMensajeAuditado;

    private int idMensajeOriginal;

    private boolean activo;

    private boolean valido;

    private boolean respuestaEnviada;

    private org.tempuri.MedioTipo medioTransmision;

    private int cantidadMensajes;

    private java.lang.String idFormulario;

    private java.lang.String tipoFormulario;

    public FiltroBusquedaMensajes() {
    }

    public FiltroBusquedaMensajes(
           java.util.Calendar fechaDesde,
           java.util.Calendar fechaHasta,
           java.lang.String idEstado,
           java.lang.String idMunicipio,
           java.lang.String idParroquia,
           java.lang.String idMesa,
           java.lang.String idCentro,
           java.lang.String idObservador,
           int idPregunta,
           int idMensajeAuditado,
           int idMensajeOriginal,
           boolean activo,
           boolean valido,
           boolean respuestaEnviada,
           org.tempuri.MedioTipo medioTransmision,
           int cantidadMensajes,
           java.lang.String idFormulario,
           java.lang.String tipoFormulario) {
           this.fechaDesde = fechaDesde;
           this.fechaHasta = fechaHasta;
           this.idEstado = idEstado;
           this.idMunicipio = idMunicipio;
           this.idParroquia = idParroquia;
           this.idMesa = idMesa;
           this.idCentro = idCentro;
           this.idObservador = idObservador;
           this.idPregunta = idPregunta;
           this.idMensajeAuditado = idMensajeAuditado;
           this.idMensajeOriginal = idMensajeOriginal;
           this.activo = activo;
           this.valido = valido;
           this.respuestaEnviada = respuestaEnviada;
           this.medioTransmision = medioTransmision;
           this.cantidadMensajes = cantidadMensajes;
           this.idFormulario = idFormulario;
           this.tipoFormulario = tipoFormulario;
    }


    /**
     * Gets the fechaDesde value for this FiltroBusquedaMensajes.
     * 
     * @return fechaDesde
     */
    public java.util.Calendar getFechaDesde() {
        return fechaDesde;
    }


    /**
     * Sets the fechaDesde value for this FiltroBusquedaMensajes.
     * 
     * @param fechaDesde
     */
    public void setFechaDesde(java.util.Calendar fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    /**
     * Gets the fechaHasta value for this FiltroBusquedaMensajes.
     * 
     * @return fechaHasta
     */
    public java.util.Calendar getFechaHasta() {
        return fechaHasta;
    }


    /**
     * Sets the fechaHasta value for this FiltroBusquedaMensajes.
     * 
     * @param fechaHasta
     */
    public void setFechaHasta(java.util.Calendar fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


    /**
     * Gets the idEstado value for this FiltroBusquedaMensajes.
     * 
     * @return idEstado
     */
    public java.lang.String getIdEstado() {
        return idEstado;
    }


    /**
     * Sets the idEstado value for this FiltroBusquedaMensajes.
     * 
     * @param idEstado
     */
    public void setIdEstado(java.lang.String idEstado) {
        this.idEstado = idEstado;
    }


    /**
     * Gets the idMunicipio value for this FiltroBusquedaMensajes.
     * 
     * @return idMunicipio
     */
    public java.lang.String getIdMunicipio() {
        return idMunicipio;
    }


    /**
     * Sets the idMunicipio value for this FiltroBusquedaMensajes.
     * 
     * @param idMunicipio
     */
    public void setIdMunicipio(java.lang.String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }


    /**
     * Gets the idParroquia value for this FiltroBusquedaMensajes.
     * 
     * @return idParroquia
     */
    public java.lang.String getIdParroquia() {
        return idParroquia;
    }


    /**
     * Sets the idParroquia value for this FiltroBusquedaMensajes.
     * 
     * @param idParroquia
     */
    public void setIdParroquia(java.lang.String idParroquia) {
        this.idParroquia = idParroquia;
    }


    /**
     * Gets the idMesa value for this FiltroBusquedaMensajes.
     * 
     * @return idMesa
     */
    public java.lang.String getIdMesa() {
        return idMesa;
    }


    /**
     * Sets the idMesa value for this FiltroBusquedaMensajes.
     * 
     * @param idMesa
     */
    public void setIdMesa(java.lang.String idMesa) {
        this.idMesa = idMesa;
    }


    /**
     * Gets the idCentro value for this FiltroBusquedaMensajes.
     * 
     * @return idCentro
     */
    public java.lang.String getIdCentro() {
        return idCentro;
    }


    /**
     * Sets the idCentro value for this FiltroBusquedaMensajes.
     * 
     * @param idCentro
     */
    public void setIdCentro(java.lang.String idCentro) {
        this.idCentro = idCentro;
    }


    /**
     * Gets the idObservador value for this FiltroBusquedaMensajes.
     * 
     * @return idObservador
     */
    public java.lang.String getIdObservador() {
        return idObservador;
    }


    /**
     * Sets the idObservador value for this FiltroBusquedaMensajes.
     * 
     * @param idObservador
     */
    public void setIdObservador(java.lang.String idObservador) {
        this.idObservador = idObservador;
    }


    /**
     * Gets the idPregunta value for this FiltroBusquedaMensajes.
     * 
     * @return idPregunta
     */
    public int getIdPregunta() {
        return idPregunta;
    }


    /**
     * Sets the idPregunta value for this FiltroBusquedaMensajes.
     * 
     * @param idPregunta
     */
    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }


    /**
     * Gets the idMensajeAuditado value for this FiltroBusquedaMensajes.
     * 
     * @return idMensajeAuditado
     */
    public int getIdMensajeAuditado() {
        return idMensajeAuditado;
    }


    /**
     * Sets the idMensajeAuditado value for this FiltroBusquedaMensajes.
     * 
     * @param idMensajeAuditado
     */
    public void setIdMensajeAuditado(int idMensajeAuditado) {
        this.idMensajeAuditado = idMensajeAuditado;
    }


    /**
     * Gets the idMensajeOriginal value for this FiltroBusquedaMensajes.
     * 
     * @return idMensajeOriginal
     */
    public int getIdMensajeOriginal() {
        return idMensajeOriginal;
    }


    /**
     * Sets the idMensajeOriginal value for this FiltroBusquedaMensajes.
     * 
     * @param idMensajeOriginal
     */
    public void setIdMensajeOriginal(int idMensajeOriginal) {
        this.idMensajeOriginal = idMensajeOriginal;
    }


    /**
     * Gets the activo value for this FiltroBusquedaMensajes.
     * 
     * @return activo
     */
    public boolean isActivo() {
        return activo;
    }


    /**
     * Sets the activo value for this FiltroBusquedaMensajes.
     * 
     * @param activo
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    /**
     * Gets the valido value for this FiltroBusquedaMensajes.
     * 
     * @return valido
     */
    public boolean isValido() {
        return valido;
    }


    /**
     * Sets the valido value for this FiltroBusquedaMensajes.
     * 
     * @param valido
     */
    public void setValido(boolean valido) {
        this.valido = valido;
    }


    /**
     * Gets the respuestaEnviada value for this FiltroBusquedaMensajes.
     * 
     * @return respuestaEnviada
     */
    public boolean isRespuestaEnviada() {
        return respuestaEnviada;
    }


    /**
     * Sets the respuestaEnviada value for this FiltroBusquedaMensajes.
     * 
     * @param respuestaEnviada
     */
    public void setRespuestaEnviada(boolean respuestaEnviada) {
        this.respuestaEnviada = respuestaEnviada;
    }


    /**
     * Gets the medioTransmision value for this FiltroBusquedaMensajes.
     * 
     * @return medioTransmision
     */
    public org.tempuri.MedioTipo getMedioTransmision() {
        return medioTransmision;
    }


    /**
     * Sets the medioTransmision value for this FiltroBusquedaMensajes.
     * 
     * @param medioTransmision
     */
    public void setMedioTransmision(org.tempuri.MedioTipo medioTransmision) {
        this.medioTransmision = medioTransmision;
    }


    /**
     * Gets the cantidadMensajes value for this FiltroBusquedaMensajes.
     * 
     * @return cantidadMensajes
     */
    public int getCantidadMensajes() {
        return cantidadMensajes;
    }


    /**
     * Sets the cantidadMensajes value for this FiltroBusquedaMensajes.
     * 
     * @param cantidadMensajes
     */
    public void setCantidadMensajes(int cantidadMensajes) {
        this.cantidadMensajes = cantidadMensajes;
    }


    /**
     * Gets the idFormulario value for this FiltroBusquedaMensajes.
     * 
     * @return idFormulario
     */
    public java.lang.String getIdFormulario() {
        return idFormulario;
    }


    /**
     * Sets the idFormulario value for this FiltroBusquedaMensajes.
     * 
     * @param idFormulario
     */
    public void setIdFormulario(java.lang.String idFormulario) {
        this.idFormulario = idFormulario;
    }


    /**
     * Gets the tipoFormulario value for this FiltroBusquedaMensajes.
     * 
     * @return tipoFormulario
     */
    public java.lang.String getTipoFormulario() {
        return tipoFormulario;
    }


    /**
     * Sets the tipoFormulario value for this FiltroBusquedaMensajes.
     * 
     * @param tipoFormulario
     */
    public void setTipoFormulario(java.lang.String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FiltroBusquedaMensajes)) return false;
        FiltroBusquedaMensajes other = (FiltroBusquedaMensajes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaDesde==null && other.getFechaDesde()==null) || 
             (this.fechaDesde!=null &&
              this.fechaDesde.equals(other.getFechaDesde()))) &&
            ((this.fechaHasta==null && other.getFechaHasta()==null) || 
             (this.fechaHasta!=null &&
              this.fechaHasta.equals(other.getFechaHasta()))) &&
            ((this.idEstado==null && other.getIdEstado()==null) || 
             (this.idEstado!=null &&
              this.idEstado.equals(other.getIdEstado()))) &&
            ((this.idMunicipio==null && other.getIdMunicipio()==null) || 
             (this.idMunicipio!=null &&
              this.idMunicipio.equals(other.getIdMunicipio()))) &&
            ((this.idParroquia==null && other.getIdParroquia()==null) || 
             (this.idParroquia!=null &&
              this.idParroquia.equals(other.getIdParroquia()))) &&
            ((this.idMesa==null && other.getIdMesa()==null) || 
             (this.idMesa!=null &&
              this.idMesa.equals(other.getIdMesa()))) &&
            ((this.idCentro==null && other.getIdCentro()==null) || 
             (this.idCentro!=null &&
              this.idCentro.equals(other.getIdCentro()))) &&
            ((this.idObservador==null && other.getIdObservador()==null) || 
             (this.idObservador!=null &&
              this.idObservador.equals(other.getIdObservador()))) &&
            this.idPregunta == other.getIdPregunta() &&
            this.idMensajeAuditado == other.getIdMensajeAuditado() &&
            this.idMensajeOriginal == other.getIdMensajeOriginal() &&
            this.activo == other.isActivo() &&
            this.valido == other.isValido() &&
            this.respuestaEnviada == other.isRespuestaEnviada() &&
            ((this.medioTransmision==null && other.getMedioTransmision()==null) || 
             (this.medioTransmision!=null &&
              this.medioTransmision.equals(other.getMedioTransmision()))) &&
            this.cantidadMensajes == other.getCantidadMensajes() &&
            ((this.idFormulario==null && other.getIdFormulario()==null) || 
             (this.idFormulario!=null &&
              this.idFormulario.equals(other.getIdFormulario()))) &&
            ((this.tipoFormulario==null && other.getTipoFormulario()==null) || 
             (this.tipoFormulario!=null &&
              this.tipoFormulario.equals(other.getTipoFormulario())));
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
        if (getFechaDesde() != null) {
            _hashCode += getFechaDesde().hashCode();
        }
        if (getFechaHasta() != null) {
            _hashCode += getFechaHasta().hashCode();
        }
        if (getIdEstado() != null) {
            _hashCode += getIdEstado().hashCode();
        }
        if (getIdMunicipio() != null) {
            _hashCode += getIdMunicipio().hashCode();
        }
        if (getIdParroquia() != null) {
            _hashCode += getIdParroquia().hashCode();
        }
        if (getIdMesa() != null) {
            _hashCode += getIdMesa().hashCode();
        }
        if (getIdCentro() != null) {
            _hashCode += getIdCentro().hashCode();
        }
        if (getIdObservador() != null) {
            _hashCode += getIdObservador().hashCode();
        }
        _hashCode += getIdPregunta();
        _hashCode += getIdMensajeAuditado();
        _hashCode += getIdMensajeOriginal();
        _hashCode += (isActivo() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isValido() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isRespuestaEnviada() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getMedioTransmision() != null) {
            _hashCode += getMedioTransmision().hashCode();
        }
        _hashCode += getCantidadMensajes();
        if (getIdFormulario() != null) {
            _hashCode += getIdFormulario().hashCode();
        }
        if (getTipoFormulario() != null) {
            _hashCode += getTipoFormulario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FiltroBusquedaMensajes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "FiltroBusquedaMensajes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEstado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdEstado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMunicipio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMunicipio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idParroquia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdParroquia"));
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
        elemField.setFieldName("idCentro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdCentro"));
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
        elemField.setFieldName("idPregunta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdPregunta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMensajeAuditado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMensajeAuditado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMensajeOriginal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMensajeOriginal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Activo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Valido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuestaEnviada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RespuestaEnviada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioTransmision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioTransmision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MedioTipo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantidadMensajes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CantidadMensajes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdFormulario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoFormulario"));
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
