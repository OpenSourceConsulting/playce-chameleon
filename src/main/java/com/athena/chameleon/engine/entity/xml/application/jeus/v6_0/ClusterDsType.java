//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.17 at 07:02:27 오후 KST 
//


package com.athena.chameleon.engine.entity.xml.application.jeus.v6_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for cluster-dsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cluster-dsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="data-source-id" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="export-name" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="data-source-target" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="data-source-selector" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="load-balance" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is-pre-conn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="use-failback" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="data-source" type="{http://www.w3.org/2001/XMLSchema}token" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cluster-dsType", propOrder = {
    "dataSourceId",
    "exportName",
    "dataSourceTarget",
    "dataSourceSelector",
    "loadBalance",
    "isPreConn",
    "useFailback",
    "dataSource"
})
public class ClusterDsType {

    @XmlElement(name = "data-source-id", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String dataSourceId;
    @XmlElement(name = "export-name", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String exportName;
    @XmlElementRef(name = "data-source-target", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", type = JAXBElement.class)
    protected List<JAXBElement<String>> dataSourceTarget;
    @XmlElement(name = "data-source-selector", namespace = "http://www.tmaxsoft.com/xml/ns/jeus")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String dataSourceSelector;
    @XmlElement(name = "load-balance", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", defaultValue = "false")
    protected Boolean loadBalance;
    @XmlElement(name = "is-pre-conn", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", defaultValue = "false")
    protected Boolean isPreConn;
    @XmlElement(name = "use-failback", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", defaultValue = "true")
    protected Boolean useFailback;
    @XmlElementRef(name = "data-source", namespace = "http://www.tmaxsoft.com/xml/ns/jeus", type = JAXBElement.class)
    protected List<JAXBElement<String>> dataSource;

    /**
     * Gets the value of the dataSourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceId() {
        return dataSourceId;
    }

    /**
     * Sets the value of the dataSourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceId(String value) {
        this.dataSourceId = value;
    }

    /**
     * Gets the value of the exportName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportName() {
        return exportName;
    }

    /**
     * Sets the value of the exportName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportName(String value) {
        this.exportName = value;
    }

    /**
     * Gets the value of the dataSourceTarget property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceTarget property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<String>> getDataSourceTarget() {
        if (dataSourceTarget == null) {
            dataSourceTarget = new ArrayList<JAXBElement<String>>();
        }
        return this.dataSourceTarget;
    }

    /**
     * Gets the value of the dataSourceSelector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceSelector() {
        return dataSourceSelector;
    }

    /**
     * Sets the value of the dataSourceSelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceSelector(String value) {
        this.dataSourceSelector = value;
    }

    /**
     * Gets the value of the loadBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLoadBalance() {
        return loadBalance;
    }

    /**
     * Sets the value of the loadBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLoadBalance(Boolean value) {
        this.loadBalance = value;
    }

    /**
     * Gets the value of the isPreConn property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPreConn() {
        return isPreConn;
    }

    /**
     * Sets the value of the isPreConn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPreConn(Boolean value) {
        this.isPreConn = value;
    }

    /**
     * Gets the value of the useFailback property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseFailback() {
        return useFailback;
    }

    /**
     * Sets the value of the useFailback property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseFailback(Boolean value) {
        this.useFailback = value;
    }

    /**
     * Gets the value of the dataSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<String>> getDataSource() {
        if (dataSource == null) {
            dataSource = new ArrayList<JAXBElement<String>>();
        }
        return this.dataSource;
    }

}
