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
 * Sang-cheon Park	2012. 10. 5.		First Draft.
 */
package com.athena.chameleon.web.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {

	@RequestMapping("/download.do")
	public void download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("path") String path) throws Exception {
		
		Assert.notNull(path, "name must not be null.");
		
		File file = new File(path);
		
		Assert.isTrue(file.exists(), path + " does not exist.");
		
		String name = path.replaceAll("\\\\", "/");
		name = name.substring(name.lastIndexOf("/")+1, name.length());
		
		long fileSize = file.length();
		
		if (fileSize > 0L) {
			response.setHeader("Content-Length", Long.toString(fileSize));
		}

		String fileExt = name.substring(name.lastIndexOf(".") + 1);

		response.reset();

		if (fileExt.equals("pdf")) {
			response.setContentType("application/pdf");
		} else if (fileExt.equals("zip")) {
			response.setContentType("application/zip");
		} else if (fileExt.equals("ear")) {
			response.setContentType("application/zip");
		} else if (fileExt.equals("war")) {
			response.setContentType("application/zip");
		} else if (fileExt.equals("jar")) {
			response.setContentType("application/zip");
		} else {
			response.setContentType("application/octet-stream");
		}
		
        if(request.getHeader("User-Agent").toLowerCase().contains("firefox") || request.getHeader("User-Agent").toLowerCase().contains("safari")) { 
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(name.getBytes("UTF-8"), "ISO-8859-1") + "\""); 
            response.setHeader("Content-Transfer-Encoding", "binary");
        } else { 
            response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        }

		try {
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
			
			int read = 0;
			while ((read = fin.read()) != -1) {
				outs.write(read);
			}
			
			IOUtils.closeQuietly(fin);
			IOUtils.closeQuietly(outs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end of download()

}
//end of FileController.java