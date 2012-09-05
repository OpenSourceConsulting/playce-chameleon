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
 * 	  The ejb-linkType is used by ejb-link
 * 	  elements in the ejb-ref or ejb-local-ref elements to specify
 * 	  that an EJB reference is linked to enterprise bean.
 * 
 * 	  The value of the ejb-link element must be the ejb-name of an
 * 	  enterprise bean in the same ejb-jar file or in another ejb-jar
 * 	  file in the same Java EE application unit.
 * 
 * 	  Alternatively, the name in the ejb-link element may be
 * 	  composed of a path name specifying the ejb-jar containing the
 * 	  referenced enterprise bean with the ejb-name of the target
 * 	  bean appended and separated from the path name by "#".  The
 * 	  path name is relative to the Deployment File containing
 * 	  Deployment Component that is referencing the enterprise
 * 	  bean.  This allows multiple enterprise beans with the same
 * 	  ejb-name to be uniquely identified.
 * 
 * 	  Examples:
 * 
 * 	      <ejb-link>EmployeeRecord</ejb-link>
 * 
 * 	      <ejb-link>../products/product.jar#ProductEJB</ejb-link>
 * 
 * 	  
 *       
 * 
 * <p>ejb-linkType complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="ejb-linkType">
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
@XmlType(name = "ejb-linkType")
public class EjbLinkType
    extends String
{


}
