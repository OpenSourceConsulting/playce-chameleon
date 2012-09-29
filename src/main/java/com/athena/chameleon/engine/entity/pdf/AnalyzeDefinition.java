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


/**
 * 분석 정보 공용 Entity
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class AnalyzeDefinition {

	private String fileName;
	
	//파일 요약정보
	private Map<FileType, FileSummary> fileSummary;
	//servlet 상속 정보
	private List<CommonAnalyze> servletExtendsList;
	//EJB 상속 정보
	private List<CommonAnalyze> ejbExtendsList;
	//java 의존성 정보 
	private List<Dependency> javaDependencyList;
	//class 의존성 정보 
	private List<Dependency> classDependencyList;
	//jsp 의존성 정보 
	private List<Dependency> jspDependencyList;
	//jsp 소스 파일 분석
	private List<CommonAnalyze> jspAnalyzeList;
	//디스크립터 정보 
	private List<CommonAnalyze> descripterList;
	//ejb application 
	private List<CommonAnalyze> ejbApplicationList;
	//lib 구성 정보
	private List<String> libConstList;
	//classes 구성 정보
    private List<ClassAnalyze> classesConstList;

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileSummary
	 */
	public Map<FileType, FileSummary> getFileSummary() {
		return fileSummary;
	}

	/**
	 * @param fileSummary the fileSummary to set
	 */
	public void setFileSummary(Map<FileType, FileSummary> fileSummary) {
		this.fileSummary = fileSummary;
	}

	/**
	 * @return the servletExtendsList
	 */
	public List<CommonAnalyze> getServletExtendsList() {
		return servletExtendsList;
	}

	/**
	 * @param servletExtendsList the servletExtendsList to set
	 */
	public void setServletExtendsList(List<CommonAnalyze> servletExtendsList) {
		this.servletExtendsList = servletExtendsList;
	}

	/**
	 * @return the ejbExtendsList
	 */
	public List<CommonAnalyze> getEjbExtendsList() {
		return ejbExtendsList;
	}

	/**
	 * @param ejbExtendsList the ejbExtendsList to set
	 */
	public void setEjbExtendsList(List<CommonAnalyze> ejbExtendsList) {
		this.ejbExtendsList = ejbExtendsList;
	}

	/**
	 * @return the javaDependencyList
	 */
	public List<Dependency> getJavaDependencyList() {
		return javaDependencyList;
	}

	/**
	 * @param javaDependencyList the javaDependencyList to set
	 */
	public void setJavaDependencyList(List<Dependency> javaDependencyList) {
		this.javaDependencyList = javaDependencyList;
	}

	/**
	 * @return the classDependencyList
	 */
	public List<Dependency> getClassDependencyList() {
		return classDependencyList;
	}

	/**
	 * @param classDependencyList the classDependencyList to set
	 */
	public void setClassDependencyList(List<Dependency> classDependencyList) {
		this.classDependencyList = classDependencyList;
	}

	/**
	 * @return the jspDependencyList
	 */
	public List<Dependency> getJspDependencyList() {
		return jspDependencyList;
	}

	/**
	 * @param jspDependencyList the jspDependencyList to set
	 */
	public void setJspDependencyList(List<Dependency> jspDependencyList) {
		this.jspDependencyList = jspDependencyList;
	}

	/**
	 * @return the jspAnalyzeList
	 */
	public List<CommonAnalyze> getJspAnalyzeList() {
		return jspAnalyzeList;
	}

	/**
	 * @param jspAnalyzeList the jspAnalyzeList to set
	 */
	public void setJspAnalyzeList(List<CommonAnalyze> jspAnalyzeList) {
		this.jspAnalyzeList = jspAnalyzeList;
	}

	/**
	 * @return the descripterList
	 */
	public List<CommonAnalyze> getDescripterList() {
		return descripterList;
	}

	/**
	 * @param descripterList the descripterList to set
	 */
	public void setDescripterList(List<CommonAnalyze> descripterList) {
		this.descripterList = descripterList;
	}

	/**
	 * @return the ejbApplicationList
	 */
	public List<CommonAnalyze> getEjbApplicationList() {
		return ejbApplicationList;
	}

	/**
	 * @param ejbApplicationList the ejbApplicationList to set
	 */
	public void setEjbApplicationList(List<CommonAnalyze> ejbApplicationList) {
		this.ejbApplicationList = ejbApplicationList;
	}

	/**
	 * @return the libConstList
	 */
	public List<String> getLibConstList() {
		return libConstList;
	}

	/**
	 * @param libConstList the libConstList to set
	 */
	public void setLibConstList(List<String> libConstList) {
		this.libConstList = libConstList;
	}

	/**
	 * @return the classesConstList
	 */
	public List<ClassAnalyze> getClassesConstList() {
		return classesConstList;
	}

	/**
	 * @param classesConstList the classesConstList to set
	 */
	public void setClassesConstList(List<ClassAnalyze> classesConstList) {
		this.classesConstList = classesConstList;
	}

}
//end of PDFDefination.java