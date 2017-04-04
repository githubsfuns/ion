/**
 * Mesa.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Mesa  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String idCentro;

    private java.lang.String nombre;

    private int electores;

    private java.math.BigDecimal abstencionHistorica;

    private int escrutados;

    private int votoNulo;

    private java.math.BigDecimal abstencionEvento;

    private org.tempuri.Observador[] observadores;

    private int cantMensajes;

    private int muestra;

    public Mesa() {
    }

    public Mesa(
           java.lang.String id,
           java.lang.String idCentro,
           java.lang.String nombre,
           int electores,
           java.math.BigDecimal abstencionHistorica,
           int escrutados,
           int votoNulo,
           java.math.BigDecimal abstencionEvento,
           org.tempuri.Observador[] observadores,
           int cantMensajes,
           int muestra) {
           this.id = id;
           this.idCentro = idCentro;
           this.nombre = nombre;
           this.electores = electores;
           this.abstencionHistorica = abstencionHistorica;
           this.escrutados = escrutados;
           this.votoNulo = votoNulo;
           this.abstencionEvento = abstencionEvento;
           this.observadores = observadores;
           this.cantMensajes = cantMensajes;
           this.muestra = muestra;
    }


    /**
     * Gets the id value for this Mesa.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Mesa.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the idCentro value for this Mesa.
     * 
     * @return idCentro
     */
    public java.lang.String getIdCentro() {
        return idCentro;
    }


    /**
     * Sets the idCentro value for this Mesa.
     * 
     * @param idCentro
     */
    public void setIdCentro(java.lang.String idCentro) {
        this.idCentro = idCentro;
    }


    /**
     * Gets the nombre value for this Mesa.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this Mesa.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the electores value for this Mesa.
     * 
     * @return electores
     */
    public int getElectores() {
        return electores;
    }


    /**
     * Sets the electores value for this Mesa.
     * 
     * @param electores
     */
    public void setElectores(int electores) {
        this.electores = electores;
    }


    /**
     * Gets the abstencionHistorica value for this Mesa.
     * 
     * @return abstencionHistorica
     */
    public java.math.BigDecimal getAbstencionHistorica() {
        return abstencionHistorica;
    }


    /**
     * Sets the abstencionHistorica value for this Mesa.
     * 
     * @param abstencionHistorica
     */
    public void setAbstencionHistorica(java.math.BigDecimal abstencionHistorica) {
        this.abstencionHistorica = abstencionHistorica;
    }


    /**
     * Gets the escrutados value for this Mesa.
     * 
     * @return escrutados
     */
    public int getEscrutados() {
        return escrutados;
    }


    /**
     * Sets the escrutados value for this Mesa.
     * 
     * @param escrutados
     */
    public void setEscrutados(int escrutados) {
        this.escrutados = escrutados;
    }


    /**
     * Gets the votoNulo value for this Mesa.
     * 
     * @return votoNulo
     */
    public int getVotoNulo() {
        return votoNulo;
    }


    /**
     * Sets the votoNulo value for this Mesa.
     * 
     * @param votoNulo
     */
    public void setVotoNulo(int votoNulo) {
        this.votoNulo = votoNulo;
    }


    /**
     * Gets the abstencionEvento value for this Mesa.
     * 
     * @return abstencionEvento
     */
    public java.math.BigDecimal getAbstencionEvento() {
        return abstencionEvento;
    }


    /**
     * Sets the abstencionEvento value for this Mesa.
     * 
     * @param abstencionEvento
     */
    public void setAbstencionEvento(java.math.BigDecimal abstencionEvento) {
        this.abstencionEvento = abstencionEvento;
    }


    /**
     * Gets the observadores value for this Mesa.
     * 
     * @return observadores
     */
    public org.tempuri.Observador[] getObservadores() {
        return observadores;
    }


    /**
     * Sets the observadores value for this Mesa.
     * 
     * @param observadores
     */
    public void setObservadores(org.tempuri.Observador[] observadores) {
        this.observadores = observadores;
    }


    /**
     * Gets the cantMensajes value for this Mesa.
     * 
     * @return cantMensajes
     */
    public int getCantMensajes() {
        return cantMensajes;
    }


    /**
     * Sets the cantMensajes value for this Mesa.
     * 
     * @param cantMensajes
     */
    public void setCantMensajes(int cantMensajes) {
        this.cantMensajes = cantMensajes;
    }


    /**
     * Gets the muestra value for this Mesa.
     * 
     * @return muestra
     */
    public int getMuestra() {
        return muestra;
    }


    /**
     * Sets the muestra value for this Mesa.
     * 
     * @param muestra
     */
    public void setMuestra(int muestra) {
        this.muestra = muestra;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Mesa)) return false;
        Mesa other = (Mesa) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.idCentro==null && other.getIdCentro()==null) || 
             (this.idCentro!=null &&
              this.idCentro.equals(other.getIdCentro()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            this.electores == other.getElectores() &&
            ((this.abstencionHistorica==null && other.getAbstencionHistorica()==null) || 
             (this.abstencionHistorica!=null &&
              this.abstencionHistorica.equals(other.getAbstencionHistorica()))) &&
            this.escrutados == other.getEscrutados() &&
            this.votoNulo == other.getVotoNulo() &&
            ((this.abstencionEvento==null && other.getAbstencionEvento()==null) || 
             (this.abstencionEvento!=null &&
              this.abstencionEvento.equals(other.getAbstencionEvento()))) &&
            ((this.observadores==null && other.getObservadores()==null) || 
             (this.observadores!=null &&
              java.util.Arrays.equals(this.observadores, other.getObservadores()))) &&
            this.cantMensajes == other.getCantMensajes() &&
            this.muestra == other.getMuestra();
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdCentro() != null) {
            _hashCode += getIdCentro().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        _hashCode += getElectores();
        if (getAbstencionHistorica() != null) {
            _hashCode += getAbstencionHistorica().hashCode();
        }
        _hashCode += getEscrutados();
        _hashCode += getVotoNulo();
        if (getAbstencionEvento() != null) {
            _hashCode += getAbstencionEvento().hashCode();
        }
        if (getObservadores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObservadores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObservadores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getCantMensajes();
        _hashCode += getMuestra();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Mesa.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Mesa"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Id"));
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
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("electores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Electores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abstencionHistorica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AbstencionHistorica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("escrutados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Escrutados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("votoNulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "VotoNulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abstencionEvento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AbstencionEvento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observadores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Observadores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Observador"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "Observador"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantMensajes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CantMensajes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("muestra");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Muestra"));
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
