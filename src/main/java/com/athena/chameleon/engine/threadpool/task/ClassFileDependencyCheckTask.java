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

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.analyzer.Dependency;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;

/**
 * <pre>
 * Class 파일에 대하여 상용 WAS에 대한 의존석 분석을 위한 Runnable Task
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ClassFileDependencyCheckTask extends BaseTask {

	private File file;
	private String rootPath;
	private Policy policy;

	private Pattern pattern;
	private Matcher match;
	private Dependency dependency;
	private String className;
	
	public ClassFileDependencyCheckTask(File file, String rootPath, Policy policy) {
		this(file.getAbsoluteFile() + " Dependency Check Task", file, rootPath, policy);
	}
	
	public ClassFileDependencyCheckTask(String taskName, File file, String rootPath, Policy policy) {
		super(taskName);
		this.file = file;
		this.rootPath = rootPath;
		this.policy = policy;
	}
	
	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.threadpool.task.BaseTask#taskRun()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void taskRun() {
		className = file.getAbsolutePath()
						.substring(rootPath.length() + 1, file.getAbsolutePath().lastIndexOf("."))
						.replaceAll("/", ".");
		
		try {
			Class<?> clazz = Class.forName(className);

			dependency = new Dependency();
			dependency.setFileName(clazz.getCanonicalName());
			dependency.setExtension("class");

			pattern = policy.getPattern();
			classParse(clazz);

			if (dependency.getDependencyStrMap().size() > 0) {
				List<Dependency> dependencyFileList = (List<Dependency>)ThreadLocalUtil.get(ChameleonConstants.DEPENDENCY_FILE_LIST);
				
				if (dependencyFileList == null) {
					dependencyFileList = new ArrayList<Dependency>();
					ThreadLocalUtil.add(ChameleonConstants.DEPENDENCY_FILE_LIST, dependencyFileList);
				}
	    		
				dependencyFileList.add(dependency);
			}
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException has occurred : ", e);
		}
	}
	
	/**
	 * <pre>
	 * 입력된 클래스에 대한 의존성 검사를 수행하는 메소드
	 * </pre>
	 * @param clazz
	 */
	private void classParse(Class<?> clazz) {
		// 인터페이스에 대한 의존성 검사
		interfaceParse(clazz.getGenericInterfaces());
		
		// 인터페이스에 대한 의존성 검사
		superClassParse(getAncestor(clazz, new ArrayList<Class<?>>()));
		
		// 애노테이션에 대한 의존성 검사
		annotationParse(clazz.getDeclaredAnnotations());
		
		// 패키지에 대한 의존성 검사
		packageParse(clazz.getPackage());

		// 생성자에 대한 의존성 검사
		memberParse(clazz.getDeclaredConstructors());
		
		// 변수에 대한 의존성 검사
		memberParse(clazz.getDeclaredFields());
		
		// 메소드에 대한 의존성 검사
		memberParse(clazz.getDeclaredMethods());

		// 이너클래스에 대한 의존성 검사
		Class<?>[] clss = clazz.getDeclaredClasses();
		for (Class<?> cls : clss) {
			classParse(cls);
		}
	}//end of classParse()
	
	/**
	 * <pre>
	 * 인터페이스에 대한 의존성 검사를 수행하는 메소드
	 * </pre>
	 * @param interfaces
	 */
	private void interfaceParse(Type[] interfaces) {
		String value = null;
		if (interfaces.length != 0) {
			for (Type intf : interfaces) {
				value = intf.toString();
				
				match = pattern.matcher(value);
				
				if(match.matches()) {
					addDependencyStrMap("Interface", value);
				}					
			}
		}
	}//end of interfaceParse()
	
	/**
	 * <pre>
	 * 부모 클래스에 대한 의존성 검사를 수행하는 메소드
	 * </pre>
	 * @param ancestorList
	 */
	private void superClassParse(List<Class<?>> ancestorList) {
		String value = null;
		for (Class<?> clazz : ancestorList) {
			value = clazz.getCanonicalName();
			
			match = pattern.matcher(value);
			
			if(match.matches()) {
				addDependencyStrMap("SuperClass", value);
			}
		}
	}//end of superClassParse()
	
	/**
	 * <pre>
	 * Annotation에 대한 의존성 검사를 수행하는 메소드
	 * </pre>
	 * @param annotations
	 */
	private void annotationParse(Annotation[] annotations) {
		String value = null;
		if (annotations.length != 0) {
			for (Annotation annotation : annotations) {
				value = annotation.toString();
				
				match = pattern.matcher(value);
				
				if(match.matches()) {
					addDependencyStrMap("Annotation", value);
				}	
			}
		}
	}//end of annotationParse()
	
	/**
	 * <pre>
	 * 패키지명에 대한 의존성 검사를 수행하는 메소드
	 * </pre>
	 * @param pack
	 */
	private void packageParse(Package pack) {
		if(pack != null) {
			String value = pack.getName();
			match = pattern.matcher(value);
			
			if(match.matches()) {
				addDependencyStrMap("Package", value);
			}		
		}
	}//end of packageParse()
	
	/**
	 * <pre>
	 * Field, Constructor, Method에 대한 의존성 검사를 수행하는 메소드
	 * </pre>
	 * @param members
	 */
	private void memberParse(Member[] members) {
		String value = null;
		for (Member member : members) {
			if (member instanceof Field) {
				value = ((Field) member).toGenericString();
				match = pattern.matcher(value);
				
				if(match.matches()) {
					addDependencyStrMap("Field", value);
				}
			} else if (member instanceof Constructor) {
				value = ((Constructor<?>) member).toGenericString();
				match = pattern.matcher(value);
				
				if(match.matches()) {
					addDependencyStrMap("Constructor", value);
				}
			} else if (member instanceof Method) {
				value = ((Method) member).toGenericString();
				match = pattern.matcher(value);
				
				if(match.matches()) {
					addDependencyStrMap("Method", value);
				}
			}
		}
	}//end of memberParse()

	/**
	 * <pre>
	 * 상위 클래스 목록을 추출하기 위한 메소드
	 * </pre>
	 * @param clazz
	 * @param ancestorList
	 * @return
	 */
	private List<Class<?>> getAncestor(Class<?> clazz, List<Class<?>> ancestorList) {
		Class<?> ancestor = clazz.getSuperclass();
		if (ancestor != null && !ancestor.getCanonicalName().equals(Object.class.getCanonicalName())) {
			ancestorList.add(ancestor);
			ancestorList = getAncestor(ancestor, ancestorList);
		}
		
		return ancestorList;
	}//end of getAncestor()
	
	private void addDependencyStrMap(String type, String value) {
		String key = "#" + (dependency.getDependencyStrMap().size() + 1) + " : " + type;
		dependency.addDependencyStrMap(key, value);
	}//end of addDependencyStrMap()
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-*.xml");
		
		Policy policy = (Policy)context.getBean("policy");
		ChameleonThreadPoolExecutor executor = (ChameleonThreadPoolExecutor)context.getBean("taskExecutor");
		
		File file = new File("/Volumes/STORAGE/development/chameleon/workspace/athena-chameleon/target/classes/com/athena/chameleon/engine/core/analyzer/AbstractAnalyzer.class");
		
		ClassFileDependencyCheckTask task = new ClassFileDependencyCheckTask(file, "/Volumes/STORAGE/development/chameleon/workspace/athena-chameleon/target/classes", policy);
		
		executor.execute(task);
		executor.getExecutor().shutdown();
	}

}//end of ClassFileDependencyCheckTask.java