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
package com.athena.chameleon.engine.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Enumeration;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.athena.chameleon.common.utils.PropertyUtil;
import com.ibm.icu.text.CharsetDetector;

/**
 * 압축된 파일의 인코딩을 변경하여 압축해제 하기 위한 유틸 클래스
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

public class FileUtil {

    private static final Log logger = LogFactory.getLog(FileUtil.class);
    
    /**
     * 
     * 입력된 압축파일을 압축 해제한다.
     *
     * @param zipFilePath 압축파일 경로
     * @param unzipDirPath 압축을 해제할 디렉토리 경로
     * @return String 압축 해제된 디렉토리 경로
     * @throws Exception
     */
    public static String extract(String zipFilePath, String unzipDirPath) throws Exception {
        return extract(zipFilePath, unzipDirPath, PropertyUtil.getProperty("unzip.default.encoding"));
    }

    /**
     * 
     * 입력된 압축파일을 압축 해제한다.
     *
     * @param zipFilePath 압축파일 경로
     * @param unzipDirPath 압축을 해제할 디렉토리 경로
     * @param declaredEncoding 인코딩할 유니코드 
     * @return String 압축 해제된 디렉토리 경로
     * @throws IOException
     */
    public static String extract(String zipFilePath, String unzipDirPath, String declaredEncoding) throws IOException {
        ZipFile zipFile = null;

        try {
            if (logger.isDebugEnabled()) {
                logger.debug("[ZipUtil] zipFilePath :" + zipFilePath);
            }

            String unZipDir = (unzipDirPath == null) ? zipFilePath.substring(0, zipFilePath.lastIndexOf('.')) + File.separator
                    : unzipDirPath + File.separator;
            if (logger.isDebugEnabled()) {
                logger.debug("[ZipUtil] unzipDirPath :" + unZipDir);
            }

            zipFile = new ZipFile(zipFilePath, "EUC-KR");

            Enumeration<?> e = zipFile.getEntries();
            for (; e.hasMoreElements();) {
                FileOutputStream output = null;
                InputStream input = null;
                String entryName;
                
                try {
                    byte[] data;
                    ZipEntry entry = (ZipEntry) e.nextElement();

                    if (logger.isDebugEnabled()) {
                        logger.debug("[ZipUtil] current entry:" + entry.getName());
                    }

                    entryName = entry.getName().replace('\\', '/');
                    if (entry.isDirectory()) {
                        File dir = new File(unZipDir + entry.getName());
                        if (!dir.exists() && !dir.mkdirs()) {
                            throw new IOException("[ZipUtil] make directory fail");
                        }
                    }
                    else {
                        File outputFile = null;

                        try {
                            outputFile = new File(unZipDir + entry.getName());

                            if (entry.getSize() == 0 && entry.getName().indexOf('.') == -1) {
                                throw new Exception("[ZipUtil] This file may be a directory");
                            }

                            File parentDir = outputFile.getParentFile();
                            if (!parentDir.exists() && !parentDir.mkdirs()) {
                                throw new IOException("[ZipUtil] make directory fail");
                            }

                            input = zipFile.getInputStream(entry);
                            output = new FileOutputStream(outputFile);
                            
                            String changeTarget = PropertyUtil.getProperty("unzip.change.target");
                            
                            if(changeTarget.indexOf(entryName.substring(entryName.lastIndexOf(".")+1, entryName.length())) > -1) {
                                
                                OutputStreamWriter osr = new OutputStreamWriter(output, declaredEncoding);
                                
                                CharsetDetector detector = new CharsetDetector();
                                data = IOUtils.toByteArray(input, entry.getSize());
                                IOUtils.closeQuietly(input);
                                detector.setDeclaredEncoding(declaredEncoding);
                                detector.setText(data);
                                Reader reader = detector.detect().getReader();
                                
                                int buffer = 0;
                                while(true) {
                                    buffer = reader.read();
                                    if(buffer == -1)
                                        break;
                                    
                                    osr.write(buffer);
                                }
                                osr.close();
                                
                            } else {
                                int len = 0;
                                byte buffer[] = new byte[1024];
                                while ((len = input.read(buffer)) > 0)
                                    output.write(buffer, 0, len);
                            }
                            
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                            if (!outputFile.exists() && !outputFile.mkdirs()) {
                                throw new IOException("[ZipUtil] make directory fail");
                            }
                        }
                    }
                }

                catch (IOException ex) {
                    throw ex;
                }

                catch (Exception ex) {
                    throw new IOException("[ZipUtil] extract fail", ex);
                }

                finally {
                    if (input != null) {
                        input.close();
                    }
                    if (output != null) {
                        output.close();
                    }
                }
            }
            return unZipDir;
        }

        catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }
    }
   

    /**
     * <pre>
     * 생성된 임시 디렉토리를 삭제하기 위해 추가
     * </pre>
     * @param path
     * @return
     */
    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        
        return (path.delete());
    }
}
