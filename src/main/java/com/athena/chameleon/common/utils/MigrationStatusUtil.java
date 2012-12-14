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
 * Sang-cheon Park	2012. 12. 12.		First Draft.
 */
package com.athena.chameleon.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.analyzer.Analyzer;
import com.athena.chameleon.engine.core.analyzer.support.ZipAnalyzer;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;

/**
 * <pre>
 * 마이그레이션 진행 상태 및 진행율을 처리하기 위한 유틸 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class MigrationStatusUtil {

	private static final Logger logger = LoggerFactory.getLogger(MigrationStatusUtil.class);
	
	public static final String STEP1 	= "임시 디렉토리에 압축을 해제합니다.";
	public static final String STEP2 	= "인코딩 변경 작업을 수행중입니다.";
	public static final String STEP3 	= "상용 WAS에 대한 의존성 분석 작업을 수행중입니다.";
	public static final String STEP3_1 	= "ear 내부의 웹 애플리케이션에 대한 분석 작업을 수행중입니다.";
	public static final String STEP3_2 	= "ear 내부의 EJB 애플리케이션에 대한 분석 작업을 수행중입니다.";
	public static final String STEP4 	= "인코딩 변경 파일 및 수정된 디스크립터 파일을 재압축합니다.";
	public static final String STEP5 	= "임시 디렉토리를 삭제합니다.";
	
	private static String currentStatus;
	private static int percent;

	/**
	 * <pre>
	 * 현재 마이그레이션 진행 상태를 스레드로컬에 저장한다.
	 * </pre>
	 * @param status
	 */
	public synchronized static void setCurrentStatus(String status) {
		ThreadLocalUtil.add(ChameleonConstants.MIGRATION_CURRENT_STATUS, status);
		currentStatus = status;
		
		logger.debug("Current Status => [{}]", getCurrentStatus());
	}//end of setCurrentStatus()
	
	/**
	 * <pre>
	 * 현재 마이그레이션 진행 상태를 스레드로컬에서 가져온다.
	 * 반환되는 값이 없으면 업로드 진행중으로 현재 마이그레이션을 시작하지 않은 상태임.
	 * </pre>
	 * @return
	 */
	public static String getCurrentStatus() {
		//return (String)ThreadLocalUtil.get(ChameleonConstants.MIGRATION_CURRENT_STATUS);
		return currentStatus;
	}//end of getCurrentStatus()
	
	/**
	 * <pre>
	 * 현재 마이그레이션 진행율을 스레드로컬에 저장한다.
	 * </pre>
	 * @param percentage
	 */
	public synchronized static void setPercentage(Analyzer analyzer, int percentage) {
		int value = percentage;
		PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
		
		if(analyzer == null) {
			value = percentage;
		} else if(analyzer instanceof ZipAnalyzer) {
			if(metadataDefinition.getDeployFile() != null) {
				value = percentage / 2;
			} 
		} else {
			if(metadataDefinition.getSourceFile() != null) {
				value = 50 + (percentage / 2);
			}
		}
		
		ThreadLocalUtil.add(ChameleonConstants.MIGRATION_PERCENTAGE, value);
		percent = value;
		
		logger.debug("Percentage => [{}]", getPercentage());
	}//end of setPercentage()
	
	/**
	 * <pre>
	 * 현재 마이그레이션 진행율을 스레드로컬에서 가져온다.
	 * </pre>
	 * @return
	 */
	public static int getPercentage() {
		//return (ThreadLocalUtil.get(ChameleonConstants.MIGRATION_PERCENTAGE) == null ? 0 : (Integer)ThreadLocalUtil.get(ChameleonConstants.MIGRATION_PERCENTAGE));
		return percent;
	}//end of getPercentage()
}
//end of MigrationStatusUtil.java