//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.12 시간 04:26:39 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1;

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
    "statefulSessionCache",
    "persistentStoreDir",
    "statefulSessionClustering",
    "allowConcurrentCalls",
    "allowRemoveDuringTransaction"
})
@XmlRootElement(name = "stateful-session-descriptor")
public class StatefulSessionDescriptor {

    @XmlElement(name = "stateful-session-cache")
    protected StatefulSessionCache statefulSessionCache;
    @XmlElement(name = "persistent-store-dir")
    protected String persistentStoreDir;
    @XmlElement(name = "stateful-session-clustering")
    protected StatefulSessionClustering statefulSessionClustering;
    @XmlElement(name = "allow-concurrent-calls")
    protected String allowConcurrentCalls;
    @XmlElement(name = "allow-remove-during-transaction")
    protected String allowRemoveDuringTransaction;

    /**
     * statefulSessionCache 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link StatefulSessionCache }
     *     
     */
    public StatefulSessionCache getStatefulSessionCache() {
        return statefulSessionCache;
    }

    /**
     * statefulSessionCache 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link StatefulSessionCache }
     *     
     */
    public void setStatefulSessionCache(StatefulSessionCache value) {
        this.statefulSessionCache = value;
    }

    /**
     * persistentStoreDir 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersistentStoreDir() {
        return persistentStoreDir;
    }

    /**
     * persistentStoreDir 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersistentStoreDir(String value) {
        this.persistentStoreDir = value;
    }

    /**
     * statefulSessionClustering 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link StatefulSessionClustering }
     *     
     */
    public StatefulSessionClustering getStatefulSessionClustering() {
        return statefulSessionClustering;
    }

    /**
     * statefulSessionClustering 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link StatefulSessionClustering }
     *     
     */
    public void setStatefulSessionClustering(StatefulSessionClustering value) {
        this.statefulSessionClustering = value;
    }

    /**
     * allowConcurrentCalls 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowConcurrentCalls() {
        return allowConcurrentCalls;
    }

    /**
     * allowConcurrentCalls 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowConcurrentCalls(String value) {
        this.allowConcurrentCalls = value;
    }

    /**
     * allowRemoveDuringTransaction 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowRemoveDuringTransaction() {
        return allowRemoveDuringTransaction;
    }

    /**
     * allowRemoveDuringTransaction 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowRemoveDuringTransaction(String value) {
        this.allowRemoveDuringTransaction = value;
    }

}
