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

import java.util.Map;

/**
 * 소스 파일 요약 Entity
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class EarAnalyzeResult {
    
    //디스크립터 정보(파일명, 위치)
    private Map<String, String> descriptor;
    
    //application.xml 정보
    private String  applicationXmlInfo;

    /**
     * @return the descriptor
     */
    public Map<String, String> getDescriptor() {
        return descriptor;
    }

    /**
     * @param descriptor the descriptor to set
     */
    public void setDescriptor(Map<String, String> descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * @return the applicationXmlInfo
     */
    public String getApplicationXmlInfo() {
        return applicationXmlInfo;
    }

    /**
     * @param applicationXmlInfo the applicationXmlInfo to set
     */
    public void setApplicationXmlInfo(String applicationXmlInfo) {
        this.applicationXmlInfo = applicationXmlInfo;
    }

}
//end of FileSummary.java