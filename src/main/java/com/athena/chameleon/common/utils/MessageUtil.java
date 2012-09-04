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
 * Sang-cheon Park	2012. 9. 4.			First Draft.
 */
package com.athena.chameleon.common.utils;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Message Source에 포함되어 있는 message properties의 내용으로부터 message id에 해당하는 해당 문자열을 반환하기 위한 유틸 클래스.
 * 
 * src/main/resources/message 디렉토리에 메시지 프로퍼티 파일을 작성 후 
 * src/main/resources/spring/context-message.xml 파일을 아래와 같이 수정한다.
 *  
 * &lt;bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
 *     &lt;property name="basenames">
 *         &lt;list>
 *             &lt;value>classpath:message/message-chameleon&lt;/value>
 *         &lt;/list>
 *     &lt;/property>
 *     &lt;property name="defaultEncoding">
 *         &lt;value>UTF-8&lt;/value>
 *     &lt;/property>
 * &lt;/bean>
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component
public class MessageUtil {

    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

	private static MessageSource messageSource;
	
	@Autowired(required = true)
	public MessageUtil(@Qualifier("messageSource") MessageSource messageSource) {
		MessageUtil.messageSource = messageSource;
	}//end of constructor
	
	/**
     * <pre>
     * Spring 모듈 위에서 호출되는 일반적인 경우 생성자를 통해 messageSource가 inject 되지만 
     * 그렇지 않은 경우 messageSource를 application context 로부터 직접 inject 한다.
     * </pre>
     */
	private static void checkMessageSource() {
		if(messageSource == null) {
			logger.debug("messageSource is null. Inject messageSource from application context.");
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-*.xml");
			messageSource = (MessageSource)context.getBean("messageSource");
		}
	}//end of initMessageSource()
	
	public static String getMessage(String messageId) {
		return getMessage(messageId, null, Locale.KOREAN);
	}//end of getMessage()
	
	public static String getMessage(String messageId, String... params) {
		return getMessage(messageId, params, Locale.KOREAN);
	}//end of getMessage()
	
	public static String getMessage(String messageId, Object[] params, Locale locale) {
		checkMessageSource();
		return messageSource.getMessage(messageId, params, locale);
	}//end of getMessage()
	
	public static String getMessage(String messageId, Object[] params, String arg, Locale locale) {
		checkMessageSource();
		return messageSource.getMessage(messageId, params, arg, locale);
	}//end of getMessage()
}//end of MessageUtil.java