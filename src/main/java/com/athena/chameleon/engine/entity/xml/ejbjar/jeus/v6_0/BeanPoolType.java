//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.05 at 10:14:54 오후 KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.jeus.v6_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bean-poolType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bean-poolType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pool-min" type="{http://www.tmaxsoft.com/xml/ns/jeus}nonNegativeIntType" minOccurs="0"/>
 *         &lt;element name="pool-max" type="{http://www.tmaxsoft.com/xml/ns/jeus}nonNegativeIntType" minOccurs="0"/>
 *         &lt;element name="resizing-period" type="{http://www.tmaxsoft.com/xml/ns/jeus}nonNegativeLongType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bean-poolType", propOrder = {
    "poolMin",
    "poolMax",
    "resizingPeriod"
})
public class BeanPoolType {

    @XmlElement(name = "pool-min", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", defaultValue = "0")
    protected Integer poolMin;
    @XmlElement(name = "pool-max", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", defaultValue = "100")
    protected Integer poolMax;
    @XmlElement(name = "resizing-period", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", defaultValue = "60000")
    protected Long resizingPeriod;

    /**
     * Gets the value of the poolMin property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPoolMin() {
        return poolMin;
    }

    /**
     * Sets the value of the poolMin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPoolMin(Integer value) {
        this.poolMin = value;
    }

    /**
     * Gets the value of the poolMax property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPoolMax() {
        return poolMax;
    }

    /**
     * Sets the value of the poolMax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPoolMax(Integer value) {
        this.poolMax = value;
    }

    /**
     * Gets the value of the resizingPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResizingPeriod() {
        return resizingPeriod;
    }

    /**
     * Sets the value of the resizingPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResizingPeriod(Long value) {
        this.resizingPeriod = value;
    }

}
