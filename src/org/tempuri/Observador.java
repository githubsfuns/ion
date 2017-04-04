/**
 * Observador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Observador  extends org.tempuri.Persona  implements java.io.Serializable {
    private int idPostulante;

    private java.lang.String idMesa;

    private java.lang.String idCentro;

    private java.lang.String rol;

    private org.tempuri.Observador[] observadoresCoordinados;

    private org.tempuri.GeoAbstracto unidadGeografica;

    private int totalizador;

    private java.lang.String nombreRol;

    private int experienciaElectoral;

    private int estadoObservador;

    private java.lang.String idCentroRegistro;

    private java.lang.String idCentroPrimarias;

    private java.lang.String nombreCentro;

    public Observador() {
    }

    public Observador(
           java.lang.String id,
           java.lang.String cedula,
           java.lang.String nombre,
           java.lang.String apellido,
           org.tempuri.Direccion[] direcciones,
           org.tempuri.MedioTipo[] canal,
           java.lang.String telefonoCelular,
           java.lang.String telefonoFijo,
           java.lang.String telefonoOficina,
           java.lang.String correoElectronico,
           java.util.Calendar fechaNacimiento,
           int idPostulante,
           java.lang.String idMesa,
           java.lang.String idCentro,
           java.lang.String rol,
           org.tempuri.Observador[] observadoresCoordinados,
           org.tempuri.GeoAbstracto unidadGeografica,
           int totalizador,
           java.lang.String nombreRol,
           int experienciaElectoral,
           int estadoObservador,
           java.lang.String idCentroRegistro,
           java.lang.String idCentroPrimarias,
           java.lang.String nombreCentro) {
        super(
            id,
            cedula,
            nombre,
            apellido,
            direcciones,
            canal,
            telefonoCelular,
            telefonoFijo,
            telefonoOficina,
            correoElectronico,
            fechaNacimiento);
        this.idPostulante = idPostulante;
        this.idMesa = idMesa;
        this.idCentro = idCentro;
        this.rol = rol;
        this.observadoresCoordinados = observadoresCoordinados;
        this.unidadGeografica = unidadGeografica;
        this.totalizador = totalizador;
        this.nombreRol = nombreRol;
        this.experienciaElectoral = experienciaElectoral;
        this.estadoObservador = estadoObservador;
        this.idCentroRegistro = idCentroRegistro;
        this.idCentroPrimarias = idCentroPrimarias;
        this.nombreCentro = nombreCentro;
    }


    /**
     * Gets the idPostulante value for this Observador.
     * 
     * @return idPostulante
     */
    public int getIdPostulante() {
        return idPostulante;
    }


    /**
     * Sets the idPostulante value for this Observador.
     * 
     * @param idPostulante
     */
    public void setIdPostulante(int idPostulante) {
        this.idPostulante = idPostulante;
    }


    /**
     * Gets the idMesa value for this Observador.
     * 
     * @return idMesa
     */
    public java.lang.String getIdMesa() {
        return idMesa;
    }


    /**
     * Sets the idMesa value for this Observador.
     * 
     * @param idMesa
     */
    public void setIdMesa(java.lang.String idMesa) {
        this.idMesa = idMesa;
    }


    /**
     * Gets the idCentro value for this Observador.
     * 
     * @return idCentro
     */
    public java.lang.String getIdCentro() {
        return idCentro;
    }


    /**
     * Sets the idCentro value for this Observador.
     * 
     * @param idCentro
     */
    public void setIdCentro(java.lang.String idCentro) {
        this.idCentro = idCentro;
    }


    /**
     * Gets the rol value for this Observador.
     * 
     * @return rol
     */
    public java.lang.String getRol() {
        return rol;
    }


    /**
     * Sets the rol value for this Observador.
     * 
     * @param rol
     */
    public void setRol(java.lang.String rol) {
        this.rol = rol;
    }


    /**
     * Gets the observadoresCoordinados value for this Observador.
     * 
     * @return observadoresCoordinados
     */
    public org.tempuri.Observador[] getObservadoresCoordinados() {
        return observadoresCoordinados;
    }


    /**
     * Sets the observadoresCoordinados value for this Observador.
     * 
     * @param observadoresCoordinados
     */
    public void setObservadoresCoordinados(org.tempuri.Observador[] observadoresCoordinados) {
        this.observadoresCoordinados = observadoresCoordinados;
    }


    /**
     * Gets the unidadGeografica value for this Observador.
     * 
     * @return unidadGeografica
     */
    public org.tempuri.GeoAbstracto getUnidadGeografica() {
        return unidadGeografica;
    }


    /**
     * Sets the unidadGeografica value for this Observador.
     * 
     * @param unidadGeografica
     */
    public void setUnidadGeografica(org.tempuri.GeoAbstracto unidadGeografica) {
        this.unidadGeografica = unidadGeografica;
    }


    /**
     * Gets the totalizador value for this Observador.
     * 
     * @return totalizador
     */
    public int getTotalizador() {
        return totalizador;
    }


    /**
     * Sets the totalizador value for this Observador.
     * 
     * @param totalizador
     */
    public void setTotalizador(int totalizador) {
        this.totalizador = totalizador;
    }


    /**
     * Gets the nombreRol value for this Observador.
     * 
     * @return nombreRol
     */
    public java.lang.String getNombreRol() {
        return nombreRol;
    }


    /**
     * Sets the nombreRol value for this Observador.
     * 
     * @param nombreRol
     */
    public void setNombreRol(java.lang.String nombreRol) {
        this.nombreRol = nombreRol;
    }


    /**
     * Gets the experienciaElectoral value for this Observador.
     * 
     * @return experienciaElectoral
     */
    public int getExperienciaElectoral() {
        return experienciaElectoral;
    }


    /**
     * Sets the experienciaElectoral value for this Observador.
     * 
     * @param experienciaElectoral
     */
    public void setExperienciaElectoral(int experienciaElectoral) {
        this.experienciaElectoral = experienciaElectoral;
    }


    /**
     * Gets the estadoObservador value for this Observador.
     * 
     * @return estadoObservador
     */
    public int getEstadoObservador() {
        return estadoObservador;
    }


    /**
     * Sets the estadoObservador value for this Observador.
     * 
     * @param estadoObservador
     */
    public void setEstadoObservador(int estadoObservador) {
        this.estadoObservador = estadoObservador;
    }


    /**
     * Gets the idCentroRegistro value for this Observador.
     * 
     * @return idCentroRegistro
     */
    public java.lang.String getIdCentroRegistro() {
        return idCentroRegistro;
    }


    /**
     * Sets the idCentroRegistro value for this Observador.
     * 
     * @param idCentroRegistro
     */
    public void setIdCentroRegistro(java.lang.String idCentroRegistro) {
        this.idCentroRegistro = idCentroRegistro;
    }


    /**
     * Gets the idCentroPrimarias value for this Observador.
     * 
     * @return idCentroPrimarias
     */
    public java.lang.String getIdCentroPrimarias() {
        return idCentroPrimarias;
    }


    /**
     * Sets the idCentroPrimarias value for this Observador.
     * 
     * @param idCentroPrimarias
     */
    public void setIdCentroPrimarias(java.lang.String idCentroPrimarias) {
        this.idCentroPrimarias = idCentroPrimarias;
    }


    /**
     * Gets the nombreCentro value for this Observador.
     * 
     * @return nombreCentro
     */
    public java.lang.String getNombreCentro() {
        return nombreCentro;
    }


    /**
     * Sets the nombreCentro value for this Observador.
     * 
     * @param nombreCentro
     */
    public void setNombreCentro(java.lang.String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Observador)) return false;
        Observador other = (Observador) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.idPostulante == other.getIdPostulante() &&
            ((this.idMesa==null && other.getIdMesa()==null) || 
             (this.idMesa!=null &&
              this.idMesa.equals(other.getIdMesa()))) &&
            ((this.idCentro==null && other.getIdCentro()==null) || 
             (this.idCentro!=null &&
              this.idCentro.equals(other.getIdCentro()))) &&
            ((this.rol==null && other.getRol()==null) || 
             (this.rol!=null &&
              this.rol.equals(other.getRol()))) &&
            ((this.observadoresCoordinados==null && other.getObservadoresCoordinados()==null) || 
             (this.observadoresCoordinados!=null &&
              java.util.Arrays.equals(this.observadoresCoordinados, other.getObservadoresCoordinados()))) &&
            ((this.unidadGeografica==null && other.getUnidadGeografica()==null) || 
             (this.unidadGeografica!=null &&
              this.unidadGeografica.equals(other.getUnidadGeografica()))) &&
            this.totalizador == other.getTotalizador() &&
            ((this.nombreRol==null && other.getNombreRol()==null) || 
             (this.nombreRol!=null &&
              this.nombreRol.equals(other.getNombreRol()))) &&
            this.experienciaElectoral == other.getExperienciaElectoral() &&
            this.estadoObservador == other.getEstadoObservador() &&
            ((this.idCentroRegistro==null && other.getIdCentroRegistro()==null) || 
             (this.idCentroRegistro!=null &&
              this.idCentroRegistro.equals(other.getIdCentroRegistro()))) &&
            ((this.idCentroPrimarias==null && other.getIdCentroPrimarias()==null) || 
             (this.idCentroPrimarias!=null &&
              this.idCentroPrimarias.equals(other.getIdCentroPrimarias()))) &&
            ((this.nombreCentro==null && other.getNombreCentro()==null) || 
             (this.nombreCentro!=null &&
              this.nombreCentro.equals(other.getNombreCentro())));
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
        _hashCode += getIdPostulante();
        if (getIdMesa() != null) {
            _hashCode += getIdMesa().hashCode();
        }
        if (getIdCentro() != null) {
            _hashCode += getIdCentro().hashCode();
        }
        if (getRol() != null) {
            _hashCode += getRol().hashCode();
        }
        if (getObservadoresCoordinados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObservadoresCoordinados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObservadoresCoordinados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUnidadGeografica() != null) {
            _hashCode += getUnidadGeografica().hashCode();
        }
        _hashCode += getTotalizador();
        if (getNombreRol() != null) {
            _hashCode += getNombreRol().hashCode();
        }
        _hashCode += getExperienciaElectoral();
        _hashCode += getEstadoObservador();
        if (getIdCentroRegistro() != null) {
            _hashCode += getIdCentroRegistro().hashCode();
        }
        if (getIdCentroPrimarias() != null) {
            _hashCode += getIdCentroPrimarias().hashCode();
        }
        if (getNombreCentro() != null) {
            _hashCode += getNombreCentro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Observador.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Observador"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPostulante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdPostulante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("rol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Rol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observadoresCoordinados");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ObservadoresCoordinados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Observador"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "Observador"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadGeografica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "UnidadGeografica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "GeoAbstracto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalizador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Totalizador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreRol");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NombreRol"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("experienciaElectoral");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ExperienciaElectoral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoObservador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "EstadoObservador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCentroRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdCentroRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCentroPrimarias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "IdCentroPrimarias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCentro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "NombreCentro"));
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
