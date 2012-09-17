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
    "destinationQueue",
    "maxTimesRedelivered",
    "timeToLive",
    "dlqUser",
    "dlqPassword"
})
@XmlRootElement(name = "DLQConfig")
public class DLQConfig {

    @XmlElement(name = "DestinationQueue", required = true)
    protected String destinationQueue;
    @XmlElement(name = "MaxTimesRedelivered", required = true)
    protected String maxTimesRedelivered;
    @XmlElement(name = "TimeToLive", required = true)
    protected String timeToLive;
    @XmlElement(name = "DLQUser")
    protected String dlqUser;
    @XmlElement(name = "DLQPassword")
    protected String dlqPassword;

    /**
     * destinationQueue 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationQueue() {
        return destinationQueue;
    }

    /**
     * destinationQueue 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationQueue(String value) {
        this.destinationQueue = value;
    }

    /**
     * maxTimesRedelivered 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxTimesRedelivered() {
        return maxTimesRedelivered;
    }

    /**
     * maxTimesRedelivered 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxTimesRedelivered(String value) {
        this.maxTimesRedelivered = value;
    }

    /**
     * timeToLive 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeToLive() {
        return timeToLive;
    }

    /**
     * timeToLive 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeToLive(String value) {
        this.timeToLive = value;
    }

    /**
     * dlqUser 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDLQUser() {
        return dlqUser;
    }

    /**
     * dlqUser 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDLQUser(String value) {
        this.dlqUser = value;
    }

    /**
     * dlqPassword 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDLQPassword() {
        return dlqPassword;
    }

    /**
     * dlqPassword 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDLQPassword(String value) {
        this.dlqPassword = value;
    }

}
