//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 05:26:20 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.v2_4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 
 * 	The mime-mappingType defines a mapping between an extension
 * 	and a mime type.
 * 
 * 	Used in: web-app
 * 
 *       
 * 
 * <p>mime-mappingType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="mime-mappingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="extension" type="{http://java.sun.com/xml/ns/j2ee}string"/>
 *         &lt;element name="mime-type" type="{http://java.sun.com/xml/ns/j2ee}mime-typeType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mime-mappingType", propOrder = {
    "extension",
    "mimeType"
})
public class MimeMappingType {

    @XmlElement(required = true)
    protected com.athena.chameleon.engine.entity.xml.webapp.v2_4.String extension;
    @XmlElement(name = "mime-type", required = true)
    protected MimeTypeType mimeType;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String id;

    /**
     * extension 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link com.athena.chameleon.engine.entity.xml.webapp.v2_4.String }
     *     
     */
    public com.athena.chameleon.engine.entity.xml.webapp.v2_4.String getExtension() {
        return extension;
    }

    /**
     * extension 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link com.athena.chameleon.engine.entity.xml.webapp.v2_4.String }
     *     
     */
    public void setExtension(com.athena.chameleon.engine.entity.xml.webapp.v2_4.String value) {
        this.extension = value;
    }

    /**
     * mimeType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MimeTypeType }
     *     
     */
    public MimeTypeType getMimeType() {
        return mimeType;
    }

    /**
     * mimeType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MimeTypeType }
     *     
     */
    public void setMimeType(MimeTypeType value) {
        this.mimeType = value;
    }

    /**
     * id 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getId() {
        return id;
    }

    /**
     * id 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

}
