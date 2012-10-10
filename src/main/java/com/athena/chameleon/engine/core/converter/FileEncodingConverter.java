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
 * Sang-cheon Park	2012. 9. 20.		First Draft.
 */
package com.athena.chameleon.engine.core.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.ArchiveType;
import com.athena.chameleon.engine.entity.pdf.FileSummary;
import com.athena.chameleon.engine.entity.pdf.FileType;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;
import com.athena.chameleon.engine.threadpool.task.FileEncodingConvertTask;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

/**
 * <pre>
 * 지정된 디렉토리 하위의 파일 목록을 탐색하면서 변환 대상 파일이 존재할 경우 ConvertEncodingTask를 이용해 Runnable Task를 생성하고
 * ThreadPoolExecutor를 이용해 실행한다.
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component("fileEncodingConverter")
public class FileEncodingConverter {
	
    private static final Logger logger = LoggerFactory.getLogger(FileEncodingConverter.class);
	
    @Inject
    @Named("policy")
    private Policy policy;

    @Inject
    @Named("taskExecutor")
	private ChameleonThreadPoolExecutor executor;
    
    private AnalyzeDefinition analyzeDefinition;
    private FileType[] fileTypes = FileType.values();
    private Map<FileType, FileSummary> fileSummaryMap;
    private FileSummary fileSummary;
    
    private List<File> warFileList;
    private List<File> jarFileList;
    
	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param fqfn
	 */
	public void convert(String fqfn, AnalyzeDefinition analyzeDefinition) {
		Assert.notNull(fqfn, "fqfn must not be null");
		Assert.notNull(analyzeDefinition, "analyzeDefinition must not be null");
		convert(new File(fqfn), analyzeDefinition);
	}//end of convert()

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param dir
	 */
	public void convert(File file, AnalyzeDefinition analyzeDefinition) {
		Assert.notNull(file, "file must not be null");
		Assert.notNull(analyzeDefinition, "analyzeDefinition must not be null");
		Assert.isTrue(file.exists(), file + " does not exist.");

		logger.debug("Convert Target Path or File : [{}]", file.getAbsolutePath());
		
		this.analyzeDefinition = analyzeDefinition;
		
		fileSummaryMap = new HashMap<FileType, FileSummary>();
		for (FileType fileType : fileTypes) {
			fileSummary = new FileSummary();
			fileSummary.setFileType(fileType);
			fileSummaryMap.put(fileType, fileSummary);
		}
		
		PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
		analyzeDefinition.setFileName(file.getAbsolutePath());
		analyzeDefinition.setFileSummaryMap(fileSummaryMap);
		
		warFileList = analyzeDefinition.getWarFileList();
		jarFileList = analyzeDefinition.getJarFileList();
		
		convertAll(file);
		executor.getExecutor().shutdown();	
		
		try {
			while (!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 모든 Runnable Task가 완료된 후 전체 항목에 대한 Total Count를 세팅한다.
		int totalCount = 0;
		for (FileType fileType : fileTypes) {
			if(!fileType.equals(FileType.SUM)) {
				totalCount += fileSummaryMap.get(fileType).getFileCount();
			}
		}

		fileSummaryMap.get(FileType.SUM).setFileCount(totalCount);
		
		for (FileType fileType : fileTypes) {
			totalCount += fileSummaryMap.get(fileType).getFileCount();
			logger.info("File Type : [{}], \tCount : [{}개], \tSource Encoding : [{}], \tTarget Encoding : [{}]", 
					new Object[] {String.format("%12s", fileSummaryMap.get(fileType).getFileType().toString()), 
								  String.format("%5s", fileSummaryMap.get(fileType).getFileCount()),
								  String.format("%12s", fileSummaryMap.get(fileType).getSourceEncoding()),
								  String.format("%5s", fileSummaryMap.get(fileType).getTargetEncoding())});
		}
		
		// 파일 확장자에 따라 파일 요약정보를 PDFMetadataDefinition에 저장한다.
		String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
		
		if (ArchiveType.ZIP.value().equals(extension)) {
			metadataDefinition.setZipDefinition(analyzeDefinition);
		} else if (ArchiveType.EAR.value().equals(extension)) {
			metadataDefinition.setEarDefinition(analyzeDefinition);
		} else if (ArchiveType.WAR.value().equals(extension)) {
			metadataDefinition.addWarDefinitionMap(file.getName(), analyzeDefinition);
		} else if (ArchiveType.JAR.value().equals(extension)) {
			metadataDefinition.addJarDefinitionMap(file.getName(), analyzeDefinition);
		}
		
	}//end of convert()
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param file
	 */
	private void convertAll(File file) {
		if (file.isDirectory()) {
			// EJB Archive 내에 Exploded 형태로 존재하는 WEB Directory 또는 EJB Directory인 경우 탐색하지 않는다.
			if(warFileList.contains(file) || jarFileList.contains(file)) {
				return;
			}
			
			fileSummary = fileSummaryMap.get(FileType.DIRECTORY);
			fileSummary.addCount();
			
			File[] files = null;
			files = file.listFiles();
			
			for (File f : files) {
				convertAll(f);
			}
		} else {
			String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
			
			for (FileType fileType : fileTypes) {
				if (fileType.value().equals(extension)) {
					fileSummary = fileSummaryMap.get(fileType);
					fileSummary.addCount();
					
					if(fileType.equals(FileType.JAR) && file.getParent().endsWith("lib")) {
						// xerces.jar, xalan.jar, xml-api.jar, jboss-*.jar 파일이 존재할 경우 제거
			    		if (file.getName().startsWith("xerces") || file.getName().startsWith("xalan") || 
			    				file.getName().startsWith("xml-api") || file.getName().startsWith("jboss-")) {
			    			
			    			// 삭제 파일 목록에 추가 후 파일 삭제
			    			analyzeDefinition.getDeleteLibraryList().add(file.getName());
			    			//file.delete();
			    			// 파일을 삭제하지 않고 .bak 확장자를 추가한다.
			    			file.renameTo(new File(file.getAbsolutePath() + ".bak"));
			    		} else {
			    			// 라이브러리 파일 목록에 추가
			    			analyzeDefinition.getLibraryList().add(file.getName());
			    			analyzeDefinition.getLibraryFullPathList().add(file.getAbsolutePath());
			    		}
		    			continue;
					}
					
					// 해당 파일 타입에 대한 소스 인코딩이 정의되어 있지 않은 경우 제일 처음 탐색되는 파일로 소스 인코딩을 판별한다.
					if ((fileSummary.getSourceEncoding().equals("N/A") || fileSummary.getSourceEncoding().equals(policy.getDefaultEncoding())) && !fileType.equals(FileType.CLASS) && !fileType.equals(FileType.JAR)) {
						try {
							InputStream input = new FileInputStream(file);
							byte[] data = IOUtils.toByteArray(input, file.length());
							IOUtils.closeQuietly(input);
							
							CharsetDetector detector = new CharsetDetector();
							detector.setDeclaredEncoding(policy.getDefaultEncoding());
							detector.setText(data);
							CharsetMatch cm = detector.detect();
							
							fileSummary.setSourceEncoding(cm.getName());
							fileSummary.setTargetEncoding(policy.getDefaultEncoding());
						} catch (FileNotFoundException e) {
							logger.error("FileNotFoundException has occurred.", e);
						} catch (IOException e) {
							logger.error("IOException has occurred.", e);
						}
					}
				}
			}
			
			// suffix property에 지정된 확장자를 가진 파일인지 검사
			if (ArrayUtils.contains(policy.getSuffix(), extension)) {
				executor.execute(new FileEncodingConvertTask(file, policy.getDefaultEncoding()));
			}
		}
	}//end of convertAll()

}//end of FileEncodingConverter.java