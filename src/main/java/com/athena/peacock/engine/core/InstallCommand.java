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
package com.athena.peacock.engine.core;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.engine.action.Action;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class InstallCommand implements Command {
	
	private static final Logger logger = LoggerFactory.getLogger(InstallCommand.class);

    private List<Action> actions = new ArrayList<Action>();
    
    /**
     * <pre>
     * 
     * </pre>
     *
     */
    public InstallCommand() {
        // TODO Auto-generated constructor stub
    }

    public InstallCommand(Action action) {
        actions.add(action);
    }
    

    public void setAction(Action action) {
        actions.add(action);
    }

    /* (non-Javadoc)
     * @see com.athena.peacock.engine.core.Command#execute()
     */
    @Override
    public boolean execute() {
        for (Action action : actions) {
        	logger.debug("[{}] will be start.", action.getClass().getCanonicalName());
        	
            action.perform();
            
        	logger.debug("[{}] has done.", action.getClass().getCanonicalName());
        }
        
        return true;
    }//end of execute()

}
//end of InstallCommand.java