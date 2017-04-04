/**
 * MensajeAuditado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MensajeAuditado  implements java.io.Serializable {
    private int idMensaje;

    private int idMensajeOriginal;

    private java.lang.String tipoFormulario;

    private java.lang.String idCentro;

    private java.lang.String idMesa;

    private java.lang.String numero;

    private java.lang.String idObservador;

    private java.lang.String idSupervisor;

    private boolean activo;

    private java.lang.String texto;

    private java.lang.String cedula;

    private java.lang.String telefono;

    private java.util.Calendar fechaEnvio;

    private java.util.Calendar fechaRecepcionIntegrador;

    private java.util.Calendar fechaRecepcionTotalizador;

    private java.util.Calendar fechaAuditoria;

    private org.tempuri.MedioTipo medioTransmision;

    private java.lang.String comentario;

    private java.lang.String comentarioRetorno;

    private boolean estadoValido;

    private java.lang.String idUsuario;

    private double tiempoAuditoria;

    private boolean comentarioEnviado;

    private java.lang.String tipoMensaje;

    public MensajeAuditado() {
    }

    public MensajeAuditado(
           int idMensaje,
           int idMensajeOriginal,
           java.lang.String tipoFormulario,
           java.lang.String idCentro,
           java.lang.String idMesa,
           java.lang.String numero,
           java.lang.String idObservador,
           java.lang.String idSupervisor,
           boolean activo,
           java.lang.String texto,
           java.lang.String cedula,
           java.lang.String telefono,
           java.util.Calendar fechaEnvio,
           java.util.Calendar fechaRecepcionIntegrador,
           java.util.Calendar fechaRecepcionTotalizador,
           java.util.Calendar fechaAuditoria,
           org.tempuri.MedioTipo medioTransmision,
           java.lang.String comentario,
           java.lang.String comentarioRetorno,
           boolean estadoValido,
           java.lang.String idUsuario,
           double tiempoAuditoria,
           boolean comentarioEnviado,
           java.lang.String tipoMensaje) {
           this.idMensaje = idMensaje;
           this.idMensajeOriginal = idMensajeOriginal;
           this.tipoFormulario = tipoFormulario;
           this.idCentro = idCentro;
           this.idMesa = idMesa;
           this.numero = numero;
           this.idObservador = idObservador;
           this.idSupervisor = idSupervisor;
           this.activo = activo;
           this.texto = texto;
           this.cedula = cedula;
           this.telefono = telefono;
           this.fechaEnvio = fechaEnvio;
           this.fechaRecepcionIntegrador = fechaRecepcionIntegrador;
           this.fechaRecepcionTotalizador = fechaRecepcionTotalizador;
           this.fechaAuditoria = fechaAuditoria;
           this.medioTransmision = medioTransmision;
           this.comentario = comentario;
           this.comentarioRetorno = comentarioRetorno;
           this.estadoValido = estadoValido;
           this.idUsuario = idUsuario;
           this.tiempoAuditoria = tiempoAuditoria;
           this.comentarioEnviado = comentarioEnviado;
           this.tipoMensaje = tipoMensaje;
    }


    /**
     * Gets the idMensaje value for this MensajeAuditado.
     * 
     * @return idMensaje
     */
    public int getIdMensaje() {
        return idMensaje;
    }


    /**
     * Sets the idMensaje value for this MensajeAuditado.
     * 
     * @param idMensaje
     */
    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }


    /**
     * Gets the idMensajeOriginal value for this MensajeAuditado.
     * 
     * @return idMensajeOriginal
     */
    public int getIdMensajeOriginal() {
        return idMensajeOriginal;
    }


    /**
     * Sets the idMensajeOriginal value for this MensajeAuditado.
     * 
     * @param idMensajeOriginal
     */
    public void setIdMensajeOriginal(int idMensajeOriginal) {
        this.idMensajeOriginal = idMensajeOriginal;
    }


    /**
     * Gets the tipoFormulario value for this MensajeAuditado.
     * 
     * @return tipoFormulario
     */
    public java.lang.String getTipoFormulario() {
        return tipoFormulario;
    }


    /**
     * Sets the tipoFormulario value for this MensajeAuditado.
     * 
     * @param tipoFormulario
     */
    public void setTipoFormulario(java.lang.String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }


    /**
     * Gets the idCentro value for this MensajeAuditado.
     * 
     * @return idCentro
     */
    public java.lang.String getIdCentro() {
        return idCentro;
    }


    /**
     * Sets the idCentro value for this MensajeAuditado.
     * 
     * @param idCentro
     */
    public void setIdCentro(java.lang.String idCentro) {
        this.idCentro = idCentro;
    }


    /**
     * Gets the idMesa value for this MensajeAuditado.
     * 
     * @return idMesa
     */
    public java.lang.String getIdMesa() {
        return idMesa;
    }


    /**
     * Sets the idMesa value for this MensajeAuditado.
     * 
     * @param idMesa
     */
    public void setIdMesa(java.lang.String idMesa) {
        this.idMesa = idMesa;
    }


    /**
     * Gets the numero value for this MensajeAuditado.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this MensajeAuditado.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the idObservador value for this MensajeAuditado.
     * 
     * @return idObservador
     */
    public java.lang.String getIdObservador() {
        return idObservador;
    }


    /**
     * Sets the idObservador value for this MensajeAuditado.
     * 
     * @param idObservador
     */
    public void setIdObservador(java.lang.String idObservador) {
        this.idObservador = idObservador;
    }


    /**
     * Gets the idSupervisor value for this MensajeAuditado.
     * 
     * @return idSupervisor
     */
    public java.lang.String getIdSupervisor() {
        return idSupervisor;
    }


    /**
     * Sets the idSupervisor value for this MensajeAuditado.
     * 
     * @param idSupervisor
     */
    public void setIdSupervisor(java.lang.String idSupervisor) {
        this.idSupervisor = idSupervisor;
    }


    /**
     * Gets the activo value for this MensajeAuditado.
     * 
     * @return activo
     */
    public boolean isActivo() {
        return activo;
    }


    /**
     * Sets the activo value for this MensajeAuditado.
     * 
     * @param activo
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    /**
     * Gets the texto value for this MensajeAuditado.
     * 
     * @return texto
     */
    public java.lang.String getTexto() {
        return texto;
    }


    /**
     * Sets the texto value for this MensajeAuditado.
     * 
     * @param texto
     */
    public void setTexto(java.lang.String texto) {
        this.texto = texto;
    }


    /**
     * Gets the cedula value for this MensajeAuditado.
     * 
     * @return cedula
     */
    public java.lang.String getCedula() {
        return cedula;
    }


    /**
     * Sets the cedula value for this MensajeAuditado.
     * 
     * @param cedula
     */
    public void setCedula(java.lang.String cedula) {
        this.cedula = cedula;
    }


    /**
     * Gets the telefono value for this MensajeAuditado.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this MensajeAuditado.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the fechaEnvio value for this MensajeAuditado.
     * 
     * @return fechaEnvio
     */
    public java.util.Calendar getFechaEnvio() {
        return fechaEnvio;
    }


    /**
     * Sets the fechaEnvio value for this MensajeAuditado.
     * 
     * @param fechaEnvio
     */
    public void setFechaEnvio(java.util.Calendar fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }


    /**
     * Gets the fechaRecepcionIntegrador value for this MensajeAuditado.
     * 
     * @return fechaRecepcionIntegrador
     */
    public java.util.Calendar getFechaRecepcionIntegrador() {
        return fechaRecepcionIntegrador;
    }


    /**
     * Sets the fechaRecepcionIntegrador value for this MensajeAuditado.
     * 
     * @param fechaRecepcionIntegrador
     */
    public void setFechaRecepcionIntegrador(java.util.Calendar fechaRecepcionIntegrador) {
        this.fechaRecepcionIntegrador = fechaRecepcionIntegrador;
    }


    /**
     * Gets the fechaRecepcionTotalizador value for this MensajeAuditado.
     * 
     * @return fechaRecepcionTotalizador
     */
    public java.util.Calendar getFechaRecepcionTotalizador() {
        return fechaRecepcionTotalizador;
    }


    /**
     * Sets the fechaRecepcionTotalizador value for this MensajeAuditado.
     * 
     * @param fechaRecepcionTotalizador
     */
    public void setFechaRecepcionTotalizador(java.util.Calendar fechaRecepcionTotalizador) {
        this.fechaRecepcionTotalizador = fechaRecepcionTotalizador;
    }


    /**
     * Gets the fechaAuditoria value for this MensajeAuditado.
     * 
     * @return fechaAuditoria
     */
    public java.util.Calendar getFechaAuditoria() {
        return fechaAuditoria;
    }


    /**
     * Sets the fechaAuditoria value for this MensajeAuditado.
     * 
     * @param fechaAuditoria
     */
    public void setFechaAuditoria(java.util.Calendar fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }


    /**
     * Gets the medioTransmision value for this MensajeAuditado.
     * 
     * @return medioTransmision
     */
    public org.tempuri.MedioTipo getMedioTransmision() {
        return medioTransmision;
    }


    /**
     * Sets the medioTransmision value for this MensajeAuditado.
     * 
     * @param medioTransmision
     */
    public void setMedioTransmision(org.tempuri.MedioTipo medioTransmision) {
        this.medioTransmision = medioTransmision;
    }


    /**
     * Gets the comentario value for this MensajeAuditado.
     * 
     * @return comentario
     */
    public java.lang.String getComentario() {
        return comentario;
    }


    /**
     * Sets the comentario value for this MensajeAuditado.
     * 
     * @param comentario
     */
    public void setComentario(java.lang.String comentario) {
        this.comentario = comentario;
    }


    /**
     * Gets the comentarioRetorno value for this MensajeAuditado.
     * 
     * @return comentarioRetorno
     */
    public java.lang.String getComentarioRetorno() {
        return comentarioRetorno;
    }


    /**
     * Sets the comentarioRetorno value for this MensajeAuditado.
     * 
     * @param comentarioRetorno
     */
    public void setComentarioRetorno(java.lang.String comentarioRetorno) {
        this.comentarioRetorno = comentarioRetorno;
    }


    /**
     * Gets the estadoValido value for this MensajeAuditado.
     * 
     * @return estadoValido
     */
    public boolean isEstadoValido() {
        return estadoValido;
    }


    /**
     * Sets the estadoValido value for this MensajeAuditado.
     * 
     * @param estadoValido
     */
    public void setEstadoValido(boolean estadoValido) {
        this.estadoValido = estadoValido;
    }


    /**
     * Gets the idUsuario value for this MensajeAuditado.
     * 
     * @return idUsuario
     */
    public java.lang.String getIdUsuario() {
        return idUsuario;
    }


    /**
     * Sets the idUsuario value for this MensajeAuditado.
     * 
     * @param idUsuario
     */
    public void setIdUsuario(java.lang.String idUsuario) {
        this.idUsuario = idUsuario;
    }


    /**
     * Gets the tiempoAuditoria value for this MensajeAuditado.
     * 
     * @return tiempoAuditoria
     */
    public double getTiempoAuditoria() {
        return tiempoAuditoria;
    }


    /**
     * Sets the tiempoAuditoria value for this MensajeAuditado.
     * 
     * @param tiempoAuditoria
     */
    public void setTiempoAuditoria(double tiempoAuditoria) {
        this.tiempoAuditoria = tiempoAuditoria;
    }


    /**
     * Gets the comentarioEnviado value for this MensajeAuditado.
     * 
     * @return comentarioEnviado
     */
    public boolean isComentarioEnviado() {
        return comentarioEnviado;
    }


    /**
     * Sets the comentarioEnviado value for this MensajeAuditado.
     * 
     * @param comentarioEnviado
     */
    public void setComentarioEnviado(boolean comentarioEnviado) {
        this.comentarioEnviado = comentarioEnviado;
    }


    /**
     * Gets the tipoMensaje value for this MensajeAuditado.
     * 
     * @return tipoMensaje
     */
    public java.lang.String getTipoMensaje() {
        return tipoMensaje;
    }


    /**
     * Sets the tipoMensaje value for this MensajeAuditado.
     * 
     * @param tipoMensaje
     */
    public void setTipoMensaje(java.lang.String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MensajeAuditado)) return false;
        MensajeAuditado other = (MensajeAuditado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMensaje == other.getIdMensaje() &&
            this.idMensajeOriginal == other.getIdMensajeOriginal() &&
            ((this.tipoFormulario==null && other.getTipoFormulario()==null) || 
             (this.tipoFormulario!=null &&
              this.tipoFormulario.equals(other.getTipoFormulario()))) &&
            ((this.idCentro==null && other.getIdCentro()==null) || 
             (this.idCentro!=null &&
              this.idCentro.equals(other.getIdCentro()))) &&
            ((this.idMesa==null && other.getIdMesa()==null) || 
             (this.idMesa!=null &&
              this.idMesa.equals(other.getIdMesa()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.idObservador==null && other.getIdObservador()==null) || 
             (this.idObservador!=null &&
              this.idObservador.equals(other.getIdObservador()))) &&
            ((this.idSupervisor==null && other.getIdSupervisor()==null) || 
             (this.idSupervisor!=null &&
              this.idSupervisor.equals(other.getIdSupervisor()))) &&
            this.activo == other.isActivo() &&
            ((this.texto==null && other.getTexto()==null) || 
             (this.texto!=null &&
              this.texto.equals(other.getTexto()))) &&
            ((this.cedula==null && other.getCedula()==null) || 
             (this.cedula!=null &&
              this.cedula.equals(other.getCedula()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.fechaEnvio==null && other.getFechaEnvio()==null) || 
             (this.fechaEnvio!=null &&
              this.fechaEnvio.equals(other.getFechaEnvio()))) &&
            ((this.fechaRecepcionIntegrador==null && other.getFechaRecepcionIntegrador()==null) || 
             (this.fechaRecepcionIntegrador!=null &&
              this.fechaRecepcionIntegrador.equals(other.getFechaRecepcionIntegrador()))) &&
            ((this.fechaRecepcionTotalizador==null && other.getFechaRecepcionTotalizador()==null) || 
             (this.fechaRecepcionTotalizador!=null &&
              this.fechaRecepcionTotalizador.equals(other.getFechaRecepcionTotalizador()))) &&
            ((this.fechaAuditoria==null && other.getFechaAuditoria()==null) || 
             (this.fechaAuditoria!=null &&
              this.fechaAuditoria.equals(other.getFechaAuditoria()))) &&
            ((this.medioTransmision==null && other.getMedioTransmision()==null) || 
             (this.medioTransmision!=null &&
              this.medioTransmision.equals(other.getMedioTransmision()))) &&
            ((this.comentario==null && other.getComentario()==null) || 
             (this.comentario!=null &&
              this.comentario.equals(other.getComentario()))) &&
            ((this.comentarioRetorno==null && other.getComentarioRetorno()==null) || 
             (this.comentarioRetorno!=null &&
              this.comentarioRetorno.equals(other.getComentarioRetorno()))) &&
            this.estadoValido == other.isEstadoValido() &&
            ((this.idUsuario==null && other.getIdUsuario()==null) || 
             (this.idUsuario!=null &&
              this.idUsuario.equals(other.getIdUsuario()))) &&
            this.tiempoAuditoria == other.getTiempoAuditoria() &&
            this.comentarioEnviado == other.isComentarioEnviado() &&
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
        _hashCode += getIdMensaje();
        _hashCode += getIdMensajeOriginal();
        if (getTipoFormulario() != null) {
            _hashCode += getTipoFormulario().hashCode();
        }
        if (getIdCentro() != null) {
            _hashCode += getIdCentro().hashCode();
        }
        if (getIdMesa() != null) {
            _hashCode += getIdMesa().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getIdObservador() != null) {
            _hashCode += getIdObservador().hashCode();
        }
        if (getIdSupervisor() != null) {
            _hashCode += getIdSupervisor().hashCode();
        }
        _hashCode += (isActivo() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTexto() != null) {
            _hashCode += getTexto().hashCode();
        }
        if (getCedula() != null) {
            _hashCode += getCedula().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
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
        if (getFechaAuditoria() != null) {
            _hashCode += getFechaAuditoria().hashCode();
        }
        if (getMedioTransmision() != null) {
            _hashCode += getMedioTransmision().hashCode();
        }
        if (getComentario() != null) {
            _hashCode += getComentario().hashCode();
        }
        if (getComentarioRetorno() != null) {
            _hashCode += getComentarioRetorno().hashCode();
        }
        _hashCode += (isEstadoValido() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getIdUsuario() != null) {
            _hashCode += getIdUsuario().hashCode();
        }
        _hashCode += new Double(getTiempoAuditoria()).hashCode();
        _hashCode += (isComentarioEnviado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTipoMensaje() != null) {
            _hashCode += getTipoMensaje().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MensajeAuditado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MensajeAuditado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMensaje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdMensaje"));
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
        elemField.setFieldName("tipoFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoFormulario"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Numero"));
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
        elemField.setFieldName("idSupervisor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdSupervisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Activo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("cedula");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Cedula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefono");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Telefono"));
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
        elemField.setFieldName("fechaAuditoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaAuditoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioTransmision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioTransmision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MedioTipo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comentario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Comentario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comentarioRetorno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ComentarioRetorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoValido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EstadoValido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("tiempoAuditoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TiempoAuditoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comentarioEnviado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ComentarioEnviado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
