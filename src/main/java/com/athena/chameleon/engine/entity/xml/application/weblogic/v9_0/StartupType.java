//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.05 at 01:53:24 오후 KST 
//


package com.athena.chameleon.engine.entity.xml.application.weblogic.v9_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for startupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="startupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startup-class" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startup-uri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "startupType", propOrder = {
    "startupClass",
    "startupUri"
})
public class StartupType {

    @XmlElement(name = "startup-class", namespace = "http://www.bea.com/ns/weblogic/90", required = true)
    protected java.lang.String startupClass;
    @XmlElement(name = "startup-uri", namespace = "http://www.bea.com/ns/weblogic/90")
    protected java.lang.String startupUri;

    /**
     * Gets the value of the startupClass property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getStartupClass() {
        return startupClass;
    }

    /**
     * Sets the value of the startupClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setStartupClass(java.lang.String value) {
        this.startupClass = value;
    }

    /**
     * Gets the value of the startupUri property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getStartupUri() {
        return startupUri;
    }

    /**
     * Sets the value of the startupUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setStartupUri(java.lang.String value) {
        this.startupUri = value;
    }

}
