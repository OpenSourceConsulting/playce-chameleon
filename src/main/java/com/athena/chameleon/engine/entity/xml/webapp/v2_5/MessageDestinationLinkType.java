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
 * 	The message-destination-linkType is used to link a message
 * 	destination reference or message-driven bean to a message
 * 	destination.
 * 
 * 	The Assembler sets the value to reflect the flow of messages
 * 	between producers and consumers in the application.
 * 
 * 	The value must be the message-destination-name of a message
 * 	destination in the same Deployment File or in another
 * 	Deployment File in the same Java EE application unit.
 * 
 * 	Alternatively, the value may be composed of a path name
 * 	specifying a Deployment File containing the referenced
 * 	message destination with the message-destination-name of the
 * 	destination appended and separated from the path name by
 * 	"#". The path name is relative to the Deployment File
 * 	containing Deployment Component that is referencing the
 * 	message destination.  This allows multiple message
 * 	destinations with the same name to be uniquely identified.
 * 
 *       
 * 
 * <p>message-destination-linkType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="message-destination-linkType">
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
@XmlType(name = "message-destination-linkType")
public class MessageDestinationLinkType
    extends String
{


}
