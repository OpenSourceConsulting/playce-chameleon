//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.11 시간 06:00:14 PM KST 
//


package com.athena.chameleon.engine.entity.xml.application.v1_3;

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
    "webUri",
    "contextRoot"
})
@XmlRootElement(name = "web")
public class Web {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "web-uri", required = true)
    protected WebUri webUri;
    @XmlElement(name = "context-root", required = true)
    protected ContextRoot contextRoot;

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
     * webUri 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link WebUri }
     *     
     */
    public WebUri getWebUri() {
        return webUri;
    }

    /**
     * webUri 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link WebUri }
     *     
     */
    public void setWebUri(WebUri value) {
        this.webUri = value;
    }

    /**
     * contextRoot 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ContextRoot }
     *     
     */
    public ContextRoot getContextRoot() {
        return contextRoot;
    }

    /**
     * contextRoot 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ContextRoot }
     *     
     */
    public void setContextRoot(ContextRoot value) {
        this.contextRoot = value;
    }

}
