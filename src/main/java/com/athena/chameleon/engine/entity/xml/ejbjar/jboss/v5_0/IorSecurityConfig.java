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
    "transportConfig",
    "asContext",
    "sasContext"
})
@XmlRootElement(name = "ior-security-config")
public class IorSecurityConfig {

    @XmlElement(name = "transport-config")
    protected TransportConfig transportConfig;
    @XmlElement(name = "as-context")
    protected AsContext asContext;
    @XmlElement(name = "sas-context")
    protected SasContext sasContext;

    /**
     * transportConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link TransportConfig }
     *     
     */
    public TransportConfig getTransportConfig() {
        return transportConfig;
    }

    /**
     * transportConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportConfig }
     *     
     */
    public void setTransportConfig(TransportConfig value) {
        this.transportConfig = value;
    }

    /**
     * asContext 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AsContext }
     *     
     */
    public AsContext getAsContext() {
        return asContext;
    }

    /**
     * asContext 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AsContext }
     *     
     */
    public void setAsContext(AsContext value) {
        this.asContext = value;
    }

    /**
     * sasContext 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SasContext }
     *     
     */
    public SasContext getSasContext() {
        return sasContext;
    }

    /**
     * sasContext 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SasContext }
     *     
     */
    public void setSasContext(SasContext value) {
        this.sasContext = value;
    }

}
