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

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import com.athena.chameleon.engine.core.PDFDataDefinition;
import com.athena.chameleon.engine.entity.file.MigrationFile;
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
    private PDFDataDefinition pdfData;

	@Value("#{filteringProperties['chameleon.upload.temp.dir']}")
	public String uploadPath;

    @RequestMapping(params = "method=show")
    public String showUpload(Model model, HttpSession session) throws Exception {
    	
    	String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
    	if(loginFlag == null || !loginFlag.equals("Y"))
    		return "redirect:/login.do?method=show";
    	
    	model.addAttribute(new Upload());
    	return "/ifrm/upload/show";
    }

    @RequestMapping(params = "method=upload")
    public String upload(Upload upload, BindingResult results, SessionStatus status, HttpSession session) throws Exception {

    	String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
    	if(loginFlag == null || !loginFlag.equals("Y"))
    		return "redirect:/login.do?method=show";
    	
    	if (results.hasErrors()) {
    		return "redirect:/upload.do?method=show";
        }
    	
        File projectFile = null;
        try {
        	projectFile = new File(uploadPath+upload.getProjectSrc().getOriginalFilename());

            if (!projectFile.exists()) {
                if (!projectFile.mkdirs()) {
                    throw new Exception("Fail to create a directory for attached file [" + projectFile + "]");
                }
            }
            
        	projectFile.deleteOnExit();
            upload.getProjectSrc().transferTo(projectFile);
            
            component = new MigrationComponent();
        	System.out.println("aaaaa:::"+projectFile);
        	System.out.println("aaaaa:::"+projectFile.getAbsolutePath());
        	component.unzipFile(projectFile.getAbsolutePath());
        	component.setMigrationFileList();

            List<MigrationFile> list = component.getMigrationFileList();
            System.out.println(pdfData.getMigrationFileList(list));

        }
        catch (IOException ex) {
        	ex.printStackTrace();
        }
        
    	        
        return "";
    }

}
