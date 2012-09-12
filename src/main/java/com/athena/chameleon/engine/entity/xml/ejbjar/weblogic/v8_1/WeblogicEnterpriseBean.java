//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.12 시간 04:26:39 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ejbName",
    "entityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor",
    "transactionDescriptor",
    "iiopSecurityDescriptor",
    "referenceDescriptor",
    "enableCallByReference",
    "clientsOnSameServer",
    "runAsIdentityPrincipalOrRunAsPrincipalName",
    "createAsPrincipalName",
    "removeAsPrincipalName",
    "passivateAsPrincipalName",
    "jndiName",
    "localJndiName",
    "dispatchPolicy",
    "remoteClientTimeout"
})
@XmlRootElement(name = "weblogic-enterprise-bean")
public class WeblogicEnterpriseBean {

    @XmlElement(name = "ejb-name", required = true)
    protected String ejbName;
    @XmlElements({
        @XmlElement(name = "entity-descriptor", type = EntityDescriptor.class),
        @XmlElement(name = "stateless-session-descriptor", type = StatelessSessionDescriptor.class),
        @XmlElement(name = "stateful-session-descriptor", type = StatefulSessionDescriptor.class),
        @XmlElement(name = "message-driven-descriptor", type = MessageDrivenDescriptor.class)
    })
    protected List<Object> entityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor;
    @XmlElement(name = "transaction-descriptor")
    protected TransactionDescriptor transactionDescriptor;
    @XmlElement(name = "iiop-security-descriptor")
    protected IiopSecurityDescriptor iiopSecurityDescriptor;
    @XmlElement(name = "reference-descriptor")
    protected ReferenceDescriptor referenceDescriptor;
    @XmlElement(name = "enable-call-by-reference")
    protected String enableCallByReference;
    @XmlElement(name = "clients-on-same-server")
    protected String clientsOnSameServer;
    @XmlElements({
        @XmlElement(name = "run-as-identity-principal", type = RunAsIdentityPrincipal.class),
        @XmlElement(name = "run-as-principal-name", type = RunAsPrincipalName.class)
    })
    protected List<Object> runAsIdentityPrincipalOrRunAsPrincipalName;
    @XmlElement(name = "create-as-principal-name")
    protected String createAsPrincipalName;
    @XmlElement(name = "remove-as-principal-name")
    protected String removeAsPrincipalName;
    @XmlElement(name = "passivate-as-principal-name")
    protected String passivateAsPrincipalName;
    @XmlElement(name = "jndi-name")
    protected String jndiName;
    @XmlElement(name = "local-jndi-name")
    protected String localJndiName;
    @XmlElement(name = "dispatch-policy")
    protected String dispatchPolicy;
    @XmlElement(name = "remote-client-timeout")
    protected String remoteClientTimeout;

    /**
     * ejbName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEjbName() {
        return ejbName;
    }

    /**
     * ejbName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEjbName(String value) {
        this.ejbName = value;
    }

    /**
     * Gets the value of the entityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityDescriptor }
     * {@link StatelessSessionDescriptor }
     * {@link StatefulSessionDescriptor }
     * {@link MessageDrivenDescriptor }
     * 
     * 
     */
    public List<Object> getEntityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor() {
        if (entityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor == null) {
            entityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor = new ArrayList<Object>();
        }
        return this.entityDescriptorOrStatelessSessionDescriptorOrStatefulSessionDescriptorOrMessageDrivenDescriptor;
    }

    /**
     * transactionDescriptor 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link TransactionDescriptor }
     *     
     */
    public TransactionDescriptor getTransactionDescriptor() {
        return transactionDescriptor;
    }

    /**
     * transactionDescriptor 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionDescriptor }
     *     
     */
    public void setTransactionDescriptor(TransactionDescriptor value) {
        this.transactionDescriptor = value;
    }

    /**
     * iiopSecurityDescriptor 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link IiopSecurityDescriptor }
     *     
     */
    public IiopSecurityDescriptor getIiopSecurityDescriptor() {
        return iiopSecurityDescriptor;
    }

    /**
     * iiopSecurityDescriptor 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link IiopSecurityDescriptor }
     *     
     */
    public void setIiopSecurityDescriptor(IiopSecurityDescriptor value) {
        this.iiopSecurityDescriptor = value;
    }

    /**
     * referenceDescriptor 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDescriptor }
     *     
     */
    public ReferenceDescriptor getReferenceDescriptor() {
        return referenceDescriptor;
    }

    /**
     * referenceDescriptor 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDescriptor }
     *     
     */
    public void setReferenceDescriptor(ReferenceDescriptor value) {
        this.referenceDescriptor = value;
    }

    /**
     * enableCallByReference 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnableCallByReference() {
        return enableCallByReference;
    }

    /**
     * enableCallByReference 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnableCallByReference(String value) {
        this.enableCallByReference = value;
    }

    /**
     * clientsOnSameServer 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientsOnSameServer() {
        return clientsOnSameServer;
    }

    /**
     * clientsOnSameServer 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientsOnSameServer(String value) {
        this.clientsOnSameServer = value;
    }

    /**
     * Gets the value of the runAsIdentityPrincipalOrRunAsPrincipalName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the runAsIdentityPrincipalOrRunAsPrincipalName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRunAsIdentityPrincipalOrRunAsPrincipalName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RunAsIdentityPrincipal }
     * {@link RunAsPrincipalName }
     * 
     * 
     */
    public List<Object> getRunAsIdentityPrincipalOrRunAsPrincipalName() {
        if (runAsIdentityPrincipalOrRunAsPrincipalName == null) {
            runAsIdentityPrincipalOrRunAsPrincipalName = new ArrayList<Object>();
        }
        return this.runAsIdentityPrincipalOrRunAsPrincipalName;
    }

    /**
     * createAsPrincipalName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateAsPrincipalName() {
        return createAsPrincipalName;
    }

    /**
     * createAsPrincipalName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateAsPrincipalName(String value) {
        this.createAsPrincipalName = value;
    }

    /**
     * removeAsPrincipalName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoveAsPrincipalName() {
        return removeAsPrincipalName;
    }

    /**
     * removeAsPrincipalName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoveAsPrincipalName(String value) {
        this.removeAsPrincipalName = value;
    }

    /**
     * passivateAsPrincipalName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassivateAsPrincipalName() {
        return passivateAsPrincipalName;
    }

    /**
     * passivateAsPrincipalName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassivateAsPrincipalName(String value) {
        this.passivateAsPrincipalName = value;
    }

    /**
     * jndiName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJndiName() {
        return jndiName;
    }

    /**
     * jndiName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJndiName(String value) {
        this.jndiName = value;
    }

    /**
     * localJndiName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalJndiName() {
        return localJndiName;
    }

    /**
     * localJndiName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalJndiName(String value) {
        this.localJndiName = value;
    }

    /**
     * dispatchPolicy 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispatchPolicy() {
        return dispatchPolicy;
    }

    /**
     * dispatchPolicy 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispatchPolicy(String value) {
        this.dispatchPolicy = value;
    }

    /**
     * remoteClientTimeout 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteClientTimeout() {
        return remoteClientTimeout;
    }

    /**
     * remoteClientTimeout 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteClientTimeout(String value) {
        this.remoteClientTimeout = value;
    }

}
