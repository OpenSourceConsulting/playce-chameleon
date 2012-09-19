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
package com.athena.chameleon.web.upload.controller;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.athena.chameleon.engine.core.MigrationComponent;
import com.athena.chameleon.engine.core.PDFDocGenerator;
import com.athena.chameleon.engine.entity.file.Migration;
import com.athena.chameleon.web.upload.vo.Upload;

/**
 * This LoginController class is a Controller class to Upload.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
@Controller("uploadController")
@RequestMapping("/upload.do")
public class UploadController {

	@Inject
    @Named("migrationComponent")
    private MigrationComponent component;

	@Inject
    @Named("pdfDataDefinition")
    private PDFDocGenerator pdfData;

	@Value("#{filteringProperties['chameleon.upload.temp.dir']}")
	public String uploadPath;

	/**
	 * 
	 * 업로드 입력정보 화면 호출
	 *
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(params = "method=show")
    public String showUpload(Model model, HttpSession session) throws Exception {
    	
    	String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
    	if(loginFlag == null || !loginFlag.equals("Y"))
    		return "redirect:/login.do?method=show";
    	
    	model.addAttribute(new Upload());
    	return "/ifrm/upload/show";
    }

    /**
     * 
     * 업로드 실행
     *
     * @param upload
     * @param results
     * @param status
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=upload")
    public String upload(Upload upload, BindingResult results, SessionStatus status, HttpSession session) throws Exception {

        String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
    	if(loginFlag == null || !loginFlag.equals("Y"))
    		return "redirect:/login.do?method=show";
    	
    	if (results.hasErrors()) {
    		return "redirect:/upload.do?method=show";
        }
    	
    	try {
            if(upload.getProjectSrc() != null && upload.getProjectSrc().getSize() > 0 ) {
                Migration entity = component.executeMigrationForMultipartFile(upload.getProjectSrc());
                System.out.println(entity.getFileListStr());
                System.out.println(entity.getWebXmlStr());
                System.out.println(entity.getApplicationXmlStr());
                System.out.println(entity.getEjbXmlStr());
            }
            
            if(upload.getDeploySrc() != null && upload.getDeploySrc().getSize() > 0 ) {
                Migration entity = component.executeMigrationForMultipartFile(upload.getDeploySrc());
                System.out.println(entity.getFileListStr());
                System.out.println(entity.getWebXmlStr());
                System.out.println(entity.getApplicationXmlStr());
                System.out.println(entity.getEjbXmlStr());
            }
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
        
    	        
        return "";
    }

}
