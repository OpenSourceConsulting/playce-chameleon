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
package com.athena.chameleon.engine.core.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.chameleon.common.utils.ClasspathUtil;
import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;
import com.athena.chameleon.engine.threadpool.task.ClassFileDependencyCheckTask;
import com.athena.chameleon.engine.threadpool.task.RegularFileDependencyCheckTask;


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
	
	protected List<File> warFileList;
	protected List<File> jarFileList;
	protected List<String> libFileList;
	protected List<String> deleteFileList;
	
	public AbstractAnalyzer(Policy policy, FileEncodingConverter converter, ChameleonThreadPoolExecutor executor) {
		this.policy = policy;
		this.converter = converter;
		this.executor = executor;
	}

	public void analyze(String path) {
		analyze(new File(path));
	}//end of analyze()
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param file
	 * @param rootPath
	 */
	protected void analyze(File file, String rootPath) {	
		defaultAnalyze(file, rootPath);
		
		executor.getExecutor().shutdown();
		
		try {
			while(!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//end of analyze()

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param file
	 * @param rootPath
	 */
	@SuppressWarnings("unchecked")
	private void defaultAnalyze(File file, String rootPath) {		
		File[] fileList = file.listFiles();
		
		String extension = null;
		for (File f : fileList) {
			if (f.isDirectory()) {
				defaultAnalyze(f, rootPath);
			} else {
				extension = f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase();
				
				
				/**
				
				if(this instanceof ZipAnalyzer) {
					
				} else if(this instanceof EarAnalyzer) {
					
				} else if(this instanceof WarAnalyzer) {
					
				} else if(this instanceof JarAnalyzer) {
					
				}

				// war, jar 파일은 EarAnalyzer
				
				// java 파일은 ZipAnalyzer
				
				// class 파일은 EarAnalyzer, WarAnalyzer, JarAnalyzer => ear, war, jar의 APP-INF/classes 또는 WEB-INF/classes, jar는 루트
				
				// jsp, properties는 ALL
				
				// xml은 ALL
				 
				 */
				
				if (extension.equals("war")) {
					if ((warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST)) == null) {
						warFileList = new ArrayList<File>();
						ThreadLocalUtil.add(ChameleonConstants.WAR_FILE_LIST, warFileList);
					}
					
					warFileList.add(f);
			    } else if (extension.equals("jar")) {
			    	// nothing to do
				} else if (extension.equals("java") || extension.equals("jsp") || extension.equals("properties")) {
					executor.execute(new RegularFileDependencyCheckTask(f, rootPath, policy));
				} else if (extension.equals("class")) {
					// classpath 내에 존재하는 class 파일일 경우에만 의존성 검사를 수행한다.
					if(f.getAbsolutePath().startsWith(ClasspathUtil.lastAddedPath)) {
						executor.execute(new ClassFileDependencyCheckTask(f, ClasspathUtil.lastAddedPath, policy));
					}
				} else if (extension.equals("xml")) {
					// [war] WEB-INF/web.xml

					// [war] WEB-INF/weblogic.xml

					// [war] WEB-INF/jeus-web-dd.xml
					
					// [ear] META-INF/application.xml

					/**
			    	// application.xml 분석 시 ejb 관련 jar 파일이 명시된 경우 jarFileList에 추가한다.
			    	// 프로젝트 소스로 입력된 경우 해당 jar 파일이 존재하지 않을 수 있으며, 이러한 경우 reporting 한다.
			    	
					if((jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST)) == null) {
						jarFileList = new ArrayList<File>();
						ThreadLocalUtil.add(ChameleonConstants.JAR_FILE_LIST, jarFileList);
					}
					
					jarFileList.add(f);
					*/
					
					// [ear] META-INF/weblogic-application.xml
					
					// [ear] META-INF/jeus-application-dd.xml
					
					// [ejb jar] META-INF/ejb-jar.xml
					
					// [ejb jar] META-INF/weblogic-ejb-jar.xml
					
					// [ejb jar] META-INF/jeus-ejb-dd.xml
				}
			}
		}
	}//end of defaultAnalyzer()

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
    
    /**
     * <pre>
     * classpath 추가를 위한 classes 디렉토리를 탐색한다.
     * </pre>
     * @param dir
     * @return
     */
    protected String getClassesDirPath(File dir) {
    	String path = null;
    	if (dir.exists()) {
            File[] files = dir.listFiles();
            
            for (File f : files) {
            	if (f.isDirectory()) {
            		// APP-INF/classes 또는 WEB-INF/classes를 탐색
            		if(f.getName().equals("classes") && f.getParent().endsWith("INF")) {
            			path = f.getAbsolutePath();
            			break;
            		} else {
            			path = getClassesDirPath(f);
            		}
            	}
            }
        }
    	
    	return path;
    }//end of getClassesDirPath()
    
    /**
     * <pre>
     * Migration 결과 디렉토리를 ${fileName}-result.${archiveType} 으로 재 압축하기 위한 파일명을 생성한다.
     * </pre>
     * @param file
     * @return
     */
    protected String getResultFile(File file) {
    	String resultFile = file.getAbsolutePath();
    	resultFile = resultFile.substring(0, resultFile.lastIndexOf(".")) + "-result" + resultFile.substring(resultFile.lastIndexOf("."));
    	
    	return resultFile;
    }//end of getResultFile()
    
}//end of DependencyAnalyzer.java