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
    "entityMappingName",
    "publicId",
    "systemId",
    "entityUri",
    "whenToCache",
    "cacheTimeoutInterval"
})
@XmlRootElement(name = "entity-mapping")
public class EntityMapping {

    @XmlElement(name = "entity-mapping-name", required = true)
    protected String entityMappingName;
    @XmlElement(name = "public-id")
    protected String publicId;
    @XmlElement(name = "system-id")
    protected String systemId;
    @XmlElement(name = "entity-uri")
    protected String entityUri;
    @XmlElement(name = "when-to-cache")
    protected String whenToCache;
    @XmlElement(name = "cache-timeout-interval")
    protected String cacheTimeoutInterval;

    /**
     * entityMappingName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityMappingName() {
        return entityMappingName;
    }

    /**
     * entityMappingName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityMappingName(String value) {
        this.entityMappingName = value;
    }

    /**
     * publicId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublicId() {
        return publicId;
    }

    /**
     * publicId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublicId(String value) {
        this.publicId = value;
    }

    /**
     * systemId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * systemId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemId(String value) {
        this.systemId = value;
    }

    /**
     * entityUri 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityUri() {
        return entityUri;
    }

    /**
     * entityUri 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityUri(String value) {
        this.entityUri = value;
    }

    /**
     * whenToCache 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhenToCache() {
        return whenToCache;
    }

    /**
     * whenToCache 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhenToCache(String value) {
        this.whenToCache = value;
    }

    /**
     * cacheTimeoutInterval 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheTimeoutInterval() {
        return cacheTimeoutInterval;
    }

    /**
     * cacheTimeoutInterval 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheTimeoutInterval(String value) {
        this.cacheTimeoutInterval = value;
    }

}
