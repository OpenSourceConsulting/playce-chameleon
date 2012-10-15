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
package com.athena.chameleon.engine.threadpool.task;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.Dependency;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;

/**
 * <pre>
 * java, jsp, properties 파일에 대한 상용 WAS 의존성 분석 기능을 확인하기 위한 테스트 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/context-*.xml" })
public class RegularFileDependencyCheckTaskTest {

	@Inject
	@Named("policy")
	private Policy policy;

	@Inject
	@Named("taskExecutor")
	private ChameleonThreadPoolExecutor executor;

	/**
	 * <pre>
	 * 필수 파라메타에 대한 Null 처리 테스트
	 * </pre>
	 */
	@Test
	public void testWithNullParam() {
		File file;
		String rootPath;
		AnalyzeDefinition analyzeDefinition;
		
		// 초기화
		file = null;
		rootPath = null;
		analyzeDefinition = null;

		try {
			// 테스트
			executor.execute(new RegularFileDependencyCheckTask(file, rootPath, policy, analyzeDefinition));
			fail("NullPointerException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 NullPointerException 이어야 합니다.", t instanceof NullPointerException);
		}
		
		// 초기화
		file = new File(this.getClass().getResource("/dependency/CartEJB.java").getFile());
		rootPath = null;
		analyzeDefinition = null;

		try {
			// 테스트
			executor.execute(new RegularFileDependencyCheckTask(file, rootPath, policy, analyzeDefinition));
			fail("IllegalArgumentException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 IllegalArgumentException 이어야 합니다.", t instanceof IllegalArgumentException);
		}
		
		// 초기화
		file = null;
		rootPath = this.getClass().getResource("/dependency").getFile();
		analyzeDefinition = null;

		try {
			// 테스트
			executor.execute(new RegularFileDependencyCheckTask(file, rootPath, policy, analyzeDefinition));
			fail("NullPointerException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 NullPointerException 이어야 합니다.", t instanceof NullPointerException);
		}
		
		// 초기화
		file = null;
		rootPath = null;
		analyzeDefinition = new AnalyzeDefinition();

		try {
			// 테스트
			executor.execute(new RegularFileDependencyCheckTask(file, rootPath, policy, analyzeDefinition));
			fail("NullPointerException이 발생해야 합니다.");
		} catch (Throwable t) {
			// 검증
			assertTrue("발생된 Exception은 NullPointerException 이어야 합니다.", t instanceof NullPointerException);
		}		
	}//end of testWithNullParam()
	
	/**
	 * <pre>
	 * java 파일에 대한 의존성 분석 테스트
	 * </pre>
	 */
	@Test
	public void testJavaDependency() {
		// 초기화
		File file = new File(this.getClass().getResource("/dependency/CartEJB.java").getFile());
		String rootPath = this.getClass().getResource("/dependency").getFile();
		AnalyzeDefinition analyzeDefinition = new AnalyzeDefinition();
		
		try {
			// 테스트
			executor.execute(new RegularFileDependencyCheckTask(file, rootPath, policy, analyzeDefinition));

			executor.getExecutor().shutdown();	

			// 테스트 Thread가 종료되길 기다린다.
			while (!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}
			
			// 검증
			List<Dependency> dependencyList = analyzeDefinition.getJavaDependencyList();
			assertTrue("dependencyList의 크기는 1이어야 합니다.", dependencyList.size() == 1);
			assertTrue("첫 번째 dependency는 \"CartEJB.java\" 이어야 합니다.", dependencyList.get(0).getFileName().equals("CartEJB.java"));
			assertTrue("\"CartEJB.java\"에는 3 개의 의존성이 있어야 합니다.", dependencyList.get(0).getDependencyStrMap().size() == 3);
		} catch (Throwable t) {
			t.printStackTrace();
			fail("NullPointerException이 발생하면 안됩니다.");
		}
	}//end of testJavaDependency()

}
//end of RegularFileDependencyCheckTaskTest.java