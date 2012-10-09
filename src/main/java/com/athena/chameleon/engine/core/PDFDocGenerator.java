package com.athena.chameleon.engine.core;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBElement;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Component;

import com.athena.chameleon.common.utils.MessageUtil;
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.ClassAnalyze;
import com.athena.chameleon.engine.entity.pdf.CommonAnalyze;
import com.athena.chameleon.engine.entity.pdf.Dependency;
import com.athena.chameleon.engine.entity.pdf.EjbRecommend;
import com.athena.chameleon.engine.entity.pdf.FileSummary;
import com.athena.chameleon.engine.entity.pdf.FileType;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.entity.upload.Upload;
import com.athena.chameleon.engine.utils.PDFWriterUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component("pdfDocGenerator")
public class PDFDocGenerator {

    public static BaseFont bfKorean;
    
    static {
        try {
            bfKorean = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    //retrun type 추후 변경 예정
    String delimiter = "\n";
    
    /**
     * 
     * PDF File을 생성
     * 
     * @param filePath PDF 파일이 생설될 File Path
     *  
     */
    public static void createPDF(String filePath, Upload upload, PDFMetadataDefinition data) throws Exception{

        if(data == null) 
            return;
        
        //PDF 파일 생성
        Document pdf = new Document(PageSize.A4, 50, 50, 70, 65); 
        PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream(filePath));
        writer.setLinearPageMode();
        
        //Page Event 생성(목차 및 머리말, 꼬리말 생성을 위한 event)
        PDFCommonEventHelper event = new PDFCommonEventHelper();
        writer.setPageEvent(event);
        
        pdf.open();
        int cNum = 1;
        
        SAXBuilder builder = new SAXBuilder();
        
        //chapter 목록 xml 호출
        File listXml = new File(PDFDocGenerator.class.getResource("/xml/chapter.xml").getFile());
        org.jdom2.Document listDoc = builder.build(listXml);
        
        for(Element chapterE : listDoc.getRootElement().getChildren()) {
            
            if(chapterE.getAttributeValue("option") != null) {
                
                String option = chapterE.getAttributeValue("option");
                
                //source 파일이 들어왔을 경우 출력
                if(option.equals("zip") && data.getZipDefinition() != null) {
                    
                    addChapterForDynamic(writer, chapterE, cNum, pdf, builder, data.getZipDefinition(), upload);
                    cNum++;
                
                //deploy 파일이 들어왔을 경우 출력
                } else if(option.equals("deploy") && (upload.getDeploySrc() != null && upload.getDeploySrc().getFileItem().getSize() > 0)) {

                    addChapterForDynamic(writer, chapterE, cNum, pdf, builder, data.getZipDefinition(), upload);
                    cNum++;

                //.ear 파일이 들어왔을 경우 출력
                } else if(option.equals("ear") && data.getEarDefinition() != null) {

                    addChapterForDynamic(writer, chapterE, cNum, pdf, builder, data, upload);
                    cNum++;

                //.war 파일이 들어왔을 경우 출력
                } else if(option.equals("war") && data.getEarDefinition() == null && data.getWarDefinitionMap() != null) {
                    Iterator iterator = data.getWarDefinitionMap().entrySet().iterator();
                    
                    if (iterator.hasNext()) {
                        Entry entry = (Entry)iterator.next();
                        addChapterForDynamic(writer, chapterE, cNum, pdf, builder, (AnalyzeDefinition)entry.getValue(), upload);
                        cNum++;
                    }
                 
                //.jar 파일이 들어왔을 경우 출력
                } else if(option.equals("jar") && data.getEarDefinition() == null && data.getJarDefinitionMap() != null) {
                    Iterator iterator = data.getJarDefinitionMap().entrySet().iterator();
                    
                    if (iterator.hasNext()) {
                        Entry entry = (Entry)iterator.next();
                        addChapterForDynamic(writer, chapterE, cNum, pdf, builder, (AnalyzeDefinition)entry.getValue(), upload);
                        cNum++;
                    }
                    
                //PDFMetadataDefinition에 들어있는 data가 필요할 경우
                } else if(option.equals("root")) {
                    
                    addChapterForDynamic(writer, chapterE, cNum, pdf, builder, data, upload);
                    cNum++;
                    
                //변환 was 에 따라 출력되는 chapter 
                } else if(option.equals(upload.getAfterWas())) {
                    
                    addChapter(writer, chapterE, cNum, pdf, builder);
                    cNum++;
                }
                
            } else {
                addChapter(writer, chapterE, cNum, pdf, builder);
                cNum++;
            }
        }

        setLastPageInfo(pdf, writer, cNum);
        setChapterSectionTOC(pdf, writer, event);
        setTitleMainPage(pdf, writer, event, upload);
        pdf.close();
    }
    
    /**
     * 
     * 결과값이 동적으로 변하는 Chatper Data 생성 (data class : AnalyzeDefinition)
     * 
     * @param writer
     * @param chapterE
     * @param cNum
     * @param pdf
     * @param builder
     * @param data
     * @param upload
     * @throws Exception
     */
    public static void addChapterForDynamic(PdfWriter writer, Element chapterE, int cNum, Document pdf, SAXBuilder builder, AnalyzeDefinition data, Upload upload) throws Exception {
        
    	//chapter 정보 xml 호출
        File chapterXml = new File(PDFDocGenerator.class.getResource(chapterE.getText()).getFile());
        org.jdom2.Document chapterDoc = builder.build(chapterXml);
        
        Element root = chapterDoc.getRootElement();
        
        //동적으로 결과값이 생성되는 정보 setting
        setDynamicSection(root, data, upload);
        
        //chapter 생성
        Chapter chapter = PDFWriterUtil.getChapter(root.getAttributeValue("title"), cNum);
            
        PDFWriterUtil.setElement(writer, chapter, root);
        
        pdf.add(chapter);
    }

    /**
     * 결과값이 동적으로 변하는 Chatper Data 생성 (data class : PDFMetadataDefinition)
     * 
     * @param writer
     * @param chapterE
     * @param cNum
     * @param pdf
     * @param builder
     * @param data
     * @param upload
     * @throws Exception
     */
    public static void addChapterForDynamic(PdfWriter writer, Element chapterE, int cNum, Document pdf, SAXBuilder builder, PDFMetadataDefinition data, Upload upload) throws Exception {
        
    	//chapter 정보 xml 호출
        File chapterXml = new File(PDFDocGenerator.class.getResource(chapterE.getText()).getFile());
        org.jdom2.Document chapterDoc = builder.build(chapterXml);
        
        Element root = chapterDoc.getRootElement();
        
        //동적으로 결과값이 생성되는 정보 setting
        setDynamicSection(root, data, upload);
        
        //chapter 생성
        Chapter chapter = PDFWriterUtil.getChapter(root.getAttributeValue("title"), cNum);
            
        PDFWriterUtil.setElement(writer, chapter, root);
        
        pdf.add(chapter);
    }
    
    /**
     * 결과값이 동적으로 변하는 Data setting (data class : PDFMetadataDefinition)
     * 
     * @param root
     * @param rootData
     * @param upload
     * @throws Exception
     */
    public static void setDynamicSection(Element root, PDFMetadataDefinition rootData, Upload upload) throws Exception {
        
        List<Element> childs = new ArrayList<Element>();
        AnalyzeDefinition data = rootData.getEarDefinition();
        
        setDynamicSection(root, data, upload);
        
        for(Element e : root.getChildren()) {
            
            if(e.getName().equals("section")) {
            	if(e.getChild("war_child_deploy") != null) {
            		//ear 하위 Web Applications 정보
                    childs = setChildDeployData(rootData, upload, "war");
                } else if(e.getChild("jar_child_deploy") != null) {
                	//ear 하위 EJB Applications 정보
                    childs = setChildDeployData(rootData, upload, "jar");
                } else if(e.getChild("trans_application_xml_info") != null) {
                	//마이그레이션 권고안 - application Deployment Descriptor 정보
                    childs = setTransXmlData(rootData, upload, "application");
                } else if(e.getChild("trans_web_xml_info") != null) {
                	//마이그레이션 권고안 - Web Deployment Descriptor 정보
                    childs = setTransXmlData(rootData, upload, "web");
                } else if(e.getChild("trans_ejb_xml_info") != null) {
                	//마이그레이션 권고안 - EJB Deployment Descriptor 정보
                    childs = setTransXmlData(rootData, upload, "ejb");
                }
            }
            
            for(Element child : childs) {
                e.addContent(child);
            }
            childs = new ArrayList<Element>();
        }
    }

    /**
     * 결과값이 동적으로 변하는 Data setting (data class : AnalyzeDefinition)
     * 
     * @param root
     * @param data
     * @param upload
     */
    public static void setDynamicSection(Element root, AnalyzeDefinition data, Upload upload) {
        
        List<Element> childs = new ArrayList<Element>();
        int index = 0;
        for(Element e : root.getChildren()) {
            if(e.getName().equals("section")) {
            	// 하위 노드가 Section 일 경우 호출 
                setDynamicSection(e, data, upload);
            } else if(e.getName().equals("file_summary")) {
            	// 소스 파일 요약
                childs = setFileSummary(data);
            } else if(e.getName().equals("pattern_servlet")) {
            	// Servlet 상속 클래스
                childs = setPatternData(data, "servlet");
            } else if(e.getName().equals("pattern_ejb")) {
            	// EJB 상속 클래스
                childs = setPatternData(data, "ejb");
            } else if(e.getName().equals("dependency_java")) {
            	// Java 의존성
                childs = setDependencyData(data, "java");
            } else if(e.getName().equals("dependency_jsp")) {
            	// jsp 의존성
                childs = setDependencyData(data, "jsp");
            } else if(e.getName().equals("dependency_property")) {
            	// Properties 의존성
                childs = setDependencyData(data, "property");
            } else if(e.getName().equals("dependency_class")) {
            	// Class 의존성
                childs = setDependencyData(data, "class");
            } else if(e.getName().equals("jsp_analyze_result")) {
            	// JSP 소스 파일 분석 결과
                childs = setJspAnalyzeData(data, upload);
            } else if(e.getName().equals("deploy_application_text")) {
            	// 디플로이 애플리케이션 개요
                childs = setDeployApplicationText(data, upload);
                index = 1;
             } else if(e.getName().equals("web_xml_info")) {
            	 // 웹 어플리케이션 디스크립터 정보
                childs = setApplicationData(data, upload);
             } else if(e.getName().equals("jar_xml_info")) {
            	// EJB 어플리케이션 디스크립터 정보
                childs = setApplicationData(data, upload);
             } else if(e.getName().equals("lib_list")) {
            	 // lib 구성 내용
                childs = setLibData(data, upload, "");
             } else if(e.getName().equals("delete_lib_list")) {
            	// 삭제된 lib 구성 내용
                childs = setLibData(data, upload, "D");
             } else if(e.getName().equals("class_info")) {
            	 // classes 구성 정보
                childs = setClassData(data, upload);
             } else if(e.getName().equals("application_list")) {
            	 // 엔터프라이즈 어플리케이션 디스크립터 목록
                childs = setApplicationListData(data, upload);
                index = 2;
             } else if(e.getName().equals("application_info")) {
            	// 엔터프라이즈 어플리케이션 디스크립터 정보
                childs = setApplicationData(data, upload);
             } else if(e.getName().equals("ejb_application_list")) {
            	// 엔터프라이즈 어플리케이션의 EJB 단위 모듈 정보
                childs = setEjbApplicationData(data, upload);
             }
        }
        
        //index가 0이 아닐경우 존재할 경우 해당 index 위치에 하위 노드 생성 
        if(index == 0) {
            root.addContent(childs);
        } else {
            root.addContent(index, childs);
            index = 0;
        }
        
    }

    /**
     * 
     * 챕터 생성
     * 
     * @param writer
     * @param chapterE
     * @param cNum
     * @param pdf
     * @param builder
     * @throws Exception
     */
    public static void addChapter(PdfWriter writer, Element chapterE, int cNum, Document pdf, SAXBuilder builder) throws Exception {
        
    	//chapter 정보 xml 호출
        File chapterXml = new File(PDFDocGenerator.class.getResource(chapterE.getText()).getFile());
        org.jdom2.Document chapterDoc = builder.build(chapterXml);
        
        //chapter 생성
        Element root = chapterDoc.getRootElement();
        Chapter chapter = PDFWriterUtil.getChapter(root.getAttributeValue("title"), cNum);
            
        PDFWriterUtil.setElement(writer, chapter, root);
        
        pdf.add(chapter);
    }
    
    /**
     * 소스 파일 요약 정보 생성
     * 
     * @param data
     * @return
     */
    public static List<Element> setFileSummary(AnalyzeDefinition data) {

        List<Element> childs = new ArrayList<Element>();
        if(data.getFileSummaryMap() != null){
            Element child1 = new Element("table");
            Element child2 = new Element("chart");
            child2.setAttribute("title", MessageUtil.getMessage("pdf.message.chart.title.file_count"));
            
            Element childE1 = new Element("header");
            Element childE2 = new Element("row");
            Element childE3;
            
            child1.setAttribute("size", "4");
            
            childE1.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.extension")));
            childE1.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.file_count")));
            childE1.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.source_encoding")));
            childE1.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.target_encoding")));
        
            for (FileType fileType : FileType.values()) {
                FileSummary summary = data.getFileSummaryMap().get(fileType);
                if(summary != null) {
                    childE2.addContent(new Element("col").setText(fileType.toString()));
                    childE2.addContent(new Element("col").setText(String.valueOf(summary.getFileCount())));
                    childE2.addContent(new Element("col").setText(summary.getSourceEncoding()));
                    childE2.addContent(new Element("col").setText(summary.getTargetEncoding()));
                    
                    childE3 = new Element("data");
                    childE3.addContent(new Element("column").setText(fileType.toString()));
                    childE3.addContent(new Element("value").setText(String.valueOf(summary.getFileCount())));
                    child2.addContent(childE3);        
                }
            }
            
            child1.addContent(childE1);
            child1.addContent(childE2);
            
            childs.add(child1);
            childs.add(child2);
        }
        return childs;
    }

    /**
     * 상속 클래스 정보 생성
     * (type : servlet, ejb)
     * 
     * @param data
     * @param type
     * @return
     */
    public static List<Element> setPatternData(AnalyzeDefinition data, String type) {

        List<Element> childs = new ArrayList<Element>();
        List<CommonAnalyze> dataList = new ArrayList<CommonAnalyze>();
        if(type.equals("servlet"))
            dataList = data.getServletExtendsList();
        else if(type.equals("ejb"))
            dataList = data.getEjbExtendsList();
        
        if(dataList.size() > 0) {
            Element child = new Element("table");
            Element childE1 = new Element("header");
            Element childE2 = new Element("row");
            
            child.setAttribute("size", "2");
            
            Element col = new Element("col");
            col.setAttribute("width", "150");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.file_name")));
            
            col = new Element("col");
            col.setAttribute("width", "300");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.location")));
        
            for(CommonAnalyze comm : dataList) {
                childE2.addContent(new Element("col").setText(comm.getItem()));
                childE2.addContent(new Element("col").setText(comm.getLocation()));
            }
            
            child.addContent(childE1);
            child.addContent(childE2);
            
            childs.add(child);
        }
        
        return childs;
    }

    /**
     * 의존성 정보 생성
     * (type : java, jsp, property, class)
     * 
     * @param data
     * @param type
     * @return
     */
    public static List<Element> setDependencyData(AnalyzeDefinition data, String type) {

        List<Element> childs = new ArrayList<Element>();
        List<Dependency> dataList = new ArrayList<Dependency>();
        if(type.equals("java"))
            dataList = data.getJavaDependencyList();
        else if(type.equals("jsp"))
            dataList = data.getJspDependencyList();
        else if(type.equals("property"))
            dataList = data.getPropertyDependencyList();
        else if(type.equals("class"))
            dataList = data.getClassDependencyList();
        
        if(dataList.size() > 0) {
            
            Element text;
            for(Dependency comm : dataList) {
                text = new Element("text");
                text.setText(comm.getFileName());
                text.setAttribute("padding", "23");
                childs.add(text);
                
                Iterator iterator = comm.getDependencyStrMap().entrySet().iterator();
                StringBuffer buf = new StringBuffer();
                while (iterator.hasNext()) {
                    Entry entry = (Entry)iterator.next();
                    buf.append(entry.getKey() + " " + entry.getValue()+"\n");
                }
                childs.add(new Element("box").setText(buf.toString()+"\n"));
            }
            
        }
        
        return childs;
    }

    /**
     * JSP 소스 파일 분석 결과 생성
     * 
     * @param data
     * @param upload
     * @return
     */
    public static List<Element> setJspAnalyzeData(AnalyzeDefinition data, Upload upload) {

        List<Element> childs = new ArrayList<Element>();
        Map<String, Integer> dataMap = data.getJspDirectiveMap();
        
        if(dataMap != null) {
            int jspFileCount = 0;
            if(data.getFileSummaryMap() != null)
                jspFileCount = ((FileSummary)data.getFileSummaryMap().get(FileType.JSP)).getFileCount();
            
            childs.add(new Element("text").setText(MessageUtil.getMessage("pdf.message.jsp_summary.info.text", upload.getProjectNm(), String.valueOf(jspFileCount))));
            Element child = new Element("table");
            Element childE1 = new Element("header");
            Element childE2 = new Element("row");
            
            child.setAttribute("size", "2");
            
            Element col = new Element("col");
            col.setAttribute("width", "330");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.directive")));
            
            col = new Element("col");
            col.setAttribute("width", "70");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.file_count")));
        
            Iterator iterator = dataMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry entry = (Entry)iterator.next();
                
                childE2.addContent(new Element("col").setText(String.valueOf(entry.getKey())));
                childE2.addContent(new Element("col").setText(String.valueOf(entry.getValue())));
                
            }
            child.addContent(childE1);
            child.addContent(childE2);
            
            childs.add(child);
        }
        
        return childs;
    }

