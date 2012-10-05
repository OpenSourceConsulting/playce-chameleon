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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "parserFactory",
    "entityMapping"
})
@XmlRootElement(name = "xml")
public class Xml {

    @XmlElement(name = "parser-factory")
    protected ParserFactory parserFactory;
    @XmlElement(name = "entity-mapping")
    protected List<EntityMapping> entityMapping;

    /**
     * parserFactory 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ParserFactory }
     *     
     */
    public ParserFactory getParserFactory() {
        return parserFactory;
    }

    /**
     * parserFactory 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ParserFactory }
     *     
     */
    public void setParserFactory(ParserFactory value) {
        this.parserFactory = value;
    }

    /**
     * Gets the value of the entityMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entityMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntityMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityMapping }
     * 
     * 
     */
    public List<EntityMapping> getEntityMapping() {
        if (entityMapping == null) {
            entityMapping = new ArrayList<EntityMapping>();
        }
        return this.entityMapping;
    }

}
