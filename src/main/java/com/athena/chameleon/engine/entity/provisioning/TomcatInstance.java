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
 * Hyo-jeong Lee	2012. 10. 30.		First Draft.
 */
package com.athena.chameleon.engine.entity.provisioning;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This Genre class is a Value Object class for Tomcat Instance.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class TomcatInstance implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //Server IP
    private String serverIp;
    //Server Port
    private String serverPort;
    //CATALINA_BASE
    private String catalinaBase;
    //신규설치 여부
    private String newInstallYn;
    //JAVA_HOME
    private String javaHome;
    //CATALINA_HOME
    private String catalinaHome;
    //SERVER_NAME
    private String serverName;
    //HTTP_PORT
    private String httpPort;
    //AJP_PORT
    private String ajpPort;
    //SSL_PORT
    private String sslPort;
    //SHUTDOWN_PORT
    private String shutdownPort;
    //SSH-Login-Id
    private String sshLoginId;
    //SSH-Login-password
    private String sshLoginPassword;
    //USER_ACCOUNT
    private String userAccount;

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
	 * @return the javaHome
	 */
	public String getJavaHome() {
		return javaHome;
	}

	/**
	 * @param javaHome the javaHome to set
	 */
	public void setJavaHome(String javaHome) {
		this.javaHome = javaHome;
	}

	/**
	 * @return the catalinaHome
	 */
	public String getCatalinaHome() {
		return catalinaHome;
	}

	/**
	 * @param catalinaHome the catalinaHome to set
	 */
	public void setCatalinaHome(String catalinaHome) {
		this.catalinaHome = catalinaHome;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the httpPort
	 */
	public String getHttpPort() {
		return httpPort;
	}

	/**
	 * @param httpPort the httpPort to set
	 */
	public void setHttpPort(String httpPort) {
		this.httpPort = httpPort;
	}

	/**
	 * @return the sslPort
	 */
	public String getSslPort() {
		return sslPort;
	}

	/**
	 * @param sslPort the sslPort to set
	 */
	public void setSslPort(String sslPort) {
		this.sslPort = sslPort;
	}

	/**
	 * @return the shutdownPort
	 */
	public String getShutdownPort() {
		return shutdownPort;
	}

	/**
	 * @param shutdownPort the shutdownPort to set
	 */
	public void setShutdownPort(String shutdownPort) {
		this.shutdownPort = shutdownPort;
	}

	/**
	 * @return the sshLoginId
	 */
	public String getSshLoginId() {
		return sshLoginId;
	}

	/**
	 * @param sshLoginId the sshLoginId to set
	 */
	public void setSshLoginId(String sshLoginId) {
		this.sshLoginId = sshLoginId;
	}

	/**
	 * @return the sshLoginPassword
	 */
	public String getSshLoginPassword() {
		return sshLoginPassword;
	}

	/**
	 * @param sshLoginPassword the sshLoginPassword to set
	 */
	public void setSshLoginPassword(String sshLoginPassword) {
		this.sshLoginPassword = sshLoginPassword;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

	/**
	 * @return the catalinaBase
	 */
	public String getCatalinaBase() {
		return catalinaBase;
	}

	/**
	 * @param catalinaBase the catalinaBase to set
	 */
	public void setCatalinaBase(String catalinaBase) {
		this.catalinaBase = catalinaBase;
	}

	/**
	 * @return the ajpPort
	 */
	public String getAjpPort() {
		return ajpPort;
	}

	/**
	 * @param ajpPort the ajpPort to set
	 */
	public void setAjpPort(String ajpPort) {
		this.ajpPort = ajpPort;
	}

	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

}
