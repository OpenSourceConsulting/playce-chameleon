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
 * Sang-cheon Park	2012. 9. 29.		First Draft.
 */
package com.athena.chameleon.engine.entity.pdf;

/**
 * <pre>
 * 프로젝트 소스 및 디플로이 애플리케이션 종류
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public enum ArchiveType {
	
	ZIP, EAR, WAR, JAR;
	
    public String value() {
        return name().toLowerCase();
    }

    public static ArchiveType fromValue(String v) {
        return valueOf(v.toUpperCase());
    }
}
//end of ArchiveType.java