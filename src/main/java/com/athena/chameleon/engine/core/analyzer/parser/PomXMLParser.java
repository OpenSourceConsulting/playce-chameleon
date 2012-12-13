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
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.w3c.dom.Element;

import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;
import com.athena.chameleon.engine.entity.pdf.CommonAnalyze;
import com.athena.chameleon.engine.entity.pdf.ExceptionInfo;
import com.athena.chameleon.engine.entity.pdf.MavenDependency;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Dependency;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Model;
import com.athena.chameleon.engine.entity.xml.project.v4_0.Model.Dependencies;
import com.athena.chameleon.engine.utils.JaxbUtils;
import com.athena.peacock.engine.common.StackTracer;

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

		CommonAnalyze commonAnalyze = null;
        try {
            commonAnalyze = new CommonAnalyze();
            commonAnalyze.setItem(file.getName());
            commonAnalyze.setLocation(removeTempDir(file.getParent(), ChameleonConstants.ZIP_ROOT_DIR));
            commonAnalyze.setContents(fileToString(file.getAbsolutePath()));
            
            analyzeDefinition.getMavenProjectList().add(commonAnalyze);
        } catch (IOException e) {
            logger.error("IOException has occurred.", e);
        }
        
    	Object obj = null;
    	
    	try {
        	// http://maven.apache.org/xsd/maven-4.0.0.xsd
			obj = checkDependency(((JAXBElement<?>)JaxbUtils.unmarshal(Model.class.getPackage().getName(), file)).getValue(), file.getParentFile().getAbsolutePath());
		} catch (JAXBException e) {
			logger.error("JAXBException has occurred.", e);
    		location = removeTempDir(file.getAbsolutePath(), ChameleonConstants.ZIP_ROOT_DIR);
    		stackTrace = StackTracer.getStackTrace(e);
    		comments = "pom.xml 파싱 중 에러가 발생하였습니다.";
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
    		location = removeTempDir(file.getAbsolutePath(), ChameleonConstants.ZIP_ROOT_DIR);
    		stackTrace = StackTracer.getStackTrace(e);
    		comments = "pom.xml 파일을 읽을 수 없습니다.";
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException has occurred.", e);
    		location = removeTempDir(file.getAbsolutePath(), ChameleonConstants.ZIP_ROOT_DIR);
    		stackTrace = StackTracer.getStackTrace(e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException has occurred.", e);
    		location = removeTempDir(file.getAbsolutePath(), ChameleonConstants.ZIP_ROOT_DIR);
    		stackTrace = StackTracer.getStackTrace(e);
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
	 * @param model
	 * @param path
	 * @return
	 * @throws IOException 
	 * @throws JAXBException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private Object checkDependency(Object model, String path) throws JAXBException, IOException, IllegalAccessException, InvocationTargetException {
		Dependencies dependencies = ((Model)model).getDependencies();
		List<Dependency> dependencyList = dependencies.getDependency();
		
		boolean isChanged = false;
		MavenDependency mavenDependency = null;
		for (Dependency dependency : dependencyList) {
			mavenDependency = new MavenDependency();
			BeanUtils.copyProperties(mavenDependency, dependency);
			
			if (mavenDependency.getVersion().startsWith("${") && mavenDependency.getVersion().endsWith("}")) {
				List<Element> elementList = ((Model)model).getProperties().getAny();
				
				for (Element element : elementList) {
					if (StringUtils.equals(mavenDependency.getVersion().substring(2, mavenDependency.getVersion().length() - 1), element.getNodeName())) {
						mavenDependency.setVersion(element.getTextContent());
						break;
					}
				}
			}
			
			analyzeDefinition.getMavenDependencyList().add(mavenDependency);
			
			if (dependency.getGroupId().equals("xerces") && dependency.getArtifactId().equals("xercesImpl") && !StringUtils.equals(dependency.getScope(), "provided")) {
				isChanged = true;
				dependency.setScope("provided");
				
				mavenDependency = new MavenDependency();
				BeanUtils.copyProperties(mavenDependency, dependency);
				analyzeDefinition.getModifiedMavenDependencyList().add(mavenDependency);
			} else if (dependency.getGroupId().equals("xml-apis") && dependency.getArtifactId().equals("xml-apis") && !StringUtils.equals(dependency.getScope(), "provided")) {
				isChanged = true;
				dependency.setScope("provided");
				
				mavenDependency = new MavenDependency();
				BeanUtils.copyProperties(mavenDependency, dependency);
				analyzeDefinition.getModifiedMavenDependencyList().add(mavenDependency);
			} else if (dependency.getGroupId().equals("xalan") && dependency.getArtifactId().equals("xalan") && !StringUtils.equals(dependency.getScope(), "provided")) {
				isChanged = true;
				dependency.setScope("provided");
				
				mavenDependency = new MavenDependency();
				BeanUtils.copyProperties(mavenDependency, dependency);
				analyzeDefinition.getModifiedMavenDependencyList().add(mavenDependency);
			}
		}
		
		if(isChanged) {
            // pom.xml 저장
			String xmlData = JaxbUtils.marshal(Model.class.getPackage().getName(), model, new String[]{"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"}, true);
			rewrite(new File(path, "pom.xml"), xmlData);
			logger.debug("pom.xml has been modified.\n{}", xmlData);

			CommonAnalyze commonAnalyze = null;
            commonAnalyze = new CommonAnalyze();
            commonAnalyze.setItem(analyzeDefinition.getMavenProjectList().get(0).getItem());
            commonAnalyze.setLocation(analyzeDefinition.getMavenProjectList().get(0).getLocation());
            commonAnalyze.setContents(xmlData);
            
            analyzeDefinition.getMavenProjectList().add(commonAnalyze);
		}

        return model;
	}//end of checkDependency()

}//end of PomXMLParser.java