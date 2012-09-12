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
    "isModifiedMethodName",
    "delayUpdatesUntilEndOfTx",
    "findersLoadBean",
    "persistenceUse"
})
@XmlRootElement(name = "persistence")
public class Persistence {

    @XmlElement(name = "is-modified-method-name")
    protected String isModifiedMethodName;
    @XmlElement(name = "delay-updates-until-end-of-tx")
    protected String delayUpdatesUntilEndOfTx;
    @XmlElement(name = "finders-load-bean")
    protected String findersLoadBean;
    @XmlElement(name = "persistence-use")
    protected PersistenceUse persistenceUse;

    /**
     * isModifiedMethodName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsModifiedMethodName() {
        return isModifiedMethodName;
    }

    /**
     * isModifiedMethodName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsModifiedMethodName(String value) {
        this.isModifiedMethodName = value;
    }

    /**
     * delayUpdatesUntilEndOfTx 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelayUpdatesUntilEndOfTx() {
        return delayUpdatesUntilEndOfTx;
    }

    /**
     * delayUpdatesUntilEndOfTx 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelayUpdatesUntilEndOfTx(String value) {
        this.delayUpdatesUntilEndOfTx = value;
    }

    /**
     * findersLoadBean 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFindersLoadBean() {
        return findersLoadBean;
    }

    /**
     * findersLoadBean 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFindersLoadBean(String value) {
        this.findersLoadBean = value;
    }

    /**
     * persistenceUse 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PersistenceUse }
     *     
     */
    public PersistenceUse getPersistenceUse() {
        return persistenceUse;
    }

    /**
     * persistenceUse 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PersistenceUse }
     *     
     */
    public void setPersistenceUse(PersistenceUse value) {
        this.persistenceUse = value;
    }

}
