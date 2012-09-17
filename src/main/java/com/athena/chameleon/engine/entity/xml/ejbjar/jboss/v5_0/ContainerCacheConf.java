//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 02:02:31 PM KST 
//


package com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0;

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
    "cachePolicy",
    "cachePolicyConf",
    "cachePolicyConfOther"
})
@XmlRootElement(name = "container-cache-conf")
public class ContainerCacheConf {

    @XmlElement(name = "cache-policy")
    protected String cachePolicy;
    @XmlElement(name = "cache-policy-conf")
    protected CachePolicyConf cachePolicyConf;
    @XmlElement(name = "cache-policy-conf-other")
    protected CachePolicyConfOther cachePolicyConfOther;

    /**
     * cachePolicy 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCachePolicy() {
        return cachePolicy;
    }

    /**
     * cachePolicy 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCachePolicy(String value) {
        this.cachePolicy = value;
    }

    /**
     * cachePolicyConf 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CachePolicyConf }
     *     
     */
    public CachePolicyConf getCachePolicyConf() {
        return cachePolicyConf;
    }

    /**
     * cachePolicyConf 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CachePolicyConf }
     *     
     */
    public void setCachePolicyConf(CachePolicyConf value) {
        this.cachePolicyConf = value;
    }

    /**
     * cachePolicyConfOther 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link CachePolicyConfOther }
     *     
     */
    public CachePolicyConfOther getCachePolicyConfOther() {
        return cachePolicyConfOther;
    }

    /**
     * cachePolicyConfOther 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link CachePolicyConfOther }
     *     
     */
    public void setCachePolicyConfOther(CachePolicyConfOther value) {
        this.cachePolicyConfOther = value;
    }

}
