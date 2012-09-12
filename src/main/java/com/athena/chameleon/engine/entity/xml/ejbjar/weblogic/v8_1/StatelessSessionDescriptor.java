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
    "pool",
    "statelessClustering"
})
@XmlRootElement(name = "stateless-session-descriptor")
public class StatelessSessionDescriptor {

    protected Pool pool;
    @XmlElement(name = "stateless-clustering")
    protected StatelessClustering statelessClustering;

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
     * statelessClustering 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link StatelessClustering }
     *     
     */
    public StatelessClustering getStatelessClustering() {
        return statelessClustering;
    }

    /**
     * statelessClustering 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link StatelessClustering }
     *     
     */
    public void setStatelessClustering(StatelessClustering value) {
        this.statelessClustering = value;
    }

}
