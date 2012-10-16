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
 * Sang-cheon Park	2012. 10. 15.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.springframework.util.Assert;

import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Dependency;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Model;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Model.Dependencies;
import com.athena.chameleon.engine.utils.JaxbUtils;

/**
 * <pre>
 * pom.xml 파일을 분석하여 jboss관련 의존성 라이브러리가 존재할 경우 scope을 provided로 변경하여 재 저장한다.
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class PomXMLParser extends Parser {
	
	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analyzer.parser.Parser#parse(java.io.File, com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition)
	 */
	@Override
	public Object parse(File file, AnalyzeDefinition analyzeDefinition) {
		Assert.notNull(file, "file cannot be null.");
		Assert.notNull(analyzeDefinition, "analyzeDefinition cannot be null.");
		
		this.analyzeDefinition = analyzeDefinition;
        
    	Object obj = null;
    	
    	try {
        	// http://maven.apache.org/xsd/maven-4.0.0.xsd
			obj = checkDependency(((JAXBElement<?>)JaxbUtils.unmarshal(Model.class.getPackage().getName(), file)).getValue(), file.getParentFile().getAbsolutePath());
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
	 * @param model
	 * @param path
	 * @return
	 * @throws IOException 
	 * @throws JAXBException 
	 */
	private Object checkDependency(Object model, String path) throws JAXBException, IOException {
		Dependencies dependencies = ((Model)model).getDependencies();
		List<Dependency> dependencyList = dependencies.getDependency();
		
		boolean isChanged = false;
		for (Dependency dependency : dependencyList) {
			if (dependency.getGroupId().equals("xerces") && dependency.getArtifactId().equals("xercesImpl")) {
				isChanged = true;
				dependency.setScope("provided");
			} else if (dependency.getGroupId().equals("xml-apis") && dependency.getArtifactId().equals("xml-apis")) {
				isChanged = true;
				dependency.setScope("provided");
			}else if (dependency.getGroupId().equals("xalan") && dependency.getArtifactId().equals("xalan")) {
				isChanged = true;
				dependency.setScope("provided");
			}
		}
		
		if(isChanged) {
            // pom.xml 저장
			String xmlData = JaxbUtils.marshal(Model.class.getPackage().getName(), model, new String[]{"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"}, true);
			rewrite(new File(path, "pom.xml"), xmlData);
			logger.debug("pom.xml has been modified.\n{}", xmlData);
		}

        return model;
	}//end of checkDependency()

}//end of PomXMLParser.java