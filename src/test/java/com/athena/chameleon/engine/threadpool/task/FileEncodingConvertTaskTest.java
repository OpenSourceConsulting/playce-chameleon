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
 * Sang-cheon Park	2012. 10. 12.		First Draft.
 */
package com.athena.chameleon.engine.threadpool.task;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

/**
 * <pre>
 * 파일 인코딩 변환 기능을 확인하기 위한 테스트 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/context-*.xml" })
public class FileEncodingConvertTaskTest {

	@Inject
	@Named("policy")
	private Policy policy;

	@Inject
	@Named("taskExecutor")
	private ChameleonThreadPoolExecutor executor;

	/**
	 * <pre>
	 * 다양한 형태로 인코딩 된 원본 테스트 파일들을 유지하기 위해 테스트에 사용될 파일들을 백업 폴더로부터 복사한다.
	 * </pre>
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File[] fileList = new File(FileEncodingConvertTaskTest.class.getResource("/converter/orig").getFile()).listFiles();
		
		for(File file : fileList) {
			copy(file, new File(file.getParentFile().getParent() + File.separator + file.getName()));
		}
	}//end of setUpBeforeClass()

	/**
	 * <pre>
	 * 필수 파라메타에 대한 Null 처리 테스트
	 * </pre>
	 */
	@Test
	public void convertTestWithNullParam() {
		// 초기화
		File file = null;

		try {
			// 테스트
			executor.execute(new FileEncodingConvertTask(file, policy.getDefaultEncoding()));
			fail("NullPointerException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 NullPointerException 이어야 합니다.", t instanceof NullPointerException);
		}
	}//end of convertTestWithNullParam()

	/**
	 * <pre>
	 * euc-kr 인코딩 파일에 대한 변환 테스트
	 * </pre>
	 */
	@Test
	public void convertTestWithEuckr() {
		File file = null;
		String beforeEnconding = null;
		String afterEnconding = null;
		String beforeContents = null;
		String afterContents = null;

		try {
			// 초기화
			file = new File(this.getClass().getResource("/converter/euckr.html").getFile());
			beforeEnconding = getCharset(file);
			beforeContents = IOUtils.toString(file.toURI());
			
			// 테스트
			executor.execute(new FileEncodingConvertTask(file, policy.getDefaultEncoding()));
			
			executor.getExecutor().shutdown();	

			// 테스트 Thread가 종료되길 기다린다.
			while (!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}

			// 검증
			afterEnconding = getCharset(file);
			afterContents = IOUtils.toString(file.toURI());

			assertTrue("변환 전 캐릭터 셋은 UTF-8이 아니어야 합니다.", !beforeEnconding.equals("UTF-8"));
			assertTrue("변환 후 캐릭터 셋은 UTF-8이어야 합니다.", afterEnconding.equals("UTF-8"));
			assertTrue("변환 전 파일에 \"euc-kr\"이 있어야 합니다.", beforeContents.indexOf("euc-kr") > -1 || beforeContents.indexOf("EUC-KR") > -1);
			assertTrue("변환 후 파일에 \"euc-kr\"이 없어야 합니다.", afterContents.indexOf("euc-kr") < 0 && afterContents.indexOf("EUC-KR") < 0);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of convertTestWithEuckr()

	/**
	 * <pre>
	 * ms949 인코딩 파일에 대한 변환 테스트
	 * </pre>
	 */
	@Test
	public void convertTestWithMs949() {
		File file = null;
		String beforeEnconding = null;
		String afterEnconding = null;
		String beforeContents = null;
		String afterContents = null;

		try {
			// 초기화
			file = new File(this.getClass().getResource("/converter/ms949.html").getFile());
			beforeEnconding = getCharset(file);
			beforeContents = IOUtils.toString(file.toURI());
			
			// 테스트
			executor.execute(new FileEncodingConvertTask(file, policy.getDefaultEncoding()));
			
			executor.getExecutor().shutdown();	

			// 테스트 Thread가 종료되길 기다린다.
			while (!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}

			// 검증
			afterEnconding = getCharset(file);
			afterContents = IOUtils.toString(file.toURI());

			assertTrue("변환 전 캐릭터 셋은 UTF-8이 아니어야 합니다.", !beforeEnconding.equals("UTF-8"));
			assertTrue("변환 후 캐릭터 셋은 UTF-8이어야 합니다.", afterEnconding.equals("UTF-8"));
			assertTrue("변환 전 파일에 \"ms949\"가 있어야 합니다.", beforeContents.indexOf("ms949") > -1 || beforeContents.indexOf("MS949") > -1);
			assertTrue("변환 후 파일에 \"ms949\"가 없어야 합니다.", afterContents.indexOf("ms949") < 0 && afterContents.indexOf("MS949") < 0);
		} catch (Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of convertTestWithMs949()

	/**
	 * <pre>
	 * ksc5601 인코딩 파일에 대한 변환 테스트
	 * </pre>
	 */
	@Test
	public void convertTestWithKsc5601() {
		File file = null;
		String beforeEnconding = null;
		String afterEnconding = null;
		String beforeContents = null;
		String afterContents = null;

		try {
			// 초기화
			file = new File(this.getClass().getResource("/converter/ksc5601.html").getFile());
			beforeEnconding = getCharset(file);
			beforeContents = IOUtils.toString(file.toURI());
			
			// 테스트
			executor.execute(new FileEncodingConvertTask(file, policy.getDefaultEncoding()));
			
			executor.getExecutor().shutdown();	

			// 테스트 Thread가 종료되길 기다린다.
			while (!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}

			// 검증
			afterEnconding = getCharset(file);
			afterContents = IOUtils.toString(file.toURI());

			assertTrue("변환 전 캐릭터 셋은 UTF-8이 아니어야 합니다.", !beforeEnconding.equals("UTF-8"));
			assertTrue("변환 후 캐릭터 셋은 UTF-8이어야 합니다.", afterEnconding.equals("UTF-8"));
			assertTrue("변환 전 파일에 \"ksc5601\"이 있어야 합니다.", beforeContents.indexOf("ksc5601") > -1 || beforeContents.indexOf("KSC5601") > -1);
			assertTrue("변환 후 파일에 \"ksc5601\"이 없어야 합니다.", afterContents.indexOf("ksc5601") < 0 && afterContents.indexOf("KSC5601") < 0);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of convertTestWithKsc5601()
	
	/**
	 * <pre>
	 * source 파일을 target으로 복사한다.
	 * </pre>
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	private static void copy(File sourceFile, File targetFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(targetFile);
		
		IOUtils.copy(inputStream, outputStream);
		IOUtils.closeQuietly(outputStream);
		IOUtils.closeQuietly(inputStream);
	}//end of copy()
	
	/**
	 * <pre>
	 * 주어진 파일에 대한 인코딩 타입을 반환한다.
	 * </pre>
	 * @param is
	 * @return
	 * @throws IOException
	 */
	private String getCharset(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		CharsetDetector detector = new CharsetDetector();
		detector.setDeclaredEncoding("UTF-8");
		detector.setText(IOUtils.toByteArray(is));
		CharsetMatch cm = detector.detect();
		
		return cm.getName();
	}//end of getCharset()

}
// end of FileEncodingConvertTaskTest.java