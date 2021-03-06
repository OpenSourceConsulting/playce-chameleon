//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.30 at 10:54:58 오전 KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.v2_1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * 	The entity-beanType declares an entity bean. The declaration
 * 	consists of:
 * 
 * 	    - an optional description
 * 	    - an optional display name
 * 	    - an optional icon element that contains a small and a large
 * 	      icon file name
 * 	    - a unique name assigned to the enterprise bean
 * 	      in the deployment descriptor
 * 	    - the names of the entity bean's remote home
 * 	      and remote interfaces, if any
 * 	    - the names of the entity bean's local home and local
 * 	      interfaces, if any
 * 	    - the entity bean's implementation class
 * 	    - the entity bean's persistence management type
 * 	    - the entity bean's primary key class name
 * 	    - an indication of the entity bean's reentrancy
 * 	    - an optional specification of the
 * 	      entity bean's cmp-version
 * 	    - an optional specification of the entity bean's
 * 	      abstract schema name
 * 	    - an optional list of container-managed fields
 * 	    - an optional specification of the primary key
 * 	      field
 * 	    - an optional declaration of the bean's environment
 * 	      entries
 * 	    - an optional declaration of the bean's EJB
 * 	      references
 * 	    - an optional declaration of the bean's local
 * 	      EJB references
 * 	    - an optional declaration of the bean's web
 * 	      service references
 * 	    - an optional declaration of the security role
 * 	      references
 * 	    - an optional declaration of the security identity
 * 	      to be used for the execution of the bean's methods
 * 	    - an optional declaration of the bean's
 * 	      resource manager connection factory references
 * 	    - an optional declaration of the bean's
 * 	      resource environment references
 * 	    - an optional declaration of the bean's message
 * 	      destination references
 * 	    - an optional set of query declarations
 * 	      for finder and select methods for an entity
 * 	      bean with cmp-version 2.x.
 * 
 * 	The optional abstract-schema-name element must be specified
 * 	for an entity bean with container-managed persistence and
 * 	cmp-version 2.x.
 * 
 * 	The optional primkey-field may be present in the descriptor
 * 	if the entity's persistence-type is Container.
 * 
 * 	The optional cmp-version element may be present in the
 * 	descriptor if the entity's persistence-type is Container. If
 * 	the persistence-type is Container and the cmp-version
 * 	element is not specified, its value defaults to 2.x.
 * 
 * 	The optional home and remote elements must be specified if
 * 	the entity bean cmp-version is 1.x.
 * 
 * 	The optional home and remote elements must be specified if
 * 	the entity bean has a remote home and remote interface.
 * 
 * 	The optional local-home and local elements must be specified
 * 	if the entity bean has a local home and local interface.
 * 
 * 	Either both the local-home and the local elements or both
 * 	the home and the remote elements must be specified.
 * 
 * 	The optional query elements must be present if the
 * 	persistence-type is Container and the cmp-version is 2.x and
 * 	query methods other than findByPrimaryKey have been defined
 * 	for the entity bean.
 * 
 * 	The other elements that are optional are "optional" in the
 * 	sense that they are omitted if the lists represented by them
 * 	are empty.
 * 
 * 	At least one cmp-field element must be present in the
 * 	descriptor if the entity's persistence-type is Container and
 * 	the cmp-version is 1.x, and none must not be present if the
 * 	entity's persistence-type is Bean.
 * 
 *       
 * 
 * <p>Java class for entity-beanType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="entity-beanType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://java.sun.com/xml/ns/j2ee}descriptionGroup"/>
 *         &lt;element name="ejb-name" type="{http://java.sun.com/xml/ns/j2ee}ejb-nameType"/>
 *         &lt;element name="home" type="{http://java.sun.com/xml/ns/j2ee}homeType" minOccurs="0"/>
 *         &lt;element name="remote" type="{http://java.sun.com/xml/ns/j2ee}remoteType" minOccurs="0"/>
 *         &lt;element name="local-home" type="{http://java.sun.com/xml/ns/j2ee}local-homeType" minOccurs="0"/>
 *         &lt;element name="local" type="{http://java.sun.com/xml/ns/j2ee}localType" minOccurs="0"/>
 *         &lt;element name="ejb-class" type="{http://java.sun.com/xml/ns/j2ee}ejb-classType"/>
 *         &lt;element name="persistence-type" type="{http://java.sun.com/xml/ns/j2ee}persistence-typeType"/>
 *         &lt;element name="prim-key-class" type="{http://java.sun.com/xml/ns/j2ee}fully-qualified-classType"/>
 *         &lt;element name="reentrant" type="{http://java.sun.com/xml/ns/j2ee}true-falseType"/>
 *         &lt;element name="cmp-version" type="{http://java.sun.com/xml/ns/j2ee}cmp-versionType" minOccurs="0"/>
 *         &lt;element name="abstract-schema-name" type="{http://java.sun.com/xml/ns/j2ee}java-identifierType" minOccurs="0"/>
 *         &lt;element name="cmp-field" type="{http://java.sun.com/xml/ns/j2ee}cmp-fieldType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="primkey-field" type="{http://java.sun.com/xml/ns/j2ee}string" minOccurs="0"/>
 *         &lt;group ref="{http://java.sun.com/xml/ns/j2ee}jndiEnvironmentRefsGroup"/>
 *         &lt;element name="security-role-ref" type="{http://java.sun.com/xml/ns/j2ee}security-role-refType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="security-identity" type="{http://java.sun.com/xml/ns/j2ee}security-identityType" minOccurs="0"/>
 *         &lt;element name="query" type="{http://java.sun.com/xml/ns/j2ee}queryType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "entity-beanType", propOrder = {
    "description",
    "displayName",
    "icon",
    "ejbName",
    "home",
    "remote",
    "localHome",
    "local",
    "ejbClass",
    "persistenceType",
    "primKeyClass",
    "reentrant",
    "cmpVersion",
    "abstractSchemaName",
    "cmpField",
    "primkeyField",
    "envEntry",
    "ejbRef",
    "ejbLocalRef",
    "serviceRef",
    "resourceRef",
    "resourceEnvRef",
    "messageDestinationRef",
    "securityRoleRef",
    "securityIdentity",
    "query"
})
public class EntityBeanType {

