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
    "cacheName",
    "replicationTrigger",
    "replicationGranularity",
    "replicationFieldBatchMode",
    "useJk",
    "maxUnreplicatedInterval",
    "snapshotMode",
    "snapshotInterval",
    "sessionNotificationPolicy"
})
@XmlRootElement(name = "replication-config")
public class ReplicationConfig {

    @XmlElement(name = "cache-name")
    protected String cacheName;
    @XmlElement(name = "replication-trigger")
    protected String replicationTrigger;
    @XmlElement(name = "replication-granularity")
    protected String replicationGranularity;
    @XmlElement(name = "replication-field-batch-mode")
    protected String replicationFieldBatchMode;
    @XmlElement(name = "use-jk")
    protected String useJk;
    @XmlElement(name = "max-unreplicated-interval")
    protected String maxUnreplicatedInterval;
    @XmlElement(name = "snapshot-mode")
    protected String snapshotMode;
    @XmlElement(name = "snapshot-interval")
    protected String snapshotInterval;
    @XmlElement(name = "session-notification-policy")
    protected String sessionNotificationPolicy;

    /**
     * cacheName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCacheName() {
        return cacheName;
    }

    /**
     * cacheName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCacheName(String value) {
        this.cacheName = value;
    }

    /**
     * replicationTrigger 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplicationTrigger() {
        return replicationTrigger;
    }

    /**
     * replicationTrigger 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplicationTrigger(String value) {
        this.replicationTrigger = value;
    }

    /**
     * replicationGranularity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplicationGranularity() {
        return replicationGranularity;
    }

    /**
     * replicationGranularity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplicationGranularity(String value) {
        this.replicationGranularity = value;
    }

    /**
     * replicationFieldBatchMode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplicationFieldBatchMode() {
        return replicationFieldBatchMode;
    }

    /**
     * replicationFieldBatchMode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplicationFieldBatchMode(String value) {
        this.replicationFieldBatchMode = value;
    }

    /**
     * useJk 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseJk() {
        return useJk;
    }

    /**
     * useJk 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseJk(String value) {
        this.useJk = value;
    }

    /**
     * maxUnreplicatedInterval 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxUnreplicatedInterval() {
        return maxUnreplicatedInterval;
    }

    /**
     * maxUnreplicatedInterval 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxUnreplicatedInterval(String value) {
        this.maxUnreplicatedInterval = value;
    }

    /**
     * snapshotMode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnapshotMode() {
        return snapshotMode;
    }

    /**
     * snapshotMode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnapshotMode(String value) {
        this.snapshotMode = value;
    }

    /**
     * snapshotInterval 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnapshotInterval() {
        return snapshotInterval;
    }

    /**
     * snapshotInterval 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnapshotInterval(String value) {
        this.snapshotInterval = value;
    }

    /**
     * sessionNotificationPolicy 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionNotificationPolicy() {
        return sessionNotificationPolicy;
    }

    /**
     * sessionNotificationPolicy 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionNotificationPolicy(String value) {
        this.sessionNotificationPolicy = value;
    }

}
