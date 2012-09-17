//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2012.09.17 시간 01:57:10 PM KST 
//


package com.athena.chameleon.engine.entity.xml.application.jboss.v4_2;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.athena.chameleon.engine.entity.xml.application.jboss.v4_2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.athena.chameleon.engine.entity.xml.application.jboss.v4_2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrincipalName }
     * 
     */
    public PrincipalName createPrincipalName() {
        return new PrincipalName();
    }

    /**
     * Create an instance of {@link Module }
     * 
     */
    public Module createModule() {
        return new Module();
    }

    /**
     * Create an instance of {@link Service }
     * 
     */
    public Service createService() {
        return new Service();
    }

    /**
     * Create an instance of {@link Har }
     * 
     */
    public Har createHar() {
        return new Har();
    }

    /**
     * Create an instance of {@link Web }
     * 
     */
    public Web createWeb() {
        return new Web();
    }

    /**
     * Create an instance of {@link SecurityRole }
     * 
     */
    public SecurityRole createSecurityRole() {
        return new SecurityRole();
    }

    /**
     * Create an instance of {@link JbossApp }
     * 
     */
    public JbossApp createJbossApp() {
        return new JbossApp();
    }

    /**
     * Create an instance of {@link LoaderRepository }
     * 
     */
    public LoaderRepository createLoaderRepository() {
        return new LoaderRepository();
    }

    /**
     * Create an instance of {@link LoaderRepositoryConfig }
     * 
     */
    public LoaderRepositoryConfig createLoaderRepositoryConfig() {
        return new LoaderRepositoryConfig();
    }

}
