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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.EjbRecommend;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;

/**
 * <pre>
 * 버젼 별 jeus-web-dd.xml에 대한 파싱 기능을 테스트 하기 위한 테스트 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JeusWebDDXMLParserTest {

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
			new JeusWebDDXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		// 초기화
		file = new File(this.getClass().getResource("/parser/jeus-web-dd.xml").getFile());
		analyzeDefinition = null;
		
		try {
			// 테스트
			new JeusWebDDXMLParser().parse(file, analyzeDefinition);
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
			new JeusWebDDXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
	}//end of testWithNullParam()
	
	/**
	 * <pre>
	 * jeus-web-dd.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@Test
	public void testJeusWebDD() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/jeus-web-dd.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		
		PDFMetadataDefinition metadataDefinition = new PDFMetadataDefinition();
		ThreadLocalUtil.clearSharedObject();
		ThreadLocalUtil.add(ChameleonConstants.PDF_METADATA_DEFINITION, metadataDefinition);
		ThreadLocalUtil.add(ChameleonConstants.PROJECT_NAME, "athena-chameleon");
		
		try {
			File jboss = new File(new File(this.getClass().getResource("/parser").getFile()), "jboss-web.xml");
			if(jboss.exists()) {
				jboss.delete();
			}
			
			// 테스트
			new JeusWebDDXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<EjbRecommend> recommendList = metadataDefinition.getWebRecommendList();
			List<String> transFileList = metadataDefinition.getWebTransFileList();
			assertTrue("recommendList의 크기는 2이어야 합니다.", recommendList.size() == 2);
			assertTrue("transFileList의 크기는 1이어야 합니다.", transFileList.size() == 1);
			assertTrue("첫 번째 transFileList는 \"jboss.xml\"이어야 합니다.", transFileList.get(0).endsWith("jboss-web.xml"));
			
			// jboss-web.xml 파일 생섬 결과 검증
			jboss = new File(this.getClass().getResource("/parser/jboss-web.xml").getFile());
			assertTrue("jboss-web.xml 파일이 생성되어 있어야 합니다.", jboss.exists());
			jboss.delete();
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testJeusWebDD()

}
//end of JeusWebDDXMLParserTest.java