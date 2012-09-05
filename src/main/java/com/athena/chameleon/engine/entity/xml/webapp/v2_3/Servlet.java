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
import javax.xml.bind.annotation.XmlElements;
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
    "icon",
    "servletName",
    "displayName",
    "description",
    "servletClassOrJspFile",
    "initParam",
    "loadOnStartup",
    "runAs",
    "securityRoleRef"
})
@XmlRootElement(name = "servlet")
public class Servlet {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    protected Icon icon;
    @XmlElement(name = "servlet-name", required = true)
    protected ServletName servletName;
    @XmlElement(name = "display-name")
    protected DisplayName displayName;
    protected Description description;
    @XmlElements({
        @XmlElement(name = "servlet-class", required = true, type = ServletClass.class),
        @XmlElement(name = "jsp-file", required = true, type = JspFile.class)
    })
    protected List<Object> servletClassOrJspFile;
    @XmlElement(name = "init-param")
    protected List<InitParam> initParam;
    @XmlElement(name = "load-on-startup")
    protected LoadOnStartup loadOnStartup;
    @XmlElement(name = "run-as")
    protected RunAs runAs;
    @XmlElement(name = "security-role-ref")
    protected List<SecurityRoleRef> securityRoleRef;

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
     * icon 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Icon }
     *     
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * icon 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Icon }
     *     
     */
    public void setIcon(Icon value) {
        this.icon = value;
    }

    /**
     * servletName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ServletName }
     *     
     */
    public ServletName getServletName() {
        return servletName;
    }

    /**
     * servletName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ServletName }
     *     
     */
    public void setServletName(ServletName value) {
        this.servletName = value;
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
     * Gets the value of the servletClassOrJspFile property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servletClassOrJspFile property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServletClassOrJspFile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServletClass }
     * {@link JspFile }
     * 
     * 
     */
    public List<Object> getServletClassOrJspFile() {
        if (servletClassOrJspFile == null) {
            servletClassOrJspFile = new ArrayList<Object>();
        }
        return this.servletClassOrJspFile;
    }

    /**
     * Gets the value of the initParam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the initParam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInitParam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InitParam }
     * 
     * 
     */
    public List<InitParam> getInitParam() {
        if (initParam == null) {
            initParam = new ArrayList<InitParam>();
        }
        return this.initParam;
    }

    /**
     * loadOnStartup 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link LoadOnStartup }
     *     
     */
    public LoadOnStartup getLoadOnStartup() {
        return loadOnStartup;
    }

    /**
     * loadOnStartup 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link LoadOnStartup }
     *     
     */
    public void setLoadOnStartup(LoadOnStartup value) {
        this.loadOnStartup = value;
    }

    /**
     * runAs 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link RunAs }
     *     
     */
    public RunAs getRunAs() {
        return runAs;
    }

    /**
     * runAs 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link RunAs }
     *     
     */
    public void setRunAs(RunAs value) {
        this.runAs = value;
    }

    /**
     * Gets the value of the securityRoleRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securityRoleRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurityRoleRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityRoleRef }
     * 
     * 
     */
    public List<SecurityRoleRef> getSecurityRoleRef() {
        if (securityRoleRef == null) {
            securityRoleRef = new ArrayList<SecurityRoleRef>();
        }
        return this.securityRoleRef;
    }

}
