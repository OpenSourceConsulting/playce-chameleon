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
 * Sang-cheon Park	2012. 10. 24.		First Draft.
 */
package com.athena.chameleon.engine.entity.pdf;

/**
 * <pre>
 * pom.xml에 기술된 dependencies 내의 dependency 라이브러리 목록을 저장하기 위한 Data Object
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class MavenDependency {
	
    private String groupId;
    private String artifactId;
    private String version;
    private String type;
    private String classifier;
    private String scope;
    private String systemPath;
    private Boolean optional;
    
	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the artifactId
	 */
	public String getArtifactId() {
		return artifactId;
	}
	/**
	 * @param artifactId the artifactId to set
	 */
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the classifier
	 */
	public String getClassifier() {
		return classifier;
	}
	/**
	 * @param classifier the classifier to set
	 */
	public void setClassifier(String classifier) {
		this.classifier = classifier;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * @return the systemPath
	 */
	public String getSystemPath() {
		return systemPath;
	}
	/**
	 * @param systemPath the systemPath to set
	 */
	public void setSystemPath(String systemPath) {
		this.systemPath = systemPath;
	}
	/**
	 * @return the optional
	 */
	public Boolean getOptional() {
		return optional;
	}
	/**
	 * @param optional the optional to set
	 */
	public void setOptional(Boolean optional) {
		this.optional = optional;
	}
}
//end of MavenDependency.java