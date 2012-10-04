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
package com.athena.chameleon.web.login.controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.athena.chameleon.web.login.service.LoginService;
import com.athena.chameleon.web.login.vo.Login;

/**
 * This LoginController class is a Controller class to Login.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
@Controller("loginController")
@RequestMapping("/login")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Inject
    @Named("coreLoginService")
    private LoginService loginService;

    /**
     * 
     * 로그인 페이지 호출
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/showLogin.do")
    public String showLogin(Model model) throws Exception {
    	model.addAttribute(new Login());
    	return "/ifrm/login/loginForm";
    }

    /**
     *  로그인
     *
     * @param login
     * @param modelMap
     * @param results
     * @param status
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/login.do")
    public String login(Login login, ModelMap modelMap, BindingResult results, SessionStatus status, HttpSession session) throws Exception {

        logger.debug("Login Info: {}", login);
    	if (results.hasErrors()) {
            return "/ifrm/login/loginForm";
        }

        boolean result = this.loginService.login(login);
        if(result) {
        	session.setAttribute("loginFlag", "Y");
        	session.setAttribute("login", login);
        }
        modelMap.addAttribute("result", result);
        
        return "jsonView";
    }

}
