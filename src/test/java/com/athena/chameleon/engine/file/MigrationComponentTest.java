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
import com.athena.chameleon.engine.entity.pdf.ClassAnalyze;
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
        AnalyzeDefinition war = new AnalyzeDefinition();
        AnalyzeDefinition ear = new AnalyzeDefinition();
        
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
        war.setFileSummaryMap(fileSummaryMap);
        ear.setFileSummaryMap(fileSummaryMap);
        
        //zip - servlet
        CommonAnalyze comm = new CommonAnalyze();
        comm.setItem("NIPAFramework");
        comm.setLocation("src/main/org/nipa/web/servlet");
        zip.getServletExtendsList().add(comm);
        war.getServletExtendsList().add(comm);
        
        comm = new CommonAnalyze();
        comm.setItem("NIPAFramework1");
        comm.setLocation("src/main/org/nipa/web/servlet1");
        zip.getServletExtendsList().add(comm);
        war.getServletExtendsList().add(comm);
        
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
        war.getJspDependencyList().add(dependency);
        
        dependency = new Dependency();
        dependency.setFileName("src/main/org/nipa/migration/WebHelper2.jsp");
        
        fileInfo = new HashMap<String, String>();
        fileInfo.put("Line 18:", "import com.bea.weblogic.jmx.DataSourceMBean");
        fileInfo.put("Line 22:", "import com.bea.weblogic.jmx.ContextMBean;");
        fileInfo.put("Line 238:", "public ContextMBean getContextMBean() {");
        dependency.setDependencyStrMap(fileInfo);
        zip.getJspDependencyList().add(dependency);
        war.getJspDependencyList().add(dependency);
        
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
        war.getClassDependencyList().add(dependency);
        
        dependency = new Dependency();
        dependency.setFileName("src/main/org/nipa/migration/WebHelper2.class");
        
        fileInfo = new HashMap<String, String>();
        fileInfo.put("Line 18:", "import com.bea.weblogic.jmx.DataSourceMBean");
        fileInfo.put("Line 22:", "import com.bea.weblogic.jmx.ContextMBean;");
        dependency.setDependencyStrMap(fileInfo);
        zip.getClassDependencyList().add(dependency);
        war.getClassDependencyList().add(dependency);
        
        //jsp 분석 결과
        Map<String, Integer> dataMap = new HashMap<String, Integer>();
        dataMap.put("<%@ page language=\"java\" contentType=\"text/html; charset=euc-kr\"%>", 35);
        dataMap.put("<%@ page language=\"java\" pageEncoding=\"java\" contentType=\"text/html; charset=EUC-KR\" %>", 200);

        zip.setJspDirectiveMap(dataMap);
        war.setJspDirectiveMap(dataMap);
        
        //descripter
        comm = new CommonAnalyze();
        comm.setItem("application.xml");
        comm.setLocation("newgpec.ear/application.xml");
        comm.setContents("<?xmlversion=\"1.0\"encoding=\"UTF-8\"?>\n<applicationxmlns=\"http://java.sun.com/xml/ns/javaee\"\nxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"version=\"5\"\nxsi:schemaLocation=\"http://java.sun.com/xml/ns/javaeehttp://java.sun.com/xml/ns/javaee/application_5.xsd\">\n\n\n<module>\n <java>test-client.jar</java>\n</module>\n\n\n<module>\n <ejb>test-ejb.jar</ejb>\n</module>\n\n\n<module>\n<web>\n   <web-uri>test.war</web-uri>\n   <context-root>test</context-root>\n </web>\n</module>\n\n\n<library-directory>lib</library-directory>\n</application>\n");
        ear.getDescripterList().add(comm);
        war.getDescripterList().add(comm);
        
        comm = new CommonAnalyze();
        comm.setItem("jeus-application.xml");
        comm.setLocation("newgpec.ear/jeus-application.xml");
        comm.setContents("<?xmlversion=\"1.0\"encoding=\"UTF-8\"?>\n<applicationxmlns=\"http://java.sun.com/xml/ns/javaee\"\nxmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"version=\"5\"\nxsi:schemaLocation=\"http://java.sun.com/xml/ns/javaeehttp://java.sun.com/xml/ns/javaee/application_5.xsd\">\n\n\n<module>\n <java>test-client.jar</java>\n</module>\n\n\n<module>\n <ejb>test-ejb.jar</ejb>\n</module>\n\n\n<module>\n<web>\n   <web-uri>test.war</web-uri>\n   <context-root>test</context-root>\n </web>\n</module>\n\n\n<library-directory>lib</library-directory>\n</application>\n");
        ear.getDescripterList().add(comm);
        war.getDescripterList().add(comm);
        
        //ear-ejb application
        List<CommonAnalyze> commList = new ArrayList<CommonAnalyze>();
        comm = new CommonAnalyze();
        comm.setItem("Home Interface");
        comm.setContents("com.xxx.xxx.XxxHome");
        commList.add(comm);
        
        comm = new CommonAnalyze();
        comm.setItem("Remote Interface");
        comm.setContents("com.xxx.xxx.XxxRemote");
        commList.add(comm);
        
        comm = new CommonAnalyze();
        comm.setItem("Enterprise Bean Class");
        comm.setContents("com.xxx.xxx.XxxBean");
        commList.add(comm);
        
        ear.getEjbApplicationMap().put("test-ejb.jar", commList);
        
        commList = new ArrayList<CommonAnalyze>();
        comm = new CommonAnalyze();
        comm.setItem("Home Interface");
        comm.setContents("com.xxx.xxx.XxxHome");
        commList.add(comm);
        
        comm = new CommonAnalyze();
        comm.setItem("Remote Interface");
        comm.setContents("com.xxx.xxx.XxxRemote");
        commList.add(comm);
        
        comm = new CommonAnalyze();
        comm.setItem("Enterprise Bean Class");
        comm.setContents("com.xxx.xxx.XxxBean");
        commList.add(comm);
        
        ear.getEjbApplicationMap().put("test-ejb1.jar", commList);
        
        //jar
        war.getLibraryList().add("commons-io.jar");
        war.getLibraryList().add("commons-dbcp.jar");
        
        war.getDeleteLibraryList().add("common.jar");
        
        ear.getLibraryList().add("commons-io.jar");
        ear.getLibraryList().add("commons-dbcp.jar");
        
        ear.getDeleteLibraryList().add("common.jar");
        
        //class 구성정보
        ClassAnalyze classes = new ClassAnalyze();
        classes.setClassName("com.xxx.xxx.XxxService");
        classes.getSuperClasses().add("java.lang.Object");
        classes.setClassModifier("public");
        classes.setFinalClass(false);
        classes.getFiledList().add("public java.lang.String com.osc.reflect.Employee._firstName");
        classes.getFiledList().add("public java.lang.String com.osc.reflect.Employee._lastName");
        classes.getMethodList().add("public int com.osc.reflect.Employee.getSalary()");
        classes.getMethodList().add("public final native java.lang.Class java.lang.Object.getClass()");
        war.getClassesConstList().add(classes);
        ear.getClassesConstList().add(classes);
        
        classes = new ClassAnalyze();
        classes.setClassName("com.xxx.xxx.YyyService");
        classes.getSuperClasses().add("java.lang.Object");
        classes.setClassModifier("public");
        classes.setFinalClass(true);
        classes.getFiledList().add("public java.lang.String com.osc.reflect.Employee._firstName");
        classes.getFiledList().add("public java.lang.String com.osc.reflect.Employee._lastName");
        classes.getMethodList().add("public boolean java.lang.Object.equals(java.lang.Object)");
        classes.getMethodList().add("public java.lang.String java.lang.Object.toString()");
        war.getClassesConstList().add(classes);
        ear.getClassesConstList().add(classes);
        
        war.setFileName("war_test");
        
        data.setZipDefinition(zip);
        data.addWarDefinitionMap("warTest", war);
        data.setEarDefinition(ear);
        
        //변환 대상 파일
        data.getTransXmlInfo().put("weblogic-ejb-jar.xml","<weblogic-ejb-jar>\n <description>\n<![CDATA[Generated by XDoclet]]>\n</description>\n <weblogic-enterprise-bean>\n <ejb-name>\nBisMainEjb</ejb-name>\n <stateless-session-descriptor>\n </stateless-session-descriptor>\n <reference-descriptor>\n </reference-descriptor>\n <enable-call-by-reference>\nTrue</enable-call-by-reference>\n <jndi-name>\nBmsBisMainFacadeHome</jndi-name>\n <local-jndi-name>\nBmsBisMainFacadeLocalHome</local-jndi-name>\n </weblogic-enterprise-bean>\n <!-- To add enterprise beans that you have deployment descriptor info for, add a file to your XDoclet merge directory called weblogic-enterprise-beans.xml that contains the <weblogic-enterprise-bean>\n</weblogic-enterprise-bean>\n markup for those beans. -->\n </weblogic-ejb-jar>\n");
        data.getTransXmlInfo().put("jboss.xml","<jboss>\n <enterprise-beans>\n <session>\n <ejb-name>\nBisMainEjb</ejb-name>\n <jndi-name>\nBmsBisMainFacadeHome</jndi-name>\n <local-jndi-name>\nBmsBisMainFacadeLocalHome</local-jndi-name>\n <call-by-value>\nfalse</call-by-value>\n </session>\n </enterprise-beans>\n </jboss>\n");

        data.getTransFileList().add("bin/ejb_xml/bisEjb/META-INF/jboss.xml");
        data.getTransFileList().add("bin/ejb_xml/comDelEjb/META-INF/jboss.xml");
        data.getTransFileList().add("bin/ejb_xml/comEjb/META-INF/jboss.xml");
        
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
