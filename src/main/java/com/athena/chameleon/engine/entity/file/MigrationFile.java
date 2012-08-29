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
 * Hyo-jeong Lee	2012. 8. 29.		First Draft.
 */
package com.athena.chameleon.engine.entity.file;

import java.util.HashMap;
import java.lang.String;;

/**
 * This class is a Value Object class for Migration File.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class MigrationFile {

    private String                   fileName;
    private HashMap<Integer, String> lineMap;
    
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
     * @return the lineMap
     */
    public HashMap<Integer, String> getLineMap() {
        return lineMap;
    }
    /**
     * @param lineMap the lineMap to set
     */
    public void setLineMap(HashMap<Integer, String> lineMap) {
        this.lineMap = lineMap;
    }
      
}
//end of MigrationFile.java