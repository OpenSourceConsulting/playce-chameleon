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
 * Sang-cheon Park	2012. 11. 15.		First Draft.
 */
package com.athena.chameleon.engine.entity.provisioning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <pre>
 * 프로비저닝 처리에 대한 실행 내용 및 결과 등이 저장되는 Data Object
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ProvisioningResult implements Serializable {
	
	private static final long serialVersionUID = -5443358106843343973L;
	
	/** 프로비저닝 성공 여부 */
	private boolean isSucceed;
	/** 프로비저닝 처리 순서에 따른 내용 목록 */
	private List<String> processSequence;
	/** 타깃 서버에서 실행한 SSH Command 내용 및 결과  */
	private String sshExecuteResult;
	/** 변경된 Data Source 내용(JBoss만 해당) */
	private String dataSourceContents;
	/** 변경된 Data Source 설정 파일이 업로드 된 서버 경로(JBoss만 해당) */
	private String dataSourceLocation;
	
	/**
	 * @return the isSucceed
	 */
	public boolean isSucceed() {
		return isSucceed;
	}
	/**
	 * @param isSucceed the isSucceed to set
	 */
	public void setSucceed(boolean isSucceed) {
		this.isSucceed = isSucceed;
	}
	/**
	 * @return the processSequence
	 */
	public List<String> getProcessSequence() {
		if (processSequence == null) {
			processSequence = new ArrayList<String>();
		}
		return processSequence;
	}
	/**
	 * @param processSequence the processSequence to set
	 */
	public void setProcessSequence(List<String> processSequence) {
		this.processSequence = processSequence;
	}
	/**
	 * @return the sshExecuteResult
	 */
	public String getSshExecuteResult() {
		return sshExecuteResult;
	}
	/**
	 * @param sshExecuteResult the sshExecuteResult to set
	 */
	public void setSshExecuteResult(String sshExecuteResult) {
		this.sshExecuteResult = sshExecuteResult;
	}
	/**
	 * @return the dataSourceContents
	 */
	public String getDataSourceContents() {
		return dataSourceContents;
	}
	/**
	 * @param dataSourceContents the dataSourceContents to set
	 */
	public void setDataSourceContents(String dataSourceContents) {
		this.dataSourceContents = dataSourceContents;
	}
	/**
	 * @return the dataSourceLocation
	 */
	public String getDataSourceLocation() {
		return dataSourceLocation;
	}
	/**
	 * @param dataSourceLocation the dataSourceLocation to set
	 */
	public void setDataSourceLocation(String dataSourceLocation) {
		this.dataSourceLocation = dataSourceLocation;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
//end of ProvisioningResult.java