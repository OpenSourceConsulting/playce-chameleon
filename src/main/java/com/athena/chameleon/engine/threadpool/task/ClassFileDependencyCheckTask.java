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

import java.io.File;

import com.athena.chameleon.engine.policy.Policy;

/**
 * <pre>
 * Class 파일에 대하여 상용 WAS에 대한 의존석 분석을 위한 Runnable Task
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ClassFileDependencyCheckTask extends BaseTask {

	private File file;
	private String rootPath;
	private Policy policy;
	
	public ClassFileDependencyCheckTask(File file, String rootPath, Policy policy) {
		this(file.getAbsoluteFile() + " Dependency Check Task", file, rootPath, policy);
	}
	
	public ClassFileDependencyCheckTask(String taskName, File file, String rootPath, Policy policy) {
		super(taskName);
		this.file = file;
		this.rootPath = rootPath;
		this.policy = policy;
	}
	
	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.threadpool.task.BaseTask#taskRun()
	 */
	@Override
	protected void taskRun() {
		// TODO Auto-generated method stub

	}

}//end of ClassFileDependencyCheckTask.java