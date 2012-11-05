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
 * Sang-cheon Park	2012. 11. 1.		First Draft.
 */
package com.athena.peacock.engine.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.athena.peacock.engine.core.TargetHost;
import com.athena.peacock.engine.util.SshExecUtil;

/**
 * <pre>
 * ScpAction을 이용한 원격지 호스트로의 파일 전송 기능을 검증하기 위한 테스트 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ScpActionTest {
	
	private ScpAction action;
	private TargetHost targetHost;
	private String source;
	private String target;

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

	@Test
	public void perform() {
		
		/*********************
		 * source가 File인 경우
		 *********************/
		// 초기화
		source = this.getClass().getResource("/ziputil/directory/test/web.xml").getFile();
		target = "~/scptest/file/web.xml";
		
		action = new ScpAction(targetHost, source, target);
		
		try {
			// 테스트
			action.perform();

			// 검증
			String command = "ls -l ~/scptest/file/web.xml";
			String resultMsg = SshExecUtil.executeCommand(targetHost, command);
			
			assertTrue("디렉토리 조회 결과에 \"web.xml\"이 존재해야 합니다.", resultMsg.indexOf("web.xml") > -1);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다. 방화벽 등의 문제로 테스트를 수행하는 네트워크 환경에 따라 테스트가 실패할 수 있습니다.");
		}
		
		/*************************
		 * source가 Directory인 경우
		 *************************/
		// 초기화
		source = this.getClass().getResource("/ziputil/directory/test").getFile();
		target = "~/scptest/directory/";
		
		action = new ScpAction(targetHost, source, target);
		
		try {
			// 테스트
			action.perform();

			// 검증
			String command = "ls -Rl ~/scptest/directory";
			String resultMsg = SshExecUtil.executeCommand(targetHost, command);
			
			assertTrue("디렉토리 조회 결과에 \"jsp\"가 존재해야 합니다.", resultMsg.indexOf("jsp") > -1);
			assertTrue("디렉토리 조회 결과에 \"web.xml\"이 존재해야 합니다.", resultMsg.indexOf("web.xml") > -1);
			assertTrue("디렉토리 조회 결과에 \"login\"이 존재해야 합니다.", resultMsg.indexOf("login") > -1);
			assertTrue("디렉토리 조회 결과에 \"show.jsp\"가 존재해야 합니다.", resultMsg.indexOf("show.jsp") > -1);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다. 방화벽 등의 문제로 테스트를 수행하는 네트워크 환경에 따라 테스트가 실패할 수 있습니다.");
		}
	}//end of perform()

}
//end of ScpActionTest.java