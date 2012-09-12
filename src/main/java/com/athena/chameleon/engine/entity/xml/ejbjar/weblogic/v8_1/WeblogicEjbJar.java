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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "weblogicEnterpriseBean",
    "securityRoleAssignment",
    "runAsRoleAssignment",
    "securityPermission",
    "transactionIsolation",
    "idempotentMethods",
    "enableBeanClassRedeploy",
    "disableWarning"
})
@XmlRootElement(name = "weblogic-ejb-jar")
public class WeblogicEjbJar {

    protected String description;
    @XmlElement(name = "weblogic-enterprise-bean")
    protected List<WeblogicEnterpriseBean> weblogicEnterpriseBean;
    @XmlElement(name = "security-role-assignment")
    protected List<SecurityRoleAssignment> securityRoleAssignment;
    @XmlElement(name = "run-as-role-assignment")
    protected List<RunAsRoleAssignment> runAsRoleAssignment;
    @XmlElement(name = "security-permission")
    protected SecurityPermission securityPermission;
    @XmlElement(name = "transaction-isolation")
    protected List<TransactionIsolation> transactionIsolation;
    @XmlElement(name = "idempotent-methods")
    protected IdempotentMethods idempotentMethods;
    @XmlElement(name = "enable-bean-class-redeploy")
    protected String enableBeanClassRedeploy;
    @XmlElement(name = "disable-warning")
    protected List<DisableWarning> disableWarning;

    /**
     * description 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the weblogicEnterpriseBean property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weblogicEnterpriseBean property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeblogicEnterpriseBean().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WeblogicEnterpriseBean }
     * 
     * 
     */
    public List<WeblogicEnterpriseBean> getWeblogicEnterpriseBean() {
        if (weblogicEnterpriseBean == null) {
            weblogicEnterpriseBean = new ArrayList<WeblogicEnterpriseBean>();
        }
        return this.weblogicEnterpriseBean;
    }

    /**
     * Gets the value of the securityRoleAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securityRoleAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurityRoleAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityRoleAssignment }
     * 
     * 
     */
    public List<SecurityRoleAssignment> getSecurityRoleAssignment() {
        if (securityRoleAssignment == null) {
            securityRoleAssignment = new ArrayList<SecurityRoleAssignment>();
        }
        return this.securityRoleAssignment;
    }

    /**
     * Gets the value of the runAsRoleAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the runAsRoleAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRunAsRoleAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RunAsRoleAssignment }
     * 
     * 
     */
    public List<RunAsRoleAssignment> getRunAsRoleAssignment() {
        if (runAsRoleAssignment == null) {
            runAsRoleAssignment = new ArrayList<RunAsRoleAssignment>();
        }
        return this.runAsRoleAssignment;
    }

    /**
     * securityPermission 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link SecurityPermission }
     *     
     */
    public SecurityPermission getSecurityPermission() {
        return securityPermission;
    }

    /**
     * securityPermission 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityPermission }
     *     
     */
    public void setSecurityPermission(SecurityPermission value) {
        this.securityPermission = value;
    }

    /**
     * Gets the value of the transactionIsolation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionIsolation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionIsolation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionIsolation }
     * 
     * 
     */
    public List<TransactionIsolation> getTransactionIsolation() {
        if (transactionIsolation == null) {
            transactionIsolation = new ArrayList<TransactionIsolation>();
        }
        return this.transactionIsolation;
    }

    /**
     * idempotentMethods 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link IdempotentMethods }
     *     
     */
    public IdempotentMethods getIdempotentMethods() {
        return idempotentMethods;
    }

    /**
     * idempotentMethods 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link IdempotentMethods }
     *     
     */
    public void setIdempotentMethods(IdempotentMethods value) {
        this.idempotentMethods = value;
    }

    /**
     * enableBeanClassRedeploy 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnableBeanClassRedeploy() {
        return enableBeanClassRedeploy;
    }

    /**
     * enableBeanClassRedeploy 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnableBeanClassRedeploy(String value) {
        this.enableBeanClassRedeploy = value;
    }

    /**
     * Gets the value of the disableWarning property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the disableWarning property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisableWarning().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DisableWarning }
     * 
     * 
     */
    public List<DisableWarning> getDisableWarning() {
        if (disableWarning == null) {
            disableWarning = new ArrayList<DisableWarning>();
        }
        return this.disableWarning;
    }

}
