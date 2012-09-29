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
/*    
    //source file 요약 정보
    private Map<FileType, FileSummary> zipFileSummary;
    //deploy ear file 요약 정보
    private Map<FileType, FileSummary> earFileSummary;
    //deploy war file 요약 정보
    private List<Map<FileType, FileSummary>> warFileSummaryList;
    //deploy EJB jar file 요약 정보
    private List<Map<FileType, FileSummary>> jarFileSummaryList;
    //의존성 분석 정보
    private Map<DependencyAnalyzeType, List<Dependency>> dependency;
    //디스크립터 정보(파일명, 위치)
    private Map<String, String> descripter;
    //application.xml 내용(ear의 경우)
    private String 	applicationXmlContent;
    //module application
    private List<ApplicationAnalyzeResult>	moduleApplicationList;
    //ejb application(파일명, 애플리케이션 정보)
    private Map<String, List<ApplicationAnalyzeResult>> ejbApplicationList;
    //ejb descripter(파일명, 디스크립터 정보)
    private Map<String, List<ApplicationAnalyzeResult>> ejbDescripterList;
    //web.xml 내용(war의 경우)
    private List<ApplicationAnalyzeResult> webXmlContents;
    //web lib 구성
    private List<String> webLib;
    //web classes 구성(클래스명, 요약정보)
    private Map<String, List<ApplicationAnalyzeResult>> webClasses;
    //변환 xml 정보(파일명, 파일내용)
    private Map<String, String> transXmlInfo;
    //변환 대상 파일 리스트
    private List<String> transFileList;
    */
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
	 * @return the warDefinitionList
	 */
	public List<AnalyzeDefinition> getWarDefinitionList() {
		return warDefinitionList;
	}
	/**
	 * @param warDefinitionList the warDefinitionList to set
	 */
	public void setWarDefinitionList(List<AnalyzeDefinition> warDefinitionList) {
		this.warDefinitionList = warDefinitionList;
	}
	/**
	 * @return the jarDefinitionList
	 */
	public List<AnalyzeDefinition> getJarDefinitionList() {
		return jarDefinitionList;
	}
	/**
	 * @param jarDefinitionList the jarDefinitionList to set
	 */
	public void setJarDefinitionList(List<AnalyzeDefinition> jarDefinitionList) {
		this.jarDefinitionList = jarDefinitionList;
	}   
}
//end of PDFDefination.java