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
import com.athena.chameleon.engine.entity.pdf.FileSummary;
import com.athena.chameleon.engine.entity.pdf.FileType;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;
import com.athena.chameleon.engine.threadpool.task.ConvertEncodingTask;
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
    
    private FileType[] fileTypes = FileType.values();
    private Map<FileType, FileSummary> fileSummaryMap;
    private FileSummary fileSummary;
    private int totalCount;
    
	/**
	 * <pre>
	 * 
	 * </pre>
	 * 
	 * @param fqfn
	 */
	public void convert(String fqfn) {
		Assert.notNull(fqfn, "fqfn must not be null");
		convert(new File(fqfn));
	}//end of convert()

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param dir
	 */
	@SuppressWarnings("unchecked")
	public void convert(File file) {
		Assert.notNull(file, "file must not be null");
		Assert.isTrue(file.exists(), file + " does not exist.");

		logger.debug("Convert Target Path or File : [{}]", file.getAbsolutePath());
		
		fileSummaryMap = (Map<FileType, FileSummary>)ThreadLocalUtil.get(ChameleonConstants.FILE_SUMMARY);
		if (fileSummaryMap == null) {
			fileSummaryMap = new HashMap<FileType, FileSummary>();
			
			for (FileType fileType : fileTypes) {
				fileSummary = new FileSummary();
				fileSummary.setFileType(fileType);
				fileSummaryMap.put(fileType, fileSummary);
			}
			
			ThreadLocalUtil.add(ChameleonConstants.FILE_SUMMARY, fileSummaryMap);
		}
		
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
		fileSummaryMap.get(FileType.SUM).setFileCount(totalCount);
		
		for (FileType fileType : fileTypes) {
			logger.info("File Type : [{}], \tCount : [{}개], \tSource Encoding : [{}], \tTarget Encoding : [{}]", 
					new Object[] {String.format("%12s", fileSummaryMap.get(fileType).getFileType().toString()), 
								  String.format("%5s", fileSummaryMap.get(fileType).getFileCount()),
								  String.format("%12s", fileSummaryMap.get(fileType).getSourceEncoding()),
								  String.format("%5s", fileSummaryMap.get(fileType).getTargetEncoding())});
		}
		
	}//end of convert()
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param file
	 */
	@SuppressWarnings("unchecked")
	private void convertAll(File file) {
		if (file.isDirectory()) {
			fileSummary = ((Map<FileType, FileSummary>)ThreadLocalUtil.get(ChameleonConstants.FILE_SUMMARY)).get(FileType.DIRECTORY);
			fileSummary.addCount();
			totalCount++;
			
			File[] files = null;
			files = file.listFiles();
			
			for (File f : files) {
				convertAll(f);
			}
		} else {
			String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
			
			for (FileType fileType : fileTypes) {
				if (fileType.name().equals(extension.toUpperCase())) {
					fileSummary = ((Map<FileType, FileSummary>)ThreadLocalUtil.get(ChameleonConstants.FILE_SUMMARY)).get(fileType);
					fileSummary.addCount();
					totalCount++;
					
					// 해당 파일 타입에 대한 소스 인코딩이 정의되어 있지 않은 경우 제일 처음 탐색되는 파일로 소스 인코딩을 판별한다.
					if (fileSummary.getSourceEncoding().equals("N/A") && !fileType.equals(FileType.JAR)) {
						try {
							InputStream input = new FileInputStream(file);
							byte[] data = IOUtils.toByteArray(input, file.length());
							IOUtils.closeQuietly(input);
							
							CharsetDetector detector = new CharsetDetector();
							detector.setDeclaredEncoding(policy.getDefaultEncoding());
							detector.setText(data);
							CharsetMatch cm = detector.detect();
							
							//*===========================================================
							if(cm.getName().equals(policy.getDefaultEncoding())) {
								continue;
							}
							//===========================================================*/
							
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
				executor.execute(new ConvertEncodingTask(file, policy.getDefaultEncoding()));
			}
		}
	}//end of convertAll()

}//end of FileEncodingConverter.java