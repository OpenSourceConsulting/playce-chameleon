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

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
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
        
        titles.add(new Paragraph(title.getContent(), new Font(bfKorean, 10)));
    }

    public void onChapterEnd(PdfWriter writer, Document document,
            float position) {/*
        drawLine(writer.getDirectContent(),
                document.left(), document.right(), position - 5);
                */
    }

    public void onSection(PdfWriter writer, Document document,
            float position, int depth, Paragraph title) {
        title = new Paragraph(title.getContent(), new Font(bfKorean, 10));
        title.setIndentationLeft(18 * depth);
        titles.add(title);
    }

    public void onSectionEnd(PdfWriter writer, Document document,
            float position) {
        /*
        drawLine(writer.getDirectContent(),
                document.left(), document.right(), position - 3);
                */
    }

    public void drawLine(PdfContentByte cb, float x1, float x2, float y) {
        cb.moveTo(x1, y);
        cb.lineTo(x2, y);
        cb.stroke();
    }
}
//end of ChapterSectionTOC.java