    /**
     * 디플로이 애플리케이션 개요 생성
     * 
     * @param data
     * @param upload
     * @return
     */
    public static List<Element> setDeployApplicationText(AnalyzeDefinition data, Upload upload) {

        List<Element> childs = new ArrayList<Element>();
        String fileName = upload.getDeploySrc().getFileItem().getName().replaceAll("\\\\", "/");
        fileName = fileName.substring(fileName.lastIndexOf("/")+1, fileName.length());
        childs.add(new Element("text").setText(MessageUtil.getMessage("pdf.message.deploy_application.info.text", fileName)));
        return childs;
        
    }

    /**
     * lib 구성 내용 생성
     * (type : D - 삭제된 lib 구성 정보)
     * 
     * @param data
     * @param upload
     * @param type
     * @return
     */
    public static List<Element> setLibData(AnalyzeDefinition data, Upload upload, String type) {

        List<Element> childs = new ArrayList<Element>();
        List<String> dataList;
        if(type.equals("D"))
            dataList = data.getDeleteLibraryList();
        else
            dataList = data.getLibraryList();
        
        if(dataList.size() > 0) {
            Element child = new Element("table");
            Element childE1 = new Element("header");
            Element childE2 = new Element("row");
            
            child.setAttribute("size", "1");
            child.setAttribute("width", "150");
            
            Element col = new Element("col");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.file_name")));
            
            for(String text : dataList) {
                childE2.addContent(new Element("col").setText(text));
            }
            child.addContent(childE1);
            child.addContent(childE2);
            
            childs.add(child);
        }
        
        return childs;
    }

