/**
 * Persona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class Persona  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String cedula;

    private java.lang.String nombre;

    private java.lang.String apellido;

    private org.tempuri.Direccion[] direcciones;

    private org.tempuri.MedioTipo[] canal;

    private java.lang.String telefonoCelular;

    private java.lang.String telefonoFijo;

    private java.lang.String telefonoOficina;

    private java.lang.String correoElectronico;

    private java.util.Calendar fechaNacimiento;

    public Persona() {
    }

    public Persona(
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
           java.util.Calendar fechaNacimiento) {
           this.id = id;
           this.cedula = cedula;
           this.nombre = nombre;
           this.apellido = apellido;
           this.direcciones = direcciones;
           this.canal = canal;
           this.telefonoCelular = telefonoCelular;
           this.telefonoFijo = telefonoFijo;
           this.telefonoOficina = telefonoOficina;
           this.correoElectronico = correoElectronico;
           this.fechaNacimiento = fechaNacimiento;
    }


    /**
     * Gets the id value for this Persona.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Persona.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the cedula value for this Persona.
     * 
     * @return cedula
     */
    public java.lang.String getCedula() {
        return cedula;
    }


    /**
     * Sets the cedula value for this Persona.
     * 
     * @param cedula
     */
    public void setCedula(java.lang.String cedula) {
        this.cedula = cedula;
    }


    /**
     * Gets the nombre value for this Persona.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this Persona.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the apellido value for this Persona.
     * 
     * @return apellido
     */
    public java.lang.String getApellido() {
        return apellido;
    }


    /**
     * Sets the apellido value for this Persona.
     * 
     * @param apellido
     */
    public void setApellido(java.lang.String apellido) {
        this.apellido = apellido;
    }


    /**
     * Gets the direcciones value for this Persona.
     * 
     * @return direcciones
     */
    public org.tempuri.Direccion[] getDirecciones() {
        return direcciones;
    }


    /**
     * Sets the direcciones value for this Persona.
     * 
     * @param direcciones
     */
    public void setDirecciones(org.tempuri.Direccion[] direcciones) {
        this.direcciones = direcciones;
    }


    /**
     * Gets the canal value for this Persona.
     * 
     * @return canal
     */
    public org.tempuri.MedioTipo[] getCanal() {
        return canal;
    }


    /**
     * Sets the canal value for this Persona.
     * 
     * @param canal
     */
    public void setCanal(org.tempuri.MedioTipo[] canal) {
        this.canal = canal;
    }


    /**
     * Gets the telefonoCelular value for this Persona.
     * 
     * @return telefonoCelular
     */
    public java.lang.String getTelefonoCelular() {
        return telefonoCelular;
    }


    /**
     * Sets the telefonoCelular value for this Persona.
     * 
     * @param telefonoCelular
     */
    public void setTelefonoCelular(java.lang.String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }


    /**
     * Gets the telefonoFijo value for this Persona.
     * 
     * @return telefonoFijo
     */
    public java.lang.String getTelefonoFijo() {
        return telefonoFijo;
    }


    /**
     * Sets the telefonoFijo value for this Persona.
     * 
     * @param telefonoFijo
     */
    public void setTelefonoFijo(java.lang.String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }


    /**
     * Gets the telefonoOficina value for this Persona.
     * 
     * @return telefonoOficina
     */
    public java.lang.String getTelefonoOficina() {
        return telefonoOficina;
    }


    /**
     * Sets the telefonoOficina value for this Persona.
     * 
     * @param telefonoOficina
     */
    public void setTelefonoOficina(java.lang.String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }


    /**
     * Gets the correoElectronico value for this Persona.
     * 
     * @return correoElectronico
     */
    public java.lang.String getCorreoElectronico() {
        return correoElectronico;
    }


    /**
     * Sets the correoElectronico value for this Persona.
     * 
     * @param correoElectronico
     */
    public void setCorreoElectronico(java.lang.String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }


    /**
     * Gets the fechaNacimiento value for this Persona.
     * 
     * @return fechaNacimiento
     */
    public java.util.Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }


    /**
     * Sets the fechaNacimiento value for this Persona.
     * 
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(java.util.Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Persona)) return false;
        Persona other = (Persona) obj;
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
            ((this.cedula==null && other.getCedula()==null) || 
             (this.cedula!=null &&
              this.cedula.equals(other.getCedula()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.apellido==null && other.getApellido()==null) || 
             (this.apellido!=null &&
              this.apellido.equals(other.getApellido()))) &&
            ((this.direcciones==null && other.getDirecciones()==null) || 
             (this.direcciones!=null &&
              java.util.Arrays.equals(this.direcciones, other.getDirecciones()))) &&
            ((this.canal==null && other.getCanal()==null) || 
             (this.canal!=null &&
              java.util.Arrays.equals(this.canal, other.getCanal()))) &&
            ((this.telefonoCelular==null && other.getTelefonoCelular()==null) || 
             (this.telefonoCelular!=null &&
              this.telefonoCelular.equals(other.getTelefonoCelular()))) &&
            ((this.telefonoFijo==null && other.getTelefonoFijo()==null) || 
             (this.telefonoFijo!=null &&
              this.telefonoFijo.equals(other.getTelefonoFijo()))) &&
            ((this.telefonoOficina==null && other.getTelefonoOficina()==null) || 
             (this.telefonoOficina!=null &&
              this.telefonoOficina.equals(other.getTelefonoOficina()))) &&
            ((this.correoElectronico==null && other.getCorreoElectronico()==null) || 
             (this.correoElectronico!=null &&
              this.correoElectronico.equals(other.getCorreoElectronico()))) &&
            ((this.fechaNacimiento==null && other.getFechaNacimiento()==null) || 
             (this.fechaNacimiento!=null &&
              this.fechaNacimiento.equals(other.getFechaNacimiento())));
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
        if (getCedula() != null) {
            _hashCode += getCedula().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getApellido() != null) {
            _hashCode += getApellido().hashCode();
        }
        if (getDirecciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDirecciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDirecciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCanal() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCanal());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCanal(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTelefonoCelular() != null) {
            _hashCode += getTelefonoCelular().hashCode();
        }
        if (getTelefonoFijo() != null) {
            _hashCode += getTelefonoFijo().hashCode();
        }
        if (getTelefonoOficina() != null) {
            _hashCode += getTelefonoOficina().hashCode();
        }
        if (getCorreoElectronico() != null) {
            _hashCode += getCorreoElectronico().hashCode();
        }
        if (getFechaNacimiento() != null) {
            _hashCode += getFechaNacimiento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Persona.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Persona"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Id"));
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
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Apellido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direcciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Direcciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "Direccion"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "Direccion"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Canal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MedioTipo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://tempuri.org/", "MedioTipo"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoCelular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TelefonoCelular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoFijo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TelefonoFijo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoOficina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TelefonoOficina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correoElectronico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "CorreoElectronico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "FechaNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
