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
import org.xeustechnologies.jcl.JarClassLoader;

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
public class JarAnalyzer extends AbstractAnalyzer {
	
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
	public JarAnalyzer(Policy policy, FileEncodingConverter converter, ChameleonThreadPoolExecutor executor, AnalyzeDefinition analyzeDefinition, boolean embed) {
		super(policy, converter, executor, analyzeDefinition);
		this.embed = embed;
	}//end of Constructor
	
	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analizer.Analyzer#analyze(java.io.File)
	 */
	@Override
	public String analyze(File file) {
		Assert.notNull("file", "file must not be null.");
		//Assert.isTrue(file.getName().endsWith(".jar"), "file name must be ends with \".jar\".");

		boolean isExploded = false;
		if (file.getName().endsWith(".jar")) {
			if (file.isDirectory()) {
				isExploded = true;
			}
		} else {
			isExploded = true;
		}
		
		String newFileName = null;
		
		try {
			// 임시 디렉토리에 압축 해제
			String tempDir = null;
			
			if(isExploded) {
				tempDir = file.getAbsolutePath();
			} else {
				tempDir = policy.getUnzipDir() + File.separator + System.currentTimeMillis();
				ZipUtil.decompress(file.getAbsolutePath(), tempDir);
			}
			
			ThreadLocalUtil.add(ChameleonConstants.JAR_ROOT_DIR, tempDir);

			// 인코딩 변경
			converter.convert(new File(tempDir), analyzeDefinition);
			
			// 압축 해제 디렉토리를 클래스 패스에 추가한다. 
			JarClassLoader jcl = null;
			
			if(embed) {
				jcl = ((PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION)).getEarDefinition().getJcl();
			} 
			
			if(jcl == null) {
				jcl = new JarClassLoader();

				jcl.add(this.getClass().getResource("/lib/ejb-api-3.0.jar"));
				jcl.add(this.getClass().getResource("/lib/javax.servlet-api-3.0.1.jar"));
				jcl.add(this.getClass().getResource("/lib/javaee-api-6.0.jar"));
				jcl.add(this.getClass().getResource("/lib/weblogic.jar"));
				jcl.add(this.getClass().getResource("/lib/jeus.jar"));

				analyzeDefinition.setJcl(jcl);
			}
			
			ClasspathUtil.addPath(tempDir, jcl);
			
			// 압축 해제 디렉토리 내의 파일을 분석한다.
			analyze(new File(tempDir), tempDir);

			if(embed) {
				((PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION)).getEarDefinition()
				.getEjbApplicationMap().put(file.getName(), analyzeDefinition.getEjbApplicationMap().get(analyzeDefinition.getFileName()));
			}

			if(!isExploded) {
				// 임시디렉토리를 재 압축한다.
				newFileName = embed ? file.getAbsolutePath() : getResultFile(file);
				ZipUtil.compress(tempDir, newFileName);
				
				// 임시 디렉토리를 삭제한다.
				deleteDirectory(new File(tempDir));
			}
			
			ThreadLocalUtil.add(ChameleonConstants.JAR_ROOT_DIR, null);
		} catch (Exception e) {
			logger.error("Unahandled Exception has occurred : ", e);
		}
		
		return newFileName;
	}//end of analyze()

}//end of JarAnalyzer.java