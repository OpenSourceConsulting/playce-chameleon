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
package sample.common.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

import sample.common.exception.message.DetailMessageSource;
import sample.common.exception.message.Message;
import sample.common.exception.message.MessageHandler;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class BaseException extends Exception implements DetailMessageSource {
    
    private static final long serialVersionUID = 882404186064009890L;
    
    protected String messageKey;
    protected Object messageParameters[];
    protected Message message;
    protected Locale locale;

    /**
     * @return the messageKey
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * @param messageKey the messageKey to set
     */
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    /**
     * @return the messageParameters
     */
    public Object[] getMessageParameters() {
        return messageParameters;
    }

    /**
     * @param messageParameters the messageParameters to set
     */
    public void setMessageParameters(Object[] messageParameters) {
        this.messageParameters = messageParameters;
    }

    /**
     * @return the message
     */
    public Message getMessages() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public BaseException() {
        this("BaseRTException without message", ((Object[]) (null)), ((Throwable) (null)));
    }

    public BaseException(String message) {
        this(message, ((Object[]) (null)), ((Throwable) (null)));
    }

    public BaseException(String message, Throwable exception) {
        this(message, ((Object[]) (null)), exception);
    }

    public BaseException(String message, Object messageParameters[]) {
        this(message, messageParameters, ((Throwable) (null)));
    }

    public BaseException(MessageSource messageSource, String messageKey, Object messageParameters[], Throwable wrappedException) {
        this(messageSource, messageKey, messageParameters, Locale.getDefault(), null, wrappedException);
    }

    public BaseException(MessageSource messageSource, String messageKey, Object messageParameters[], Locale locale, Throwable wrappedException) {
        this(messageSource, messageKey, messageParameters, locale, null, wrappedException);
    }

    public BaseException(MessageSource messageSource, String messageKey, Throwable wrappedException) {
        this(messageSource, messageKey, null, Locale.getDefault(), null, wrappedException);
    }

    public BaseException(MessageSource messageSource, String messageKey, Locale locale, Throwable wrappedException) {
        this(messageSource, messageKey, null, locale, null, wrappedException);
    }

    public BaseException(MessageSource messageSource, String messageKey, String defaultMessage, Throwable wrappedException) {
        this(messageSource, messageKey, null, Locale.getDefault(), defaultMessage, wrappedException);
    }

    public BaseException(MessageSource messageSource, String messageKey, Locale locale, String defaultMessage, Throwable wrappedException) {
        this(messageSource, messageKey, null, locale, defaultMessage, wrappedException);
    }

    public BaseException(MessageSource messageSource, String messageKey, Object messageParameters[]) {
        this(messageSource, messageKey, messageParameters, Locale.getDefault(), null, null);
    }

    public BaseException(MessageSource messageSource, String messageKey, Object messageParameters[], Locale locale) {
        this(messageSource, messageKey, messageParameters, locale, null, null);
    }

    public BaseException(MessageSource messageSource, String messageKey, Object messageParameters[], String defaultMessage) {
        this(messageSource, messageKey, messageParameters, Locale.getDefault(), defaultMessage, null);
    }

    public BaseException(MessageSource messageSource, String messageKey, Object messageParameters[], Locale locale, String defaultMessage) {
        this(messageSource, messageKey, messageParameters, locale, defaultMessage, null);
    }

    public BaseException(MessageSource messageSource, String messageKey) {
        this(messageSource, messageKey, null, Locale.getDefault(), null, null);
    }

    public BaseException(MessageSource messageSource, String messageKey, Locale locale) {
        this(messageSource, messageKey, null, locale, null, null);
    }

    public BaseException(MessageSource messageSource, String messageKey, String defaultMessage) {
        this(messageSource, messageKey, null, Locale.getDefault(), defaultMessage, null);
    }

    public BaseException(MessageSource messageSource, String messageKey, Locale locale, String defaultMessage) {
        this(messageSource, messageKey, null, locale, defaultMessage, null);
    }
    
    public BaseException(MessageSource messageSource, String messageKey, Object messageParameters[], String defaultMessage, Throwable wrappedException) {
        super(wrappedException);
        message = new Message();
        locale = Locale.getDefault();
        this.messageKey = messageKey;
        this.messageParameters = messageParameters;
        message = MessageHandler.handleExMessage(messageSource, messageKey, messageParameters, defaultMessage);
    }

    public BaseException(MessageSource messageSource, String messageKey, Object messageParameters[], Locale locale, String defaultMessage, Throwable wrappedException) {
        super(wrappedException);
        message = new Message();
        this.locale = Locale.getDefault();
        this.messageKey = messageKey;
        this.messageParameters = messageParameters;
        this.locale = locale;
        message = MessageHandler.handleExMessage(messageSource, messageKey, messageParameters, locale, defaultMessage);
    }

    public BaseException(String message, Object messageParameters[], Throwable wrappedException) {
        super(wrappedException);
        this.message = new Message();
        locale = Locale.getDefault();
        this.messageParameters = messageParameters;
        this.message = MessageHandler.handleExMessage(message, messageParameters);
    }
}
// end of BaseException.java