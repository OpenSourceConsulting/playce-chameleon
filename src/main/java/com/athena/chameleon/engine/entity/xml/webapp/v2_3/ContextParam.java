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
    "paramName",
    "paramValue",
    "description"
})
@XmlRootElement(name = "context-param")
public class ContextParam {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "param-name", required = true)
    protected ParamName paramName;
    @XmlElement(name = "param-value", required = true)
    protected ParamValue paramValue;
    protected Description description;

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
     * paramName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ParamName }
     *     
     */
    public ParamName getParamName() {
        return paramName;
    }

    /**
     * paramName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamName }
     *     
     */
    public void setParamName(ParamName value) {
        this.paramName = value;
    }

    /**
     * paramValue 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ParamValue }
     *     
     */
    public ParamValue getParamValue() {
        return paramValue;
    }

    /**
     * paramValue 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamValue }
     *     
     */
    public void setParamValue(ParamValue value) {
        this.paramValue = value;
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

}
