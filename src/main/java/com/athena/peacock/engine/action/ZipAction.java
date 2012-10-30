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

import org.apache.tools.ant.taskdefs.ManifestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.engine.util.ZipUtil;

/**
 * <pre>
 * 지정된 경로에 대한 압축을 수행하기 위한 Action 클래스
 * </pre>
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
public class ZipAction implements Action {
	
	private static final Logger logger = LoggerFactory.getLogger(ZipAction.class);
	
	// 압축 대상 디렉토리
	private String baseDir;
	// 압축 결과 파일
	private String destFile;
	
	public ZipAction(String baseDir) {
		this(baseDir, null);
	}
	
	public ZipAction(String baseDir, String destFile) {
		this.baseDir = baseDir;
		this.destFile = destFile;
	}

    /* (non-Javadoc)
     * @see com.athena.peacock.engine.action.Action#perform()
     */
    @Override
    public void perform() {
    	logger.debug("Before compress [{}] directory to [{}]", baseDir, destFile);
    	
    	try {
			destFile = ZipUtil.compress(baseDir, destFile);
			logger.debug("[{}] Compress has done successfully.", destFile);
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		} catch (ManifestException e) {
			logger.error("ManifestException has occurred.", e);
		}
    }//end of perform()
    
}
//end of ZipAction.java