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

import java.io.Serializable;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class Property implements Serializable {
    /**
     * Serializable
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 프로퍼티 키
     */
    private String key;
    
    /**
     * 프로퍼티 값
     */
    private String value;
        
    
    public Property() {
        super();
    }

    public Property(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    /**
     * 프로퍼티 키 반환
     * @return
     */
    public String getKey() {
        return key;
    }
    
    /**
     * 프로퍼티 키 설정
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    /**
     * 프로퍼티 값 반환
     * @return
     */
    public String getValue() {
        return value;
    }
    
    /**
     * 프로퍼티 값 설정
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * 문자열 반환
     */
    @Override
    public String toString() {
        return "property [key=" + key + ", value=" + value + "]";
    }
}
//end of Property.java