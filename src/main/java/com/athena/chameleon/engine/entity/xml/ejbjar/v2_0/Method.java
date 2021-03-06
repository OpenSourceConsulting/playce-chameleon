//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.10.09 시간 12:53:10 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.v2_0;

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
    "ejbName",
    "methodIntf",
    "methodName",
    "methodParams"
})
@XmlRootElement(name = "method")
public class Method {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    protected Description description;
    @XmlElement(name = "ejb-name", required = true)
    protected EjbName ejbName;
    @XmlElement(name = "method-intf")
    protected MethodIntf methodIntf;
    @XmlElement(name = "method-name", required = true)
    protected MethodName methodName;
    @XmlElement(name = "method-params")
    protected MethodParams methodParams;

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
     * ejbName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbName }
     *     
     */
    public EjbName getEjbName() {
        return ejbName;
    }

    /**
     * ejbName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbName }
     *     
     */
    public void setEjbName(EjbName value) {
        this.ejbName = value;
    }

    /**
     * methodIntf 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MethodIntf }
     *     
     */
    public MethodIntf getMethodIntf() {
        return methodIntf;
    }

    /**
     * methodIntf 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodIntf }
     *     
     */
    public void setMethodIntf(MethodIntf value) {
        this.methodIntf = value;
    }

    /**
     * methodName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MethodName }
     *     
     */
    public MethodName getMethodName() {
        return methodName;
    }

    /**
     * methodName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodName }
     *     
     */
    public void setMethodName(MethodName value) {
        this.methodName = value;
    }

    /**
     * methodParams 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MethodParams }
     *     
     */
    public MethodParams getMethodParams() {
        return methodParams;
    }

    /**
     * methodParams 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MethodParams }
     *     
     */
    public void setMethodParams(MethodParams value) {
        this.methodParams = value;
    }

}
