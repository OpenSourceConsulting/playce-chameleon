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
package com.athena.chameleon.engine.core.analizer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public abstract class AbstractAnalyzer implements Analyzer {
	
	protected static final Logger logger = LoggerFactory.getLogger(AbstractAnalyzer.class);
	
	protected Policy policy;
	protected FileEncodingConverter converter;
	protected ChameleonThreadPoolExecutor executor;
	
	protected List<File> warFileList = new ArrayList<File>();
	protected List<File> jarFileList = new ArrayList<File>();
	
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
	public void setConverter(FileEncodingConverter converter) {
		this.converter = converter;
	}
	
	public void setExecutor(ChameleonThreadPoolExecutor executor) {
		this.executor = executor;
	}

	public void analyze(String path) {
		analyze(new File(path));
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param file
	 */
	protected void defaultAnalyze(File file) {		
		File[] fileList = file.listFiles();
		
		String extension = null;
		for (File f : fileList) {
			if (f.isDirectory()) {
				defaultAnalyze(f);
			} else {
				extension = f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase();
				
				if (extension.equals("war")) {
					warFileList.add(f);
			    } else if (extension.equals("jar")) {
					jarFileList.add(f);
				} else if (extension.equals("java")) {
					javaAndJspAnalyze(f);
				} else if (extension.equals("jsp")) {
					javaAndJspAnalyze(f);
				} else if (extension.equals("class")) {
					classAnalyze(f);
				} else if (extension.equals("xml")) {
					// WEB-INF/web.xml
					
					// WEB-INF/application.xml

					// WEB-INF/weblogic.xml

					// WEB-INF/jeus-application-dd.xml
					
					// META-INF/ejb-jar.xml
					
					// META-INF/weblogic-ejb-jar.xml
					
					// META-INF/jeus-ejb-dd.xml
				}
			}
		}
	}//end of defaultAnalyzer()
	
	/**
	 * <pre>
	 * java 및 jsp 파일에 대한 의존성 분석
	 * </pre>
	 * @param file
	 */
	protected void javaAndJspAnalyze(File file) {
		
	}//end of javaAndJspAnalyze()
	
	/**
	 * <pre>
	 * class 파일에 대한 의존성 분석
	 * </pre>
	 * @param file
	 */
	protected void classAnalyze(File file) {
		
	}//end of classAnalyze()

    /**
     * <pre>
     * 생성된 임시 디렉토리를 삭제한다.
     * </pre>
     * @param path
     * @return
     */
    protected boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            
            for (File f : files) {
            	if (f.isDirectory()) {
            		deleteDirectory(f);
            	} else {
            		f.delete();
            	}
            }
        }
        
        return path.delete();
    }//end of deleteDirectory()
    
}//end of DependencyAnalyzer.java