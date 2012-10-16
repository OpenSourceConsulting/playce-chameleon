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
 * Sang-cheon Park	2012. 10. 16.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.parser;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Dependency;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Model;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Model.Dependencies;

/**
 * <pre>
 * pom.xml에 대한 파싱 기능을 테스트 하기 위한 테스트 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class PomXMLParserTest {

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
			new PomXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		// 초기화
		file = new File(this.getClass().getResource("/parser/pom_4_0.xml").getFile());
		analyzeDefinition = null;
		
		try {
			// 테스트
			new PomXMLParser().parse(file, analyzeDefinition);
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
			new PomXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
	}//end of testWithNullParam()
	
	/**
	 * <pre>
	 * pom.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@Test
	public void testPom() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/pom_4_0.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		
		try {
			// 테스트
			Model model = (Model) new PomXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			assertNotNull("model은 null이 아니어야 합니다.", model);
			
			Dependencies dependencies = model.getDependencies();
			List<Dependency> dependencyList = dependencies.getDependency();
			for (Dependency dependency : dependencyList) {
				if (dependency.getGroupId().equals("xerces") && dependency.getArtifactId().equals("xercesImpl")) {
					assertTrue("xerces의 scope은 \"provided\"이어야 합니다.", dependency.getScope().equals("provided"));
				} else if (dependency.getGroupId().equals("xml-apis") && dependency.getArtifactId().equals("xml-apis")) {
					assertTrue("xml-apis의 scope은 \"provided\"이어야 합니다.", dependency.getScope().equals("provided"));
				}else if (dependency.getGroupId().equals("xalan") && dependency.getArtifactId().equals("xalan")) {
					assertTrue("xalan의 scope은 \"provided\"이어야 합니다.", dependency.getScope().equals("provided"));
				}
			}

			File pom = new File(new File(this.getClass().getResource("/parser").getFile()), "pom.xml");
			assertTrue("pom.xml 파일이 생성되어 있어야 합니다.", pom.exists());
			pom.delete();
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testPom()
}
//end of PomXMLParserTest.java