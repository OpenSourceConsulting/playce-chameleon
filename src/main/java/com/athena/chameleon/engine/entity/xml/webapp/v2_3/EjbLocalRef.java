//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 05:10:33 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.v2_3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "ejbRefName",
    "ejbRefType",
    "localHome",
    "local",
    "ejbLink"
})
@XmlRootElement(name = "ejb-local-ref")
public class EjbLocalRef {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    protected Description description;
    @XmlElement(name = "ejb-ref-name", required = true)
    protected EjbRefName ejbRefName;
    @XmlElement(name = "ejb-ref-type", required = true)
    protected EjbRefType ejbRefType;
    @XmlElement(name = "local-home", required = true)
    protected LocalHome localHome;
    @XmlElement(required = true)
    protected Local local;
    @XmlElement(name = "ejb-link")
    protected EjbLink ejbLink;

    /**
     * id 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * id 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * description 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * description 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * ejbRefName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbRefName }
     *     
     */
    public EjbRefName getEjbRefName() {
        return ejbRefName;
    }

    /**
     * ejbRefName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbRefName }
     *     
     */
    public void setEjbRefName(EjbRefName value) {
        this.ejbRefName = value;
    }

    /**
     * ejbRefType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbRefType }
     *     
     */
    public EjbRefType getEjbRefType() {
        return ejbRefType;
    }

    /**
     * ejbRefType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbRefType }
     *     
     */
    public void setEjbRefType(EjbRefType value) {
        this.ejbRefType = value;
    }

    /**
     * localHome 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link LocalHome }
     *     
     */
    public LocalHome getLocalHome() {
        return localHome;
    }

    /**
     * localHome 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalHome }
     *     
     */
    public void setLocalHome(LocalHome value) {
        this.localHome = value;
    }

    /**
     * local 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Local }
     *     
     */
    public Local getLocal() {
        return local;
    }

    /**
     * local 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Local }
     *     
     */
    public void setLocal(Local value) {
        this.local = value;
    }

    /**
     * ejbLink 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbLink }
     *     
     */
    public EjbLink getEjbLink() {
        return ejbLink;
    }

    /**
     * ejbLink 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbLink }
     *     
     */
    public void setEjbLink(EjbLink value) {
        this.ejbLink = value;
    }

}
