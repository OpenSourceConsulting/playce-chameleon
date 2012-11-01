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

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.ssh.Scp;
import org.apache.tools.ant.types.FileSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.athena.peacock.engine.core.TargetHost;

/**
 * <pre>
 * 목적지 호스트로의 scp 명령을 수행하기 위한 유틸리티 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ScpUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ScpUtil.class);
	
	/**
	 * <pre>
	 * source에 해당하는 파일(또는 디렉토리)를 지정된 호스트의 target으로 전송한다.
	 * </pre>
	 * @param targetHost
	 * @param source
	 * @param target
	 */
	public static void upload(TargetHost targetHost, String source, String target) {

		Assert.notNull(targetHost, "targetHost cannot be null.");
		Assert.notNull(source, "source cannot be null.");
		Assert.notNull(target, "target cannot be null.");
		
		String destination = targetHost.getUsername() + "@" + targetHost.getHost() + ":" + target;
		
		logger.debug("[scp upload] " + source + " - " + destination);
		
		Project project = new Project();
		
		Scp scp = new Scp();
		
		// Ant Project Property
		scp.setProject(project);
		scp.setVerbose(true);

		// Set Scp properties 
		scp.setPort(targetHost.getPort());
		scp.setPassword(targetHost.getPassword());
		scp.setTodir(destination);
		scp.setTrust(targetHost.isTrust());
		
		// 전달받은 source가 디렉토리 일 경우 FileSet을 scp에 추가한다.
		File filesetDir = new File(source);
		if(filesetDir.isDirectory()) {
			FileSet fileSet = new FileSet();
			fileSet.setDir(filesetDir);
	    	fileSet.setProject(project);
	    	
	    	scp.addFileset(fileSet);
		} else {
			scp.setFile(source);
		}
		
		if( targetHost.getKeyfile() != null) {
			scp.setKeyfile(targetHost.getKeyfile());
		}
		
		scp.execute();
	}//end of upload()
	
}
//end of ScpUtil.java