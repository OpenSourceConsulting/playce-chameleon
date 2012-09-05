//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 05:26:20 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.v2_4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>web-appType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="web-appType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;group ref="{http://java.sun.com/xml/ns/j2ee}descriptionGroup"/>
 *         &lt;element name="distributable" type="{http://java.sun.com/xml/ns/j2ee}emptyType"/>
 *         &lt;element name="context-param" type="{http://java.sun.com/xml/ns/j2ee}param-valueType"/>
 *         &lt;element name="filter" type="{http://java.sun.com/xml/ns/j2ee}filterType"/>
 *         &lt;element name="filter-mapping" type="{http://java.sun.com/xml/ns/j2ee}filter-mappingType"/>
 *         &lt;element name="listener" type="{http://java.sun.com/xml/ns/j2ee}listenerType"/>
 *         &lt;element name="servlet" type="{http://java.sun.com/xml/ns/j2ee}servletType"/>
 *         &lt;element name="servlet-mapping" type="{http://java.sun.com/xml/ns/j2ee}servlet-mappingType"/>
 *         &lt;element name="session-config" type="{http://java.sun.com/xml/ns/j2ee}session-configType"/>
 *         &lt;element name="mime-mapping" type="{http://java.sun.com/xml/ns/j2ee}mime-mappingType"/>
 *         &lt;element name="welcome-file-list" type="{http://java.sun.com/xml/ns/j2ee}welcome-file-listType"/>
 *         &lt;element name="error-page" type="{http://java.sun.com/xml/ns/j2ee}error-pageType"/>
 *         &lt;element name="jsp-config" type="{http://java.sun.com/xml/ns/j2ee}jsp-configType"/>
 *         &lt;element name="security-constraint" type="{http://java.sun.com/xml/ns/j2ee}security-constraintType"/>
 *         &lt;element name="login-config" type="{http://java.sun.com/xml/ns/j2ee}login-configType"/>
 *         &lt;element name="security-role" type="{http://java.sun.com/xml/ns/j2ee}security-roleType"/>
 *         &lt;group ref="{http://java.sun.com/xml/ns/j2ee}jndiEnvironmentRefsGroup"/>
 *         &lt;element name="message-destination" type="{http://java.sun.com/xml/ns/j2ee}message-destinationType"/>
 *         &lt;element name="locale-encoding-mapping-list" type="{http://java.sun.com/xml/ns/j2ee}locale-encoding-mapping-listType"/>
 *       &lt;/choice>
 *       &lt;attribute name="version" use="required" type="{http://java.sun.com/xml/ns/j2ee}web-app-versionType" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "web-appType", propOrder = {
    "descriptionAndDisplayNameAndIcon"
})
public class WebAppType {

    @XmlElements({
        @XmlElement(name = "description", type = DescriptionType.class),
        @XmlElement(name = "display-name", type = DisplayNameType.class),
        @XmlElement(name = "icon", type = IconType.class),
        @XmlElement(name = "distributable", type = EmptyType.class),
        @XmlElement(name = "context-param", type = ParamValueType.class),
        @XmlElement(name = "filter", type = FilterType.class),
        @XmlElement(name = "filter-mapping", type = FilterMappingType.class),
        @XmlElement(name = "listener", type = ListenerType.class),
        @XmlElement(name = "servlet", type = ServletType.class),
        @XmlElement(name = "servlet-mapping", type = ServletMappingType.class),
        @XmlElement(name = "session-config", type = SessionConfigType.class),
        @XmlElement(name = "mime-mapping", type = MimeMappingType.class),
        @XmlElement(name = "welcome-file-list", type = WelcomeFileListType.class),
        @XmlElement(name = "error-page", type = ErrorPageType.class),
        @XmlElement(name = "jsp-config", type = JspConfigType.class),
        @XmlElement(name = "security-constraint", type = SecurityConstraintType.class),
        @XmlElement(name = "login-config", type = LoginConfigType.class),
        @XmlElement(name = "security-role", type = SecurityRoleType.class),
        @XmlElement(name = "env-entry", type = EnvEntryType.class),
        @XmlElement(name = "ejb-ref", type = EjbRefType.class),
        @XmlElement(name = "ejb-local-ref", type = EjbLocalRefType.class),
        @XmlElement(name = "service-ref", type = ServiceRefType.class),
        @XmlElement(name = "resource-ref", type = ResourceRefType.class),
        @XmlElement(name = "resource-env-ref", type = ResourceEnvRefType.class),
        @XmlElement(name = "message-destination-ref", type = MessageDestinationRefType.class),
        @XmlElement(name = "message-destination", type = MessageDestinationType.class),
        @XmlElement(name = "locale-encoding-mapping-list", type = LocaleEncodingMappingListType.class)
    })
    protected List<Object> descriptionAndDisplayNameAndIcon;
    @XmlAttribute(name = "version", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected java.lang.String version;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String id;

    /**
     * Gets the value of the descriptionAndDisplayNameAndIcon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the descriptionAndDisplayNameAndIcon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescriptionAndDisplayNameAndIcon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionType }
     * {@link DisplayNameType }
     * {@link IconType }
     * {@link EmptyType }
     * {@link ParamValueType }
     * {@link FilterType }
     * {@link FilterMappingType }
     * {@link ListenerType }
     * {@link ServletType }
     * {@link ServletMappingType }
     * {@link SessionConfigType }
     * {@link MimeMappingType }
     * {@link WelcomeFileListType }
     * {@link ErrorPageType }
     * {@link JspConfigType }
     * {@link SecurityConstraintType }
     * {@link LoginConfigType }
     * {@link SecurityRoleType }
     * {@link EnvEntryType }
     * {@link EjbRefType }
     * {@link EjbLocalRefType }
     * {@link ServiceRefType }
     * {@link ResourceRefType }
     * {@link ResourceEnvRefType }
     * {@link MessageDestinationRefType }
     * {@link MessageDestinationType }
     * {@link LocaleEncodingMappingListType }
     * 
     * 
     */
    public List<Object> getDescriptionAndDisplayNameAndIcon() {
        if (descriptionAndDisplayNameAndIcon == null) {
            descriptionAndDisplayNameAndIcon = new ArrayList<Object>();
        }
        return this.descriptionAndDisplayNameAndIcon;
    }

    /**
     * version 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getVersion() {
        return version;
    }

    /**
     * version 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setVersion(java.lang.String value) {
        this.version = value;
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
