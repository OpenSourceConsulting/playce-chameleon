//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 02:02:31 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "home",
    "bean",
    "listEntity"
})
@XmlRootElement(name = "client-interceptors")
public class ClientInterceptors {

    @XmlAttribute(name = "exposeContainer")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String exposeContainer;
    @XmlElement(required = true)
    protected Home home;
    @XmlElement(required = true)
    protected Bean bean;
    @XmlElement(name = "list-entity")
    protected ListEntity listEntity;

    /**
     * exposeContainer 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExposeContainer() {
        if (exposeContainer == null) {
            return "false";
        } else {
            return exposeContainer;
        }
    }

    /**
     * exposeContainer 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExposeContainer(String value) {
        this.exposeContainer = value;
    }

    /**
     * home 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Home }
     *     
     */
    public Home getHome() {
        return home;
    }

    /**
     * home 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Home }
     *     
     */
    public void setHome(Home value) {
        this.home = value;
    }

    /**
     * bean 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Bean }
     *     
     */
    public Bean getBean() {
        return bean;
    }

    /**
     * bean 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Bean }
     *     
     */
    public void setBean(Bean value) {
        this.bean = value;
    }

    /**
     * listEntity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ListEntity }
     *     
     */
    public ListEntity getListEntity() {
        return listEntity;
    }

    /**
     * listEntity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ListEntity }
     *     
     */
    public void setListEntity(ListEntity value) {
        this.listEntity = value;
    }

}
