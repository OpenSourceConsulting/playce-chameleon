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

import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.CommonAnalyze;

/**
 * <pre>
 * 버젼 별 ejb-jar.xml에 대한 파싱 기능을 테스트 하기 위한 테스트 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class EjbJarXMLParserTest {

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
			new EjbJarXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		// 초기화
		file = new File(this.getClass().getResource("/parser/ejb-jar_2_0.xml").getFile());
		analyzeDefinition = null;
		
		try {
			// 테스트
			new EjbJarXMLParser().parse(file, analyzeDefinition);
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
			new EjbJarXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
	}//end of testWithNullParam()
	
	/**
	 * <pre>
	 * 2.0 버젼의 ejb-jar.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@Test
	public void testEjbjar_2_0() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/ejb-jar_2_0.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		
		try {
			// 테스트
			new EjbJarXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<CommonAnalyze> commonAnalyzeList = analyzeDefinition.getEjbApplicationMap().get(analyzeDefinition.getFileName());
			
			assertNotNull("commonAnalyzeList는 null이 아니어야 합니다.", commonAnalyzeList);
			assertTrue("commonAnalyzeList의 크기는 3이어야 합니다.", commonAnalyzeList.size() == 3);
			assertTrue("첫 번째 commonAnalyze는 \"Home Interface\", \"market.login.LoginHome\" 이어야 합니다.", 
					commonAnalyzeList.get(0).getItem().equals("Home Interface") && commonAnalyzeList.get(0).getContents().equals("market.login.LoginHome"));
			assertTrue("두 번째 commonAnalyze는 \"Remote Interface\", \"market.login.Login\" 이어야 합니다.", 
					commonAnalyzeList.get(1).getItem().equals("Remote Interface") && commonAnalyzeList.get(1).getContents().equals("market.login.Login"));
			assertTrue("세 번째 commonAnalyze는 \"Enterprise Bean Class\", \"market.login.LoginEJB\" 이어야 합니다.", 
					commonAnalyzeList.get(2).getItem().equals("Enterprise Bean Class") && commonAnalyzeList.get(2).getContents().equals("market.login.LoginEJB"));
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testEjbJar2_0()
	
	/**
	 * <pre>
	 * 2.1 버젼의 ejb-jar.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@Test
	public void testEjbjar_2_1() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/ejb-jar_2_1.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		
		try {
			// 테스트
			new EjbJarXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<CommonAnalyze> commonAnalyzeList = analyzeDefinition.getEjbApplicationMap().get(analyzeDefinition.getFileName());
			
			assertNotNull("commonAnalyzeList는 null이 아니어야 합니다.", commonAnalyzeList);
			assertTrue("commonAnalyzeList의 크기는 3이어야 합니다.", commonAnalyzeList.size() == 3);
			assertTrue("첫 번째 commonAnalyze는 \"Home Interface\", \"market.login.LoginHome\" 이어야 합니다.", 
					commonAnalyzeList.get(0).getItem().equals("Home Interface") && commonAnalyzeList.get(0).getContents().equals("market.login.LoginHome"));
			assertTrue("두 번째 commonAnalyze는 \"Remote Interface\", \"market.login.Login\" 이어야 합니다.", 
					commonAnalyzeList.get(1).getItem().equals("Remote Interface") && commonAnalyzeList.get(1).getContents().equals("market.login.Login"));
			assertTrue("세 번째 commonAnalyze는 \"Enterprise Bean Class\", \"market.login.LoginEJB\" 이어야 합니다.", 
					commonAnalyzeList.get(2).getItem().equals("Enterprise Bean Class") && commonAnalyzeList.get(2).getContents().equals("market.login.LoginEJB"));
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testEjbJar2_1()

}
//end of EjbJarXMLParserTest.java