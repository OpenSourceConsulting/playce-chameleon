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
 * Sang-cheon Park	2012. 9. 11.		First Draft.
 */
package com.athena.chameleon.engine.threadpool.handler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * queueCapacity 용량 부족으로 처리되지 못하는 task가 존재할 경우 처리(로깅)하기 위한 핸들러
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ChameleonRejectedExecutionHandler implements RejectedExecutionHandler {
	
    private static final Logger logger = LoggerFactory.getLogger(ChameleonRejectedExecutionHandler.class);

	@Override
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
		logger.info("[{}] has been rejected.", runnable.toString());
		
		// TODO task의 실행이 거부되었을 경우 처리해야할 로직이 남아 있다면 여기에 추가.
		
	}//end of rejectedExecution()

}//end of ChameleonRejectedExecutionHandler.java