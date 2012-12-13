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
 * Sang-cheon Park	2012. 12. 13.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.EjbRecommend;
import com.athena.chameleon.engine.entity.pdf.ExceptionInfo;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.EnterpriseBeans;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.Jboss;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.JndiName;
import com.athena.chameleon.engine.entity.xml.ejbjar.jboss.v5_0.Session;
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
public class IbmEjbJarBndXMIParser extends Parser {

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
		
		PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
		EjbRecommend ejbRecommend = new EjbRecommend();
		
        try {
        	ejbRecommend = new EjbRecommend();
    		ejbRecommend.setItem(file.getName());
    		ejbRecommend.setTransFlag(false);
    		ejbRecommend.setLocation(removeTempDir(file.getParent(), key));
    		ejbRecommend.setContents(fileToString(file.getAbsolutePath()));
    		
    		metadataDefinition.getEjbRecommendList().add(ejbRecommend);
        } catch (IOException e) {
            logger.error("IOException has occurred.", e);
        }
        
        List<String> jndiNameList = new ArrayList<String>();
        
        try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);
			
			// normalize text representation
            doc.getDocumentElement().normalize();
            
            NodeList listClass = doc.getElementsByTagName("ejbBindings");
            Element element = null;
            String jndiName = null;
            for (int i = 0; i < listClass.getLength(); i++) {
            	element = (Element) listClass.item(i);
            	jndiName = element.getAttribute("jndiName");
            	
            	if (StringUtils.isNotEmpty(jndiName)) {
            		jndiNameList.add(jndiName);
            	}
            }            
		} catch (Exception e) {
    		location = removeTempDir(file.getAbsolutePath(), key);
    		stackTrace = StackTracer.getStackTrace(e);
    		comments = "ibm-ejb-jar-bnd.xmi 파일 분석 중 에러가 발생하였습니다.";
		} finally {
			if(StringUtils.isNotEmpty(stackTrace)) {
				exceptionInfo = new ExceptionInfo();
				exceptionInfo.setLocation(location);
				exceptionInfo.setStackTrace(stackTrace);
				exceptionInfo.setComments(comments);
				((PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION)).getExceptionInfoList().add(exceptionInfo);
			}
		}
        
        stackTrace = null;
        
		// jboss.xml 변환 생성
    	try {
    		if(jndiNameList.size() > 0) {
    			Jboss jboss = generateJbossXML(jndiNameList);
    			String xmlData = JaxbUtils.marshal(Jboss.class.getPackage().getName(), jboss, "<!DOCTYPE jboss PUBLIC \"-//JBoss//DTD JBOSS 4.0//EN\" \"http://www.jboss.org/j2ee/dtd/jboss_4_2.dtd\">").replaceAll(" standalone=\"yes\"", "").replaceAll(" standalone=\"true\"", "");
    			rewrite(new File(file.getParentFile(), "jboss.xml"), xmlData);

            	ejbRecommend = new EjbRecommend();
        		ejbRecommend.setItem("jboss.xml");
        		ejbRecommend.setTransFlag(true);
        		ejbRecommend.setLocation(removeTempDir(file.getParent(), key));
        		ejbRecommend.setContents(xmlData);
        		
        		metadataDefinition.getEjbRecommendList().add(ejbRecommend);
        		metadataDefinition.getEjbTransFileList().add(ejbRecommend.getLocation() + File.separator + "jboss.xml");
    		}
		} catch (JAXBException e) {
			logger.error("JAXBException has occurred.", e);
    		location = removeTempDir(file.getAbsolutePath(), key);
    		stackTrace = StackTracer.getStackTrace(e);
    		comments = "jboss.xml 파일 생성 중 marshalling이 실패하였습니다.";
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
    		location = removeTempDir(file.getAbsolutePath(), key);
    		stackTrace = StackTracer.getStackTrace(e);
    		comments = "jboss.xml 파일 생성할 수 였습니다.";
		} finally {
			if(StringUtils.isNotEmpty(stackTrace)) {
				exceptionInfo = new ExceptionInfo();
				exceptionInfo.setLocation(location);
				exceptionInfo.setStackTrace(stackTrace);
				exceptionInfo.setComments(comments);
				((PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION)).getExceptionInfoList().add(exceptionInfo);
			}
		}
    	
    	return ejbRecommend.getContents();
	}//end of parse()
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param obj
	 */
	private Jboss generateJbossXML(List<String> jndiNameList) {
		Jboss jboss = null;
		EnterpriseBeans enterpriseBeans = null;
		Session session = null;
		JndiName jndiName = null;
		
		if(jndiNameList.size() > 0) {
			jboss = new Jboss();
			enterpriseBeans = new EnterpriseBeans();
		
			for(String name : jndiNameList) {
				session = new Session();
				
				jndiName = new JndiName();
				jndiName.setvalue(name);
				session.setJndiName(jndiName);
				
				session.setCallByValue("true");
				session.setClustered("False");
				
				enterpriseBeans.getSessionOrEntityOrMessageDriven().add(session);
			}
			
			jboss.setEnterpriseBeans(enterpriseBeans);
		}
		
		return jboss;
	}//end of generateJbossXML()

}
//end of IbmEjbJarBndXMIParser.java