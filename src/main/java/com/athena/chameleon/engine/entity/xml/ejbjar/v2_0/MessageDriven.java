//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.10.09 시간 12:53:10 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.v2_0;

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
    "description",
    "displayName",
    "smallIcon",
    "largeIcon",
    "ejbName",
    "ejbClass",
    "transactionType",
    "messageSelector",
    "acknowledgeMode",
    "messageDrivenDestination",
    "envEntry",
    "ejbRef",
    "ejbLocalRef",
    "securityIdentity",
    "resourceRef",
    "resourceEnvRef"
})
@XmlRootElement(name = "message-driven")
public class MessageDriven {

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
    @XmlElement(name = "ejb-name", required = true)
    protected EjbName ejbName;
    @XmlElement(name = "ejb-class", required = true)
    protected EjbClass ejbClass;
    @XmlElement(name = "transaction-type", required = true)
    protected TransactionType transactionType;
    @XmlElement(name = "message-selector")
    protected MessageSelector messageSelector;
    @XmlElement(name = "acknowledge-mode")
    protected AcknowledgeMode acknowledgeMode;
    @XmlElement(name = "message-driven-destination")
    protected MessageDrivenDestination messageDrivenDestination;
    @XmlElement(name = "env-entry")
    protected List<EnvEntry> envEntry;
    @XmlElement(name = "ejb-ref")
    protected List<EjbRef> ejbRef;
    @XmlElement(name = "ejb-local-ref")
    protected List<EjbLocalRef> ejbLocalRef;
    @XmlElement(name = "security-identity")
    protected SecurityIdentity securityIdentity;
    @XmlElement(name = "resource-ref")
    protected List<ResourceRef> resourceRef;
    @XmlElement(name = "resource-env-ref")
    protected List<ResourceEnvRef> resourceEnvRef;

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
     * ejbName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbName }
     *     
     */
    public EjbName getEjbName() {
        return ejbName;
    }

    /**
     * ejbName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbName }
     *     
     */
    public void setEjbName(EjbName value) {
        this.ejbName = value;
    }

    /**
     * ejbClass 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbClass }
     *     
     */
    public EjbClass getEjbClass() {
        return ejbClass;
    }

    /**
     * ejbClass 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbClass }
     *     
     */
    public void setEjbClass(EjbClass value) {
        this.ejbClass = value;
    }

    /**
     * transactionType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link TransactionType }
     *     
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * transactionType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionType }
     *     
     */
    public void setTransactionType(TransactionType value) {
        this.transactionType = value;
    }

    /**
     * messageSelector 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageSelector }
     *     
     */
    public MessageSelector getMessageSelector() {
        return messageSelector;
    }

    /**
     * messageSelector 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageSelector }
     *     
     */
    public void setMessageSelector(MessageSelector value) {
        this.messageSelector = value;
    }

    /**
     * acknowledgeMode 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link AcknowledgeMode }
     *     
     */
    public AcknowledgeMode getAcknowledgeMode() {
        return acknowledgeMode;
    }

    /**
     * acknowledgeMode 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AcknowledgeMode }
     *     
     */
    public void setAcknowledgeMode(AcknowledgeMode value) {
        this.acknowledgeMode = value;
    }

    /**
     * messageDrivenDestination 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link MessageDrivenDestination }
     *     
     */
    public MessageDrivenDestination getMessageDrivenDestination() {
        return messageDrivenDestination;
    }

    /**
     * messageDrivenDestination 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageDrivenDestination }
     *     
     */
    public void setMessageDrivenDestination(MessageDrivenDestination value) {
        this.messageDrivenDestination = value;
    }

    /**
     * Gets the value of the envEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the envEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnvEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnvEntry }
     * 
     * 
     */
    public List<EnvEntry> getEnvEntry() {
        if (envEntry == null) {
            envEntry = new ArrayList<EnvEntry>();
        }
        return this.envEntry;
    }

    /**
     * Gets the value of the ejbRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ejbRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEjbRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EjbRef }
     * 
     * 
     */
    public List<EjbRef> getEjbRef() {
        if (ejbRef == null) {
            ejbRef = new ArrayList<EjbRef>();
        }
        return this.ejbRef;
    }

    /**
     * Gets the value of the ejbLocalRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ejbLocalRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEjbLocalRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EjbLocalRef }
     * 
     * 
     */
    public List<EjbLocalRef> getEjbLocalRef() {
        if (ejbLocalRef == null) {
            ejbLocalRef = new ArrayList<EjbLocalRef>();
        }
        return this.ejbLocalRef;
    }

    /**
     * securityIdentity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SecurityIdentity }
     *     
     */
    public SecurityIdentity getSecurityIdentity() {
        return securityIdentity;
    }

    /**
     * securityIdentity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityIdentity }
     *     
     */
    public void setSecurityIdentity(SecurityIdentity value) {
        this.securityIdentity = value;
    }

    /**
     * Gets the value of the resourceRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceRef }
     * 
     * 
     */
    public List<ResourceRef> getResourceRef() {
        if (resourceRef == null) {
            resourceRef = new ArrayList<ResourceRef>();
        }
        return this.resourceRef;
    }

    /**
     * Gets the value of the resourceEnvRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceEnvRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceEnvRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceEnvRef }
     * 
     * 
     */
    public List<ResourceEnvRef> getResourceEnvRef() {
        if (resourceEnvRef == null) {
            resourceEnvRef = new ArrayList<ResourceEnvRef>();
        }
        return this.resourceEnvRef;
    }

}
