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
package com.athena.peacock.engine.action;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

/**
 * <pre>
 * JUnit Test Case for ZipAction class.
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ZipActionTest {

	private ZipAction action;
	
	/**
	 * <pre>
	 * 선택된 디렉토리에 대한 zip 파일로의 압축 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void perform() {
		String srcDir = null;
		String destFile = null;
		File file = null;
		
		/*========================================
		 * 소스 디렉토리명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcDir = "";
		
		// 테스트
		try {
			action = new ZipAction(srcDir);
			action.perform();
			fail("IllegalArgumentException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		/*========================================
		 * 소스 디렉토리명만 주어진 경우 
		 ========================================*/
		// 초기화
		srcDir = this.getClass().getResource("/ziputil/directory/test").getFile();
		
		// 테스트
		try {
			action = new ZipAction(srcDir);
			action.perform();
			
			// 검증
			file = new File(this.getClass().getResource("/ziputil/directory/test.zip").getFile());
			
			assertTrue("test.zip 파일이 존재해야 합니다.", file.exists());
			assertTrue("test.zip은 파일 이어야 합니다.", file.isFile());
			
			// 압축 파일 삭제
			file.delete();
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
		
		/*========================================
		 * 소스 디렉토리명과 압축 파일명이 함께 주어진 경우
		 ========================================*/
		// 초기화
		srcDir = this.getClass().getResource("/ziputil/directory/test").getFile();
		destFile = this.getClass().getResource("/ziputil/directory").getFile() + File.separator + "test.zip";
		
		// 테스트
		try {
			action = new ZipAction(srcDir, destFile);
			action.perform();
			
			// 검증
			file = new File(destFile);
			
			assertTrue(destFile + " 파일이 존재해야 합니다.", file.exists());
			assertTrue(destFile + "은(는) 파일 이어야 합니다.", file.isFile());

			// 압축 파일 삭제
			file.delete();
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of perform()

}
//end of ZipActionTest.java