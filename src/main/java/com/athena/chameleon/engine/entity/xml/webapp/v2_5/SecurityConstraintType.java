//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 05:58:17 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.v2_5;

import java.util.ArrayList;
import java.util.List;
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
 * 	The security-constraintType is used to associate
 * 	security constraints with one or more web resource
 * 	collections
 * 
 * 	Used in: web-app
 * 
 *       
 * 
 * <p>security-constraintType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="security-constraintType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="display-name" type="{http://java.sun.com/xml/ns/javaee}display-nameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="web-resource-collection" type="{http://java.sun.com/xml/ns/javaee}web-resource-collectionType" maxOccurs="unbounded"/>
 *         &lt;element name="auth-constraint" type="{http://java.sun.com/xml/ns/javaee}auth-constraintType" minOccurs="0"/>
 *         &lt;element name="user-data-constraint" type="{http://java.sun.com/xml/ns/javaee}user-data-constraintType" minOccurs="0"/>
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
@XmlType(name = "security-constraintType", propOrder = {
    "displayName",
    "webResourceCollection",
    "authConstraint",
    "userDataConstraint"
})
public class SecurityConstraintType {

    @XmlElement(name = "display-name")
    protected List<DisplayNameType> displayName;
    @XmlElement(name = "web-resource-collection", required = true)
    protected List<WebResourceCollectionType> webResourceCollection;
    @XmlElement(name = "auth-constraint")
    protected AuthConstraintType authConstraint;
    @XmlElement(name = "user-data-constraint")
    protected UserDataConstraintType userDataConstraint;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String id;

    /**
     * Gets the value of the displayName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DisplayNameType }
     * 
     * 
     */
    public List<DisplayNameType> getDisplayName() {
        if (displayName == null) {
            displayName = new ArrayList<DisplayNameType>();
        }
        return this.displayName;
    }

    /**
     * Gets the value of the webResourceCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the webResourceCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWebResourceCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WebResourceCollectionType }
     * 
     * 
     */
    public List<WebResourceCollectionType> getWebResourceCollection() {
        if (webResourceCollection == null) {
            webResourceCollection = new ArrayList<WebResourceCollectionType>();
        }
        return this.webResourceCollection;
    }

    /**
     * authConstraint 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AuthConstraintType }
     *     
     */
    public AuthConstraintType getAuthConstraint() {
        return authConstraint;
    }

    /**
     * authConstraint 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthConstraintType }
     *     
     */
    public void setAuthConstraint(AuthConstraintType value) {
        this.authConstraint = value;
    }

    /**
     * userDataConstraint 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link UserDataConstraintType }
     *     
     */
    public UserDataConstraintType getUserDataConstraint() {
        return userDataConstraint;
    }

    /**
     * userDataConstraint 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link UserDataConstraintType }
     *     
     */
    public void setUserDataConstraint(UserDataConstraintType value) {
        this.userDataConstraint = value;
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
