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
 * <p>Java class for driver-paramsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="driver-paramsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statement" type="{http://www.bea.com/ns/weblogic/90}statementType" minOccurs="0"/>
 *         &lt;element name="prepared-statement" type="{http://www.bea.com/ns/weblogic/90}prepared-statementType" minOccurs="0"/>
 *         &lt;element name="row-prefetch-enabled" type="{http://www.bea.com/ns/weblogic/90}true-falseType" minOccurs="0"/>
 *         &lt;element name="row-prefetch-size" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="stream-chunk-size" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "driver-paramsType", propOrder = {
    "statement",
    "preparedStatement",
    "rowPrefetchEnabled",
    "rowPrefetchSize",
    "streamChunkSize"
})
public class DriverParamsType {

    @XmlElement(namespace = "http://www.bea.com/ns/weblogic/90")
    protected StatementType statement;
    @XmlElement(name = "prepared-statement", namespace = "http://www.bea.com/ns/weblogic/90")
    protected PreparedStatementType preparedStatement;
    @XmlElement(name = "row-prefetch-enabled", namespace = "http://www.bea.com/ns/weblogic/90")
    protected TrueFalseType rowPrefetchEnabled;
    @XmlElement(name = "row-prefetch-size", namespace = "http://www.bea.com/ns/weblogic/90")
    protected Integer rowPrefetchSize;
    @XmlElement(name = "stream-chunk-size", namespace = "http://www.bea.com/ns/weblogic/90")
    protected Integer streamChunkSize;

    /**
     * Gets the value of the statement property.
     * 
     * @return
     *     possible object is
     *     {@link StatementType }
     *     
     */
    public StatementType getStatement() {
        return statement;
    }

    /**
     * Sets the value of the statement property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatementType }
     *     
     */
    public void setStatement(StatementType value) {
        this.statement = value;
    }

    /**
     * Gets the value of the preparedStatement property.
     * 
     * @return
     *     possible object is
     *     {@link PreparedStatementType }
     *     
     */
    public PreparedStatementType getPreparedStatement() {
        return preparedStatement;
    }

    /**
     * Sets the value of the preparedStatement property.
     * 
     * @param value
     *     allowed object is
     *     {@link PreparedStatementType }
     *     
     */
    public void setPreparedStatement(PreparedStatementType value) {
        this.preparedStatement = value;
    }

    /**
     * Gets the value of the rowPrefetchEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getRowPrefetchEnabled() {
        return rowPrefetchEnabled;
    }

    /**
     * Sets the value of the rowPrefetchEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setRowPrefetchEnabled(TrueFalseType value) {
        this.rowPrefetchEnabled = value;
    }

    /**
     * Gets the value of the rowPrefetchSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRowPrefetchSize() {
        return rowPrefetchSize;
    }

    /**
     * Sets the value of the rowPrefetchSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRowPrefetchSize(Integer value) {
        this.rowPrefetchSize = value;
    }

    /**
     * Gets the value of the streamChunkSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStreamChunkSize() {
        return streamChunkSize;
    }

    /**
     * Sets the value of the streamChunkSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStreamChunkSize(Integer value) {
        this.streamChunkSize = value;
    }

}