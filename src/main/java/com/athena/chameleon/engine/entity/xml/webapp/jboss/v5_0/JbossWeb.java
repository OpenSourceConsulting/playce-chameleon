//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 02:04:20 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.jboss.v5_0;

import java.util.ArrayList;
import java.util.List;
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
    "classLoading",
    "securityDomain",
    "jaccStarRoleAllow",
    "contextRoot",
    "virtualHost",
    "useSessionCookies",
    "replicationConfig",
    "resourceEnvRef",
    "ejbRef",
    "ejbLocalRef",
    "serviceRef",
    "resourceRef",
    "messageDestinationRef",
    "securityRole",
    "messageDestination",
    "webserviceDescription",
    "depends",
    "servlet",
    "maxActiveSessions",
    "passivationConfig"
})
@XmlRootElement(name = "jboss-web")
public class JbossWeb {

    @XmlElement(name = "class-loading")
    protected ClassLoading classLoading;
    @XmlElement(name = "security-domain")
    protected SecurityDomain securityDomain;
    @XmlElement(name = "jacc-star-role-allow")
    protected String jaccStarRoleAllow;
    @XmlElement(name = "context-root")
    protected String contextRoot;
    @XmlElement(name = "virtual-host")
    protected List<VirtualHost> virtualHost;
    @XmlElement(name = "use-session-cookies")
    protected String useSessionCookies;
    @XmlElement(name = "replication-config")
    protected ReplicationConfig replicationConfig;
    @XmlElement(name = "resource-env-ref")
    protected List<ResourceEnvRef> resourceEnvRef;
    @XmlElement(name = "ejb-ref")
    protected List<EjbRef> ejbRef;
    @XmlElement(name = "ejb-local-ref")
    protected List<EjbLocalRef> ejbLocalRef;
    @XmlElement(name = "service-ref")
    protected List<ServiceRef> serviceRef;
    @XmlElement(name = "resource-ref")
    protected List<ResourceRef> resourceRef;
    @XmlElement(name = "message-destination-ref")
    protected List<MessageDestinationRef> messageDestinationRef;
    @XmlElement(name = "security-role")
    protected List<SecurityRole> securityRole;
    @XmlElement(name = "message-destination")
    protected List<MessageDestination> messageDestination;
    @XmlElement(name = "webservice-description")
    protected List<WebserviceDescription> webserviceDescription;
    protected List<Depends> depends;
    protected List<Servlet> servlet;
    @XmlElement(name = "max-active-sessions")
    protected String maxActiveSessions;
    @XmlElement(name = "passivation-config")
    protected PassivationConfig passivationConfig;

    /**
     * classLoading 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ClassLoading }
     *     
     */
    public ClassLoading getClassLoading() {
        return classLoading;
    }

    /**
     * classLoading 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassLoading }
     *     
     */
    public void setClassLoading(ClassLoading value) {
        this.classLoading = value;
    }

    /**
     * securityDomain 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SecurityDomain }
     *     
     */
    public SecurityDomain getSecurityDomain() {
        return securityDomain;
    }

    /**
     * securityDomain 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityDomain }
     *     
     */
    public void setSecurityDomain(SecurityDomain value) {
        this.securityDomain = value;
    }

    /**
     * jaccStarRoleAllow 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJaccStarRoleAllow() {
        return jaccStarRoleAllow;
    }

    /**
     * jaccStarRoleAllow 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJaccStarRoleAllow(String value) {
        this.jaccStarRoleAllow = value;
    }

    /**
     * contextRoot 속성의 값을 가져옵니다.
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
     * contextRoot 속성의 값을 설정합니다.
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
     * Gets the value of the virtualHost property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the virtualHost property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVirtualHost().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VirtualHost }
     * 
     * 
     */
    public List<VirtualHost> getVirtualHost() {
        if (virtualHost == null) {
            virtualHost = new ArrayList<VirtualHost>();
        }
        return this.virtualHost;
    }

    /**
     * useSessionCookies 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseSessionCookies() {
        return useSessionCookies;
    }

    /**
     * useSessionCookies 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseSessionCookies(String value) {
        this.useSessionCookies = value;
    }

    /**
     * replicationConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ReplicationConfig }
     *     
     */
    public ReplicationConfig getReplicationConfig() {
        return replicationConfig;
    }

    /**
     * replicationConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ReplicationConfig }
     *     
     */
    public void setReplicationConfig(ReplicationConfig value) {
        this.replicationConfig = value;
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
     * Gets the value of the securityRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securityRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurityRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityRole }
     * 
     * 
     */
    public List<SecurityRole> getSecurityRole() {
        if (securityRole == null) {
            securityRole = new ArrayList<SecurityRole>();
        }
        return this.securityRole;
    }

    /**
     * Gets the value of the messageDestination property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageDestination property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageDestination().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageDestination }
     * 
     * 
     */
    public List<MessageDestination> getMessageDestination() {
        if (messageDestination == null) {
            messageDestination = new ArrayList<MessageDestination>();
        }
        return this.messageDestination;
    }

    /**
     * Gets the value of the webserviceDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the webserviceDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWebserviceDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WebserviceDescription }
     * 
     * 
     */
    public List<WebserviceDescription> getWebserviceDescription() {
        if (webserviceDescription == null) {
            webserviceDescription = new ArrayList<WebserviceDescription>();
        }
        return this.webserviceDescription;
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
     * Gets the value of the servlet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servlet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServlet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Servlet }
     * 
     * 
     */
    public List<Servlet> getServlet() {
        if (servlet == null) {
            servlet = new ArrayList<Servlet>();
        }
        return this.servlet;
    }

    /**
     * maxActiveSessions 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxActiveSessions() {
        return maxActiveSessions;
    }

    /**
     * maxActiveSessions 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxActiveSessions(String value) {
        this.maxActiveSessions = value;
    }

    /**
     * passivationConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PassivationConfig }
     *     
     */
    public PassivationConfig getPassivationConfig() {
        return passivationConfig;
    }

    /**
     * passivationConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PassivationConfig }
     *     
     */
    public void setPassivationConfig(PassivationConfig value) {
        this.passivationConfig = value;
    }

}
