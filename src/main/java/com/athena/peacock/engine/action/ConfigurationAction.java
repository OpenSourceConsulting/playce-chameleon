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
 * Ji-Woong Choi	2012. 10. 5.		First Draft.
 */
package com.athena.peacock.engine.action;

import java.util.List;

import com.athena.peacock.engine.core.Property;


/**
 * <pre>
 *  환경 설정을 해야 할 파일을 찾아 프로퍼티에 있는 실제 값으로 치환한다. 
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class ConfigurationAction implements Action {
    private String fileName;
    
    /**
     * 프로비저닝시 사용할 프로퍼티들을 정의
     */
    private List<Property> properties;

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
     * @return the properties
     */
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    /* (non-Javadoc)
     * @see com.athena.peacock.engine.action.Action#perform()
     */
    @Override
    public void perform() {
        // TODO Replace configuration variable using apache configuration
        System.out.println("Change configuration file using properties");
    } 
    
    
}
//end of Configuration.java