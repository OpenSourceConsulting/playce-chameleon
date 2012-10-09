//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.10.09 시간 12:53:10 PM KST 
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
    "displayName",
    "smallIcon",
    "largeIcon",
    "enterpriseBeans",
    "relationships",
    "assemblyDescriptor",
    "ejbClientJar"
})
@XmlRootElement(name = "ejb-jar")
public class EjbJar {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    protected Description description;
    @XmlElement(name = "display-name")
    protected DisplayName displayName;
    @XmlElement(name = "small-icon")
    protected SmallIcon smallIcon;
    @XmlElement(name = "large-icon")
    protected LargeIcon largeIcon;
    @XmlElement(name = "enterprise-beans", required = true)
    protected EnterpriseBeans enterpriseBeans;
    protected Relationships relationships;
    @XmlElement(name = "assembly-descriptor")
    protected AssemblyDescriptor assemblyDescriptor;
    @XmlElement(name = "ejb-client-jar")
    protected EjbClientJar ejbClientJar;

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
     * smallIcon 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SmallIcon }
     *     
     */
    public SmallIcon getSmallIcon() {
        return smallIcon;
    }

    /**
     * smallIcon 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SmallIcon }
     *     
     */
    public void setSmallIcon(SmallIcon value) {
        this.smallIcon = value;
    }

    /**
     * largeIcon 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link LargeIcon }
     *     
     */
    public LargeIcon getLargeIcon() {
        return largeIcon;
    }

    /**
     * largeIcon 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link LargeIcon }
     *     
     */
    public void setLargeIcon(LargeIcon value) {
        this.largeIcon = value;
    }

    /**
     * enterpriseBeans 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EnterpriseBeans }
     *     
     */
    public EnterpriseBeans getEnterpriseBeans() {
        return enterpriseBeans;
    }

    /**
     * enterpriseBeans 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EnterpriseBeans }
     *     
     */
    public void setEnterpriseBeans(EnterpriseBeans value) {
        this.enterpriseBeans = value;
    }

    /**
     * relationships 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Relationships }
     *     
     */
    public Relationships getRelationships() {
        return relationships;
    }

    /**
     * relationships 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Relationships }
     *     
     */
    public void setRelationships(Relationships value) {
        this.relationships = value;
    }

    /**
     * assemblyDescriptor 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AssemblyDescriptor }
     *     
     */
    public AssemblyDescriptor getAssemblyDescriptor() {
        return assemblyDescriptor;
    }

    /**
     * assemblyDescriptor 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AssemblyDescriptor }
     *     
     */
    public void setAssemblyDescriptor(AssemblyDescriptor value) {
        this.assemblyDescriptor = value;
    }

    /**
     * ejbClientJar 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbClientJar }
     *     
     */
    public EjbClientJar getEjbClientJar() {
        return ejbClientJar;
    }

    /**
     * ejbClientJar 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbClientJar }
     *     
     */
    public void setEjbClientJar(EjbClientJar value) {
        this.ejbClientJar = value;
    }

}
