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

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

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

/**
 * <pre>
 * 파일 인코딩 변환 기능을 확인하기 위핸 테스트 클래스
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
	//@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File[] fileList = new File(FileEncodingConvertTaskTest.class.getResource("/converter/orig").getFile()).listFiles();
		
		for(File file : fileList) {
			copy2(file, new File(file.getParent() + File.separator + file.getName()));
		}
	}

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
	}

	@Test
	public void convertTestWithEuckr() {
		File file = null;
		String beforeContents = null;
		String afterContents = null;

		try {
			// 초기화
			file = new File(this.getClass().getResource("/converter/euckr.html").getFile());
			beforeContents = fileToString(file);
			
			// 테스트
			executor.execute(new FileEncodingConvertTask(file, policy.getDefaultEncoding()));
			
			// 테스트 Thread가 종료되길 기다린다.
			executor.getExecutor().shutdown();	
			
			try {
				while (!executor.getExecutor().isTerminated()) {
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 검증
			afterContents = fileToString(file);
			
			//assertTrue("변환 전 파일에 \"euc-kr\"이 있어야 합니다.", beforeContents.indexOf("euc-kr") > -1 && beforeContents.indexOf("EUC-KR") > -1);
			assertTrue("파일에 \"euc-kr\"이 없어야 합니다.", afterContents.indexOf("euc-kr") < 0 && afterContents.indexOf("EUC-KR") < 0);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다.");
		}
	}

	@Test
	public void convertTestWithMs949() {
		File file = null;
		String beforeContents = null;
		String afterContents = null;

		try {
			// 초기화
			file = new File(this.getClass().getResource("/converter/ms949.html").getFile());
			beforeContents = fileToString(file);
			
			// 테스트
			executor.execute(new FileEncodingConvertTask(file, policy.getDefaultEncoding()));
			
			// 테스트 Thread가 종료되길 기다린다.
			executor.getExecutor().shutdown();	
			
			try {
				while (!executor.getExecutor().isTerminated()) {
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 검증
			afterContents = fileToString(file);
			
			//assertTrue("변환 전 파일에 \"ms949\"가 있어야 합니다.", beforeContents.indexOf("ms949") && beforeContents.indexOf("MS949") > -1);
			assertTrue("파일에 \"ms949\"가 없어야 합니다.", afterContents.indexOf("ms949") < 0 && afterContents.indexOf("MS949") < 0);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다.");
		}
	}

	@Test
	public void convertTestWithKsc5601() {
		File file = null;
		String beforeContents = null;
		String afterContents = null;

		try {
			// 초기화
			file = new File(this.getClass().getResource("/converter/ksc5601.html").getFile());
			beforeContents = fileToString(file);
			
			// 테스트
			executor.execute(new FileEncodingConvertTask(file, policy.getDefaultEncoding()));
			
			// 테스트 Thread가 종료되길 기다린다.
			executor.getExecutor().shutdown();	
			
			try {
				while (!executor.getExecutor().isTerminated()) {
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 검증
			afterContents = fileToString(file);
			
			//assertTrue("변환 전 파일에 \"ksc5601\"이 있어야 합니다.", beforeContents.indexOf("ksc5601") > -1 && beforeContents.indexOf("KSC5601") > -1);
			assertTrue("파일에 \"ksc5601\"이 없어야 합니다.", afterContents.indexOf("ksc5601") < 0 && afterContents.indexOf("KSC5601") < 0);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다.");
		}
	}

	/**
	 * <pre>
	 * source 파일을 target으로 복사한다.
	 * </pre>
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void copy(File source, String target) throws Exception {
		// 스트림, 채널 선언
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel fcin = null;
		FileChannel fcout = null;

		try {
			// 스트림 생성
			inputStream = new FileInputStream(source);
			outputStream = new FileOutputStream(target);
			
			// 채널 생성
			fcin = inputStream.getChannel();
			fcout = outputStream.getChannel();

			// 채널을 통한 스트림 전송
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
		} catch (Exception e) {
			throw e;
		} finally {
			// 자원 해제
			IOUtils.closeQuietly(fcout);
			IOUtils.closeQuietly(fcin);
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
	}//end of copy()
	
	/**
	 * <pre>
	 * source 파일을 target으로 복사한다.
	 * </pre>
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copy2(File sourceFile, File targetFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(targetFile);
		
		IOUtils.copy(inputStream, outputStream);
		IOUtils.closeQuietly(outputStream);
		IOUtils.closeQuietly(inputStream);
	}//end of copy2()
	
    /**
     * <pre>
     * 파일의 내용을 문자열로 변환하여 반환한다.
     * </pre>
     * @param file
     * @return
     * @throws IOException 
     */
    protected String fileToString(File file) throws IOException {
        String result = null;

        DataInputStream in = null;
        byte[] buffer = new byte[(int) file.length()];
        in = new DataInputStream(new FileInputStream(file));
        in.readFully(buffer);
        result = new String(buffer);
        IOUtils.closeQuietly(in);
        
        return result;
    }//end of fileToString()
    
    public static void main(String[] args) {
		try {
			File[] fileList = new File(FileEncodingConvertTaskTest.class.getResource("/converter/orig").getFile()).listFiles();
			
			for(File file : fileList) {
				copy2(file, new File(file.getParent() + File.separator + file.getName()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
// end of FileEncodingConvertTaskTest.java