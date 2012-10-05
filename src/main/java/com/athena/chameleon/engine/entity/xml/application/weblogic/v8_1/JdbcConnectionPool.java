//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.10.05 시간 10:52:30 AM KST 
//


package com.athena.chameleon.engine.entity.xml.application.weblogic.v8_1;

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
    "dataSourceName",
    "connectionFactory",
    "poolParams",
    "driverParams",
    "aclName"
})
@XmlRootElement(name = "jdbc-connection-pool")
public class JdbcConnectionPool {

    @XmlElement(name = "data-source-name", required = true)
    protected String dataSourceName;
    @XmlElement(name = "connection-factory", required = true)
    protected ConnectionFactory connectionFactory;
    @XmlElement(name = "pool-params")
    protected PoolParams poolParams;
    @XmlElement(name = "driver-params")
    protected DriverParams driverParams;
    @XmlElement(name = "acl-name")
    protected String aclName;

    /**
     * dataSourceName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSourceName() {
        return dataSourceName;
    }

    /**
     * dataSourceName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSourceName(String value) {
        this.dataSourceName = value;
    }

    /**
     * connectionFactory 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionFactory }
     *     
     */
    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    /**
     * connectionFactory 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionFactory }
     *     
     */
    public void setConnectionFactory(ConnectionFactory value) {
        this.connectionFactory = value;
    }

    /**
     * poolParams 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PoolParams }
     *     
     */
    public PoolParams getPoolParams() {
        return poolParams;
    }

    /**
     * poolParams 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PoolParams }
     *     
     */
    public void setPoolParams(PoolParams value) {
        this.poolParams = value;
    }

    /**
     * driverParams 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link DriverParams }
     *     
     */
    public DriverParams getDriverParams() {
        return driverParams;
    }

    /**
     * driverParams 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link DriverParams }
     *     
     */
    public void setDriverParams(DriverParams value) {
        this.driverParams = value;
    }

    /**
     * aclName 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAclName() {
        return aclName;
    }

    /**
     * aclName 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAclName(String value) {
        this.aclName = value;
    }

}
