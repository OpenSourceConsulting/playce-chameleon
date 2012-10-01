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
 * 소스 파일 요약 Entity
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class CommonAnalyze {

	//항목
	private String item;
	//위치
	private String location;
	//내용
    private String contents;
    //비고
    private String note;
    //directive
    private String directive;
    //파일갯수
    private int fileCount;
    //Xml Object
    private Object xmlObject;
	//xml Data List
    private List<CommonAnalyze> xmlDataList;
    
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the directive
	 */
	public String getDirective() {
		return directive;
	}
	/**
	 * @param directive the directive to set
	 */
	public void setDirective(String directive) {
		this.directive = directive;
	}
	/**
	 * @return the fileCount
	 */
	public int getFileCount() {
		return fileCount;
	}
	/**
	 * @param fileCount the fileCount to set
	 */
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	/**
	 * @return the xmlObject
	 */
	public Object getXmlObject() {
		return xmlObject;
	}
	/**
	 * @param xmlObject the xmlObject to set
	 */
	public void setXmlObject(Object xmlObject) {
		this.xmlObject = xmlObject;
	}
	/**
	 * @return the xmlDataList
	 */
	public List<CommonAnalyze> getXmlDataList() {
		return xmlDataList;
	}
	/**
	 * @param xmlDataList the xmlDataList to set
	 */
	public void setXmlDataList(List<CommonAnalyze> xmlDataList) {
		this.xmlDataList = xmlDataList;
	}
}
//end of FileSummary.java