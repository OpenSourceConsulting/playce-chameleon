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

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.EjbRecommend;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.entity.xml.application.jboss.v5_0.JbossApp;
import com.athena.chameleon.engine.entity.xml.application.jboss.v5_0.LoaderRepository;
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
public class IbmApplicationBndXMIParser extends Parser {

	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analyzer.parser.Parser#parse(java.io.File, com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition)
	 */
	@Override
	public Object parse(File file, AnalyzeDefinition analyzeDefinition) {
		Assert.notNull(file, "file cannot be null.");
		Assert.notNull(analyzeDefinition, "analyzeDefinition cannot be null.");
		
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
    		location = removeTempDir(file.getAbsolutePath(), key);
    		stackTrace = StackTracer.getStackTrace(e);
    		comments = "jboss-app.xml 파일 생성 중 marshalling이 실패하였습니다.";
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
    		location = removeTempDir(file.getAbsolutePath(), key);
    		stackTrace = StackTracer.getStackTrace(e);
    		comments = "jboss-app.xml 파일 생성할 수 였습니다.";
		} finally {
			if(StringUtils.isNotEmpty(stackTrace)) {
				exceptionInfo.setLocation(location);
				exceptionInfo.setStackTrace(stackTrace);
				exceptionInfo.setComments(comments);
				((PDFMetadataDefinition)ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION)).getExceptionInfoList().add(exceptionInfo);
			}
		}
        
		return ejbRecommend.getContents();
	}//end of parse()

}
//end of IbmApplicationBndXMIParser.java