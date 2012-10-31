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
 * Sang-cheon Park	2012. 10. 31.		First Draft.
 */
package com.athena.peacock.engine.action;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.athena.chameleon.common.utils.ZipUtilTest;
import com.athena.peacock.engine.core.Property;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ConfigurationActionTest {
	
	private ConfigurationAction action;
	private String fileName;
	private List<Property> properties;

	/**
	 * <pre>
	 * 테스트 환경 초기화를 위해 env.ori 파일을 env.sh 파일로 저장한다.
	 * </pre>
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File file = new File(new File(ZipUtilTest.class.getResource("/peacock/configuration").getFile()), "env.ori");
		copy(file, new File(file.getParent() + File.separator + "env.sh"));
	}
	
	@Before
	public void setUp() {
		fileName = this.getClass().getResource("/peacock/configuration/env.sh").getFile();
		
		properties = new ArrayList<Property>();
		
		Property property = new Property();
		property.setKey("domain.dir");
		property.setValue("/engn001/jbsadm/jboss51/domains");
		properties.add(property);
		
		property = new Property();
		property.setKey("jboss.dir");
		property.setValue("/opt/jboss-eap-5.1");
		properties.add(property);
		
		property = new Property();
		property.setKey("jboss.home");
		property.setValue("\\$JBOSS_DIR/jboss-as");
		properties.add(property);
		
		property = new Property();
		property.setKey("server.home");
		property.setValue("\\$DOMAIN_DIR/stmsDomain");
		properties.add(property);
		
		property = new Property();
		property.setKey("apps.dir");
		property.setValue("/sorc001/stmsadm/applications/mainWebApp.war");
		properties.add(property);
		
		property = new Property();
		property.setKey("jboss.user");
		property.setValue("root");
		properties.add(property);
		
		property = new Property();
		property.setKey("server.name");
		property.setValue("stms_10_8080");
		properties.add(property);
		
		property = new Property();
		property.setKey("jboss.service.binding.set");
		property.setValue("ports-default");
		properties.add(property);
	}

	@Test
	public void perform() {
		// 초기화
		action = new ConfigurationAction(fileName, properties);
		
		try {
			// 테스트
			action.perform();
			
			// 검증
			String contents = IOUtils.toString(new File(fileName).toURI());
			
			assertTrue("\"${\"로 시작하는 문자열이 없어야 합니다.", contents.indexOf("${") < 0);
			
			for(Property property : properties) {
				assertTrue(property.getValue() + " 문자열이 있어야 합니다.", contents.indexOf(property.getValue().replaceAll("\\\\", "")) > -1);
			}
		} catch (Throwable t) {
			t.printStackTrace();
			fail("Exception이 발생하면 안됩니다.");
		}
	}//end of perform()

	/**
	 * <pre>
	 * source 파일을 target으로 복사한다.
	 * </pre>
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	private static void copy(File sourceFile, File targetFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(targetFile);
		
		IOUtils.copy(inputStream, outputStream);
		IOUtils.closeQuietly(outputStream);
		IOUtils.closeQuietly(inputStream);
	}//end of copy()
}
//end of ConfigurationActionTest.java