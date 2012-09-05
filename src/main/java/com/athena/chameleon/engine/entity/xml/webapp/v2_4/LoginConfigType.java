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
 * 	The login-configType is used to configure the authentication
 * 	method that should be used, the realm name that should be
 * 	used for this application, and the attributes that are
 * 	needed by the form login mechanism.
 * 
 * 	Used in: web-app
 * 
 *       
 * 
 * <p>login-configType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="login-configType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auth-method" type="{http://java.sun.com/xml/ns/j2ee}auth-methodType" minOccurs="0"/>
 *         &lt;element name="realm-name" type="{http://java.sun.com/xml/ns/j2ee}string" minOccurs="0"/>
 *         &lt;element name="form-login-config" type="{http://java.sun.com/xml/ns/j2ee}form-login-configType" minOccurs="0"/>
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
@XmlType(name = "login-configType", propOrder = {
    "authMethod",
    "realmName",
    "formLoginConfig"
})
public class LoginConfigType {

    @XmlElement(name = "auth-method")
    protected AuthMethodType authMethod;
    @XmlElement(name = "realm-name")
    protected com.athena.chameleon.engine.entity.xml.webapp.v2_4.String realmName;
    @XmlElement(name = "form-login-config")
    protected FormLoginConfigType formLoginConfig;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String id;

    /**
     * authMethod 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AuthMethodType }
     *     
     */
    public AuthMethodType getAuthMethod() {
        return authMethod;
    }

    /**
     * authMethod 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthMethodType }
     *     
     */
    public void setAuthMethod(AuthMethodType value) {
        this.authMethod = value;
    }

    /**
     * realmName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link com.athena.chameleon.engine.entity.xml.webapp.v2_4.String }
     *     
     */
    public com.athena.chameleon.engine.entity.xml.webapp.v2_4.String getRealmName() {
        return realmName;
    }

    /**
     * realmName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link com.athena.chameleon.engine.entity.xml.webapp.v2_4.String }
     *     
     */
    public void setRealmName(com.athena.chameleon.engine.entity.xml.webapp.v2_4.String value) {
        this.realmName = value;
    }

    /**
     * formLoginConfig 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link FormLoginConfigType }
     *     
     */
    public FormLoginConfigType getFormLoginConfig() {
        return formLoginConfig;
    }

    /**
     * formLoginConfig 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link FormLoginConfigType }
     *     
     */
    public void setFormLoginConfig(FormLoginConfigType value) {
        this.formLoginConfig = value;
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
