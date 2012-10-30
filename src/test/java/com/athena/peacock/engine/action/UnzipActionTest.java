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
 * JUnit Test Case for UnzipAction class.
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class UnzipActionTest {
	
	private UnzipAction action;

	/**
	 * <pre>
	 * zip 파일에 대한 압축 해제 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void perform() {
		String srcFile = null;
		String destDir = null;
		File file = null;
		
		/*========================================
		 * 압축 파일명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcFile = "";
		
		// 테스트
		try {
			action = new UnzipAction(srcFile);
			action.perform();
			fail("IllegalArgumentException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		/*========================================
		 * 압축 파일명만 주어진 경우 
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/ziputil/archive/zipTest.zip").getFile();
		
		// 테스트
		try {
			action = new UnzipAction(srcFile);
			action.perform();
			
			// 검증
			file = new File(this.getClass().getResource("/ziputil/archive/zipTest").getFile());
			
			assertTrue("zipTest가 존재해야 합니다.", file.exists());
			assertTrue("zipTest는 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
		
		/*========================================
		 * 압축 파일명과 압축 해제 디렉토리가 함께 주어진 경우
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/ziputil/archive/zipTest.zip").getFile();
		destDir = this.getClass().getResource("/ziputil/archive/").getFile() + File.separator + System.currentTimeMillis();
		
		// 테스트
		try {
			action = new UnzipAction(srcFile, destDir);
			action.perform();
			
			// 검증
			file = new File(destDir);
			
			assertTrue(destDir + "이(가) 존재해야 합니다.", file.exists());
			assertTrue(destDir + "은(는) 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of perform()
	
	/**
	 * <pre>
	 * 테스트 시 생성된 임시 디렉토리를 삭제한다.
	 * </pre>
	 * @param path
	 * @return
	 */
	private boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		
		return (path.delete());
	}//end of deleteDirectory

}
//end of UnzipActionTest.java