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
 * Sang-cheon Park	2012. 10. 2.		First Draft.
 */
package com.athena.chameleon.engine.core.analyzer.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.ArrayUtils;
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
 * web.xml 파일을 web-app 버젼별로 unmarshalling 하고 인코딩 관련 필터의 존재 여부 확인 후 재 저장한다.
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class WebXMLParser extends Parser {
	
	/* (non-Javadoc)
	 * @see com.athena.chameleon.engine.core.analyzer.parser.Parser#parse(java.io.File, com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition)
	 */
	@Override
	public Object parse(File file, AnalyzeDefinition analyzeDefinition) {
		Assert.notNull(file, "file cannot be null.");
		Assert.notNull(analyzeDefinition, "analyzeDefinition cannot be null.");
		
		this.analyzeDefinition = analyzeDefinition;
		
        // only zip and war
        String key = ChameleonConstants.ZIP_ROOT_DIR;
        
        if(StringUtils.isEmpty((String)ThreadLocalUtil.get(key))) {
        	key = ChameleonConstants.WAR_ROOT_DIR;
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
        	// http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd
			obj = checkEncodignFilter(((JAXBElement<?>)JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.webapp.v2_5.WebAppType.class.getPackage().getName(), file)).getValue(), file.getParentFile().getAbsolutePath());
		} catch (JAXBException e1) {
        	try {
            	// http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd
				obj = checkEncodignFilter(((JAXBElement<?>)JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.webapp.v2_4.WebAppType.class.getPackage().getName(), file)).getValue(), file.getParentFile().getAbsolutePath());
			} catch (JAXBException e2) {
            	try {
        			// http://java.sun.com/dtd/web-app_2_3.dtd
	        		removeDoctype(file);
					obj = checkEncodignFilter(JaxbUtils.unmarshal(com.athena.chameleon.engine.entity.xml.webapp.v2_3.WebApp.class.getPackage().getName(), file), file.getParentFile().getAbsolutePath());
					rewrite(file, commonAnalyze.getContents());
            	} catch (JAXBException e3) {
					logger.error("JAXBException has occurred.", e3);
		    		location = removeTempDir(file.getAbsolutePath(), key);
		    		stackTrace = StackTracer.getStackTrace(e1);
		    		comments = "지원하지 않는 버젼의 파일이거나 인코딩 필터 추가 작업 시 에러가 발생하였습니다.";
    			} catch (IOException e3) {
    				logger.error("IOException has occurred.", e3);
    	    		location = removeTempDir(file.getAbsolutePath(), key);
    	    		stackTrace = StackTracer.getStackTrace(e1);
    			} catch (Exception e3) {
    				logger.error("Unhandled Exception has occurred.", e3);
    	    		location = removeTempDir(file.getAbsolutePath(), key);
    	    		stackTrace = StackTracer.getStackTrace(e1);
    			}
			} catch (IOException e2) {
				logger.error("IOException has occurred.", e2);
	    		location = removeTempDir(file.getAbsolutePath(), key);
	    		stackTrace = StackTracer.getStackTrace(e1);
			} catch (Exception e2) {
				logger.error("Unhandled Exception has occurred.", e2);
	    		location = removeTempDir(file.getAbsolutePath(), key);
	    		stackTrace = StackTracer.getStackTrace(e1);
			}
		} catch (IOException e1) {
			logger.error("IOException has occurred.", e1);
    		location = removeTempDir(file.getAbsolutePath(), key);
    		stackTrace = StackTracer.getStackTrace(e1);
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
	 * web.xml 파일에 인코딩 관련 filter 및 filter-mapping이 있고, UTF-8에 대한 filter 인지 확인하고 
	 * filter 가 없거나 UTF-8이 아닌 경우 새로운 filter 및 filter-mapping을 추가한다. 
	 * </pre>
	 * @param obj
	 * @param path
	 * @return
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	private Object checkEncodignFilter(Object obj, String path) throws JAXBException, IOException {
        String[] charSet = {"UTF-8", "UTF8"};
        boolean hasEncodingFilter = false;
        boolean hasUTF8EncodingFilter = false;
        
		if (obj instanceof com.athena.chameleon.engine.entity.xml.webapp.v2_5.WebAppType) {
			com.athena.chameleon.engine.entity.xml.webapp.v2_5.WebAppType webApp = (com.athena.chameleon.engine.entity.xml.webapp.v2_5.WebAppType) obj;
			
			// webApp에 <filter />, <filter-mapping />이 존재하는지 확인
            List<JAXBElement<?>> elementList = webApp.getDescriptionAndDisplayNameAndIcon();
            
            Object o = null;
            List<com.athena.chameleon.engine.entity.xml.webapp.v2_5.ParamValueType> paramList = null;
            com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterNameType filtername = null;
            
            for(JAXBElement<?> element : elementList) {
            	o = element.getValue();
            	
            	if(o instanceof com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterType) {
            		filtername = ((com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterType)o).getFilterName();
            		
            		paramList = ((com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterType)o).getInitParam();
            		for(com.athena.chameleon.engine.entity.xml.webapp.v2_5.ParamValueType param : paramList) {
                		// init-param의 param-name이 encoding이 존재하는지 확인
            			if(param.getParamName().getValue().toLowerCase().equals("encoding")) {
                			hasEncodingFilter = true;
                			
                    		// param-value가 UTF-8인지 확인
            				if(ArrayUtils.contains(charSet, param.getParamValue().getValue().toUpperCase())) {
            					hasUTF8EncodingFilter = true;
                    			break;
                			}
            			}
            		}
            		
            		if(hasEncodingFilter) {
            			break;
            		}
            	}
            }
            
            // encoding filter는 존재하지만 UTF-8이 아닐 경우 해당 filter 및 filter-mapping 엘리먼트를 삭제한다.
            if(hasEncodingFilter && !hasUTF8EncodingFilter) {
                JAXBElement<?> f = null;
                JAXBElement<?> fm = null;
                
	            for(JAXBElement<?> element : elementList) {
	            	o = element.getValue();
	            	
	            	if(o instanceof com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterType) {
	            		if(((com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterType)o).getFilterName().getValue().equals(filtername.getValue())) {
	            			f = element;
	            		}
	            	} else if(o instanceof com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterMappingType) {
	            		if(((com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterMappingType)o).getFilterName().getValue().equals(filtername.getValue())) {
	            			fm = element;
	            		}
	            	}
	            }
	            
	            webApp.getDescriptionAndDisplayNameAndIcon().remove(f);
	            webApp.getDescriptionAndDisplayNameAndIcon().remove(fm);
            }
            
            if(!hasUTF8EncodingFilter) {
	            // <filter> element 추가
            	com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterType filter = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterType();
	            
            	com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterNameType filterName = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterNameType();
	            filterName.setValue("UTF_EncodingFilter");
	            filter.setFilterName(filterName);
	            
	            com.athena.chameleon.engine.entity.xml.webapp.v2_5.FullyQualifiedClassType filterClass = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.FullyQualifiedClassType();
	            filterClass.setValue("com.osc.filters.SetCharacterEncodingFilter");
	            filter.setFilterClass(filterClass);
	            
	            // <init-param> element 추가
	            com.athena.chameleon.engine.entity.xml.webapp.v2_5.ParamValueType paramValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.ParamValueType();
	            com.athena.chameleon.engine.entity.xml.webapp.v2_5.String strName = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.String();
	            strName.setValue("encoding");
	            com.athena.chameleon.engine.entity.xml.webapp.v2_5.XsdStringType strValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.XsdStringType();
	            strValue.setValue("UTF-8");
	            paramValue.setParamName(strName);
	            paramValue.setParamValue(strValue);
	            
	            filter.getInitParam().add(paramValue);
	            
	            paramValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.ParamValueType();
	            strName = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.String();
	            strName.setValue("forceEncoding");
	            strValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.XsdStringType();
	            strValue.setValue("UTF-8");
	            paramValue.setParamName(strName);
	            paramValue.setParamValue(strValue);
	            
	            filter.getInitParam().add(paramValue);
            	
	            // <filter-mapping> elemnet 추가
	            com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterMappingType filterMapping = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.FilterMappingType();
	            com.athena.chameleon.engine.entity.xml.webapp.v2_5.UrlPatternType urlPattern = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.UrlPatternType();
	            urlPattern.setValue("/*");
	            filterMapping.getUrlPatternOrServletName().add(urlPattern);
	            filterMapping.setFilterName(filterName);

	            // <web-app>에 filter 추가
	            com.athena.chameleon.engine.entity.xml.webapp.v2_5.ObjectFactory factory = new com.athena.chameleon.engine.entity.xml.webapp.v2_5.ObjectFactory();
	            webApp.getDescriptionAndDisplayNameAndIcon().add(factory.createWebAppTypeFilter(filter));
	            webApp.getDescriptionAndDisplayNameAndIcon().add(factory.createWebAppTypeFilterMapping(filterMapping));
	            
	            try {
		            // Filter 관련 라이브러리 복사
		            fileCopy(new File(WebXMLParser.class.getResource("/lib/osc-filters.jar").getFile()), new File(path, "lib/osc-filters.jar"));
		            
		            // web.xml 저장
					String xmlData = JaxbUtils.marshal(com.athena.chameleon.engine.entity.xml.webapp.v2_5.WebAppType.class.getPackage().getName(), webApp, new String[]{"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"});
					rewrite(new File(path, "web.xml"), xmlData.replaceAll(" standalone=\"yes\"", "").replaceAll(" standalone=\"true\"", ""));
					logger.debug("web.xml has been modified.\n{}", xmlData);
				} catch (JAXBException e) {
					logger.error("JAXBException has occurred.", e);
					throw e;
				} catch (IOException e) {
					logger.error("IOException has occurred.", e);
					throw e;
				}
            }
            
            return webApp;
		} else if (obj instanceof com.athena.chameleon.engine.entity.xml.webapp.v2_4.WebAppType) {
			com.athena.chameleon.engine.entity.xml.webapp.v2_4.WebAppType webApp = (com.athena.chameleon.engine.entity.xml.webapp.v2_4.WebAppType) obj;
			
			// webApp에 <filter />, <filter-mapping />이 존재하는지 확인
            List<Object> elementList = webApp.getDescriptionAndDisplayNameAndIcon();
            
            List<com.athena.chameleon.engine.entity.xml.webapp.v2_4.ParamValueType> paramList = null;
            com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterNameType filtername = null;
            
            for(Object element : elementList) {
            	if(element instanceof com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType) {
            		filtername = ((com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType)element).getFilterName();
            		
            		paramList = ((com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType)element).getInitParam();
            		for(com.athena.chameleon.engine.entity.xml.webapp.v2_4.ParamValueType param : paramList) {
                		// init-param의 param-name이 encoding이 존재하는지 확인
            			if(param.getParamName().getValue().toLowerCase().equals("encoding")) {
                			hasEncodingFilter = true;
                			
                    		// param-value가 UTF-8인지 확인
            				if(ArrayUtils.contains(charSet, param.getParamValue().getValue().toUpperCase())) {
            					hasUTF8EncodingFilter = true;
                    			break;
                			}
            			}
            		}
            		
            		if(hasEncodingFilter) {
            			break;
            		}
            	}
            }
            
            // encoding filter는 존재하지만 UTF-8이 아닐 경우 해당 filter 및 filter-mapping 엘리먼트를 삭제한다.
            if(hasEncodingFilter && !hasUTF8EncodingFilter) {
            	com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType f = null;
            	com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterMappingType fm = null;
                
	            for(Object element : elementList) {
	            	if(element instanceof com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType) {
	            		if(((com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType)element).getFilterName().getValue().equals(filtername.getValue())) {
	            			f = (com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType)element;
	            		}
	            	} else if(element instanceof com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterMappingType) {
	            		if(((com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterMappingType)element).getFilterName().getValue().equals(filtername.getValue())) {
	            			fm = (com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterMappingType)element;
	            		}
	            	}
	            }
	            
	            webApp.getDescriptionAndDisplayNameAndIcon().remove(f);
	            webApp.getDescriptionAndDisplayNameAndIcon().remove(fm);
            }
            
            if(!hasUTF8EncodingFilter) {
	            // <filter> element 추가
            	com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType filter = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterType();
	            
            	com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterNameType filterName = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterNameType();
	            filterName.setValue("UTF_EncodingFilter");
	            filter.setFilterName(filterName);
	            
	            com.athena.chameleon.engine.entity.xml.webapp.v2_4.FullyQualifiedClassType filterClass = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.FullyQualifiedClassType();
	            filterClass.setValue("com.osc.filters.SetCharacterEncodingFilter");
	            filter.setFilterClass(filterClass);
	            
	            // <init-param> element 추가
	            com.athena.chameleon.engine.entity.xml.webapp.v2_4.ParamValueType paramValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.ParamValueType();
	            com.athena.chameleon.engine.entity.xml.webapp.v2_4.String strName = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.String();
	            strName.setValue("encoding");
	            com.athena.chameleon.engine.entity.xml.webapp.v2_4.XsdStringType strValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.XsdStringType();
	            strValue.setValue("UTF-8");
	            paramValue.setParamName(strName);
	            paramValue.setParamValue(strValue);
	            
	            filter.getInitParam().add(paramValue);
	            
	            paramValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.ParamValueType();
	            strName = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.String();
	            strName.setValue("forceEncoding");
	            strValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.XsdStringType();
	            strValue.setValue("UTF-8");
	            paramValue.setParamName(strName);
	            paramValue.setParamValue(strValue);
	            
	            filter.getInitParam().add(paramValue);
            	
	            // <filter-mapping> elemnet 추가
	            com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterMappingType filterMapping = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.FilterMappingType();
	            com.athena.chameleon.engine.entity.xml.webapp.v2_4.UrlPatternType urlPattern = new com.athena.chameleon.engine.entity.xml.webapp.v2_4.UrlPatternType();
	            urlPattern.setValue("/*");
	            filterMapping.setUrlPattern(urlPattern);
	            filterMapping.setFilterName(filterName);

	            // <web-app>에 filter 추가
	            webApp.getDescriptionAndDisplayNameAndIcon().add(filter);
	            webApp.getDescriptionAndDisplayNameAndIcon().add(filterMapping);
	            
	            try {
		            // Filter 관련 라이브러리 복사
		            fileCopy(new File(WebXMLParser.class.getResource("/lib/osc-filters.jar").getFile()), new File(path, "lib/osc-filters.jar"));
		            
		            // web.xml 저장
					String xmlData = JaxbUtils.marshal(com.athena.chameleon.engine.entity.xml.webapp.v2_4.WebAppType.class.getPackage().getName(), webApp, new String[]{"http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd"});
					rewrite(new File(path, "web.xml"), xmlData.replaceAll(" standalone=\"yes\"", "").replaceAll(" standalone=\"true\"", ""));
					logger.debug("web.xml has been modified.\n{}", xmlData);
				} catch (JAXBException e) {
					logger.error("JAXBException has occurred.", e);
					throw e;
				} catch (IOException e) {
					logger.error("IOException has occurred.", e);
					throw e;
				}
            }

            return webApp;
		} else {
			com.athena.chameleon.engine.entity.xml.webapp.v2_3.WebApp webApp = (com.athena.chameleon.engine.entity.xml.webapp.v2_3.WebApp) obj;
			
			// webApp에 <filter />, <filter-mapping />이 존재하는지 확인
			List<com.athena.chameleon.engine.entity.xml.webapp.v2_3.Filter> filterList = webApp.getFilter();

            com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterName filtername = null;
            List<com.athena.chameleon.engine.entity.xml.webapp.v2_3.InitParam> initParamList = null;
            for(com.athena.chameleon.engine.entity.xml.webapp.v2_3.Filter filter : filterList) {
            	filtername = filter.getFilterName();

            	initParamList = filter.getInitParam();
            	for(com.athena.chameleon.engine.entity.xml.webapp.v2_3.InitParam initParam : initParamList) {
            		if(initParam.getParamName().getvalue().toLowerCase().equals("encoding")) {
            			hasEncodingFilter = true;
            			
                		// param-value가 UTF-8인지 확인
        				if(ArrayUtils.contains(charSet, initParam.getParamValue().getvalue().toUpperCase())) {
        					hasUTF8EncodingFilter = true;
                			break;
            			}
            		}
            	}

        		if(hasEncodingFilter) {
        			break;
        		}
            }
            
            // encoding filter는 존재하지만 UTF-8이 아닐 경우 해당 filter 및 filter-mapping 엘리먼트를 삭제한다.
            if(hasEncodingFilter && !hasUTF8EncodingFilter) {
            	com.athena.chameleon.engine.entity.xml.webapp.v2_3.Filter filter = null;
            	
                for(com.athena.chameleon.engine.entity.xml.webapp.v2_3.Filter f : filterList) {
                	if(f.getFilterName().getvalue().equals(filtername.getvalue())) {
                		filter = f;
                	}
                }

    			List<com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterMapping> filterMappingList = webApp.getFilterMapping();
            	com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterMapping filterMapping = null;
                for(com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterMapping fm : filterMappingList) {
                	if(fm.getFilterName().getvalue().equals(filtername.getvalue())) {
                		filterMapping = fm;
                	}
                }
                
                webApp.getFilter().remove(filter);
                webApp.getFilterMapping().remove(filterMapping);
            }
            
            if(!hasUTF8EncodingFilter) {
	            // <filter> element 추가
            	com.athena.chameleon.engine.entity.xml.webapp.v2_3.Filter filter = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.Filter();
            	com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterName filterName = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterName();
            	
	            filterName.setvalue("UTF_EncodingFilter");
	            filter.setFilterName(filterName);
	            
	            com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterClass filterClass = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterClass();
	            filterClass.setvalue("com.osc.filters.SetCharacterEncodingFilter");
	            filter.setFilterClass(filterClass);
	            
	            // <init-param> element 추가
	            com.athena.chameleon.engine.entity.xml.webapp.v2_3.InitParam initParam = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.InitParam();
	            com.athena.chameleon.engine.entity.xml.webapp.v2_3.ParamName paramName = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.ParamName();
	            com.athena.chameleon.engine.entity.xml.webapp.v2_3.ParamValue paramValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.ParamValue();
	            
	            paramName.setvalue("encoding");
	            paramValue.setvalue("UTF-8");
	            
	            initParam.setParamName(paramName);
	            initParam.setParamValue(paramValue);
	            
	            filter.getInitParam().add(initParam);
	            
	            initParam = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.InitParam();
	            paramName = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.ParamName();
	            paramValue = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.ParamValue();
	            
	            paramName.setvalue("forceEncoding");
	            paramValue.setvalue("UTF-8");
	            
	            initParam.setParamName(paramName);
	            initParam.setParamValue(paramValue);
	            
	            filter.getInitParam().add(initParam);
            	
	            // <filter-mapping> elemnet 추가
	            com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterMapping filterMapping = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.FilterMapping();
	            com.athena.chameleon.engine.entity.xml.webapp.v2_3.UrlPattern urlPattern = new com.athena.chameleon.engine.entity.xml.webapp.v2_3.UrlPattern();
	            urlPattern.setvalue("/*");
	            filterMapping.getUrlPatternOrServletName().add(urlPattern);
	            filterMapping.setFilterName(filterName);

	            // <web-app>에 filter 추가
	            webApp.getFilter().add(filter);
	            webApp.getFilterMapping().add(filterMapping);
	            
	            try {
		            // Filter 관련 라이브러리 복사
		            fileCopy(new File(WebXMLParser.class.getResource("/lib/osc-filters.jar").getFile()), new File(path, "lib/osc-filters.jar"));
		            
		            // web.xml 저장
					String xmlData = JaxbUtils.marshal(com.athena.chameleon.engine.entity.xml.webapp.v2_3.WebApp.class.getPackage().getName(), webApp, "<!DOCTYPE web-app PUBLIC \"-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN\" \"http://java.sun.com/dtd/web-app_2_3.dtd\">");
					rewrite(new File(path, "web.xml"), xmlData.replaceAll(" standalone=\"yes\"", "").replaceAll(" standalone=\"true\"", ""));
					logger.debug("web.xml has been modified.\n{}", xmlData);
				} catch (JAXBException e) {
					logger.error("JAXBException has occurred.", e);
					throw e;
				} catch (IOException e) {
					logger.error("IOException has occurred.", e);
					throw e;
				}
            }

            return webApp;
		}
	}//end of checkEncodignFilter()

}//end of WebXMLParser.java