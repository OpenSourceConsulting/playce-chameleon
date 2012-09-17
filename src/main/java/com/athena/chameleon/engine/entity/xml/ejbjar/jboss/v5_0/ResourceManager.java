//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 02:02:31 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0;

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
    "resName",
    "resJndiNameOrResUrl"
})
@XmlRootElement(name = "resource-manager")
public class ResourceManager {

    @XmlElement(name = "res-name", required = true)
    protected String resName;
    @XmlElements({
        @XmlElement(name = "res-jndi-name", required = true, type = ResJndiName.class),
        @XmlElement(name = "res-url", required = true, type = ResUrl.class)
    })
    protected List<Object> resJndiNameOrResUrl;

    /**
     * resName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResName() {
        return resName;
    }

    /**
     * resName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResName(String value) {
        this.resName = value;
    }

    /**
     * Gets the value of the resJndiNameOrResUrl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resJndiNameOrResUrl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResJndiNameOrResUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResJndiName }
     * {@link ResUrl }
     * 
     * 
     */
    public List<Object> getResJndiNameOrResUrl() {
        if (resJndiNameOrResUrl == null) {
            resJndiNameOrResUrl = new ArrayList<Object>();
        }
        return this.resJndiNameOrResUrl;
    }

}
