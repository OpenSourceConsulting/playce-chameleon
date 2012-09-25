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

import java.util.ArrayList;

import org.jdom2.Element;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * PDF Writer 유틸 클래스
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

public class PDFWriterUtil {

    public static BaseFont bfKorean;
    public static Font     fnNormal;
    public static Font     fnNormalBold;
    public static Font     fnBox;
    public static Font     fnBoxRed;
    public static Font     fnChapter;
    public static Font     fnSection;
    public static Font     fnSection2;
    public static Font     fnURL;
    
    static {
        try {
            bfKorean = BaseFont.createFont("HYGoThic-Medium", "UniKS-UCS2-H", BaseFont.NOT_EMBEDDED);
            
            fnNormal     = new Font(bfKorean, 10);
            fnBox        = new Font(bfKorean, 9, Font.UNDEFINED, new BaseColor(40, 40, 40));
            fnBoxRed     = new Font(bfKorean, 9, Font.UNDEFINED, new BaseColor(255, 0, 0));
            fnNormalBold = new Font(bfKorean, 10, Font.BOLD);
            fnChapter    = new Font(bfKorean, 14, Font.BOLD);
            fnSection    = new Font(bfKorean, 12, Font.BOLD);
            fnSection2   = new Font(bfKorean, 11, Font.BOLD);
            fnURL  		 = new Font(bfKorean, 10, Font.UNDERLINE, new BaseColor(0,0,255));
    	    
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
        Paragraph ph = new Paragraph(text, fnNormal);
        ph.setMultipliedLeading(1.8F);
        ph.setIndentationLeft(23);
        ph.setSpacingAfter(14);
        return ph;
    }

    /**
     * Default 출력 형식(Bold)
     *
     * @param text 입력 text
     * @return Paragraph
     */
    public static Paragraph getDefaultPoint(String text) {
        Font font = new Font(bfKorean, 10, Font.BOLD);
        
        Paragraph ph = new Paragraph("· "+text, font);
        ph.setMultipliedLeading(1.8F);
        ph.setIndentationLeft(45);
        ph.setSpacingAfter(14);
        return ph;
    }
    
    /**
     * Default 출력 형식(RED)
     *
     * @param text 입력 text
     * @param style Font style
     * @return Paragraph
     */
    public static Paragraph getDefaultRed(String text, int style) {
        Font font = new Font(bfKorean, 10, style);
        font.setColor(new BaseColor(255,0,0));
        
        Paragraph ph = new Paragraph(text, font);
        ph.setMultipliedLeading(1.8F);
        ph.setIndentationLeft(45);
        ph.setSpacingAfter(14);
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
    public static Section getSection(Section chapter, String text) {
        Section section = chapter.addSection(text);
        String title = section.getTitle().getContent();
        
        Chunk c;
        if(section.getDepth() == 3) {
            c = new Chunk(text, fnSection2);
        } else {
            c = new Chunk(text, fnSection);
        }
        c.setLocalDestination(title);
        
        Paragraph sectionPh = new Paragraph();
        sectionPh.add(c);
        sectionPh.setSpacingBefore(8);
        sectionPh.setSpacingAfter(3);
        if(section.getDepth() == 3)
            sectionPh.setIndentationLeft(23);
        
        section.setTitle(sectionPh);
        return section;
    }
    
    /**
     * 
     * Section 생성 및 Element Mapping
     * 
     * @param parent parent 객체
     * @param e element 정보
     * @return Section
     * @throws Exception
     */
    public static Section setSectionElement(Section parent, Element e) throws Exception {
    	
    	Section section = getSection(parent, e.getAttributeValue("title"));
    	return setElement(section, e);
        
    }
    
    /**
     * Element Mapping
     * 
     * @param section section 객체
     * @param e element 정보
     * @return Section
     * @throws Exception
     */
    public static Section setElement(Section section, Element e) throws Exception {

    	
        for(org.jdom2.Element e1 : e.getChildren()) {
            
            if(e1.getName().equals("section")) {
                setSectionElement(section, e1);
                
            } else if(e1.getName().equals("text")) {
                
                if(e1.getAttributeValue("padding") != null) {
                    Paragraph text = getDefault(e1.getText());
                    text.setIndentationLeft(text.getIndentationLeft()+Float.parseFloat(e1.getAttributeValue("padding")));
                    
                    section.add(text);
                } else {
                    section.add(getDefault(e1.getText()));
                }
            
            } else if(e1.getName().equals("table")) {
                setTable(section, e1);
                
            } else if(e1.getName().equals("textR")) {
                section.add(getDefaultRed(e1.getText(), Font.ITALIC));
                
            } else if(e1.getName().equals("textP")) {
                section.add(getDefaultPoint(e1.getText()));
                
            } else if(e1.getName().equals("box")) {
                setBox(section, e1);
                
            } else if(e1.getName().equals("list")) {
            	setList(section, e1);
            }
        }
        
        return section;
    
    }
    
    /**
     * 
     * 기본 Table 구성
     *
     * @param section table이 들어갈 section 객체
     * @param e table 정보가 들어있는 element
     * @throws Exception
     */
    public static void setTable(Section section, Element e) throws Exception {
        
        int colSize = Integer.parseInt(e.getAttributeValue("size"));
        
        PdfPTable t = new PdfPTable(colSize);
        if(e.getAttributeValue("width") != null)
            t.setTotalWidth(Float.parseFloat(e.getAttributeValue("width")));
        
        t.setSpacingAfter(12);
        t.getDefaultCell().setFixedHeight(22); 
        t.getDefaultCell().setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        
        setTableHeader(e, t, colSize);
        setTableRow(e, t);
        
        section.add(t);
    }
    
    /**
     * 
     * 기본 box 구성
     *
     * @param section table이 들어갈 section 객체
     * @param e box 정보가 들어있는 element
     * @throws Exception
     */
    public static void setBox(Section section, Element e) throws Exception {

        PdfPTable t = new PdfPTable(1);
        t.setSpacingBefore(1);
        t.setSpacingAfter(12);
        
        if(e.getAttributeValue("width") != null)
            t.setTotalWidth(Float.parseFloat(e.getAttributeValue("width")));
        
        PdfPCell cell = new PdfPCell();
        if(e.getAttributeValue("option") != null) {
            
            ColumnText col = new ColumnText(null);
            for(Element e1 : e.getChildren()) {
                 if(e1.getAttributeValue("type").equals("red"))
                     col.addText(new Phrase(e1.getText(), fnBoxRed));
                 else
                     col.addText(new Phrase(e1.getText(), fnBox));
            }
            cell.setColumn(col);
            
        } else {
            cell.setPhrase(new Phrase(e.getText(), fnBox));    
        }
        
        cell.setPaddingLeft(10);
        cell.setLeading(0.0F, 1.8F);
        cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(new BaseColor(238, 236, 225));
        
        t.addCell(cell);
        
        section.add(t);
        
    }
    
    public static void setList(Section section, Element e) throws Exception {
    	List list = new List(false, 15);
    	list.setIndentationLeft(23);
    	for(Element e1 : e.getChildren()) {
    		ListItem item = new ListItem(e1.getText(), fnNormal);
    		item.setMultipliedLeading(1.8F);
            list.add(item);
        }
    	list.getFirstItem().setSpacingBefore(-14);
    	list.getLastItem().setSpacingAfter(14);
    	
    	section.add(list);
    }
    
    /**
     * 
     * 테이블의 header 구성
     *
     * @param e table 정보가 들어있는 element
     * @param t header를 구성할 table 객체
     * @param colCount column 갯수
     * @throws Exception
     */
    public static void setTableHeader(Element e, PdfPTable t, int colCount) throws Exception {

        t.getDefaultCell().setBackgroundColor(new BaseColor(217, 217, 217));
        t.getDefaultCell().setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        
        ArrayList<Integer> colWidth = new ArrayList<Integer>();
        for(Element e1 : e.getChild("header").getChildren()) {
            t.addCell(new Phrase(e1.getText(), fnNormalBold));
            
            if(e1.getAttributeValue("width") != null)
                colWidth.add(Integer.parseInt(e1.getAttributeValue("width")));
        }
        
        if(colCount == colWidth.size()) {
            int[] col = new int[colCount]; 
            for(int i=0;i<colCount;i++)
                col[i] = colWidth.get(i);
                
            t.setWidths(col);
        }
        
    }

    /**
     * 
     * 테이블의 row 구성
     *
     * @param e table 정보가 들어있는 element
     * @param t header를 구성할 table 객체
     * @throws Exception
     */
    public static void setTableRow(Element e, PdfPTable t) throws Exception {

        t.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
        t.getDefaultCell().setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
        
        for(Element e1 : e.getChild("row").getChildren()) {
            t.addCell(new Phrase(e1.getText(), fnNormal));
        }
        
    }
    
}
//end of PDFUtil.java