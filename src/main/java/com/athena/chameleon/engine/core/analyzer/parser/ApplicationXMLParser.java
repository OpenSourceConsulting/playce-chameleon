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
 * Sang-cheon Park	2012. 10. 3.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.CommonAnalyze;
import com.athena.chameleon.engine.utils.JaxbUtils;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ApplicationXMLParser extends Parser {

	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analyzer.parser.Parser#parse(java.io.File, com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition)
	 */
	@Override
	public Object parse(File file, AnalyzeDefinition analyzeDefinition) {
		this.analyzeDefinition = analyzeDefinition;
		
		try {
            CommonAnalyze commonAnalyze = new CommonAnalyze();
            commonAnalyze.setItem(file.getName());
            commonAnalyze.setLocation(file.getPath());
            commonAnalyze.setContents(fileToString(file.getAbsolutePath()));
            
            analyzeDefinition.getDescripterList().add(commonAnalyze);
        } catch (IOException e) {
            logger.error("IOException has occurred.", e);
        }
		
    	Object obj = null;
    	
    	try {
        	// http://java.sun.com/xml/ns/javaee/application_5.xsd
			obj = checkApplication(((JAXBElement<?>)JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.application.v1_5.ApplicationType.class.getPackage().getName(), file)).getValue(), file.getParentFile().getParentFile());
    	} catch (JAXBException e1) {
	    	try {
	        	// http://java.sun.com/xml/ns/j2ee/application_1_4.xsd
				obj = checkApplication(((JAXBElement<?>)JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.application.v1_4.ApplicationType.class.getPackage().getName(), file)).getValue(), file.getParentFile().getParentFile());
			} catch (JAXBException e2) {
	        	try {
	            	// http://java.sun.com/dtd/application_1_3.dtd
					obj = checkApplication(JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.application.v1_3.Application.class.getPackage().getName(), file), file.getParentFile().getParentFile());
				} catch (JAXBException e3) {
					logger.error("JAXBException has occurred.", e3);
				}
			}
    	}
    	
		return obj;
	}//end of parse()
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param obj
	 * @param root
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object checkApplication(Object obj, File root) {
		List<File> warFileList = null;
		List<File> jarFileList = null;
		
		if(obj instanceof com.athena.chameleon.engine.entity.xml.application.v1_5.ApplicationType) {
			com.athena.chameleon.engine.entity.xml.application.v1_5.ApplicationType application = (com.athena.chameleon.engine.entity.xml.application.v1_5.ApplicationType)obj;
			
			List<com.athena.chameleon.engine.entity.xml.application.v1_5.ModuleType> moduleList = application.getModule();
			com.athena.chameleon.engine.entity.xml.application.v1_5.WebType webType = null;
			com.athena.chameleon.engine.entity.xml.application.v1_5.PathType pathType = null;
			for(com.athena.chameleon.engine.entity.xml.application.v1_5.ModuleType module : moduleList) {
				webType = module.getWeb();
				pathType = module.getEjb();
				
				if(webType != null) {
					// Web 관련 war 파일이 존재할 경우 분석을 위해 ThreadLocal에 추가한다.
					if ((warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST)) == null) {
						warFileList = new ArrayList<File>();
						ThreadLocalUtil.add(ChameleonConstants.WAR_FILE_LIST, warFileList);
					}
					
					warFileList.add(new File(root, webType.getWebUri().getValue()));
					logger.debug("WAR file detected in application.xml : [{}]", warFileList.get(warFileList.size() - 1));
				}
				
				if(pathType != null) {
					// EJB 관련 jar 파일이 존재할 경우 분석을 위해 ThreadLocal에 추가한다.
					if((jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST)) == null) {
						jarFileList = new ArrayList<File>();
						ThreadLocalUtil.add(ChameleonConstants.JAR_FILE_LIST, jarFileList);
					}
				
					jarFileList.add(new File(root, pathType.getValue()));
					logger.debug("JAR file detected in application.xml : [{}]", jarFileList.get(jarFileList.size() - 1));
				}
			}
			
			return application;
		} else if(obj instanceof com.athena.chameleon.engine.entity.xml.application.v1_4.ApplicationType) {
			com.athena.chameleon.engine.entity.xml.application.v1_4.ApplicationType application = (com.athena.chameleon.engine.entity.xml.application.v1_4.ApplicationType)obj;
			
			List<com.athena.chameleon.engine.entity.xml.application.v1_4.ModuleType> moduleList = application.getModule();
			com.athena.chameleon.engine.entity.xml.application.v1_4.WebType webType = null;
			com.athena.chameleon.engine.entity.xml.application.v1_4.PathType pathType = null;
			for(com.athena.chameleon.engine.entity.xml.application.v1_4.ModuleType module : moduleList) {
				webType = module.getWeb();
				pathType = module.getEjb();
				
				if(webType != null) {
					// Web 관련 war 파일이 존재할 경우 분석을 위해 ThreadLocal에 추가한다.
					if ((warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST)) == null) {
						warFileList = new ArrayList<File>();
						ThreadLocalUtil.add(ChameleonConstants.WAR_FILE_LIST, warFileList);
					}
					
					warFileList.add(new File(root, webType.getWebUri().getValue()));
					logger.debug("WAR file detected in application.xml : [{}]", warFileList.get(warFileList.size() - 1));
				}
				
				if(pathType != null) {
					// EJB 관련 jar 파일이 존재할 경우 분석을 위해 ThreadLocal에 추가한다.
					if((jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST)) == null) {
						jarFileList = new ArrayList<File>();
						ThreadLocalUtil.add(ChameleonConstants.JAR_FILE_LIST, jarFileList);
					}
				
					jarFileList.add(new File(root, pathType.getValue()));
					logger.debug("JAR file detected in application.xml : [{}]", jarFileList.get(jarFileList.size() - 1));
				}
			}
			
			return application;
		} else {
			com.athena.chameleon.engine.entity.xml.application.v1_3.Application application = (com.athena.chameleon.engine.entity.xml.application.v1_3.Application)obj;
			
			List<com.athena.chameleon.engine.entity.xml.application.v1_3.Module> moduleList = application.getModule();
			for(com.athena.chameleon.engine.entity.xml.application.v1_3.Module module : moduleList) {
				List<Object> connectorList = module.getConnectorOrEjbOrJavaOrWeb();
				
				for(Object connector : connectorList) {
					if(connector instanceof com.athena.chameleon.engine.entity.xml.application.v1_3.Web) {
						// Web 관련 war 파일이 존재할 경우 분석을 위해 ThreadLocal에 추가한다.
						if ((warFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.WAR_FILE_LIST)) == null) {
							warFileList = new ArrayList<File>();
							ThreadLocalUtil.add(ChameleonConstants.WAR_FILE_LIST, warFileList);
						}
					
						warFileList.add(new File(root, ((com.athena.chameleon.engine.entity.xml.application.v1_3.Web)connector).getWebUri().getvalue()));
						logger.debug("WAR file detected in application.xml : [{}]", warFileList.get(warFileList.size() - 1));
					} else if(connector instanceof com.athena.chameleon.engine.entity.xml.application.v1_3.Ejb) {
						// EJB 관련 jar 파일이 존재할 경우 분석을 위해 ThreadLocal에 추가한다.
						if((jarFileList = (List<File>) ThreadLocalUtil.get(ChameleonConstants.JAR_FILE_LIST)) == null) {
							jarFileList = new ArrayList<File>();
							ThreadLocalUtil.add(ChameleonConstants.JAR_FILE_LIST, jarFileList);
						}
					
						jarFileList.add(new File(root, ((com.athena.chameleon.engine.entity.xml.application.v1_3.Ejb)connector).getvalue()));
						logger.debug("JAR file detected in application.xml : [{}]", jarFileList.get(jarFileList.size() - 1));
					}
				}
			}
			
			return application;
		}
	}//end of checkApplication()
}
//end of ApplicationXMLParser.java