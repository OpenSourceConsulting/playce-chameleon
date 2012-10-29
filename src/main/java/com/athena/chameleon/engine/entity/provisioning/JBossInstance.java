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
 * Hyo-jeong Lee	2012. 9. 13.		First Draft.
 */
package com.athena.chameleon.engine.entity.provisioning;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * This Genre class is a Value Object class for Upload.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class JBossInstance implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //Server IP
    private String serverIp;
    //Server Port
    private String serverPort;
    //JBoss Engine Home
    private String engineHome;
    //신규설치 여부
    private String newInstallYn;
    //JBoss Instance Home
    private String instanceHome;
    //JBoss Engine Name
    private String engineName;
    //Port Group
    private String portGroup;

    /**
     * @return the serverIp
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * @param serverIp the serverIp to set
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    /**
     * @return the serverPort
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * @param serverPort the serverPort to set
     */
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * @return the engineHome
     */
    public String getEngineHome() {
        return engineHome;
    }

    /**
     * @param engineHome the engineHome to set
     */
    public void setEngineHome(String engineHome) {
        this.engineHome = engineHome;
    }

    /**
     * @return the instanceHome
     */
    public String getInstanceHome() {
        return instanceHome;
    }

    /**
     * @param instanceHome the instanceHome to set
     */
    public void setInstanceHome(String instanceHome) {
        this.instanceHome = instanceHome;
    }

    /**
     * @return the newInstallYn
     */
    public String getNewInstallYn() {
        return newInstallYn;
    }

    /**
     * @param newInstallYn the newInstallYn to set
     */
    public void setNewInstallYn(String newInstallYn) {
        this.newInstallYn = newInstallYn;
    }

    /**
     * @return the engineName
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * @param engineName the engineName to set
     */
    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    /**
     * @return the portGroup
     */
    public String getPortGroup() {
        return portGroup;
    }

    /**
     * @param portGroup the portGroup to set
     */
    public void setPortGroup(String portGroup) {
        this.portGroup = portGroup;
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
