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
 * Hyo-jeong Lee	2012. 9. 14.		First Draft.
 */
package com.athena.chameleon.engine.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;

/**
 * PDF Writer 유틸 클래스
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

public class PDFWriterUtil {

    private static final Log logger = LogFactory.getLog(PDFWriterUtil.class);

    private static BaseFont bfKorean;
    
    static {
        try {
            bfKorean = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Paragraph getDefault(String text) {
        Font fnNormal = new Font(bfKorean, 10);
        Paragraph ph = new Paragraph(text, fnNormal);
        ph.setIndentationLeft(30);
        ph.setSpacingAfter(2);
        return ph;
    }
    
    public static Chapter getChapter(String text, int chapterNo) {
        Font fnChapter = new Font(bfKorean, 14, Font.BOLD);
                
        Paragraph chapterPh = new Paragraph(text, fnChapter);
        chapterPh.setSpacingAfter(12);
        Chapter chapter = new Chapter(chapterPh, chapterNo);
        return chapter;

    }
    
    public static Section getSection(Chapter chapter, String text) {
        Font fnSection = new Font(bfKorean, 12, Font.BOLD);
        
        Paragraph sectionPh = new Paragraph(text, fnSection);
        sectionPh.setSpacingBefore(8);
        sectionPh.setSpacingAfter(3);
        Section section = chapter.addSection(sectionPh);
        
        return section;
    }
}
//end of PDFUtil.java