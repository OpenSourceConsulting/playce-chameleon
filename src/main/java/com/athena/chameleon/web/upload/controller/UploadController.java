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
package com.athena.chameleon.web.upload.controller;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.athena.chameleon.common.utils.PropertyUtil;
import com.athena.chameleon.common.utils.ThreadLocalUtil;
import com.athena.chameleon.engine.constant.ChameleonConstants;
import com.athena.chameleon.engine.core.MigrationComponent;
import com.athena.chameleon.engine.core.PDFDocGenerator;
import com.athena.chameleon.engine.entity.pdf.PDFMetadataDefinition;
import com.athena.chameleon.engine.entity.upload.Upload;

/**
 * This LoginController class is a Controller class to Upload.
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
@Controller("uploadController")
@RequestMapping("/upload")
public class UploadController {

    @Inject
    @Named("migrationComponent")
    private MigrationComponent component;

    @Inject
    @Named("pdfDocGenerator")
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
    @RequestMapping("/form.do")
    public String showUpload(Model model, HttpSession session) throws Exception {
        
        String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
        if(loginFlag == null || !loginFlag.equals("Y"))
            return "redirect:/login/showLogin.do";
        
        model.addAttribute(new Upload());
        return "/ifrm/upload/uploadForm";
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
    @RequestMapping("/upload.do")
    public String upload(Upload upload, ModelMap modelMap, BindingResult results, SessionStatus status, HttpSession session) throws Exception {

        String loginFlag = String.valueOf(session.getAttribute("loginFlag"));
        if(loginFlag == null || !loginFlag.equals("Y"))
            return "redirect:/login/showLogin.do";
        
        if (results.hasErrors()) {
            return "redirect:/upload/form.do";
        }
        
        try {
            String defaultPath = PropertyUtil.getProperty("chameleon.upload.temp.dir") + File.separator + System.currentTimeMillis()  + File.separator;
            String sourceFile, deployFile = "";
            File migrationFile;
            
            if(upload.getProjectSrc() != null && upload.getProjectSrc().getSize() > 0 ) {
                
                migrationFile = new File(defaultPath+upload.getProjectSrc().getOriginalFilename());
                if (!migrationFile.exists()) {
                    if (!migrationFile.mkdirs()) {
                        throw new Exception("Fail to create a directory for attached file [" + migrationFile + "]");
                    }
                }
                
                migrationFile.deleteOnExit();
                upload.getProjectSrc().transferTo(migrationFile);
                
                sourceFile = migrationFile.getAbsolutePath();
            } else {
                sourceFile = null;
            }
            
            if(upload.getDeploySrc() != null && upload.getDeploySrc().getSize() > 0 ) {
                
                migrationFile = new File(defaultPath+upload.getDeploySrc().getOriginalFilename());
                if (!migrationFile.exists()) {
                    if (!migrationFile.mkdirs()) {
                        throw new Exception("Fail to create a directory for attached file [" + migrationFile + "]");
                    }
                }
                
                migrationFile.deleteOnExit();
                upload.getDeploySrc().transferTo(migrationFile);
                
                deployFile = migrationFile.getAbsolutePath();
                
            } else {
                deployFile = null;
            }
            component.migrate(sourceFile, deployFile, upload);
            
            PDFMetadataDefinition metaData = (PDFMetadataDefinition) ThreadLocalUtil.get(ChameleonConstants.PDF_METADATA_DEFINITION);
            
            modelMap.addAttribute("upload", upload);
            modelMap.addAttribute("result", true);
            modelMap.addAttribute("metaData", metaData);
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
            modelMap.addAttribute("result", false);
        }
        
                
        return "/ifrm/upload/resultForm";
    }

}
