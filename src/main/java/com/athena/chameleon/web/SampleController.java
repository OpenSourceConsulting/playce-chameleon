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
package com.athena.chameleon.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athena.chameleon.web.login.vo.Login;

/**
 * This LoginController class is a Controller class to Login.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
@Controller("sampleController")
@RequestMapping("/")
public class SampleController {

    /**
     * 
     * dash board 호출
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sample/dashBoard.do")
    public String dashBoard(Model model, HttpSession session) throws Exception {
    	return "/ec/ec/dashBoard";
    }

    /**
     * 
     * grid 및 detail sample 호출
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sample/gridForm.do")
    public String gridForm(Model model, HttpSession session) throws Exception {
    	return "/ajax/gridForm";
    }
   
}
