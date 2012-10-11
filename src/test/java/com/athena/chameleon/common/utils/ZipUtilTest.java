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
 * Hyo-jeong Lee	2012. 10. 11.		First Draft.
 */
package com.athena.chameleon.common.utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

/**
 * Source File 압축 해제 관련 Junit Test 클래스
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class ZipUtilTest {

	/**
	 * <pre>
	 * zip 파일에 대한 압축 해제 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void zipDecompress() {
		String srcFile = null;
		String destDir = null;
		Boolean result = null;
		File file = null;
		
		/*========================================
		 * 압축 파일명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcFile = "";
		
		// 테스트
		try {
			ZipUtil.decompress(srcFile);
			fail("IllegalArgumentException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		/*========================================
		 * 압축 파일명만 주어진 경우 
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/files/archive/zipTest.zip").getFile();
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/files/archive/zipTest").getFile());
			
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
		srcFile = this.getClass().getResource("/files/archive/zipTest.zip").getFile();
		destDir = this.getClass().getResource("/files").getFile() + File.separator + System.currentTimeMillis();;
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile, destDir);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(destDir);
			
			assertTrue(destDir + "이(가) 존재해야 합니다.", file.exists());
			assertTrue(destDir + "은(는) 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of zipDecompress()

	/**
	 * <pre>
	 * ear 파일에 대한 압축 해제 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void earDecompress() {
		String srcFile = null;
		String destDir = null;
		Boolean result = null;
		File file = null;
		
		/*========================================
		 * 압축 파일명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcFile = "";
		
		// 테스트
		try {
			ZipUtil.decompress(srcFile);
			fail("IllegalArgumentException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		/*========================================
		 * 압축 파일명만 주어진 경우 
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/files/archive/earTest.ear").getFile();
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/files/archive/earTest").getFile());
			
			assertTrue("earTest가 존재해야 합니다.", file.exists());
			assertTrue("earTest는 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
		
		/*========================================
		 * 압축 파일명과 압축 해제 디렉토리가 함께 주어진 경우
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/files/archive/earTest.ear").getFile();
		destDir = this.getClass().getResource("/files").getFile() + File.separator + System.currentTimeMillis();;
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile, destDir);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(destDir);
			
			assertTrue(destDir + "이(가) 존재해야 합니다.", file.exists());
			assertTrue(destDir + "은(는) 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of earDecompress()

	/**
	 * <pre>
	 * war 파일에 대한 압축 해제 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void warDecompress() {
		String srcFile = null;
		String destDir = null;
		Boolean result = null;
		File file = null;
		
		/*========================================
		 * 압축 파일명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcFile = "";
		
		// 테스트
		try {
			ZipUtil.decompress(srcFile);
			fail("IllegalArgumentException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		/*========================================
		 * 압축 파일명만 주어진 경우 
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/files/archive/warTest.war").getFile();
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/files/archive/warTest").getFile());
			
			assertTrue("warTest가 존재해야 합니다.", file.exists());
			assertTrue("warTest는 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
		
		/*========================================
		 * 압축 파일명과 압축 해제 디렉토리가 함께 주어진 경우
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/files/archive/warTest.war").getFile();
		destDir = this.getClass().getResource("/files").getFile() + File.separator + System.currentTimeMillis();;
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile, destDir);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(destDir);
			
			assertTrue(destDir + "이(가) 존재해야 합니다.", file.exists());
			assertTrue(destDir + "은(는) 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of warDecompress()

	/**
	 * <pre>
	 * jar 파일에 대한 압축 해제 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void jarDecompress() {
		String srcFile = null;
		String destDir = null;
		Boolean result = null;
		File file = null;
		
		/*========================================
		 * 압축 파일명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcFile = "";
		
		// 테스트
		try {
			ZipUtil.decompress(srcFile);
			fail("IllegalArgumentException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		/*========================================
		 * 압축 파일명만 주어진 경우 
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/files/archive/jarTest.jar").getFile();
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/files/archive/jarTest").getFile());
			
			assertTrue("jarTest가 존재해야 합니다.", file.exists());
			assertTrue("jarTest는 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
		
		/*========================================
		 * 압축 파일명과 압축 해제 디렉토리가 함께 주어진 경우
		 ========================================*/
		// 초기화
		srcFile = this.getClass().getResource("/files/archive/jarTest.jar").getFile();
		destDir = this.getClass().getResource("/files").getFile() + File.separator + System.currentTimeMillis();;
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile, destDir);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(destDir);
			
			assertTrue(destDir + "이(가) 존재해야 합니다.", file.exists());
			assertTrue(destDir + "은(는) 디렉토리 이어야 합니다.", file.isDirectory());
			
			// 압축 해제된 디렉토리 삭제
			deleteDirectory(file);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of jarDecompress()
	
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
//end of ZipUtilTest.java