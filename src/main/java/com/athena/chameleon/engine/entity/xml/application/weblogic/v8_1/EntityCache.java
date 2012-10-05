//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.10.05 시간 10:52:30 AM KST 
//


package com.athena.chameleon.engine.entity.xml.application.weblogic.v8_1;

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
    "entityCacheName",
    "maxBeansInCacheOrMaxCacheSize",
    "cachingStrategy"
})
@XmlRootElement(name = "entity-cache")
public class EntityCache {

    @XmlElement(name = "entity-cache-name", required = true)
    protected String entityCacheName;
    @XmlElements({
        @XmlElement(name = "max-beans-in-cache", type = MaxBeansInCache.class),
        @XmlElement(name = "max-cache-size", type = MaxCacheSize.class)
    })
    protected List<Object> maxBeansInCacheOrMaxCacheSize;
    @XmlElement(name = "caching-strategy")
    protected String cachingStrategy;

    /**
     * entityCacheName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityCacheName() {
        return entityCacheName;
    }

    /**
     * entityCacheName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityCacheName(String value) {
        this.entityCacheName = value;
    }

    /**
     * Gets the value of the maxBeansInCacheOrMaxCacheSize property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the maxBeansInCacheOrMaxCacheSize property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaxBeansInCacheOrMaxCacheSize().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MaxBeansInCache }
     * {@link MaxCacheSize }
     * 
     * 
     */
    public List<Object> getMaxBeansInCacheOrMaxCacheSize() {
        if (maxBeansInCacheOrMaxCacheSize == null) {
            maxBeansInCacheOrMaxCacheSize = new ArrayList<Object>();
        }
        return this.maxBeansInCacheOrMaxCacheSize;
    }

    /**
     * cachingStrategy 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCachingStrategy() {
        return cachingStrategy;
    }

    /**
     * cachingStrategy 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCachingStrategy(String value) {
        this.cachingStrategy = value;
    }

}
