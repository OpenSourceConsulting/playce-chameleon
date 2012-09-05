//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.05 시간 05:58:17 PM KST 
//


package com.athena.chameleon.engine.entity.xml.webapp.v2_5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 	
 * 
 * 	  This type contains the fully-qualified Java type of the
 * 	  environment entry value that is expected by the
 * 	  application's code.
 * 
 * 	  The following are the legal values of env-entry-type-valuesType:
 * 
 * 	      java.lang.Boolean
 * 	      java.lang.Byte
 * 	      java.lang.Character
 * 	      java.lang.String
 * 	      java.lang.Short
 * 	      java.lang.Integer
 * 	      java.lang.Long
 * 	      java.lang.Float
 * 	      java.lang.Double
 * 
 * 	  Example:
 * 
 * 	  <env-entry-type>java.lang.Boolean</env-entry-type>
 * 
 * 	  
 *       
 * 
 * <p>env-entry-type-valuesType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="env-entry-type-valuesType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;http://java.sun.com/xml/ns/javaee>string">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "env-entry-type-valuesType")
public class EnvEntryTypeValuesType
    extends String
{


}
