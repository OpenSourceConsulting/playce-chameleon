//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.15 at 07:46:01 오후 KST 
//


package com.athena.chameleon.engine.entity.xml.project.v4_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Describes the prerequisites a project can have.
 * 
 * <p>Java class for Prerequisites complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Prerequisites">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="maven" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prerequisites", propOrder = {

})
public class Prerequisites {

    @XmlElement(namespace = "http://maven.apache.org/POM/4.0.0", defaultValue = "2.0")
    protected String maven;

    /**
     * Gets the value of the maven property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaven() {
        return maven;
    }

    /**
     * Sets the value of the maven property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaven(String value) {
        this.maven = value;
    }

}
