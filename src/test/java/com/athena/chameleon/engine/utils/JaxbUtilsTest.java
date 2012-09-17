/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * Sang-cheon Park	2012. 9. 6.		First Draft.
 */
package com.athena.chameleon.engine.utils;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import com.athena.chameleon.engine.entity.xml.application.jeus.v5_0.JeusSystemType;
import com.athena.chameleon.engine.entity.xml.application.v1_4.ApplicationType;
import com.athena.chameleon.engine.entity.xml.application.weblogic.v1_0.WeblogicApplicationType;
import com.athena.chameleon.engine.entity.xml.ejbjar.jeus.v5_0.JeusEjbDdType;
import com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0.WeblogicEjbJarType;
import com.athena.chameleon.engine.entity.xml.webapp.v2_5.WebAppType;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JaxbUtilsTest {
    
    @Test
    public void webAppV25Test() {
        String xml = this.getClass().getResource("/files/webapp/web.xml").getFile();
        File file = new File(xml);
        
        try {
            Object obj = JaxbUtils.unmarshal(WebAppType.class.getPackage().getName(), this.getClass().getResource("/files/webapp/").getFile(), file.getName());
            
            System.out.println(obj.getClass().getCanonicalName());
            
            String xmlData = JaxbUtils.marshal(WebAppType.class.getPackage().getName(), obj, new String[]{"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"});
            System.out.println(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error");
        }
    }

    @Test
    public void appV14Test() {
        String xml = this.getClass().getResource("/files/application/application.xml").getFile();
        File file = new File(xml);
        
        try {
            Object obj = JaxbUtils.unmarshal(ApplicationType.class.getPackage().getName(), this.getClass().getResource("/files/application/").getFile(), file.getName());
            
            System.out.println(obj.getClass().getCanonicalName());
            
            String xmlData = JaxbUtils.marshal(ApplicationType.class.getPackage().getName(), obj, new String[]{"http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/application_1_4.xsd"});
            System.out.println(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error");
        }
    }

    @Test
    public void jeusEjbDdV50Test() {
        String xml = this.getClass().getResource("/files/jeusejbdd/jeus-ejb-dd.xml").getFile();
        File file = new File(xml);
        
        try {
            Object obj = JaxbUtils.unmarshal(JeusEjbDdType.class.getPackage().getName(), this.getClass().getResource("/files/jeusejbdd/").getFile(), file.getName());
            
            System.out.println(obj.getClass().getCanonicalName());
            
            String xmlData = JaxbUtils.marshal(JeusEjbDdType.class.getPackage().getName(), obj, new String[]{"http://www.tmaxsoft.com/xml/ns/jeus schema/v5_0/jeus-ejb-dd.xsd"});
            System.out.println(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error");
        }
    }

    @Test
    public void weblogicEjbJarV90Test() {
        String xml = this.getClass().getResource("/files/weblogicejbjar/weblogic-ejb-jar.xml").getFile();
        File file = new File(xml);
        
        try {
            Object obj = JaxbUtils.unmarshal(WeblogicEjbJarType.class.getPackage().getName(), this.getClass().getResource("/files/weblogicejbjar/").getFile(), file.getName());
            
            System.out.println(obj.getClass().getCanonicalName());
            
            String xmlData = JaxbUtils.marshal(WeblogicEjbJarType.class.getPackage().getName(), obj, new String[]{"http://www.bea.com/ns/weblogic/90 http://www.bea.com/ns/weblogic/910/weblogic-ejb-jar.xsd"});
            System.out.println(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error");
        }
    }

    @Test
    public void weblogicApplicationV10Test() {
        String xml = this.getClass().getResource("/files/application/weblogic/weblogic-application.xml").getFile();
        File file = new File(xml);
        
        try {
            Object obj = JaxbUtils.unmarshal(WeblogicApplicationType.class.getPackage().getName(), this.getClass().getResource("/files/application/weblogic/").getFile(), file.getName());
            
            System.out.println(obj.getClass().getCanonicalName());
            
            String xmlData = JaxbUtils.marshal(WeblogicApplicationType.class.getPackage().getName(), obj, new String[]{"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/javaee_5.xsd", "http://www.bea.com/ns/weblogic/weblogic-application http://www.bea.com/ns/weblogic/weblogic-application/1.0/weblogic-application.xsd"});
            System.out.println(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error");
        }
    }

    @Test
    public void jeusMainV50Test() {
        String xml = this.getClass().getResource("/files/application/jeus/v5_0/jeus-main.xml").getFile();
        File file = new File(xml);
        
        try {
            Object obj = JaxbUtils.unmarshal(JeusSystemType.class.getPackage().getName(), this.getClass().getResource("/files/application/jeus/v5_0/").getFile(), file.getName());
            
            System.out.println(obj.getClass().getCanonicalName());
            
            String xmlData = JaxbUtils.marshal(JeusSystemType.class.getPackage().getName(), obj, new String[]{"http://www.tmaxsoft.com/xml/ns/jeus schema/jeus-main.xsd"});
            System.out.println(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error");
        }
    }

    @Test
    public void jeusMainV60Test() {
        String xml = this.getClass().getResource("/files/application/jeus/v6_0/jeus-main.xml").getFile();
        File file = new File(xml);
        
        try {
            Object obj = JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.application.jeus.v6_0.JeusSystemType.class.getPackage().getName(), this.getClass().getResource("/files/application/jeus/v6_0/").getFile(), file.getName());
            
            System.out.println(obj.getClass().getCanonicalName());
            
            String xmlData = JaxbUtils.marshal(com.athena.chameleon.engine.entity.xml.application.jeus.v6_0.JeusSystemType.class.getPackage().getName(), obj, new String[]{"http://www.tmaxsoft.com/xml/ns/jeus schema/jeus-main.xsd"});
            System.out.println(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error");
        }
    }

}
//end of JaxbUtilsTest.java