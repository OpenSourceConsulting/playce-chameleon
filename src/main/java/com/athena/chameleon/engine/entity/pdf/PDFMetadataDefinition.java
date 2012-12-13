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
 * Hyo-jeong Lee	2012. 9. 27.		First Draft.
 */
package com.athena.chameleon.engine.entity.pdf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * PDF 생성을 위한 root pojo object
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class PDFMetadataDefinition {
	
	private String sourceFile;
	private String migrateSourceFile;
	private String deployFile;
	private String migrateDeployFile;
	private String pdfFile;

	//zip 분석정보
    private AnalyzeDefinition zipDefinition;
    //ear 분석정보
    private AnalyzeDefinition earDefinition;
    //war 분석정보
    private Map<String, AnalyzeDefinition> warDefinitionMap;
    //jar 분석정보
    private Map<String, AnalyzeDefinition> jarDefinitionMap;
    //변환 application xml 정보(파일명, 파일내용)
    private List<EjbRecommend> applicationRecommendList;
    //변환 web xml 정보(파일명, 파일내용)
    private List<EjbRecommend> webRecommendList;
    //변환 xml 정보(파일명, 파일내용)
    private List<EjbRecommend> ejbRecommendList;
    //application 변환 대상 파일 리스트
    private List<String> appTransFileList;
    //application 변환 대상 파일 리스트
    private List<String> webTransFileList;
    //application 변환 대상 파일 리스트
    private List<String> ejbTransFileList;
    //exception 발생 항목
    private List<ExceptionInfo> exceptionInfoList;
    
	/**
	 * @return the sourceFile
	 */
	public String getSourceFile() {
		return sourceFile;
	}
	/**
	 * @param sourceFile the sourceFile to set
	 */
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	/**
	 * @return the migrateSourceFile
	 */
	public String getMigrateSourceFile() {
		return migrateSourceFile;
	}
	/**
     * @return the migrateSourceFile
     */
    public String getMigrateSourceFileName() {
        if(migrateSourceFile == null || migrateSourceFile.isEmpty())
            return "";
        else {
            String fileName = migrateSourceFile.replaceAll("\\\\", "/");
            return fileName.substring(fileName.lastIndexOf("/")+1, fileName.length());
        }
    }
	/**
	 * @param migrateSourceFile the migrateSourceFile to set
	 */
	public void setMigrateSourceFile(String migrateSourceFile) {
		this.migrateSourceFile = migrateSourceFile;
	}
	/**
	 * @return the deployFile
	 */
	public String getDeployFile() {
		return deployFile;
	}
	/**
	 * @param deployFile the deployFile to set
	 */
	public void setDeployFile(String deployFile) {
		this.deployFile = deployFile;
	}
	/**
     * @return the migrateDeployFile
     */
    public String getMigrateDeployFile() {
        return migrateDeployFile;
    }
	/**
	 * @return the migrateDeployFile
	 */
	public String getMigrateDeployFileName() {
	    if(migrateDeployFile == null || migrateDeployFile.isEmpty())
            return "";
        else {
            String fileName = migrateDeployFile.replaceAll("\\\\", "/");
            return fileName.substring(fileName.lastIndexOf("/")+1, fileName.length());
        }
	}
	/**
	 * @param migrateDeployFile the migrateDeployFile to set
	 */
	public void setMigrateDeployFile(String migrateDeployFile) {
		this.migrateDeployFile = migrateDeployFile;
	}
	/**
	 * @return the zipDefinition
	 */
	public AnalyzeDefinition getZipDefinition() {
		return zipDefinition;
	}
	/**
	 * @param zipDefinition the zipDefinition to set
	 */
	public void setZipDefinition(AnalyzeDefinition zipDefinition) {
		this.zipDefinition = zipDefinition;
	}
	/**
	 * @return the earDefinition
	 */
	public AnalyzeDefinition getEarDefinition() {
		return earDefinition;
	}
	/**
	 * @param earDefinition the earDefinition to set
	 */
	public void setEarDefinition(AnalyzeDefinition earDefinition) {
		this.earDefinition = earDefinition;
	}
	/**
	 * @return the warDefinitionMap
	 */
	public Map<String, AnalyzeDefinition> getWarDefinitionMap() {
	    if(warDefinitionMap == null)
	        warDefinitionMap = new TreeMap<String, AnalyzeDefinition>();
	    
		return warDefinitionMap;
	}
	/**
	 * @param fileName
	 * @param warDefinition
	 */
	public void addWarDefinitionMap(String fileName, AnalyzeDefinition warDefinition) {
		if(warDefinitionMap == null) {
			warDefinitionMap = new TreeMap<String, AnalyzeDefinition>();
		}
		
		warDefinitionMap.put(fileName, warDefinition);
	}
	/**
	 * @param warDefinitionMap the warDefinitionMap to set
	 */
	public void setWarDefinitionMap(Map<String, AnalyzeDefinition> warDefinitionMap) {
		this.warDefinitionMap = warDefinitionMap;
	}
	/**
	 * @return the jarDefinitionMap
	 */
	public Map<String, AnalyzeDefinition> getJarDefinitionMap() {
	    if(jarDefinitionMap == null) {
	        jarDefinitionMap = new TreeMap<String, AnalyzeDefinition>();
        }
		return jarDefinitionMap;
	}
	/**
	 * @param fileName
	 * @param jarDefinition
	 */
	public void addJarDefinitionMap(String fileName, AnalyzeDefinition jarDefinition) {
		if(jarDefinitionMap == null) {
			jarDefinitionMap = new TreeMap<String, AnalyzeDefinition>();
		}
		
		jarDefinitionMap.put(fileName, jarDefinition);
	}
	/**
	 * @param jarDefinitionMap the jarDefinitionMap to set
	 */
	public void setJarDefinitionMap(Map<String, AnalyzeDefinition> jarDefinitionMap) {
		this.jarDefinitionMap = jarDefinitionMap;
	}
	/**
	 * @return the appTransFileList
	 */
	public List<String> getAppTransFileList() {
		if(appTransFileList == null) {
			appTransFileList = new ArrayList<String>();
		}
		return appTransFileList;
	}
	/**
	 * @return the webTransFileList
	 */
	public List<String> getWebTransFileList() {
		if(webTransFileList == null) {
			webTransFileList = new ArrayList<String>();
		}
		return webTransFileList;
	}
	/**
	 * @return the ejbTransFileList
	 */
	public List<String> getEjbTransFileList() {
		if(ejbTransFileList == null) {
			ejbTransFileList = new ArrayList<String>();
		}
		return ejbTransFileList;
	}
    /**
     * @return the ejbRecommendList
     */
    public List<EjbRecommend> getEjbRecommendList() {
        if(ejbRecommendList == null)
            ejbRecommendList = new ArrayList<EjbRecommend>(); 
        return ejbRecommendList;
    }
    /**
     * @return the pdfFile
     */
    public String getPdfFile() {
        return pdfFile;
    }
    /**
     * @return the pdfFile
     */
    public String getPdfFileName() {
        if(pdfFile == null || pdfFile.isEmpty())
            return "";
        else {
            String fileName = pdfFile.replaceAll("\\\\", "/");
            return fileName.substring(fileName.lastIndexOf("/")+1, fileName.length());
        }
    }
    /**
     * @param pdfFile the pdfFile to set
     */
    public void setPdfFile(String pdfFile) {
        this.pdfFile = pdfFile;
    }
    /**
     * @return the applicationRecommendList
     */
    public List<EjbRecommend> getApplicationRecommendList() {
        if(applicationRecommendList == null)
            applicationRecommendList = new ArrayList<EjbRecommend>();
        return applicationRecommendList;
    }
    /**
     * @return the webRecommendList
     */
    public List<EjbRecommend> getWebRecommendList() {
        if(webRecommendList == null)
            webRecommendList = new ArrayList<EjbRecommend>();
        return webRecommendList;
    }
    /**
	 * @return the exceptionInfoList
	 */
	public List<ExceptionInfo> getExceptionInfoList() {
        if(exceptionInfoList == null)
        	exceptionInfoList = new ArrayList<ExceptionInfo>();
		return exceptionInfoList;
	}
}
//end of PDFDefination.java