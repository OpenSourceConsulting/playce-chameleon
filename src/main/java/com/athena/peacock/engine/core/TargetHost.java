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
    /**
     * Serialiable
     */
    private static final long serialVersionUID = 1L;

    /**
     * Callback 받을 URL 
     */
    private String callback;

    /**
     * 프로비저닝 대상 Hostname
     */
    private String hostname;

    
    /**
     * 프로비저닝 대상의 SSH Port 번호
     */
    private Integer port;

    /**
     * 프로비저닝 대상 Host의 Shell 계정
     */
    private String username;

    /**
     * 프로비저닝 대상 Host의 Shell 패스워드
     */
    private String password;
    
    
    
    /**
     * ssh key file
     */
    private String keyfile;


    
    
    /**
     * Hostname을 반환
     * @return
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Hostname을 설정
     * @param hostname
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    
    /**
     * SSH Port 반환
     * @return
     */
    public Integer getPort() {
        return port;
    }

    /**
     * SSH 포트 설정
     * @param port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * SSH 사용자 ID 반환
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * SSH 사용자 ID 설정
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * SSH 사용자 패스워드 반환 
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * SSH 사용자 패스워드 설정
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public String getKeyfile() {
        return keyfile;
    }

    public void setKeyfile(String keyfile) {
        this.keyfile = keyfile;
    }


    /**
     * 문자열로 반환
     */
    @Override
    public String toString() {
        return "Action [callback=" + callback + ", hostname=" + hostname
                + ", port=" + port + ", username="
                + username + ", password=" + password + "]";
    }
}

//end of TargetHost.java