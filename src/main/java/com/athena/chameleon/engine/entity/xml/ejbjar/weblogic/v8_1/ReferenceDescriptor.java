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
    "resourceDescription",
    "resourceEnvDescription",
    "ejbReferenceDescription",
    "ejbLocalReferenceDescription"
})
@XmlRootElement(name = "reference-descriptor")
public class ReferenceDescriptor {

    @XmlElement(name = "resource-description")
    protected List<ResourceDescription> resourceDescription;
    @XmlElement(name = "resource-env-description")
    protected List<ResourceEnvDescription> resourceEnvDescription;
    @XmlElement(name = "ejb-reference-description")
    protected List<EjbReferenceDescription> ejbReferenceDescription;
    @XmlElement(name = "ejb-local-reference-description")
    protected List<EjbLocalReferenceDescription> ejbLocalReferenceDescription;

    /**
     * Gets the value of the resourceDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceDescription }
     * 
     * 
     */
    public List<ResourceDescription> getResourceDescription() {
        if (resourceDescription == null) {
            resourceDescription = new ArrayList<ResourceDescription>();
        }
        return this.resourceDescription;
    }

    /**
     * Gets the value of the resourceEnvDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resourceEnvDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResourceEnvDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceEnvDescription }
     * 
     * 
     */
    public List<ResourceEnvDescription> getResourceEnvDescription() {
        if (resourceEnvDescription == null) {
            resourceEnvDescription = new ArrayList<ResourceEnvDescription>();
        }
        return this.resourceEnvDescription;
    }

    /**
     * Gets the value of the ejbReferenceDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ejbReferenceDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEjbReferenceDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EjbReferenceDescription }
     * 
     * 
     */
    public List<EjbReferenceDescription> getEjbReferenceDescription() {
        if (ejbReferenceDescription == null) {
            ejbReferenceDescription = new ArrayList<EjbReferenceDescription>();
        }
        return this.ejbReferenceDescription;
    }

    /**
     * Gets the value of the ejbLocalReferenceDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ejbLocalReferenceDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEjbLocalReferenceDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EjbLocalReferenceDescription }
     * 
     * 
     */
    public List<EjbLocalReferenceDescription> getEjbLocalReferenceDescription() {
        if (ejbLocalReferenceDescription == null) {
            ejbLocalReferenceDescription = new ArrayList<EjbLocalReferenceDescription>();
        }
        return this.ejbLocalReferenceDescription;
    }

}
