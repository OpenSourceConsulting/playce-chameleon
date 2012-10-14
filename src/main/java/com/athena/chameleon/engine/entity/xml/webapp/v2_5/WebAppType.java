//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 05:58:17 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.v2_5;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
 *         &lt;group ref="{http://java.sun.com/xml/ns/javaee}descriptionGroup"/>
 *         &lt;element name="distributable" type="{http://java.sun.com/xml/ns/javaee}emptyType"/>
 *         &lt;element name="context-param" type="{http://java.sun.com/xml/ns/javaee}param-valueType"/>
 *         &lt;element name="filter" type="{http://java.sun.com/xml/ns/javaee}filterType"/>
 *         &lt;element name="filter-mapping" type="{http://java.sun.com/xml/ns/javaee}filter-mappingType"/>
 *         &lt;element name="listener" type="{http://java.sun.com/xml/ns/javaee}listenerType"/>
 *         &lt;element name="servlet" type="{http://java.sun.com/xml/ns/javaee}servletType"/>
 *         &lt;element name="servlet-mapping" type="{http://java.sun.com/xml/ns/javaee}servlet-mappingType"/>
 *         &lt;element name="session-config" type="{http://java.sun.com/xml/ns/javaee}session-configType"/>
 *         &lt;element name="mime-mapping" type="{http://java.sun.com/xml/ns/javaee}mime-mappingType"/>
 *         &lt;element name="welcome-file-list" type="{http://java.sun.com/xml/ns/javaee}welcome-file-listType"/>
 *         &lt;element name="error-page" type="{http://java.sun.com/xml/ns/javaee}error-pageType"/>
 *         &lt;element name="jsp-config" type="{http://java.sun.com/xml/ns/javaee}jsp-configType"/>
 *         &lt;element name="security-constraint" type="{http://java.sun.com/xml/ns/javaee}security-constraintType"/>
 *         &lt;element name="login-config" type="{http://java.sun.com/xml/ns/javaee}login-configType"/>
 *         &lt;element name="security-role" type="{http://java.sun.com/xml/ns/javaee}security-roleType"/>
 *         &lt;group ref="{http://java.sun.com/xml/ns/javaee}jndiEnvironmentRefsGroup"/>
 *         &lt;element name="message-destination" type="{http://java.sun.com/xml/ns/javaee}message-destinationType"/>
 *         &lt;element name="locale-encoding-mapping-list" type="{http://java.sun.com/xml/ns/javaee}locale-encoding-mapping-listType"/>
 *       &lt;/choice>
 *       &lt;attribute name="version" use="required" type="{http://java.sun.com/xml/ns/javaee}web-app-versionType" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="metadata-complete" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "web-app")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "web-appType", propOrder = {
    "descriptionAndDisplayNameAndIcon"
})
public class WebAppType {

    @XmlElementRefs({
        @XmlElementRef(name = "listener", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "filter", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "service-ref", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "resource-ref", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "mime-mapping", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "filter-mapping", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "message-destination-ref", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "description", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "persistence-context-ref", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "context-param", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "login-config", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "session-config", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "security-constraint", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "welcome-file-list", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "display-name", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "persistence-unit-ref", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "ejb-ref", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "distributable", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "ejb-local-ref", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "security-role", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "pre-destroy", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "jsp-config", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "env-entry", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "post-construct", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "servlet", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "locale-encoding-mapping-list", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "message-destination", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "resource-env-ref", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "error-page", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "icon", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class),
        @XmlElementRef(name = "servlet-mapping", namespace = "http://java.sun.com/xml/ns/javaee", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> descriptionAndDisplayNameAndIcon;
    @XmlAttribute(name = "version", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected java.lang.String version;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected java.lang.String id;
    @XmlAttribute(name = "metadata-complete")
    protected Boolean metadataComplete;

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
     * {@link JAXBElement }{@code <}{@link ListenerType }{@code >}
     * {@link JAXBElement }{@code <}{@link FilterType }{@code >}
     * {@link JAXBElement }{@code <}{@link ServiceRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link ResourceRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link MimeMappingType }{@code >}
     * {@link JAXBElement }{@code <}{@link FilterMappingType }{@code >}
     * {@link JAXBElement }{@code <}{@link MessageDestinationRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link DescriptionType }{@code >}
     * {@link JAXBElement }{@code <}{@link PersistenceContextRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link ParamValueType }{@code >}
     * {@link JAXBElement }{@code <}{@link LoginConfigType }{@code >}
     * {@link JAXBElement }{@code <}{@link SessionConfigType }{@code >}
     * {@link JAXBElement }{@code <}{@link SecurityConstraintType }{@code >}
     * {@link JAXBElement }{@code <}{@link WelcomeFileListType }{@code >}
     * {@link JAXBElement }{@code <}{@link DisplayNameType }{@code >}
     * {@link JAXBElement }{@code <}{@link PersistenceUnitRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link EjbRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link EmptyType }{@code >}
     * {@link JAXBElement }{@code <}{@link EjbLocalRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link SecurityRoleType }{@code >}
     * {@link JAXBElement }{@code <}{@link LifecycleCallbackType }{@code >}
     * {@link JAXBElement }{@code <}{@link JspConfigType }{@code >}
     * {@link JAXBElement }{@code <}{@link EnvEntryType }{@code >}
     * {@link JAXBElement }{@code <}{@link LifecycleCallbackType }{@code >}
     * {@link JAXBElement }{@code <}{@link ServletType }{@code >}
     * {@link JAXBElement }{@code <}{@link ResourceEnvRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link MessageDestinationType }{@code >}
     * {@link JAXBElement }{@code <}{@link LocaleEncodingMappingListType }{@code >}
     * {@link JAXBElement }{@code <}{@link ErrorPageType }{@code >}
     * {@link JAXBElement }{@code <}{@link IconType }{@code >}
     * {@link JAXBElement }{@code <}{@link ServletMappingType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getDescriptionAndDisplayNameAndIcon() {
        if (descriptionAndDisplayNameAndIcon == null) {
            descriptionAndDisplayNameAndIcon = new ArrayList<JAXBElement<?>>();
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

    /**
     * metadataComplete 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMetadataComplete() {
        return metadataComplete;
    }

    /**
     * metadataComplete 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMetadataComplete(Boolean value) {
        this.metadataComplete = value;
    }

}
