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
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.ssh.SSHExec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.athena.peacock.engine.core.TargetHost;

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

	private static File output = new File("exec_result.log"); // 프로젝트 root에 파일 생성.
	private static boolean append = false;
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param targetHost
	 * @param commandList
	 * @return
	 * @throws IOException 
	 */
	public static String executeCommand(TargetHost targetHost, List<String> commandList) throws IOException {
		for(String command : commandList) {
			executeCommand(targetHost, command);
			append = true;
		}
		append = false;
		
		return IOUtils.toString(output.toURI());
	}//end of executeCommand()
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param targetHost
	 * @param command
	 * @return
	 * @throws IOException 
	 */
	public static String executeCommand(TargetHost targetHost, String command) throws IOException {
    	Assert.notNull(targetHost, "targetHost cannot be null.");
    	Assert.notNull(command, "command cannot be null.");

		logger.debug("[ssh exec] " + command);
    	
		Project project = new Project();
		
		SSHExec exec = new SSHExec();
		exec.setProject(project);
		exec.setHost(targetHost.getHost());
		exec.setPort(targetHost.getPort());
		exec.setUsername(targetHost.getUsername());
		exec.setPassword(targetHost.getPassword());
		
		if (targetHost.getKeyfile() != null) {
			exec.setKeyfile(targetHost.getKeyfile());
		}
		
		exec.setTrust(targetHost.isTrust());
		exec.setVerbose(true);
		exec.setCommand(command);
		
		// command 실행 결과가 저장될 파일 지정
		exec.setOutput(output);
		exec.setAppend(append);

		exec.execute();
		
		if (append) {
			return null;
		} else {
			return IOUtils.toString(output.toURI());
		}
	}//end of executeCommand()

}
//end of SshExecUtil.java