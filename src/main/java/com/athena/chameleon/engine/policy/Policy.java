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
 * Author              Date             Description
 * ------------------  --------------   ------------------
 * Sang-cheon Park     2012. 9. 18.     First Draft.
 */
package com.athena.chameleon.engine.policy;

import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;

/**
 * <pre>
 * Migration 기본 정책을 포함하고 있는 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @since 1.0
 */
public class Policy {

	private String unzipDir;
	private boolean convertYn;
	private String defaultEncoding;
	private String enEncoding;
	private String[] suffix;
	private String[] webSphere;
	private String[] weblogic;
	private String[] jeus;
	private String[] others;
	private String[] encodings;
	private Pattern pattern;
	private Pattern etcPattern;
	
	/**
	 * @return the unzipDir
	 */
	public String getUnzipDir() {
		return unzipDir;
	}
	/**
	 * @param unzipDir the unzipDir to set
	 */
	public void setUnzipDir(String unzipDir) {
		this.unzipDir = unzipDir;
	}
	/**
	 * @return the convertYn
	 */
	public boolean getConvertYn() {
		return convertYn;
	}
	/**
	 * @param convertYn the convertYn to set
	 */
	public void setConvertYn(boolean convertYn) {
		this.convertYn = convertYn;
	}
	/**
	 * @return the defaultEncoding
	 */
	public String getDefaultEncoding() {
		return defaultEncoding;
	}
	/**
	 * @param defaultEncoding the defaultEncoding to set
	 */
	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}
	/**
	 * @return the enEncoding
	 */
	public String getEnEncoding() {
		return enEncoding;
	}
	/**
	 * @param enEncoding the enEncoding to set
	 */
	public void setEnEncoding(String enEncoding) {
		this.enEncoding = enEncoding;
	}
	/**
	 * @return the suffix
	 */
	public String[] getSuffix() {
		return suffix;
	}
	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String[] suffix) {
		this.suffix = suffix;
	}
	/**
	 * @return the webSphere
	 */
	public String[] getWebSphere() {
		return webSphere;
	}
	/**
	 * @param webSphere the webSphere to set
	 */
	public void setWebSphere(String[] webSphere) {
		this.webSphere = webSphere;
	}
	/**
	 * @return the weblogic
	 */
	public String[] getWeblogic() {
		return weblogic;
	}
	/**
	 * @param weblogic the weblogic to set
	 */
	public void setWeblogic(String[] weblogic) {
		this.weblogic = weblogic;
	}
	/**
	 * @return the jeus
	 */
	public String[] getJeus() {
		return jeus;
	}
	/**
	 * @param jeus the jeus to set
	 */
	public void setJeus(String[] jeus) {
		this.jeus = jeus;
	}	
	/**
	 * @return the others
	 */
	public String[] getOthers() {
		return others;
	}
	/**
	 * @param others the others to set
	 */
	public void setOthers(String[] others) {
		this.others = others;
	}
	/**
	 * @return the encodings
	 */
	public String[] getEncodings() {
		return encodings;
	}
	/**
	 * @param encodings the encodings to set
	 */
	public void setEncodings(String[] encodings) {
		this.encodings = encodings;
	}
	/**
	 * @return the pattern
	 */
	public Pattern getPattern() {
		return pattern;
	}
	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}	
	/**
	 * @return the etcPattern
	 */
	public Pattern getEtcPattern() {
		return etcPattern;
	}
	/**
	 * @param etcPattern the etcPattern to set
	 */
	public void setEtcPattern(Pattern etcPattern) {
		this.etcPattern = etcPattern;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("unzipDir : ").append(unzipDir).append("\n")
				.append("defaultEncoding : ").append(defaultEncoding).append("\n")
				.append("enEncoding : " + enEncoding).append("\n")
				.append("suffix : ").append(ArrayUtils.toString(suffix)).append("\n")
				.append("webSphere : ").append(ArrayUtils.toString(webSphere)).append("\n")
				.append("weblogic : ").append(ArrayUtils.toString(weblogic)).append("\n")
				.append("jeus : ").append(ArrayUtils.toString(jeus))
				.append("others : ").append(ArrayUtils.toString(others)).append("\n")
				.append("pattern : ").append(pattern.toString())
				.append("etcPattern : ").append(etcPattern.toString());
		
		return sb.toString();
	}
}//end of Policy.java