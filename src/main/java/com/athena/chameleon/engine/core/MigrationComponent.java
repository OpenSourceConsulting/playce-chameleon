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

import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.athena.chameleon.common.utils.MigrationStatusUtil;
import com.athena.chameleon.common.utils.PropertyUtil;
import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.analyzer.support.EarAnalyzer;
import com.athena.chameleon.engine.core.analyzer.support.JarAnalyzer;
import com.athena.chameleon.engine.core.analyzer.support.WarAnalyzer;
import com.athena.chameleon.engine.core.analyzer.support.ZipAnalyzer;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.entity.file.Migration;
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.entity.upload.Upload;
import com.athena.chameleon.engine.policy.Policy;
import com.athena.chameleon.engine.threadpool.executor.ChameleonThreadPoolExecutor;
import com.athena.chameleon.engine.utils.FileUtil;
import com.athena.chameleon.engine.utils.JaxbUtils;

/**
 * Migration 을 위한 Context
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

@Component("migrationComponent")
public class MigrationComponent {

    public String                   defaultPath;
    public String                   rootPath;
    public File						unzipFile;
    public File                     webXmlFile;
    public File                     applicationXmlFile;
    public File                     ejbXmlFile;
    public File                     weblogicEjbXmlFile;
    public File						jeusEjbXmlFile;
    public List<MigrationFile>      migrationFileList = new ArrayList<MigrationFile>();
    
    @Inject
    @Named("policy")
    private Policy policy;

    @Inject
    @Named("taskExecutor")
    private ChameleonThreadPoolExecutor executor;

    @Inject
    @Named("fileEncodingConverter")
    private FileEncodingConverter converter;

    {
        try {
            defaultPath = PropertyUtil.getProperty("chameleon.upload.temp.dir") + File.separator + System.currentTimeMillis() + File.separator;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
     
    /**
     * 
     * Migration 실행 메소드
     *
     * @param commFile
     * @return
     */
    public Migration executeMigration(File migrationFile) {
        
        if(migrationFile == null)
            return null;
        
        Migration entity = null;
        
        try {
            String unzipPath = FileUtil.extract(migrationFile.getAbsolutePath(), defaultPath);
            
            this.unzipFile = new File(unzipPath);
            this.rootPath = unzipFile.getAbsolutePath();
            setMigrationFileList();
            
            //test를 위한 임시 Pojo. 추후 삭제 및 다른 Pojo로 변경 예정
            entity = new Migration();
            
            PDFDocGenerator pdfData = new PDFDocGenerator();
            entity.setFileListStr(pdfData.getMigrationFileList(migrationFileList));
            entity.setCheckFileListStr(pdfData.getMigrationFileCheckLine(migrationFileList));
            entity.setWebXmlStr(pdfData.getWebXmlSettingInfo(webXmlParsing()));
            entity.setApplicationXmlStr(pdfData.getApplicationXmlSettingInfo(applicationXmlParsing()));
            entity.setEjbXmlStr(pdfData.getEjbXmlSettingInfo(ejbXmlParsing(), weblogicEjbXmlParsing(), jeusEjbXmlParsing()));
            
            FileUtil.deleteDirectory(this.unzipFile);
                
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return entity;
            
    }
    
	/**
	 * 
	 * 입력된 path의 압축파일을 unzip
	 *
	 * @param zipFilePath 압축파일 path
	 * @throws Exception
	 */
    public void unzipFile(String zipFilePath) throws Exception {
    	String tmpFileDir = PropertyUtil.getProperty("chameleon.upload.temp.dir") + File.separator + System.currentTimeMillis();
        String unzipPath = FileUtil.extract(zipFilePath, tmpFileDir);
        
        this.unzipFile = new File(unzipPath);
        this.rootPath = unzipFile.getAbsolutePath();
        
    }

    /**
     * 
     * file diractory 안에 있는 file list setting 
     *
     * @param rootPath 최상위 path
     * @throws Exception
     */
    public List<MigrationFile> setMigrationFileList() throws Exception {
    	return setMigrationFileList(this.unzipFile, this.rootPath);
    }
    
    /**
     * 
     * file diractory 안에 있는 file list setting 
     *
     * @param file diractory file
     * @param rootPath 최상위 path
     * @throws Exception
     */
    public List<MigrationFile> setMigrationFileList(File file, String rootPath) throws Exception {
        this.rootPath = rootPath;
        return setMigrationFileList(file);
    }
    
    /**
     * 
     * file diractory 안에 있는 file list setting 
     *
     * @param file diractory file
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public List<MigrationFile> setMigrationFileList(File file) throws Exception {

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
                        
                        //xml file parsing
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
        
        return migrationFileList;
    }

    /**
     * 
     * web.xml parsing
     *
     * @param xmlFile web.xml file
     * @return WebAppType
     */
    public Object webXmlParsing(File xmlFile) {
        this.webXmlFile = xmlFile;
        return webXmlParsing();
    }
    
    /**
     * 
     * web.xml parsing
     *
     * @return WebAppType
     */
    public Object webXmlParsing() {
        
    	if(webXmlFile == null)
    		return null;
    		
    	Object webApp = null;
        Unmarshaller unmarshaller;
        try {
        	//web.xml 2.5
            unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.webapp.v2_5.WebAppType.class.getPackage().getName());
        	webApp = unmarshaller.unmarshal(webXmlFile);
        } catch(Exception e1) {
            try {
            	//web.xml 2.4
                unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.webapp.v2_4.WebAppType.class.getPackage().getName());
                webApp = unmarshaller.unmarshal(webXmlFile);
        	} catch(Exception e2) {
        		try {
        			//web.xml 2.3
        		    unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.webapp.v2_3.WebApp.class.getPackage().getName());
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
     * application.xml parsing
     *
     * @param xmlFile application.xml file
     * @return Object
     */
    public Object applicationXmlParsing(File xmlFile) {
        this.applicationXmlFile = xmlFile;
        return applicationXmlParsing();
    }
    
    /**
     * 
     * application.xml parsing
     *
     * @return Object
     */
    public Object applicationXmlParsing() {
        
    	if(applicationXmlFile == null)
    		return null;

        Object app = null;
        Unmarshaller unmarshaller;
        try {
            //application.xml 1.4
            unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.application.v1_4.ApplicationType.class.getPackage().getName());
            app = unmarshaller.unmarshal(applicationXmlFile);
        } catch(Exception e1) {
            try {
                //application.xml 1.3
                unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.application.v1_3.Application.class.getPackage().getName());
                app = unmarshaller.unmarshal(applicationXmlFile);
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        return app;
    }

    /**
     * 
     * ejb-jar.xml parsing
     *
     * @param xmlFile ejb-jar.xml file
     * @return EjbJarType
     */
    public Object ejbXmlParsing(File xmlFile) {
        this.ejbXmlFile = xmlFile;
        return ejbXmlParsing();
    }
    
    /**
     * 
     *  ejb-jar.xml parsing
     *
     * @return EjbJarType
     */
    public Object ejbXmlParsing() {
        
    	if(ejbXmlFile == null)
    		return null;
            
        Object ejb = null;
        Unmarshaller unmarshaller;
        try {
            //ejb-jar.xml 2.1
            unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.EjbJarType.class.getPackage().getName());
            ejb = ((JAXBElement<?>) unmarshaller.unmarshal(ejbXmlFile)).getValue();
        } catch(Exception e1) {
            try {
                //ejb-jar.xml 2.0
                unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.EjbJar.class.getPackage().getName());
                ejb = unmarshaller.unmarshal(ejbXmlFile);
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        return ejb;
    }

    /**
     * 
     * weblogic-ejb-jar.xml parsing
     *
     * @param xmlFile weblogic-ejb-jar.xml file
     * @return EjbJarType
     */
    public Object weblogicEjbXmlParsing(File xmlFile) {
        this.weblogicEjbXmlFile = xmlFile;
        return weblogicEjbXmlParsing();
    }
    
    /**
     * 
     *  weblogic-ejb-jar.xml parsing
     *
     * @return EjbJarType
     */
    public Object weblogicEjbXmlParsing() {
        
        if(weblogicEjbXmlFile == null)
            return null;
        
        Object result = null;
        Unmarshaller unmarshaller;
        try {
            //weblogic-ejb-jar.xml 9.0
            unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0.WeblogicEjbJarType.class.getPackage().getName());
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
     * jeus-ejb-dd.xml parsing
     *
     * @param xmlFile jeus-ejb-dd.xml file
     * @return Object
     */
    public Object jeusEjbXmlParsing(File xmlFile) {
        this.jeusEjbXmlFile = xmlFile;
        return jeusEjbXmlParsing();
    }
    
    /**
     * 
     *  jeus-ejb-dd.xml parsing
     *
     * @return Object
     */
    public Object jeusEjbXmlParsing() {
        
        if(jeusEjbXmlFile == null)
            return null;
        
        Object result = null;
        Unmarshaller unmarshaller;
        try {
            //jeus-ejb-jar.xml 6.0
            unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.ejbjar.jeus.v6_0.JeusEjbDdType.class.getPackage().getName());
            result = ((JAXBElement<?>) unmarshaller.unmarshal(jeusEjbXmlFile)).getValue();
        } catch(Exception e1) {
            try {
                //jeus-ejb-jar.xml 5.0
                unmarshaller = JaxbUtils.createUnmarshaller(com.athena.chameleon.engine.entity.xml.ejbjar.jeus.v5_0.JeusEjbDdType.class.getPackage().getName());
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

	public void migrate(String sourceFile, String deployFile, Upload upload) {
		// PDF 출력용 통합 Data Object를 초기화 하고 ThreadLocal에 저장한다.
		PDFMetadataDefinition metadataDefinition = new PDFMetadataDefinition();
		metadataDefinition.setSourceFile(sourceFile);
		metadataDefinition.setDeployFile(deployFile);		
		
		ThreadLocalUtil.add(ChameleonConstants.PDF_METADATA_DEFINITION, metadataDefinition);
		
		AnalyzeDefinition analyzeDefinition = null;
		
		String extension = null;
		
		if (sourceFile != null) {
			analyzeDefinition = new AnalyzeDefinition();
			
			extension = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
			if (extension.equals("zip")) {
				metadataDefinition.setZipDefinition(analyzeDefinition);
				metadataDefinition.setMigrateSourceFile(new ZipAnalyzer(policy, converter, executor, analyzeDefinition).analyze(sourceFile));
			}
		}
		
		if (deployFile != null) {
			analyzeDefinition = new AnalyzeDefinition();
			
			extension = deployFile.substring(deployFile.lastIndexOf(".") + 1);
			if (extension.equals("ear")) {
				metadataDefinition.setEarDefinition(analyzeDefinition);
				metadataDefinition.setMigrateDeployFile(new EarAnalyzer(policy, converter, executor, analyzeDefinition).analyze(deployFile));
			} else if (extension.equals("war")) {
				metadataDefinition.addWarDefinitionMap(new File(deployFile).getName(), analyzeDefinition);
				metadataDefinition.setMigrateDeployFile(new WarAnalyzer(policy, converter, executor, analyzeDefinition, false).analyze(deployFile));
			} else if (extension.equals("jar")) {
				metadataDefinition.addJarDefinitionMap(new File(deployFile).getName(), analyzeDefinition);
				metadataDefinition.setMigrateDeployFile(new JarAnalyzer(policy, converter, executor, analyzeDefinition, false).analyze(deployFile));
			}
		}
		
		try {
    		if (sourceFile != null) {
    		    PDFDocGenerator.createPDF(new File(sourceFile).getParentFile().getAbsolutePath()+File.separator+upload.getProjectNm()+"_Migration.pdf", upload, metadataDefinition);
    		    metadataDefinition.setPdfFile(new File(sourceFile).getParentFile().getAbsolutePath()+File.separator+upload.getProjectNm()+"_Migration.pdf");
    		} else if (deployFile != null) {
    		    PDFDocGenerator.createPDF(new File(deployFile).getParentFile().getAbsolutePath()+File.separator+upload.getProjectNm()+"_Migration.pdf", upload, metadataDefinition);
    		    metadataDefinition.setPdfFile(new File(deployFile).getParentFile().getAbsolutePath()+File.separator+upload.getProjectNm()+"_Migration.pdf");
    		}
    		
    		MigrationStatusUtil.setPercentage(null, 0);
        	MigrationStatusUtil.setCurrentStatus(null);
        	
		} catch(Exception e) {
		    e.printStackTrace();
		}
		
	}

}
//end of MigrationContext.java