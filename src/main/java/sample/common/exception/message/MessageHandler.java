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
package sample.common.exception.message;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class MessageHandler {

    public MessageHandler() {
    }

    public static Message handleExMessage(MessageSource messageSource, String messageKey, Object parameters[]) {
        return handleExMessage(messageSource, messageKey, parameters, null);
    }

    public static Message handleExMessage(MessageSource messageSource, String messageKey, Object parameters[], String defaultMessage) {
        return handleExMessage(messageSource, messageKey, parameters, Locale.getDefault(), defaultMessage);
    }

    public static Message handleExMessage(MessageSource messageSource, String messageKey, Object parameters[], Locale locale, String defaultMessage) {
        String userMessageKey = messageKey;
        String solutionKey = (new StringBuilder()).append(messageKey).append(".solution").toString();
        String reasonKey = (new StringBuilder()).append(messageKey).append(".reason").toString();
        String userMessage = messageSource.getMessage(userMessageKey, parameters, defaultMessage, locale);
        String solution = messageSource.getMessage(solutionKey, null, userMessage, locale);
        String reason = messageSource.getMessage(reasonKey, null, userMessage, locale);
        return new Message(messageKey, userMessage, solution, reason);
    }

    public static Message handleExMessage(String message, Object parameters[]) {
        String userMessage = message;
        if (parameters != null) {
            userMessage = MessageFormat.format(message, parameters);
        }
        return new Message(userMessage, userMessage, userMessage, userMessage);
    }
}
// end of MessageHandler.java