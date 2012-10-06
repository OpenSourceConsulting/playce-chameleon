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
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.EjbRecommend;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.EnterpriseBeans;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.Jboss;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.JndiName;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.Method;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.MethodAttributes;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.Session;
import com.athena.chameleon.engine.utils.JaxbUtils;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class WeblogicEjbJarXMLParser extends Parser {

	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analyzer.parser.Parser#parse(java.io.File, com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition)
	 */
	@Override
	public Object parse(File file, AnalyzeDefinition analyzeDefinition) {
		this.analyzeDefinition = analyzeDefinition;
		
		PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
		EjbRecommend ejbRecommend = new EjbRecommend();
		
        try {
        	ejbRecommend = new EjbRecommend();
    		ejbRecommend.setItem(file.getName());
    		ejbRecommend.setTransFlag(false);
    		ejbRecommend.setLocation(removeTempDir(file.getParent()));
    		ejbRecommend.setContents(fileToString(file.getAbsolutePath()));
    		
    		metadataDefinition.getEjbRecommendList().add(ejbRecommend);
        } catch (IOException e) {
            logger.error("IOException has occurred.", e);
        }
        
    	Object obj = null;
    	
    	try {
        	// weblogic-ejb-jar v9_0
			obj = ((JAXBElement<?>)JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0.WeblogicEjbJarType.class.getPackage().getName(), file)).getValue();
    	} catch (JAXBException e1) {
	    	try {
	        	// weblogic-ejb-jar v8_1
        		removeDoctype(file);
				obj = JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1.WeblogicEjbJar.class.getPackage().getName(), file);
				rewrite(file, ejbRecommend.getContents());
	    	} catch (JAXBException e2) {
				logger.error("JAXBException has occurred.", e2);
			} catch (IOException e2) {
				logger.error("IOException has occurred.", e2);
			}
    	}
		
		// jboss.xml 변환 생성
    	try {
    		if(obj != null) {
    			Jboss jboss = generateJbossXML(obj);
    			String xmlData = JaxbUtils.marshal(Jboss.class.getPackage().getName(), jboss, "<!DOCTYPE jboss PUBLIC \"-//JBoss//DTD JBOSS 4.0//EN\" \"http://www.jboss.org/j2ee/dtd/jboss_4_2.dtd\">").replaceAll(" standalone=\"yes\"", "").replaceAll(" standalone=\"true\"", "");
    			rewrite(new File(file.getParentFile(), "jboss.xml"), xmlData);

            	ejbRecommend = new EjbRecommend();
        		ejbRecommend.setItem("jboss.xml");
        		ejbRecommend.setTransFlag(true);
        		ejbRecommend.setLocation(removeTempDir(file.getParent()));
        		ejbRecommend.setContents(xmlData);
        		
        		metadataDefinition.getEjbRecommendList().add(ejbRecommend);
        		metadataDefinition.getEjbTransFileList().add(ejbRecommend.getLocation() + File.separator + "jboss.xml");
    		}
		} catch (JAXBException e) {
			logger.error("JAXBException has occurred.", e);
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		}
        
    	return obj;
	}//end of parse()
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param obj
	 */
	private Jboss generateJbossXML(Object obj) {
		Jboss jboss = null;
		EnterpriseBeans enterpriseBeans = null;
		Session session = null;
		JndiName jndiName = null;
		MethodAttributes methodAttributes = null;
		
		if(obj instanceof com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0.WeblogicEjbJarType) {
			com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0.WeblogicEjbJarType weblogicEjbJarType = (com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0.WeblogicEjbJarType)obj;
			List<com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0.WeblogicEnterpriseBeanType> weblogicEnterpriseBeanList = weblogicEjbJarType.getWeblogicEnterpriseBean();
			if(weblogicEnterpriseBeanList.size() > 0) {
				jboss = new Jboss();
				enterpriseBeans = new EnterpriseBeans();
			
				for(com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v9_0.WeblogicEnterpriseBeanType weblogicEnterpriseBean : weblogicEnterpriseBeanList) {
					session = new Session();
					
					if(weblogicEnterpriseBean.getEjbName() != null) {
						session.setEjbName(weblogicEnterpriseBean.getEjbName().getValue());
					}
					
					if(weblogicEnterpriseBean.getJndiName() != null) {
						jndiName = new JndiName();
						jndiName.setvalue(weblogicEnterpriseBean.getJndiName().getValue());
						session.setJndiName(jndiName);
					}
					
					if(weblogicEnterpriseBean.getLocalJndiName() != null) {
						session.setLocalJndiName(weblogicEnterpriseBean.getLocalJndiName().getValue());
					}
					
					session.setCallByValue("false");
					session.setClustered("False");
					
					if(weblogicEnterpriseBean.getTransactionDescriptor() != null) {
						methodAttributes = new MethodAttributes();
						Method method = new Method();
						
						method.setMethodName("*");
						method.setTransactionTimeout(weblogicEnterpriseBean.getTransactionDescriptor().getTransTimeoutSeconds().getValue().toString());
						methodAttributes.getMethod().add(method);
					}
					
					enterpriseBeans.getSessionOrEntityOrMessageDriven().add(session);
				}
				
				jboss.setEnterpriseBeans(enterpriseBeans);
			}
		} else {
			com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1.WeblogicEjbJar weblogicEjbJar = (com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1.WeblogicEjbJar)obj;
			List<com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1.WeblogicEnterpriseBean> weblogicEnterpriseBeanList = weblogicEjbJar.getWeblogicEnterpriseBean();
			if(weblogicEnterpriseBeanList.size() > 0) {
				jboss = new Jboss();
				enterpriseBeans = new EnterpriseBeans();
			
				for(com.athena.chameleon.engine.entity.xml.ejbjar.weblogic.v8_1.WeblogicEnterpriseBean weblogicEnterpriseBean : weblogicEnterpriseBeanList) {
					session = new Session();
					
					if(weblogicEnterpriseBean.getEjbName() != null) {
						session.setEjbName(weblogicEnterpriseBean.getEjbName());
					}
					
					if(weblogicEnterpriseBean.getJndiName() != null) {
						jndiName = new JndiName();
						jndiName.setvalue(weblogicEnterpriseBean.getJndiName());
						session.setJndiName(jndiName);
					}
					
					if(weblogicEnterpriseBean.getLocalJndiName() != null) {
						session.setLocalJndiName(weblogicEnterpriseBean.getLocalJndiName());
					}
					
					session.setCallByValue("false");
					session.setClustered("False");
					
					if(weblogicEnterpriseBean.getTransactionDescriptor() != null) {
						methodAttributes = new MethodAttributes();
						Method method = new Method();
						
						method.setMethodName("*");
						method.setTransactionTimeout(weblogicEnterpriseBean.getTransactionDescriptor().getTransTimeoutSeconds());
						methodAttributes.getMethod().add(method);
					}
					
					enterpriseBeans.getSessionOrEntityOrMessageDriven().add(session);
				}
				
				jboss.setEnterpriseBeans(enterpriseBeans);
			}
		}
		
		return jboss;
	}//end of generateJbossXML()

}
//end of WeblogicEjbJarXMLParser.java