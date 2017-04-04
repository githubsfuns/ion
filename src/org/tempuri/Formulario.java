/**
 * Formulario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Formulario  implements java.io.Serializable {
    private int idFormulario;

    private java.lang.String tipoFormulario;

    private java.lang.String nombreFormulario;

    private java.lang.String cedula;

    private java.lang.String telefono;

    private org.tempuri.MensajeAuditado mensajeAuditado;

    private org.tempuri.MensajeOriginal mensajeOriginal;

    private org.tempuri.Pregunta[] preguntas;

    private org.tempuri.Mesa mesa;

    public Formulario() {
    }

    public Formulario(
           int idFormulario,
           java.lang.String tipoFormulario,
           java.lang.String nombreFormulario,
           java.lang.String cedula,
           java.lang.String telefono,
           org.tempuri.MensajeAuditado mensajeAuditado,
           org.tempuri.MensajeOriginal mensajeOriginal,
           org.tempuri.Pregunta[] preguntas,
           org.tempuri.Mesa mesa) {
           this.idFormulario = idFormulario;
           this.tipoFormulario = tipoFormulario;
           this.nombreFormulario = nombreFormulario;
           this.cedula = cedula;
           this.telefono = telefono;
           this.mensajeAuditado = mensajeAuditado;
           this.mensajeOriginal = mensajeOriginal;
           this.preguntas = preguntas;
           this.mesa = mesa;
    }


    /**
     * Gets the idFormulario value for this Formulario.
     * 
     * @return idFormulario
     */
    public int getIdFormulario() {
        return idFormulario;
    }


    /**
     * Sets the idFormulario value for this Formulario.
     * 
     * @param idFormulario
     */
    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }


    /**
     * Gets the tipoFormulario value for this Formulario.
     * 
     * @return tipoFormulario
     */
    public java.lang.String getTipoFormulario() {
        return tipoFormulario;
    }


    /**
     * Sets the tipoFormulario value for this Formulario.
     * 
     * @param tipoFormulario
     */
    public void setTipoFormulario(java.lang.String tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }


    /**
     * Gets the nombreFormulario value for this Formulario.
     * 
     * @return nombreFormulario
     */
    public java.lang.String getNombreFormulario() {
        return nombreFormulario;
    }


    /**
     * Sets the nombreFormulario value for this Formulario.
     * 
     * @param nombreFormulario
     */
    public void setNombreFormulario(java.lang.String nombreFormulario) {
        this.nombreFormulario = nombreFormulario;
    }


    /**
     * Gets the cedula value for this Formulario.
     * 
     * @return cedula
     */
    public java.lang.String getCedula() {
        return cedula;
    }


    /**
     * Sets the cedula value for this Formulario.
     * 
     * @param cedula
     */
    public void setCedula(java.lang.String cedula) {
        this.cedula = cedula;
    }


    /**
     * Gets the telefono value for this Formulario.
     * 
     * @return telefono
     */
    public java.lang.String getTelefono() {
        return telefono;
    }


    /**
     * Sets the telefono value for this Formulario.
     * 
     * @param telefono
     */
    public void setTelefono(java.lang.String telefono) {
        this.telefono = telefono;
    }


    /**
     * Gets the mensajeAuditado value for this Formulario.
     * 
     * @return mensajeAuditado
     */
    public org.tempuri.MensajeAuditado getMensajeAuditado() {
        return mensajeAuditado;
    }


    /**
     * Sets the mensajeAuditado value for this Formulario.
     * 
     * @param mensajeAuditado
     */
    public void setMensajeAuditado(org.tempuri.MensajeAuditado mensajeAuditado) {
        this.mensajeAuditado = mensajeAuditado;
    }


    /**
     * Gets the mensajeOriginal value for this Formulario.
     * 
     * @return mensajeOriginal
     */
    public org.tempuri.MensajeOriginal getMensajeOriginal() {
        return mensajeOriginal;
    }


    /**
     * Sets the mensajeOriginal value for this Formulario.
     * 
     * @param mensajeOriginal
     */
    public void setMensajeOriginal(org.tempuri.MensajeOriginal mensajeOriginal) {
        this.mensajeOriginal = mensajeOriginal;
    }


    /**
     * Gets the preguntas value for this Formulario.
     * 
     * @return preguntas
     */
    public org.tempuri.Pregunta[] getPreguntas() {
        return preguntas;
    }


    /**
     * Sets the preguntas value for this Formulario.
     * 
     * @param preguntas
     */
    public void setPreguntas(org.tempuri.Pregunta[] preguntas) {
        this.preguntas = preguntas;
    }


    /**
     * Gets the mesa value for this Formulario.
     * 
     * @return mesa
     */
    public org.tempuri.Mesa getMesa() {
        return mesa;
    }


    /**
     * Sets the mesa value for this Formulario.
     * 
     * @param mesa
     */
    public void setMesa(org.tempuri.Mesa mesa) {
        this.mesa = mesa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Formulario)) return false;
        Formulario other = (Formulario) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idFormulario == other.getIdFormulario() &&
            ((this.tipoFormulario==null && other.getTipoFormulario()==null) || 
             (this.tipoFormulario!=null &&
              this.tipoFormulario.equals(other.getTipoFormulario()))) &&
            ((this.nombreFormulario==null && other.getNombreFormulario()==null) || 
             (this.nombreFormulario!=null &&
              this.nombreFormulario.equals(other.getNombreFormulario()))) &&
            ((this.cedula==null && other.getCedula()==null) || 
             (this.cedula!=null &&
              this.cedula.equals(other.getCedula()))) &&
            ((this.telefono==null && other.getTelefono()==null) || 
             (this.telefono!=null &&
              this.telefono.equals(other.getTelefono()))) &&
            ((this.mensajeAuditado==null && other.getMensajeAuditado()==null) || 
             (this.mensajeAuditado!=null &&
              this.mensajeAuditado.equals(other.getMensajeAuditado()))) &&
            ((this.mensajeOriginal==null && other.getMensajeOriginal()==null) || 
             (this.mensajeOriginal!=null &&
              this.mensajeOriginal.equals(other.getMensajeOriginal()))) &&
            ((this.preguntas==null && other.getPreguntas()==null) || 
             (this.preguntas!=null &&
              java.util.Arrays.equals(this.preguntas, other.getPreguntas()))) &&
            ((this.mesa==null && other.getMesa()==null) || 
             (this.mesa!=null &&
              this.mesa.equals(other.getMesa())));
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
        _hashCode += getIdFormulario();
        if (getTipoFormulario() != null) {
            _hashCode += getTipoFormulario().hashCode();
        }
        if (getNombreFormulario() != null) {
            _hashCode += getNombreFormulario().hashCode();
        }
        if (getCedula() != null) {
            _hashCode += getCedula().hashCode();
        }
        if (getTelefono() != null) {
            _hashCode += getTelefono().hashCode();
        }
        if (getMensajeAuditado() != null) {
            _hashCode += getMensajeAuditado().hashCode();
        }
        if (getMensajeOriginal() != null) {
            _hashCode += getMensajeOriginal().hashCode();
        }
        if (getPreguntas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPreguntas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPreguntas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMesa() != null) {
            _hashCode += getMesa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Formulario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Formulario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("nombreFormulario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NombreFormulario"));
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
        elemField.setFieldName("mensajeAuditado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MensajeAuditado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MensajeAuditado"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeOriginal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "MensajeOriginal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MensajeOriginal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preguntas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Preguntas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Pregunta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "Pregunta"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mesa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Mesa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Mesa"));
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
