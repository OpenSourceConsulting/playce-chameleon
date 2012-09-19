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
import java.io.FileOutputStream;
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

import com.athena.chameleon.engine.core.ChapterSectionTOC;
import com.athena.chameleon.engine.core.MigrationComponent;
import com.athena.chameleon.engine.core.PDFDocGenerator;
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.engine.utils.FileUtil;
import com.athena.chameleon.engine.utils.PDFWriterUtil;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfWriter;

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

        Document pdf = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream(unzipDirPath+File.separator+"test.pdf"));
        writer.setLinearPageMode();
        ChapterSectionTOC tocEvent = new ChapterSectionTOC();
        writer.setPageEvent(tocEvent);
        
        pdf.open();
        
        //HeaderFooter header = new HeaderFooter(phrase1, true);
        //HeaderFooter footer = new HeaderFooter(phrase2, true);
        //pdf.setHeader(header);
        //pdf.setFooter(footer);

        
        Chapter chapter1 = PDFWriterUtil.getChapter("마이그레이션의개요", 1);
        chapter1.add(PDFWriterUtil.getDefault("본 문서의 목적은 정부통합전산센터의 상용"));
        chapter1.add(PDFWriterUtil.getDefault("WAS서버에서 작동되는업무 애플리케이션을 공개SW기반의 "));
        chapter1.add(PDFWriterUtil.getDefault("WAS에서 작동이 되는 변경 작업에 대한 결과 산출물로 툴에의해 자동으로 생성된 보고서입니다."));
        
        Section section1_1 =  PDFWriterUtil.getSection(chapter1, "목표기대치", 1);
        section1_1.add(PDFWriterUtil.getDefault("본자동화도구를활용하여변경된애플리케이션이기존 WAS대비 50% "));
        section1_1.add(PDFWriterUtil.getDefault("작동을목표로하고있습니다"));
        
        Section section1_2 =  PDFWriterUtil.getSection(chapter1, "보고서의범위", 2);
        section1_2.add(PDFWriterUtil.getDefault("본보고서는다음의결과물을포함하고있습니다."));
        
        pdf.add(chapter1);

        Chapter chapter2 = PDFWriterUtil.getChapter("마이그레이션의개요", 2);
        chapter2.add(PDFWriterUtil.getDefault("본 문서의 목적은 정부통합전산센터의 상용"));
        chapter2.add(PDFWriterUtil.getDefault("WAS서버에서 작동되는 업무애플리케이션을 공개 SW기반의 "));
        chapter2.add(PDFWriterUtil.getDefault("WAS에서 작동이 되는 변경작업에 대한 결과 산출물로 툴에 의해 자동으로 생성된 보고서입니다."));
        
        Section section2_1 =  PDFWriterUtil.getSection(chapter2, "목표기대치", 1);
        section2_1.add(PDFWriterUtil.getDefault("본자동화도구를활용하여변경된애플리케이션이기존 WAS대비 50% "));
        section2_1.add(PDFWriterUtil.getDefault("작동을목표로하고있습니다"));
        
        Section section2_2 =  PDFWriterUtil.getSection(chapter2, "보고서의범위", 2);
        section2_2.add(PDFWriterUtil.getDefault("본보고서는다음의결과물을포함하고있습니다."));
        
        pdf.add(chapter2);
        
        PDFWriterUtil.setChapterSectionTOC(pdf, writer, tocEvent);
        
        pdf.close();
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
