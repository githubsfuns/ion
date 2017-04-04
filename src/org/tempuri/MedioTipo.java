/**
 * MedioTipo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class MedioTipo implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected MedioTipo(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _SMS = "SMS";
    public static final java.lang.String _PIN = "PIN";
    public static final java.lang.String _CEL = "CEL";
    public static final java.lang.String _TWT = "TWT";
    public static final java.lang.String _FCB = "FCB";
    public static final java.lang.String _WEB = "WEB";
    public static final java.lang.String _MedioIndefinido = "MedioIndefinido";
    public static final java.lang.String _CC = "CC";
    public static final MedioTipo SMS = new MedioTipo(_SMS);
    public static final MedioTipo PIN = new MedioTipo(_PIN);
    public static final MedioTipo CEL = new MedioTipo(_CEL);
    public static final MedioTipo TWT = new MedioTipo(_TWT);
    public static final MedioTipo FCB = new MedioTipo(_FCB);
    public static final MedioTipo WEB = new MedioTipo(_WEB);
    public static final MedioTipo MedioIndefinido = new MedioTipo(_MedioIndefinido);
    public static final MedioTipo CC = new MedioTipo(_CC);
    public java.lang.String getValue() { return _value_;}
    public static MedioTipo fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        MedioTipo enumeration = (MedioTipo)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static MedioTipo fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MedioTipo.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "MedioTipo"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