    /**
     * classes 구성 정보 생성
     * 
     * @param data
     * @param upload
     * @return
     */
    public static List<Element> setClassData(AnalyzeDefinition data, Upload upload) {

        List<Element> childs = new ArrayList<Element>();
        List<ClassAnalyze> dataList = data.getClassesConstList();
        
        if(dataList.size() > 0) {
            
            Element child, childE1, childE2, col, text;
            for (ClassAnalyze comm : dataList) {
                
                text = new Element("text");
                text.setText(MessageUtil.getMessage("pdf.message.class_summary.label.class_name") + comm.getClassName());
                childs.add(text);
                
                child = new Element("table");
                childE1 = new Element("header");
                childE2 = new Element("row");
                
                child.setAttribute("size", "2");
                
                col = new Element("col");
                col.setAttribute("width", "100");
                childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.subject")));
                
                col = new Element("col");
                col.setAttribute("width", "300");
                childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.contents")));
            
                childE2.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.supper_class")));
                childE2.addContent(new Element("col").setText(comm.getSuperClassesStr()));
                childE2.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.interfaces")));
                childE2.addContent(new Element("col").setText(comm.getInterfacesStr()));
                childE2.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.class_modifier")));
                childE2.addContent(new Element("col").setText(comm.getClassModifier()));
                childE2.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.final_class")));
                childE2.addContent(new Element("col").setText(comm.isFinalClass() == null ? "" : String.valueOf(comm.isFinalClass())));
                childE2.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.fields")));
                childE2.addContent(new Element("col").setText(comm.getFiledListStr()));
                childE2.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.methods")));
                childE2.addContent(new Element("col").setText(comm.getMethodListStr()));
                
                child.addContent(childE1);
                child.addContent(childE2);
                
                childs.add(child);    
            }
        }
        
        return childs;
    }

