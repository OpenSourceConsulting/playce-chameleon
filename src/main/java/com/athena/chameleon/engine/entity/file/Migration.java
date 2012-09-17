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
 * Hyo-jeong Lee	2012. 9. 17.		First Draft.
 */
package com.athena.chameleon.engine.entity.file;

/**
 * Temp Migration Output Entity
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class Migration {
    
    private String fileListStr;
    private String checkFileListStr;
    private String webXmlStr;
    private String applicationXmlStr;
    private String ejbXmlStr;
    /**
     * @return the fileListStr
     */
    public String getFileListStr() {
        return fileListStr;
    }
    /**
     * @param fileListStr the fileListStr to set
     */
    public void setFileListStr(String fileListStr) {
        this.fileListStr = fileListStr;
    }
    /**
     * @return the checkFileListStr
     */
    public String getCheckFileListStr() {
        return checkFileListStr;
    }
    /**
     * @param checkFileListStr the checkFileListStr to set
     */
    public void setCheckFileListStr(String checkFileListStr) {
        this.checkFileListStr = checkFileListStr;
    }
    /**
     * @return the webXmlStr
     */
    public String getWebXmlStr() {
        return webXmlStr;
    }
    /**
     * @param webXmlStr the webXmlStr to set
     */
    public void setWebXmlStr(String webXmlStr) {
        this.webXmlStr = webXmlStr;
    }
    /**
     * @return the applicationXmlStr
     */
    public String getApplicationXmlStr() {
        return applicationXmlStr;
    }
    /**
     * @param applicationXmlStr the applicationXmlStr to set
     */
    public void setApplicationXmlStr(String applicationXmlStr) {
        this.applicationXmlStr = applicationXmlStr;
    }
    /**
     * @return the ejbXmlStr
     */
    public String getEjbXmlStr() {
        return ejbXmlStr;
    }
    /**
     * @param ejbXmlStr the ejbXmlStr to set
     */
    public void setEjbXmlStr(String ejbXmlStr) {
        this.ejbXmlStr = ejbXmlStr;
    }
    
    

}
//end of Migration.java