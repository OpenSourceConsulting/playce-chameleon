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

import org.junit.BeforeClass;
import org.junit.Test;

import com.athena.chameleon.engine.entity.pdf.ArchiveType;

/**
 * Source File 압축 해제 관련 Junit Test 클래스
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class ZipUtilTest {
	
	/**
	 * <pre>
	 * GitHub에 ear, war 파일에 대한 commit이 불가능하기 때문에 
	 * 테스트 시작 전 earr, warr 파일이 존재하면 ear, war 파일로 확장자를 변경한다.
	 * </pre>
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file = new File(new File(ZipUtilTest.class.getResource("/ziputil/archive/").getFile()), "earTest.earr");
		
		if(file.exists()) {
			file.renameTo(new File(file.getParent() + File.separator + "earTest.ear"));
		}
		
		file = new File(new File(ZipUtilTest.class.getResource("/ziputil/archive/").getFile()), "warTest.warr");
		
		if(file.exists()) {
			file.renameTo(new File(file.getParent() + File.separator + "warTest.war"));
		}
	}//end of setUpBeforeClass()

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
		srcFile = this.getClass().getResource("/ziputil/archive/zipTest.zip").getFile();
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
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
		srcFile = this.getClass().getResource("/ziputil/archive/earTest.ear").getFile();
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/ziputil/archive/earTest").getFile());
			
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
		srcFile = this.getClass().getResource("/ziputil/archive/earTest.ear").getFile();
		destDir = this.getClass().getResource("/ziputil/archive/").getFile() + File.separator + System.currentTimeMillis();
		
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
		srcFile = this.getClass().getResource("/ziputil/archive/warTest.war").getFile();
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/ziputil/archive/warTest").getFile());
			
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
		srcFile = this.getClass().getResource("/ziputil/archive/warTest.war").getFile();
		destDir = this.getClass().getResource("/ziputil/archive/").getFile() + File.separator + System.currentTimeMillis();
		
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
		srcFile = this.getClass().getResource("/ziputil/archive/jarTest.jar").getFile();
		
		// 테스트
		try {
			result = ZipUtil.decompress(srcFile);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/ziputil/archive/jarTest").getFile());
			
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
		srcFile = this.getClass().getResource("/ziputil/archive/jarTest.jar").getFile();
		destDir = this.getClass().getResource("/ziputil/archive/").getFile() + File.separator + System.currentTimeMillis();
		
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
	 * 선택된 디렉토리에 대한 zip 파일로의 압축 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void zipCompress() {
		String srcDir = null;
		String destFile = null;
		Boolean result = null;
		File file = null;
		
		/*========================================
		 * 소스 디렉토리명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcDir = "";
		
		// 테스트
		try {
			ZipUtil.compress(srcDir, ArchiveType.ZIP);
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
			result = ZipUtil.compress(srcDir, ArchiveType.ZIP);
			
			// 검증
			assertTrue("압축 결과는 true 이어야 합니다.", result);
			
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
			result = ZipUtil.compress(srcDir, destFile, ArchiveType.ZIP);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(destFile);
			
			assertTrue(destFile + " 파일이 존재해야 합니다.", file.exists());
			assertTrue(destFile + "은(는) 파일 이어야 합니다.", file.isFile());

			// 압축 파일 삭제
			file.delete();
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of zipCompress()

	/**
	 * <pre>
	 * 선택된 디렉토리에 대한 ear 파일로의 압축 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void earCompress() {
		String srcDir = null;
		String destFile = null;
		Boolean result = null;
		File file = null;
		
		/*========================================
		 * 소스 디렉토리명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcDir = "";
		
		// 테스트
		try {
			ZipUtil.compress(srcDir, ArchiveType.EAR);
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
			result = ZipUtil.compress(srcDir, ArchiveType.EAR);
			
			// 검증
			assertTrue("압축 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/ziputil/directory/test.ear").getFile());
			
			assertTrue("test.ear 파일이 존재해야 합니다.", file.exists());
			assertTrue("test.ear은 파일 이어야 합니다.", file.isFile());
			
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
		destFile = this.getClass().getResource("/ziputil/directory").getFile() + File.separator + "test.ear";
		
		// 테스트
		try {
			result = ZipUtil.compress(srcDir, destFile, ArchiveType.EAR);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(destFile);
			
			assertTrue(destFile + " 파일이 존재해야 합니다.", file.exists());
			assertTrue(destFile + "은(는) 파일 이어야 합니다.", file.isFile());

			// 압축 파일 삭제
			file.delete();
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of earCompress()

	/**
	 * <pre>
	 * 선택된 디렉토리에 대한 war 파일로의 압축 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void warCompress() {
		String srcDir = null;
		String destFile = null;
		Boolean result = null;
		File file = null;
		
		/*========================================
		 * 소스 디렉토리명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcDir = "";
		
		// 테스트
		try {
			ZipUtil.compress(srcDir, ArchiveType.WAR);
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
			result = ZipUtil.compress(srcDir, ArchiveType.WAR);
			
			// 검증
			assertTrue("압축 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/ziputil/directory/test.war").getFile());
			
			assertTrue("test.war 파일이 존재해야 합니다.", file.exists());
			assertTrue("test.war은 파일 이어야 합니다.", file.isFile());
			
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
		destFile = this.getClass().getResource("/ziputil/directory").getFile() + File.separator + "test.war";
		
		// 테스트
		try {
			result = ZipUtil.compress(srcDir, destFile, ArchiveType.WAR);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(destFile);
			
			assertTrue(destFile + " 파일이 존재해야 합니다.", file.exists());
			assertTrue(destFile + "은(는) 파일 이어야 합니다.", file.isFile());

			// 압축 파일 삭제
			file.delete();
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of warCompress()

	/**
	 * <pre>
	 * 선택된 디렉토리에 대한 jar 파일로의 압축 기능을 확인하기 위한 테스트 메소드
	 * </pre>
	 */
	@Test
	public void jarCompress() {
		String srcDir = null;
		String destFile = null;
		Boolean result = null;
		File file = null;
		
		/*========================================
		 * 소스 디렉토리명이 주어지지 않은 경우
		 ========================================*/
		// 초기화
		srcDir = "";
		
		// 테스트
		try {
			ZipUtil.compress(srcDir, ArchiveType.JAR);
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
			result = ZipUtil.compress(srcDir, ArchiveType.JAR);
			
			// 검증
			assertTrue("압축 결과는 true 이어야 합니다.", result);
			
			file = new File(this.getClass().getResource("/ziputil/directory/test.jar").getFile());
			
			assertTrue("test.ear 파일이 존재해야 합니다.", file.exists());
			assertTrue("test.ear은 파일 이어야 합니다.", file.isFile());
			
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
		destFile = this.getClass().getResource("/ziputil/directory").getFile() + File.separator + "test.jar";
		
		// 테스트
		try {
			result = ZipUtil.compress(srcDir, destFile, ArchiveType.JAR);
			
			// 검증
			assertTrue("압축 해제 결과는 true 이어야 합니다.", result);
			
			file = new File(destFile);
			
			assertTrue(destFile + " 파일이 존재해야 합니다.", file.exists());
			assertTrue(destFile + "은(는) 파일 이어야 합니다.", file.isFile());

			// 압축 파일 삭제
			file.delete();
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of jarCompress()
	
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