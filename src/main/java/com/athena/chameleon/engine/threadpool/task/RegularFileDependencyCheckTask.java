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
		Matcher match = null;

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
				if(file.getName().endsWith("jsp")) {
					if(!isEnd) {
						// Ignore Carriage Return Line Feed
						directive += lineStr;
						
						if(directive.lastIndexOf("%>") > -1) {
							isEnd = true;
							
							// 동일한 Directive 가 있으면 add count, 없으면 신규 Directive 등록
							analyzeDefinition.addJspDirectiveCount(directive);
						}
					}
				}
				
				// Servlet 상속 여부 검사
				// 추후 패턴으로 지정하여 파일 전체 내용을 검사
				if (commonAnalyze == null && file.getName().endsWith("java") && lineStr.indexOf("extends HttpServlet") > -1) {
					commonAnalyze = new CommonAnalyze();
					commonAnalyze.setItem(file.getName());
					commonAnalyze.setLocation(file.getAbsolutePath().substring(rootPath.length(), file.getAbsolutePath().indexOf(file.getName())));
					
					analyzeDefinition.getServletExtendsList().add(commonAnalyze);
				}
				
				// EJB 관련 상속 여부 검사
				// 추후 패턴으로 지정하여 파일 전체 내용을 검사
				if (commonAnalyze == null && file.getName().endsWith("java") && lineStr.indexOf("extends EJBHome") > -1 || 
						lineStr.indexOf("extends EJBObject") > -1 || lineStr.indexOf("implements SessionBean") > -1) {
					
					commonAnalyze = new CommonAnalyze();
					commonAnalyze.setItem(file.getName());
					commonAnalyze.setLocation(file.getAbsolutePath().substring(rootPath.length(), file.getAbsolutePath().indexOf(file.getName())));
					
					analyzeDefinition.getServletExtendsList().add(commonAnalyze);
				}
				
				//  Weblogic, Jeus 등 상용 WAS 의존성 검사
			    match = pattern.matcher(lineStr);
			    if (match.matches()) {
			    	dependency.addDependencyStrMap(new String("Line " + Integer.toString(lineNum)), lineStr);
			    }
			    
				lineNum++;
			}
			
			IOUtils.closeQuietly(buffer);
			
			if (dependency.getDependencyStrMap().size() > 0) {
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