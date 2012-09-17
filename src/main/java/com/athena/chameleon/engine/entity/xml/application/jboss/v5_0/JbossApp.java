//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 01:59:10 PM KST 
//


package com.athena.chameleon.engine.entity.xml.application.jboss.v5_0;

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
    "moduleOrder",
    "securityDomain",
    "unauthenticatedPrincipal",
    "loaderRepository",
    "jmxName",
    "libraryDirectory",
    "module",
    "securityRole"
})
@XmlRootElement(name = "jboss-app")
public class JbossApp {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlElement(name = "module-order")
    protected String moduleOrder;
    @XmlElement(name = "security-domain")
    protected String securityDomain;
    @XmlElement(name = "unauthenticated-principal")
    protected String unauthenticatedPrincipal;
    @XmlElement(name = "loader-repository")
    protected LoaderRepository loaderRepository;
    @XmlElement(name = "jmx-name")
    protected String jmxName;
    @XmlElement(name = "library-directory")
    protected String libraryDirectory;
    protected List<Module> module;
    @XmlElement(name = "security-role")
    protected List<SecurityRole> securityRole;

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
     * moduleOrder 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleOrder() {
        return moduleOrder;
    }

    /**
     * moduleOrder 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleOrder(String value) {
        this.moduleOrder = value;
    }

    /**
     * securityDomain 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityDomain() {
        return securityDomain;
    }

    /**
     * securityDomain 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityDomain(String value) {
        this.securityDomain = value;
    }

    /**
     * unauthenticatedPrincipal 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnauthenticatedPrincipal() {
        return unauthenticatedPrincipal;
    }

    /**
     * unauthenticatedPrincipal 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnauthenticatedPrincipal(String value) {
        this.unauthenticatedPrincipal = value;
    }

    /**
     * loaderRepository 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link LoaderRepository }
     *     
     */
    public LoaderRepository getLoaderRepository() {
        return loaderRepository;
    }

    /**
     * loaderRepository 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link LoaderRepository }
     *     
     */
    public void setLoaderRepository(LoaderRepository value) {
        this.loaderRepository = value;
    }

    /**
     * jmxName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmxName() {
        return jmxName;
    }

    /**
     * jmxName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmxName(String value) {
        this.jmxName = value;
    }

    /**
     * libraryDirectory 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLibraryDirectory() {
        return libraryDirectory;
    }

    /**
     * libraryDirectory 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLibraryDirectory(String value) {
        this.libraryDirectory = value;
    }

    /**
     * Gets the value of the module property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the module property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Module }
     * 
     * 
     */
    public List<Module> getModule() {
        if (module == null) {
            module = new ArrayList<Module>();
        }
        return this.module;
    }

    /**
     * Gets the value of the securityRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securityRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurityRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityRole }
     * 
     * 
     */
    public List<SecurityRole> getSecurityRole() {
        if (securityRole == null) {
            securityRole = new ArrayList<SecurityRole>();
        }
        return this.securityRole;
    }

}
