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
 * Author           Date                Description
 * ---------------  ----------------    ------------
 * Ji-Woong Choi    2012. 10. 5.        First Draft.
 */
package com.athena.peacock.engine.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Stack Trace를 문자열로 가져옴 
 * 
 * @author Junshik Jeon(jjeon@redhat.com)
 *
 */
public class StackTracer {

	/**
	 * Exception에서 StackTrace를 문자열로 반환
	 * 
	 * @param e
	 * @return
	 */
	public static String getStackTrace(Exception e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(out);
		
		e.printStackTrace(ps);

		return out.toString();
	}
}
