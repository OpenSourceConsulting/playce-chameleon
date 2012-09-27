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
 * Sang-cheon Park	2012. 9. 25.		First Draft.
 */
package com.athena.chameleon.engine.threadpool.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.Dependency;
import com.athena.chameleon.engine.policy.Policy;

/**
 * <pre>
 * JAVA, JSP, Properties 파일등 일반 파일에 대하여 상용 WAS에 대한 의존성 분석을 위한 Runnable Task
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class RegularFileDependencyCheckTask extends BaseTask {

	private File file;
	private String rootPath;
	private Policy policy;
	
	public RegularFileDependencyCheckTask(File file, String rootPath, Policy policy) {
		this(file.getAbsoluteFile() + " Dependency Check Task", file, rootPath, policy);
	}
	
	public RegularFileDependencyCheckTask(String taskName, File file, String rootPath, Policy policy) {
		super(taskName);
		this.file = file;
		this.rootPath = rootPath;
		this.policy = policy;
	}

	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.threadpool.task.BaseTask#taskRun()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void taskRun() {
		Pattern pattern = policy.getPattern();
		Matcher match = null;

		try {
			Dependency dependency = new Dependency();
			dependency.setFileName(file.getAbsolutePath().substring(rootPath.length() + 1));
			dependency.setExtension(file.getName().substring(file.getName().lastIndexOf(".") + 1));
		
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			String lineStr = null;
			int lineNum = 1;
			while ((lineStr = buffer.readLine()) != null) {
			    match = pattern.matcher(lineStr);
			    if (match.matches()) {
			    	dependency.addDependencyStrMap(new String("#" + Integer.toString(lineNum)), lineStr);
			    }
			    
				lineNum++;
			}
			
			IOUtils.closeQuietly(buffer);
			
			if (dependency.getDependencyStrMap().size() > 0) {
				List<Dependency> dependencyFileList = (List<Dependency>)ThreadLocalUtil.get(ChameleonConstants.DEPENDENCY_FILE_LIST);
				
				if (dependencyFileList == null) {
					dependencyFileList = new ArrayList<Dependency>();
					ThreadLocalUtil.add(ChameleonConstants.DEPENDENCY_FILE_LIST, dependencyFileList);
				}
	    		
				dependencyFileList.add(dependency);
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException has occurred : ", e);
		} catch (IOException e) {
			logger.error("IOException has occurred : ", e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}//end of RegularFileDependencyCheckTask.java