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
 * Sang-cheon Park	2012. 11. 13.		First Draft.
 */
package com.athena.peacock.installer.tomcat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.chameleon.engine.entity.provisioning.Provisioning;
import com.athena.chameleon.engine.entity.provisioning.TomcatInstance;
import com.athena.peacock.engine.action.Action;
import com.athena.peacock.engine.action.ConfigurationAction;
import com.athena.peacock.engine.action.ScpAction;
import com.athena.peacock.engine.action.SshAction;
import com.athena.peacock.engine.core.InstallCommand;
import com.athena.peacock.engine.core.Property;
import com.athena.peacock.engine.core.TargetHost;

/**
 * <pre>
 * Provisioning 화면에서 입력된 정보를 이용하여 Tomcat Provisioning을 수행한다.
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class TomcatProvisioning {

	private static final Logger logger = LoggerFactory.getLogger(TomcatProvisioning.class);
			
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param provisioning
	 * @throws IOException 
	 */
	public void doProvision(Provisioning provisioning) throws IOException {
		
		logger.debug("[Tomcat Provisioning] Input Parameter : [{}]", provisioning);
		
		InstallCommand command = new InstallCommand();
		Action action = null;
		
		TomcatInstance instance = provisioning.getTomcatInstance();
		
		/****************************************************************************
		 * 1. ConfigurationAction을 이용해 env.sh 파일에 포함된 환경변수를 입력된 값으로 치환한다.
		 ****************************************************************************/
		File origFile = new File(new File(this.getClass().getResource("/provisioning/environment/tomcat").getFile()), "env.sh");
		File newFile = new File(new File(this.getClass().getResource("/provisioning/environment/tomcat").getFile()), instance.getServerName() + ".sh");
		
		copy(origFile, newFile);

		List<Property> properties = new ArrayList<Property>();
		Property property = null;
		
		property = new Property();
		property.setKey("java.home");
		property.setValue(instance.getJavaHome());
		properties.add(property);
		
		property = new Property();
		property.setKey("server.name");
		property.setValue(instance.getServerName());
		properties.add(property);
		
		property = new Property();
		property.setKey("catalina.home");
		property.setValue(instance.getCatalinaHome());
		properties.add(property);
		
		property = new Property();
		property.setKey("catalina.base");
		property.setValue(instance.getCatalinaBase());
		properties.add(property);
		
		property = new Property();
		property.setKey("http.port");
		property.setValue(instance.getHttpPort());
		properties.add(property);
		
		property = new Property();
		property.setKey("ajp.port");
		property.setValue(instance.getAjpPort());
		properties.add(property);
		
		property = new Property();
		property.setKey("ssl.port");
		property.setValue(instance.getSslPort());
		properties.add(property);
		
		property = new Property();
		property.setKey("shutdown.port");
		property.setValue(instance.getShutdownPort());
		properties.add(property);
		
		property = new Property();
		property.setKey("user.account");
		property.setValue(instance.getUserAccount());
		properties.add(property);
		
		action = new ConfigurationAction(newFile.getAbsolutePath(), properties);
		command.setAction(action);
		
		
		/****************************************************************************
		 * 2. ScpAction을 이용해 Tomcat Template 파일을 Target 서버로 업로드한다. 
		 *    (SSH 로그인 아이디의 홈 디렉토리)
		 ****************************************************************************/
		TargetHost targetHost = new TargetHost();
		targetHost.setHost(instance.getServerIp());
		targetHost.setPort(Integer.parseInt(instance.getServerPort()));
		targetHost.setUsername(instance.getSshLoginId());
		targetHost.setPassword(instance.getSshLoginPassword());
		
		action = new ScpAction(targetHost, this.getClass().getResource("/provisioning/repository/tomcat-template-6.0.XX.zip").getFile(), "~/");
		command.setAction(action);
		
		
		/****************************************************************************
		 * 3. ScpAction을 이용해 설정 변경된 env.sh 파일을 Target 서버로 업로드한다. 
		 *    (SSH 로그인 아이디의 홈 디렉토리)
		 ****************************************************************************/
		action = new ScpAction(targetHost, newFile.getAbsolutePath(), "~/env.sh");
		command.setAction(action);
		
		
		/****************************************************************************
		 * 4. SshAction을 이용해 업로드 된 파일을 지정된 Server Home(Catalina Base) 디렉토리에 압축 해제한다.
		 *    env.sh 파일을 Server Home 디렉토리로 이동시킨다.
		 *    Server Home 디렉토리에 존재하는 *.sh 파일들의 퍼미션을 755로 변경한다.
		 ****************************************************************************/
		List<String> commandList = new ArrayList<String>();
		commandList.add("unzip -o ~/tomcat-template-6.0.XX.zip -d " + instance.getCatalinaBase());
		commandList.add("cp ~/env.sh " + instance.getCatalinaBase());
		commandList.add("chmod 755 " + instance.getCatalinaBase() + "/*.sh");
		
		action = new SshAction(targetHost, commandList);
		command.setAction(action);
		
		
		/************************
		 * 7. InstallCommand 실행
		 ************************/
		command.execute();

		
	}//end of doProvision()

	/**
	 * <pre>
	 * source 파일을 target으로 복사한다.
	 * </pre>
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	private void copy(File sourceFile, File targetFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(targetFile);
		
		IOUtils.copy(inputStream, outputStream);
		IOUtils.closeQuietly(outputStream);
		IOUtils.closeQuietly(inputStream);
	}//end of copy()

}
//end of TomcatProvisioning.java