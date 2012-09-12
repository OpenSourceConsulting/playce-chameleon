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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pool",
    "entityCacheOrEntityCacheRef",
    "persistence",
    "entityClustering",
    "invalidationTarget",
    "enableDynamicQueries"
})
@XmlRootElement(name = "entity-descriptor")
public class EntityDescriptor {

    protected Pool pool;
    @XmlElements({
        @XmlElement(name = "entity-cache", type = EntityCache.class),
        @XmlElement(name = "entity-cache-ref", type = EntityCacheRef.class)
    })
    protected List<Object> entityCacheOrEntityCacheRef;
    protected Persistence persistence;
    @XmlElement(name = "entity-clustering")
    protected EntityClustering entityClustering;
    @XmlElement(name = "invalidation-target")
    protected InvalidationTarget invalidationTarget;
    @XmlElement(name = "enable-dynamic-queries")
    protected String enableDynamicQueries;

    /**
     * pool 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Pool }
     *     
     */
    public Pool getPool() {
        return pool;
    }

    /**
     * pool 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Pool }
     *     
     */
    public void setPool(Pool value) {
        this.pool = value;
    }

    /**
     * Gets the value of the entityCacheOrEntityCacheRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entityCacheOrEntityCacheRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntityCacheOrEntityCacheRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityCache }
     * {@link EntityCacheRef }
     * 
     * 
     */
    public List<Object> getEntityCacheOrEntityCacheRef() {
        if (entityCacheOrEntityCacheRef == null) {
            entityCacheOrEntityCacheRef = new ArrayList<Object>();
        }
        return this.entityCacheOrEntityCacheRef;
    }

    /**
     * persistence 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Persistence }
     *     
     */
    public Persistence getPersistence() {
        return persistence;
    }

    /**
     * persistence 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Persistence }
     *     
     */
    public void setPersistence(Persistence value) {
        this.persistence = value;
    }

    /**
     * entityClustering 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link EntityClustering }
     *     
     */
    public EntityClustering getEntityClustering() {
        return entityClustering;
    }

    /**
     * entityClustering 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityClustering }
     *     
     */
    public void setEntityClustering(EntityClustering value) {
        this.entityClustering = value;
    }

    /**
     * invalidationTarget 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link InvalidationTarget }
     *     
     */
    public InvalidationTarget getInvalidationTarget() {
        return invalidationTarget;
    }

    /**
     * invalidationTarget 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link InvalidationTarget }
     *     
     */
    public void setInvalidationTarget(InvalidationTarget value) {
        this.invalidationTarget = value;
    }

    /**
     * enableDynamicQueries 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnableDynamicQueries() {
        return enableDynamicQueries;
    }

    /**
     * enableDynamicQueries 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnableDynamicQueries(String value) {
        this.enableDynamicQueries = value;
    }

}
