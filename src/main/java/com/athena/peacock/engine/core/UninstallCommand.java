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

import com.athena.peacock.engine.action.Action;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class UninstallCommand implements Command {

    private List<Action> actions = new ArrayList<Action>();
    
    public UninstallCommand(Action action) {
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
        // TODO Auto-generated method stub
        for( Action action : actions) {
            action.perform();
        }
        return true;
    }


}
//end of InstallCommand.java