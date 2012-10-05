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
    "sizeParams",
    "xaParams",
    "loginDelaySeconds",
    "secsToTrustAnIdlePoolCon",
    "leakProfilingEnabled",
    "connectionCheckParams",
    "jdbcxaDebugLevel",
    "removeInfectedConnectionsEnabled"
})
@XmlRootElement(name = "pool-params")
public class PoolParams {

    @XmlElement(name = "size-params")
    protected SizeParams sizeParams;
    @XmlElement(name = "xa-params")
    protected XaParams xaParams;
    @XmlElement(name = "login-delay-seconds")
    protected String loginDelaySeconds;
    @XmlElement(name = "secs-to-trust-an-idle-pool-con")
    protected String secsToTrustAnIdlePoolCon;
    @XmlElement(name = "leak-profiling-enabled")
    protected String leakProfilingEnabled;
    @XmlElement(name = "connection-check-params")
    protected ConnectionCheckParams connectionCheckParams;
    @XmlElement(name = "jdbcxa-debug-level")
    protected String jdbcxaDebugLevel;
    @XmlElement(name = "remove-infected-connections-enabled")
    protected String removeInfectedConnectionsEnabled;

    /**
     * sizeParams 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SizeParams }
     *     
     */
    public SizeParams getSizeParams() {
        return sizeParams;
    }

    /**
     * sizeParams 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SizeParams }
     *     
     */
    public void setSizeParams(SizeParams value) {
        this.sizeParams = value;
    }

    /**
     * xaParams 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link XaParams }
     *     
     */
    public XaParams getXaParams() {
        return xaParams;
    }

    /**
     * xaParams 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link XaParams }
     *     
     */
    public void setXaParams(XaParams value) {
        this.xaParams = value;
    }

    /**
     * loginDelaySeconds 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginDelaySeconds() {
        return loginDelaySeconds;
    }

    /**
     * loginDelaySeconds 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginDelaySeconds(String value) {
        this.loginDelaySeconds = value;
    }

    /**
     * secsToTrustAnIdlePoolCon 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecsToTrustAnIdlePoolCon() {
        return secsToTrustAnIdlePoolCon;
    }

    /**
     * secsToTrustAnIdlePoolCon 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecsToTrustAnIdlePoolCon(String value) {
        this.secsToTrustAnIdlePoolCon = value;
    }

    /**
     * leakProfilingEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeakProfilingEnabled() {
        return leakProfilingEnabled;
    }

    /**
     * leakProfilingEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeakProfilingEnabled(String value) {
        this.leakProfilingEnabled = value;
    }

    /**
     * connectionCheckParams 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionCheckParams }
     *     
     */
    public ConnectionCheckParams getConnectionCheckParams() {
        return connectionCheckParams;
    }

    /**
     * connectionCheckParams 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionCheckParams }
     *     
     */
    public void setConnectionCheckParams(ConnectionCheckParams value) {
        this.connectionCheckParams = value;
    }

    /**
     * jdbcxaDebugLevel 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJdbcxaDebugLevel() {
        return jdbcxaDebugLevel;
    }

    /**
     * jdbcxaDebugLevel 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJdbcxaDebugLevel(String value) {
        this.jdbcxaDebugLevel = value;
    }

    /**
     * removeInfectedConnectionsEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoveInfectedConnectionsEnabled() {
        return removeInfectedConnectionsEnabled;
    }

    /**
     * removeInfectedConnectionsEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoveInfectedConnectionsEnabled(String value) {
        this.removeInfectedConnectionsEnabled = value;
    }

}
