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
public class EjbRecommend {

	//파일명
	private String item;
	//위치
	private String location;
	//변환여부(변환완료시 true, 변환전인 경우 false)
	private boolean transFlag;
	//내용
	private String contents;
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
     * @return the transFlag
     */
    public boolean isTransFlag() {
        return transFlag;
    }
    /**
     * @param transFlag the transFlag to set
     */
    public void setTransFlag(boolean transFlag) {
        this.transFlag = transFlag;
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
	
}
//end of FileSummary.java