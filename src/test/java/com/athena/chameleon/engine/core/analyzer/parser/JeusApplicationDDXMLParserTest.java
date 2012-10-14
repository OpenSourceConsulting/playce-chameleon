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
 * 버젼 별 jesu-application-dd.xml 또는 JEUSMain.xml에 대한 파싱 기능을 테스트 하기 위한 테스트 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JeusApplicationDDXMLParserTest {

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
			new JeusApplicationDDXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		// 초기화
		file = new File(this.getClass().getResource("/parser/JEUSMain_5_0.xml").getFile());
		analyzeDefinition = null;
		
		try {
			// 테스트
			new JeusApplicationDDXMLParser().parse(file, analyzeDefinition);
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
			new JeusApplicationDDXMLParser().parse(file, analyzeDefinition);
			fail("IllegalArgumentException 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
	}//end of testWithNullParam()
	
	/**
	 * <pre>
	 * 5.0 버젼의 jesu-application-dd.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@Test
	public void testJeusApplicationDD_5_0() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/jeus-application-dd.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		
		PDFMetadataDefinition metadataDefinition = new PDFMetadataDefinition();
		ThreadLocalUtil.clearSharedObject();
		ThreadLocalUtil.add(ChameleonConstants.PDF_METADATA_DEFINITION, metadataDefinition);
		ThreadLocalUtil.add(ChameleonConstants.PROJECT_NAME, "athena-chameleon");
		
		try {
			File jboss = new File(new File(this.getClass().getResource("/parser").getFile()), "jboss-app.xml");
			if(jboss.exists()) {
				jboss.delete();
			}
			
			// 테스트
			new JeusApplicationDDXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<EjbRecommend> recommendList = metadataDefinition.getApplicationRecommendList();
			List<String> transFileList = metadataDefinition.getAppTransFileList();
			assertTrue("recommendList의 크기는 2이어야 합니다.", recommendList.size() == 2);
			assertTrue("transFileList의 크기는 1이어야 합니다.", transFileList.size() == 1);
			assertTrue("첫 번째 transFileList는 \"jboss-app.xml\"이어야 합니다.", transFileList.get(0).endsWith("jboss-app.xml"));
			
			// jboss-app.xml 파일 생섬 결과 검증
			jboss = new File(this.getClass().getResource("/parser/jboss-app.xml").getFile());
			assertTrue("jboss.xml 파일이 생성되어 있어야 합니다.", jboss.exists());
			jboss.delete();
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testJeusApplicationDD_5_0()
	
	/**
	 * <pre>
	 * 5.0 버젼의 JEUSMain.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@Test
	public void testJeusMain_5_0() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/JEUSMain_5_0.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		
		PDFMetadataDefinition metadataDefinition = new PDFMetadataDefinition();
		ThreadLocalUtil.clearSharedObject();
		ThreadLocalUtil.add(ChameleonConstants.PDF_METADATA_DEFINITION, metadataDefinition);
		ThreadLocalUtil.add(ChameleonConstants.PROJECT_NAME, "athena-chameleon");
		
		try {
			File jboss = new File(new File(this.getClass().getResource("/parser").getFile()), "jboss-app.xml");
			if(jboss.exists()) {
				jboss.delete();
			}
			
			// 테스트
			new JeusApplicationDDXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<EjbRecommend> recommendList = metadataDefinition.getApplicationRecommendList();
			List<String> transFileList = metadataDefinition.getAppTransFileList();
			assertTrue("recommendList의 크기는 2이어야 합니다.", recommendList.size() == 2);
			assertTrue("transFileList의 크기는 1이어야 합니다.", transFileList.size() == 1);
			assertTrue("첫 번째 transFileList는 \"jboss-app.xml\"이어야 합니다.", transFileList.get(0).endsWith("jboss-app.xml"));
			
			// jboss-app.xml 파일 생섬 결과 검증
			jboss = new File(this.getClass().getResource("/parser/jboss-app.xml").getFile());
			assertTrue("jboss.xml 파일이 생성되어 있어야 합니다.", jboss.exists());
			jboss.delete();
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testJeusMain_5_0()
	
	/**
	 * <pre>
	 * 6.0 버젼의 JEUSMain.xml 파일에 대한 파싱 테스트
	 * </pre>
	 */
	@Test
	public void testJeusMain_6_0() {
		// 초기화
		File file = new File(this.getClass().getResource("/parser/JEUSMain_6_0.xml").getFile());
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		
		PDFMetadataDefinition metadataDefinition = new PDFMetadataDefinition();
		ThreadLocalUtil.clearSharedObject();
		ThreadLocalUtil.add(ChameleonConstants.PDF_METADATA_DEFINITION, metadataDefinition);
		ThreadLocalUtil.add(ChameleonConstants.PROJECT_NAME, "athena-chameleon");
		
		try {
			File jboss = new File(new File(this.getClass().getResource("/parser").getFile()), "jboss-app.xml");
			if(jboss.exists()) {
				jboss.delete();
			}
			
			// 테스트
			new JeusApplicationDDXMLParser().parse(file, analyzeDefinition);
			
			// 검증
			List<EjbRecommend> recommendList = metadataDefinition.getApplicationRecommendList();
			List<String> transFileList = metadataDefinition.getAppTransFileList();
			assertTrue("recommendList의 크기는 2이어야 합니다.", recommendList.size() == 2);
			assertTrue("transFileList의 크기는 1이어야 합니다.", transFileList.size() == 1);
			assertTrue("첫 번째 transFileList는 \"jboss-app.xml\"이어야 합니다.", transFileList.get(0).endsWith("jboss-app.xml"));
			
			// jboss-app.xml 파일 생섬 결과 검증
			jboss = new File(this.getClass().getResource("/parser/jboss-app.xml").getFile());
			assertTrue("jboss.xml 파일이 생성되어 있어야 합니다.", jboss.exists());
			jboss.delete();
		} catch(Throwable t) {
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testJeusMain_6_0()

}
//end of JeusApplicationDDXMLParserTest.java