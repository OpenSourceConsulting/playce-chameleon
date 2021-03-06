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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * <p>Java class for serviceClientType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="serviceClientType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="service-ref-name" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="port-info" type="{http://www.tmaxsoft.com/xml/ns/jeus}portInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="service-impl-class" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="wsdl-override" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="require-dynamic-proxy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="service-qname" type="{http://www.w3.org/2001/XMLSchema}QName" minOccurs="0"/>
 *         &lt;element name="call-property" type="{http://www.tmaxsoft.com/xml/ns/jeus}nameValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceClientType", propOrder = {
    "serviceRefName",
    "portInfo",
    "serviceImplClass",
    "wsdlOverride",
    "requireDynamicProxy",
    "serviceQname",
    "callProperty"
})
public class ServiceClientType {

    @XmlElement(name = "service-ref-name", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceRefName;
    @XmlElement(name = "port-info", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", required = true)
    protected List<PortInfoType> portInfo;
    @XmlElement(name = "service-impl-class", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String serviceImplClass;
    @XmlElement(name = "wsdl-override", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected String wsdlOverride;
    @XmlElement(name = "require-dynamic-proxy", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected Boolean requireDynamicProxy;
    @XmlElement(name = "service-qname", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected QName serviceQname;
    @XmlElement(name = "call-property", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", required = true)
    protected List<NameValueType> callProperty;

    /**
     * Gets the value of the serviceRefName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRefName() {
        return serviceRefName;
    }

    /**
     * Sets the value of the serviceRefName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRefName(String value) {
        this.serviceRefName = value;
    }

    /**
     * Gets the value of the portInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortInfoType }
     * 
     * 
     */
    public List<PortInfoType> getPortInfo() {
        if (portInfo == null) {
            portInfo = new ArrayList<PortInfoType>();
        }
        return this.portInfo;
    }

    /**
     * Gets the value of the serviceImplClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceImplClass() {
        return serviceImplClass;
    }

    /**
     * Sets the value of the serviceImplClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceImplClass(String value) {
        this.serviceImplClass = value;
    }

    /**
     * Gets the value of the wsdlOverride property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlOverride() {
        return wsdlOverride;
    }

    /**
     * Sets the value of the wsdlOverride property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlOverride(String value) {
        this.wsdlOverride = value;
    }

    /**
     * Gets the value of the requireDynamicProxy property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequireDynamicProxy() {
        return requireDynamicProxy;
    }

    /**
     * Sets the value of the requireDynamicProxy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequireDynamicProxy(Boolean value) {
        this.requireDynamicProxy = value;
    }

    /**
     * Gets the value of the serviceQname property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getServiceQname() {
        return serviceQname;
    }

    /**
     * Sets the value of the serviceQname property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setServiceQname(QName value) {
        this.serviceQname = value;
    }

    /**
     * Gets the value of the callProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the callProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCallProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameValueType }
     * 
     * 
     */
    public List<NameValueType> getCallProperty() {
        if (callProperty == null) {
            callProperty = new ArrayList<NameValueType>();
        }
        return this.callProperty;
    }

}
