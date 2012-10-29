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


/**
 * 하위의 Action 리스트를 가진 Command를 Multi로 수행할 경우 사용되는 클래스이다.
 * 일반적으로 XXXCommand에서 모두 처리하지만 Uninstall후 다시 Install 등이 필요할 경우 사용될 수 있는 실행환경을 제공한다. 
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class CommandExecutor {
    private List<Command> commands = new ArrayList<Command>();
    
    /**
     * Adds command object to command list
     *
     * @param command
     */
    public void setCommand(Command command) {
        commands.add(command);
    }

    /**
     * Executes command 
     *
     */
    public void executeCommands() {
        for( Command command : commands) {
            command.execute();
        }
    }
}
//end of CommandExecutor.java