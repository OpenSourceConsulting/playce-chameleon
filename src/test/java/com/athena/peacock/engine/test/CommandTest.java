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
package com.athena.peacock.engine.test;

import org.junit.Before;
import org.junit.Test;

import com.athena.peacock.engine.action.Action;
import com.athena.peacock.engine.action.ConfigurationAction;
import com.athena.peacock.engine.action.SshAction;
import com.athena.peacock.engine.action.ZipAction;
import com.athena.peacock.engine.core.Command;
import com.athena.peacock.engine.core.CommandExecutor;
import com.athena.peacock.engine.core.InstallCommand;
import com.athena.peacock.installer.jboss.JBossProvisioning;
import com.athena.peacock.installer.jdk.JDKProvisioning;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class CommandTest {
    
    @Before
    public void setUp() throws Exception {
        
    }
    
    @Test
    public void commandExecutorTest() {
        Command command = new InstallCommand();
        CommandExecutor executor = new CommandExecutor();
        executor.setCommand(command);
        executor.executeCommands();
    }
    
    @Test
    public void jdkInstall() {
        Command command = new InstallCommand();
        SshAction ssh = new SshAction();
        command.setAction(ssh);
        
        ConfigurationAction config = new ConfigurationAction();
        command.setAction(config);
        
        ZipAction zip = new ZipAction();
        command.setAction(zip);
        
        command.execute();
    }
    
    @Test
    public void jbossInstall() {
       
    }
}
//end of CommandTest.java