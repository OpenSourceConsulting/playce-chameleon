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
package com.athena.peacock.web.jdk;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.athena.chameleon.common.utils.PropertyUtil;
import com.athena.chameleon.engine.core.MigrationComponent;
import com.athena.chameleon.engine.core.PDFDocGenerator;
import com.athena.chameleon.engine.entity.upload.Upload;
import com.athena.peacock.engine.common.SystemConstants;
import com.athena.peacock.engine.common.SystemProperties;
import com.athena.peacock.engine.core.TargetHost;


/**
 * This JBossController class is a Controller class to install JBoss on Linux.
 * 
 * @author Ji-Woong Choi
 * @version 1.0
 */
@Controller("jbossController")
@RequestMapping("/jdk")
public class JDKController {
    
    
    

    /**
     * 
     * Install jdk to remote server
     * 
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/install.do")
    public String install(TargetHost targetHost, Model model, HttpSession session) throws Exception {
        System.out.println(targetHost);
        

        model.addAttribute(new Upload());
        return "/ifrm/upload/uploadForm";
    }
}