    protected List<DescriptionType> description;
    @XmlElement(name = "display-name")
    protected List<DisplayNameType> displayName;
    protected List<IconType> icon;
    @XmlElement(name = "ejb-name", required = true)
    protected EjbNameType ejbName;
    protected HomeType home;
    protected RemoteType remote;
    @XmlElement(name = "local-home")
    protected LocalHomeType localHome;
    protected LocalType local;
    @XmlElement(name = "ejb-class", required = true)
    protected EjbClassType ejbClass;
    @XmlElement(name = "persistence-type", required = true)
    protected PersistenceTypeType persistenceType;
    @XmlElement(name = "prim-key-class", required = true)
    protected FullyQualifiedClassType primKeyClass;
    @XmlElement(required = true)
    protected TrueFalseType reentrant;
    @XmlElement(name = "cmp-version")
    protected CmpVersionType cmpVersion;
    @XmlElement(name = "abstract-schema-name")
    protected JavaIdentifierType abstractSchemaName;
    @XmlElement(name = "cmp-field")
    protected List<CmpFieldType> cmpField;
    @XmlElement(name = "primkey-field")
    protected String primkeyField;
    @XmlElement(name = "env-entry")
    protected List<EnvEntryType> envEntry;
    @XmlElement(name = "ejb-ref")
    protected List<EjbRefType> ejbRef;
    @XmlElement(name = "ejb-local-ref")
    protected List<EjbLocalRefType> ejbLocalRef;
    @XmlElement(name = "service-ref")
    protected List<ServiceRefType> serviceRef;
    @XmlElement(name = "resource-ref")
    protected List<ResourceRefType> resourceRef;
    @XmlElement(name = "resource-env-ref")
    protected List<ResourceEnvRefType> resourceEnvRef;
    @XmlElement(name = "message-destination-ref")
    protected List<MessageDestinationRefType> messageDestinationRef;
    @XmlElement(name = "security-role-ref")
    protected List<SecurityRoleRefType> securityRoleRef;
    @XmlElement(name = "security-identity")
    protected SecurityIdentityType securityIdentity;
    protected List<QueryType> query;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String id;

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionType }
     * 
     * 
     */
    public List<DescriptionType> getDescription() {
        if (description == null) {
            description = new ArrayList<DescriptionType>();
        }
        return this.description;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DisplayNameType }
     * 
     * 
     */
    public List<DisplayNameType> getDisplayName() {
        if (displayName == null) {
            displayName = new ArrayList<DisplayNameType>();
        }
        return this.displayName;
    }

    /**
     * Gets the value of the icon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the icon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIcon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IconType }
     * 
     * 
     */
    public List<IconType> getIcon() {
        if (icon == null) {
            icon = new ArrayList<IconType>();
        }
        return this.icon;
    }

    /**
     * Gets the value of the ejbName property.
     * 
     * @return
     *     possible object is
     *     {@link EjbNameType }
     *     
     */
    public EjbNameType getEjbName() {
        return ejbName;
    }

    /**
     * Sets the value of the ejbName property.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbNameType }
     *     
     */
    public void setEjbName(EjbNameType value) {
        this.ejbName = value;
    }

    /**
     * Gets the value of the home property.
     * 
     * @return
     *     possible object is
     *     {@link HomeType }
     *     
     */
    public HomeType getHome() {
        return home;
    }

    /**
     * Sets the value of the home property.
     * 
     * @param value
     *     allowed object is
     *     {@link HomeType }
     *     
     */
    public void setHome(HomeType value) {
        this.home = value;
    }

    /**
     * Gets the value of the remote property.
     * 
     * @return
     *     possible object is
     *     {@link RemoteType }
     *     
     */
    public RemoteType getRemote() {
        return remote;
    }

    /**
     * Sets the value of the remote property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteType }
     *     
     */
    public void setRemote(RemoteType value) {
        this.remote = value;
    }

    /**
     * Gets the value of the localHome property.
     * 
     * @return
     *     possible object is
     *     {@link LocalHomeType }
     *     
     */
    public LocalHomeType getLocalHome() {
        return localHome;
    }

    /**
     * Sets the value of the localHome property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalHomeType }
     *     
     */
    public void setLocalHome(LocalHomeType value) {
        this.localHome = value;
    }

    /**
     * Gets the value of the local property.
     * 
     * @return
     *     possible object is
     *     {@link LocalType }
     *     
     */
    public LocalType getLocal() {
        return local;
    }

    /**
     * Sets the value of the local property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalType }
     *     
     */
    public void setLocal(LocalType value) {
        this.local = value;
    }

    /**
     * Gets the value of the ejbClass property.
     * 
     * @return
     *     possible object is
     *     {@link EjbClassType }
     *     
     */
    public EjbClassType getEjbClass() {
        return ejbClass;
    }

    /**
     * Sets the value of the ejbClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbClassType }
     *     
     */
    public void setEjbClass(EjbClassType value) {
        this.ejbClass = value;
    }

    /**
     * Gets the value of the persistenceType property.
     * 
     * @return
     *     possible object is
     *     {@link PersistenceTypeType }
     *     
     */
    public PersistenceTypeType getPersistenceType() {
        return persistenceType;
    }

    /**
     * Sets the value of the persistenceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersistenceTypeType }
     *     
     */
    public void setPersistenceType(PersistenceTypeType value) {
        this.persistenceType = value;
    }

    /**
     * Gets the value of the primKeyClass property.
     * 
     * @return
     *     possible object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public FullyQualifiedClassType getPrimKeyClass() {
        return primKeyClass;
    }

    /**
     * Sets the value of the primKeyClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullyQualifiedClassType }
     *     
     */
    public void setPrimKeyClass(FullyQualifiedClassType value) {
        this.primKeyClass = value;
    }

    /**
     * Gets the value of the reentrant property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getReentrant() {
        return reentrant;
    }

    /**
     * Sets the value of the reentrant property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setReentrant(TrueFalseType value) {
        this.reentrant = value;
    }

    /**
     * Gets the value of the cmpVersion property.
     * 
     * @return
     *     possible object is
     *     {@link CmpVersionType }
     *     
     */
    public CmpVersionType getCmpVersion() {
        return cmpVersion;
    }

    /**
     * Sets the value of the cmpVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CmpVersionType }
     *     
     */
    public void setCmpVersion(CmpVersionType value) {
        this.cmpVersion = value;
    }

    /**
     * Gets the value of the abstractSchemaName property.
     * 
     * @return
     *     possible object is
     *     {@link JavaIdentifierType }
     *     
     */
    public JavaIdentifierType getAbstractSchemaName() {
        return abstractSchemaName;
    }

    /**
     * Sets the value of the abstractSchemaName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JavaIdentifierType }
     *     
     */
    public void setAbstractSchemaName(JavaIdentifierType value) {
        this.abstractSchemaName = value;
    }

    /**
     * Gets the value of the cmpField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cmpField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCmpField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CmpFieldType }
     * 
     * 
     */
    public List<CmpFieldType> getCmpField() {
        if (cmpField == null) {
            cmpField = new ArrayList<CmpFieldType>();
        }
        return this.cmpField;
    }

    /**
     * Gets the value of the primkeyField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimkeyField() {
        return primkeyField;
    }

    /**
     * Sets the value of the primkeyField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimkeyField(String value) {
        this.primkeyField = value;
    }

    /**
     * Gets the value of the envEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the envEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnvEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnvEntryType }
     * 
     * 
     */
    public List<EnvEntryType> getEnvEntry() {
        if (envEntry == null) {
            envEntry = new ArrayList<EnvEntryType>();
        }
        return this.envEntry;
    }

    /**
     * Gets the value of the ejbRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ejbRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEjbRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EjbRefType }
     * 
     * 
     */
    public List<EjbRefType> getEjbRef() {
        if (ejbRef == null) {
            ejbRef = new ArrayList<EjbRefType>();
        }
        return this.ejbRef;
    }

    /**
     * Gets the value of the ejbLocalRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ejbLocalRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEjbLocalRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EjbLocalRefType }
     * 
     * 
     */
    public List<EjbLocalRefType> getEjbLocalRef() {
        if (ejbLocalRef == null) {
            ejbLocalRef = new ArrayList<EjbLocalRefType>();
        }
        return this.ejbLocalRef;
    }

    /**
     * Gets the value of the serviceRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceRefType }
     * 
     * 
     */
    public List<ServiceRefType> getServiceRef() {
        if (serviceRef == null) {
            serviceRef = new ArrayList<ServiceRefType>();
        }
        return this.serviceRef;
    }

    /**
     * Gets the value of the resourceRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceRefType }
     * 
     * 
     */
    public List<ResourceRefType> getResourceRef() {
        if (resourceRef == null) {
            resourceRef = new ArrayList<ResourceRefType>();
        }
        return this.resourceRef;
    }

    /**
     * Gets the value of the resourceEnvRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceEnvRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceEnvRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceEnvRefType }
     * 
     * 
     */
    public List<ResourceEnvRefType> getResourceEnvRef() {
        if (resourceEnvRef == null) {
            resourceEnvRef = new ArrayList<ResourceEnvRefType>();
        }
        return this.resourceEnvRef;
    }

    /**
     * Gets the value of the messageDestinationRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageDestinationRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageDestinationRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageDestinationRefType }
     * 
     * 
     */
    public List<MessageDestinationRefType> getMessageDestinationRef() {
        if (messageDestinationRef == null) {
            messageDestinationRef = new ArrayList<MessageDestinationRefType>();
        }
        return this.messageDestinationRef;
    }

    /**
     * Gets the value of the securityRoleRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securityRoleRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurityRoleRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityRoleRefType }
     * 
     * 
     */
    public List<SecurityRoleRefType> getSecurityRoleRef() {
        if (securityRoleRef == null) {
            securityRoleRef = new ArrayList<SecurityRoleRefType>();
        }
        return this.securityRoleRef;
    }

    /**
     * Gets the value of the securityIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityIdentityType }
     *     
     */
    public SecurityIdentityType getSecurityIdentity() {
        return securityIdentity;
    }

    /**
     * Sets the value of the securityIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityIdentityType }
     *     
     */
    public void setSecurityIdentity(SecurityIdentityType value) {
        this.securityIdentity = value;
    }

    /**
     * Gets the value of the query property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the query property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuery().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryType }
     * 
     * 
     */
    public List<QueryType> getQuery() {
        if (query == null) {
            query = new ArrayList<QueryType>();
        }
        return this.query;
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
