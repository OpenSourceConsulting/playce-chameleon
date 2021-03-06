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
    "homeIsClusterable",
    "homeLoadAlgorithm",
    "homeCallRouterClassName",
    "useServersideStubs",
    "statelessBeanIsClusterable",
    "statelessBeanLoadAlgorithm",
    "statelessBeanCallRouterClassName",
    "statelessBeanMethodsAreIdempotent"
})
@XmlRootElement(name = "stateless-clustering")
public class StatelessClustering {

    @XmlElement(name = "home-is-clusterable")
    protected String homeIsClusterable;
    @XmlElement(name = "home-load-algorithm")
    protected String homeLoadAlgorithm;
    @XmlElement(name = "home-call-router-class-name")
    protected String homeCallRouterClassName;
    @XmlElement(name = "use-serverside-stubs")
    protected String useServersideStubs;
    @XmlElement(name = "stateless-bean-is-clusterable")
    protected String statelessBeanIsClusterable;
    @XmlElement(name = "stateless-bean-load-algorithm")
    protected String statelessBeanLoadAlgorithm;
    @XmlElement(name = "stateless-bean-call-router-class-name")
    protected String statelessBeanCallRouterClassName;
    @XmlElement(name = "stateless-bean-methods-are-idempotent")
    protected String statelessBeanMethodsAreIdempotent;

    /**
     * homeIsClusterable 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeIsClusterable() {
        return homeIsClusterable;
    }

    /**
     * homeIsClusterable 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeIsClusterable(String value) {
        this.homeIsClusterable = value;
    }

    /**
     * homeLoadAlgorithm 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeLoadAlgorithm() {
        return homeLoadAlgorithm;
    }

    /**
     * homeLoadAlgorithm 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeLoadAlgorithm(String value) {
        this.homeLoadAlgorithm = value;
    }

    /**
     * homeCallRouterClassName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCallRouterClassName() {
        return homeCallRouterClassName;
    }

    /**
     * homeCallRouterClassName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCallRouterClassName(String value) {
        this.homeCallRouterClassName = value;
    }

    /**
     * useServersideStubs 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseServersideStubs() {
        return useServersideStubs;
    }

    /**
     * useServersideStubs 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseServersideStubs(String value) {
        this.useServersideStubs = value;
    }

    /**
     * statelessBeanIsClusterable 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatelessBeanIsClusterable() {
        return statelessBeanIsClusterable;
    }

    /**
     * statelessBeanIsClusterable 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatelessBeanIsClusterable(String value) {
        this.statelessBeanIsClusterable = value;
    }

    /**
     * statelessBeanLoadAlgorithm 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatelessBeanLoadAlgorithm() {
        return statelessBeanLoadAlgorithm;
    }

    /**
     * statelessBeanLoadAlgorithm 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatelessBeanLoadAlgorithm(String value) {
        this.statelessBeanLoadAlgorithm = value;
    }

    /**
     * statelessBeanCallRouterClassName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatelessBeanCallRouterClassName() {
        return statelessBeanCallRouterClassName;
    }

    /**
     * statelessBeanCallRouterClassName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatelessBeanCallRouterClassName(String value) {
        this.statelessBeanCallRouterClassName = value;
    }

    /**
     * statelessBeanMethodsAreIdempotent 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatelessBeanMethodsAreIdempotent() {
        return statelessBeanMethodsAreIdempotent;
    }

    /**
     * statelessBeanMethodsAreIdempotent 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatelessBeanMethodsAreIdempotent(String value) {
        this.statelessBeanMethodsAreIdempotent = value;
    }

}
