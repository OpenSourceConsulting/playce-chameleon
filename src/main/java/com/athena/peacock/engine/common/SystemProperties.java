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
 * Author           Date                Description
 * ---------------  ----------------    ------------
 * Ji-Woong Choi    2012. 10. 5.        First Draft.
 */
package com.athena.peacock.engine.common;

import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Loading System Properties files.
 * 
 * Basically, system.properties file will be loaded in the classpath.
 * 
 * @author Ji-Woong Choi(jchoi@osci.kr)
 */
@Service
public class SystemProperties {

	private static final Logger logger = LoggerFactory.getLogger(SystemProperties.class);

	/**
	 * Properties object
	 */
	private Properties properties = new Properties();


	/**
	 * Default private constructor.
	 */
	private SystemProperties() {
	
	}

	/**
	 * Load properties file depends on server mode 
	 */
	@PostConstruct
	protected void loadProperties() {
		String serverMode = System.getProperty("server.mode");
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>> debug ::: " + serverMode  + "<<<");
		String[] propertiesFiles = { "provisioning/property" + serverMode + "/system.properties" };

		this.properties.clear();
		
		for (String propertiesFile : propertiesFiles) {
			Properties temp = new Properties();
			InputStream in = null;
			try {
				in = getClass().getClassLoader().getResourceAsStream(propertiesFile);
				temp.load(in);
				this.properties.putAll(temp);
			} catch (Exception e) {
				logger.error("Error during load [" + propertiesFile + "].", e);
			} finally {
				IOUtils.closeQuietly(in);
			}
		}
	}

	/**
	 * Returns property value of key.
	 * 
	 * @param key key in properties file
	 * @return value of key
	 */
	public String get(String key) {
		return this.properties.getProperty(key);
	}

}