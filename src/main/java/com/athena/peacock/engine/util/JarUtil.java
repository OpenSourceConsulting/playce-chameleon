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
 * Sang-cheon Park	2012. 10. 30.		First Draft.
 */
package com.athena.peacock.engine.util;

import java.io.File;
import java.util.Map;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.Manifest.Attribute;
import org.apache.tools.ant.types.FileSet;

import com.athena.peacock.engine.common.SystemConstants;

/**
 * <pre>
 * JAR Packaging을 위한 유틸리티 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JarUtil {
	
	/**
	 * 입력된 디렉토리를 이용하여 압축 파일을 만든다.
	 * @param jarName
	 * @param directoryName
	 * @throws Exception 
	 */
	public static void compressDirectory(String jarName, String directoryName, Map<String, String> attributes) throws Exception {
		Project project = new Project();
		
		String repositoryDir = PropertyUtil.getProperty(SystemConstants.REPOSITORY_URL);
		File resultJarFile = new File(repositoryDir, jarName);
		File filesetDir = new File(directoryName);
		
		Jar jar = new Jar();
		jar.setProject(project);
		
		// Set fileset information
		jar.setDestFile(resultJarFile);
		
		FileSet fileSet = new FileSet();
		fileSet.setDir(filesetDir);
		jar.addFileset(fileSet);
		
		// Create manifest
		Manifest manifest = new Manifest();

        for( Map.Entry<String, String> entry : attributes.entrySet() ){
        	Attribute attribute = new Attribute();
        	attribute.setName(entry.getKey());
        	attribute.setValue(entry.getValue());
        	manifest.addConfiguredAttribute(attribute);
        }
        jar.addConfiguredManifest(manifest);
		
		jar.execute();
	}//end of compressDirectory()
	
	/**
	 * 입력된 파일의 압축을 푼다.
	 * @param asiName
	 * @param targetDir
	 * @throws Exception 
	 */
	public static void decompressAsi(String asiName, String targetDir) throws Exception {
		Project project = new Project();
		
		String repositoryDir = PropertyUtil.getProperty(SystemConstants.REPOSITORY_URL);
		File src = new File(repositoryDir, asiName);
		File dest = new File(targetDir);
		
		Expand zip = new Expand();
		zip.setProject(project);
		
		// Set fileset information
		zip.setSrc(src);
		zip.setDest(dest);
		
		zip.execute();
	}//end of decompressAsi()
}
//end of JarUtil.java