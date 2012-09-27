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
 * Hyo-jeong Lee	2012. 9. 27.		First Draft.
 */
package com.athena.chameleon.engine.entity.pdf;

/**
 * 파일 유형 enum
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public enum FileType {
	
    JAVA, HTML, HTM, JSP, JS, CSS, XML, PROPERTIES, JAR, DIRECTORY, SUM;

    public String toString() {
    	if(this.equals(DIRECTORY) || this.equals(SUM)) {
    		return super.toString();
    	} else {
    		return "*." + super.toString().toLowerCase();
    	}
    }
    
    public String value() {
        return name();
    }

    public static FileType fromValue(String v) {
        return valueOf(v);
    }

}
//end of FileType.java