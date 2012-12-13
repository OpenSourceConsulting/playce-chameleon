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

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.CommonAnalyze;
import com.athena.chameleon.engine.entity.pdf.ExceptionInfo;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.utils.JaxbUtils;
import com.athena.peacock.engine.common.StackTracer;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class EjbJarXMLParser extends Parser {

	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analyzer.parser.Parser#parse(java.io.File, com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition)
	 */
	@Override
	public Object parse(File file, AnalyzeDefinition analyzeDefinition) {
		Assert.notNull(file, "file cannot be null.");
		Assert.notNull(analyzeDefinition, "analyzeDefinition cannot be null.");
		
		this.analyzeDefinition = analyzeDefinition;
		
        // only zip and jar
        String key = ChameleonConstants.ZIP_ROOT_DIR;
        
        if(StringUtils.isEmpty((String)ThreadLocalUtil.get(key))) {
        	key = ChameleonConstants.JAR_ROOT_DIR;
        }

		CommonAnalyze commonAnalyze = null;
        try {
            commonAnalyze = new CommonAnalyze();
            commonAnalyze.setItem(file.getName());
            commonAnalyze.setLocation(removeTempDir(file.getParent(), key));
            commonAnalyze.setContents(fileToString(file.getAbsolutePath()));
            
            analyzeDefinition.getDescripterList().add(commonAnalyze);
        } catch (IOException e) {
            logger.error("IOException has occurred.", e);
        }
        
    	Object obj = null;

    	try {
        	// ejb-jar v2_1
			obj = checkEjbJar(((JAXBElement<?>)JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.EjbJarType.class.getPackage().getName(), file)).getValue(), removeTempDir(file.getAbsolutePath(), key));
    	} catch (JAXBException e1) {
	    	try {
	        	// ejb-jar v2_0
        		removeDoctype(file);
				obj = checkEjbJar(JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.EjbJar.class.getPackage().getName(), file), removeTempDir(file.getAbsolutePath(), key));
				rewrite(file, commonAnalyze.getContents());
			} catch (JAXBException e2) {
				logger.error("JAXBException has occurred.", e2);
        		location = removeTempDir(file.getAbsolutePath(), key);
        		stackTrace = StackTracer.getStackTrace(e2);
        		comments = "지원되지 않는 버젼의 파일입니다.";
			} catch (IOException e2) {
				logger.error("IOException has occurred.", e2);
        		location = removeTempDir(file.getAbsolutePath(), key);
        		stackTrace = StackTracer.getStackTrace(e2);
        		comments = "파일을 열 수 없습니다.";
			} catch (Exception e2) {
				logger.error("Unhandled Exception has occurred.", e2);
	    		location = removeTempDir(file.getAbsolutePath(), key);
	    		stackTrace = StackTracer.getStackTrace(e2);
	    	} 
    	} catch (Exception e1) {
			logger.error("Unhandled Exception has occurred.", e1);
    		location = removeTempDir(file.getAbsolutePath(), key);
    		stackTrace = StackTracer.getStackTrace(e1);
    	} finally {
			if(StringUtils.isNotEmpty(stackTrace)) {
				exceptionInfo = new ExceptionInfo();
				exceptionInfo.setLocation(location);
				exceptionInfo.setStackTrace(stackTrace);
				exceptionInfo.setComments(comments);
				((PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION)).getExceptionInfoList().add(exceptionInfo);
			}
		}
    	
    	return obj;
	}//end of parse()
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param obj
	 * @param loc
	 * @return
	 */
	private Object checkEjbJar(Object obj, String loc) {
		if(obj instanceof com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.EjbJarType) {
			com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.EjbJarType ejbJar = (com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.EjbJarType)obj;
			com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.EnterpriseBeansType enterpriseBeans = ejbJar.getEnterpriseBeans();
			
			List<Object> beanList = enterpriseBeans.getSessionOrEntityOrMessageDriven();
			
			List<CommonAnalyze> commonAnalyzeList = new ArrayList<CommonAnalyze>();
			CommonAnalyze commonAnalyze = null;
			for(Object bean : beanList) {
				if(bean instanceof com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.SessionBeanType) {
					try {
						commonAnalyze = new CommonAnalyze();
						commonAnalyze.setItem("Home Interface");
						commonAnalyze.setContents(((com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.SessionBeanType)bean).getHome().getValue());
						commonAnalyzeList.add(commonAnalyze);

						commonAnalyze = new CommonAnalyze();
						commonAnalyze.setItem("Remote Interface");
						commonAnalyze.setContents(((com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.SessionBeanType)bean).getRemote().getValue());
						commonAnalyzeList.add(commonAnalyze);

						commonAnalyze = new CommonAnalyze();
						commonAnalyze.setItem("Enterprise Bean Class");
						commonAnalyze.setContents(((com.athena.chameleon.engine.entity.xml.ejbjar.v2_1.SessionBeanType)bean).getEjbClass().getValue());
						commonAnalyzeList.add(commonAnalyze);
					} catch (Exception e) {
		        		location = loc;
		        		stackTrace = StackTracer.getStackTrace(e);
		        		comments = "Session Bean 내에 home / remote / ejb-class 가 모두 존재해야 합니다.";
					}
				}
			}
			
			if(commonAnalyzeList.size() > 0) {
				analyzeDefinition.getEjbApplicationMap().put(analyzeDefinition.getFileName(), commonAnalyzeList);
			}
			
			return ejbJar;
		} else {
			com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.EjbJar ejbJar = (com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.EjbJar)obj;
			com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.EnterpriseBeans enterpriseBeans = ejbJar.getEnterpriseBeans();
			
			List<Object> beanList = enterpriseBeans.getSessionOrEntityOrMessageDriven();
			
			List<CommonAnalyze> commonAnalyzeList = new ArrayList<CommonAnalyze>();
			CommonAnalyze commonAnalyze = null;
			for(Object bean : beanList) {
				if(bean instanceof com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.Session) {
					try {
						commonAnalyze = new CommonAnalyze();
						commonAnalyze.setItem("Home Interface");
						commonAnalyze.setContents(((com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.Session)bean).getHome().getvalue());
						commonAnalyzeList.add(commonAnalyze);

						commonAnalyze = new CommonAnalyze();
						commonAnalyze.setItem("Remote Interface");
						commonAnalyze.setContents(((com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.Session)bean).getRemote().getvalue());
						commonAnalyzeList.add(commonAnalyze);

						commonAnalyze = new CommonAnalyze();
						commonAnalyze.setItem("Enterprise Bean Class");
						commonAnalyze.setContents(((com.athena.chameleon.engine.entity.xml.ejbjar.v2_0.Session)bean).getEjbClass().getvalue());
						commonAnalyzeList.add(commonAnalyze);
					} catch (Exception e) {
		        		location = loc;
		        		stackTrace = StackTracer.getStackTrace(e);
		        		comments = "Session Bean 내에 home / remote / ejb-class 가 모두 존재해야 합니다.";
					}
				}
			}
			
			if(commonAnalyzeList.size() > 0) {
				analyzeDefinition.getEjbApplicationMap().put(analyzeDefinition.getFileName(), commonAnalyzeList);
			}
			
			return ejbJar;
		}
	}//end of checkEjbJar()
}
//end of EjbJarXMLParser.java