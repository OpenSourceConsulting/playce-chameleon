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
 * Ji-Woong Choi	2012. 10. 24.		First Draft.
 */
package com.athena.peacock.engine.action;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.engine.util.ZipUtil;

/**
 * <pre>
 * 지정된 파일에 대한 압축해제를 위한 Action 클래스
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class UnzipAction implements Action {
	
	private static final Logger logger = LoggerFactory.getLogger(UnzipAction.class);
	
	// 압축해제 대상 파일
	private String sourceFile;
	// 압축해제 결과 디렉토리
	private String destDir;
	
	public UnzipAction(String sourceFile) {
		this(sourceFile, null);
	}
	
	public UnzipAction(String sourceFile, String destDir) {
		this.sourceFile = sourceFile;
		this.destDir = destDir;
	}

    /* (non-Javadoc)
     * @see com.athena.peacock.engine.action.Action#perform()
     */
    @Override
    public void perform() {
    	logger.debug("Before decompress [{}] file to [{}]", sourceFile, destDir);
    	
    	try {
    		destDir = ZipUtil.decompress(sourceFile, destDir);
			logger.debug("[{}] Decompress has done successfully.", destDir);
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		}
    }//end of perform()

}
//end of UnzipAction.java