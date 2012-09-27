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

import java.util.List;
import java.util.Map;

import com.athena.chameleon.engine.core.analyzer.Dependency;

/**
 * PDF 생성을 위한 root pojo object
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class PDFMetadataDefinition {

    //파일 요약 정보
    private FileSummary fileSummary;
    //의존성 분석 정보
    private Map<DependencyAnalyzeType, List<Dependency>> dependency;
    //ear 분석 정보
    //war 분석 정보
    
    /**
     * @return the fileSummary
     */
    public FileSummary getFileSummary() {
        return fileSummary;
    }
    /**
     * @param fileSummary the fileSummary to set
     */
    public void setFileSummary(FileSummary fileSummary) {
        this.fileSummary = fileSummary;
    }
    /**
     * @return the dependency
     */
    public Map<DependencyAnalyzeType, List<Dependency>> getDependency() {
        return dependency;
    }
    /**
     * @param dependency the dependency to set
     */
    public void setDependency(Map<DependencyAnalyzeType, List<Dependency>> dependency) {
        this.dependency = dependency;
    }
    
    
}
//end of PDFDefination.java