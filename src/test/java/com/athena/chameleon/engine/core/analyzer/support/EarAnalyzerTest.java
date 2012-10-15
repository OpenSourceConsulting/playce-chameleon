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
 * Sang-cheon Park	2012. 10. 15.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.support;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.common.utils.ZipUtilTest;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.Dependency;
import com.athena.chameleon.engine.entity.pdf.FileSummary;
import com.athena.chameleon.engine.entity.pdf.FileType;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/context-*.xml" })
public class EarAnalyzerTest {
	
    @Inject
    @Named("policy")
    private Policy policy;

    @Inject
    @Named("taskExecutor")
    private ChameleonThreadPoolExecutor executor;

    @Inject
    @Named("fileEncodingConverter")
    private FileEncodingConverter converter;

	/**
	 * <pre>
	 * GitHub에 ear 파일에 대한 commit이 불가능하기 때문에 
	 * 테스트 시작 전 earr 파일이 존재하면 ear 파일로 확장자를 변경한다.
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file = new File(new File(ZipUtilTest.class.getResource("/dependency").getFile()), "physicial.earr");
		
		if(file.exists()) {
			file.renameTo(new File(file.getParent() + File.separator + "physicial.ear"));
		}
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * <pre>
	 * analyzeDefinition, deployFile 등 null parameter에 대한 테스트
	 * </pre>
	 */
	@Test
	public void testWithNullParam() {
		AnalyzeDefinition analyzeDefinition;
		String deployFile;

		// 초기화
		analyzeDefinition = null;
		deployFile = null;
		
		try {
			// 테스트
			new EarAnalyzer(policy, converter, executor, analyzeDefinition).analyze(deployFile);
			fail("Exception이 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 NullPointerException이어야 합니다.", t instanceof NullPointerException);
		}

		// 초기화
		analyzeDefinition = new AnalyzeDefinition();
		deployFile = null;
		
		try {
			// 테스트
			new EarAnalyzer(policy, converter, executor, analyzeDefinition).analyze(deployFile);
			fail("Exception이 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 NullPointerException이어야 합니다.", t instanceof NullPointerException);
		}

		// 초기화
		analyzeDefinition = null;
		deployFile = this.getClass().getResource("/dependency/physicial.ear").getFile();
		
		try {
			// 테스트
			new EarAnalyzer(policy, converter, executor, analyzeDefinition).analyze(deployFile);
			fail("Exception이 발생해야 합니다.");
		} catch(Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 NullPointerException이어야 합니다.", t instanceof NullPointerException);
		}
	}//end of testWithNullParam()
	
