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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.athena.chameleon.engine.core.PDFDocGenerator;
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.CommonAnalyze;
import com.athena.chameleon.engine.entity.pdf.Dependency;
import com.athena.chameleon.engine.entity.pdf.FileSummary;
import com.athena.chameleon.engine.entity.pdf.FileType;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.web.upload.vo.Upload;

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
    @Named("pdfDocGenerator")
    private PDFDocGenerator pdfData;

    @Test
    public void unzipTest() throws Exception  {
        String zipFilePath = this.getClass().getResource("/files/test.zip").getFile();
        String tmpFileDir = unzipDirPath + File.separator + System.currentTimeMillis();
        /*
        String unzipPath = FileUtil.extract(zipFilePath, tmpFileDir);
        
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
        deleteDirectory(unzipFile);*/
    }
    
    @Test
    public void pdfWriterTest() throws Exception {

        Upload upload = new Upload();
        upload.setProjectNm("Video Hub Project");
        upload.setDepartment("삼성전자");
        upload.setBeforeWas("W");
        upload.setAfterWas("T");
        upload.setPerson("홍길동");
        upload.setOrgRole("개발팀");
        
        PDFMetadataDefinition data = new PDFMetadataDefinition();
        AnalyzeDefinition zip = new AnalyzeDefinition();
        
        //zip - summary
        FileSummary zipSummary = new FileSummary();
        zipSummary.setFileCount(10);
        zipSummary.setSourceEncoding("EUC-KR");
        zipSummary.setTargetEncoding("UTF-8");
        
        Map<FileType, FileSummary> fileSummaryMap = new HashMap<FileType, FileSummary>();
        fileSummaryMap.put(FileType.JAVA, zipSummary);
        
        zipSummary = new FileSummary();
        zipSummary.setFileCount(20);
        zipSummary.setSourceEncoding("EUC-KR");
        zipSummary.setTargetEncoding("UTF-8");
        
        fileSummaryMap.put(FileType.JSP, zipSummary);
        zip.setFileSummaryMap(fileSummaryMap);
        
        //zip - servlet
        CommonAnalyze comm = new CommonAnalyze();
        comm.setItem("NIPAFramework");
        comm.setLocation("src/main/org/nipa/web/servlet");
        zip.getServletExtendsList().add(comm);
        
        comm = new CommonAnalyze();
        comm.setItem("NIPAFramework1");
        comm.setLocation("src/main/org/nipa/web/servlet1");
        zip.getServletExtendsList().add(comm);
        
        //zip - ejb
        comm = new CommonAnalyze();
        comm.setItem("NIPAFrameworkEjb");
        comm.setLocation("src/main/org/nipa/web/ejb");
        zip.getEjbExtendsList().add(comm);
        
        comm = new CommonAnalyze();
        comm.setItem("NIPAFrameworkEjb1");
        comm.setLocation("src/main/org/nipa/web/ejb1");
        zip.getEjbExtendsList().add(comm);
        
        //zip - java
        Dependency dependency = new Dependency();
        dependency.setFileName("src/main/org/nipa/migration/WebHelper.java");
        
        Map<String, String> fileInfo = new HashMap<String, String>();
        fileInfo.put("Line 8:", "import com.bea.weblogic.jmx.DataSourceMBean");
        fileInfo.put("Line 12:", "import com.bea.weblogic.jmx.ContextMBean;");
        fileInfo.put("Line 138:", "public ContextMBean getContextMBean() {");
        dependency.setDependencyStrMap(fileInfo);
        zip.getJavaDependencyList().add(dependency);
        
        dependency = new Dependency();
        dependency.setFileName("src/main/org/nipa/migration/WebHelper2.java");
        
        fileInfo = new HashMap<String, String>();
        fileInfo.put("Line 18:", "import com.bea.weblogic.jmx.DataSourceMBean");
        fileInfo.put("Line 22:", "import com.bea.weblogic.jmx.ContextMBean;");
        fileInfo.put("Line 238:", "public ContextMBean getContextMBean() {");
        dependency.setDependencyStrMap(fileInfo);
        zip.getJavaDependencyList().add(dependency);
        
        //zip - jsp
        dependency = new Dependency();
        dependency.setFileName("src/main/org/nipa/migration/WebHelper.jsp");
        
        fileInfo = new HashMap<String, String>();
        fileInfo.put("Line 8:", "import com.bea.weblogic.jmx.DataSourceMBean");
        fileInfo.put("Line 12:", "import com.bea.weblogic.jmx.ContextMBean;");
        fileInfo.put("Line 138:", "public ContextMBean getContextMBean() {");
        dependency.setDependencyStrMap(fileInfo);
        zip.getJspDependencyList().add(dependency);
        
        dependency = new Dependency();
        dependency.setFileName("src/main/org/nipa/migration/WebHelper2.jsp");
        
        fileInfo = new HashMap<String, String>();
        fileInfo.put("Line 18:", "import com.bea.weblogic.jmx.DataSourceMBean");
        fileInfo.put("Line 22:", "import com.bea.weblogic.jmx.ContextMBean;");
        fileInfo.put("Line 238:", "public ContextMBean getContextMBean() {");
        dependency.setDependencyStrMap(fileInfo);
        zip.getJspDependencyList().add(dependency);

        //zip - property
		dependency = new Dependency();
		dependency.setFileName("src/main/org/nipa/migration/WebHelper.properties");

		fileInfo = new HashMap<String, String>();
		fileInfo.put("Line 8:", "import com.bea.weblogic.jmx.DataSourceMBean");
		fileInfo.put("Line 12:", "import com.bea.weblogic.jmx.ContextMBean;");
		dependency.setDependencyStrMap(fileInfo);
		zip.getPropertyDependencyList().add(dependency);

		dependency = new Dependency();
		dependency.setFileName("src/main/org/nipa/migration/WebHelper2.properties");

		fileInfo = new HashMap<String, String>();
		fileInfo.put("Line 22:", "import com.bea.weblogic.jmx.ContextMBean;");
		fileInfo.put("Line 238:", "public ContextMBean getContextMBean() {");
		dependency.setDependencyStrMap(fileInfo);
		zip.getPropertyDependencyList().add(dependency);

        //zip - class
        dependency = new Dependency();
        dependency.setFileName("src/main/org/nipa/migration/WebHelper.class");
        
        fileInfo = new HashMap<String, String>();
        fileInfo.put("Line 8:", "import com.bea.weblogic.jmx.DataSourceMBean");
        fileInfo.put("Line 138:", "public ContextMBean getContextMBean() {");
        dependency.setDependencyStrMap(fileInfo);
        zip.getClassDependencyList().add(dependency);
        
        dependency = new Dependency();
        dependency.setFileName("src/main/org/nipa/migration/WebHelper2.class");
        
        fileInfo = new HashMap<String, String>();
        fileInfo.put("Line 18:", "import com.bea.weblogic.jmx.DataSourceMBean");
        fileInfo.put("Line 22:", "import com.bea.weblogic.jmx.ContextMBean;");
        dependency.setDependencyStrMap(fileInfo);
        zip.getClassDependencyList().add(dependency);
        
        //jsp 분석 결과
        Map<String, Integer> dataMap = new HashMap<String, Integer>();
        dataMap.put("<%@ page language=\"java\" contentType=\"text/html; charset=euc-kr\"%>", 35);
        dataMap.put("<%@ page language=\"java\" pageEncoding=\"java\" contentType=\"text/html; charset=EUC-KR\" %>", 200);

        zip.setJspDirectiveMap(dataMap);
        
        data.setZipDefinition(zip);
        
        PDFDocGenerator.createPDF(unzipDirPath+File.separator+"test.pdf", upload, data);
        
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
