/**
 * Pregunta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Pregunta  extends org.tempuri.Elemento  implements java.io.Serializable {
    private int idPregunta;

    private int idFormulario;

    private java.lang.String tipoDato;

    private java.lang.String descPregunta;

    private boolean multiple;

    private org.tempuri.Tag[] listaTags;

    public Pregunta() {
    }

    public Pregunta(
           int idPregunta,
           int idFormulario,
           java.lang.String tipoDato,
           java.lang.String descPregunta,
           boolean multiple,
           org.tempuri.Tag[] listaTags) {
        this.idPregunta = idPregunta;
        this.idFormulario = idFormulario;
        this.tipoDato = tipoDato;
        this.descPregunta = descPregunta;
        this.multiple = multiple;
        this.listaTags = listaTags;
    }


    /**
     * Gets the idPregunta value for this Pregunta.
     * 
     * @return idPregunta
     */
    public int getIdPregunta() {
        return idPregunta;
    }


    /**
     * Sets the idPregunta value for this Pregunta.
     * 
     * @param idPregunta
     */
    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }


    /**
     * Gets the idFormulario value for this Pregunta.
     * 
     * @return idFormulario
     */
    public int getIdFormulario() {
        return idFormulario;
    }


    /**
     * Sets the idFormulario value for this Pregunta.
     * 
     * @param idFormulario
     */
    public void setIdFormulario(int idFormulario) {
        this.idFormulario = idFormulario;
    }


    /**
     * Gets the tipoDato value for this Pregunta.
     * 
     * @return tipoDato
     */
    public java.lang.String getTipoDato() {
        return tipoDato;
    }


    /**
     * Sets the tipoDato value for this Pregunta.
     * 
     * @param tipoDato
     */
    public void setTipoDato(java.lang.String tipoDato) {
        this.tipoDato = tipoDato;
    }


    /**
     * Gets the descPregunta value for this Pregunta.
     * 
     * @return descPregunta
     */
    public java.lang.String getDescPregunta() {
        return descPregunta;
    }


    /**
     * Sets the descPregunta value for this Pregunta.
     * 
     * @param descPregunta
     */
    public void setDescPregunta(java.lang.String descPregunta) {
        this.descPregunta = descPregunta;
    }


    /**
     * Gets the multiple value for this Pregunta.
     * 
     * @return multiple
     */
    public boolean isMultiple() {
        return multiple;
    }


    /**
     * Sets the multiple value for this Pregunta.
     * 
     * @param multiple
     */
    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }


    /**
     * Gets the listaTags value for this Pregunta.
     * 
     * @return listaTags
     */
    public org.tempuri.Tag[] getListaTags() {
        return listaTags;
    }


    /**
     * Sets the listaTags value for this Pregunta.
     * 
     * @param listaTags
     */
    public void setListaTags(org.tempuri.Tag[] listaTags) {
        this.listaTags = listaTags;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Pregunta)) return false;
        Pregunta other = (Pregunta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.idPregunta == other.getIdPregunta() &&
            this.idFormulario == other.getIdFormulario() &&
            ((this.tipoDato==null && other.getTipoDato()==null) || 
             (this.tipoDato!=null &&
              this.tipoDato.equals(other.getTipoDato()))) &&
            ((this.descPregunta==null && other.getDescPregunta()==null) || 
             (this.descPregunta!=null &&
              this.descPregunta.equals(other.getDescPregunta()))) &&
            this.multiple == other.isMultiple() &&
            ((this.listaTags==null && other.getListaTags()==null) || 
             (this.listaTags!=null &&
              java.util.Arrays.equals(this.listaTags, other.getListaTags())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += getIdPregunta();
        _hashCode += getIdFormulario();
        if (getTipoDato() != null) {
            _hashCode += getTipoDato().hashCode();
        }
        if (getDescPregunta() != null) {
            _hashCode += getDescPregunta().hashCode();
        }
        _hashCode += (isMultiple() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getListaTags() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTags());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTags(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Pregunta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Pregunta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPregunta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdPregunta"));
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
        elemField.setFieldName("tipoDato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoDato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descPregunta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "DescPregunta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multiple");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Multiple"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTags");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ListaTags"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Tag"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "Tag"));
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
