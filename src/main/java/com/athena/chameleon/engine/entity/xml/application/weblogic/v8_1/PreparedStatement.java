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
    "profilingEnabled",
    "cacheProfilingThreshold",
    "cacheSize",
    "parameterLoggingEnabled",
    "maxParameterLength",
    "cacheType"
})
@XmlRootElement(name = "prepared-statement")
public class PreparedStatement {

    @XmlElement(name = "profiling-enabled")
    protected String profilingEnabled;
    @XmlElement(name = "cache-profiling-threshold")
    protected String cacheProfilingThreshold;
    @XmlElement(name = "cache-size")
    protected String cacheSize;
    @XmlElement(name = "parameter-logging-enabled")
    protected String parameterLoggingEnabled;
    @XmlElement(name = "max-parameter-length")
    protected String maxParameterLength;
    @XmlElement(name = "cache-type")
    protected String cacheType;

    /**
     * profilingEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfilingEnabled() {
        return profilingEnabled;
    }

    /**
     * profilingEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfilingEnabled(String value) {
        this.profilingEnabled = value;
    }

    /**
     * cacheProfilingThreshold 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheProfilingThreshold() {
        return cacheProfilingThreshold;
    }

    /**
     * cacheProfilingThreshold 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheProfilingThreshold(String value) {
        this.cacheProfilingThreshold = value;
    }

    /**
     * cacheSize 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheSize() {
        return cacheSize;
    }

    /**
     * cacheSize 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheSize(String value) {
        this.cacheSize = value;
    }

    /**
     * parameterLoggingEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterLoggingEnabled() {
        return parameterLoggingEnabled;
    }

    /**
     * parameterLoggingEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterLoggingEnabled(String value) {
        this.parameterLoggingEnabled = value;
    }

    /**
     * maxParameterLength 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxParameterLength() {
        return maxParameterLength;
    }

    /**
     * maxParameterLength 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxParameterLength(String value) {
        this.maxParameterLength = value;
    }

    /**
     * cacheType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheType() {
        return cacheType;
    }

    /**
     * cacheType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheType(String value) {
        this.cacheType = value;
    }

}
