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
 * Hyo-jeong Lee	2012. 10. 5.		First Draft.
 */
package com.athena.chameleon.web.common.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
@Controller("downloadController")
@RequestMapping("/download")
public class DownloadController {

    private WebApplicationContext context = null;
    
    @RequestMapping("/down.do")
    public ModelAndView download(HttpServletRequest request) throws Exception {
        File downloadFile = new File(request.getParameter("path"));
        return new ModelAndView("downloadView", "downloadFile", downloadFile);
    }
 
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = (WebApplicationContext) applicationContext;
    }

}
//end of DownloadController.java