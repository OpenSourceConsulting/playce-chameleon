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

/**
 * 소스 파일 요약 Entity
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class ClassAnalyze {

	//클래스명
	private String className;
	//Super Class
	private List<String> superClasses;
	//Interfaces
	private List<String> interfaces;
	//Class Modifier
	private String classModifier;
	//Final class 여부
	private boolean isFinalClass;
	//Fields
	private List<String> filedList;
	//Methods
	private List<String> methodList;
	//Constructors
	private List<String> constructorList;
	//inner classes
	private List<ClassAnalyze> classAnalyzeList;
	
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the superClasses
	 */
	public List<String> getSuperClasses() {
		if(superClasses == null) {
			superClasses = new ArrayList<String>();
		}
		return superClasses;
	}
	/**
	 * @return the interfaces
	 */
	public List<String> getInterfaces() {
		if(interfaces == null) {
			interfaces = new ArrayList<String>();
		}
		return interfaces;
	}
	/**
	 * @return the classModifier
	 */
	public String getClassModifier() {
		return classModifier;
	}
	/**
	 * @param classModifier the classModifier to set
	 */
	public void setClassModifier(String classModifier) {
		this.classModifier = classModifier;
	}
	/**
	 * @return the isFinalClass
	 */
	public boolean isFinalClass() {
		return isFinalClass;
	}
	/**
	 * @param isFinalClass the isFinalClass to set
	 */
	public void setFinalClass(boolean isFinalClass) {
		this.isFinalClass = isFinalClass;
	}
	/**
	 * @return the filedList
	 */
	public List<String> getFiledList() {
		if(filedList == null) {
			filedList = new ArrayList<String>();
		}
		return filedList;
	}
	/**
	 * @return the methodList
	 */
	public List<String> getMethodList() {
		if(methodList == null) {
			methodList = new ArrayList<String>();
		}
		return methodList;
	}
	/**
	 * @return the constructorList
	 */
	public List<String> getConstructorList() {
		if(constructorList == null) {
			constructorList = new ArrayList<String>();
		}
		return constructorList;
	}
	/**
	 * @return the classAnalyzeList
	 */
	public List<ClassAnalyze> getClassAnalyzeList() {
		if(classAnalyzeList == null) {
			classAnalyzeList = new ArrayList<ClassAnalyze>();
		}
		return classAnalyzeList;
	}
	
}
//end of FileSummary.java