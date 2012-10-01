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
import java.util.HashMap;
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
	private Map<FileType, FileSummary> fileSummaryMap;
	//servlet 상속 정보
	private List<CommonAnalyze> servletExtendsList;
	//EJB 상속 정보
	private List<CommonAnalyze> ejbExtendsList;
	//java 의존성 정보 
	private List<Dependency> javaDependencyList;
	//jsp 의존성 정보 
	private List<Dependency> jspDependencyList;
	//properties 의존성 정보 
	private List<Dependency> propertyDependencyList;
	//class 의존성 정보 
	private List<Dependency> classDependencyList;
	//jsp 소스 파일 분석
	private Map<String, Integer> jspDirectiveMap;
	//디스크립터 정보 
	private List<CommonAnalyze> descripterList;
	//ejb application 
	private List<CommonAnalyze> ejbApplicationList;
	//library 구성 정보
	private List<String> libraryList;
	//삭제 라이브러리 목록 정보
	private List<String> deleteLibraryList;
	//class 파일 갯수
	private int classFileCount;
	//class 디렉토리 갯수
	private int classDirCount;
	
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
	 * @return the fileSummaryMap
	 */
	public Map<FileType, FileSummary> getFileSummaryMap() {
		return fileSummaryMap;
	}

	/**
	 * @param fileSummaryMap the fileSummaryMap to set
	 */
	public void setFileSummaryMap(Map<FileType, FileSummary> fileSummaryMap) {
		this.fileSummaryMap = fileSummaryMap;
	}

	/**
	 * @return the servletExtendsList
	 */
	public List<CommonAnalyze> getServletExtendsList() {
		if(servletExtendsList == null) {
			servletExtendsList = new ArrayList<CommonAnalyze>();
		}
		return servletExtendsList;
	}

	/**
	 * @return the ejbExtendsList
	 */
	public List<CommonAnalyze> getEjbExtendsList() {
		if(ejbExtendsList == null) {
			ejbExtendsList = new ArrayList<CommonAnalyze>();
		}
		return ejbExtendsList;
	}

	/**
	 * @return the javaDependencyList
	 */
	public List<Dependency> getJavaDependencyList() {
		if(javaDependencyList == null) {
			javaDependencyList = new ArrayList<Dependency>();
		}
		return javaDependencyList;
	}

	/**
	 * @return the jspDependencyList
	 */
	public List<Dependency> getJspDependencyList() {
		if(jspDependencyList == null) {
			jspDependencyList = new ArrayList<Dependency>();
		}
		return jspDependencyList;
	}

	/**
	 * @return the classDependencyList
	 */
	public List<Dependency> getClassDependencyList() {
		if(classDependencyList == null) {
			classDependencyList = new ArrayList<Dependency>();
		}
		return classDependencyList;
	}

	/**
	 * @return the propertyDependencyList
	 */
	public List<Dependency> getPropertyDependencyList() {
		if(propertyDependencyList == null) {
			propertyDependencyList = new ArrayList<Dependency>();
		}
		return propertyDependencyList;
	}

	/**
	 * @return the jspDirectiveMap
	 */
	public Map<String, Integer> getJspDirectiveMap() {
		if(jspDirectiveMap == null) {
			jspDirectiveMap = new HashMap<String, Integer>();
		}
		return jspDirectiveMap;
	}
	
	/**
	 * @param jspDirectiveMap the jspDirectiveMap to set
	 */
	public void setJspDirectiveMap(Map<String, Integer> jspDirectiveMap) {
		this.jspDirectiveMap = jspDirectiveMap;
	}
	
	/**
	 * @return the jspDirectiveList
	 */
	public void addJspDirectiveCount(String directive) {
		int cnt = getJspDirectiveMap().get(directive) == null ? 0 : getJspDirectiveMap().get(directive);
		getJspDirectiveMap().put(directive, ++cnt);
	}

	/**
	 * @return the descripterList
	 */
	public List<CommonAnalyze> getDescripterList() {
		if(descripterList == null) {
			descripterList = new ArrayList<CommonAnalyze>();
		}
		return descripterList;
	}

	/**
	 * @return the ejbApplicationList
	 */
	public List<CommonAnalyze> getEjbApplicationList() {
		if(ejbApplicationList == null) {
			ejbApplicationList = new ArrayList<CommonAnalyze>();
		}
		return ejbApplicationList;
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
     *    getLibraryList().add(newItem);
     * </pre>
     * 
	 * @return the libraryList
	 */
	public List<String> getLibraryList() {
		if(libraryList == null) {
			libraryList = new ArrayList<String>();
		}
		return libraryList;
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
     *    getDeleteLibraryList().add(newItem);
     * </pre>
     * 
	 * @return the deleteLibraryList
	 */
	public List<String> getDeleteLibraryList() {
		if(deleteLibraryList == null) {
			deleteLibraryList = new ArrayList<String>();
		}
		return deleteLibraryList;
	}

	/**
	 * @return the classesConstList
	 */
	public List<ClassAnalyze> getClassesConstList() {
		if(classesConstList == null) {
			classesConstList = new ArrayList<ClassAnalyze>();
		}
		return classesConstList;
	}

	/**
	 * @return the classFileCount
	 */
	public int getClassFileCount() {
		return classFileCount;
	}

	/**
	 * @param classFileCount the classFileCount to set
	 */
	public void setClassFileCount(int classFileCount) {
		this.classFileCount = classFileCount;
	}

	/**
	 * @return the classDirCount
	 */
	public int getClassDirCount() {
		return classDirCount;
	}

	/**
	 * @param classDirCount the classDirCount to set
	 */
	public void setClassDirCount(int classDirCount) {
		this.classDirCount = classDirCount;
	}

}
//end of PDFDefination.java