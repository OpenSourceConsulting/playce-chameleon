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
 * Sang-cheon Park	2012. 8. 17.		First Draft.
 */
package com.athena.chameleon.common.exception.message;

import java.io.Serializable;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -1911045958279469348L;
    
    private String userMessage;
    private String solution;
    private String messageKey;
    private String reason;

    /**
     * @return the userMessage
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * @param userMessage
     *            the userMessage to set
     */
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    /**
     * @return the solution
     */
    public String getSolution() {
        return solution;
    }

    /**
     * @param solution
     *            the solution to set
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * @return the messageKey
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * @param messageKey
     *            the messageKey to set
     */
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    public Message() {
        userMessage = "";
        solution = "";
        messageKey = "";
        reason = "";
    }

    public Message(String messageKey, String userMessage, String solution, String reason) {
        this.userMessage = "";
        this.solution = "";
        this.messageKey = "";
        this.reason = "";
        this.userMessage = userMessage;
        this.solution = solution;
        this.messageKey = messageKey;
        this.reason = reason;
    }
}
// end of Message.java