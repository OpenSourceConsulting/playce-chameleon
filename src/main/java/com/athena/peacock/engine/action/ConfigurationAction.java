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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

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
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationAction.class);
	
    /**
     * 프로비저닝 시 변경되어야 할 파일
     */
    private String fileName;
    
    /**
     * 파일 내의 변경되어야 할 항목들에 대한 목록
     */
    private List<Property> properties;
    
    public ConfigurationAction(String fileName, List<Property> properties) {
    	this.fileName = fileName;
    	this.properties = properties;
    }

    /* (non-Javadoc)
     * @see com.athena.peacock.engine.action.Action#perform()
     */
    @Override
    public void perform() {
    	Assert.notNull(fileName, "filename cannot be null.");
    	Assert.notNull(properties, "properties cannot be null.");
    	
    	File file = new File(fileName);
    	
    	Assert.isTrue(file.exists(), fileName + " does not exist.");
    	Assert.isTrue(file.isFile(), fileName + " is not a file.");
    	
    	logger.debug("[{}] file's configuration will be changed.", fileName);
    	
    	try {
			String fileContents = IOUtils.toString(file.toURI());
			
			for(Property property : properties) {
				logger.debug("\"${{}}\" will be changed to \"{}\".", property.getKey(), property.getValue().replaceAll("\\\\", ""));
				fileContents = fileContents.replaceAll("\\$\\{" + property.getKey() + "\\}", property.getValue());
			}
			
			FileOutputStream fos = new FileOutputStream(file);
			IOUtils.write(fileContents, fos);
			IOUtils.closeQuietly(fos);
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		}
    }//end of perform()
    
}
//end of Configuration.java