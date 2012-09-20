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

import com.athena.chameleon.common.utils.MessageUtil;
import com.athena.chameleon.engine.core.PDFCommonEventHalper;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PDF Writer 유틸 클래스
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

public class PDFWriterUtil {

    public static BaseFont bfKorean;
    
    static {
        try {
            bfKorean = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Default 출력 형식
     *
     * @param text 입력 text
     * @return Paragraph
     */
    public static Paragraph getDefault(String text) {
        Font fnNormal = new Font(bfKorean, 10);
        Paragraph ph = new Paragraph(text, fnNormal);
        ph.setIndentationLeft(30);
        ph.setSpacingAfter(2);
        return ph;
    }
    
    /**
     * Chapter 출력 형식
     *
     * @param text Chapter title
     * @param chapterNo Chapter Number
     * @return Chapter
     */
    public static Chapter getChapter(String text, int chapterNo) {
        Font fnChapter = new Font(bfKorean, 14, Font.BOLD);
        
        Chapter chapter = new Chapter(text, chapterNo);
        String title = chapter.getTitle().getContent();
        
        Chunk c = new Chunk(text, fnChapter);
        c.setLocalDestination(title);
        Paragraph chapterPh = new Paragraph();
        chapterPh.add(c);
        chapterPh.setSpacingAfter(12);
        chapter.setTitle(chapterPh);
        return chapter;

    }
    
    /**
     * 
     * Section 출력 형식
     *
     * @param chapter section이 들어갈 chapter 객체
     * @param text section title
     * @param sectionNo section Number
     * @return Section
     */
    public static Section getSection(Chapter chapter, String text, int sectionNo) {
        Font fnSection = new Font(bfKorean, 12, Font.BOLD);
        
        Section section = chapter.addSection(text);
        String title = section.getTitle().getContent();
        
        Chunk c = new Chunk(text, fnSection);
        c.setLocalDestination(title);
        
        Paragraph sectionPh = new Paragraph();
        sectionPh.add(c);
        sectionPh.setSpacingBefore(8);
        sectionPh.setSpacingAfter(3);
        section.setTitle(sectionPh);
        return section;
    }
    
    /**
     * 목차 생성
     *
     * @param doc 
     * @param writer 
     * @param event
     * @throws Exception
     */
    public static void setChapterSectionTOC(Document doc, PdfWriter writer, PDFCommonEventHalper event) throws Exception {
        
    	doc.newPage();
        event.setPagingFlag(false);
        
        Paragraph title = new Paragraph(MessageUtil.getMessage("pdf.message.toc.title"), new Font(bfKorean, 13, Font.BOLD));
        title.setSpacingAfter(8);
        doc.add(title);
        
        int toc = writer.getPageNumber();
        for(Paragraph p : event.titles)
            doc.add(p);
        
        doc.newPage();
        int total = writer.reorderPages(null);
        
        int[] order = new int[total];
        for (int i = 0; i < total; i++) {
            order[i] = i + toc;
            if (order[i] > total)
                order[i] -= total;
        }
        // apply the new order
        writer.reorderPages(order);

    }
}
//end of PDFUtil.java