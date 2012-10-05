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
    "debugLevel",
    "keepConnUntilTxCompleteEnabled",
    "endOnlyOnceEnabled",
    "recoverOnlyOnceEnabled",
    "txContextOnCloseNeeded",
    "newConnForCommitEnabled",
    "preparedStatementCacheSize",
    "keepLogicalConnOpenOnRelease",
    "localTransactionSupported",
    "resourceHealthMonitoringEnabled",
    "xaSetTransactionTimeout",
    "xaTransactionTimeout",
    "rollbackLocaltxUponConnclose"
})
@XmlRootElement(name = "xa-params")
public class XaParams {

    @XmlElement(name = "debug-level")
    protected String debugLevel;
    @XmlElement(name = "keep-conn-until-tx-complete-enabled")
    protected String keepConnUntilTxCompleteEnabled;
    @XmlElement(name = "end-only-once-enabled")
    protected String endOnlyOnceEnabled;
    @XmlElement(name = "recover-only-once-enabled")
    protected String recoverOnlyOnceEnabled;
    @XmlElement(name = "tx-context-on-close-needed")
    protected String txContextOnCloseNeeded;
    @XmlElement(name = "new-conn-for-commit-enabled")
    protected String newConnForCommitEnabled;
    @XmlElement(name = "prepared-statement-cache-size")
    protected String preparedStatementCacheSize;
    @XmlElement(name = "keep-logical-conn-open-on-release")
    protected String keepLogicalConnOpenOnRelease;
    @XmlElement(name = "local-transaction-supported")
    protected String localTransactionSupported;
    @XmlElement(name = "resource-health-monitoring-enabled")
    protected String resourceHealthMonitoringEnabled;
    @XmlElement(name = "xa-set-transaction-timeout")
    protected String xaSetTransactionTimeout;
    @XmlElement(name = "xa-transaction-timeout")
    protected String xaTransactionTimeout;
    @XmlElement(name = "rollback-localtx-upon-connclose")
    protected String rollbackLocaltxUponConnclose;

    /**
     * debugLevel 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebugLevel() {
        return debugLevel;
    }

    /**
     * debugLevel 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebugLevel(String value) {
        this.debugLevel = value;
    }

    /**
     * keepConnUntilTxCompleteEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeepConnUntilTxCompleteEnabled() {
        return keepConnUntilTxCompleteEnabled;
    }

    /**
     * keepConnUntilTxCompleteEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeepConnUntilTxCompleteEnabled(String value) {
        this.keepConnUntilTxCompleteEnabled = value;
    }

    /**
     * endOnlyOnceEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndOnlyOnceEnabled() {
        return endOnlyOnceEnabled;
    }

    /**
     * endOnlyOnceEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndOnlyOnceEnabled(String value) {
        this.endOnlyOnceEnabled = value;
    }

    /**
     * recoverOnlyOnceEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecoverOnlyOnceEnabled() {
        return recoverOnlyOnceEnabled;
    }

    /**
     * recoverOnlyOnceEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecoverOnlyOnceEnabled(String value) {
        this.recoverOnlyOnceEnabled = value;
    }

    /**
     * txContextOnCloseNeeded 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxContextOnCloseNeeded() {
        return txContextOnCloseNeeded;
    }

    /**
     * txContextOnCloseNeeded 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxContextOnCloseNeeded(String value) {
        this.txContextOnCloseNeeded = value;
    }

    /**
     * newConnForCommitEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewConnForCommitEnabled() {
        return newConnForCommitEnabled;
    }

    /**
     * newConnForCommitEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewConnForCommitEnabled(String value) {
        this.newConnForCommitEnabled = value;
    }

    /**
     * preparedStatementCacheSize 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreparedStatementCacheSize() {
        return preparedStatementCacheSize;
    }

    /**
     * preparedStatementCacheSize 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreparedStatementCacheSize(String value) {
        this.preparedStatementCacheSize = value;
    }

    /**
     * keepLogicalConnOpenOnRelease 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeepLogicalConnOpenOnRelease() {
        return keepLogicalConnOpenOnRelease;
    }

    /**
     * keepLogicalConnOpenOnRelease 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeepLogicalConnOpenOnRelease(String value) {
        this.keepLogicalConnOpenOnRelease = value;
    }

    /**
     * localTransactionSupported 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalTransactionSupported() {
        return localTransactionSupported;
    }

    /**
     * localTransactionSupported 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalTransactionSupported(String value) {
        this.localTransactionSupported = value;
    }

    /**
     * resourceHealthMonitoringEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResourceHealthMonitoringEnabled() {
        return resourceHealthMonitoringEnabled;
    }

    /**
     * resourceHealthMonitoringEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResourceHealthMonitoringEnabled(String value) {
        this.resourceHealthMonitoringEnabled = value;
    }

    /**
     * xaSetTransactionTimeout 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXaSetTransactionTimeout() {
        return xaSetTransactionTimeout;
    }

    /**
     * xaSetTransactionTimeout 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXaSetTransactionTimeout(String value) {
        this.xaSetTransactionTimeout = value;
    }

    /**
     * xaTransactionTimeout 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXaTransactionTimeout() {
        return xaTransactionTimeout;
    }

    /**
     * xaTransactionTimeout 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXaTransactionTimeout(String value) {
        this.xaTransactionTimeout = value;
    }

    /**
     * rollbackLocaltxUponConnclose 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRollbackLocaltxUponConnclose() {
        return rollbackLocaltxUponConnclose;
    }

    /**
     * rollbackLocaltxUponConnclose 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRollbackLocaltxUponConnclose(String value) {
        this.rollbackLocaltxUponConnclose = value;
    }

}
