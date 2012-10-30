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

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.ssh.Scp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	 * Repository 위치의 파일을 서버로 업로드한다.
	 * </pre>
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param localFile
	 * @param todir
	 * @param keyfile
	 * @param trust
	 */
	public static void upload(String host, int port, String username, String password, String localFile, String todir, String keyfile, boolean trust) {
		String destination = username + "@" + host + ":" + todir;
		
		logger.debug("[scp upload] " + localFile + " - " + destination);
		Project project = new Project();
		
		Scp scp = new Scp();
		// Ant Project Property
		scp.setProject(project);
		scp.setVerbose(true);

		// Set Scp properties 
		scp.setPort(port);
		scp.setPassword(password);
		scp.setFile(localFile);
		scp.setTodir(destination);
		scp.setTrust(trust);
		
		if( keyfile != null) {
			scp.setKeyfile(keyfile);
		}
		
		scp.execute();
	}//end of upload()
}
//end of ScpUtil.java