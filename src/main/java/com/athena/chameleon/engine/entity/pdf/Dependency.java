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
 * Sang-cheon Park	2012. 9. 25.		First Draft.
 */
package com.athena.chameleon.engine.entity.pdf;

import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 * JAVA, JSP, Class, Properties 파일에 대한 의존성 분석 결과가 저장될 Data Object
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class Dependency {
	
	// Full Qualified File Name
	private String fileName;
	
	// File Extension
	private String extension;
	
	// 몇 번째 라인 또는 어떤 위치(메소드, 필드 등)에 어떤 의존 클래스가 포함되어 있는지..
	private Map<String, String> dependencyStrMap;
	
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
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	/**
	 * @return the dependencyStrMap
	 */
	public Map<String, String> getDependencyStrMap() {
		if(dependencyStrMap == null) {
			dependencyStrMap = new TreeMap<String, String>();
		}
		
		return dependencyStrMap;
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public void addDependencyStrMap(String key, String value) {
		getDependencyStrMap().put(key, value);
	}
	
	/**
	 * @param dependencyStrMap the dependencyStrMap to set
	 */
	public void setDependencyStrMap(Map<String, String> dependencyStrMap) {
		this.dependencyStrMap = dependencyStrMap;
	}
}//end of Dependency.java