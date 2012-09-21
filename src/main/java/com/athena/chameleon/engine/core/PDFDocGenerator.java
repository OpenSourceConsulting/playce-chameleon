package com.athena.chameleon.engine.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXBElement;

import org.springframework.stereotype.Component;

import com.athena.chameleon.common.utils.MessageUtil;
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.web.upload.vo.Upload;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
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
	 * PDF Title Page 구성
	 *
	 * @param doc
	 * @param writer
	 */
	public static void setTitleMainPage(Document doc, PdfWriter writer, PDFCommonEventHalper event, Upload upload) throws Exception {
	    
	    Font fnTitle = new Font(bfKorean, 20, Font.BOLD);
	    Font fnLabel = new Font(bfKorean, 11, Font.BOLD);
	    Font fnText  = new Font(bfKorean, 11);
	    LineSeparator UNDERLINE = new LineSeparator(1, 80, null, Element.ALIGN_CENTER, -5);
	    doc.newPage();
	    doc.add(Chunk.NEWLINE); 

	    event.setTitleFlag(true);
        
	    int toc = writer.getPageNumber();
        Image img = Image.getInstance(PDFDocGenerator.class.getResource("/image/title.gif"));
        img.setAlignment(Element.ALIGN_CENTER);
	    img.scalePercent(80, 80);
	    doc.add(img);
	    
	    Paragraph titlePh = new Paragraph(MessageUtil.getMessage("pdf.message.main.title"), fnTitle);
        titlePh.setAlignment(Element.ALIGN_CENTER);
        titlePh.setSpacingBefore(50);
        titlePh.setSpacingAfter(30);
	    doc.add(titlePh);
	    
	    doc.add(UNDERLINE);
	    
	    PdfPTable t1 = new PdfPTable(2);
	    t1.setSpacingBefore(20);
	    t1.setWidths(new int[]{100, 300});
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
        t2.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        
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
    public static void setChapterSectionTOC(Document doc, PdfWriter writer, PDFCommonEventHalper event) throws Exception {
        
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
