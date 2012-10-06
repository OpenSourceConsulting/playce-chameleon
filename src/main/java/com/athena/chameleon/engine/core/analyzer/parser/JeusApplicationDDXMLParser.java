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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.EjbRecommend;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.entity.xml.application.jboss.v5_0.JbossApp;
import com.athena.chameleon.engine.entity.xml.application.jboss.v5_0.LoaderRepository;
import com.athena.chameleon.engine.utils.JaxbUtils;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JeusApplicationDDXMLParser extends Parser {

	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analyzer.parser.Parser#parse(java.io.File, com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition)
	 */
	@Override
	public Object parse(File file, AnalyzeDefinition analyzeDefinition) {
		this.analyzeDefinition = analyzeDefinition;
		
        // only zip and ear
        String key = ChameleonConstants.ZIP_ROOT_DIR;
        
        if(StringUtils.isEmpty((String)ThreadLocalUtil.get(key))) {
        	key = ChameleonConstants.EAR_ROOT_DIR;
        }
		
		PDFMetadataDefinition metadataDefinition = (PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
		EjbRecommend ejbRecommend = new EjbRecommend();
		
        try {
        	ejbRecommend = new EjbRecommend();
    		ejbRecommend.setItem(file.getName());
    		ejbRecommend.setTransFlag(false);
    		ejbRecommend.setLocation(removeTempDir(file.getParent(), key));
    		ejbRecommend.setContents(fileToString(file.getAbsolutePath()));
    		
    		metadataDefinition.getApplicationRecommendList().add(ejbRecommend);
        } catch (IOException e) {
            logger.error("IOException has occurred.", e);
        }

//        try {
//            CommonAnalyze commonAnalyze = new CommonAnalyze();
//            commonAnalyze.setItem(file.getName());
//            commonAnalyze.setLocation(removeTempDir(file.getParent()));
//            commonAnalyze.setContents(fileToString(file.getAbsolutePath()));
//            
//            analyzeDefinition.getDescripterList().add(commonAnalyze);
//        } catch (IOException e) {
//            logger.error("IOException has occurred.", e);
//        }
        
    	Object obj = null;

    	try {
        	// jeus-main.xsd v6_0
			obj = ((JAXBElement<?>)JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.application.jeus.v6_0.ApplicationType.class.getPackage().getName(), file)).getValue();
    	} catch (JAXBException e1) {
	    	try {
	        	// jeus-main.xsd v5_0
				obj = ((JAXBElement<?>)JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.application.jeus.v5_0.ApplicationType.class.getPackage().getName(), file)).getValue();
			} catch (JAXBException e2) {
				logger.error("JAXBException has occurred.", e2);
			}
    	}
    	
		try {
			JbossApp jbossApp = new JbossApp();
			
			LoaderRepository loaderRepository = new LoaderRepository();
			loaderRepository.setvalue("com.athena.chameleon:loader=" + ThreadLocalUtil.get(ChameleonConstants.PROJECT_NAME));
			
			jbossApp.setLoaderRepository(loaderRepository);
			
			String xmlData = JaxbUtils.marshal(JbossApp.class.getPackage().getName(), jbossApp, "<!DOCTYPE jboss-app PUBLIC \"-//JBoss//DTD J2EE Application 5.0//EN\" \"http://www.jboss.org/j2ee/dtd/jboss-app_5_0.dtd\">");

			rewrite(new File(file.getParentFile(), "jboss-app.xml"), xmlData.replaceAll(" standalone=\"yes\"", "").replaceAll(" standalone=\"true\"", ""));
			
        	ejbRecommend = new EjbRecommend();
    		ejbRecommend.setItem("jboss-app.xml");
    		ejbRecommend.setTransFlag(true);
    		ejbRecommend.setLocation(removeTempDir(file.getParent(), key));
    		ejbRecommend.setContents(xmlData.replaceAll(" standalone=\"yes\"", "").replaceAll(" standalone=\"true\"", ""));
    		
    		metadataDefinition.getApplicationRecommendList().add(ejbRecommend);
    		metadataDefinition.getAppTransFileList().add(ejbRecommend.getLocation() + File.separator + "jboss-app.xml");
		} catch (JAXBException e) {
			logger.error("JAXBException has occurred.", e);
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		}
		
		return obj;
	}//end of parse()

}
//end of JeusApplicationDDXMLParser.java