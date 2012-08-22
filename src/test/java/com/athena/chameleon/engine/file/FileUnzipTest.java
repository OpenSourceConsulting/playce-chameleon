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
 * Author           Date                Description
 * ---------------  ----------------    ------------
 * Hyo-jeong Lee    2012. 8. 22.        First Draft.
 */
package com.athena.chameleon.engine.file;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.athena.chameleon.common.utils.PropertyUtil;
import com.athena.chameleon.engine.utils.ZipUtil;

import com.ibm.icu.text.CharsetDetector;

/**
 * This FileUnzipTest class is a Test Case class for FileUnzip.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/context-*.xml" })
public class FileUnzipTest {

    @Test
    public void unzipTest() throws Exception  {
        String zipFilePath = "C:/test/test.zip";
        String tmpfileDir = PropertyUtil.getProperty("unzip.dir.path") + File.separator + System.currentTimeMillis();
        String unzipPath = ZipUtil.extract(zipFilePath, tmpfileDir);
        
        File unzipDir = new File(unzipPath);
        fileAsset(unzipDir);
        fileRead(unzipDir);
    }
    
    public void fileAsset(File file) throws Exception {
        if(file.isDirectory()) {
            for (int i=0;i < file.listFiles().length;i++) {
                File f = file.listFiles()[i];
                if(f.isDirectory()) {
                    fileAsset(f);
                } else {
                    //File unzip TestCase
                    assertNotNull("["+f.getAbsolutePath() + "] file null error", f);
                    
                    String changeTarget = PropertyUtil.getProperty("unzip.change.target");
                    String fileName = f.getName();
                    
                    //UTF-8 Encoding Test Case
                    if(changeTarget.indexOf(fileName.substring(fileName.lastIndexOf(".")+1, fileName.length())) > -1) {
                        CharsetDetector detector = new CharsetDetector();
                        detector.setText(FileUtils.readFileToByteArray(f));
                        
                        //ISO-8859의 경우 예외처리
                        assertTrue("["+f.getAbsolutePath() + "] file encoding error : " + detector.detect().getName(),
                                (detector.detect().getName().equals(PropertyUtil.getProperty("unzip.default.encoding"))
                                        || detector.detect().getName().indexOf(PropertyUtil.getProperty("unzip.en.encoding")) > -1));
                    }
                 
                }
            }
        }
    }

    public void fileRead(File file) throws Exception {
        if(file.isDirectory()) {
            for (int i=0;i < file.listFiles().length;i++) {
                File f = file.listFiles()[i];
                if(f.isDirectory()) {
                    fileRead(f);
                } else {
                    
                    String changeTarget = PropertyUtil.getProperty("unzip.change.target");
                    String fileName = f.getName();
                    
                    //문서 라인 추출
                    if(changeTarget.indexOf(fileName.substring(fileName.lastIndexOf(".")+1, fileName.length())) > -1) {
                    
                        try {
                            FileReader reader = new FileReader(f);
                            BufferedReader buffer = new BufferedReader(reader);
                            
                            String lineStr = null;
                            int line = 0;
                            while((lineStr = buffer.readLine()) != null) {
                                
                                line++;
                                
                                if(lineStr.indexOf("test") > -1) {
                                    System.out.println("["+f.getAbsolutePath()+"] " + line + " line : " + lineStr);
                                }
                            }
                            
                        } catch(Exception e) {
                            fail("File Read error");
                        }
                        
                    }
                 
                }
            }
        }
    }
}
//end of FileUnzipTest.java