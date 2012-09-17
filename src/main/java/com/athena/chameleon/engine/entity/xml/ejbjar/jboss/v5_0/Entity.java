//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 02:02:31 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0;

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
    "ejbName",
    "jndiName",
    "localJndiName",
    "callByValue",
    "readOnly",
    "exceptionOnRollback",
    "timerPersistence",
    "configurationName",
    "invokerBindings",
    "securityProxy",
    "ejbRef",
    "ejbLocalRef",
    "serviceRef",
    "resourceRef",
    "resourceEnvRef",
    "messageDestinationRef",
    "securityIdentity",
    "methodAttributes",
    "clustered",
    "clusterConfig",
    "cacheInvalidation",
    "cacheInvalidationConfig",
    "depends",
    "iorSecurityConfig"
})
@XmlRootElement(name = "entity")
public class Entity {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "ejb-name", required = true)
    protected String ejbName;
    @XmlElement(name = "jndi-name")
    protected JndiName jndiName;
    @XmlElement(name = "local-jndi-name")
    protected String localJndiName;
    @XmlElement(name = "call-by-value")
    protected String callByValue;
    @XmlElement(name = "read-only")
    protected String readOnly;
    @XmlElement(name = "exception-on-rollback")
    protected String exceptionOnRollback;
    @XmlElement(name = "timer-persistence")
    protected String timerPersistence;
    @XmlElement(name = "configuration-name")
    protected String configurationName;
    @XmlElement(name = "invoker-bindings")
    protected InvokerBindings invokerBindings;
    @XmlElement(name = "security-proxy")
    protected String securityProxy;
    @XmlElement(name = "ejb-ref")
    protected List<EjbRef> ejbRef;
    @XmlElement(name = "ejb-local-ref")
    protected List<EjbLocalRef> ejbLocalRef;
    @XmlElement(name = "service-ref")
    protected List<ServiceRef> serviceRef;
    @XmlElement(name = "resource-ref")
    protected List<ResourceRef> resourceRef;
    @XmlElement(name = "resource-env-ref")
    protected List<ResourceEnvRef> resourceEnvRef;
    @XmlElement(name = "message-destination-ref")
    protected List<MessageDestinationRef> messageDestinationRef;
    @XmlElement(name = "security-identity")
    protected SecurityIdentity securityIdentity;
    @XmlElement(name = "method-attributes")
    protected MethodAttributes methodAttributes;
    protected String clustered;
    @XmlElement(name = "cluster-config")
    protected ClusterConfig clusterConfig;
    @XmlElement(name = "cache-invalidation")
    protected String cacheInvalidation;
    @XmlElement(name = "cache-invalidation-config")
    protected CacheInvalidationConfig cacheInvalidationConfig;
    protected List<Depends> depends;
    @XmlElement(name = "ior-security-config")
    protected IorSecurityConfig iorSecurityConfig;

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
     * ejbName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEjbName() {
        return ejbName;
    }

    /**
     * ejbName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEjbName(String value) {
        this.ejbName = value;
    }

    /**
     * jndiName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link JndiName }
     *     
     */
    public JndiName getJndiName() {
        return jndiName;
    }

    /**
     * jndiName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link JndiName }
     *     
     */
    public void setJndiName(JndiName value) {
        this.jndiName = value;
    }

    /**
     * localJndiName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalJndiName() {
        return localJndiName;
    }

    /**
     * localJndiName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalJndiName(String value) {
        this.localJndiName = value;
    }

    /**
     * callByValue 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallByValue() {
        return callByValue;
    }

    /**
     * callByValue 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallByValue(String value) {
        this.callByValue = value;
    }

    /**
     * readOnly 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadOnly() {
        return readOnly;
    }

    /**
     * readOnly 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadOnly(String value) {
        this.readOnly = value;
    }

    /**
     * exceptionOnRollback 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionOnRollback() {
        return exceptionOnRollback;
    }

    /**
     * exceptionOnRollback 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionOnRollback(String value) {
        this.exceptionOnRollback = value;
    }

    /**
     * timerPersistence 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimerPersistence() {
        return timerPersistence;
    }

    /**
     * timerPersistence 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimerPersistence(String value) {
        this.timerPersistence = value;
    }

    /**
     * configurationName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigurationName() {
        return configurationName;
    }

    /**
     * configurationName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigurationName(String value) {
        this.configurationName = value;
    }

    /**
     * invokerBindings 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link InvokerBindings }
     *     
     */
    public InvokerBindings getInvokerBindings() {
        return invokerBindings;
    }

    /**
     * invokerBindings 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link InvokerBindings }
     *     
     */
    public void setInvokerBindings(InvokerBindings value) {
        this.invokerBindings = value;
    }

    /**
     * securityProxy 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityProxy() {
        return securityProxy;
    }

    /**
     * securityProxy 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityProxy(String value) {
        this.securityProxy = value;
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
     * {@link ServiceRef }
     * 
     * 
     */
    public List<ServiceRef> getServiceRef() {
        if (serviceRef == null) {
            serviceRef = new ArrayList<ServiceRef>();
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
     * {@link MessageDestinationRef }
     * 
     * 
     */
    public List<MessageDestinationRef> getMessageDestinationRef() {
        if (messageDestinationRef == null) {
            messageDestinationRef = new ArrayList<MessageDestinationRef>();
        }
        return this.messageDestinationRef;
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
     * methodAttributes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MethodAttributes }
     *     
     */
    public MethodAttributes getMethodAttributes() {
        return methodAttributes;
    }

    /**
     * methodAttributes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodAttributes }
     *     
     */
    public void setMethodAttributes(MethodAttributes value) {
        this.methodAttributes = value;
    }

    /**
     * clustered 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClustered() {
        return clustered;
    }

    /**
     * clustered 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClustered(String value) {
        this.clustered = value;
    }

    /**
     * clusterConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ClusterConfig }
     *     
     */
    public ClusterConfig getClusterConfig() {
        return clusterConfig;
    }

    /**
     * clusterConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ClusterConfig }
     *     
     */
    public void setClusterConfig(ClusterConfig value) {
        this.clusterConfig = value;
    }

    /**
     * cacheInvalidation 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheInvalidation() {
        return cacheInvalidation;
    }

    /**
     * cacheInvalidation 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheInvalidation(String value) {
        this.cacheInvalidation = value;
    }

    /**
     * cacheInvalidationConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CacheInvalidationConfig }
     *     
     */
    public CacheInvalidationConfig getCacheInvalidationConfig() {
        return cacheInvalidationConfig;
    }

    /**
     * cacheInvalidationConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CacheInvalidationConfig }
     *     
     */
    public void setCacheInvalidationConfig(CacheInvalidationConfig value) {
        this.cacheInvalidationConfig = value;
    }

    /**
     * Gets the value of the depends property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the depends property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepends().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Depends }
     * 
     * 
     */
    public List<Depends> getDepends() {
        if (depends == null) {
            depends = new ArrayList<Depends>();
        }
        return this.depends;
    }

    /**
     * iorSecurityConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link IorSecurityConfig }
     *     
     */
    public IorSecurityConfig getIorSecurityConfig() {
        return iorSecurityConfig;
    }

    /**
     * iorSecurityConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link IorSecurityConfig }
     *     
     */
    public void setIorSecurityConfig(IorSecurityConfig value) {
        this.iorSecurityConfig = value;
    }

}
