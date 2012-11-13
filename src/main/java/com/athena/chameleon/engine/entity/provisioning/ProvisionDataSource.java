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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This Genre class is a Value Object class for Data Source.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class ProvisionDataSource implements Serializable {

    private static final long serialVersionUID = 1L;

    //Database 유형
    private String databaseType;
    //JNDI 이름
    private String jndiName;
    //Connection URL
    private String connectionUrl;
    //User Name
    private String userName;
    //Password
    private String password;
    //Min Pool Size
    private String minPoolSize;
    //Max Pool Size
    private String maxPoolSize;

    /**
     * @return the databaseType
     */
    public String getDatabaseType() {
        return databaseType;
    }

    /**
     * @param databaseType the databaseType to set
     */
    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    /**
     * @return the jndiName
     */
    public String getJndiName() {
        return jndiName;
    }

    /**
     * @param jndiName the jndiName to set
     */
    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }

    /**
     * @return the connectionUrl
     */
    public String getConnectionUrl() {
        return connectionUrl;
    }

    /**
     * @param connectionUrl the connectionUrl to set
     */
    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the minPoolSize
     */
    public String getMinPoolSize() {
        return minPoolSize;
    }

    /**
     * @param minPoolSize the minPoolSize to set
     */
    public void setMinPoolSize(String minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    /**
     * @return the maxPoolSize
     */
    public String getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * @param maxPoolSize the maxPoolSize to set
     */
    public void setMaxPoolSize(String maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
