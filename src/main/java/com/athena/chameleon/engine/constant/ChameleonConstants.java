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
 * Sang-cheon Park	2012. 9. 25.		First Draft.
 */
package com.athena.chameleon.engine.constant;

/**
 * <pre>
 * Athena-Chameleon에서 범용으로 사용되는 상수 등을 정의하기 위한 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ChameleonConstants {

	/**
	 * ThreadLocal을 사용한 스레드간 공유 객체에 사용되는 Key
	 */
	public static final String PDF_METADATA_DEFINITION 	= "PDF_METADATA_DEFINITION";
	
	public static final String DELETE_FILE_LIST 		= "DELETE_FILE_LIST";
	public static final String DEPENDENCY_FILE_LIST 	= "DEPENDENCY_FILE_LIST";
	
	public static final String WAR_FILE_LIST 			= "WAR_FILE_LIST";
	public static final String JAR_FILE_LIST 			= "JAR_FILE_LIST";
	public static final String LIB_FILE_LIST 			= "LIB_FILE_LIST";

	public static final String PROJECT_NAME 			= "PROJECT_NAME";
	
	public static final String TEMP_ROOT_DIR			= "TEMP_ROOT_DIR";
	
}//end of ChameleonConstants.java