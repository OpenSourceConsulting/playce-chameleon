//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.17 at 07:02:27 오후 KST 
//


package com.athena.chameleon.engine.entity.xml.application.jeus.v6_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for credential-mappingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="credential-mappingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="default-credential-mapping-service" type="{http://www.tmaxsoft.com/xml/ns/jeus}default-credential-mapping-serviceType" minOccurs="0"/>
 *         &lt;element name="custom-credential-mapping-service" type="{http://www.tmaxsoft.com/xml/ns/jeus}SecurityServiceType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "credential-mappingType", propOrder = {
    "defaultCredentialMappingService",
    "customCredentialMappingService"
})
public class CredentialMappingType {

    @XmlElement(name = "default-credential-mapping-service", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected DefaultCredentialMappingServiceType defaultCredentialMappingService;
    @XmlElement(name = "custom-credential-mapping-service", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", required = true)
    protected List<SecurityServiceType> customCredentialMappingService;

    /**
     * Gets the value of the defaultCredentialMappingService property.
     * 
     * @return
     *     possible object is
     *     {@link DefaultCredentialMappingServiceType }
     *     
     */
    public DefaultCredentialMappingServiceType getDefaultCredentialMappingService() {
        return defaultCredentialMappingService;
    }

    /**
     * Sets the value of the defaultCredentialMappingService property.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultCredentialMappingServiceType }
     *     
     */
    public void setDefaultCredentialMappingService(DefaultCredentialMappingServiceType value) {
        this.defaultCredentialMappingService = value;
    }

    /**
     * Gets the value of the customCredentialMappingService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customCredentialMappingService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomCredentialMappingService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityServiceType }
     * 
     * 
     */
    public List<SecurityServiceType> getCustomCredentialMappingService() {
        if (customCredentialMappingService == null) {
            customCredentialMappingService = new ArrayList<SecurityServiceType>();
        }
        return this.customCredentialMappingService;
    }

}
