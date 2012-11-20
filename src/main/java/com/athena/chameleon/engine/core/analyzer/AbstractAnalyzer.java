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
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.chameleon.common.utils.ClasspathUtil;
import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.core.analyzer.parser.ApplicationXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.EjbJarXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.JeusApplicationDDXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.JeusEjbDDXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.JeusWebDDXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.PomXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.WebXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.WeblogicApplicationXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.WeblogicEjbJarXMLParser;
import com.athena.chameleon.engine.core.analyzer.parser.WeblogicXMLParser;
import com.athena.chameleon.engine.core.analyzer.support.EarAnalyzer;
import com.athena.chameleon.engine.core.analyzer.support.JarAnalyzer;
import com.athena.chameleon.engine.core.analyzer.support.WarAnalyzer;
import com.athena.chameleon.engine.core.analyzer.support.ZipAnalyzer;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
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
	protected AnalyzeDefinition analyzeDefinition;
	
	protected List<String> libFileList;
	protected List<String> deleteFileList;
	
	protected List<File> warFileList;
	protected List<File> jarFileList;
	
	public AbstractAnalyzer(Policy policy, FileEncodingConverter converter, ChameleonThreadPoolExecutor executor, AnalyzeDefinition analyzeDefinition) {
		this.policy = policy;
		this.converter = converter;
		this.executor = executor;
		this.analyzeDefinition = analyzeDefinition;
	}

	public String analyze(String path) {
		File file = new File(path);
		analyzeDefinition.setFileName(file.getName());
		
		return analyze(file);
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
	private void defaultAnalyze(File file, String rootPath) {		
		File[] fileList = file.listFiles();
		
		String extension = null;
		for (File f : fileList) {
			if (f.isDirectory()) {
				// EJB Archive 내에 Exploded 형태로 존재하는 WEB Directory 또는 EJB Directory인 경우 탐색하지 않는다.
				if(this instanceof EarAnalyzer && (warFileList.contains(file) || jarFileList.contains(file))) {
					return;
				}
				
				// classpath 내에 존재하는 디렉토리일 경우 디렉토리 갯수를 센다. 
				if (f.getAbsolutePath().startsWith(ClasspathUtil.lastAddedPath)) {
					analyzeDefinition.addClassDirCount();
				}
				defaultAnalyze(f, rootPath);
			} else {
				extension = f.getName().substring(f.getName().lastIndexOf(".") + 1).toLowerCase();

				if (extension.equals("java") || extension.equals("jsp") || extension.equals("properties")) {
					executor.execute(new RegularFileDependencyCheckTask(f, rootPath, policy, analyzeDefinition));
				} else if (extension.equals("class")) {
					// classpath 내에 존재하는 class 파일일 경우에만 의존성 검사를 수행한다.
					if (f.getAbsolutePath().startsWith(ClasspathUtil.lastAddedPath)) {
						analyzeDefinition.addClassFileCount();
						executor.execute(new ClassFileDependencyCheckTask(f, ClasspathUtil.lastAddedPath, policy, analyzeDefinition));
					}
				} else if (extension.equals("xml")) {
					// [war] WEB-INF/web.xml, WEB-INF/weblogic.xml, WEB-INF/jeus-web-dd.xml
					// [ear] META-INF/application.xml, META-INF/weblogic-application.xml, META-INF/jeus-application-dd.xml
					// [jar] META-INF/ejb-jar.xml, META-INF/weblogic-ejb-jar.xml, META-INF/jeus-ejb-dd.xml
					if (f.getParent().endsWith("WEB-INF")) {
						if (f.getName().equals("web.xml")) {
							if (this instanceof ZipAnalyzer || this instanceof WarAnalyzer) {
								new WebXMLParser().parse(f, analyzeDefinition);
							}
						} else if (f.getName().equals("weblogic.xml")) {
							if (this instanceof ZipAnalyzer || this instanceof WarAnalyzer) {
								new WeblogicXMLParser().parse(f, analyzeDefinition);
							}
						} else if (f.getName().equals("jeus-web-dd.xml")) {
							if (this instanceof ZipAnalyzer || this instanceof WarAnalyzer) {
								new JeusWebDDXMLParser().parse(f, analyzeDefinition);
							}
						}
					} else if (f.getParent().endsWith("META-INF")) {
						if (f.getName().equals("application.xml")) {
							// EarAnalyzer는 application.xml 파일을 미리 파싱한다.
							if (this instanceof ZipAnalyzer) {
								new ApplicationXMLParser().parse(f, analyzeDefinition);
							}
						} else if (f.getName().equals("weblogic-application.xml")) {
							if (this instanceof ZipAnalyzer || this instanceof EarAnalyzer) {
								new WeblogicApplicationXMLParser().parse(f, analyzeDefinition);
							}
						} else if (f.getName().equals("jeus-application-dd.xml") || f.getName().equals("JEUSMain.xml")) {
							if (this instanceof ZipAnalyzer || this instanceof EarAnalyzer) {
								new JeusApplicationDDXMLParser().parse(f, analyzeDefinition);
							}
						} else if (f.getName().equals("ejb-jar.xml")) {
							if (this instanceof ZipAnalyzer || this instanceof JarAnalyzer) {
								new EjbJarXMLParser().parse(f, analyzeDefinition);
							}
						} else if (f.getName().equals("weblogic-ejb-jar.xml")) {
							if (this instanceof ZipAnalyzer || this instanceof JarAnalyzer) {
								new WeblogicEjbJarXMLParser().parse(f, analyzeDefinition);
							}
						} else if (f.getName().equals("jeus-ejb-dd.xml")) {
							if (this instanceof ZipAnalyzer || this instanceof JarAnalyzer) {
								new JeusEjbDDXMLParser().parse(f, analyzeDefinition);
							}
						}
					} else {
						if (f.getName().equals("pom.xml")) {
							if (this instanceof ZipAnalyzer) {
								new PomXMLParser().parse(f, analyzeDefinition);
							}
						}
					}
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
     * WEB-INF 디렉토리를 탐색한다.
     * </pre>
     * @param dir
     * @return
     */
    protected File getWebInfDirPath(File dir) {
    	File path = null;
    	if (dir.exists()) {
            File[] files = dir.listFiles();
            
            for (File f : files) {
            	if (f.isDirectory()) {
            		// APP-INF/classes 또는 WEB-INF/classes를 탐색
            		if(f.getName().endsWith("WEB-INF")) {
            			path = f;
            			break;
            		} else {
            			path = getWebInfDirPath(f);
            		}
            	}
            }
        }
    	
    	return path;
    }//end of getWebInfDirPath()
    
    /**
     * <pre>
     * META-INF 디렉토리를 탐색한다.
     * </pre>
     * @param dir
     * @return
     */
    protected File getMetaInfDirPath(File dir) {
    	File path = null;
    	if (dir.exists()) {
            File[] files = dir.listFiles();
            
            for (File f : files) {
            	if (f.isDirectory()) {
            		// APP-INF/classes 또는 WEB-INF/classes를 탐색
            		if(f.getName().endsWith("META-INF")) {
            			path = f;
            			break;
            		} else {
            			path = getMetaInfDirPath(f);
            		}
            	}
            }
        }
    	
    	return path;
    }//end of getMetaInfDirPath()
    
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
    
    /**
     * <pre>
     * jboss-classloading.xml 파일을 생성한다.
     * </pre>
     * @param parentPath
     * @param domain
     * @param patentDomain
     */
    protected void makeClassLoading(File parentPath, String domain, String patentDomain) {
    	if (parentPath != null && domain != null) {
	    	StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
	    	
	    	if (StringUtils.isEmpty(patentDomain)) {
	    		sb.append("<classloading xmlns=\"urn:jboss:classloading:1.0\"\r\n")
	    		  .append("		  domain=\"").append(domain).append("\"\r\n")
	    		  .append("		  export-all=\"NON_EMPTY\"\r\n")
	    		  .append("		  import-all=\"true\"\r\n")
	    		  .append("		  parent-first=\"false\">\r\n")
	    		  .append("</classloading>");
	    	} else {
	    		/*
	    		sb.append("<classloading xmlns=\"urn:jboss:classloading:1.0\"\r\n")
		  		  .append("		  domain=\"").append(domain).append("\"\r\n")
		  		  .append("		  parent-domain=\"").append(patentDomain).append("\"\r\n")
		  		  .append("		  export-all=\"NON_EMPTY\"\r\n")
		  		  .append("		  import-all=\"true\">\r\n")
		  		  .append("</classloading>");
	    		*/
	    		sb.append("<classloading xmlns=\"urn:jboss:classloading:1.0\"\r\n")
		  		  .append("		  name=\"").append(domain).append("\"\r\n")
		  		  .append("		  domain=\"DefaultDomain\"\r\n")
		  		  .append("		  parent-domain=\"Ignored\"\r\n")
		  		  .append("		  parent-first=\"false\"\r\n")
		  		  .append("		  export-all=\"NON_EMPTY\"\r\n")
		  		  .append("		  import-all=\"true\">\r\n")
		  		  .append("</classloading>");
	    	}
	    	
	    	try {
				File file = new File(parentPath, "jboss-classloading.xml");
				FileWriter fw = new FileWriter(file);
				fw.write(sb.toString());
				IOUtils.closeQuietly(fw);
			} catch (IOException e) {
				logger.error("IOException has occurred.", e);
			}
    	}
    }//end of makeClassLoading()
    
    /**
     * <pre>
     * 절대경로 상에서 압축해제를 위한 임시 디렉토리까지의 경로를 삭제한 상대 경로를 구한다.
     * </pre>
     * @param fullPath
     * @param key
     * @return
     */
    protected String removeTempDir(String fullPath, String key) {
    	String tempPath = (String)ThreadLocalUtil.get(key);
    	
    	if(StringUtils.isEmpty(tempPath)) {
    		return fullPath;
    	} else {
    		return fullPath.substring(tempPath.length() + 1);
    	}
    }//end of removeTempDir()
    
}//end of DependencyAnalyzer.java