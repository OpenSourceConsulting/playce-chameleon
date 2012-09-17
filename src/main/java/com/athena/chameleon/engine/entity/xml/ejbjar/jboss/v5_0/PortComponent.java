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
    "portComponentName",
    "portComponentUri",
    "authMethod",
    "transportGuarantee"
})
@XmlRootElement(name = "port-component")
public class PortComponent {

    @XmlElement(name = "port-component-name", required = true)
    protected String portComponentName;
    @XmlElement(name = "port-component-uri")
    protected String portComponentUri;
    @XmlElement(name = "auth-method")
    protected String authMethod;
    @XmlElement(name = "transport-guarantee")
    protected String transportGuarantee;

    /**
     * portComponentName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortComponentName() {
        return portComponentName;
    }

    /**
     * portComponentName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortComponentName(String value) {
        this.portComponentName = value;
    }

    /**
     * portComponentUri 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortComponentUri() {
        return portComponentUri;
    }

    /**
     * portComponentUri 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortComponentUri(String value) {
        this.portComponentUri = value;
    }

    /**
     * authMethod 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthMethod() {
        return authMethod;
    }

    /**
     * authMethod 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthMethod(String value) {
        this.authMethod = value;
    }

    /**
     * transportGuarantee 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportGuarantee() {
        return transportGuarantee;
    }

    /**
     * transportGuarantee 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportGuarantee(String value) {
        this.transportGuarantee = value;
    }

}
