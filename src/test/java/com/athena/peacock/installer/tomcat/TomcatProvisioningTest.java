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
 * Sang-cheon Park	2012. 11. 14.		First Draft.
 */
package com.athena.peacock.installer.tomcat;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.athena.chameleon.engine.entity.provisioning.Provisioning;
import com.athena.chameleon.engine.entity.provisioning.TomcatInstance;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class TomcatProvisioningTest {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Provisioning provisioning = new Provisioning();
		provisioning.setTargetWas("T");
		
		TomcatInstance instance = new TomcatInstance();
		instance.setServerIp("server IP");
		instance.setServerPort("22");
		instance.setSshLoginId("username");
		instance.setSshLoginPassword("password");
		instance.setJavaHome("/home/scpark/provisioning/java");
		instance.setServerName("test2");
		instance.setCatalinaHome("/home/scpark/provisioning/tomcat");
		instance.setCatalinaBase("/home/scpark/provisioning/servers/test2");
		instance.setHttpPort("8080");
		instance.setAjpPort("8009");
		instance.setSslPort("8443");
		instance.setShutdownPort("8005");
		instance.setUserAccount("scpark");
		provisioning.setTomcatInstance(instance);
		
		try {
			new TomcatProvisioning().doProvision(provisioning);
		} catch (IOException e) {
			e.printStackTrace();   
			fail("Not yet implemented");
		}
	}

}
//end of TomcatProvisioningTest.java