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

import com.athena.chameleon.common.utils.MigrationStatusUtil;
import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.common.utils.ZipUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.analyzer.AbstractAnalyzer;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.ArchiveType;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ZipAnalyzer extends AbstractAnalyzer {

	/**
	 * <pre>
	 * Constructor
	 * </pre>
	 * @param policy
	 * @param converter
	 * @param executor
	 */
	public ZipAnalyzer(Policy policy, FileEncodingConverter converter, ChameleonThreadPoolExecutor executor, AnalyzeDefinition analyzeDefinition) {
		super(policy, converter, executor, analyzeDefinition);
	}//end of Constructor
	
	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analizer.Analyzer#analyze(java.io.File)
	 */
	@Override
	public String analyze(File file) {
		Assert.notNull("file", "file must not be null.");
		Assert.isTrue(file.getName().endsWith(".zip"), "file name must be ends with \".zip\".");
		
		String newFileName = null;
		
		try {
			// 임시 디렉토리에 압축 해제
			MigrationStatusUtil.setCurrentStatus(MigrationStatusUtil.STEP1);
			MigrationStatusUtil.setPercentage(this, 20);
			String tempDir = policy.getUnzipDir() + File.separator + System.currentTimeMillis();
			ZipUtil.decompress(file.getAbsolutePath(), tempDir);
			
			ThreadLocalUtil.add(ChameleonConstants.ZIP_ROOT_DIR, tempDir);

			// 인코딩 변경
			MigrationStatusUtil.setCurrentStatus(MigrationStatusUtil.STEP2);
			MigrationStatusUtil.setPercentage(this, 40);
			converter.convert(new File(tempDir), analyzeDefinition);
			
			// 프로젝트 소스(zip) 입력 시 class 파일에 대한 의존성 검사를 수행하지 않는다. 
			// ClasspathUtil.addPath(tempDir);
			
			// 압축 해제 디렉토리 내의 파일을 분석한다.
			MigrationStatusUtil.setCurrentStatus(MigrationStatusUtil.STEP3);
			MigrationStatusUtil.setPercentage(this, 60);
			analyze(new File(tempDir), tempDir);
			
			// 해당 zip 파일을 xxx-result.zip으로 재 압축한다.
			MigrationStatusUtil.setCurrentStatus(MigrationStatusUtil.STEP4);
			MigrationStatusUtil.setPercentage(this, 80);
			newFileName = getResultFile(file);
			ZipUtil.compress(tempDir, newFileName, ArchiveType.ZIP);
			
			// 임시 디렉토리를 삭제한다.
			MigrationStatusUtil.setCurrentStatus(MigrationStatusUtil.STEP5);
			MigrationStatusUtil.setPercentage(this, 100);
			deleteDirectory(new File(tempDir));
			
			ThreadLocalUtil.add(ChameleonConstants.ZIP_ROOT_DIR, null);
		} catch (Exception e) {
			logger.error("Unahandled Exception has occurred : ", e);
		}
		
		return newFileName;
	}//end of analyze()
	
}//end of ZipAnalyzer.java