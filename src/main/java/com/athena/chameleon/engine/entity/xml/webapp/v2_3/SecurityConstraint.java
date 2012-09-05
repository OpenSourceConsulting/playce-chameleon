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
    "displayName",
    "webResourceCollection",
    "authConstraint",
    "userDataConstraint"
})
@XmlRootElement(name = "security-constraint")
public class SecurityConstraint {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "display-name")
    protected DisplayName displayName;
    @XmlElement(name = "web-resource-collection", required = true)
    protected List<WebResourceCollection> webResourceCollection;
    @XmlElement(name = "auth-constraint")
    protected AuthConstraint authConstraint;
    @XmlElement(name = "user-data-constraint")
    protected UserDataConstraint userDataConstraint;

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
     * displayName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link DisplayName }
     *     
     */
    public DisplayName getDisplayName() {
        return displayName;
    }

    /**
     * displayName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link DisplayName }
     *     
     */
    public void setDisplayName(DisplayName value) {
        this.displayName = value;
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
     * {@link WebResourceCollection }
     * 
     * 
     */
    public List<WebResourceCollection> getWebResourceCollection() {
        if (webResourceCollection == null) {
            webResourceCollection = new ArrayList<WebResourceCollection>();
        }
        return this.webResourceCollection;
    }

    /**
     * authConstraint 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AuthConstraint }
     *     
     */
    public AuthConstraint getAuthConstraint() {
        return authConstraint;
    }

    /**
     * authConstraint 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthConstraint }
     *     
     */
    public void setAuthConstraint(AuthConstraint value) {
        this.authConstraint = value;
    }

    /**
     * userDataConstraint 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link UserDataConstraint }
     *     
     */
    public UserDataConstraint getUserDataConstraint() {
        return userDataConstraint;
    }

    /**
     * userDataConstraint 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link UserDataConstraint }
     *     
     */
    public void setUserDataConstraint(UserDataConstraint value) {
        this.userDataConstraint = value;
    }

}
