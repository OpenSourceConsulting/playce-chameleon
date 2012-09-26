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
 * Hyo-jeong Lee	2012. 9. 12.		First Draft.
 */
package com.athena.chameleon.web.login.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.athena.chameleon.web.login.service.LoginService;
import com.athena.chameleon.web.login.vo.Login;


/**
 * This LoginServiceImpl class is an Implementation class to Login
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
@Service("coreLoginService")
public class LoginServiceImpl implements LoginService {

    @Value("#{filteringProperties['chameleon.login.id']}")
	String loginId;

    @Value("#{filteringProperties['chameleon.login.password']}")
	String passWord;

	/**
	 * 로그인 체크 Service Impl
	 */
	public boolean login(Login login) throws Exception {
		
		boolean result = false;
		
		if(login.getLoginId().equals(loginId) 
				&& login.getPassword().equals(passWord)) {
			result = true;
		}
		return result;
	}

}
