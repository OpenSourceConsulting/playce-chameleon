package com.athena.chameleon.engine.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.bind.JAXBElement;

import org.springframework.stereotype.Component;

import com.athena.chameleon.common.utils.MessageUtil;
import com.athena.chameleon.engine.entity.file.MigrationFile;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component("pdfDataDefinition")
public class PDFDataDefinition {

	//retrun type 추후 변경 예정
	String delimiter = "\n";
	
	/**
	 * 
	 * @param fileList
	 * @return
	 */
	public String getMigrationFileList(List<MigrationFile> fileList) {
		StringBuffer buf = new StringBuffer();
		buf.append("*****Migration File List*****"+delimiter);
		
		for(MigrationFile file : fileList) {
			buf.append(file.getFileName()+delimiter);
        }
		return buf.toString();
	}
	
	public String getMigrationFileCheckLine(List<MigrationFile> fileList) {
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
	
	public String getWebXmlSettingInfo(Object webApp) throws Exception{
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
			
			for(Object o : (List) webApp.getClass().getMethod("getWelcomeFileList").invoke(webApp))
				buf.append(getWelcomeFileListType(o));
			
			for(Object o : (List) webApp.getClass().getMethod("getResourceRef").invoke(webApp))
				buf.append(getResourceRefType(o));
			
		}
		return buf.toString();
	}

    //application file pasing
	public String getApplicationXmlSettingInfo(Object app) throws Exception{
        
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

    //ejb file pasing
	public String getEjbXmlSettingInfo(Object ejb, Object weblogic, Object jeus) throws Exception{
        
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

    // filter mapping 정보
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

    //servlet mapping 정보
    private String getServletMappingType(Object servlet) throws Exception {

    	if(servlet == null)
    		return "";
        
    	StringBuffer buf = new StringBuffer();
    	Class Cls = servlet.getClass();
        buf.append("[servlet mapping type]"+delimiter);
        buf.append("servlet name : " + getValue(Cls.getMethod("getServletName").invoke(servlet))+delimiter);
        
        for(Object pattern : (List) Cls.getMethod("getUrlPattern").invoke(servlet)) {
            buf.append("url pattern : " + getValue(pattern)+delimiter);
        }
        return buf.toString();
    }

    //error page 정보
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

    //welcome file 정보
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

    //resource reference 정보
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
