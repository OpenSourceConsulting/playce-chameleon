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
 * <p>Java class for web-componentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="web-componentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="context-root" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="deployment-target" type="{http://www.tmaxsoft.com/xml/ns/jeus}deployment-targetType" minOccurs="0"/>
 *         &lt;element name="keep-generated" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="jeus-web-dd" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="java-security-permission" type="{http://www.tmaxsoft.com/xml/ns/jeus}securityPermissionType" minOccurs="0"/>
 *         &lt;element name="session-timeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "web-componentType", propOrder = {
    "uri",
    "contextRoot",
    "deploymentTarget",
    "keepGenerated",
    "jeusWebDd",
    "javaSecurityPermission",
    "sessionTimeout"
})
public class WebComponentType {

    @XmlElement(namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String uri;
    @XmlElement(name = "context-root", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String contextRoot;
    @XmlElement(name = "deployment-target", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected DeploymentTargetType deploymentTarget;
    @XmlElement(name = "keep-generated", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected Boolean keepGenerated;
    @XmlElement(name = "jeus-web-dd", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String jeusWebDd;
    @XmlElement(name = "java-security-permission", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected SecurityPermissionType javaSecurityPermission;
    @XmlElement(name = "session-timeout", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    protected Integer sessionTimeout;

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the contextRoot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextRoot() {
        return contextRoot;
    }

    /**
     * Sets the value of the contextRoot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextRoot(String value) {
        this.contextRoot = value;
    }

    /**
     * Gets the value of the deploymentTarget property.
     * 
     * @return
     *     possible object is
     *     {@link DeploymentTargetType }
     *     
     */
    public DeploymentTargetType getDeploymentTarget() {
        return deploymentTarget;
    }

    /**
     * Sets the value of the deploymentTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeploymentTargetType }
     *     
     */
    public void setDeploymentTarget(DeploymentTargetType value) {
        this.deploymentTarget = value;
    }

    /**
     * Gets the value of the keepGenerated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isKeepGenerated() {
        return keepGenerated;
    }

    /**
     * Sets the value of the keepGenerated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setKeepGenerated(Boolean value) {
        this.keepGenerated = value;
    }

    /**
     * Gets the value of the jeusWebDd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJeusWebDd() {
        return jeusWebDd;
    }

    /**
     * Sets the value of the jeusWebDd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJeusWebDd(String value) {
        this.jeusWebDd = value;
    }

    /**
     * Gets the value of the javaSecurityPermission property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityPermissionType }
     *     
     */
    public SecurityPermissionType getJavaSecurityPermission() {
        return javaSecurityPermission;
    }

    /**
     * Sets the value of the javaSecurityPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityPermissionType }
     *     
     */
    public void setJavaSecurityPermission(SecurityPermissionType value) {
        this.javaSecurityPermission = value;
    }

    /**
     * Gets the value of the sessionTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    /**
     * Sets the value of the sessionTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSessionTimeout(Integer value) {
        this.sessionTimeout = value;
    }

}