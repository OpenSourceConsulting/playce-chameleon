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
 * Sang-cheon Park	2012. 10. 3.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.parser;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public abstract class Parser {
	
	protected static final Logger logger = LoggerFactory.getLogger(Parser.class);
	
	protected AnalyzeDefinition analyzeDefinition;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param file
	 * @param analyzeDefinition
	 * @return
	 */
	public abstract Object parse(File file, AnalyzeDefinition analyzeDefinition);
	
	/**
	 * <pre>
	 * sourceFile을 targetFile로 복사한다.
	 * web.xml 파일에 encoding 관련 filter 및 filter-mapping이 추가된 경우 /WEB-INF/lib 디렉토리에 osc-filters.jar 파일 복사 등의 경우에 사용
	 * </pre>
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException 
	 */
	protected void fileCopy(File sourceFile, File targetFile) throws IOException {
		if(!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		
		FileInputStream inputStream = new FileInputStream(sourceFile);
		FileOutputStream outputStream = new FileOutputStream(targetFile);
		
		IOUtils.copy(inputStream, outputStream);
		IOUtils.closeQuietly(outputStream);
		IOUtils.closeQuietly(inputStream);
	}//end of fileCopy
	
	/**
	 * <pre>
	 * XML 관련 내용을 파일에 저장한다.
	 * </pre>
	 * @param file
	 * @param xmlData
	 * @throws IOException 
	 */
	protected void rewrite(File file, String xmlData) throws IOException {
		OutputStreamWriter output =  new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        output.write(xmlData);
        IOUtils.closeQuietly(output);
	}//end of rewrite()
	
    /**
     * <pre>
     * 파일의 내용을 문자열로 변환하여 반환한다.
     * </pre>
     * @param file
     * @return
     * @throws IOException 
     */
    protected String fileToString(String file) throws IOException {
    	
    	// return IOUtils.toString(file.toURI());
    	
        String result = null;

        DataInputStream in = null;
        File f = new File(file);
        byte[] buffer = new byte[(int) f.length()];
        in = new DataInputStream(new FileInputStream(f));
        in.readFully(buffer);
        result = new String(buffer);
        IOUtils.closeQuietly(in);
        
        return result;
    }//end of fileToString()
    
    /**
     * <pre>
     * 절대경로 상에서 압축해제를 위한 임시 디렉토리까지의 경로를 삭제한 상대 경로를 구한다.
     * </pre>
     * @param fullPath
     * @param key
     * @return
     */
    protected String removeTempDir(String fullPath, String key) {
    	String tempPath = (String)ThreadLocalUtil.get(key);
    	String earPath = (String)ThreadLocalUtil.get(ChameleonConstants.DEPLOY_DIR_IN_EAR);
    	
    	if(earPath == null) {
    		earPath = "";
    	} else if(!earPath.endsWith(File.separator)) {
    		earPath += File.separator;
    	}
    	
    	if(StringUtils.isEmpty(tempPath)) {
    		return earPath + fullPath;
    	} else {
    		return earPath + fullPath.substring(tempPath.length() + 1);
    	}
    }//end of removeTempDir()
    
    /**
     * <pre>
     * <!DOCTYPE >에 명시된 dtd가 존재할 경우 해당 DTD를 참조하려 하기 때문에
     * 인터넷에 연결되지 않은 상태에서는 UnmarshalException이 발생한다.
     * 따라서 file 내에 <!DOCTYPE >이 존재할 경우 해당 구문을 제거하고 재 저장한다.
     * </pre>
     * @param file
     */
    protected void removeDoctype(File file) {
    	try {
			String xmlData = fileToString(file.getAbsolutePath());
			
			int idx1 = xmlData.indexOf("<!DOCTYPE");
			int idx2 = xmlData.indexOf(">", idx1);
			
			rewrite(file, xmlData.substring(0, idx1) + xmlData.substring(idx2 + 1));
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		}
    }//end of removeDoctype()
}//end of Parser.java