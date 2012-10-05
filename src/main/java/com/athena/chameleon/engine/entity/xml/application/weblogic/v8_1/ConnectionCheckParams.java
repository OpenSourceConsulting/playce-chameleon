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
    "tableName",
    "checkOnReserveEnabled",
    "checkOnReleaseEnabled",
    "refreshMinutes",
    "checkOnCreateEnabled",
    "connectionReserveTimeoutSeconds",
    "connectionCreationRetryFrequencySeconds",
    "inactiveConnectionTimeoutSeconds",
    "testFrequencySeconds",
    "initSql"
})
@XmlRootElement(name = "connection-check-params")
public class ConnectionCheckParams {

    @XmlElement(name = "table-name")
    protected String tableName;
    @XmlElement(name = "check-on-reserve-enabled")
    protected String checkOnReserveEnabled;
    @XmlElement(name = "check-on-release-enabled")
    protected String checkOnReleaseEnabled;
    @XmlElement(name = "refresh-minutes")
    protected String refreshMinutes;
    @XmlElement(name = "check-on-create-enabled")
    protected String checkOnCreateEnabled;
    @XmlElement(name = "connection-reserve-timeout-seconds")
    protected String connectionReserveTimeoutSeconds;
    @XmlElement(name = "connection-creation-retry-frequency-seconds")
    protected String connectionCreationRetryFrequencySeconds;
    @XmlElement(name = "inactive-connection-timeout-seconds")
    protected String inactiveConnectionTimeoutSeconds;
    @XmlElement(name = "test-frequency-seconds")
    protected String testFrequencySeconds;
    @XmlElement(name = "init-sql")
    protected String initSql;

    /**
     * tableName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * tableName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

    /**
     * checkOnReserveEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckOnReserveEnabled() {
        return checkOnReserveEnabled;
    }

    /**
     * checkOnReserveEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckOnReserveEnabled(String value) {
        this.checkOnReserveEnabled = value;
    }

    /**
     * checkOnReleaseEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckOnReleaseEnabled() {
        return checkOnReleaseEnabled;
    }

    /**
     * checkOnReleaseEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckOnReleaseEnabled(String value) {
        this.checkOnReleaseEnabled = value;
    }

    /**
     * refreshMinutes 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefreshMinutes() {
        return refreshMinutes;
    }

    /**
     * refreshMinutes 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefreshMinutes(String value) {
        this.refreshMinutes = value;
    }

    /**
     * checkOnCreateEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckOnCreateEnabled() {
        return checkOnCreateEnabled;
    }

    /**
     * checkOnCreateEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckOnCreateEnabled(String value) {
        this.checkOnCreateEnabled = value;
    }

    /**
     * connectionReserveTimeoutSeconds 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionReserveTimeoutSeconds() {
        return connectionReserveTimeoutSeconds;
    }

    /**
     * connectionReserveTimeoutSeconds 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionReserveTimeoutSeconds(String value) {
        this.connectionReserveTimeoutSeconds = value;
    }

    /**
     * connectionCreationRetryFrequencySeconds 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionCreationRetryFrequencySeconds() {
        return connectionCreationRetryFrequencySeconds;
    }

    /**
     * connectionCreationRetryFrequencySeconds 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionCreationRetryFrequencySeconds(String value) {
        this.connectionCreationRetryFrequencySeconds = value;
    }

    /**
     * inactiveConnectionTimeoutSeconds 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInactiveConnectionTimeoutSeconds() {
        return inactiveConnectionTimeoutSeconds;
    }

    /**
     * inactiveConnectionTimeoutSeconds 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInactiveConnectionTimeoutSeconds(String value) {
        this.inactiveConnectionTimeoutSeconds = value;
    }

    /**
     * testFrequencySeconds 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestFrequencySeconds() {
        return testFrequencySeconds;
    }

    /**
     * testFrequencySeconds 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestFrequencySeconds(String value) {
        this.testFrequencySeconds = value;
    }

    /**
     * initSql 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitSql() {
        return initSql;
    }

    /**
     * initSql 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitSql(String value) {
        this.initSql = value;
    }

}
