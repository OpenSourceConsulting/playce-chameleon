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

import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.athena.chameleon.engine.core.MigrationComponent;
import com.athena.chameleon.engine.core.PDFDataDefinition;
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.engine.utils.ZipUtil;

/**
 * This FileUnzipTest class is a Test Case class for FileUnzip.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */ 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/context-*.xml" })
public class MigrationComponentTest {
	
	private static final Log logger = LogFactory.getLog(MigrationComponentTest.class);
	
	@Value("#{filteringProperties['chameleon.upload.temp.dir']}")
	public String unzipDirPath;

	@Value("#{contextProperties['unzip.default.encoding']}")
	public String defaultEncoding;

	@Value("#{contextProperties['unzip.en.encoding']}")
	public String enEncoding;

	@Inject
    @Named("migrationComponent")
    private MigrationComponent component;

	@Inject
    @Named("pdfDataDefinition")
    private PDFDataDefinition pdfData;

    @Test
    public void unzipTest() throws Exception  {
        String zipFilePath = this.getClass().getResource("/files/test.zip").getFile();
        String tmpFileDir = unzipDirPath + File.separator + System.currentTimeMillis();
        String unzipPath = ZipUtil.extract(zipFilePath, tmpFileDir);
        
        File unzipFile = new File(unzipPath);
        component = new MigrationComponent();
        component.setMigrationFileList(unzipFile, unzipPath);
        
        List<MigrationFile> list = component.getMigrationFileList();
        
        fileAsset(list);
        fileRead(list);
        webXmlPasing(component.webXmlPasing());
        applicationXmlPasing(component.applicationXmlPasing());
        ejbXmlPasing(component.ejbXmlPasing(), component.weblogicEjbXmlPasing(), component.jeusEjbXmlPasing());
        
        // 테스트 종료 후 압축해제 디렉토리 제거
        deleteDirectory(unzipFile);
    }
    
    public void fileAsset(List<MigrationFile> list) throws Exception {
        if (logger.isDebugEnabled()) {
        	logger.debug(pdfData.getMigrationFileList(list));
        }
    }

    //FileRead 및 라인단위 패턴 매칭 Test Code
    public void fileRead(List<MigrationFile> list) throws Exception {
    	if (logger.isDebugEnabled()) {
        	logger.debug(pdfData.getMigrationFileCheckLine(list));
        }
    }
    
    //xml file pasing
    public void webXmlPasing(Object webApp) {
        
        try {
            if(webApp == null)
                return;
                
        	if (logger.isDebugEnabled()) {
        		logger.debug(pdfData.getWebXmlSettingInfo(webApp));
        	}
        } catch(Exception e) {
            e.printStackTrace();
            fail("web xml Pasing Error");
        }
    }

    //application file pasing
    public void applicationXmlPasing(Object app) {
        
        try {
        	if(app == null)
        		return;
        	
        	if (logger.isDebugEnabled()) {
        		logger.debug(pdfData.getApplicationXmlSettingInfo(app));
        	}
        	
        } catch(Exception e) {
            e.printStackTrace();
            fail("application xml Pasing Error");
        }
    }

    //ejb file pasing
    public void ejbXmlPasing(Object ejb, Object weblogic, Object jeus) {
        
        try {
        	
        	if(ejb == null) 
        	    return;
        	
        	if (logger.isDebugEnabled()) {
                logger.debug(pdfData.getEjbXmlSettingInfo(ejb, weblogic, jeus));
            }
        } catch(Exception e) {
            e.printStackTrace();
            fail("application xml Pasing Error");
        }
    }
    
	/**
	 * <pre>
	 * 테스트 시 생성된 임시 디렉토리를 삭제하기 위해 추가
	 * </pre>
	 * @param path
	 * @return
	 */
	public boolean deleteDirectory(File path) {
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
	}//end of deleteDirectory
}
//end of FileUnzipTest.java