    /**
     * 엔터프라이즈 어플리케이션 디스크립터 목록 생성
     * 
     * @param data
     * @param upload
     * @return
     */
    public static List<Element> setApplicationListData(AnalyzeDefinition data, Upload upload) {

        List<Element> childs = new ArrayList<Element>();
        List<CommonAnalyze> dataList = data.getDescripterList();
        
        if(dataList.size() > 0) {
            Element child = new Element("table");
            Element childE1 = new Element("header");
            Element childE2 = new Element("row");
            
            child.setAttribute("size", "2");
            
            Element col = new Element("col");
            col.setAttribute("width", "150");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.file_name")));
            
            col = new Element("col");
            col.setAttribute("width", "300");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.location")));
            
            for(CommonAnalyze comm : dataList) {
                childE2.addContent(new Element("col").setText(comm.getItem()));
                childE2.addContent(new Element("col").setText(comm.getLocation()+File.separator+comm.getItem()));
            }
            child.addContent(childE1);
            child.addContent(childE2);
            
            childs.add(child);
        }
        
        return childs;
    }

    /**
     * 엔터프라이즈 어플리케이션 디스크립터 정보 생성
     * 
     * @param data
     * @param upload
     * @return
     */
    public static List<Element> setApplicationData(AnalyzeDefinition data, Upload upload) {

        List<Element> childs = new ArrayList<Element>();
        List<CommonAnalyze> dataList = data.getDescripterList();
        
        if(dataList.size() > 0) {
            
            Element text;
            for(CommonAnalyze comm : dataList) {
                text = new Element("text");
                text.setText(comm.getItem());
                text.setAttribute("padding", "23");
                childs.add(text);
                childs.add(new Element("box").setText(comm.getContents()+"\n"));
            }

        }
        return childs;
    }

    /**
     * 엔터프라이즈 어플리케이션의 EJB 단위 모듈 정보 생성
     * 
     * @param data
     * @param upload
     * @return
     */
    public static List<Element> setEjbApplicationData(AnalyzeDefinition data, Upload upload) {

        List<Element> childs = new ArrayList<Element>();
        Iterator iterator = data.getEjbApplicationMap().entrySet().iterator();
        
        while (iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            
            Element child, childE1, childE2, col;
            Element section = new Element("section");
            section.setAttribute("title", String.valueOf(entry.getKey()));

            child = new Element("table");
            childE1 = new Element("header");
            childE2 = new Element("row");
            
            child.setAttribute("size", "2");
            
            col = new Element("col");
            col.setAttribute("width", "150");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.contents")));
            
            col = new Element("col");
            col.setAttribute("width", "300");
            childE1.addContent(col.setText(MessageUtil.getMessage("pdf.message.table.header.type")));
            
            List<CommonAnalyze> dataList = (List<CommonAnalyze>) entry.getValue();
            for(CommonAnalyze comm : dataList) {
                childE2.addContent(new Element("col").setText(comm.getItem()));
                childE2.addContent(new Element("col").setText(comm.getContents()));
            }
            
            child.addContent(childE1);
            child.addContent(childE2);
            section.addContent(new Element("text").setText(MessageUtil.getMessage("pdf.message.ejb_application.info.text", String.valueOf(entry.getKey()))));
            section.addContent(child);
            childs.add(section);
        }
        
        return childs;
    }

