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
    "integrity",
    "confidentiality",
    "establishTrustInTarget",
    "establishTrustInClient",
    "detectMisordering",
    "detectReplay"
})
@XmlRootElement(name = "transport-config")
public class TransportConfig {

    @XmlElement(required = true)
    protected String integrity;
    @XmlElement(required = true)
    protected String confidentiality;
    @XmlElement(name = "establish-trust-in-target", required = true)
    protected String establishTrustInTarget;
    @XmlElement(name = "establish-trust-in-client", required = true)
    protected String establishTrustInClient;
    @XmlElement(name = "detect-misordering")
    protected String detectMisordering;
    @XmlElement(name = "detect-replay")
    protected String detectReplay;

    /**
     * integrity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntegrity() {
        return integrity;
    }

    /**
     * integrity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntegrity(String value) {
        this.integrity = value;
    }

    /**
     * confidentiality 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfidentiality() {
        return confidentiality;
    }

    /**
     * confidentiality 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfidentiality(String value) {
        this.confidentiality = value;
    }

    /**
     * establishTrustInTarget 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstablishTrustInTarget() {
        return establishTrustInTarget;
    }

    /**
     * establishTrustInTarget 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstablishTrustInTarget(String value) {
        this.establishTrustInTarget = value;
    }

    /**
     * establishTrustInClient 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstablishTrustInClient() {
        return establishTrustInClient;
    }

    /**
     * establishTrustInClient 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstablishTrustInClient(String value) {
        this.establishTrustInClient = value;
    }

    /**
     * detectMisordering 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetectMisordering() {
        return detectMisordering;
    }

    /**
     * detectMisordering 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetectMisordering(String value) {
        this.detectMisordering = value;
    }

    /**
     * detectReplay 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetectReplay() {
        return detectReplay;
    }

    /**
     * detectReplay 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetectReplay(String value) {
        this.detectReplay = value;
    }

}
