//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 05:10:33 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.v2_3;

import java.util.ArrayList;
import java.util.List;
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
    "webResourceName",
    "description",
    "urlPattern",
    "httpMethod"
})
@XmlRootElement(name = "web-resource-collection")
public class WebResourceCollection {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "web-resource-name", required = true)
    protected WebResourceName webResourceName;
    protected Description description;
    @XmlElement(name = "url-pattern")
    protected List<UrlPattern> urlPattern;
    @XmlElement(name = "http-method")
    protected List<HttpMethod> httpMethod;

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
     * webResourceName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link WebResourceName }
     *     
     */
    public WebResourceName getWebResourceName() {
        return webResourceName;
    }

    /**
     * webResourceName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link WebResourceName }
     *     
     */
    public void setWebResourceName(WebResourceName value) {
        this.webResourceName = value;
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
     * Gets the value of the urlPattern property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the urlPattern property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrlPattern().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UrlPattern }
     * 
     * 
     */
    public List<UrlPattern> getUrlPattern() {
        if (urlPattern == null) {
            urlPattern = new ArrayList<UrlPattern>();
        }
        return this.urlPattern;
    }

    /**
     * Gets the value of the httpMethod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the httpMethod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHttpMethod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HttpMethod }
     * 
     * 
     */
    public List<HttpMethod> getHttpMethod() {
        if (httpMethod == null) {
            httpMethod = new ArrayList<HttpMethod>();
        }
        return this.httpMethod;
    }

}