    /**
     * 마이그레이션 권고안 Deployment Descriptor 정보 생성
     * (type : application, web, ejb)
     * 
     * @param data
     * @param upload
     * @param type
     * @return
     */
    public static List<Element> setTransXmlData(PDFMetadataDefinition data, Upload upload, String type) {

        List<Element> childs = new ArrayList<Element>();
        List<EjbRecommend> dataList = null;
        List<String> transFileList = null;
        if(type.equals("application")) {
            dataList = data.getApplicationRecommendList();
            transFileList = data.getAppTransFileList();
        } else if(type.equals("web")) {
            dataList = data.getWebRecommendList();
            transFileList = data.getWebTransFileList();
        } else if(type.equals("ejb")) {
            dataList = data.getEjbRecommendList();
            transFileList = data.getEjbTransFileList();
        }
        
        Element section; 
        for(EjbRecommend comm : dataList) {
            
            section = new Element("section");
            if(comm.isTransFlag())
                section.setAttribute("title", MessageUtil.getMessage("pdf.message.advice.title.trans_complete_file", comm.getItem()));
            else
                section.setAttribute("title", MessageUtil.getMessage("pdf.message.advice.title.trans_target_file", comm.getItem()));
            
            section.addContent(new Element("text").setText(comm.getLocation()+File.separator+comm.getItem()));
            section.addContent(new Element("box").setText(comm.getContents()));
            
            childs.add(section);
        }
        
        childs.add(new Element("text").setAttribute("padding","23").setText(MessageUtil.getMessage("pdf.message.table.header.trans_target_file_list")));
        
        if(transFileList.size() > 0) {
            
            Element child, childE1, childE2;
            child = new Element("table");
            childE1 = new Element("header");
            childE2 = new Element("row");
            
            child.setAttribute("size", "1");
            
            childE1.addContent(new Element("col").setText(MessageUtil.getMessage("pdf.message.table.header.file_name")));
            
            for(String s : transFileList) {
                childE2.addContent(new Element("col").setText(s));
            }
            child.addContent(childE1);
            child.addContent(childE2);
            childs.add(child);
        }
        
        return childs;
    }

    /**
     * ear 하위 Applications 정보
     * (type : war - Web, jar - EJB)
     * 
     * @param rootData
     * @param upload
     * @param type
     * @return
     * @throws Exception
     */
    public static List<Element> setChildDeployData(PDFMetadataDefinition rootData, Upload upload, String type) throws Exception {
        
        List<Element> childs = new ArrayList<Element>();
        List<Element> childs2;
        Iterator iterator = null;
        if(type.equals("war")) {
            iterator = rootData.getWarDefinitionMap().entrySet().iterator();
        } else if(type.equals("jar")) {
            iterator = rootData.getJarDefinitionMap().entrySet().iterator();
        }
        
        Element section;
        File childXml = null;
        while (iterator.hasNext()) {
            childs2 = new ArrayList<Element>();
            Entry entry = (Entry)iterator.next();
            AnalyzeDefinition data = (AnalyzeDefinition)entry.getValue();
            
            if(type.equals("war")) {
                childXml = new File(PDFDocGenerator.class.getResource("/xml/war_application.xml").getFile());
            } else if(type.equals("jar")) {
                childXml = new File(PDFDocGenerator.class.getResource("/xml/jar_application.xml").getFile());
            }
            
            org.jdom2.Document chapterDoc = new SAXBuilder().build(childXml);
            
            Element root = chapterDoc.getRootElement();
            
            section = new Element("section");
            section.setAttribute("title", String.valueOf(entry.getKey()));
            
            for(Element e : root.getChildren()) {
                childs2.add(e.clone());
            }
            
            for(Element e : childs2) {
                section.addContent(e);
            }
            setDynamicSection(section, data, upload);
            
            childs.add(section);
        }
        
        return childs;
    }
    
    /**
     * 
     * PDF Title Page 구성
     *
     * @param doc
     * @param writer
     */
    public static void setTitleMainPage(Document doc, PdfWriter writer, PDFCommonEventHelper event, Upload upload) throws Exception {
        
        Font fnTitle = new Font(bfKorean, 20, Font.BOLD);
        Font fnLabel = new Font(bfKorean, 11, Font.BOLD);
        Font fnText  = new Font(bfKorean, 11);
        LineSeparator UNDERLINE = new LineSeparator(1, 80, null, com.itextpdf.text.Element.ALIGN_CENTER, -5);
        doc.newPage();
        doc.add(Chunk.NEWLINE); 

        event.setTitleFlag(true);
        
        int toc = writer.getPageNumber();
        Image img = Image.getInstance(PDFDocGenerator.class.getResource("/image/title.gif"));
        img.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        img.scalePercent(80, 80);
        doc.add(img);
        
        Paragraph titlePh = new Paragraph(MessageUtil.getMessage("pdf.message.main.title"), fnTitle);
        titlePh.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        titlePh.setSpacingBefore(50);
        titlePh.setSpacingAfter(30);
        doc.add(titlePh);
        
        doc.add(UNDERLINE);
        
        PdfPTable t1 = new PdfPTable(2);
        t1.setSpacingBefore(20);
        t1.setWidths(new int[]{110, 290});
        t1.getDefaultCell().setBorder(0);
        t1.getDefaultCell().setFixedHeight(32); 
        
        t1.addCell(new Phrase(MessageUtil.getMessage("pdf.message.main.label.project_name"), fnLabel));
        t1.addCell(new Phrase(upload.getProjectNm(), fnText));
        t1.addCell(new Phrase(MessageUtil.getMessage("pdf.message.main.label.department"), fnLabel));
        t1.addCell(new Phrase(upload.getDepartment(), fnText));
        t1.addCell(new Phrase(MessageUtil.getMessage("pdf.message.main.label.focus_name"), fnLabel));
        t1.addCell(new Phrase(MessageUtil.getMessage("pdf.message.main.text.focus_name"), fnText));
        t1.addCell(new Phrase(MessageUtil.getMessage("pdf.message.main.label.product"), fnLabel));
        t1.addCell(new Phrase(MessageUtil.getMessage("pdf.message.main.text.product", upload.getBeforeWas(), upload.getAfterWas()), fnText));
        doc.add(t1);
        
        doc.add(UNDERLINE);

        Paragraph executedPh = new Paragraph(MessageUtil.getMessage("pdf.message.main.label.executed"), fnLabel);
        executedPh.setSpacingBefore(30);
        executedPh.setSpacingAfter(15);
        executedPh.setIndentationLeft(50);
        doc.add(executedPh);

        PdfPTable t2 = new PdfPTable(2);
        t2.getDefaultCell().setFixedHeight(28);
        t2.getDefaultCell().setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        
        t2.getDefaultCell().setBackgroundColor(new BaseColor(217, 217, 217));
        t2.addCell(new Phrase(MessageUtil.getMessage("pdf.message.main.label.owner"), fnLabel));
        t2.addCell(new Phrase(MessageUtil.getMessage("pdf.message.main.label.project_role"), fnLabel));
        t2.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
        t2.addCell(new Phrase(upload.getPerson(), fnText));
        t2.addCell(new Phrase(upload.getOrgRole(), fnText));
        doc.add(t2);
        
        doc.newPage();
        int total = writer.reorderPages(null);
        
        int[] order = new int[total];
        for (int i = 0; i < total; i++) {
            order[i] = i + toc;
            if (order[i] > total)
                order[i] -= total;
        }
        // apply the new order
        writer.reorderPages(order);

    }

