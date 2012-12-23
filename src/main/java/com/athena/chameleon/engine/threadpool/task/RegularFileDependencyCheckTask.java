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
 * Sang-cheon Park	2012. 9. 25.		First Draft.
 */
package com.athena.chameleon.engine.threadpool.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;

import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.CommonAnalyze;
import com.athena.chameleon.engine.entity.pdf.Dependency;
import com.athena.chameleon.engine.policy.Policy;

/**
 * <pre>
 * JAVA, JSP, Properties 파일등 일반 파일에 대하여 상용 WAS에 대한 의존성 분석을 위한 Runnable Task
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class RegularFileDependencyCheckTask extends BaseTask {

	private File file;
	private String rootPath;
	private Policy policy;
	private AnalyzeDefinition analyzeDefinition;
	
	public RegularFileDependencyCheckTask(File file, String rootPath, Policy policy, AnalyzeDefinition analyzeDefinition) {
		this(file.getAbsoluteFile() + " Dependency Check Task", file, rootPath, policy, analyzeDefinition);
	}
	
	public RegularFileDependencyCheckTask(String taskName, File file, String rootPath, Policy policy, AnalyzeDefinition analyzeDefinition) {
		super(taskName);
		
		Assert.notNull(file, "file cannot be null");
		Assert.notNull(rootPath, "rootPath cannot be null");
		Assert.notNull(analyzeDefinition, "analyzeDefinition cannot be null");
		
		this.file = file;
		this.rootPath = rootPath;
		this.policy = policy;
		this.analyzeDefinition = analyzeDefinition;
	}

	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.threadpool.task.BaseTask#taskRun()
	 */
	@Override
	protected void taskRun() {
		Pattern pattern = policy.getPattern();
		Pattern etcPattern = policy.getEtcPattern();
		Matcher match = null;
		
		String[] encodings = policy.getEncodings();

		try {
			Dependency dependency = new Dependency();
			dependency.setFileName(file.getAbsolutePath().substring(rootPath.length() + 1));
			dependency.setExtension(file.getName().substring(file.getName().lastIndexOf(".") + 1));
			
			CommonAnalyze commonAnalyze = null;
		
			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);

			String directive = "";
			boolean isEnd = false;
			String lineStr = null;
			int lineNum = 1;
			while ((lineStr = buffer.readLine()) != null) {
				
				// JSP Directive 검사
				if (file.getName().endsWith("jsp")) {
					if (!isEnd) {
						if (lineStr.indexOf("<%@page") > -1 || lineStr.indexOf("<%@ page") > -1) {
							directive += lineStr;
						}
						
						if (lineStr.lastIndexOf("%>") > -1 && !directive.equals("")) {
							if(!directive.equals(lineStr)) {
								// Ignore Carriage Return Line Feed
								directive += lineStr;
							}
							
							isEnd = true;
							
							// 동일한 Directive 가 있으면 add count, 없으면 신규 Directive 등록
							analyzeDefinition.addJspDirectiveCount(directive);
						}
					}
				}
				
				// Java 파일 내 Servlet 상속 여부, EJB 관련 상속 여부, 인코딩 변경 여부 검사
				if (file.getName().endsWith("java")) {

					// Servlet 상속 여부 검사
					if(commonAnalyze == null && (lineStr.indexOf("extends HttpServlet") > -1 || 
							lineStr.indexOf("extends javax.servlet.http.HttpServlet") > -1 || 
							lineStr.indexOf("@Controller") > -1)) {
						commonAnalyze = new CommonAnalyze();
						commonAnalyze.setItem(file.getName());
						commonAnalyze.setLocation(file.getAbsolutePath().substring(rootPath.length(), file.getAbsolutePath().indexOf(file.getName())));
						
						analyzeDefinition.getServletExtendsList().add(commonAnalyze);
					}

					// EJB 관련 상속 여부 검사
					if(commonAnalyze == null && (lineStr.indexOf("extends EJBHome") > -1 || lineStr.indexOf("extends javax.ejb.EJBHome") > -1 ||
							lineStr.indexOf("extends EJBObject") > -1 || lineStr.indexOf("extends javax.ejb.EJBObject") > -1 || 
							lineStr.indexOf("implements SessionBean") > -1 || lineStr.indexOf("implements javax.ejb.SessionBean") > -1 ||
							lineStr.indexOf("@Stateless") > -1 || lineStr.indexOf("@Stateful") > -1 || 
							lineStr.indexOf("@Entity") > -1 || lineStr.indexOf("@Remote") > -1)) {
						commonAnalyze = new CommonAnalyze();
						commonAnalyze.setItem(file.getName());
						commonAnalyze.setLocation(file.getAbsolutePath().substring(rootPath.length(), file.getAbsolutePath().indexOf(file.getName())));
						
						analyzeDefinition.getEjbExtendsList().add(commonAnalyze);
					}
					
					// 인코딩 변경 여부 검사
					for(String encoding : encodings) {
						if(lineStr.indexOf(encoding) > -1) {
							dependency.addEncodingStrMap(new String("Line " + Integer.toString(lineNum)) + " : ", lineStr);
						}
					}
				}
				
				//  WehSphere, Weblogic, Jeus 등 상용 WAS 의존성 검사
			    match = pattern.matcher(lineStr);
			    if (match.matches()) {
			    	dependency.addDependencyStrMap(new String("Line " + Integer.toString(lineNum)) + " : ", lineStr);
			    }
				
				// Connection URL 등 기타 레포트 대상 요소 존재여부 검사
			    match = etcPattern.matcher(lineStr);
			    if (match.matches()) {
			    	dependency.addOthersStrMap(new String("Line " + Integer.toString(lineNum)) + " : ", lineStr);
			    }
			    
				lineNum++;
			}
			
			IOUtils.closeQuietly(buffer);
			
			if (dependency.getDependencyStrMap().size() > 0 || dependency.getOthersStrMap().size() > 0 || dependency.getEncodingStrMap().size() > 0) {
				if (file.getName().endsWith("java")) {
		    		analyzeDefinition.getJavaDependencyList().add(dependency);
		    	} else if (file.getName().endsWith("jsp")) {
		    		analyzeDefinition.getJspDependencyList().add(dependency);
		    	} else if (file.getName().endsWith("properties")) {
		    		analyzeDefinition.getPropertyDependencyList().add(dependency);
		    	}
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException has occurred : ", e);
		} catch (IOException e) {
			logger.error("IOException has occurred : ", e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}//end of RegularFileDependencyCheckTask.java