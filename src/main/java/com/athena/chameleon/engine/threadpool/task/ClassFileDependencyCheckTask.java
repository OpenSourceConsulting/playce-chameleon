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
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.ClassAnalyze;
import com.athena.chameleon.engine.entity.pdf.CommonAnalyze;
import com.athena.chameleon.engine.entity.pdf.Dependency;
import com.athena.chameleon.engine.policy.Policy;

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
	private AnalyzeDefinition analyzeDefinition;
	
	private Pattern pattern;
	private Matcher match;
	private Dependency dependency;
	private String className;
	
	public ClassFileDependencyCheckTask(File file, String rootPath, Policy policy, AnalyzeDefinition analyzeDefinition) {
		this(file.getAbsoluteFile() + " Dependency Check Task", file, rootPath, policy, analyzeDefinition);
	}
	
	public ClassFileDependencyCheckTask(String taskName, File file, String rootPath, Policy policy, AnalyzeDefinition analyzeDefinition) {
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
		className = file.getAbsolutePath()
						.substring(rootPath.length() + 1, file.getAbsolutePath().lastIndexOf("."))
						.replaceAll("/", ".");
		
		try {
			Class<?> clazz = Class.forName(className);

			dependency = new Dependency();
			dependency.setFileName(clazz.getCanonicalName());
			dependency.setExtension("class");
			
			ClassAnalyze classAnalyze = new ClassAnalyze();
			classAnalyze.setClassName(clazz.getCanonicalName());
			classAnalyze.setClassModifier(Modifier.toString(clazz.getModifiers()));
			classAnalyze.setFinalClass(Modifier.isFinal(clazz.getModifiers()));

			pattern = policy.getPattern();
			classParse(clazz, classAnalyze);

			if (dependency.getDependencyStrMap().size() > 0) {
				analyzeDefinition.getClassDependencyList().add(dependency);
			}
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException has occurred : ", e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * <pre>
	 * 입력된 클래스에 대한 의존성 검사를 수행하는 메소드
	 * </pre>
	 * @param clazz
	 * @param classAnalyze
	 */
	private void classParse(Class<?> clazz, ClassAnalyze classAnalyze) {
		className = clazz.getCanonicalName();
		
		// 인터페이스에 대한 의존성 검사
		interfaceParse(clazz.getGenericInterfaces(), classAnalyze);
		
		// 인터페이스에 대한 의존성 검사
		superClassParse(getAncestor(clazz, new ArrayList<Class<?>>()), classAnalyze);
		
		// 애노테이션에 대한 의존성 검사
		annotationParse(clazz.getDeclaredAnnotations());
		
		// 패키지에 대한 의존성 검사
		packageParse(clazz.getPackage());

		// 생성자에 대한 의존성 검사
		memberParse(clazz.getDeclaredConstructors(), classAnalyze);
		
		// 변수에 대한 의존성 검사
		memberParse(clazz.getDeclaredFields(), classAnalyze);
		
		// 메소드에 대한 의존성 검사
		memberParse(clazz.getDeclaredMethods(), classAnalyze);

		// 이너클래스에 대한 의존성 검사
		Class<?>[] clss = clazz.getDeclaredClasses();
		ClassAnalyze alalyze = null;
		for (Class<?> cls : clss) {
			alalyze = new ClassAnalyze();
			alalyze.setClassName(cls.getCanonicalName());
			alalyze.setClassModifier(Modifier.toString(cls.getModifiers()));
			alalyze.setFinalClass(Modifier.isFinal(cls.getModifiers()));
			
			classParse(cls, alalyze);
			
			classAnalyze.getClassAnalyzeList().add(alalyze);
		}
	}//end of classParse()
	
	/**
	 * <pre>
	 * 인터페이스에 대한 의존성 검사를 수행하는 메소드
	 * </pre>
	 * @param interfaces
	 * @param classAnalyze
	 */
	private void interfaceParse(Type[] interfaces, ClassAnalyze classAnalyze) {
		String value = null;
		if (interfaces.length != 0) {
			for (Type intf : interfaces) {
				value = intf.toString();
				classAnalyze.getInterfaces().add(value);

				// EJB 상속 여부
				if(value.indexOf("javax.ejb.SessionBean") > -1) {
					CommonAnalyze commonAnalyze = new CommonAnalyze();
					commonAnalyze.setItem(className.substring(className.lastIndexOf(".") + 1));
					commonAnalyze.setLocation(className.substring(0, className.lastIndexOf(".")));
					
					analyzeDefinition.getEjbExtendsList().add(commonAnalyze);
				}
				
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
	 * @param classAnalyze
	 */
	private void superClassParse(List<Class<?>> ancestorList, ClassAnalyze classAnalyze) {
		String value = null;
		for (Class<?> clazz : ancestorList) {
			value = clazz.getCanonicalName();
			classAnalyze.getSuperClasses().add(value);
			
			// HttpServlet 상속 여부
			if(value.indexOf("javax.ejb.SessionBean") > -1) {
				CommonAnalyze commonAnalyze = new CommonAnalyze();
				commonAnalyze.setItem(className.substring(className.lastIndexOf(".") + 1));
				commonAnalyze.setLocation(className.substring(0, className.lastIndexOf(".")));
				
				analyzeDefinition.getServletExtendsList().add(commonAnalyze);
			}
			
			// EJB 상속 여부
			if(value.indexOf("javax.ejb.EJBHome") > -1 || value.indexOf("javax.ejb.EJBObject") > -1) {
				CommonAnalyze commonAnalyze = new CommonAnalyze();
				commonAnalyze.setItem(className.substring(className.lastIndexOf(".") + 1));
				commonAnalyze.setLocation(className.substring(0, className.lastIndexOf(".")));
				
				analyzeDefinition.getEjbExtendsList().add(commonAnalyze);
			}
			
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
				
				// Annotation을 이용한 EJB 상속 여부
				if(value.indexOf("@javax.ejb.Stateless") > -1 || value.indexOf("@javax.ejb.Remote") > -1) {
					CommonAnalyze commonAnalyze = new CommonAnalyze();
					commonAnalyze.setItem(className.substring(className.lastIndexOf(".") + 1));
					commonAnalyze.setLocation(className.substring(0, className.lastIndexOf(".")));
					
					analyzeDefinition.getEjbExtendsList().add(commonAnalyze);
				}
				
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
	 * @param classAnalyze
	 */
	private void memberParse(Member[] members, ClassAnalyze classAnalyze) {
		String value = null;
		for (Member member : members) {
			if (member instanceof Field) {
				value = ((Field) member).toGenericString();
				classAnalyze.getFiledList().add(value);
				
				match = pattern.matcher(value);
				if(match.matches()) {
					addDependencyStrMap("Field", value);
				}
			} else if (member instanceof Constructor) {
				value = ((Constructor<?>) member).toGenericString();
				classAnalyze.getConstructorList().add(value);
				
				match = pattern.matcher(value);
				if(match.matches()) {
					addDependencyStrMap("Constructor", value);
				}
			} else if (member instanceof Method) {
				value = ((Method) member).toGenericString();
				classAnalyze.getMethodList().add(value);
				
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

}//end of ClassFileDependencyCheckTask.java