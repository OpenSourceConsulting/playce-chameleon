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
package com.athena.peacock.installer.jboss;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.athena.chameleon.engine.entity.provisioning.JBossInstance;
import com.athena.chameleon.engine.entity.provisioning.ProvisionDataSource;
import com.athena.chameleon.engine.entity.provisioning.Provisioning;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JBossProvisioningTest {

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
		provisioning.setTargetWas("B");
		
		JBossInstance instance = new JBossInstance();
		instance.setServerIp("server IP");
		instance.setServerPort("22");
		instance.setSshLoginId("username");
		instance.setSshLoginPassword("password");
		instance.setJbossHome("/home/scpark/provisioning/jboss-as");
		instance.setServerHome("/home/scpark/provisioning/servers");
		instance.setServerName("test");
		instance.setPartitionName("partition");
		instance.setBindAddress("0.0.0.0");
		instance.setBindPort("ports-default");
		provisioning.setJbossInstance(instance);
		
		ProvisionDataSource dataSource = new ProvisionDataSource();
		dataSource.setDatabaseType("mysql");
		dataSource.setJndiName("testDS");
		dataSource.setConnectionUrl("jdbc:mysql:/10.1.2.3:3319/testdb");
		dataSource.setUserName("username");
		dataSource.setPassword("password");
		dataSource.setMinPoolSize("10");
		dataSource.setMaxPoolSize("20");
		provisioning.setDataSource(dataSource);
		
		try {
			new JBossProvisioning().doProvision(provisioning);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
//end of JBossProvisioningTest.java