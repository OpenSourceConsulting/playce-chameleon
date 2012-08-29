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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

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
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.engine.entity.xml.webapp.DescriptionType;
import com.athena.chameleon.engine.entity.xml.webapp.DisplayNameType;
import com.athena.chameleon.engine.entity.xml.webapp.ErrorPageType;
import com.athena.chameleon.engine.entity.xml.webapp.FilterMappingType;
import com.athena.chameleon.engine.entity.xml.webapp.ResourceRefType;
import com.athena.chameleon.engine.entity.xml.webapp.ServletMappingType;
import com.athena.chameleon.engine.entity.xml.webapp.ServletNameType;
import com.athena.chameleon.engine.entity.xml.webapp.UrlPatternType;
import com.athena.chameleon.engine.entity.xml.webapp.WebAppType;
import com.athena.chameleon.engine.entity.xml.webapp.WelcomeFileListType;
import com.athena.chameleon.engine.utils.ZipUtil;

/**
 * This FileUnzipTest class is a Test Case class for FileUnzip.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */ 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/context-*.xml" })
public class FileUnzipTest {
	
	private static final Log logger = LogFactory.getLog(FileUnzipTest.class);
	
	@Value("#{filteringProperties['chameleon.upload.temp.dir']}")
	public String unzipDirPath;

	@Value("#{contextProperties['unzip.default.encoding']}")
	public String defaultEncoding;

	@Value("#{contextProperties['unzip.en.encoding']}")
	public String enEncoding;

	@Inject
    @Named("engineMigrationComponent")
    private MigrationComponent component;

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
        
        // 테스트 종료 후 압축해제 디렉토리 제거
        deleteDirectory(unzipFile);
    }
    
    public void fileAsset(List<MigrationFile> list) throws Exception {
        for(MigrationFile file : list) {
            if (logger.isDebugEnabled()) {
                logger.debug("[FileUnzipTest] FilePath :" + file.getFileName());
            }
        }
    }

    //FileRead 및 라인단위 패턴 매칭 Test Code
    public void fileRead(List<MigrationFile> list) throws Exception {
    	for(MigrationFile file : list) {
    	    
    	    Iterator<Entry<Integer, String>> iterator = file.getLineMap().entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<Integer, String> entry = (Entry<Integer, String>)iterator.next();
                
                if (logger.isDebugEnabled()) {
                    logger.debug("["+file.getFileName()+"] " + entry.getKey() + " line : " + entry.getValue());
                }
                
            }
    	}
    }
    
    //xml file pasing
    public void webXmlPasing(WebAppType webapp) {
        
        try {
            
            FilterMappingType mappingType = new FilterMappingType(); 
            mappingType = (FilterMappingType) component.getWebXmlElementEntity(webapp, mappingType);
            getFilterMappingType(mappingType);
            
            List<Object> entityList = new ArrayList<Object>();
            entityList.add(new ServletMappingType());
            entityList.add(new DisplayNameType());
            entityList.add(new ErrorPageType());
            entityList.add(new WelcomeFileListType());
            entityList.add(new ResourceRefType());
            
            HashMap<Object, Object> pasingMap = (HashMap<Object, Object>) component.getWebXmlElementEntityMap(webapp, entityList);
            Iterator<Entry<Object, Object>> iterator = pasingMap.entrySet().iterator();
            
            while (iterator.hasNext()) {
                Entry<Object, Object> entry = (Entry<Object, Object>)iterator.next();
                
                if(entry.getKey() instanceof ServletMappingType) {
                    //servlet mapping 정보
                    getServletMappingType((ServletMappingType) entry.getValue());
                } else if(entry.getKey() instanceof DisplayNameType) {
                    //display name 정보
                    if (logger.isDebugEnabled()) 
                        logger.debug("[display name] " + ((DisplayNameType)entry.getValue()).getValue());
                } else if(entry.getKey() instanceof ErrorPageType) {
                    //error page 정보
                    getErrorPageType((ErrorPageType) entry.getValue());
                } else if(entry.getKey() instanceof WelcomeFileListType) {
                    //welcome file 정보
                    getWelcomFileListType((WelcomeFileListType) entry.getValue());
                } else if(entry.getKey() instanceof ResourceRefType) {
                    //resource reference 정보
                    getResourceRefType((ResourceRefType) entry.getValue());
                }
                
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            fail("Xml Pasing Error");
        }
    }
    
    // filter mapping 정보
    public void getFilterMappingType(FilterMappingType filterMapping) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("[filter mapping type]");
            logger.debug("filter name : " + filterMapping.getFilterName().getValue());
            
            for(Object o : filterMapping.getUrlPatternOrServletName()) {

                if(o instanceof UrlPatternType)
                    logger.debug("url pattern : " + ((UrlPatternType)o).getValue());
                else if (o instanceof ServletNameType)
                    logger.debug("url pattern : " + ((ServletNameType)o).getValue());
                    
            }
        }
    }

    //servlet mapping 정보
    public void getServletMappingType(ServletMappingType servletMapping) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("[servlet mapping type]");
            logger.debug("servlet name : " + servletMapping.getServletName().getValue());
            
            for(UrlPatternType pattern : servletMapping.getUrlPattern()) {
                logger.debug("url pattern : " + pattern.getValue());
            }
        }
    }
    
    //error page 정보
    public void getErrorPageType(ErrorPageType errorPage) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("[error page type]");
            logger.debug("error code : " + errorPage.getErrorCode().getValue());
            logger.debug("location   : " + errorPage.getLocation().getValue());
        }
    }

    //welcome file 정보
    public void getWelcomFileListType(WelcomeFileListType welcomeFileList) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("[welcome file list type]");
            for(String welcomeFile : welcomeFileList.getWelcomeFile())
                logger.debug("welcome file : " + welcomeFile);
        }
    }

    //resource reference 정보
    public void getResourceRefType(ResourceRefType resourceRef) {
        
        if (logger.isDebugEnabled()) {
            logger.debug("[resource reference type]");
            for(DescriptionType desc : resourceRef.getDescription()) 
                logger.debug("discription : " + desc.getValue());
            
            logger.debug("resource ref name : " + resourceRef.getResRefName().getValue());
            logger.debug("resource type : " + resourceRef.getResType().getValue());
            logger.debug("resource auth : " + resourceRef.getResAuth().getValue());
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