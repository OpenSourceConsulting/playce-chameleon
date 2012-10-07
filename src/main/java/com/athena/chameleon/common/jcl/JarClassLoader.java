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
 * Sang-cheon Park	2012. 10. 7.		First Draft.
 */
package com.athena.chameleon.common.jcl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JarClassLoader extends URLClassLoader {
	
	public JarClassLoader(URL url) {
		super(new URL[] { url });
	}

	public JarClassLoader(String urlString) throws MalformedURLException {
		this(new URL("jar:" + urlString + "!/"));
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param urlStrings
	 * @throws MalformedURLException
	 */
	public void addJarURLs(String[] urlStrings) throws MalformedURLException {
		if (urlStrings != null) {
			for (int i = 0; i < urlStrings.length; i++) {
				addJarURL(urlStrings[i]);
			}
		}
	}//end of addJarURLs()

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param urlString
	 * @throws MalformedURLException
	 */
	public void addJarURL(String urlString) throws MalformedURLException {
		if (urlString != null) {
			addURL(new URL("jar:" + urlString + "!/"));
		}
	}//end of addJarURL()

}
// end of JarClassLoader.java