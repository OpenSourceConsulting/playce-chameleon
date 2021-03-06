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
 * Sang-cheon Park	2012. 9. 20.		First Draft.
 */
package com.athena.chameleon.engine.threadpool.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ibm.icu.text.CharsetDetector;

/**
 * <pre>
 * 파일의 인코딩을 변경하기 위한 Runnable Task
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class FileEncodingConvertTask extends BaseTask {
	
	/*
	private static final String[] SEARCH_CHAR_SET = {
		"encoding=\"EUC-KR\"", "encoding=\"euc-kr\"", "Encoding=\"EUC-KR\"", "Encoding=\"euc-kr\"",
		"charset=EUC-KR", "charset=euc-kr", "charset=\"EUC-KR\"", "charset=\"euc-kr\"", 

		"encoding=\"MS949\"", "encoding=\"ms949\"", "Encoding=\"MS949\"", "Encoding=\"ms949\"",
		"charset=MS949", "charset=ms949", "charset=\"MS949\"", "charset=\"ms949\"",
		
		"encoding=\"KSC5601\"", "encoding=\"ksc5601\"", "Encoding=\"KSC5601\"", "Encoding=\"ksc5601\"",
		"charset=KSC5601", "charset=ksc5601", "charset=\"KSC5601\"", "charset=\"ksc5601\"", 

		"encoding=\"UTF-16BE\"", "encoding=\"utf-16be\"", "Encoding=\"UTF-16BE\"", "Encoding=\"utf-16be\"",
		"charset=UTF-16BE", "charset=utf-16be", "charset=\"UTF-16BE\"", "charset=\"utf-16be\"", 

		"encoding=\"UTF-16LE\"", "encoding=\"utf-16le\"", "Encoding=\"UTF-16LE\"", "Encoding=\"utf-16le\"",
		"charset=UTF-16LE", "charset=utf-16le", "charset=\"UTF-16LE\"", "charset=\"utf-16le\"", 
		
		"encoding=\"UTF-16\"", "encoding=\"utf-16\"", "Encoding=\"UTF-16\"", "Encoding=\"utf-16\"",
		"charset=UTF-16", "charset=utf-16", "charset=\"UTF-16\"", "charset=\"utf-16\""
		};
	/*/
	private static String[] SEARCH_CHAR_SET;
	//*/
	// 해당 확장자를 가진 파일 안에는 또 다른 인코딩 설정 정보가 들어가 있을 수 있기 때문...
	private static final String[] TARGET_SUFFIX = {"html", "htm", "jsp", "xml", "js", "css"};
	
	static {
		String[] temp = CharsetDetector.getAllDetectableCharsets();
		temp = (String[])ArrayUtils.add(temp, "MS949");
		temp = (String[])ArrayUtils.add(temp, "KSC5601");
		
		SEARCH_CHAR_SET = CharsetDetector.getAllDetectableCharsets();
		SEARCH_CHAR_SET = (String[])ArrayUtils.add(SEARCH_CHAR_SET, "MS949");
		SEARCH_CHAR_SET = (String[])ArrayUtils.add(SEARCH_CHAR_SET, "KSC5601");

		for(String charSet : temp) {
			SEARCH_CHAR_SET = (String[])ArrayUtils.add(SEARCH_CHAR_SET, charSet.toLowerCase());
		}
	}
	
	private File file;
	private String defaultEncoding = "UTF-8";
	private String extension;
	
	public FileEncodingConvertTask(File file, String defaultEncoding) {
		this(file.getAbsolutePath() + " Convert Task", file, defaultEncoding);
	}
	
	public FileEncodingConvertTask(String taskName, File file, String defaultEncoding) {
		super(taskName);
		setFile(file);
		if(StringUtils.isNotEmpty(defaultEncoding)) {
			setDefaultEncoding(defaultEncoding);
		}
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
		this.file.setWritable(true);
		this.extension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
	}

	/**
	 * @param defaultEncoding the defaultEncoding to set
	 */
	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}
	
	@Override
	protected void taskRun() {
		OutputStreamWriter output = null;
		InputStream input = null;
		
		try {
			input = new FileInputStream(file);
            byte[] data = IOUtils.toByteArray(input, file.length());
            IOUtils.closeQuietly(input);
            
            /*
             * CharsetDector는 다음과 같은 캐릭터셋을 지원한다.
             * 
             * UTF-8, UTF-16BE, UTF-16LE, UTF-32BE, UTF-32LE, Shift_JIS, ISO-2022-JP, 
             * ISO-2022-CN, ISO-2022-KR, GB18030, EUC-JP, EUC-KR, Big5, ISO-8859-1, 
             * ISO-8859-2, ISO-8859-5, ISO-8859-6, ISO-8859-7, ISO-8859-8, windows-1251, 
             * windows-1256, KOI8-R, ISO-8859-9, IBM424_rtl, IBM424_ltr, IBM420_rtl, IBM420_ltr
             */
			// 파일 내용을 defaultEncoding 타입으로 변경 후 문자열로 반환
            // new CharsetDetector().getString(data, defaultEncoding); 시 지원되지 않은 인코딩 타입일 경우
            // 파일이 null로 바뀌는 현상이 발생함.
            //String fileContents = new CharsetDetector().getString(data, defaultEncoding);
            
            String fileContents = null;
            
			try {
				CharsetDetector detector = new CharsetDetector();
				detector.setDeclaredEncoding(defaultEncoding);
				detector.setText(data);
				
				for (com.ibm.icu.text.CharsetMatch m : detector.detectAll()) {
					if (m.getName().toLowerCase().equals("euc-kr")) {
						fileContents = m.getString();
						break;
					}
				}
				
				if (fileContents == null) {
					fileContents = detector.detect().getString();
				}
				
	            //logger.debug("Encoding => {}" + cm.getName());
	            //logger.debug("Contents => {}" + cm.getString());
			} catch (Exception e) {
				// Ignore...
			}
            
            if(fileContents != null) {
    	    	// html, jsp, xml 파일의 내부에 Character Set 관련 문자열이 포함되었을 경우 UTF-8로 변경
    	    	// charset=EUC-KR, encoding="EUC-KR" 등으로 검사하지 않고 EUC-KR, ISO-8859-1 등으로 검사
                // 추후 필요할 경우 SEARCH_CHAR_SET에 설정
                if(ArrayUtils.contains(TARGET_SUFFIX, extension)) {
                	fileContents = replace(fileContents);
    	    	}
                
                output = new OutputStreamWriter(new FileOutputStream(file), defaultEncoding);
                output.write(fileContents);
                IOUtils.closeQuietly(output);
            }
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException has occurred : ", e);
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException has occurred : ", e);
		} catch (IOException e) {
			logger.error("IOException has occurred : ", e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private String replace(String str) {
		for(String charSet : SEARCH_CHAR_SET) {
			str = str.replaceAll(charSet, defaultEncoding);
		}
		
		return str;
	}//end of replace()

}//end of FileEncodingConvertTask.java