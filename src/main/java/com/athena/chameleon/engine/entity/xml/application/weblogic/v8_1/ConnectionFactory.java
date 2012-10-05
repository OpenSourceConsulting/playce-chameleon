//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.10.05 시간 10:52:30 AM KST 
//


package com.athena.chameleon.engine.entity.xml.application.weblogic.v8_1;

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
    "factoryName",
    "connectionProperties"
})
@XmlRootElement(name = "connection-factory")
public class ConnectionFactory {

    @XmlElement(name = "factory-name")
    protected String factoryName;
    @XmlElement(name = "connection-properties")
    protected ConnectionProperties connectionProperties;

    /**
     * factoryName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactoryName() {
        return factoryName;
    }

    /**
     * factoryName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactoryName(String value) {
        this.factoryName = value;
    }

    /**
     * connectionProperties 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionProperties }
     *     
     */
    public ConnectionProperties getConnectionProperties() {
        return connectionProperties;
    }

    /**
     * connectionProperties 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionProperties }
     *     
     */
    public void setConnectionProperties(ConnectionProperties value) {
        this.connectionProperties = value;
    }

}
