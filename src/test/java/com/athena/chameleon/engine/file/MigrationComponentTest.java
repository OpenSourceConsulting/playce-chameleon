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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.SystemOutLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.athena.chameleon.common.utils.MessageUtil;
import com.athena.chameleon.engine.core.MigrationComponent;
import com.athena.chameleon.engine.entity.file.MigrationFile;
import com.athena.chameleon.engine.entity.xml.application.ApplicationType;
import com.athena.chameleon.engine.entity.xml.application.ModuleType;
import com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.EjbJar;
import com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.EnterpriseBeans;
import com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.EjbJarType;
import com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.EntityBeanType;
import com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.MessageDrivenBeanType;
import com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.SessionBeanType;
import com.athena.chameleon.engine.entity.xml.j2ee.SecurityRoleType;
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

import freemarker.template.utility.StringUtil;

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
            
        	if(webapp == null)
        		return;
        	
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
            fail("web xml Pasing Error");
        }
    }

    //application file pasing
    public void applicationXmlPasing(ApplicationType app) {
        
        try {
        	if(app == null)
        		return;
        	
        	List<ModuleType> moduleList = app.getModule();
        	
        	StringBuffer buf = new StringBuffer();
        	buf.append("\n[application.xml] \n");
        	if(moduleList != null) 
        		buf.append(" [module type] \n");
        		
        	for(ModuleType module : moduleList) {
        		if(module.getEjb() != null)
        			buf.append("ejb : " + module.getEjb().getValue() + "\n");
        		else if(module.getJava() != null) 
        			buf.append("java : " + module.getJava().getValue() + "\n");
        		else if(module.getWeb() != null) 
        			buf.append("web url : " + module.getWeb().getWebUri().getValue() + "\n" +
        					"web context root : " + module.getWeb().getContextRoot().getValue() + "\n");
        	}
        	
        	List<SecurityRoleType> securityRoleList = app.getSecurityRole();
        	if(securityRoleList != null) 
        		buf.append(" [Security Role type] \n");
        		
        	for(SecurityRoleType securityRole : securityRoleList) {
        		for(com.athena.chameleon.engine.entity.xml.j2ee.DescriptionType desc : securityRole.getDescription()) 
        			buf.append("discription : " + desc.getValue() + "\n");
        		
        		buf.append("role name : " + securityRole.getRoleName().getValue() + "\n");
        	}
        	
        	if (logger.isDebugEnabled()) {
        		logger.debug(buf.toString());
        	}
        	
        } catch(Exception e) {
            e.printStackTrace();
            fail("application xml Pasing Error");
        }
    }

    //ejb file pasing
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void ejbXmlPasing(Object ejb, Object weblogic, Object jeus) {
        
        try {
        	
        	if(ejb != null) {
        	    Object enterpriseBean = ejb.getClass().getMethod("getEnterpriseBeans").invoke(ejb);
                
        	    if(enterpriseBean != null) {
        	        
            		for(Object o : (List) enterpriseBean.getClass().getMethod("getSessionOrEntityOrMessageDriven").invoke(enterpriseBean)){
            		
            		    if(o.getClass().toString().indexOf("Session") > -1) {
            		        Class cls = o.getClass();
            		        String ejbName = getValue(cls.getMethod("getEjbName").invoke(o));
            		        
                		    String[] param = new String[7];
                            param[0] = ejbName;
                            param[1] = ejbName;
                            param[2] = getValue(cls.getMethod("getHome").invoke(o));
                            param[3] = getValue(cls.getMethod("getRemote").invoke(o));
                            param[4] = getValue(cls.getMethod("getEjbClass").invoke(o));
                            param[5] = getValue(cls.getMethod("getSessionType").invoke(o));
                            param[6] = getValue(cls.getMethod("getTransactionType").invoke(o));
                            
                            if (logger.isDebugEnabled()) {
                                logger.debug(MessageUtil.getMessage("pdf.message.ejbjar.session", param));
                            }
                            
                            if(weblogic != null) {
                                
                                for(Object o2 : (List) weblogic.getClass().getMethod("getWeblogicEnterpriseBean").invoke(weblogic)){
                                    
                                    Class cls2 = o2.getClass();
                                    if(ejbName.equals(getValue(cls2.getMethod("getEjbName").invoke(o2)))) {
                                        String[] param2 = new String[6];
                                        param2[0] = getValue(cls.getMethod("getHome").invoke(o));
                                        param2[1] = getValue(cls.getMethod("getRemote").invoke(o));
                                        param2[2] = getValue(cls2.getMethod("getJndiName").invoke(o2));
                                        param2[3] = getValue(cls.getMethod("getTransactionType").invoke(o)).toUpperCase();
                                        param2[4] = getValue(cls.getMethod("getEjbClass").invoke(o)).substring(getValue(cls.getMethod("getEjbClass").invoke(o)).lastIndexOf(".")+1, getValue(cls.getMethod("getEjbClass").invoke(o)).length());
                                        param2[5] = getValue(cls.getMethod("getRemote").invoke(o)).substring(getValue(cls.getMethod("getRemote").invoke(o)).lastIndexOf(".")+1, getValue(cls.getMethod("getRemote").invoke(o)).length());
                                        
                                        if (logger.isDebugEnabled()) {
                                            logger.debug(MessageUtil.getMessage("pdf.message.jebjar.weblogic", param2));
                                        }
                                        
                                        /*
                                        
                                        String message2 = "만약 EJB3.0 유형으로 변경을 하고 싶으시다면 아래의 내용을 참고하십시오.\n" +
                                                " 단계 0 : 이클립스의 EJB 프로젝트를 생서하십시오.\n" +
                                                " 단계 1 : {0} 소스를 삭제하십시오.\n" +
                                                " 단계 2 : Remote 클래스를 다음이 순서로 변경하십시오.\n" +
                                                "        public interface {1} extends Remote 부분의 extends Remote를 삭제하십시오.\n" +
                                                "        비즈니스 메소드의 throws RemoteException 을 삭제하십시오.\n" +
                                                "        클래스 레벨의 어노테이션으로 다음을 추가하십시오.\n" +
                                                "       @Stateless(mappedName=\"{2}\")\n" +
                                                "       @TransactionManagement(value=TransactionManagementType.CONTAINER)\n" +
                                                "       public class {3} extends {4} {\n" +
                                                "       }\n" +
                                                " 단계 3 : 위의 생성된 코드를 컴파일하신 후 압축하십시오.";
                                        */      
                                        
                                        
                                    }
                                    
                                }
                                
                            }
                            
                            if(jeus != null) {

                            	Object beanList = jeus.getClass().getMethod("getBeanlist").invoke(jeus);
                            	for(Object o2 : (List) beanList.getClass().getMethod("getJeusBean").invoke(beanList)){
                                    
                                    Class cls2 = o2.getClass();
                                    if(ejbName.equals(getValue(cls2.getMethod("getEjbName").invoke(o2)))) {
                                        String[] param2 = new String[6];
                                        param2[0] = getValue(cls.getMethod("getHome").invoke(o));
                                        param2[1] = getValue(cls.getMethod("getRemote").invoke(o));
                                        param2[2] = getValue(cls2.getMethod("getExportName").invoke(o2));
                                        param2[3] = getValue(cls.getMethod("getTransactionType").invoke(o)).toUpperCase();
                                        param2[4] = getValue(cls.getMethod("getEjbClass").invoke(o)).substring(getValue(cls.getMethod("getEjbClass").invoke(o)).lastIndexOf(".")+1, getValue(cls.getMethod("getEjbClass").invoke(o)).length());
                                        param2[5] = getValue(cls.getMethod("getRemote").invoke(o)).substring(getValue(cls.getMethod("getRemote").invoke(o)).lastIndexOf(".")+1, getValue(cls.getMethod("getRemote").invoke(o)).length());
                                        
                                        if (logger.isDebugEnabled()) {
                                            logger.debug(MessageUtil.getMessage("pdf.message.jebjar.weblogic", param2));
                                        }
                                        
                                        /*
                                        
                                        String message2 = "만약 EJB3.0 유형으로 변경을 하고 싶으시다면 아래의 내용을 참고하십시오.\n" +
                                                " 단계 0 : 이클립스의 EJB 프로젝트를 생서하십시오.\n" +
                                                " 단계 1 : {0} 소스를 삭제하십시오.\n" +
                                                " 단계 2 : Remote 클래스를 다음이 순서로 변경하십시오.\n" +
                                                "        public interface {1} extends Remote 부분의 extends Remote를 삭제하십시오.\n" +
                                                "        비즈니스 메소드의 throws RemoteException 을 삭제하십시오.\n" +
                                                "        클래스 레벨의 어노테이션으로 다음을 추가하십시오.\n" +
                                                "       @Stateless(mappedName=\"{2}\")\n" +
                                                "       @TransactionManagement(value=TransactionManagementType.CONTAINER)\n" +
                                                "       public class {3} extends {4} {\n" +
                                                "       }\n" +
                                                " 단계 3 : 위의 생성된 코드를 컴파일하신 후 압축하십시오.";
                                        */      
                                        
                                        
                                    }
                                    
                                }
                                
                            }
            		    } else if(o.getClass().toString().indexOf("MessageDriven") > -1) {
            		        
            			} else if(o.getClass().toString().indexOf("Entity") > -1) {
            				
            			}
            		}
        	    }
        		
        	}
        	
        } catch(Exception e) {
            e.printStackTrace();
            fail("application xml Pasing Error");
        }
    }
    
    public String getValue(Object o) throws Exception {
        try { 
        	if(o instanceof java.lang.String)
        		return (String) o;
        	else
        		return (String) o.getClass().getMethod("getValue").invoke(o);
        				
        } catch(NoSuchMethodException se) {
            return (String) o.getClass().getMethod("getvalue").invoke(o);
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