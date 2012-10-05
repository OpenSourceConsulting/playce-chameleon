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
    "ejb",
    "xml",
    "jdbcConnectionPool",
    "security",
    "applicationParam",
    "classloaderStructure",
    "listener",
    "startup",
    "shutdown"
})
@XmlRootElement(name = "weblogic-application")
public class WeblogicApplication {

    protected Ejb ejb;
    protected Xml xml;
    @XmlElement(name = "jdbc-connection-pool")
    protected List<JdbcConnectionPool> jdbcConnectionPool;
    protected Security security;
    @XmlElement(name = "application-param")
    protected List<ApplicationParam> applicationParam;
    @XmlElement(name = "classloader-structure")
    protected ClassloaderStructure classloaderStructure;
    protected List<Listener> listener;
    protected List<Startup> startup;
    protected List<Shutdown> shutdown;

    /**
     * ejb 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Ejb }
     *     
     */
    public Ejb getEjb() {
        return ejb;
    }

    /**
     * ejb 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Ejb }
     *     
     */
    public void setEjb(Ejb value) {
        this.ejb = value;
    }

    /**
     * xml 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Xml }
     *     
     */
    public Xml getXml() {
        return xml;
    }

    /**
     * xml 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Xml }
     *     
     */
    public void setXml(Xml value) {
        this.xml = value;
    }

    /**
     * Gets the value of the jdbcConnectionPool property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jdbcConnectionPool property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJdbcConnectionPool().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JdbcConnectionPool }
     * 
     * 
     */
    public List<JdbcConnectionPool> getJdbcConnectionPool() {
        if (jdbcConnectionPool == null) {
            jdbcConnectionPool = new ArrayList<JdbcConnectionPool>();
        }
        return this.jdbcConnectionPool;
    }

    /**
     * security 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Security }
     *     
     */
    public Security getSecurity() {
        return security;
    }

    /**
     * security 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Security }
     *     
     */
    public void setSecurity(Security value) {
        this.security = value;
    }

    /**
     * Gets the value of the applicationParam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicationParam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicationParam().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApplicationParam }
     * 
     * 
     */
    public List<ApplicationParam> getApplicationParam() {
        if (applicationParam == null) {
            applicationParam = new ArrayList<ApplicationParam>();
        }
        return this.applicationParam;
    }

    /**
     * classloaderStructure 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link ClassloaderStructure }
     *     
     */
    public ClassloaderStructure getClassloaderStructure() {
        return classloaderStructure;
    }

    /**
     * classloaderStructure 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassloaderStructure }
     *     
     */
    public void setClassloaderStructure(ClassloaderStructure value) {
        this.classloaderStructure = value;
    }

    /**
     * Gets the value of the listener property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listener property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListener().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Listener }
     * 
     * 
     */
    public List<Listener> getListener() {
        if (listener == null) {
            listener = new ArrayList<Listener>();
        }
        return this.listener;
    }

    /**
     * Gets the value of the startup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the startup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStartup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Startup }
     * 
     * 
     */
    public List<Startup> getStartup() {
        if (startup == null) {
            startup = new ArrayList<Startup>();
        }
        return this.startup;
    }

    /**
     * Gets the value of the shutdown property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shutdown property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShutdown().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Shutdown }
     * 
     * 
     */
    public List<Shutdown> getShutdown() {
        if (shutdown == null) {
            shutdown = new ArrayList<Shutdown>();
        }
        return this.shutdown;
    }

}
