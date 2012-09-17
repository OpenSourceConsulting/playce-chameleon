//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 02:04:20 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.jboss.v5_0;

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
    "webserviceDescriptionName",
    "configName",
    "configFile",
    "wsdlPublishLocation"
})
@XmlRootElement(name = "webservice-description")
public class WebserviceDescription {

    @XmlElement(name = "webservice-description-name", required = true)
    protected String webserviceDescriptionName;
    @XmlElement(name = "config-name")
    protected String configName;
    @XmlElement(name = "config-file")
    protected String configFile;
    @XmlElement(name = "wsdl-publish-location")
    protected String wsdlPublishLocation;

    /**
     * webserviceDescriptionName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebserviceDescriptionName() {
        return webserviceDescriptionName;
    }

    /**
     * webserviceDescriptionName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebserviceDescriptionName(String value) {
        this.webserviceDescriptionName = value;
    }

    /**
     * configName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * configName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigName(String value) {
        this.configName = value;
    }

    /**
     * configFile 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigFile() {
        return configFile;
    }

    /**
     * configFile 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigFile(String value) {
        this.configFile = value;
    }

    /**
     * wsdlPublishLocation 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlPublishLocation() {
        return wsdlPublishLocation;
    }

    /**
     * wsdlPublishLocation 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlPublishLocation(String value) {
        this.wsdlPublishLocation = value;
    }

}
