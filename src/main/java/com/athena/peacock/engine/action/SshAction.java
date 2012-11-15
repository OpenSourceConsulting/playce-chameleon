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
package com.athena.peacock.engine.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.engine.core.TargetHost;
import com.athena.peacock.engine.util.SshExecUtil;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class SshAction implements Action {
	
	private static final Logger logger = LoggerFactory.getLogger(SshAction.class);
	
	private TargetHost targetHost;
    private List<String> commandList;

    public SshAction(TargetHost targetHost, List<String> commandList) {
    	this.targetHost = targetHost;
        this.commandList = commandList;
    }
    
    /* (non-Javadoc)
     * @see com.athena.peacock.engine.action.Action#perform()
     */
    @Override
    public void perform() {
    	logger.debug("\n- Target Host Info : [{}]", targetHost.toString());
    	
        try {
			SshExecUtil.executeCommand(targetHost, commandList);
			logger.debug("Execute Command(s) Result : \n{}", IOUtils.toString(SshExecUtil.output.toURI()));
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		}
    }//end of perform()

}
//end of SshAction.java