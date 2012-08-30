//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.30 at 10:54:58 오전 KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * 	The enterprise-beansType declares one or more enterprise
 * 	beans. Each bean can be a session, entity or message-driven
 * 	bean.
 * 
 *       
 * 
 * <p>Java class for enterprise-beansType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="enterprise-beansType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="session" type="{http://java.sun.com/xml/ns/j2ee}session-beanType"/>
 *         &lt;element name="entity" type="{http://java.sun.com/xml/ns/j2ee}entity-beanType"/>
 *         &lt;element name="message-driven" type="{http://java.sun.com/xml/ns/j2ee}message-driven-beanType"/>
 *       &lt;/choice>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enterprise-beansType", propOrder = {
    "sessionOrEntityOrMessageDriven"
})
public class EnterpriseBeansType {

    @XmlElements({
        @XmlElement(name = "message-driven", type = MessageDrivenBeanType.class),
        @XmlElement(name = "entity", type = EntityBeanType.class),
        @XmlElement(name = "session", type = SessionBeanType.class)
    })
    protected List<Object> sessionOrEntityOrMessageDriven;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String id;

    /**
     * Gets the value of the sessionOrEntityOrMessageDriven property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sessionOrEntityOrMessageDriven property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSessionOrEntityOrMessageDriven().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageDrivenBeanType }
     * {@link EntityBeanType }
     * {@link SessionBeanType }
     * 
     * 
     */
    public List<Object> getSessionOrEntityOrMessageDriven() {
        if (sessionOrEntityOrMessageDriven == null) {
            sessionOrEntityOrMessageDriven = new ArrayList<Object>();
        }
        return this.sessionOrEntityOrMessageDriven;
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
