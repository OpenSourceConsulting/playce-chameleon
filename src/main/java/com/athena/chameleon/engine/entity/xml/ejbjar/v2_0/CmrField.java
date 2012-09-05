//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 11:44:05 AM KST 
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
    "cmrFieldName",
    "cmrFieldType"
})
@XmlRootElement(name = "cmr-field")
public class CmrField {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    protected Description description;
    @XmlElement(name = "cmr-field-name", required = true)
    protected CmrFieldName cmrFieldName;
    @XmlElement(name = "cmr-field-type")
    protected CmrFieldType cmrFieldType;

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
     * cmrFieldName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CmrFieldName }
     *     
     */
    public CmrFieldName getCmrFieldName() {
        return cmrFieldName;
    }

    /**
     * cmrFieldName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CmrFieldName }
     *     
     */
    public void setCmrFieldName(CmrFieldName value) {
        this.cmrFieldName = value;
    }

    /**
     * cmrFieldType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CmrFieldType }
     *     
     */
    public CmrFieldType getCmrFieldType() {
        return cmrFieldType;
    }

    /**
     * cmrFieldType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CmrFieldType }
     *     
     */
    public void setCmrFieldType(CmrFieldType value) {
        this.cmrFieldType = value;
    }

}
