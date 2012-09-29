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
import java.util.TreeMap;

/**
 * PDF 생성을 위한 root pojo object
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class PDFMetadataDefinition {

	//zip 분석정보
    private AnalyzeDefinition zipDefinition;
    //ear 분석정보
    private AnalyzeDefinition earDefinition;
    //war 분석정보
    private Map<String, AnalyzeDefinition> warDefinitionMap;
    //jar 분석정보
    private Map<String, AnalyzeDefinition> jarDefinitionMap;
    //변환 xml 정보(파일명, 파일내용)
    private Map<String, String> transXmlInfo;
    //변환 대상 파일 리스트
    private List<String> transFileList;
    
    /**
	 * @return the zipDefinition
	 */
	public AnalyzeDefinition getZipDefinition() {
		return zipDefinition;
	}
	/**
	 * @param zipDefinition the zipDefinition to set
	 */
	public void setZipDefinition(AnalyzeDefinition zipDefinition) {
		this.zipDefinition = zipDefinition;
	}
	/**
	 * @return the earDefinition
	 */
	public AnalyzeDefinition getEarDefinition() {
		return earDefinition;
	}
	/**
	 * @param earDefinition the earDefinition to set
	 */
	public void setEarDefinition(AnalyzeDefinition earDefinition) {
		this.earDefinition = earDefinition;
	}
	/**
	 * @return the warDefinitionMap
	 */
	public Map<String, AnalyzeDefinition> getWarDefinitionMap() {
		return warDefinitionMap;
	}
	/**
	 * @param fileName
	 * @param warDefinition
	 */
	public void addWarDefinitionMap(String fileName, AnalyzeDefinition warDefinition) {
		if(warDefinitionMap == null) {
			warDefinitionMap = new TreeMap<String, AnalyzeDefinition>();
		}
		
		warDefinitionMap.put(fileName, warDefinition);
	}
	/**
	 * @param warDefinitionMap the warDefinitionMap to set
	 */
	public void setWarDefinitionMap(Map<String, AnalyzeDefinition> warDefinitionMap) {
		this.warDefinitionMap = warDefinitionMap;
	}
	/**
	 * @return the jarDefinitionMap
	 */
	public Map<String, AnalyzeDefinition> getJarDefinitionMap() {
		return jarDefinitionMap;
	}
	/**
	 * @param fileName
	 * @param jarDefinition
	 */
	public void addJarDefinitionMap(String fileName, AnalyzeDefinition jarDefinition) {
		if(jarDefinitionMap == null) {
			jarDefinitionMap = new TreeMap<String, AnalyzeDefinition>();
		}
		
		jarDefinitionMap.put(fileName, jarDefinition);
	}
	/**
	 * @param jarDefinitionMap the jarDefinitionMap to set
	 */
	public void setJarDefinitionMap(Map<String, AnalyzeDefinition> jarDefinitionMap) {
		this.jarDefinitionMap = jarDefinitionMap;
	}
	/**
	 * @return the transXmlInfo
	 */
	public Map<String, String> getTransXmlInfo() {
		return transXmlInfo;
	}
	/**
	 * @param transXmlInfo the transXmlInfo to set
	 */
	public void setTransXmlInfo(Map<String, String> transXmlInfo) {
		this.transXmlInfo = transXmlInfo;
	}
	/**
	 * @return the transFileList
	 */
	public List<String> getTransFileList() {
		return transFileList;
	}
	/**
	 * @param transFileList the transFileList to set
	 */
	public void setTransFileList(List<String> transFileList) {
		this.transFileList = transFileList;
	}
}
//end of PDFDefination.java