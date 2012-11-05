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
 * Author           Date                Description
 * ---------------  ----------------    ------------
 * Hyo-jeong Lee    2012. 9. 12.        First Draft.
 */
package com.athena.chameleon.web.system.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athena.chameleon.common.utils.PropertyUtil;
import com.ibm.icu.text.CharsetDetector;

/**
 * This SystemController class is a Controller class to System.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
@Controller("systemController")
@RequestMapping("/system")
public class SystemController {

    /**
     * 코드관리 저장
     * 
     * @param model
     * @param modelMap
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/codeForm.do")
    public String showCode(Model model, ModelMap modelMap, HttpSession session) throws Exception {
        
        String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
        if(loginFlag == null || !loginFlag.equals("Y"))
            return "redirect:/login/showLogin.do";
        
        File resource = new File(PropertyUtil.class.getResource("/filtering.properties").getFile());
        CharsetDetector detector = new CharsetDetector();
        detector.setText(FileUtils.readFileToByteArray(resource));
        String code = FileUtils.readFileToString(resource, detector.detect().getName());

        modelMap.addAttribute("code", code);
        
        return "/main/system/codeForm";
    }

    /**
     * 코드관리 저장
     * 
     * @param request
     * @param response
     * @param modelMap
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveCode.do")
    public String saveCode(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, HttpSession session) throws Exception {
        
    	
    	String code = request.getParameter("code");
    	File resource = new File(PropertyUtil.class.getResource("/filtering.properties").getFile());
    	
    	Writer output = new BufferedWriter(new FileWriter(resource));
    	output.write(code);
    	output.close();
    	
    	modelMap.addAttribute("result", true);
    	return "jsonView";
    }

}
