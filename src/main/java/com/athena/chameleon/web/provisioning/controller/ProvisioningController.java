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
package com.athena.chameleon.web.provisioning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athena.chameleon.engine.entity.provisioning.Provisioning;
import com.athena.chameleon.engine.entity.provisioning.ProvisioningResult;
import com.athena.peacock.installer.jboss.JBossProvisioning;
import com.athena.peacock.installer.tomcat.TomcatProvisioning;

/**
 * This LoginController class is a Controller class to Upload.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
@Controller("provisioningController")
@RequestMapping("/provisioning")
public class ProvisioningController {

    /**
     * 
     * Was 선택화면 호출
     *
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/wasSelectForm.do")
    public String showWasSelect(Model model, HttpSession session) throws Exception {
        
        String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
        if(loginFlag == null || !loginFlag.equals("Y"))
            return "redirect:/login/showLogin.do";
        
        return "/main/provisioning/wasSelectForm";
    }

    /**
     * Instance 입력화면 호출
     *
     * @param provisioning
     * @param modelMap
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/wasInstanceForm.do")
    public String showWasInstance(Provisioning provisioning, ModelMap modelMap, HttpSession session) throws Exception {
        
        String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
        if(loginFlag == null || !loginFlag.equals("Y"))
            return "redirect:/login/showLogin.do";
        
        if(provisioning == null || provisioning.getTargetWas() == null)
            return "redirect:/provisioning/wasSelectForm.do";
        
        modelMap.addAttribute("provisioning", provisioning);
        
        String returnForm = "redirect:/provisioning/wasSelectForm.do";
        if(provisioning.getTargetWas().equals("B"))
            returnForm = "/main/provisioning/jbossInstanceForm";
        else if(provisioning.getTargetWas().equals("T"))
        	returnForm = "/main/provisioning/tomcatInstanceForm";
        
        return returnForm;
    }

    /**
     * Instance 입력화면 호출
     *
     * @param provisioning
     * @param modelMap
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/dataSourceForm.do")
    public String showDataSource(Provisioning provisioning, ModelMap modelMap, HttpSession session) throws Exception {
        
        String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
        if(loginFlag == null || !loginFlag.equals("Y"))
            return "redirect:/login/showLogin.do";
        
        if(provisioning == null || provisioning.getTargetWas() == null)
            return "redirect:/provisioning/wasSelectForm.do";
        
        return "/main/provisioning/dataSourceForm";
    }

    /**
     * Instance 입력화면 호출
     *
     * @param provisioning
     * @param modelMap
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/install.do")
    public String installWas(Provisioning provisioning, ModelMap modelMap, HttpSession session) throws Exception {
        
        String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
        if(loginFlag == null || !loginFlag.equals("Y"))
            return "redirect:/login/showLogin.do";
        
        if(provisioning == null || provisioning.getTargetWas() == null)
            return "redirect:/provisioning/wasSelectForm.do";

        
        modelMap.addAttribute("provisioning", provisioning);
        
        String returnForm = "redirect:/provisioning/wasSelectForm.do";
        
        ProvisioningResult provisioningResult = null;
        if(provisioning.getTargetWas().equals("B")) {
            returnForm = "/main/provisioning/jbossResultForm";
            provisioningResult = new JBossProvisioning().doProvision(provisioning);
        } else if(provisioning.getTargetWas().equals("T")) {
        	returnForm = "/main/provisioning/tomcatResultForm";
        	provisioningResult = new TomcatProvisioning().doProvision(provisioning);
        }
        
        modelMap.addAttribute("provisioningResult", provisioningResult);
        
        return returnForm;
    }

}
