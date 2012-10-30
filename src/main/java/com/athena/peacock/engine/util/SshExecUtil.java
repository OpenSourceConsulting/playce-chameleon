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

import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.ssh.SSHExec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 목적지 호스트에 ssh 명령을 통해 지정된 명령을 수행하기 위한 유틸리티 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class SshExecUtil {

	private static final Logger logger = LoggerFactory.getLogger(SshExecUtil.class);
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param commands
	 */
	public static void executeCommand(List<String> commands) {
		
	}//end of executeCommand()
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param keyfile
	 * @param trust
	 * @param command
	 */
	public static void executeCommand(String host, int port, String username, String password, String keyfile, boolean trust, String command) {
		SSHExec exec = new SSHExec();
		
		logger.debug("[ssh exec] " + command);
		Project project = new Project();
		
		exec.setProject(project);
		
		exec.setHost(host);
		exec.setPort(port);
		exec.setUsername(username);
		exec.setPassword(password);
		
		if( keyfile != null ) {
			exec.setKeyfile(keyfile);
		}
		
		exec.setTrust(trust);
		exec.setCommand(command);
		
		exec.execute();
	}//end of execute()
}
//end of SshExecUtil.java