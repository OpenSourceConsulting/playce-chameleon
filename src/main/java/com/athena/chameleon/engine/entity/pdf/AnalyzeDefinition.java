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

import java.util.Map;


/**
 * 분석 정보 공용 Entity
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class AnalyzeDefinition {

	private String origFileName;
	private String migrateFileName;
	private Map<FileType, FileSummary> fileSummary;

	/**
	 * @return the origFileName
	 */
	public String getOrigFileName() {
		return origFileName;
	}

	/**
	 * @param origFileName the origFileName to set
	 */
	public void setOrigFileName(String origFileName) {
		this.origFileName = origFileName;
	}

	/**
	 * @return the migrateFileName
	 */
	public String getMigrateFileName() {
		return migrateFileName;
	}

	/**
	 * @param migrateFileName the migrateFileName to set
	 */
	public void setMigrateFileName(String migrateFileName) {
		this.migrateFileName = migrateFileName;
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
}
//end of PDFDefination.java