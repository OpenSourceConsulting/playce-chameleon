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
    "minCapacity",
    "maxCapacity",
    "removerPeriod",
    "maxBeanLife",
    "overagerPeriod",
    "maxBeanAge",
    "resizerPeriod",
    "maxCacheMissPeriod",
    "minCacheMissPeriod",
    "cacheLoadFactor",
    "flushEnabled"
})
@XmlRootElement(name = "cache-policy-conf")
public class CachePolicyConf {

    @XmlElement(name = "min-capacity")
    protected String minCapacity;
    @XmlElement(name = "max-capacity")
    protected String maxCapacity;
    @XmlElement(name = "remover-period")
    protected String removerPeriod;
    @XmlElement(name = "max-bean-life")
    protected String maxBeanLife;
    @XmlElement(name = "overager-period")
    protected String overagerPeriod;
    @XmlElement(name = "max-bean-age")
    protected String maxBeanAge;
    @XmlElement(name = "resizer-period")
    protected String resizerPeriod;
    @XmlElement(name = "max-cache-miss-period")
    protected String maxCacheMissPeriod;
    @XmlElement(name = "min-cache-miss-period")
    protected String minCacheMissPeriod;
    @XmlElement(name = "cache-load-factor")
    protected String cacheLoadFactor;
    @XmlElement(name = "flush-enabled")
    protected String flushEnabled;

    /**
     * minCapacity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinCapacity() {
        return minCapacity;
    }

    /**
     * minCapacity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinCapacity(String value) {
        this.minCapacity = value;
    }

    /**
     * maxCapacity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * maxCapacity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxCapacity(String value) {
        this.maxCapacity = value;
    }

    /**
     * removerPeriod 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoverPeriod() {
        return removerPeriod;
    }

    /**
     * removerPeriod 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoverPeriod(String value) {
        this.removerPeriod = value;
    }

    /**
     * maxBeanLife 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxBeanLife() {
        return maxBeanLife;
    }

    /**
     * maxBeanLife 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxBeanLife(String value) {
        this.maxBeanLife = value;
    }

    /**
     * overagerPeriod 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOveragerPeriod() {
        return overagerPeriod;
    }

    /**
     * overagerPeriod 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOveragerPeriod(String value) {
        this.overagerPeriod = value;
    }

    /**
     * maxBeanAge 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxBeanAge() {
        return maxBeanAge;
    }

    /**
     * maxBeanAge 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxBeanAge(String value) {
        this.maxBeanAge = value;
    }

    /**
     * resizerPeriod 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResizerPeriod() {
        return resizerPeriod;
    }

    /**
     * resizerPeriod 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResizerPeriod(String value) {
        this.resizerPeriod = value;
    }

    /**
     * maxCacheMissPeriod 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxCacheMissPeriod() {
        return maxCacheMissPeriod;
    }

    /**
     * maxCacheMissPeriod 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxCacheMissPeriod(String value) {
        this.maxCacheMissPeriod = value;
    }

    /**
     * minCacheMissPeriod 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinCacheMissPeriod() {
        return minCacheMissPeriod;
    }

    /**
     * minCacheMissPeriod 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinCacheMissPeriod(String value) {
        this.minCacheMissPeriod = value;
    }

    /**
     * cacheLoadFactor 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheLoadFactor() {
        return cacheLoadFactor;
    }

    /**
     * cacheLoadFactor 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheLoadFactor(String value) {
        this.cacheLoadFactor = value;
    }

    /**
     * flushEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlushEnabled() {
        return flushEnabled;
    }

    /**
     * flushEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlushEnabled(String value) {
        this.flushEnabled = value;
    }

}