	/**
	 * <pre>
	 * ear 패키징 파일에 대한 분석을 수행하며 다음 항목에 대한 결과를 확인한다.
	 * 
	 * <ul>
	 * 	  <li>각 애플리케이션에 대한 파일 요약 정보</li>
	 * 	  <li>APP-INF/lib 디렉토리에 대한 라이브러리 분석</li>
	 * 	  <li>WEB-INF/lib 디렉토리에 대한 라이브러리 분석</li>
	 * 	  <li>class 파일에 대한 의존성 분석</li>
	 * 	  <li>jsp 파일에 대한 의존성 분석</li>
	 * 	  <li>jsp 파일에 대한 directive 분석</li>
	 * </ul>
	 * </pre>
	 */
	@Test
	public void testAnalyze() {
		// 초기화
		PDFMetadataDefinition metadataDefinition = new PDFMetadataDefinition();
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		String deployFile = this.getClass().getResource("/dependency/physicial.ear").getFile();

		metadataDefinition.setDeployFile(deployFile);	
		metadataDefinition.setEarDefinition(analyzeDefinition);
		ThreadLocalUtil.add(ChameleonConstants.PDF_METADATA_DEFINITION, metadataDefinition);
		
		try {
			// 테스트
			new EarAnalyzer(policy, converter, executor, analyzeDefinition).analyze(deployFile);
			
			/********
			 * 검 증 *
			 ********/
			// 1. 각 애플리케이션에 대한 파일 요약 정보 확인
			// 1-1. ear 파일 요약정보
			Map<FileType, FileSummary> fileSummaryMap = analyzeDefinition.getFileSummaryMap();
			assertTrue("전체 파일의 갯수는 23이어야 합니다.", fileSummaryMap.get(FileType.SUM).getFileCount() == 23);

			// 1-2. war 파일 요약정보
			fileSummaryMap = metadataDefinition.getWarDefinitionMap().get("physicianWebApp").getFileSummaryMap();
			assertTrue("전체 파일의 갯수는 50이어야 합니다.", fileSummaryMap.get(FileType.SUM).getFileCount() == 50);

			// 1-3. jar 파일 요약정보
			fileSummaryMap = metadataDefinition.getJarDefinitionMap().get("physSessionEjbs").getFileSummaryMap();
			assertTrue("전체 파일의 갯수는 23이어야 합니다.", fileSummaryMap.get(FileType.SUM).getFileCount() == 23);
			
			// 2. APP-INF/lib 디렉토리에 대한 라이브러리 분석 결과 확인
			List<String> libList = analyzeDefinition.getLibraryList();
			assertTrue("APP-INF/lib 의 라이브러리 갯수는 17이어야 합니다.", libList.size() == 17);
			
			// 3. WEB-INF/lib 디렉토리에 대한 라이브러리 분석 결과 확인
			libList = metadataDefinition.getWarDefinitionMap().get("physicianWebApp").getLibraryList();
			assertTrue("WEB-INF/lib 의 라이브러리 갯수는 10이어야 합니다.", libList.size() == 10);
			
			// 3. class 파일에 대한 의존성 분석
			// 3-1. ear class 의존성 분석
			List<Dependency> dependencyList = analyzeDefinition.getClassDependencyList();
			assertTrue("dependencyList의 크기는 0이어야 합니다.", dependencyList.size() == 0);
			
			// 3-2. war class 의존성 분석
			dependencyList = metadataDefinition.getWarDefinitionMap().get("physicianWebApp").getClassDependencyList();
			assertTrue("dependencyList의 크기는 2이어야 합니다.", dependencyList.size() == 2);

			for(Dependency dependency : dependencyList) {
				assertTrue("의존성 클래스 파일은 \"com.bea.medrec\" 으로 시작해야 합니다.", dependency.getFileName().startsWith("com.bea.medrec"));
			}
			
			// 3-3. jar class 의존성 분석
			dependencyList = metadataDefinition.getJarDefinitionMap().get("physSessionEjbs").getClassDependencyList();
			assertTrue("dependencyList의 크기는 4이어야 합니다.", dependencyList.size() == 4);

			for(Dependency dependency : dependencyList) {
				assertTrue("의존성 클래스 파일은 \"com.bea.medrec.controller\" 으로 시작해야 합니다.", dependency.getFileName().startsWith("com.bea.medrec.controller"));
			}
			
			// 4. jsp 파일에 대한 의존성 분석
			dependencyList = metadataDefinition.getWarDefinitionMap().get("physicianWebApp").getJspDependencyList();
			assertTrue("dependencyList의 크기는 12이어야 합니다.", dependencyList.size() == 12);

			/*
			Confirmation.jsp
			CreateRx.jsp
			Error.jsp
			CreateVisit.jsp
			Login.jsp
			PhysicianHeader.jsp
			PatientHeader.jsp
			SearchResults.jsp
			ViewProfile.jsp
			Search.jsp
			ViewRecord.jsp
			ViewRecords.jsp
			*/
			
			// 5. jsp 파일에 대한 directive 분석
			Map<String, Integer> jspDirectiveMap = metadataDefinition.getWarDefinitionMap().get("physicianWebApp").getJspDirectiveMap();
			assertTrue("jspDirectiveMap의 크기는 3이어야 합니다.", jspDirectiveMap.size() == 3);
			
			// 6. 결과 압축 파일 생성 확인
			File resultFile = new File(this.getClass().getResource("/dependency").getFile(), "physicial-result.ear");
			assertTrue("physicial-result.ear 파일이 생성되어 있어야 합니다.", resultFile.exists());
			
			resultFile.delete();
		} catch(Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of testAnalyze()

}
//end of EarAnalyzerTest.java