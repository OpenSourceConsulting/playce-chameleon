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
 * Sang-cheon Park	2012. 9. 19.		First Draft.
 */
package com.athena.chameleon.common.utils;

import com.athena.chameleon.common.jcl.JarClassLoader;

/**
 * <pre>
 * Runtime 시 Classpath를 동적으로 추가하기 위한 유틸 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ClasspathUtil {

	public static String lastAddedPath = "NONE";
	
	/**
	 * <pre>
	 * ClassLoader에 지정된 path를 추가한다.
	 * </pre>
	 * 
	 * @param path
	 * @throws Exception
	 */
	public static void addPath(String path, JarClassLoader jcl) throws Exception {
		java.io.File file = new java.io.File(path);
		java.net.URL url = file.toURI().toURL();
		//java.net.URLClassLoader urlClassLoader = (java.net.URLClassLoader) ClassLoader.getSystemClassLoader();
		Class<?> urlClass = java.net.URLClassLoader.class;
		java.lang.reflect.Method method = urlClass.getDeclaredMethod("addURL", new Class[] { java.net.URL.class });
		method.setAccessible(true);
		//method.invoke(urlClassLoader, new Object[] { url });
		method.invoke(jcl, new Object[] { url });
		
		lastAddedPath = path;
	}//end of addPath()
}//end of ClasspathUtil.java