    /**
     * 목차 생성
     *
     * @param doc 
     * @param writer 
     * @param event
     * @throws Exception
     */
    public static void setChapterSectionTOC(Document doc, PdfWriter writer, PDFCommonEventHelper event) throws Exception {
        
        doc.newPage();
        event.setPagingFlag(false);
        
        Paragraph title = new Paragraph(MessageUtil.getMessage("pdf.message.toc.title"), new Font(bfKorean, 13, Font.BOLD));
        title.setSpacingAfter(8);
        doc.add(title);
        
        int toc = writer.getPageNumber();
        for(Paragraph p : event.titles)
            doc.add(p);
        
        doc.newPage();
        int total = writer.reorderPages(null);
        
        int[] order = new int[total];
        for (int i = 0; i < total; i++) {
            order[i] = i + toc;
            if (order[i] > total)
                order[i] -= total;
        }
        // apply the new order
        writer.reorderPages(order);

    }
    
    public static void setLastPageInfo(Document doc, PdfWriter writer, int cNum) throws Exception {
        Chapter chapter = PDFWriterUtil.getChapter(MessageUtil.getMessage("pdf.message.chapter.confirm.title"), cNum);
        
        Paragraph preP = new Paragraph();
        preP.add(new Phrase(MessageUtil.getMessage("pdf.message.chapter.confirm.prepared1"), PDFWriterUtil.fnNormalBold));
        preP.add(new Phrase("                                                           ", new Font(bfKorean, 10, Font.UNDERLINE)));
        preP.setSpacingBefore(15);
        preP.setSpacingAfter(2);
        chapter.add(preP);
        
        preP = new Paragraph();
        preP.add(new Phrase(MessageUtil.getMessage("pdf.message.chapter.confirm.prepared2"), PDFWriterUtil.fnNormal));
        preP.setIndentationLeft(65);
        preP.setSpacingAfter(14);
        chapter.add(preP);

        preP = new Paragraph();
        preP.add(new Phrase(MessageUtil.getMessage("pdf.message.chapter.confirm.approved1"), PDFWriterUtil.fnNormalBold));
        preP.add(new Phrase("                                                           ", new Font(bfKorean, 10, Font.UNDERLINE)));
        preP.setSpacingBefore(15);
        preP.setSpacingAfter(2);
        chapter.add(preP);
        
        preP = new Paragraph();
        preP.add(new Phrase(MessageUtil.getMessage("pdf.message.chapter.confirm.approved2"), PDFWriterUtil.fnNormal));
        preP.setIndentationLeft(65);
        preP.setSpacingAfter(14);
        chapter.add(preP);

        cNum++;
        doc.add(chapter);
        
        chapter = PDFWriterUtil.getChapter(MessageUtil.getMessage("pdf.message.chapter.appendices.title"), cNum);
        Section section = PDFWriterUtil.getSection(chapter, MessageUtil.getMessage("pdf.message.chapter.appendices.label1"));
        
        Chunk url = new Chunk(MessageUtil.getMessage("pdf.message.chapter.appendices.text1"), PDFWriterUtil.fnURL);
        url.setAction(new PdfAction(new URL(MessageUtil.getMessage("pdf.message.chapter.appendices.text1"))));

        preP = new Paragraph(url);
        preP.setIndentationLeft(23);
        preP.setSpacingAfter(14);
        section.add(preP);
        
        section = PDFWriterUtil.getSection(chapter, MessageUtil.getMessage("pdf.message.chapter.appendices.label2"));
        
        doc.add(chapter);
    }
    
