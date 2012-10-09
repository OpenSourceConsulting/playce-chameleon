//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.10.09 시간 12:53:10 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.v2_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "displayName",
    "smallIcon",
    "largeIcon",
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
    "securityRoleRef",
    "securityIdentity",
    "resourceRef",
    "resourceEnvRef",
    "query"
})
@XmlRootElement(name = "entity")
public class Entity {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    protected Description description;
    @XmlElement(name = "display-name")
    protected DisplayName displayName;
    @XmlElement(name = "small-icon")
    protected SmallIcon smallIcon;
    @XmlElement(name = "large-icon")
    protected LargeIcon largeIcon;
    @XmlElement(name = "ejb-name", required = true)
    protected EjbName ejbName;
    protected Home home;
    protected Remote remote;
    @XmlElement(name = "local-home")
    protected LocalHome localHome;
    protected Local local;
    @XmlElement(name = "ejb-class", required = true)
    protected EjbClass ejbClass;
    @XmlElement(name = "persistence-type", required = true)
    protected PersistenceType persistenceType;
    @XmlElement(name = "prim-key-class", required = true)
    protected PrimKeyClass primKeyClass;
    @XmlElement(required = true)
    protected Reentrant reentrant;
    @XmlElement(name = "cmp-version")
    protected CmpVersion cmpVersion;
    @XmlElement(name = "abstract-schema-name")
    protected AbstractSchemaName abstractSchemaName;
    @XmlElement(name = "cmp-field")
    protected List<CmpField> cmpField;
    @XmlElement(name = "primkey-field")
    protected PrimkeyField primkeyField;
    @XmlElement(name = "env-entry")
    protected List<EnvEntry> envEntry;
    @XmlElement(name = "ejb-ref")
    protected List<EjbRef> ejbRef;
    @XmlElement(name = "ejb-local-ref")
    protected List<EjbLocalRef> ejbLocalRef;
    @XmlElement(name = "security-role-ref")
    protected List<SecurityRoleRef> securityRoleRef;
    @XmlElement(name = "security-identity")
    protected SecurityIdentity securityIdentity;
    @XmlElement(name = "resource-ref")
    protected List<ResourceRef> resourceRef;
    @XmlElement(name = "resource-env-ref")
    protected List<ResourceEnvRef> resourceEnvRef;
    protected List<Query> query;

    /**
     * id 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * id 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * description 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * description 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * displayName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link DisplayName }
     *     
     */
    public DisplayName getDisplayName() {
        return displayName;
    }

    /**
     * displayName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link DisplayName }
     *     
     */
    public void setDisplayName(DisplayName value) {
        this.displayName = value;
    }

    /**
     * smallIcon 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SmallIcon }
     *     
     */
    public SmallIcon getSmallIcon() {
        return smallIcon;
    }

    /**
     * smallIcon 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SmallIcon }
     *     
     */
    public void setSmallIcon(SmallIcon value) {
        this.smallIcon = value;
    }

    /**
     * largeIcon 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link LargeIcon }
     *     
     */
    public LargeIcon getLargeIcon() {
        return largeIcon;
    }

    /**
     * largeIcon 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link LargeIcon }
     *     
     */
    public void setLargeIcon(LargeIcon value) {
        this.largeIcon = value;
    }

    /**
     * ejbName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbName }
     *     
     */
    public EjbName getEjbName() {
        return ejbName;
    }

    /**
     * ejbName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbName }
     *     
     */
    public void setEjbName(EjbName value) {
        this.ejbName = value;
    }

    /**
     * home 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Home }
     *     
     */
    public Home getHome() {
        return home;
    }

    /**
     * home 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Home }
     *     
     */
    public void setHome(Home value) {
        this.home = value;
    }

    /**
     * remote 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Remote }
     *     
     */
    public Remote getRemote() {
        return remote;
    }

    /**
     * remote 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Remote }
     *     
     */
    public void setRemote(Remote value) {
        this.remote = value;
    }

    /**
     * localHome 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link LocalHome }
     *     
     */
    public LocalHome getLocalHome() {
        return localHome;
    }

    /**
     * localHome 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalHome }
     *     
     */
    public void setLocalHome(LocalHome value) {
        this.localHome = value;
    }

    /**
     * local 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Local }
     *     
     */
    public Local getLocal() {
        return local;
    }

    /**
     * local 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Local }
     *     
     */
    public void setLocal(Local value) {
        this.local = value;
    }

