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
 * Ji-Woong Choi	2012. 10. 5.		First Draft.
 */
package com.athena.peacock.engine.core;

/**
 * <pre>
 * 프로비저닝을 실행할 서버의 정보를 담는 POJO 객체
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class TargetHost implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

    /** Callback 받을 URL */
    private String callback;

    /** 프로비저닝 대상 host */
    private String host;

    /** 프로비저닝 대상의 SSH Port 번호 */
    private Integer port;

    /** 프로비저닝 대상 Host의 Shell 계정 */
    private String username;

    /** 프로비저닝 대상 Host의 Shell 패스워드 */
    private String password;
    
    /** ssh key file */
    private String keyfile;
    
    /** is trust Y/N (default : true) */
    private boolean trust = true;

    /**
	 * @return the callback
	 */
	public String getCallback() {
		return callback;
	}

	/**
	 * @param callback the callback to set
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the keyfile
	 */
	public String getKeyfile() {
		return keyfile;
	}

	/**
	 * @param keyfile the keyfile to set
	 */
	public void setKeyfile(String keyfile) {
		this.keyfile = keyfile;
	}

	/**
	 * @return the trust
	 */
	public boolean isTrust() {
		return trust;
	}

	/**
	 * @param trust the trust to set
	 */
	public void setTrust(boolean trust) {
		this.trust = trust;
	}

	@Override
    public String toString() {
        return "[callback=" + callback 
        		+ ", host=" + host
                + ", port=" + port 
                + ", username=" + username 
                + ", password=" + password 
                + ", keyfile=" + keyfile
                + ", trust=" + trust + "]";
    }
	
}
//end of TargetHost.java