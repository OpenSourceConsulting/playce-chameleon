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
 * Sang-cheon Park	2012. 10. 14.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.parser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;

/**
 * <pre>
 * 버젼 별 application.xml에 대한 파싱 기능을 테스트 하기 위한 테스트 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ApplicationXMLParserTest {

	/**
	 * <pre>
	 * File, AnalyzeDefinition이 초기화 되지 않은 경우에 대한 테스트
	 * </pre>
	 */
	@Test
	public void testWithNullParam() {
		File file = null;
		AnalyzeDefinition analyzeDefinition = null;
		
		try {
			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		// 초기화
		file = new File(this.getClass().getResource("/parser/application_1_3.xml").getFile());
		analyzeDefinition = null;
		
		try {
			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		// 초기화
		file = null;
		analyzeDefinition = new AnalyzeDefinition();
		
		try {
			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
	}//end of testWithNullParam()
	
	/**
	 * <pre>
	 * 1.3 버젼의 application.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testApplication_1_3() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/application_1_3.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		ThreadLocalUtil.clearSharedObject();
		
		try {
			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<File> warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			List<File> jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			

			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
		} catch(Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testApplication_1_3()
	
	/**
	 * <pre>
	 * 1.4 버젼의 application.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testApplication_1_4() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/application_1_4.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		ThreadLocalUtil.clearSharedObject();
		
		try {
			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<File> warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			List<File> jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			
			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			assertTrue("Web application은 2개 이어야 합니다.", warFileList.size() == 2);
			assertTrue("EJB application은 2개 이어야 합니다.", jarFileList.size() == 2);
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testApplication_1_4()
	
	/**
	 * <pre>
	 * 1.5 버젼의 application.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testApplication_1_5() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/application_1_5.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		ThreadLocalUtil.clearSharedObject();
		
		try {
			// 테스트
			new ApplicationXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<File> warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			List<File> jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);

			assertNotNull("warFileList는 null이 아니어야 합니다.", warFileList);
			assertNotNull("jarFileList는 null이 아니어야 합니다.", jarFileList);
			assertTrue("Web application은 2개 이어야 합니다.", warFileList.size() == 2);
			assertTrue("EJB application은 2개 이어야 합니다.", jarFileList.size() == 2);
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testApplication_1_5()

}
//end of ApplicationXMLParserTest.java