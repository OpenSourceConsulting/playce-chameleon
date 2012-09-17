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
    "serviceRefName",
    "serviceImplClass",
    "serviceQname",
    "configName",
    "configFile",
    "handlerChain",
    "portComponentRef",
    "wsdlOverride"
})
@XmlRootElement(name = "service-ref")
public class ServiceRef {

    @XmlElement(name = "service-ref-name", required = true)
    protected String serviceRefName;
    @XmlElement(name = "service-impl-class")
    protected String serviceImplClass;
    @XmlElement(name = "service-qname")
    protected String serviceQname;
    @XmlElement(name = "config-name")
    protected String configName;
    @XmlElement(name = "config-file")
    protected String configFile;
    @XmlElement(name = "handler-chain")
    protected String handlerChain;
    @XmlElement(name = "port-component-ref")
    protected List<PortComponentRef> portComponentRef;
    @XmlElement(name = "wsdl-override")
    protected String wsdlOverride;

    /**
     * serviceRefName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRefName() {
        return serviceRefName;
    }

    /**
     * serviceRefName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRefName(String value) {
        this.serviceRefName = value;
    }

    /**
     * serviceImplClass 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceImplClass() {
        return serviceImplClass;
    }

    /**
     * serviceImplClass 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceImplClass(String value) {
        this.serviceImplClass = value;
    }

    /**
     * serviceQname 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceQname() {
        return serviceQname;
    }

    /**
     * serviceQname 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceQname(String value) {
        this.serviceQname = value;
    }

    /**
     * configName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * configName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigName(String value) {
        this.configName = value;
    }

    /**
     * configFile 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigFile() {
        return configFile;
    }

    /**
     * configFile 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigFile(String value) {
        this.configFile = value;
    }

    /**
     * handlerChain 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandlerChain() {
        return handlerChain;
    }

    /**
     * handlerChain 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandlerChain(String value) {
        this.handlerChain = value;
    }

    /**
     * Gets the value of the portComponentRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the portComponentRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPortComponentRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PortComponentRef }
     * 
     * 
     */
    public List<PortComponentRef> getPortComponentRef() {
        if (portComponentRef == null) {
            portComponentRef = new ArrayList<PortComponentRef>();
        }
        return this.portComponentRef;
    }

    /**
     * wsdlOverride 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlOverride() {
        return wsdlOverride;
    }

    /**
     * wsdlOverride 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlOverride(String value) {
        this.wsdlOverride = value;
    }

}
