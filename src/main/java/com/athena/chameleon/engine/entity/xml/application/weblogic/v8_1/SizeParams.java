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
    "initialCapacity",
    "maxCapacity",
    "capacityIncrement",
    "shrinkingEnabled",
    "shrinkPeriodMinutes",
    "shrinkFrequencySeconds",
    "highestNumWaiters",
    "highestNumUnavailable"
})
@XmlRootElement(name = "size-params")
public class SizeParams {

    @XmlElement(name = "initial-capacity")
    protected String initialCapacity;
    @XmlElement(name = "max-capacity")
    protected String maxCapacity;
    @XmlElement(name = "capacity-increment")
    protected String capacityIncrement;
    @XmlElement(name = "shrinking-enabled")
    protected String shrinkingEnabled;
    @XmlElement(name = "shrink-period-minutes")
    protected String shrinkPeriodMinutes;
    @XmlElement(name = "shrink-frequency-seconds")
    protected String shrinkFrequencySeconds;
    @XmlElement(name = "highest-num-waiters")
    protected String highestNumWaiters;
    @XmlElement(name = "highest-num-unavailable")
    protected String highestNumUnavailable;

    /**
     * initialCapacity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialCapacity() {
        return initialCapacity;
    }

    /**
     * initialCapacity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialCapacity(String value) {
        this.initialCapacity = value;
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
     * capacityIncrement 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapacityIncrement() {
        return capacityIncrement;
    }

    /**
     * capacityIncrement 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapacityIncrement(String value) {
        this.capacityIncrement = value;
    }

    /**
     * shrinkingEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShrinkingEnabled() {
        return shrinkingEnabled;
    }

    /**
     * shrinkingEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShrinkingEnabled(String value) {
        this.shrinkingEnabled = value;
    }

    /**
     * shrinkPeriodMinutes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShrinkPeriodMinutes() {
        return shrinkPeriodMinutes;
    }

    /**
     * shrinkPeriodMinutes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShrinkPeriodMinutes(String value) {
        this.shrinkPeriodMinutes = value;
    }

    /**
     * shrinkFrequencySeconds 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShrinkFrequencySeconds() {
        return shrinkFrequencySeconds;
    }

    /**
     * shrinkFrequencySeconds 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShrinkFrequencySeconds(String value) {
        this.shrinkFrequencySeconds = value;
    }

    /**
     * highestNumWaiters 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHighestNumWaiters() {
        return highestNumWaiters;
    }

    /**
     * highestNumWaiters 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHighestNumWaiters(String value) {
        this.highestNumWaiters = value;
    }

    /**
     * highestNumUnavailable 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHighestNumUnavailable() {
        return highestNumUnavailable;
    }

    /**
     * highestNumUnavailable 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHighestNumUnavailable(String value) {
        this.highestNumUnavailable = value;
    }

}
