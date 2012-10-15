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
 * Hyo-jeong Lee	2012. 10. 15.		First Draft.
 */
package com.athena.chameleon.engine.entity.provisioning;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class Provisioning implements Serializable {

    private static final long serialVersionUID = 1L;

    //대상 Was(jboss : B, tomcat : T)
    private String targetWas;
    //Jboss Instance 정보
    private JBossInstance jbossInstance;
    //Data Source 정보
    private ProvisionDataSource dataSource;
    
    /**
     * @return the targetWas
     */
    public String getTargetWas() {
        return targetWas;
    }
    /**
     * @param targetWas the targetWas to set
     */
    public void setTargetWas(String targetWas) {
        this.targetWas = targetWas;
    }
    /**
     * @return the jbossInstance
     */
    public JBossInstance getJbossInstance() {
        return jbossInstance;
    }
    /**
     * @param jbossInstance the jbossInstance to set
     */
    public void setJbossInstance(JBossInstance jbossInstance) {
        this.jbossInstance = jbossInstance;
    }
    /**
     * @return the dataSource
     */
    public ProvisionDataSource getDataSource() {
        return dataSource;
    }
    /**
     * @param dataSource the dataSource to set
     */
    public void setDataSource(ProvisionDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 
     * Model class 의 field 를 문자열로 정리하여 반환함.
     * 
     * @param object
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static String getReflectionToString(Object object) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder returnString = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);
            returnString.append(field.getName());
            returnString.append(" = ");
            try {
                returnString.append(field.get(object));
            }
            catch (IllegalArgumentException e) {
                returnString.append("IllegalArgumentException occured!!");
                returnString.append(e.toString());
            }
            catch (IllegalAccessException e) {
                returnString.append("IllegalAccessException occured!!");
                returnString.append(e.toString());
            }
            returnString.append(";");
        }
        return returnString.toString();
    }

}
//end of Provisioning.java