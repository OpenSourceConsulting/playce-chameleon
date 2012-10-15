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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.athena.chameleon.common.jcl.JarClassLoader;
import com.athena.chameleon.common.utils.ClasspathUtil;
import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.common.utils.ZipUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.analyzer.AbstractAnalyzer;
import com.athena.chameleon.engine.core.analyzer.parser.ApplicationXMLParser;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.ArchiveType;
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

		// 입력된 파일명을 프로젝트 이름으로 사용한다.(jboss-app.xml, jboss-web.xml 파일 생성시 사용)
		ThreadLocalUtil.add(ChameleonConstants.PROJECT_NAME, file.getName().substring(0, file.getName().lastIndexOf(".")));
		
		String newFileName = null;
		
		try {
			// 임시 디렉토리에 압축 해제
			String tempDir = policy.getUnzipDir() + File.separator + System.currentTimeMillis();
			ZipUtil.decompress(file.getAbsolutePath(), tempDir);
			
			ThreadLocalUtil.add(ChameleonConstants.EAR_ROOT_DIR, tempDir);
			
			// EAR 패키징 파일 내부에 Web Application(s) 또는 EJB Application(s) 가 압축되지 않은 Exploded 형태로 존재할 경우
			// EarAnalyzer는 각 Application 내부에 존재하는 파일에 대해 인코딩 변경, 라이브러리 탐색, 의존성 탐색, deployment descriptor XML 파일 처리 등의 작업을
			// 중복해서 처리하게 된다. 따라서 EarAnalyzer는 먼저 /META-INF/application.xml 파일을 분석하여 
			// Web Application(s), EJB Applicaiton(s)가 존재하는지 먼저 확인하고, FileEncodingConverter 를 통한 인코딩 변경, analyze()를 이용한 디렉토리 내 파일 분석 시
			// 각 Application 디렉토리는 무시하도록 한다.
			//File appplicationXmlFile = new File(tempDir, "META-INF/application.xml");
			
			File appplicationXmlFile = null;
			if((appplicationXmlFile = getAppllicationXml(new File(tempDir))).exists()) {
				new ApplicationXMLParser().parse(appplicationXmlFile, analyzeDefinition);
				
				warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST);
				jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST);
			} else {
				warFileList = new ArrayList<File>();
				jarFileList = new ArrayList<File>();
			}
			analyzeDefinition.setWarFileList(warFileList);
			analyzeDefinition.setJarFileList(jarFileList);

			// 인코딩 변경
			converter.convert(new File(tempDir), analyzeDefinition);
			
			// 압축 해제 디렉토리 중 classes 디렉토리를 클래스 패스에 추가한다. 
			if(!StringUtils.isEmpty(getClassesDirPath(new File(tempDir)))) {
				JarClassLoader jcl = null;
				
				List<String> pathList = analyzeDefinition.getLibraryFullPathList();
				for(String str : pathList) {
					try {
						if(jcl == null) {
							jcl = new JarClassLoader(new File(str).toURI().toURL());
						} else {
							jcl.addJarURL(new File(str).toURI().toURL().toString());
						}
					} catch (Exception e) {
						// Ignore.
						logger.error("[{}] file can't add to Class Loader.", str);
					}
				}
				
				if(jcl == null) {
					jcl = new JarClassLoader(this.getClass().getResource("/lib/ejb-api-3.0.jar").toString());
				} else {
					jcl.addJarURL(this.getClass().getResource("/lib/ejb-api-3.0.jar").toString());
				}
				jcl.addJarURL(this.getClass().getResource("/lib/javax.servlet-api-3.0.1.jar").toString());
				jcl.addJarURL(this.getClass().getResource("/lib/javaee-api-6.0.jar").toString());
				jcl.addJarURL(this.getClass().getResource("/lib/weblogic.jar").toString());
				jcl.addJarURL(this.getClass().getResource("/lib/jeus.jar").toString());
				
				analyzeDefinition.setJcl(jcl);
				
	            ClasspathUtil.addPath(getClassesDirPath(new File(tempDir)), jcl);
			}
			
			// 압축 해제 디렉토리 내의 파일을 분석한다.
			analyze(new File(tempDir), tempDir);
			
			// war 파일이 존재할 경우 해당 war 파일에 대해 분석한다.
			if(warFileList != null && warFileList.size() > 0) {
				PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
				AnalyzeDefinition warDefinition = null;
				for(File warFile : warFileList) {
					warDefinition = new AnalyzeDefinition();
					metadataDefinition.addWarDefinitionMap(warFile.getName(), warDefinition);
					// 변환전 web 관련 xml 및 변환 후 jboss-web.xml 파일의 경로를 표시하기 위해
					ThreadLocalUtil.add(ChameleonConstants.DEPLOY_DIR_IN_EAR, removeTempDir(warFile.getAbsolutePath(), ChameleonConstants.EAR_ROOT_DIR));
					new WarAnalyzer(policy, converter, executor, warDefinition, true).analyze(warFile);
				}
			}
			
			// jar 파일이 존재할 경우 해당 jar 파일에 대해 분석한다.
			if(jarFileList != null && jarFileList.size() > 0) {
				PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
				AnalyzeDefinition jarDefinition = null;
				for(File jarFile : jarFileList) {
					jarDefinition = new AnalyzeDefinition();
					jarDefinition.setFileName(jarFile.getName());
					metadataDefinition.addJarDefinitionMap(jarFile.getName(), jarDefinition);
					// 변환전 ejb 관련 xml 및 변환 후 jboss.xml 파일의 경로를 표시하기 위해
					ThreadLocalUtil.add(ChameleonConstants.DEPLOY_DIR_IN_EAR, removeTempDir(jarFile.getAbsolutePath(), ChameleonConstants.EAR_ROOT_DIR));
					new JarAnalyzer(policy, converter, executor, jarDefinition, true).analyze(jarFile);
				}
			}
			
			// jboss-classloading.xml 파일을 생성한다.
			makeClassLoading(getMetaInfDirPath(new File(tempDir)), file.getName(), null);
			
			// 해당 ear 파일로 재 압축한다.
			newFileName = getResultFile(file);
			ZipUtil.compress(tempDir, newFileName, ArchiveType.EAR);
			
			// 임시 디렉토리를 삭제한다.
			deleteDirectory(new File(tempDir));
			
			ThreadLocalUtil.add(ChameleonConstants.EAR_ROOT_DIR, null);
		} catch (Exception e) {
			logger.error("Unahandled Exception has occurred : ", e);
		}
		
		return newFileName;
	}//end of analyze()

	/**
	 * <pre>
	 * 지정된 디렉토리로부터 META-INF/application.xml 파일을 탐색한다.
	 * </pre>
	 * @param baseDir
	 * @return
	 */
	private File getAppllicationXml(File baseDir) {
		File appXmlFile = null;
		
        File[] files = baseDir.listFiles();
        for (File f : files) {
        	if (f.isDirectory()) {
        		if(f.getName().equals("META-INF")) {
        			appXmlFile = new File(f, "application.xml");
        			break;
        		} else {
        			appXmlFile = getAppllicationXml(f);
        		}
        	}
        }
        
        return appXmlFile;
	}//end of getAppllicationXml()
	
}//end of EarAnalyzer.java