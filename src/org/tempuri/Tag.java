/**
 * Tag.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Tag  implements java.io.Serializable {
    private org.tempuri.Respuesta respuesta;

    private int posTag;

    private java.lang.String tagDesc;

    private boolean obligatorio;

    private java.lang.String descripcion;

    public Tag() {
    }

    public Tag(
           org.tempuri.Respuesta respuesta,
           int posTag,
           java.lang.String tagDesc,
           boolean obligatorio,
           java.lang.String descripcion) {
           this.respuesta = respuesta;
           this.posTag = posTag;
           this.tagDesc = tagDesc;
           this.obligatorio = obligatorio;
           this.descripcion = descripcion;
    }


    /**
     * Gets the respuesta value for this Tag.
     * 
     * @return respuesta
     */
    public org.tempuri.Respuesta getRespuesta() {
        return respuesta;
    }


    /**
     * Sets the respuesta value for this Tag.
     * 
     * @param respuesta
     */
    public void setRespuesta(org.tempuri.Respuesta respuesta) {
        this.respuesta = respuesta;
    }


    /**
     * Gets the posTag value for this Tag.
     * 
     * @return posTag
     */
    public int getPosTag() {
        return posTag;
    }


    /**
     * Sets the posTag value for this Tag.
     * 
     * @param posTag
     */
    public void setPosTag(int posTag) {
        this.posTag = posTag;
    }


    /**
     * Gets the tagDesc value for this Tag.
     * 
     * @return tagDesc
     */
    public java.lang.String getTagDesc() {
        return tagDesc;
    }


    /**
     * Sets the tagDesc value for this Tag.
     * 
     * @param tagDesc
     */
    public void setTagDesc(java.lang.String tagDesc) {
        this.tagDesc = tagDesc;
    }


    /**
     * Gets the obligatorio value for this Tag.
     * 
     * @return obligatorio
     */
    public boolean isObligatorio() {
        return obligatorio;
    }


    /**
     * Sets the obligatorio value for this Tag.
     * 
     * @param obligatorio
     */
    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }


    /**
     * Gets the descripcion value for this Tag.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this Tag.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tag)) return false;
        Tag other = (Tag) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.respuesta==null && other.getRespuesta()==null) || 
             (this.respuesta!=null &&
              this.respuesta.equals(other.getRespuesta()))) &&
            this.posTag == other.getPosTag() &&
            ((this.tagDesc==null && other.getTagDesc()==null) || 
             (this.tagDesc!=null &&
              this.tagDesc.equals(other.getTagDesc()))) &&
            this.obligatorio == other.isObligatorio() &&
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion())));
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
        if (getRespuesta() != null) {
            _hashCode += getRespuesta().hashCode();
        }
        _hashCode += getPosTag();
        if (getTagDesc() != null) {
            _hashCode += getTagDesc().hashCode();
        }
        _hashCode += (isObligatorio() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tag.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Tag"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "respuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Respuesta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("posTag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "PosTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tagDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TagDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obligatorio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Obligatorio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Descripcion"));
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
