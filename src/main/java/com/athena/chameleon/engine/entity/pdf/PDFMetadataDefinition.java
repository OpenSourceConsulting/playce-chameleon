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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private List<AnalyzeDefinition> warDefinitionList;
    //jar 분석정보
    private List<AnalyzeDefinition> jarDefinitionList;
    //변환 xml 정보(파일명, 파일내용)
    private Map<String, String> transXmlInfo;
    //변환 대상 파일 리스트
    private List<String> transFileList;
    
	/**
	 * @return the zipDefinition
	 */
	public AnalyzeDefinition getZipDefinition() {
		if(zipDefinition == null) {
			zipDefinition = new AnalyzeDefinition();
		}
		return zipDefinition;
	}
	/**
	 * @return the earDefinition
	 */
	public AnalyzeDefinition getEarDefinition() {
		if(earDefinition == null) {
			earDefinition = new AnalyzeDefinition();
		}
		return earDefinition;
	}
	/**
	 * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present inside this object.
     * This is why there is not a <CODE>set</CODE> method for the application property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWarDefinitionList().add(newItem);
     * </pre>
     * 
	 * @return the warDefinitionList
	 */
	public List<AnalyzeDefinition> getWarDefinitionList() {
		if(warDefinitionList == null) {
			warDefinitionList = new ArrayList<AnalyzeDefinition>();
		}
		return warDefinitionList;
	}
	/**
	 * <p>
     * This accessor method returns a reference to the live list, not a snapshot.
     * Therefore any modification you make to the returned list will be present inside this object.
     * This is why there is not a <CODE>set</CODE> method for the application property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJarDefinitionList().add(newItem);
     * </pre>
     * 
	 * @return the jarDefinitionList
	 */
	public List<AnalyzeDefinition> getJarDefinitionList() {
		if(jarDefinitionList == null) {
			jarDefinitionList = new ArrayList<AnalyzeDefinition>();
		}
		return jarDefinitionList;
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