    /**
     * ejbClass 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbClass }
     *     
     */
    public EjbClass getEjbClass() {
        return ejbClass;
    }

    /**
     * ejbClass 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbClass }
     *     
     */
    public void setEjbClass(EjbClass value) {
        this.ejbClass = value;
    }

    /**
     * persistenceType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PersistenceType }
     *     
     */
    public PersistenceType getPersistenceType() {
        return persistenceType;
    }

    /**
     * persistenceType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PersistenceType }
     *     
     */
    public void setPersistenceType(PersistenceType value) {
        this.persistenceType = value;
    }

    /**
     * primKeyClass 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PrimKeyClass }
     *     
     */
    public PrimKeyClass getPrimKeyClass() {
        return primKeyClass;
    }

    /**
     * primKeyClass 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimKeyClass }
     *     
     */
    public void setPrimKeyClass(PrimKeyClass value) {
        this.primKeyClass = value;
    }

    /**
     * reentrant 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Reentrant }
     *     
     */
    public Reentrant getReentrant() {
        return reentrant;
    }

    /**
     * reentrant 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Reentrant }
     *     
     */
    public void setReentrant(Reentrant value) {
        this.reentrant = value;
    }

    /**
     * cmpVersion 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CmpVersion }
     *     
     */
    public CmpVersion getCmpVersion() {
        return cmpVersion;
    }

    /**
     * cmpVersion 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CmpVersion }
     *     
     */
    public void setCmpVersion(CmpVersion value) {
        this.cmpVersion = value;
    }

    /**
     * abstractSchemaName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AbstractSchemaName }
     *     
     */
    public AbstractSchemaName getAbstractSchemaName() {
        return abstractSchemaName;
    }

    /**
     * abstractSchemaName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractSchemaName }
     *     
     */
    public void setAbstractSchemaName(AbstractSchemaName value) {
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
     * {@link CmpField }
     * 
     * 
     */
    public List<CmpField> getCmpField() {
        if (cmpField == null) {
            cmpField = new ArrayList<CmpField>();
        }
        return this.cmpField;
    }

    /**
     * primkeyField 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PrimkeyField }
     *     
     */
    public PrimkeyField getPrimkeyField() {
        return primkeyField;
    }

    /**
     * primkeyField 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimkeyField }
     *     
     */
    public void setPrimkeyField(PrimkeyField value) {
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
     * {@link EnvEntry }
     * 
     * 
     */
    public List<EnvEntry> getEnvEntry() {
        if (envEntry == null) {
            envEntry = new ArrayList<EnvEntry>();
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
     * {@link EjbRef }
     * 
     * 
     */
    public List<EjbRef> getEjbRef() {
        if (ejbRef == null) {
            ejbRef = new ArrayList<EjbRef>();
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
     * {@link EjbLocalRef }
     * 
     * 
     */
    public List<EjbLocalRef> getEjbLocalRef() {
        if (ejbLocalRef == null) {
            ejbLocalRef = new ArrayList<EjbLocalRef>();
        }
        return this.ejbLocalRef;
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
     * {@link SecurityRoleRef }
     * 
     * 
     */
    public List<SecurityRoleRef> getSecurityRoleRef() {
        if (securityRoleRef == null) {
            securityRoleRef = new ArrayList<SecurityRoleRef>();
        }
        return this.securityRoleRef;
    }

    /**
     * securityIdentity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SecurityIdentity }
     *     
     */
    public SecurityIdentity getSecurityIdentity() {
        return securityIdentity;
    }

    /**
     * securityIdentity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityIdentity }
     *     
     */
    public void setSecurityIdentity(SecurityIdentity value) {
        this.securityIdentity = value;
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
     * {@link ResourceRef }
     * 
     * 
     */
    public List<ResourceRef> getResourceRef() {
        if (resourceRef == null) {
            resourceRef = new ArrayList<ResourceRef>();
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
     * {@link ResourceEnvRef }
     * 
     * 
     */
    public List<ResourceEnvRef> getResourceEnvRef() {
        if (resourceEnvRef == null) {
            resourceEnvRef = new ArrayList<ResourceEnvRef>();
        }
        return this.resourceEnvRef;
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
     * {@link Query }
     * 
     * 
     */
    public List<Query> getQuery() {
        if (query == null) {
            query = new ArrayList<Query>();
        }
        return this.query;
    }

}
