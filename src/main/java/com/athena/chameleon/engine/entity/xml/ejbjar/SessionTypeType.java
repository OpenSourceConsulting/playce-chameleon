//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.30 at 10:54:58 오전 KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * 	The session-typeType describes whether the session bean is a
 * 	stateful session or stateless session. It is used by
 * 	session-type elements.
 * 
 * 	The value must be one of the two following:
 * 
 * 	    Stateful
 * 	    Stateless
 * 
 *       
 * 
 * <p>Java class for session-typeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="session-typeType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;http://java.sun.com/xml/ns/j2ee>string">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "session-typeType")
public class SessionTypeType
    extends com.athena.chameleon.engine.entity.xml.j2ee.String
{


}