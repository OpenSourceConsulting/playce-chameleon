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
package com.athena.chameleon.engine.threadpool.monitor;

/**
 * <pre>
 * ChameleonThreadPoolExecutor 실행시 ChameleonThreadPoolMonitor에 의해 모니터링 되는 항목 정의
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class MonitorDefinition {
	// ThreadPoolExceutor
	private int currentPoolSize;
	private int corePoolSize;
	private int maximumPoolSize;
	private int activeTaskCount;
	private long completedTaskCount;
	private long totalTaskCount;
	private boolean isTerminated;
	
	// MemoryMXBean (bytes 단위)
	private long used;
	private long committed;
	private long max;
	
	//ThreadMXBean
	private int live;
	private int peak;
	private long total;
	
	/**
	 * @return the currentPoolSize
	 */
	public int getCurrentPoolSize() {
		return currentPoolSize;
	}
	/**
	 * @param currentPoolSize the currentPoolSize to set
	 */
	public void setCurrentPoolSize(int currentPoolSize) {
		this.currentPoolSize = currentPoolSize;
	}
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
	 * @return the maximumPoolSize
	 */
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}
	/**
	 * @param maximumPoolSize the maximumPoolSize to set
	 */
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}
	/**
	 * @return the activeTaskCount
	 */
	public int getActiveTaskCount() {
		return activeTaskCount;
	}
	/**
	 * @param activeTaskCount the activeTaskCount to set
	 */
	public void setActiveTaskCount(int activeTaskCount) {
		this.activeTaskCount = activeTaskCount;
	}
	/**
	 * @return the completedTaskCount
	 */
	public long getCompletedTaskCount() {
		return completedTaskCount;
	}
	/**
	 * @param completedTaskCount the completedTaskCount to set
	 */
	public void setCompletedTaskCount(long completedTaskCount) {
		this.completedTaskCount = completedTaskCount;
	}
	/**
	 * @return the totalTaskCount
	 */
	public long getTotalTaskCount() {
		return totalTaskCount;
	}
	/**
	 * @param totalTaskCount the totalTaskCount to set
	 */
	public void setTotalTaskCount(long totalTaskCount) {
		this.totalTaskCount = totalTaskCount;
	}
	/**
	 * @return the isTerminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}
	/**
	 * @param isTerminated the isTerminated to set
	 */
	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
	/**
	 * @return the used
	 */
	public long getUsed() {
		return used;
	}
	/**
	 * @param used the used to set
	 */
	public void setUsed(long used) {
		this.used = used;
	}
	/**
	 * @return the committed
	 */
	public long getCommitted() {
		return committed;
	}
	/**
	 * @param committed the committed to set
	 */
	public void setCommitted(long committed) {
		this.committed = committed;
	}
	/**
	 * @return the max
	 */
	public long getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(long max) {
		this.max = max;
	}
	/**
	 * @return the live
	 */
	public int getLive() {
		return live;
	}
	/**
	 * @param live the live to set
	 */
	public void setLive(int live) {
		this.live = live;
	}
	/**
	 * @return the peak
	 */
	public int getPeak() {
		return peak;
	}
	/**
	 * @param peak the peak to set
	 */
	public void setPeak(int peak) {
		this.peak = peak;
	}
	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}
	
	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder("\n");
		sb.append("[ThreadPoolExecutor] CurrentPoolSize : ").append(getCurrentPoolSize())
		  .append(", CorePoolSize : ").append(getCorePoolSize())
		  .append(", MaximumPoolSize : ").append(getMaximumPoolSize())
		  .append(", ActiveTaskCount : ").append(getActiveTaskCount())
		  .append(", CompletedTaskCount : ").append(getCompletedTaskCount())
		  .append(", TotalTaskCount : ").append(getTotalTaskCount())
		  .append(", isTerminated : ").append(isTerminated()).append("\n")
		  .append("[MemoryMXBean] Used : ").append(getUsed())
		  .append(", Committed : ").append(getCommitted())
		  .append(", Max : ").append(getMax()).append("\n")
		  .append("[ThreadMXBean] Live : ").append(getLive())
		  .append(", Peak : ").append(getPeak())
		  .append(", Total : ").append(getTotal());
		
		return sb.toString();
    }
}//end of MonitorDefinition.java