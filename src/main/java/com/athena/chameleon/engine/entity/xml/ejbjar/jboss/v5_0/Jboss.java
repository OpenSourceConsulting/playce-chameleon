//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 02:02:31 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "loaderRepository",
    "jmxName",
    "enforceEjbRestrictions",
    "securityDomain",
    "missingMethodPermissionsExcludedMode",
    "unauthenticatedPrincipal",
    "exceptionOnRollback",
    "webservices",
    "enterpriseBeans",
    "assemblyDescriptor",
    "resourceManagers",
    "invokerProxyBindings",
    "containerConfigurations"
})
@XmlRootElement(name = "jboss")
public class Jboss {

    @XmlElement(name = "loader-repository")
    protected LoaderRepository loaderRepository;
    @XmlElement(name = "jmx-name")
    protected String jmxName;
    @XmlElement(name = "enforce-ejb-restrictions")
    protected String enforceEjbRestrictions;
    @XmlElement(name = "security-domain")
    protected String securityDomain;
    @XmlElement(name = "missing-method-permissions-excluded-mode")
    protected String missingMethodPermissionsExcludedMode;
    @XmlElement(name = "unauthenticated-principal")
    protected String unauthenticatedPrincipal;
    @XmlElement(name = "exception-on-rollback")
    protected String exceptionOnRollback;
    protected Webservices webservices;
    @XmlElement(name = "enterprise-beans")
    protected EnterpriseBeans enterpriseBeans;
    @XmlElement(name = "assembly-descriptor")
    protected AssemblyDescriptor assemblyDescriptor;
    @XmlElement(name = "resource-managers")
    protected ResourceManagers resourceManagers;
    @XmlElement(name = "invoker-proxy-bindings")
    protected InvokerProxyBindings invokerProxyBindings;
    @XmlElement(name = "container-configurations")
    protected ContainerConfigurations containerConfigurations;

    /**
     * loaderRepository 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link LoaderRepository }
     *     
     */
    public LoaderRepository getLoaderRepository() {
        return loaderRepository;
    }

    /**
     * loaderRepository 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link LoaderRepository }
     *     
     */
    public void setLoaderRepository(LoaderRepository value) {
        this.loaderRepository = value;
    }

    /**
     * jmxName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmxName() {
        return jmxName;
    }

    /**
     * jmxName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmxName(String value) {
        this.jmxName = value;
    }

    /**
     * enforceEjbRestrictions 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnforceEjbRestrictions() {
        return enforceEjbRestrictions;
    }

    /**
     * enforceEjbRestrictions 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnforceEjbRestrictions(String value) {
        this.enforceEjbRestrictions = value;
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
     * missingMethodPermissionsExcludedMode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMissingMethodPermissionsExcludedMode() {
        return missingMethodPermissionsExcludedMode;
    }

    /**
     * missingMethodPermissionsExcludedMode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMissingMethodPermissionsExcludedMode(String value) {
        this.missingMethodPermissionsExcludedMode = value;
    }

    /**
     * unauthenticatedPrincipal 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnauthenticatedPrincipal() {
        return unauthenticatedPrincipal;
    }

    /**
     * unauthenticatedPrincipal 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnauthenticatedPrincipal(String value) {
        this.unauthenticatedPrincipal = value;
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
     * webservices 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Webservices }
     *     
     */
    public Webservices getWebservices() {
        return webservices;
    }

    /**
     * webservices 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Webservices }
     *     
     */
    public void setWebservices(Webservices value) {
        this.webservices = value;
    }

    /**
     * enterpriseBeans 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EnterpriseBeans }
     *     
     */
    public EnterpriseBeans getEnterpriseBeans() {
        return enterpriseBeans;
    }

    /**
     * enterpriseBeans 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EnterpriseBeans }
     *     
     */
    public void setEnterpriseBeans(EnterpriseBeans value) {
        this.enterpriseBeans = value;
    }

    /**
     * assemblyDescriptor 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AssemblyDescriptor }
     *     
     */
    public AssemblyDescriptor getAssemblyDescriptor() {
        return assemblyDescriptor;
    }

    /**
     * assemblyDescriptor 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AssemblyDescriptor }
     *     
     */
    public void setAssemblyDescriptor(AssemblyDescriptor value) {
        this.assemblyDescriptor = value;
    }

    /**
     * resourceManagers 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ResourceManagers }
     *     
     */
    public ResourceManagers getResourceManagers() {
        return resourceManagers;
    }

    /**
     * resourceManagers 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceManagers }
     *     
     */
    public void setResourceManagers(ResourceManagers value) {
        this.resourceManagers = value;
    }

    /**
     * invokerProxyBindings 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link InvokerProxyBindings }
     *     
     */
    public InvokerProxyBindings getInvokerProxyBindings() {
        return invokerProxyBindings;
    }

    /**
     * invokerProxyBindings 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link InvokerProxyBindings }
     *     
     */
    public void setInvokerProxyBindings(InvokerProxyBindings value) {
        this.invokerProxyBindings = value;
    }

    /**
     * containerConfigurations 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ContainerConfigurations }
     *     
     */
    public ContainerConfigurations getContainerConfigurations() {
        return containerConfigurations;
    }

    /**
     * containerConfigurations 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerConfigurations }
     *     
     */
    public void setContainerConfigurations(ContainerConfigurations value) {
        this.containerConfigurations = value;
    }

}
