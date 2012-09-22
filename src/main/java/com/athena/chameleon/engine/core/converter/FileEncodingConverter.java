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

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;
import com.athena.chameleon.engine.threadpool.task.ConvertEncodingTask;

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
	public void convert(File file) {
		Assert.notNull(file, "file must not be null");
		Assert.isTrue(file.exists(), file + " does not exist.");

		logger.debug("Convert Target Path or File : [{}]", file.getAbsolutePath());
		
		convertAll(file);
		executor.getExecutor().shutdown();	
		
		try {
			while(!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//end of convert()
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param file
	 */
	private void convertAll(File file) {
		if(file.isDirectory()) {
			File[] files = null;
			files = file.listFiles();
			
			for(File f : files) {
				convertAll(f);
			}
		} else {
			// suffix property에 지정된 확장자를 가진 파일인지 검사
			if(ArrayUtils.contains(policy.getSuffix(), file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase())) {
				executor.execute(new ConvertEncodingTask(file, policy.getDefaultEncoding()));
			}
		}
	}//end of convertAll()

}//end of FileEncodingConverter.java