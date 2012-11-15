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
 * Ji-Woong Choi	2012. 10. 24.		First Draft.
 */
package com.athena.peacock.installer.jboss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.chameleon.engine.entity.provisioning.JBossInstance;
import com.athena.chameleon.engine.entity.provisioning.ProvisionDataSource;
import com.athena.chameleon.engine.entity.provisioning.Provisioning;
import com.athena.chameleon.engine.entity.provisioning.ProvisioningResult;
import com.athena.peacock.engine.action.Action;
import com.athena.peacock.engine.action.ConfigurationAction;
import com.athena.peacock.engine.action.ScpAction;
import com.athena.peacock.engine.action.SshAction;
import com.athena.peacock.engine.core.InstallCommand;
import com.athena.peacock.engine.core.Property;
import com.athena.peacock.engine.core.TargetHost;
import com.athena.peacock.engine.util.SshExecUtil;

/**
 * <pre>
 * Provisioning 화면에서 입력된 정보를 이용하여 JBoss Provisioning을 수행한다.
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class JBossProvisioning {
	
	private static final Logger logger = LoggerFactory.getLogger(JBossProvisioning.class);

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param provisioning
	 * @return
	 * @throws IOException 
	 */
	public ProvisioningResult doProvision(Provisioning provisioning) throws IOException {
		
		logger.debug("[JBoss Provisioning] Input Parameter : [{}]", provisioning);
		
		ProvisioningResult provisioningResult = new ProvisioningResult();
		
		InstallCommand command = new InstallCommand();
		Action action = null;
		
		JBossInstance instance = provisioning.getJbossInstance();
		
		/****************************************************************************
		 * 1. ConfigurationAction을 이용해 env.sh 파일에 포함된 환경변수를 입력된 값으로 치환한다.
		 ****************************************************************************/
		File origEnv = new File(new File(this.getClass().getResource("/provisioning/environment/jboss").getFile()), "env.sh");
		File newEnv = new File(new File(this.getClass().getResource("/provisioning/environment/jboss").getFile()), instance.getServerName() + ".sh");
		
		copy(origEnv, newEnv);

		List<Property> properties = new ArrayList<Property>();
		Property property = null;
		
		property = new Property();
		property.setKey("jboss.home");
		property.setValue(instance.getJbossHome());
		properties.add(property);
		
		property = new Property();
		property.setKey("server.home");
		property.setValue(instance.getServerHome());
		properties.add(property);
		
		property = new Property();
		property.setKey("server.name");
		property.setValue(instance.getServerName());
		properties.add(property);
		
		property = new Property();
		property.setKey("partition.name");
		property.setValue(instance.getPartitionName());
		properties.add(property);
		
		property = new Property();
		property.setKey("bind.address");
		property.setValue(instance.getBindAddress());
		properties.add(property);
		
		property = new Property();
		property.setKey("bind.port");
		property.setValue(instance.getBindPort());
		properties.add(property);
		
		action = new ConfigurationAction(newEnv.getAbsolutePath(), properties);
		command.setAction(action);
		
		
		/****************************************************************************
		 * 2. 선택된 DB 환경에 따른 DataSource 설정 파일로부터 ConfigurationAction을 이용하여
		 *    입력된 값으로 치환한다.
		 ****************************************************************************/
		ProvisionDataSource dataSource = provisioning.getDataSource();
		
		File origDs = null;
		File newDs = null;

		String dbType = dataSource.getDatabaseType();
		if (dbType.equals("mysql")) {
			origDs = new File(new File(this.getClass().getResource("/provisioning/repository").getFile()), "mysql-ds.xml");
		} else if (dbType.equals("oracle")) {
			origDs = new File(new File(this.getClass().getResource("/provisioning/repository").getFile()), "oracle-ds.xml");
		} else {
			origDs = new File(new File(this.getClass().getResource("/provisioning/repository").getFile()), "cubrid-ds.xml");
		}
		newDs = new File(new File(this.getClass().getResource("/provisioning/repository").getFile()), instance.getServerName() + "-ds.xml");
		
		copy(origDs, newDs);
		
		properties = new ArrayList<Property>();
		
		property = new Property();
		property.setKey("jndi.name");
		property.setValue(dataSource.getJndiName());
		properties.add(property);
		
		property = new Property();
		property.setKey("connection.url");
		property.setValue(dataSource.getConnectionUrl());
		properties.add(property);
		
		property = new Property();
		property.setKey("user.name");
		property.setValue(dataSource.getUserName());
		properties.add(property);
		
		property = new Property();
		property.setKey("user.password");
		property.setValue(dataSource.getPassword());
		properties.add(property);
		
		property = new Property();
		property.setKey("pool.min");
		property.setValue(dataSource.getMinPoolSize());
		properties.add(property);
		
		property = new Property();
		property.setKey("pool.max");
		property.setValue(dataSource.getMaxPoolSize());
		properties.add(property);
		
		action = new ConfigurationAction(newDs.getAbsolutePath(), properties);
		command.setAction(action);
		
		provisioningResult.getProcessSequence().add("1. 입력된 변수를 이용하여 환경설정 파일을 작성합니다.");

		
		/****************************************************************************
		 * 3. ScpAction을 이용해 JBoss Template 파일을 Target 서버로 업로드한다. 
		 *    (SSH 로그인 아이디의 홈 디렉토리)
		 ****************************************************************************/
		TargetHost targetHost = new TargetHost();
		targetHost.setHost(instance.getServerIp());
		targetHost.setPort(Integer.parseInt(instance.getServerPort()));
		targetHost.setUsername(instance.getSshLoginId());
		targetHost.setPassword(instance.getSshLoginPassword());
		
		action = new ScpAction(targetHost, this.getClass().getResource("/provisioning/repository/jboss-cluster-template-5.1.2.zip").getFile(), "~/");
		command.setAction(action);
		
		
		/****************************************************************************
		 * 4. ScpAction을 이용해 설정 변경된 env.sh 파일을 Target 서버로 업로드한다. 
		 *    (SSH 로그인 아이디의 홈 디렉토리)
		 ****************************************************************************/
		action = new ScpAction(targetHost, newEnv.getAbsolutePath(), "~/env.sh");
		command.setAction(action);
		
		
		/****************************************************************************
		 * 5. ScpAction을 이용해 설정 변경된 *-ds.xml 파일을 Target 서버로 업로드한다. 
		 *    (SSH 로그인 아이디의 홈 디렉토리)
		 ****************************************************************************/
		action = new ScpAction(targetHost, newDs.getAbsolutePath(), "~/" + newDs.getName());
		command.setAction(action);
		
		provisioningResult.getProcessSequence().add("2. JBoss 템플릿 및 작성된 환경설정 파일을 지정된 서버로 업로드 합니다.");
		
		
		/****************************************************************************
		 * 6. SshAction을 이용해 업로드 된 파일을 지정된 Server Home 디렉토리에 압축 해제한다.
		 *    env.sh 파일을 Server Home 디렉토리 하위의 bin 디렉토리로 복사한다.
		 *    *-ds.xml 파일을 Server Home 디렉토리 하위의 deploy 디렉토리로 복사한다.
		 *    Server Home 디렉토리 하위의 bin 디렉토리에 존재하는 *.sh 파일들의 퍼미션을 755로 변경한다.
		 ****************************************************************************/
		List<String> commandList = new ArrayList<String>();
		commandList.add("unzip -o ~/jboss-cluster-template-5.1.2.zip -d " + instance.getServerHome() + "/" + instance.getServerName());
		commandList.add("cp ~/env.sh " + instance.getServerHome() + "/" + instance.getServerName() + "/bin");
		commandList.add("cp ~/" + newDs.getName() + " " + instance.getServerHome() + "/" + instance.getServerName() + "/deploy");
		commandList.add("chmod 755 " + instance.getServerHome() + "/" + instance.getServerName() + "/bin/*.sh");
		
		action = new SshAction(targetHost, commandList);
		command.setAction(action);

		provisioningResult.getProcessSequence().add("3. [SSH 실행 결과 참조] 업로드 된 JBoss 템플릿을 압축 해제합니다.");
		provisioningResult.getProcessSequence().add("4. [SSH 실행 결과 참조] 업로드 된 환경설정 파일을 압축 해제 디렉토리 하위로 복사합니다.");
		provisioningResult.getProcessSequence().add("5. [SSH 실행 결과 참조] Shell Script 파일에 대한 실행 권한을 추가합니다.");
		
		/************************
		 * 7. InstallCommand 실행
		 ************************/
		command.execute();
		provisioningResult.setSucceed(true);
		provisioningResult.setDataSourceContents(IOUtils.toString(newDs.toURI()));
		provisioningResult.setDataSourceLocation(instance.getServerHome() + "/" + instance.getServerName() + "/deploy/" + newDs.getName());
		provisioningResult.setSshExecuteResult(IOUtils.toString(SshExecUtil.output.toURI()));
		
		return provisioningResult;
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
//end of JBossProvisioning.java