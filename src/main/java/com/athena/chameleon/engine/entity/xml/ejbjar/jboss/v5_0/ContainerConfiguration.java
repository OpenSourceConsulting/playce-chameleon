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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "containerName",
    "callLogging",
    "invokerProxyBindingName",
    "syncOnCommitOnly",
    "insertAfterEjbPostCreate",
    "callEjbStoreOnClean",
    "storeNotFlushed",
    "containerInterceptors",
    "instancePool",
    "instanceCache",
    "persistenceManager",
    "webClassLoader",
    "lockingPolicy",
    "containerCacheConf",
    "containerPoolConf",
    "commitOption",
    "optiondRefreshRate",
    "securityDomain",
    "clusterConfig",
    "depends"
})
@XmlRootElement(name = "container-configuration")
public class ContainerConfiguration {

    @XmlAttribute(name = "extends")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String _extends;
    @XmlElement(name = "container-name", required = true)
    protected String containerName;
    @XmlElement(name = "call-logging")
    protected String callLogging;
    @XmlElement(name = "invoker-proxy-binding-name")
    protected List<InvokerProxyBindingName> invokerProxyBindingName;
    @XmlElement(name = "sync-on-commit-only")
    protected String syncOnCommitOnly;
    @XmlElement(name = "insert-after-ejb-post-create")
    protected String insertAfterEjbPostCreate;
    @XmlElement(name = "call-ejb-store-on-clean")
    protected String callEjbStoreOnClean;
    @XmlElement(name = "store-not-flushed")
    protected String storeNotFlushed;
    @XmlElement(name = "container-interceptors")
    protected ContainerInterceptors containerInterceptors;
    @XmlElement(name = "instance-pool")
    protected String instancePool;
    @XmlElement(name = "instance-cache")
    protected String instanceCache;
    @XmlElement(name = "persistence-manager")
    protected String persistenceManager;
    @XmlElement(name = "web-class-loader")
    protected String webClassLoader;
    @XmlElement(name = "locking-policy")
    protected String lockingPolicy;
    @XmlElement(name = "container-cache-conf")
    protected ContainerCacheConf containerCacheConf;
    @XmlElement(name = "container-pool-conf")
    protected ContainerPoolConf containerPoolConf;
    @XmlElement(name = "commit-option")
    protected String commitOption;
    @XmlElement(name = "optiond-refresh-rate")
    protected String optiondRefreshRate;
    @XmlElement(name = "security-domain")
    protected String securityDomain;
    @XmlElement(name = "cluster-config")
    protected ClusterConfig clusterConfig;
    protected List<Depends> depends;

    /**
     * extends 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtends() {
        return _extends;
    }

    /**
     * extends 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtends(String value) {
        this._extends = value;
    }

    /**
     * containerName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerName() {
        return containerName;
    }

    /**
     * containerName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerName(String value) {
        this.containerName = value;
    }

    /**
     * callLogging 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallLogging() {
        return callLogging;
    }

    /**
     * callLogging 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallLogging(String value) {
        this.callLogging = value;
    }

    /**
     * Gets the value of the invokerProxyBindingName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invokerProxyBindingName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvokerProxyBindingName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvokerProxyBindingName }
     * 
     * 
     */
    public List<InvokerProxyBindingName> getInvokerProxyBindingName() {
        if (invokerProxyBindingName == null) {
            invokerProxyBindingName = new ArrayList<InvokerProxyBindingName>();
        }
        return this.invokerProxyBindingName;
    }

    /**
     * syncOnCommitOnly 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSyncOnCommitOnly() {
        return syncOnCommitOnly;
    }

    /**
     * syncOnCommitOnly 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSyncOnCommitOnly(String value) {
        this.syncOnCommitOnly = value;
    }

    /**
     * insertAfterEjbPostCreate 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsertAfterEjbPostCreate() {
        return insertAfterEjbPostCreate;
    }

    /**
     * insertAfterEjbPostCreate 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsertAfterEjbPostCreate(String value) {
        this.insertAfterEjbPostCreate = value;
    }

    /**
     * callEjbStoreOnClean 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallEjbStoreOnClean() {
        return callEjbStoreOnClean;
    }

    /**
     * callEjbStoreOnClean 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallEjbStoreOnClean(String value) {
        this.callEjbStoreOnClean = value;
    }

    /**
     * storeNotFlushed 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreNotFlushed() {
        return storeNotFlushed;
    }

    /**
     * storeNotFlushed 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreNotFlushed(String value) {
        this.storeNotFlushed = value;
    }

    /**
     * containerInterceptors 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ContainerInterceptors }
     *     
     */
    public ContainerInterceptors getContainerInterceptors() {
        return containerInterceptors;
    }

    /**
     * containerInterceptors 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerInterceptors }
     *     
     */
    public void setContainerInterceptors(ContainerInterceptors value) {
        this.containerInterceptors = value;
    }

    /**
     * instancePool 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstancePool() {
        return instancePool;
    }

    /**
     * instancePool 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstancePool(String value) {
        this.instancePool = value;
    }

    /**
     * instanceCache 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceCache() {
        return instanceCache;
    }

    /**
     * instanceCache 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceCache(String value) {
        this.instanceCache = value;
    }

    /**
     * persistenceManager 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersistenceManager() {
        return persistenceManager;
    }

    /**
     * persistenceManager 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersistenceManager(String value) {
        this.persistenceManager = value;
    }

    /**
     * webClassLoader 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebClassLoader() {
        return webClassLoader;
    }

    /**
     * webClassLoader 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebClassLoader(String value) {
        this.webClassLoader = value;
    }

    /**
     * lockingPolicy 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockingPolicy() {
        return lockingPolicy;
    }

    /**
     * lockingPolicy 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockingPolicy(String value) {
        this.lockingPolicy = value;
    }

    /**
     * containerCacheConf 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ContainerCacheConf }
     *     
     */
    public ContainerCacheConf getContainerCacheConf() {
        return containerCacheConf;
    }

    /**
     * containerCacheConf 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerCacheConf }
     *     
     */
    public void setContainerCacheConf(ContainerCacheConf value) {
        this.containerCacheConf = value;
    }

    /**
     * containerPoolConf 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ContainerPoolConf }
     *     
     */
    public ContainerPoolConf getContainerPoolConf() {
        return containerPoolConf;
    }

    /**
     * containerPoolConf 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerPoolConf }
     *     
     */
    public void setContainerPoolConf(ContainerPoolConf value) {
        this.containerPoolConf = value;
    }

    /**
     * commitOption 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitOption() {
        return commitOption;
    }

    /**
     * commitOption 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitOption(String value) {
        this.commitOption = value;
    }

    /**
     * optiondRefreshRate 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOptiondRefreshRate() {
        return optiondRefreshRate;
    }

    /**
     * optiondRefreshRate 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptiondRefreshRate(String value) {
        this.optiondRefreshRate = value;
    }

    /**
     * securityDomain 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityDomain() {
        return securityDomain;
    }

    /**
     * securityDomain 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityDomain(String value) {
        this.securityDomain = value;
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

}
