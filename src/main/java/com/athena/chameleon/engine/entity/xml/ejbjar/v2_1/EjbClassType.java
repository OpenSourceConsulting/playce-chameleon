//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.30 at 10:54:58 오전 KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.v2_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 	
 * 
 * 	  The ejb-classType contains the fully-qualified name of the
 * 	  enterprise bean's class. It is used by ejb-class elements.
 * 
 * 	  Example:
 * 
 * 	      <ejb-class>com.wombat.empl.EmployeeServiceBean</ejb-class>
 * 
 * 	  
 *       
 * 
 * <p>Java class for ejb-classType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ejb-classType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;http://java.sun.com/xml/ns/j2ee>fully-qualified-classType">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ejb-classType")
public class EjbClassType
    extends FullyQualifiedClassType
{


}
