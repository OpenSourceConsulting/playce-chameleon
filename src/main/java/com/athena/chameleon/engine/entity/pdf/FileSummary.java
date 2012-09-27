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

/**
 * 소스 파일 요약 Entity
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class FileSummary {
    
    //파일 확장자
    private FileType fileType;
    //파일 갯수
    private int fileCount;
    //인코딩유형
    private String sourceEncoding = "N/A";
    //결과유형
    private String targetEncoding = "N/A";
    
    /**
     * @return the fileType
     */
    public FileType getFileType() {
        return fileType;
    }
    /**
     * @param fileType the fileType to set
     */
    public void setFileType(FileType fileType) {
        this.fileType = fileType;
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
     * @return the sourceEncoding
     */
    public String getSourceEncoding() {
        return sourceEncoding;
    }
    /**
     * @param sourceEncoding the sourceEncoding to set
     */
    public void setSourceEncoding(String sourceEncoding) {
        this.sourceEncoding = sourceEncoding;
    }
    /**
     * @return the targetEncoding
     */
    public String getTargetEncoding() {
        return targetEncoding;
    }
    /**
     * @param targetEncoding the targetEncoding to set
     */
    public void setTargetEncoding(String targetEncoding) {
        this.targetEncoding = targetEncoding;
    }
    
    public synchronized void addCount(){
        this.fileCount++;
    }

}
//end of FileSummary.java