//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.17 at 07:02:27 오후 KST 
//


package com.athena.chameleon.engine.entity.xml.application.jeus.v6_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for app-targetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="app-targetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="node-name" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *           &lt;element name="engine-container-name" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;/choice>
 *         &lt;element name="web-context-group" type="{http://www.tmaxsoft.com/xml/ns/jeus}web-context-groupType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "app-targetType", propOrder = {
    "nodeName",
    "engineContainerName",
    "webContextGroup"
})
public class AppTargetType {

    @XmlElement(name = "node-name", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String nodeName;
    @XmlElement(name = "engine-container-name", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String engineContainerName;
    @XmlElement(name = "web-context-group", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected WebContextGroupType webContextGroup;

    /**
     * Gets the value of the nodeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * Sets the value of the nodeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeName(String value) {
        this.nodeName = value;
    }

    /**
     * Gets the value of the engineContainerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEngineContainerName() {
        return engineContainerName;
    }

    /**
     * Sets the value of the engineContainerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEngineContainerName(String value) {
        this.engineContainerName = value;
    }

    /**
     * Gets the value of the webContextGroup property.
     * 
     * @return
     *     possible object is
     *     {@link WebContextGroupType }
     *     
     */
    public WebContextGroupType getWebContextGroup() {
        return webContextGroup;
    }

    /**
     * Sets the value of the webContextGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebContextGroupType }
     *     
     */
    public void setWebContextGroup(WebContextGroupType value) {
        this.webContextGroup = value;
    }

}
