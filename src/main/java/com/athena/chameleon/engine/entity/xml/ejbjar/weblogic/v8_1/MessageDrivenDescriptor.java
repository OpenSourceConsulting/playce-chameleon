//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.12 시간 04:26:39 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1;

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
    "pool",
    "destinationJndiName",
    "initialContextFactory",
    "providerUrl",
    "connectionFactoryJndiName",
    "jmsPollingIntervalSeconds",
    "jmsClientId"
})
@XmlRootElement(name = "message-driven-descriptor")
public class MessageDrivenDescriptor {

    protected Pool pool;
    @XmlElement(name = "destination-jndi-name")
    protected String destinationJndiName;
    @XmlElement(name = "initial-context-factory")
    protected String initialContextFactory;
    @XmlElement(name = "provider-url")
    protected String providerUrl;
    @XmlElement(name = "connection-factory-jndi-name")
    protected String connectionFactoryJndiName;
    @XmlElement(name = "jms-polling-interval-seconds")
    protected String jmsPollingIntervalSeconds;
    @XmlElement(name = "jms-client-id")
    protected String jmsClientId;

    /**
     * pool 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Pool }
     *     
     */
    public Pool getPool() {
        return pool;
    }

    /**
     * pool 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Pool }
     *     
     */
    public void setPool(Pool value) {
        this.pool = value;
    }

    /**
     * destinationJndiName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationJndiName() {
        return destinationJndiName;
    }

    /**
     * destinationJndiName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationJndiName(String value) {
        this.destinationJndiName = value;
    }

    /**
     * initialContextFactory 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialContextFactory() {
        return initialContextFactory;
    }

    /**
     * initialContextFactory 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialContextFactory(String value) {
        this.initialContextFactory = value;
    }

    /**
     * providerUrl 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderUrl() {
        return providerUrl;
    }

    /**
     * providerUrl 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderUrl(String value) {
        this.providerUrl = value;
    }

    /**
     * connectionFactoryJndiName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionFactoryJndiName() {
        return connectionFactoryJndiName;
    }

    /**
     * connectionFactoryJndiName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionFactoryJndiName(String value) {
        this.connectionFactoryJndiName = value;
    }

    /**
     * jmsPollingIntervalSeconds 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmsPollingIntervalSeconds() {
        return jmsPollingIntervalSeconds;
    }

    /**
     * jmsPollingIntervalSeconds 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmsPollingIntervalSeconds(String value) {
        this.jmsPollingIntervalSeconds = value;
    }

    /**
     * jmsClientId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmsClientId() {
        return jmsClientId;
    }

    /**
     * jmsClientId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmsClientId(String value) {
        this.jmsClientId = value;
    }

}
