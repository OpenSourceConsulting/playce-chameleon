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
    "statement",
    "preparedStatement",
    "rowPrefetchEnabled",
    "rowPrefetchSize",
    "streamChunkSize"
})
@XmlRootElement(name = "driver-params")
public class DriverParams {

    protected Statement statement;
    @XmlElement(name = "prepared-statement")
    protected PreparedStatement preparedStatement;
    @XmlElement(name = "row-prefetch-enabled")
    protected String rowPrefetchEnabled;
    @XmlElement(name = "row-prefetch-size")
    protected String rowPrefetchSize;
    @XmlElement(name = "stream-chunk-size")
    protected String streamChunkSize;

    /**
     * statement 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Statement }
     *     
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * statement 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Statement }
     *     
     */
    public void setStatement(Statement value) {
        this.statement = value;
    }

    /**
     * preparedStatement 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link PreparedStatement }
     *     
     */
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    /**
     * preparedStatement 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link PreparedStatement }
     *     
     */
    public void setPreparedStatement(PreparedStatement value) {
        this.preparedStatement = value;
    }

    /**
     * rowPrefetchEnabled 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowPrefetchEnabled() {
        return rowPrefetchEnabled;
    }

    /**
     * rowPrefetchEnabled 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowPrefetchEnabled(String value) {
        this.rowPrefetchEnabled = value;
    }

    /**
     * rowPrefetchSize 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRowPrefetchSize() {
        return rowPrefetchSize;
    }

    /**
     * rowPrefetchSize 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRowPrefetchSize(String value) {
        this.rowPrefetchSize = value;
    }

    /**
     * streamChunkSize 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreamChunkSize() {
        return streamChunkSize;
    }

    /**
     * streamChunkSize 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreamChunkSize(String value) {
        this.streamChunkSize = value;
    }

}
