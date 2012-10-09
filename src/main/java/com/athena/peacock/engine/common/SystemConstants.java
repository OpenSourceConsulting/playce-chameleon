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
 * Author           Date                Description
 * ---------------  ----------------    ------------
 * Ji-Woong Choi    2012. 10. 5.        First Draft.
 */
package com.athena.peacock.engine.common;
/**
 * <pre>
 * 시스템 상수를 통합 정의한 클래스.
 *
 * </pre>
 * 
 * @author 
 */
public abstract class SystemConstants {

    
    /**
     * 날짜포맷
     */
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String MYSQL_YMD_FORMAT = "%Y-%m-%d";
    public static final String MYSQL_YMDHMS_FORMAT = "%Y-%m-%d %H:%i:%s";
    
    /**
     * 쿠키 이름 상수
     */
    public static final String LANG_CD_COOKIE_NAME = "LANG";     // 언어코드
    public static final String TIMEZONE_COOKIE_NAME = "TIMEZONE";   // 시간대
    
    /**
     * Repository URL
     */
    public static final String REPOSITORY_URL = "athena.software.repository.dir";
    
    /**
     * ASI(Athena Software Image)에 들어갈 Manifest 속성 이름
     */
    public static final String ASI_NAME = "ASI Name";
    public static final String OWNER = "Owner";
    public static final String VENDOR = "Vendor";
    public static final String VERSION = "Version";
    public static final String DEFAULT_INSTALL_LOCATION = "Default Install Location";
    public static final String DESCRIPTION = "Description";
    
    /**
     * Script Configuration File Location
     */
    public static final String JBOSS_EAP_5_1 = "scripts/middleware/jboss-eap-5.1";
    
    
    public static final String HA_PROXY = "scripts/load-balancer/haproxy";
    public static final String IPVSADM = "scripts/load-balancer/ipvsadm";
}