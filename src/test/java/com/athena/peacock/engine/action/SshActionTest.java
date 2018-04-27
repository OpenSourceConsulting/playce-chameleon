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
 * Sang-cheon Park	2012. 11. 2.		First Draft.
 */
package com.athena.peacock.engine.action;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.athena.peacock.engine.core.TargetHost;

/**
 * <pre>
 * SshAction을 이용한 원격지 호스트로의 명령 수행 기능을 검증하기 위한 테스트 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class SshActionTest {
	
	private SshAction action;
	private TargetHost targetHost;
	private List<String> commandList;

	/**
	 * <pre>
	 * 테스트 수행전 Target Host 정보를 세팅한다.
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		targetHost = new TargetHost();
		
		// 보안상의 문제로 테스트 시 수정하여 사용한다.
		// 수정 결과를 GitHub에 Commit 금지.
		targetHost.setHost("xxx.xxx.xxx.xxx");
		targetHost.setPort(0);
		targetHost.setUsername("****");
		
		//*
		targetHost.setPassword("****");
		/*/
		targetHost.setKeyfile("./***.pem");
		//*/
	}

//	@Test
	public void perform() {
		// 초기화
		commandList = new ArrayList<String>();
		commandList.add("echo Hello World!");
		commandList.add("touch SshAction");
		commandList.add("ls -al SshAction");
		commandList.add("rm -f SshAction");
		
		action = new SshAction(targetHost, commandList);
		
		try {
			// 테스트
			action.perform();
			
			// 검증
			File output = new File("exec_result.log");
			
			assertTrue("exec_result.log 파일이 존재해야 합니다.", output.exists());
			
			String resultMsg = IOUtils.toString(output.toURI());
			
			assertTrue("결과 로그에 \"Hello World!\"가 존재해야 합니다.", resultMsg.indexOf("Hello World!") > -1);
			assertTrue("결과 로그에 \"SshAction\"이 존재해야 합니다.", resultMsg.indexOf("SshAction") > -1);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다. 방화벽 등의 문제로 테스트를 수행하는 네트워크 환경에 따라 테스트가 실패할 수 있습니다.");
		}
	}//end of perform()

}
//end of SshActionTest.java