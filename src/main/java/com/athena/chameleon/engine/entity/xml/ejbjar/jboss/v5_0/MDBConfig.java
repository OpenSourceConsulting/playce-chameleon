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
    "reconnectIntervalSec",
    "deliveryActive",
    "dlqConfig"
})
@XmlRootElement(name = "MDBConfig")
public class MDBConfig {

    @XmlElement(name = "ReconnectIntervalSec", required = true)
    protected String reconnectIntervalSec;
    @XmlElement(name = "DeliveryActive")
    protected String deliveryActive;
    @XmlElement(name = "DLQConfig")
    protected DLQConfig dlqConfig;

    /**
     * reconnectIntervalSec 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReconnectIntervalSec() {
        return reconnectIntervalSec;
    }

    /**
     * reconnectIntervalSec 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReconnectIntervalSec(String value) {
        this.reconnectIntervalSec = value;
    }

    /**
     * deliveryActive 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryActive() {
        return deliveryActive;
    }

    /**
     * deliveryActive 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryActive(String value) {
        this.deliveryActive = value;
    }

    /**
     * dlqConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link DLQConfig }
     *     
     */
    public DLQConfig getDLQConfig() {
        return dlqConfig;
    }

    /**
     * dlqConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link DLQConfig }
     *     
     */
    public void setDLQConfig(DLQConfig value) {
        this.dlqConfig = value;
    }

}
