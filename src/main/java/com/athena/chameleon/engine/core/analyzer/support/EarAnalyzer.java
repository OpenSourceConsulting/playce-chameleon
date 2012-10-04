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
import java.util.List;

import org.springframework.util.Assert;

import com.athena.chameleon.common.utils.ClasspathUtil;
import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.common.utils.ZipUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.analyzer.AbstractAnalyzer;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
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
public class EarAnalyzer extends AbstractAnalyzer {
	
	private List<File> warFileList;
	private List<File> jarFileList;

	/**
	 * <pre>
	 * Constructor
	 * </pre>
	 * @param policy
	 * @param converter
	 * @param executor
	 */
	public EarAnalyzer(Policy policy, FileEncodingConverter converter, ChameleonThreadPoolExecutor executor, AnalyzeDefinition analyzeDefinition) {
		super(policy, converter, executor, analyzeDefinition);
	}//end of Constructor

	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analizer.Analyzer#analyze(java.io.File)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String analyze(File file) {
		Assert.notNull("file", "file must not be null.");
		Assert.isTrue(file.getName().endsWith(".ear"), "file name must be ends with \".ear\".");
		
		String newFileName = null;
		
		try {
			// 임시 디렉토리에 압축 해제
			String tempDir = policy.getUnzipDir() + File.separator + System.currentTimeMillis();
			ZipUtil.decompress(file.getAbsolutePath(), tempDir);

			// 인코딩 변경
			converter.convert(new File(tempDir), analyzeDefinition);
			
			// 압축 해제 디렉토리 중 classes 디렉토리를 클래스 패스에 추가한다. 
			ClasspathUtil.addPath(getClassesDirPath(new File(tempDir)));
			
			// 압축 해제 디렉토리 내의 파일을 분석한다.
			analyze(new File(tempDir), tempDir);
			
			// war 파일이 존재할 경우 해당 war 파일에 대해 분석한다.
			warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
			if(warFileList != null && warFileList.size() > 0) {
				PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
				AnalyzeDefinition warDefinition = null;
				for(File warFile : warFileList) {
					warDefinition = new AnalyzeDefinition();
					metadataDefinition.addWarDefinitionMap(warFile.getName(), warDefinition);
					new WarAnalyzer(policy, converter, executor, warDefinition, true).analyze(warFile);
				}
			}
			
			// jar 파일이 존재할 경우 해당 jar 파일에 대해 분석한다.
			jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			if(jarFileList != null && jarFileList.size() > 0) {
				PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
				AnalyzeDefinition jarDefinition = null;
				for(File jarFile : jarFileList) {
					jarDefinition = new AnalyzeDefinition();
					metadataDefinition.addWarDefinitionMap(jarFile.getName(), jarDefinition);
					new JarAnalyzer(policy, converter, executor, jarDefinition, true).analyze(jarFile);
				}
			}
			
			// jboss-classloading.xml 파일을 생성한다.
			makeClassLoading(new File(tempDir, "META-INF"), file.getName(), null);
			
			// 해당 ear 파일로 재 압축한다.
			newFileName = getResultFile(file);
			ZipUtil.compress(tempDir, newFileName);
			
			// 임시 디렉토리를 삭제한다.
			deleteDirectory(new File(tempDir));
			
			// ThreadLocal에 저장된 의존성 검사 및 DD 관련 재 가공 후 ThreadLocal에 저장
			
		} catch (Exception e) {
			logger.error("Unahandled Exception has occurred : ", e);
		}
		
		return newFileName;
	}//end of analyze()
	
}//end of EarAnalyzer.java