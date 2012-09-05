//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 11:44:05 AM KST 
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
    "ejbRelationshipRoleName",
    "multiplicity",
    "cascadeDelete",
    "relationshipRoleSource",
    "cmrField"
})
@XmlRootElement(name = "ejb-relationship-role")
public class EjbRelationshipRole {

    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    protected Description description;
    @XmlElement(name = "ejb-relationship-role-name")
    protected EjbRelationshipRoleName ejbRelationshipRoleName;
    @XmlElement(required = true)
    protected Multiplicity multiplicity;
    @XmlElement(name = "cascade-delete")
    protected CascadeDelete cascadeDelete;
    @XmlElement(name = "relationship-role-source", required = true)
    protected RelationshipRoleSource relationshipRoleSource;
    @XmlElement(name = "cmr-field")
    protected CmrField cmrField;

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
     * ejbRelationshipRoleName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EjbRelationshipRoleName }
     *     
     */
    public EjbRelationshipRoleName getEjbRelationshipRoleName() {
        return ejbRelationshipRoleName;
    }

    /**
     * ejbRelationshipRoleName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EjbRelationshipRoleName }
     *     
     */
    public void setEjbRelationshipRoleName(EjbRelationshipRoleName value) {
        this.ejbRelationshipRoleName = value;
    }

    /**
     * multiplicity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Multiplicity }
     *     
     */
    public Multiplicity getMultiplicity() {
        return multiplicity;
    }

    /**
     * multiplicity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Multiplicity }
     *     
     */
    public void setMultiplicity(Multiplicity value) {
        this.multiplicity = value;
    }

    /**
     * cascadeDelete 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CascadeDelete }
     *     
     */
    public CascadeDelete getCascadeDelete() {
        return cascadeDelete;
    }

    /**
     * cascadeDelete 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CascadeDelete }
     *     
     */
    public void setCascadeDelete(CascadeDelete value) {
        this.cascadeDelete = value;
    }

    /**
     * relationshipRoleSource 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link RelationshipRoleSource }
     *     
     */
    public RelationshipRoleSource getRelationshipRoleSource() {
        return relationshipRoleSource;
    }

    /**
     * relationshipRoleSource 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationshipRoleSource }
     *     
     */
    public void setRelationshipRoleSource(RelationshipRoleSource value) {
        this.relationshipRoleSource = value;
    }

    /**
     * cmrField 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CmrField }
     *     
     */
    public CmrField getCmrField() {
        return cmrField;
    }

    /**
     * cmrField 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CmrField }
     *     
     */
    public void setCmrField(CmrField value) {
        this.cmrField = value;
    }

}