    /**
     * 
     * 압축을 푼 파일 목록을 PDF 출력 형식으로 변환
     *
     * @param fileList 압축을 푼 파일목록
     * @return
     */
    public String getMigrationFileList(List<MigrationFile> fileList) {
        
        if(fileList == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        buf.append("*****Migration File List*****"+delimiter);
        
        for(MigrationFile file : fileList) {
            buf.append(file.getFileName()+delimiter);
        }
        return buf.toString();
    }
    
    /**
     * 
     * 압축을 푼 파일 중 마이그레이션 대상 파일 목록을 PDF 출력 형식으로 변환 (file.getLineMap()이 존재할 경우 마이그레이션 대상)
     *
     * @param fileList 압축을 푼 파일목록
     * @return
     */
    public String getMigrationFileCheckLine(List<MigrationFile> fileList) {
        
        if(fileList == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        buf.append("*****Migration File Check*****"+delimiter);
        
        for(MigrationFile file : fileList) {

            if(file.getLineMap() != null) {
                Iterator<Entry<Integer, String>> iterator = file.getLineMap().entrySet().iterator();
    
                while (iterator.hasNext()) {
                    Entry<Integer, String> entry = (Entry<Integer, String>)iterator.next();
                    buf.append("["+file.getFileName()+"] " + entry.getKey() + " line : " + entry.getValue() + delimiter);
                    
                }
            }
        }
        return buf.toString();
    }
    
    /**
     * 
     * web.xml pasing 정보를 PDF 출력 형식으로 변환
     *
     * @param webApp web.xml root Pojo
     * @return
     * @throws Exception
     */
    public String getWebXmlSettingInfo(Object webApp) throws Exception{
        
        if(webApp == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        buf.append("*****Web.xml Setting Info*****"+delimiter);
        
        if(webApp instanceof JAXBElement<?>) {
            webApp = ((JAXBElement<?>) webApp).getValue();
            
            buf.append(getFilterMappingType(getWebXmlElementEntity(webApp, "FilterMapping")));
            buf.append(getServletMappingType(getWebXmlElementEntity(webApp, "ServletMapping")));
            buf.append(getErrorPageType(getWebXmlElementEntity(webApp, "ErrorPage")));
            buf.append(getWelcomeFileListType(getWebXmlElementEntity(webApp, "WelcomeFileList")));
            buf.append(getResourceRefType(getWebXmlElementEntity(webApp, "ResourceRef")));
            
        } else {

            for(Object o : (List) webApp.getClass().getMethod("getFilterMapping").invoke(webApp))
                buf.append(getFilterMappingType(o));
            
            for(Object o : (List) webApp.getClass().getMethod("getServletMapping").invoke(webApp))
                buf.append(getServletMappingType(o));
            
            for(Object o : (List) webApp.getClass().getMethod("getErrorPage").invoke(webApp))
                buf.append(getErrorPageType(o));
            
            try {
                for(Object o : (List) webApp.getClass().getMethod("getWelcomeFileList").invoke(webApp))
                    buf.append(getWelcomeFileListType(o));
            } catch(ClassCastException c) {
                buf.append(getWelcomeFileListType(webApp.getClass().getMethod("getWelcomeFileList").invoke(webApp)));
            }
            
            for(Object o : (List) webApp.getClass().getMethod("getResourceRef").invoke(webApp))
                buf.append(getResourceRefType(o));
            
        }
        return buf.toString();
    }

    /**
     * 
     * application.xml pasing 정보를 PDF 출력 형식으로 변환
     *
     * @param app application.xml root Pojo
     * @return
     * @throws Exception
     */
    public String getApplicationXmlSettingInfo(Object app) throws Exception{

        if(app == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        buf.append("*****application.xml Setting Info*****"+delimiter);
        
        if(app instanceof JAXBElement<?>) {
            app = ((JAXBElement<?>) app).getValue();
            
            List moduleList = (List) app.getClass().getMethod("getModule").invoke(app);
            
            if(moduleList != null) {
                buf.append(" [module type]"+delimiter);
                
                for(Object module : moduleList) {
                    Object moduleEjb = module.getClass().getMethod("getEjb").invoke(module);
                    Object moduleJava = module.getClass().getMethod("getJava").invoke(module);
                    Object moduleWeb = module.getClass().getMethod("getWeb").invoke(module);
                    
                    if(moduleEjb != null)
                        buf.append("ejb : " + getValue(moduleEjb) + delimiter);
                    else if(moduleJava != null) 
                        buf.append("java : " + getValue(moduleJava) + delimiter);
                    else if(moduleWeb != null) 
                        buf.append("web url : " + getValue(moduleWeb.getClass().getMethod("getWebUri").invoke(moduleWeb)) + delimiter +
                                "web context root : " + getValue(moduleWeb.getClass().getMethod("getContextRoot").invoke(moduleWeb)) + delimiter);
                }
            }
            
            List securityRoleList = (List) app.getClass().getMethod("getSecurityRole").invoke(app);
            if(securityRoleList != null) {
                    buf.append(" [Security Role type]"+delimiter);
                    
                for(Object securityRole : securityRoleList) {
                    for(Object desc : (List) securityRole.getClass().getMethod("getDescription").invoke(securityRole)) 
                        buf.append("discription : " + getValue(desc) + delimiter);
                    
                    buf.append("role name : " + getValue(securityRole.getClass().getMethod("getRoleName").invoke(securityRole)) + delimiter);
                }
            }
        } else {

            List moduleList = (List) app.getClass().getMethod("getModule").invoke(app);
            
            if(moduleList != null) {
                buf.append(" [module type]"+delimiter);
                
                for(Object module : moduleList) {
                    
                        for(Object o : (List) module.getClass().getMethod("getConnectorOrEjbOrJavaOrWeb").invoke(module)) {
                            
                            if(o.getClass().toString().indexOf("Ejb") > -1) 
                                buf.append("ejb : " + getValue(o) + delimiter);
                            else if(o.getClass().toString().indexOf("Java") > -1)
                                buf.append("java : " + getValue(o) + delimiter);
                            else if(o.getClass().toString().indexOf("Web") > -1)
                                buf.append("web url : " + getValue(o.getClass().getMethod("getWebUri").invoke(o)) + delimiter +
                                        "web context root : " + getValue(o.getClass().getMethod("getContextRoot").invoke(o)) + delimiter);
                        }
                }
            }
            
            List securityRoleList = (List) app.getClass().getMethod("getSecurityRole").invoke(app);
            if(securityRoleList != null) {
                    buf.append(" [Security Role type]"+delimiter);
                    
                for(Object securityRole : securityRoleList) {
                    buf.append("discription : " + getValue(securityRole.getClass().getMethod("getDescription").invoke(securityRole)) + delimiter);
                    buf.append("role name : " + getValue(securityRole.getClass().getMethod("getRoleName").invoke(securityRole)) + delimiter);
                }
            }
        }
        return buf.toString();
    }

    /**
     * 
     * ejb 관련 pasing 정보를 PDF 출력 형식으로 변환
     *
     * @param ejb ejb.xml root Pojo
     * @param weblogic weblogic-ejb.xml root Pojo
     * @param jeus jeus-ejb.xml root Pojo
     * @return
     * @throws Exception
     */
    public String getEjbXmlSettingInfo(Object ejb, Object weblogic, Object jeus) throws Exception{

        if(ejb == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        buf.append("*****ejb-jar.xml Setting Info*****"+delimiter);

        Object enterpriseBean = ejb.getClass().getMethod("getEnterpriseBeans").invoke(ejb);
        
        if(enterpriseBean != null) {
            
            for(Object o : (List) enterpriseBean.getClass().getMethod("getSessionOrEntityOrMessageDriven").invoke(enterpriseBean)){
            
                if(o.getClass().toString().indexOf("Session") > -1) {
                    Class cls = o.getClass();
                    String ejbName = getValue(cls.getMethod("getEjbName").invoke(o));
                    String homeName = getValue(cls.getMethod("getHome").invoke(o));
                    String remoteName = getValue(cls.getMethod("getRemote").invoke(o));
                    String ejbclass = getValue(cls.getMethod("getEjbClass").invoke(o));
                    String transaction = getValue(cls.getMethod("getTransactionType").invoke(o));
                    
                    String[] param = new String[]{ejbName, ejbName, homeName, remoteName, ejbclass, 
                        getValue(cls.getMethod("getSessionType").invoke(o)), transaction};
                        
                    buf.append(MessageUtil.getMessage("pdf.message.ejbjar.session", param));
                    
                    if(weblogic != null) {
                        
                        for(Object o2 : (List) weblogic.getClass().getMethod("getWeblogicEnterpriseBean").invoke(weblogic)){
                            
                            Class cls2 = o2.getClass();
                            if(ejbName.equals(getValue(cls2.getMethod("getEjbName").invoke(o2)))) {
                                String[] param2 = new String[]{homeName, remoteName.substring(remoteName.lastIndexOf(".")+1, remoteName.length()), getValue(cls2.getMethod("getJndiName").invoke(o2)), 
                                        transaction.toUpperCase(), ejbclass.substring(ejbclass.lastIndexOf(".")+1, ejbclass.length()), remoteName.substring(remoteName.lastIndexOf(".")+1, remoteName.length())};
                                
                                    buf.append(MessageUtil.getMessage("pdf.message.jebjar.weblogic", param2));
                            }
                            
                        }
                        
                    }
                    
                    if(jeus != null) {

                        Object beanList = jeus.getClass().getMethod("getBeanlist").invoke(jeus);
                        for(Object o2 : (List) beanList.getClass().getMethod("getJeusBean").invoke(beanList)){
                            
                            Class cls2 = o2.getClass();
                            if(ejbName.equals(getValue(cls2.getMethod("getEjbName").invoke(o2)))) {
                                String[] param2 = new String[]{homeName, remoteName.substring(remoteName.lastIndexOf(".")+1, remoteName.length()), getValue(cls2.getMethod("getExportName").invoke(o2)), 
                                        transaction.toUpperCase(), ejbclass.substring(ejbclass.lastIndexOf(".")+1, ejbclass.length()), remoteName.substring(remoteName.lastIndexOf(".")+1, remoteName.length())};
                                
                                buf.append(MessageUtil.getMessage("pdf.message.jebjar.weblogic", param2));
                            }
                            
                        }
                        
                    }
                } else if(o.getClass().toString().indexOf("MessageDriven") > -1) {
                    //pdf 출력 양식 나오면 작업
                } else if(o.getClass().toString().indexOf("Entity") > -1) {
                  //pdf 출력 양식 나오면 작업
                }
            }
        }
        return buf.toString();
    }
    
    /**
     * 
     * web.xml 안에 있는 element 가져오기
     *
     * @param webApp web.xml 최상위 객체
     * @param elementEntity 가져올 elementEntity
     * @return Object
     */
    private Object getWebXmlElementEntity(Object webApp, String elementName) throws Exception {

        if(webApp == null)
            return null;
        
        List<JAXBElement<?>> elementList = (List<JAXBElement<?>>) webApp.getClass().getMethod("getDescriptionAndDisplayNameAndIcon").invoke(webApp);
        Object entity = null;
        
        for(JAXBElement<?> element : elementList) {
            if(element.getValue().getClass().toString().indexOf(elementName) > -1) {
                entity = element.getValue();
            }
        }
        return entity;
    }

    /**
     * 
     * web.xml 의 filter mapping 정보를 PDF 출력 형식으로 변환 
     *
     * @param filter
     * @return
     * @throws Exception
     */
    private String getFilterMappingType(Object filter) throws Exception {
        
        if(filter == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        Class filterCls = filter.getClass();
        buf.append("[filter mapping]"+delimiter);
        buf.append("filter name : " + getValue(filterCls.getMethod("getFilterName").invoke(filter))+delimiter);
        
        for(Object o : (List)filterCls.getMethod("getUrlPatternOrServletName").invoke(filter)) {
            Class cls = o.getClass();
            if(cls.toString().indexOf("UrlPattern") > -1)
                buf.append("url pattern : " + getValue(o)+delimiter);
            else if(cls.toString().indexOf("ServletName") > -1)
                buf.append("url pattern : " + getValue(o)+delimiter);
                
        }
        return buf.toString();
    }

    /**
     * 
     * web.xml 의 servlet mapping 정보를 PDF 출력 형식으로 변환
     *
     * @param servlet
     * @return
     * @throws Exception
     */
    private String getServletMappingType(Object servlet) throws Exception {

        if(servlet == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        Class Cls = servlet.getClass();
        buf.append("[servlet mapping type]"+delimiter);
        buf.append("servlet name : " + getValue(Cls.getMethod("getServletName").invoke(servlet))+delimiter);
        
        try {
            for(Object pattern : (List) Cls.getMethod("getUrlPattern").invoke(servlet)) {
                buf.append("url pattern : " + getValue(pattern)+delimiter);
            }
        } catch(ClassCastException c) {
            buf.append("url pattern : " + getValue(Cls.getMethod("getUrlPattern").invoke(servlet))+delimiter);
        }
        return buf.toString();
    }

    /**
     * 
     * web.xml 의 error page 정보를 PDF 출력 형식으로 변환
     *
     * @param errorPage
     * @return
     * @throws Exception
     */
    private String getErrorPageType(Object errorPage) throws Exception {
        
        if(errorPage == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        Class cls = errorPage.getClass();
        
        buf.append("[error page]"+delimiter);
        buf.append("error code : " + getValue(cls.getMethod("getErrorCode").invoke(errorPage))+delimiter);
        buf.append("location   : " + getValue(cls.getMethod("getLocation").invoke(errorPage))+delimiter);
        
        return buf.toString();
    }

    /**
     * 
     * web.xml 의 welcome file 정보를 PDF 출력 형식으로 변환
     *
     * @param welcomeFile
     * @return
     * @throws Exception
     */
    private String getWelcomeFileListType(Object welcomeFile) throws Exception {
        
        if(welcomeFile == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        Class cls = welcomeFile.getClass();
        
        buf.append("[welcome file list]"+delimiter);
        for(Object o : (List)cls.getMethod("getWelcomeFile").invoke(welcomeFile))
            buf.append("welcome file : " + getValue(o)+delimiter);
        
        return buf.toString();
    }

    /**
     * 
     * web.xml 의 resource reference 정보를 PDF 출력 형식으로 변환
     *
     * @param resourceRef
     * @return
     * @throws Exception
     */
    private String getResourceRefType(Object resourceRef) throws Exception {

        if(resourceRef == null)
            return "";
        
        StringBuffer buf = new StringBuffer();
        Class cls = resourceRef.getClass();
        
        buf.append("[resource reference type]"+delimiter);
        for(Object desc : (List)cls.getMethod("getDescription").invoke(resourceRef)) 
            buf.append("discription : " + getValue(desc)+delimiter);
        
        buf.append("resource ref name : " + getValue(cls.getMethod("getResRefName").invoke(resourceRef))+delimiter);
        buf.append("resource type : " + getValue(cls.getMethod("getResType").invoke(resourceRef))+delimiter);
        buf.append("resource auth : " + getValue(cls.getMethod("getResAuth").invoke(resourceRef))+delimiter);
        
        return buf.toString();
    }
    
    /**
     * 
     * object의 value값 반환
     *
     * @param o value를 반환할 object 객체
     * @return String
     * @throws Exception
     */
    private String getValue(Object o) throws Exception {
        
        if(o == null)
            return "";
        
        try { 
            if(o instanceof java.lang.String)
                return String.valueOf(o);
            else
                return String.valueOf(o.getClass().getMethod("getValue").invoke(o));
                        
        } catch(NoSuchMethodException se1) {
            return String.valueOf(o.getClass().getMethod("getvalue").invoke(o));
        }
    }
    
}
