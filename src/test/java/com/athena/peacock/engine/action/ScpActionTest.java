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
 * Sang-cheon Park	2012. 11. 1.		First Draft.
 */
package com.athena.peacock.engine.action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.athena.peacock.engine.core.TargetHost;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ScpActionTest {
	
	private ScpAction action;
	private TargetHost targetHost;
	private String source;
	private String target;

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		targetHost = new TargetHost();
		
		targetHost.setHost("");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
//end of ScpActionTest.java