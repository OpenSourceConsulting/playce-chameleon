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
 * Sang-cheon Park	2012. 9. 24.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.support;

import java.io.File;

import org.springframework.util.Assert;

import com.athena.chameleon.common.utils.ClasspathUtil;
import com.athena.chameleon.common.utils.ZipUtil;
import com.athena.chameleon.engine.core.analyzer.AbstractAnalyzer;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class WarAnalyzer extends AbstractAnalyzer {
	
	// Ear Application 내에 포함된 Archive 인지의 여부
	// 재압축 시 Ear Application 내의 Archive일 경우 동일한 이름으로 재압축 하기 위해 사용됨.
	private boolean embed;

	/**
	 * <pre>
	 * Constructor
	 * </pre>
	 * @param policy
	 * @param converter
	 * @param executor
	 */
	public WarAnalyzer(Policy policy, FileEncodingConverter converter, ChameleonThreadPoolExecutor executor, boolean embed) {
		super(policy, converter, executor);
		this.embed = embed;
	}//end of Constructor
	
	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analizer.Analyzer#analyze(java.io.File)
	 */
	@Override
	public void analyze(File file) {
		Assert.notNull("file", "file must not be null.");
		Assert.isTrue(file.getName().endsWith(".war"), "file name must be ends with \".war\".");
		
		try {
			// 임시 디렉토리에 압축 해제
			String tempDir = policy.getUnzipDir() + File.separator + System.currentTimeMillis();
			ZipUtil.decompress(file.getAbsolutePath(), tempDir);
			
			/****************************************************************************************
			 * 
			 * TODO Should be Delete. MAC 에서 테스트 애플리케이션 입력 시 __MACOSX 라는 garbage 디렉토리가 생김.
			 * 
			 ****************************************************************************************/
			if(new File(tempDir, "__MACOSX").exists()) {
				deleteDirectory(new File(tempDir, "__MACOSX"));
			}

			// 인코딩 변경
			converter.convert(new File(tempDir));
			
			// 압축 해제 디렉토리 중 classes 디렉토리를 클래스 패스에 추가한다. 
			ClasspathUtil.addPath(getClassesDirPath(new File(tempDir)));
			
			// 압축 해제 디렉토리 내의 파일을 분석한다.
			analyze(new File(tempDir), tempDir);

			// 임시디렉토리를 재 압축한다.
			ZipUtil.compress(tempDir, (embed ? file.getAbsolutePath() : getResultFile(file)));
			
			// 임시 디렉토리를 삭제한다.
			deleteDirectory(new File(tempDir));
			
			// ThreadLocal에 저장된 의존성 검사 및 DD 관련 재 가공 후 ThreadLocal에 저장
			
		} catch (Exception e) {
			logger.error("Unahandled Exception has occurred : ", e);
		}
	}//end of analyze()

}//end of WarAnalyzer.java