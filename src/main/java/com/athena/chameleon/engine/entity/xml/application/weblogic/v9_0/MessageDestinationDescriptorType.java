//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.10.05 at 01:53:24 오후 KST 
//


package com.athena.chameleon.engine.entity.xml.application.weblogic.v9_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for message-destination-descriptorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="message-destination-descriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="message-destination-name" type="{http://www.bea.com/ns/weblogic/90}message-destination-nameType"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="destination-jndi-name" type="{http://www.bea.com/ns/weblogic/90}destination-jndi-nameType"/>
 *             &lt;element name="initial-context-factory" type="{http://www.bea.com/ns/weblogic/90}initial-context-factoryType" minOccurs="0"/>
 *             &lt;element name="provider-url" type="{http://www.bea.com/ns/weblogic/90}provider-urlType" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="destination-resource-link" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "message-destination-descriptorType", propOrder = {
    "messageDestinationName",
    "destinationJndiName",
    "initialContextFactory",
    "providerUrl",
    "destinationResourceLink"
})
public class MessageDestinationDescriptorType {

    @XmlElement(name = "message-destination-name", namespace = "http://www.bea.com/ns/weblogic/90", required = true)
    protected MessageDestinationNameType messageDestinationName;
    @XmlElement(name = "destination-jndi-name", namespace = "http://www.bea.com/ns/weblogic/90")
    protected DestinationJndiNameType destinationJndiName;
    @XmlElement(name = "initial-context-factory", namespace = "http://www.bea.com/ns/weblogic/90")
    protected InitialContextFactoryType initialContextFactory;
    @XmlElement(name = "provider-url", namespace = "http://www.bea.com/ns/weblogic/90")
    protected ProviderUrlType providerUrl;
    @XmlElement(name = "destination-resource-link", namespace = "http://www.bea.com/ns/weblogic/90")
    protected com.athena.chameleon.engine.entity.xml.application.weblogic.v9_0.String destinationResourceLink;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected java.lang.String id;

    /**
     * Gets the value of the messageDestinationName property.
     * 
     * @return
     *     possible object is
     *     {@link MessageDestinationNameType }
     *     
     */
    public MessageDestinationNameType getMessageDestinationName() {
        return messageDestinationName;
    }

    /**
     * Sets the value of the messageDestinationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageDestinationNameType }
     *     
     */
    public void setMessageDestinationName(MessageDestinationNameType value) {
        this.messageDestinationName = value;
    }

    /**
     * Gets the value of the destinationJndiName property.
     * 
     * @return
     *     possible object is
     *     {@link DestinationJndiNameType }
     *     
     */
    public DestinationJndiNameType getDestinationJndiName() {
        return destinationJndiName;
    }

    /**
     * Sets the value of the destinationJndiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationJndiNameType }
     *     
     */
    public void setDestinationJndiName(DestinationJndiNameType value) {
        this.destinationJndiName = value;
    }

    /**
     * Gets the value of the initialContextFactory property.
     * 
     * @return
     *     possible object is
     *     {@link InitialContextFactoryType }
     *     
     */
    public InitialContextFactoryType getInitialContextFactory() {
        return initialContextFactory;
    }

    /**
     * Sets the value of the initialContextFactory property.
     * 
     * @param value
     *     allowed object is
     *     {@link InitialContextFactoryType }
     *     
     */
    public void setInitialContextFactory(InitialContextFactoryType value) {
        this.initialContextFactory = value;
    }

    /**
     * Gets the value of the providerUrl property.
     * 
     * @return
     *     possible object is
     *     {@link ProviderUrlType }
     *     
     */
    public ProviderUrlType getProviderUrl() {
        return providerUrl;
    }

    /**
     * Sets the value of the providerUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderUrlType }
     *     
     */
    public void setProviderUrl(ProviderUrlType value) {
        this.providerUrl = value;
    }

    /**
     * Gets the value of the destinationResourceLink property.
     * 
     * @return
     *     possible object is
     *     {@link com.athena.chameleon.engine.entity.xml.application.weblogic.v9_0.String }
     *     
     */
    public com.athena.chameleon.engine.entity.xml.application.weblogic.v9_0.String getDestinationResourceLink() {
        return destinationResourceLink;
    }

    /**
     * Sets the value of the destinationResourceLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.athena.chameleon.engine.entity.xml.application.weblogic.v9_0.String }
     *     
     */
    public void setDestinationResourceLink(com.athena.chameleon.engine.entity.xml.application.weblogic.v9_0.String value) {
        this.destinationResourceLink = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

}
