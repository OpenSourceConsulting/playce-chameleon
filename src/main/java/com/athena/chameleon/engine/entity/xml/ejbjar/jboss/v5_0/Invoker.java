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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "invokerProxyBindingName",
    "jndiName",
    "ejbRef"
})
@XmlRootElement(name = "invoker")
public class Invoker {

    @XmlElement(name = "invoker-proxy-binding-name", required = true)
    protected InvokerProxyBindingName invokerProxyBindingName;
    @XmlElement(name = "jndi-name")
    protected JndiName jndiName;
    @XmlElement(name = "ejb-ref")
    protected List<EjbRef> ejbRef;

    /**
     * invokerProxyBindingName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link InvokerProxyBindingName }
     *     
     */
    public InvokerProxyBindingName getInvokerProxyBindingName() {
        return invokerProxyBindingName;
    }

    /**
     * invokerProxyBindingName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link InvokerProxyBindingName }
     *     
     */
    public void setInvokerProxyBindingName(InvokerProxyBindingName value) {
        this.invokerProxyBindingName = value;
    }

    /**
     * jndiName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link JndiName }
     *     
     */
    public JndiName getJndiName() {
        return jndiName;
    }

    /**
     * jndiName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link JndiName }
     *     
     */
    public void setJndiName(JndiName value) {
        this.jndiName = value;
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

}
