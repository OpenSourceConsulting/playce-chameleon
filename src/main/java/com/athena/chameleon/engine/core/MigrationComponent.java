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
 * Hyo-jeong Lee	2012. 8. 29.		First Draft.
 */
package com.athena.chameleon.engine.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.athena.chameleon.common.utils.PropertyUtil;
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.engine.utils.JaxbUtils;

/**
 * Migration 을 위한 Context
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

@Component("migrationComponent")
public class MigrationComponent {

    public String                   rootPath;
    public File                     webXmlFile;
    public File                     applicationXmlFile;
    public File                     ejbXmlFile;
    public File                     weblogicEjbXmlFile;
    public File						jeusEjbXmlFile;
    public List<MigrationFile>      migrationFileList = new ArrayList<MigrationFile>();
    
    /**
     * 
     * file diractory 안에 있는 file list setting 
     *
     * @param file diractory file
     * @param rootPath 최상위 path
     * @throws Exception
     */
    public void setMigrationFileList(File file, String rootPath) throws Exception {
        this.rootPath = rootPath;
        setMigrationFileList(file);
    }
    
    /**
     * 
     * file diractory 안에 있는 file list setting 
     *
     * @param file diractory file
     * @throws Exception
     */
    public void setMigrationFileList(File file) throws Exception {

        Pattern p = Pattern.compile(".*test.*"); //추후 pattern 변경
        Matcher match = null;
        
        if(file.isDirectory()) {
            for (int i=0;i < file.listFiles().length;i++) {
                File f = file.listFiles()[i];
                if(f.isDirectory()) {
                    setMigrationFileList(f);
                } else {
                    
                    String filePath = f.getAbsolutePath().substring(rootPath.length(), f.getAbsolutePath().length()).replace('\\', '/');
                    String changeTarget = PropertyUtil.getProperty("unzip.change.target");
                    
                    MigrationFile fileEntity = new MigrationFile();
                    fileEntity.setFileName(filePath);
                    
                    //문서 라인 추출
                    if(changeTarget.indexOf(filePath.substring(filePath.lastIndexOf(".")+1, filePath.length())) > -1) {
                    
                        HashMap<Integer, String> lineMap = new LinkedHashMap<Integer, String>();
                        
                        //xml file pasing
                        if(filePath.indexOf("WEB-INF/web.xml") > -1) {
                            webXmlFile = f;
                        } else if(filePath.indexOf("WEB-INF/application.xml") > -1) {
                        	applicationXmlFile = f;
                        } else if(filePath.indexOf("META-INF/ejb-jar.xml") > -1) {
                            ejbXmlFile = f;
                        } else if(filePath.indexOf("META-INF/weblogic-ejb-jar.xml") > -1) {
                            weblogicEjbXmlFile = f;
                        } else if(filePath.indexOf("META-INF/jeus-ejb-dd.xml") > -1){
                        	jeusEjbXmlFile = f;
                        }
                        
                        try {
                            FileReader reader = new FileReader(f);
                            BufferedReader buffer = new BufferedReader(reader);
                            
                            
                            String lineStr = null;
                            int line = 0;
                            while((lineStr = buffer.readLine()) != null) {
                                
                                line++;
                                
                                match = p.matcher(lineStr);
                                if(match.matches()) {
                                    lineMap.put(line, lineStr);
                                }
                            }
                            fileEntity.setLineMap(lineMap);
                            migrationFileList.add(fileEntity);
                            
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                        
                    }
                 
                }
            }
        }
    }

    /**
     * 
     * web.xml pasing
     *
     * @param xmlFile web.xml file
     * @return WebAppType
     */
    public Object webXmlPasing(File xmlFile) {
        this.webXmlFile = xmlFile;
        return webXmlPasing();
    }
    
    /**
     * 
     * web.xml pasing
     *
     * @return WebAppType
     */
    public Object webXmlPasing() {
        
    	if(webXmlFile == null)
    		return null;
    		
    	Object webApp = null;
        Unmarshaller unmarshaller;
        try {
        	//web.xml 2.5
            unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.webapp.v2_5");
        	webApp = unmarshaller.unmarshal(webXmlFile);
        } catch(Exception e1) {
            try {
            	//web.xml 2.4
        		unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.webapp.v2_4");
                webApp = unmarshaller.unmarshal(webXmlFile);
        	} catch(Exception e2) {
        		try {
        			//web.xml 2.3
	        		unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.webapp.v2_3");
	                webApp = unmarshaller.unmarshal(webXmlFile);
        		} catch(Exception e3) {
        			e3.printStackTrace();
        		}
        	}
        }
        return webApp;
    }
    
    /**
     * 
     * application.xml pasing
     *
     * @param xmlFile application.xml file
     * @return Object
     */
    public Object applicationXmlPasing(File xmlFile) {
        this.applicationXmlFile = xmlFile;
        return applicationXmlPasing();
    }
    
    /**
     * 
     * application.xml pasing
     *
     * @return Object
     */
    public Object applicationXmlPasing() {
        
    	if(applicationXmlFile == null)
    		return null;

        Object app = null;
        Unmarshaller unmarshaller;
        try {
            //application.xml 1.4
            unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.application.v1_4");
            app = unmarshaller.unmarshal(applicationXmlFile);
        } catch(Exception e1) {
            try {
                //application.xml 1.3
                unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.application.v1_3");
                app = unmarshaller.unmarshal(applicationXmlFile);
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        return app;
    }

    /**
     * 
     * ejb-jar.xml pasing
     *
     * @param xmlFile ejb-jar.xml file
     * @return EjbJarType
     */
    public Object ejbXmlPasing(File xmlFile) {
        this.ejbXmlFile = xmlFile;
        return ejbXmlPasing();
    }
    
    /**
     * 
     *  ejb-jar.xml pasing
     *
     * @return EjbJarType
     */
    public Object ejbXmlPasing() {
        
    	if(ejbXmlFile == null)
    		return null;
            
        Object ejb = null;
        Unmarshaller unmarshaller;
        try {
            //ejb-jar.xml 2.1
            unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.ejbjar.v2_1");
            ejb = ((JAXBElement<?>) unmarshaller.unmarshal(ejbXmlFile)).getValue();
        } catch(Exception e1) {
            try {
                //ejb-jar.xml 2.0
                unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.ejbjar.v2_0");
                ejb = unmarshaller.unmarshal(ejbXmlFile);
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        return ejb;
    }

    /**
     * 
     * weblogic-ejb-jar.xml pasing
     *
     * @param xmlFile weblogic-ejb-jar.xml file
     * @return EjbJarType
     */
    public Object weblogicEjbXmlPasing(File xmlFile) {
        this.weblogicEjbXmlFile = xmlFile;
        return weblogicEjbXmlPasing();
    }
    
    /**
     * 
     *  weblogic-ejb-jar.xml pasing
     *
     * @return EjbJarType
     */
    public Object weblogicEjbXmlPasing() {
        
        if(weblogicEjbXmlFile == null)
            return null;
        
        Object result = null;
        Unmarshaller unmarshaller;
        try {
            //weblogic-ejb-jar.xml 9.0
            unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0");
            result = ((JAXBElement<?>) unmarshaller.unmarshal(weblogicEjbXmlFile)).getValue();
        } catch(JAXBException je) {
            je.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * jeus-ejb-dd.xml pasing
     *
     * @param xmlFile jeus-ejb-dd.xml file
     * @return Object
     */
    public Object jeusEjbXmlPasing(File xmlFile) {
        this.jeusEjbXmlFile = xmlFile;
        return jeusEjbXmlPasing();
    }
    
    /**
     * 
     *  jeus-ejb-dd.xml pasing
     *
     * @return Object
     */
    public Object jeusEjbXmlPasing() {
        
        if(jeusEjbXmlFile == null)
            return null;
        
        Object result = null;
        Unmarshaller unmarshaller;
        try {
            //jeus-ejb-jar.xml 6.0
            unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.ejbjar.jeus.v6_0");
            result = ((JAXBElement<?>) unmarshaller.unmarshal(jeusEjbXmlFile)).getValue();
        } catch(Exception e1) {
            try {
                //jeus-ejb-jar.xml 5.0
                unmarshaller = JaxbUtils.createUnmarshaller("com.athena.chameleon.engine.entity.xml.ejbjar.jeus.v5_0");
                result = ((JAXBElement<?>) unmarshaller.unmarshal(jeusEjbXmlFile)).getValue();
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
       
    
    /**
     * @return the migrationFileList
     */
    public List<MigrationFile> getMigrationFileList() {
        return migrationFileList;
    }

    /**
     * @return the rootPath
     */
    public String getRootPath() {
        return rootPath;
    }

    /**
     * @return the webXmlFile
     */
    public File getWebXmlFile() {
        return webXmlFile;
    }

    /**
     * @param webXmlFile the webXmlFile to set
     */
    public void setWebXmlFile(File webXmlFile) {
        this.webXmlFile = webXmlFile;
    }
    
    /**
     * 
     * @return the applicationXmlFile
     */
    public File getApplicationXmlFile() {
		return applicationXmlFile;
	}

    /**
     * 
     * @param applicationXmlFile the applicationXmlFile to set
     */
	public void setApplicationXmlFile(File applicationXmlFile) {
		this.applicationXmlFile = applicationXmlFile;
	}

}
//end of MigrationContext.java