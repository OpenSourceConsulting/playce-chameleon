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
    "clientInterceptors",
    "endpointInterceptors",
    "webClassLoader",
    "activationConfig",
    "jmsProviderAdapterJNDI",
    "serverSessionPoolFactoryJNDI",
    "createJBossMQDestination",
    "minimumSize",
    "maximumSize",
    "keepAliveMillis",
    "maxMessages",
    "mdbConfig",
    "poa",
    "registerEjbsInJnpContext",
    "jnpContext",
    "interfaceRepositorySupported"
})
@XmlRootElement(name = "proxy-factory-config")
public class ProxyFactoryConfig {

    @XmlElement(name = "client-interceptors")
    protected ClientInterceptors clientInterceptors;
    @XmlElement(name = "endpoint-interceptors")
    protected EndpointInterceptors endpointInterceptors;
    @XmlElement(name = "web-class-loader")
    protected String webClassLoader;
    @XmlElement(name = "activation-config")
    protected ActivationConfig activationConfig;
    @XmlElement(name = "JMSProviderAdapterJNDI")
    protected String jmsProviderAdapterJNDI;
    @XmlElement(name = "ServerSessionPoolFactoryJNDI")
    protected String serverSessionPoolFactoryJNDI;
    @XmlElement(name = "CreateJBossMQDestination")
    protected String createJBossMQDestination;
    @XmlElement(name = "MinimumSize")
    protected MinimumSize minimumSize;
    @XmlElement(name = "MaximumSize")
    protected MaximumSize maximumSize;
    @XmlElement(name = "KeepAliveMillis")
    protected String keepAliveMillis;
    @XmlElement(name = "MaxMessages")
    protected String maxMessages;
    @XmlElement(name = "MDBConfig")
    protected MDBConfig mdbConfig;
    protected String poa;
    @XmlElement(name = "register-ejbs-in-jnp-context")
    protected String registerEjbsInJnpContext;
    @XmlElement(name = "jnp-context")
    protected String jnpContext;
    @XmlElement(name = "interface-repository-supported")
    protected String interfaceRepositorySupported;

    /**
     * clientInterceptors 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ClientInterceptors }
     *     
     */
    public ClientInterceptors getClientInterceptors() {
        return clientInterceptors;
    }

    /**
     * clientInterceptors 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientInterceptors }
     *     
     */
    public void setClientInterceptors(ClientInterceptors value) {
        this.clientInterceptors = value;
    }

    /**
     * endpointInterceptors 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EndpointInterceptors }
     *     
     */
    public EndpointInterceptors getEndpointInterceptors() {
        return endpointInterceptors;
    }

    /**
     * endpointInterceptors 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointInterceptors }
     *     
     */
    public void setEndpointInterceptors(EndpointInterceptors value) {
        this.endpointInterceptors = value;
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
     * activationConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ActivationConfig }
     *     
     */
    public ActivationConfig getActivationConfig() {
        return activationConfig;
    }

    /**
     * activationConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivationConfig }
     *     
     */
    public void setActivationConfig(ActivationConfig value) {
        this.activationConfig = value;
    }

    /**
     * jmsProviderAdapterJNDI 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJMSProviderAdapterJNDI() {
        return jmsProviderAdapterJNDI;
    }

    /**
     * jmsProviderAdapterJNDI 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJMSProviderAdapterJNDI(String value) {
        this.jmsProviderAdapterJNDI = value;
    }

    /**
     * serverSessionPoolFactoryJNDI 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerSessionPoolFactoryJNDI() {
        return serverSessionPoolFactoryJNDI;
    }

    /**
     * serverSessionPoolFactoryJNDI 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerSessionPoolFactoryJNDI(String value) {
        this.serverSessionPoolFactoryJNDI = value;
    }

    /**
     * createJBossMQDestination 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateJBossMQDestination() {
        return createJBossMQDestination;
    }

    /**
     * createJBossMQDestination 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateJBossMQDestination(String value) {
        this.createJBossMQDestination = value;
    }

    /**
     * minimumSize 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MinimumSize }
     *     
     */
    public MinimumSize getMinimumSize() {
        return minimumSize;
    }

    /**
     * minimumSize 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MinimumSize }
     *     
     */
    public void setMinimumSize(MinimumSize value) {
        this.minimumSize = value;
    }

    /**
     * maximumSize 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MaximumSize }
     *     
     */
    public MaximumSize getMaximumSize() {
        return maximumSize;
    }

    /**
     * maximumSize 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MaximumSize }
     *     
     */
    public void setMaximumSize(MaximumSize value) {
        this.maximumSize = value;
    }

    /**
     * keepAliveMillis 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeepAliveMillis() {
        return keepAliveMillis;
    }

    /**
     * keepAliveMillis 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeepAliveMillis(String value) {
        this.keepAliveMillis = value;
    }

    /**
     * maxMessages 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxMessages() {
        return maxMessages;
    }

    /**
     * maxMessages 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxMessages(String value) {
        this.maxMessages = value;
    }

    /**
     * mdbConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MDBConfig }
     *     
     */
    public MDBConfig getMDBConfig() {
        return mdbConfig;
    }

    /**
     * mdbConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MDBConfig }
     *     
     */
    public void setMDBConfig(MDBConfig value) {
        this.mdbConfig = value;
    }

    /**
     * poa 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoa() {
        return poa;
    }

    /**
     * poa 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoa(String value) {
        this.poa = value;
    }

    /**
     * registerEjbsInJnpContext 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisterEjbsInJnpContext() {
        return registerEjbsInJnpContext;
    }

    /**
     * registerEjbsInJnpContext 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisterEjbsInJnpContext(String value) {
        this.registerEjbsInJnpContext = value;
    }

    /**
     * jnpContext 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJnpContext() {
        return jnpContext;
    }

    /**
     * jnpContext 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJnpContext(String value) {
        this.jnpContext = value;
    }

    /**
     * interfaceRepositorySupported 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterfaceRepositorySupported() {
        return interfaceRepositorySupported;
    }

    /**
     * interfaceRepositorySupported 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterfaceRepositorySupported(String value) {
        this.interfaceRepositorySupported = value;
    }

}
