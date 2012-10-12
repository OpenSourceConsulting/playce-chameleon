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
package com.athena.chameleon.engine.threadpool.executor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.athena.chameleon.engine.threadpool.task.BaseTask;

/**
 * <pre>
 * ChameleonTaskExecutor를 위한 JUnit Test 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ChameleonThreadPoolExecutorTest {
	
	private ChameleonThreadPoolExecutor executor;
	
	private static final int CNT = 5;
	private int corePoolSize = 1;
	private int maxPoolSize = 1;
	private int queueCapacity = 2;

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new FileSystemXmlApplicationContext("file:./src/main/resources/spring/context-*.xml");
    	executor = (ChameleonThreadPoolExecutor)context.getBean("taskExecutor");
    	
    	executor.setCorePoolSize(corePoolSize);
    	executor.setMaxPoolSize(maxPoolSize);
    	executor.setQueueCapacity(queueCapacity);
    	executor.initialize();
	}

	@Test
	public void test1() {
		try {
			List<BaseTask> taskList = new ArrayList<BaseTask>();
			
			for(int i = 1; i <= CNT; i++) {
				taskList.add(new MockTask("Task"+i));
			}
			executor.execute(taskList);

			executor.getExecutor().shutdown();
			
			while(!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}
		} catch (Exception e) {
			fail("test failed.");
		}
	}

	@Test
	public void test2() {
    	try {
			for(int i = 1; i <= CNT; i++) {
				executor.execute(new MockTask("Task"+i));
			}

			executor.getExecutor().shutdown();
			
			while(!executor.getExecutor().isTerminated()) {
				Thread.sleep(100);
			}
		} catch (Exception e) {
			fail("test failed.");
		}
	}

    public static void main(String[] args) {
		ChameleonThreadPoolExecutorTest test = new ChameleonThreadPoolExecutorTest();
		
    	try {
			test.setUp();

			List<BaseTask> taskList = new ArrayList<BaseTask>();
			for(int i = 1; i <= CNT; i++) {
				taskList.add(new MockTask("Task"+i));
			}
			test.executor.execute(taskList);

			test.executor.getExecutor().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}//end of ChameleonTaskExecutorTest.java

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
class MockTask extends BaseTask {
	
	public MockTask() {
	}

	public MockTask(String taskName) {
		super(taskName);
	}

	@Override
	protected void taskRun() {
		try {
			Thread.sleep(2000);
		} catch (Throwable e) {
			logger.error("[{}] is not completed!", this.taskName);
			
			if(e instanceof InterruptedException) {
				logger.error("InterruptedException has occurred.", e);
			} else {
				// exception propagate to handle at ChameleonTaskExceptionHandler
				throw new RuntimeException(e);
			}
		}		
	}
}//end of MockTask.java