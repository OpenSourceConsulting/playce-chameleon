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

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.athena.chameleon.engine.threadpool.handler.ChameleonRejectedExecutionHandler;
import com.athena.chameleon.engine.threadpool.monitor.ChameleonThreadPoolMonitor;
import com.athena.chameleon.engine.threadpool.task.BaseTask;

/**
 * <pre>
 * Runnable Task를 실행시키기 위한 Exceutor
 * Monitoring 및 확장성을 위해 별도로 구현되었으며, Spring ThreadPoolTaskExecutor과 같은 방식으로 동작
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ChameleonThreadPoolExecutor implements InitializingBean {

	private int corePoolSize = 1;
	private int maxPoolSize = Integer.MAX_VALUE;
	private long keepAliveTime = 60;
	private int queueCapacity = Integer.MAX_VALUE;
	private ChameleonRejectedExecutionHandler rejectedExecutionHandler;

    private ThreadPoolExecutor executor;
    private ChameleonThreadPoolMonitor monitor;
	
	/**
	 * @return the corePoolSize
	 */
	public int getCorePoolSize() {
		return corePoolSize;
	}

	/**
	 * @param corePoolSize the corePoolSize to set
	 */
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	/**
	 * @return the maxPoolSize
	 */
	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * @param maxPoolSize the maxPoolSize to set
	 */
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	/**
	 * @return the keepAliveTime
	 */
	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	/**
	 * @param keepAliveTime the keepAliveTime to set
	 */
	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	/**
	 * @return the queueCapacity
	 */
	public int getQueueCapacity() {
		return queueCapacity;
	}

	/**
	 * @param queueCapacity the queueCapacity to set
	 */
	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	/**
	 * @return the rejectedExecutionHandler
	 */
	public ChameleonRejectedExecutionHandler getRejectedExecutionHandler() {
		return rejectedExecutionHandler;
	}

	/**
	 * @param rejectedExecutionHandler the rejectedExecutionHandler to set
	 */
	public void setRejectedExecutionHandler(ChameleonRejectedExecutionHandler rejectedExecutionHandler) {
		this.rejectedExecutionHandler = rejectedExecutionHandler;
	}

	/**
	 * @return the executor
	 */
	public ThreadPoolExecutor getExecutor() {
		return executor;
	}

	/**
	 * @param monitor the monitor to set
	 */
	public void setMonitor(ChameleonThreadPoolMonitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initialize();
	}//end of afterPropertiesSet()
	
	/**
	 * <pre>
	 * executor 초기화
	 * </pre> 
	 */
	public void initialize() {
		executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
				keepAliveTime, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(queueCapacity),
				rejectedExecutionHandler);
		
		monitor.setExecutor(executor);
	}//end of initialize()
    
    /**
     * <pre>
     * 
     * </pre>
     * @param task
     */
    public void execute(Runnable task) {
    	Assert.notNull(task, "task must not be null.");
    	
    	if(!monitor.isAlive()) {
    		monitor.start();
    	}
    	executor.execute(task);
    }//end of execute()
    
    /**
     * <pre>
     * 
     * </pre>
     * @param task
     */
    public void execute(BaseTask task) {
    	Assert.notNull(task, "task must not be null.");

    	if(!monitor.isAlive()) {
    		monitor.start();
    	}
    	executor.execute(task);
    }//end of execute()
    
    /**
     * <pre>
     * 
     * </pre>
     * @param taskList
     */
    public void execute(List<BaseTask> taskList) {
    	Assert.notNull(taskList, "taskList must not be null.");

    	if(!monitor.isAlive()) {
    		monitor.start();
    	}
        for(BaseTask task : taskList) {
        	executor.execute(task);
        }
    }//end of execute()
}//end of ChameleonTaskExecutor.java