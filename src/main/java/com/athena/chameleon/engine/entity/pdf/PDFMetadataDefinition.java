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
    
	/**
	 * @return the zipFileSummary
	 */
	public Map<FileType, FileSummary> getZipFileSummary() {
		return zipFileSummary;
	}
	/**
	 * @param zipFileSummary the zipFileSummary to set
	 */
	public void setZipFileSummary(Map<FileType, FileSummary> zipFileSummary) {
		this.zipFileSummary = zipFileSummary;
	}
	/**
	 * @return the earFileSummary
	 */
	public Map<FileType, FileSummary> getEarFileSummary() {
		return earFileSummary;
	}
	/**
	 * @param earFileSummary the earFileSummary to set
	 */
	public void setEarFileSummary(Map<FileType, FileSummary> earFileSummary) {
		this.earFileSummary = earFileSummary;
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
     *    getWarFileSummaryList().add(newItem);
     * </pre>
     * 
	 * @return the warFileSummaryList
	 */
	public List<Map<FileType, FileSummary>> getWarFileSummaryList() {
		if(warFileSummaryList == null) {
			warFileSummaryList = new ArrayList<Map<FileType, FileSummary>>();
		}
		return warFileSummaryList;
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
     *    getJarFileSummaryList().add(newItem);
     * </pre>
     * 
	 * @return the jarFileSummaryList
	 */
	public List<Map<FileType, FileSummary>> getJarFileSummaryList() {
		if(jarFileSummaryList == null) {
			jarFileSummaryList = new ArrayList<Map<FileType, FileSummary>>();
		}
		return jarFileSummaryList;
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
	public void setDependency(
			Map<DependencyAnalyzeType, List<Dependency>> dependency) {
		this.dependency = dependency;
	}
	/**
	 * @return the descripter
	 */
	public Map<String, String> getDescripter() {
		return descripter;
	}
	/**
	 * @param descripter the descripter to set
	 */
	public void setDescripter(Map<String, String> descripter) {
		this.descripter = descripter;
	}
	/**
	 * @return the applicationXmlContent
	 */
	public String getApplicationXmlContent() {
		return applicationXmlContent;
	}
	/**
	 * @param applicationXmlContent the applicationXmlContent to set
	 */
	public void setApplicationXmlContent(String applicationXmlContent) {
		this.applicationXmlContent = applicationXmlContent;
	}
	/**
	 * @return the moduleApplicationList
	 */
	public List<ApplicationAnalyzeResult> getModuleApplicationList() {
		return moduleApplicationList;
	}
	/**
	 * @param moduleApplicationList the moduleApplicationList to set
	 */
	public void setModuleApplicationList(
			List<ApplicationAnalyzeResult> moduleApplicationList) {
		this.moduleApplicationList = moduleApplicationList;
	}
	/**
	 * @return the ejbApplicationList
	 */
	public Map<String, List<ApplicationAnalyzeResult>> getEjbApplicationList() {
		return ejbApplicationList;
	}
	/**
	 * @param ejbApplicationList the ejbApplicationList to set
	 */
	public void setEjbApplicationList(
			Map<String, List<ApplicationAnalyzeResult>> ejbApplicationList) {
		this.ejbApplicationList = ejbApplicationList;
	}
	/**
	 * @return the ejbDescripterList
	 */
	public Map<String, List<ApplicationAnalyzeResult>> getEjbDescripterList() {
		return ejbDescripterList;
	}
	/**
	 * @param ejbDescripterList the ejbDescripterList to set
	 */
	public void setEjbDescripterList(
			Map<String, List<ApplicationAnalyzeResult>> ejbDescripterList) {
		this.ejbDescripterList = ejbDescripterList;
	}
	/**
	 * @return the webXmlContents
	 */
	public List<ApplicationAnalyzeResult> getWebXmlContents() {
		return webXmlContents;
	}
	/**
	 * @param webXmlContents the webXmlContents to set
	 */
	public void setWebXmlContents(List<ApplicationAnalyzeResult> webXmlContents) {
		this.webXmlContents = webXmlContents;
	}
	/**
	 * @return the webLib
	 */
	public List<String> getWebLib() {
		return webLib;
	}
	/**
	 * @param webLib the webLib to set
	 */
	public void setWebLib(List<String> webLib) {
		this.webLib = webLib;
	}
	/**
	 * @return the webClasses
	 */
	public Map<String, List<ApplicationAnalyzeResult>> getWebClasses() {
		return webClasses;
	}
	/**
	 * @param webClasses the webClasses to set
	 */
	public void setWebClasses(Map<String, List<ApplicationAnalyzeResult>> webClasses) {
		this.webClasses = webClasses;
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