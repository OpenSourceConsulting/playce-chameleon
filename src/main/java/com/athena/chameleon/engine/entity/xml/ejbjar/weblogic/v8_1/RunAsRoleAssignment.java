//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.12 시간 04:26:39 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1;

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
    "roleName",
    "runAsPrincipalName"
})
@XmlRootElement(name = "run-as-role-assignment")
public class RunAsRoleAssignment {

    @XmlElement(name = "role-name", required = true)
    protected String roleName;
    @XmlElement(name = "run-as-principal-name", required = true)
    protected RunAsPrincipalName runAsPrincipalName;

    /**
     * roleName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * roleName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    /**
     * runAsPrincipalName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link RunAsPrincipalName }
     *     
     */
    public RunAsPrincipalName getRunAsPrincipalName() {
        return runAsPrincipalName;
    }

    /**
     * runAsPrincipalName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link RunAsPrincipalName }
     *     
     */
    public void setRunAsPrincipalName(RunAsPrincipalName value) {
        this.runAsPrincipalName = value;
    }

}
