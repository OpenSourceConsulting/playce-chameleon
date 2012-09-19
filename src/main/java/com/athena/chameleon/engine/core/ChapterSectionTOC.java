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
 * Hyo-jeong Lee	2012. 9. 17.		First Draft.
 */
package com.athena.chameleon.engine.core;

import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */
public class ChapterSectionTOC extends PdfPageEventHelper {

    private static BaseFont bfKorean;
    
    static {
        try {
            bfKorean = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /** List with the titles. */
    public static List<Paragraph> titles = new ArrayList<Paragraph>();

    public void onChapter(PdfWriter writer, Document document,
            float position, Paragraph title) {
        
        titles.add(getTocParagraph(title.getContent(), writer.getPageNumber(), 0, document.left(), document.right()));
        
    }

    public void onSection(PdfWriter writer, Document document,
            float position, int depth, Paragraph title) {
        title = getTocParagraph(title.getContent(), writer.getPageNumber(), depth, document.left(), document.right());
        title.setIndentationLeft(12 * depth);
        titles.add(title);
    }

    public void drawLine(PdfContentByte cb, float x1, float x2, float y) {
        cb.moveTo(x1, y);
        cb.lineTo(x2, y);
        cb.stroke();
    }

    public Paragraph getTocParagraph(String title, int pageNumber, int depth, float x1, float x2) {
        
        Font tocFont = new Font(bfKorean, 10);
        if(depth == 0)
            tocFont.setStyle(Font.BOLD);
        
        Paragraph p = new Paragraph();
        p.setSpacingAfter(5);
            
        Chunk tit = new Chunk(title+" ", tocFont);
        tit.setAction(PdfAction.gotoLocalPage(title, false));
        
        Chunk point = new Chunk(".", tocFont);
        
        Chunk number = new Chunk(" "+pageNumber, tocFont);
        number.setAction(PdfAction.gotoLocalPage(title, false));
        
        p.add(tit);
        
        float width = x2-x1-tit.getWidthPoint()-number.getWidthPoint()-(depth*12);
        
        float i=point.getWidthPoint();
        while(i < width) {
            p.add(point);
            i += point.getWidthPoint();
        }
        
        p.add(number);
        
        
        return p;
    }
}
//end of ChapterSectionTOC.java
