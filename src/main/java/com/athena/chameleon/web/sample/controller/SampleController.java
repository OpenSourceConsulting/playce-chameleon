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
package com.athena.chameleon.web.sample.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.athena.chameleon.web.login.vo.Login;
import com.athena.chameleon.web.sample.vo.SampleData;
import com.athena.chameleon.web.sample.vo.SampleDataParam;

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
     * dash board 호출(ajax)
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sample/ajax/dashBoard.do")
    public String dashBoardAjax(Model model, HttpSession session) throws Exception {
    	return "/ajax/dashBoard";
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

    /**
     * 
     * grid Data 
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sample/gridData.do")
    public String gridData(SampleDataParam param, Model model, HttpSession session, ModelMap modelMap) throws Exception {
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	
    	int row = (param.getPage()-1)*param.getRows();
    	int totalRow = 30;
    	int lastRow = row+param.getRows();
    	if(lastRow > totalRow) 	lastRow = totalRow;
    	for(int i=row+1; i<=lastRow; i++) {
    		Map<String, Object> map = new HashMap<String, Object>();
        	map.put("id", i); 
        	map.put("name", "홍길동"+i);
        	map.put("amount", "10000"+i);
        	map.put("tax", "1000"+i);
        	
    		list.add(map);
    	}
    	
    	double totalPage;
    	if(totalRow > 0)
    		totalPage = param.getRows() != 0 ? (totalRow - 1) / param.getRows() + 1 : totalRow;
    	else
    		totalPage = 0;
    	
    	SampleData data = new SampleData();
    	
    	data.setRecords(list.size());
    	data.setTotal((int) totalPage);
    	data.setPage(param.getPage());
    	data.setRows(list);
    	
    	modelMap.addAttribute("jsonData", data);
        
        return "jsonView";
    }

    /**
     * 
     * detail sample 호출
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sample/detailForm.do")
    public String detailForm(Model model, HttpSession session) throws Exception {
    	return "/ajax/detailForm";
    }

}

