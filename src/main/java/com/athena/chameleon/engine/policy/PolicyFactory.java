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

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.athena.chameleon.common.utils.PropertyUtil;

/**
 * <pre>
 * Migration 기본 정책을 포함하는 Policy 객체를 생성하기 위한 Factory 클래스
 * 
 * 다음과 같은 방법으로 Policy 객체를 초기화한다.
 * 
 * &#64Inject
 * &#64Name("policy")
 * private Policy policy;
 *    
 * or
 * 
 * Policy policy = (Policy)context.getBean("policy");
 * </pre>
 * 
 * @author Sang-cheon Park
 * @since 1.0
 */
@Component("policy")
public class PolicyFactory implements FactoryBean<Policy>, InitializingBean {

	private Policy policy;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		policy = new Policy();
		
		// Migration 기본 정책이 DB에 저장될 경우 여기에서 DB 조회 및 Policy 초기화 수행
		policy.setUnzipDir(PropertyUtil.getProperty("chameleon.migration.policy.unzip.dir"));
		policy.setConvertYn((PropertyUtil.getProperty("chameleon.migration.policy.convert.encoding.yn") == null ? true :
			PropertyUtil.getProperty("chameleon.migration.policy.convert.encoding.yn").equalsIgnoreCase("true") ||
			PropertyUtil.getProperty("chameleon.migration.policy.convert.encoding.yn").equalsIgnoreCase("yes") ||
			PropertyUtil.getProperty("chameleon.migration.policy.convert.encoding.yn").equalsIgnoreCase("y")));
		policy.setDefaultEncoding(PropertyUtil.getProperty("chameleon.migration.policy.default.encoding"));
		policy.setEnEncoding(PropertyUtil.getProperty("chameleon.migration.policy.en.encoding"));
		policy.setSuffix((PropertyUtil.getProperty("chameleon.migration.policy.suffix").indexOf(",") > -1 ? 
				PropertyUtil.getProperty("chameleon.migration.policy.suffix").split(",") : 
					new String[]{PropertyUtil.getProperty("chameleon.migration.policy.suffix")}));
		policy.setWebSphere((PropertyUtil.getProperty("chameleon.migration.policy.webSphere").indexOf(",") > -1 ? 
				PropertyUtil.getProperty("chameleon.migration.policy.webSphere").split(",") : 
					new String[]{PropertyUtil.getProperty("chameleon.migration.policy.webSphere")}));
		policy.setWeblogic((PropertyUtil.getProperty("chameleon.migration.policy.weblogic").indexOf(",") > -1 ? 
				PropertyUtil.getProperty("chameleon.migration.policy.weblogic").split(",") : 
					new String[]{PropertyUtil.getProperty("chameleon.migration.policy.weblogic")}));
		policy.setJeus((PropertyUtil.getProperty("chameleon.migration.policy.jeus").indexOf(",") > -1 ? 
				PropertyUtil.getProperty("chameleon.migration.policy.jeus").split(",") : 
					new String[]{PropertyUtil.getProperty("chameleon.migration.policy.jeus")}));
		policy.setOthers((PropertyUtil.getProperty("chameleon.migration.policy.others").indexOf(",") > -1 ? 
				PropertyUtil.getProperty("chameleon.migration.policy.others").split(",") : 
					new String[]{PropertyUtil.getProperty("chameleon.migration.policy.others")}));
		policy.setEncodings((PropertyUtil.getProperty("chameleon.migrarion.policy.detect.encodings").indexOf(",") > -1 ? 
				PropertyUtil.getProperty("chameleon.migrarion.policy.detect.encodings").split(",") : 
					new String[]{PropertyUtil.getProperty("chameleon.migrarion.policy.detect.encodings")}));
		
		StringBuilder regex = new StringBuilder("(");

		for(String webSphere : policy.getWebSphere()) {
			regex.append(".*").append(webSphere).append(".*|");
		}
		for(String weblogic : policy.getWeblogic()) {
			regex.append(".*").append(weblogic).append(".*|");
		}
		for(String jeus : policy.getJeus()) {
			regex.append(".*").append(jeus).append(".*|");
		}
		regex.deleteCharAt(regex.toString().length() - 1);
		regex.append(")");
		
		policy.setPattern(Pattern.compile(regex.toString()));
		
		regex = new StringBuilder("(");
		for(String other : policy.getOthers()) {
			regex.append(".*").append(other).append(".*|");
		}
		regex.deleteCharAt(regex.toString().length() - 1);
		regex.append(")");
		
		policy.setEtcPattern(Pattern.compile(regex.toString()));
	}

	@Override
	public Policy getObject() throws Exception {
		return policy;
	}

	@Override
	public Class<?> getObjectType() {
		return Policy.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}//end of PolicyFactory.java