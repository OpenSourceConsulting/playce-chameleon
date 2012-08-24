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
 * Author              Date             Description
 * ------------------  --------------   ------------------
 * Sang-cheon Park     2012. 8. 24.     First Draft.
 */
package com.athena.chameleon.engine.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.bind.v2.WellKnownNamespace;

/**
 * <pre>
 * OXM(Object XML Mapping)을 위한 Simple Utility Class
 * </pre>
 * @author 박상천
 * @version 1.0
 */
public abstract class SimpleOxmUtils {
    
    private static final String DEFAULT_ENCODING = "UTF-8";
    
    /**
     * <pre>
     * 기본 인코딩으로 UTF-8을 사용하여 Object를 XML로 변환한다.
     * </pre>
     * @param obj Source Object
     * @return xml 변환 문자열
     * @throws JAXBException Object -> XML 변환 시 발생 가능한 예외
     * @throws IOException Object -> XML 변환 시 발생 가능한 예외
     */
    public static String marshal(Object obj) throws JAXBException, IOException {
        return marshal(obj, DEFAULT_ENCODING, null, null, null);
    }//end of marshal()

    /**
     * <pre>
     * 주어진 인코딩을 사용하여 Object를 XML로 변환한다.
     * </pre>
     * @param obj Source Object
     * @param encoding 변환할 encoding 타입
     * @return xml 변환 문자열
     * @throws JAXBException Object -> XML 변환 시  발생 가능한 예외
     * @throws IOException Object -> XML 변환 시 발생 가능한 예외
     */
    public static String marshal(Object obj, String encoding) throws JAXBException, IOException {
        return marshal(obj, encoding, null, null, null);
    }//end of marshal()

    /**
     * <pre>
     * Namespace 및 schemaLocation을 지정하여 Object를 XML로 변환한다.
     * </pre>
     * @param obj Source Object
     * @param schemaLocations 프로퍼티에 추가할 schemaLocation 목록
     * @param mapper Namespace Mapper
     * @return xml 변환 문자열
     * @throws JAXBException Object -> XML 변환 시  발생 가능한 예외
     * @throws IOException Object -> XML 변환 시 발생 가능한 예외
     */
    public static String marshal(Object obj, String[] schemaLocations, NamespacePrefixMapper mapper) throws JAXBException, IOException {
        return marshal(obj, DEFAULT_ENCODING, null, schemaLocations, mapper);
    }//end of marshal()

    /**
     * <pre>
     * 주어진 인코딩과 Namespace 및 schemaLocation을 지정하여 Object를 XML로 변환한다.
     * </pre>
     * @param obj Source Object
     * @param encoding 변환할 encoding 타입
     * @param schemaLocations 프로퍼티에 추가할 schemaLocation 목록
     * @return xml 변환 문자열
     * @throws JAXBException Object -> XML 변환 시  발생 가능한 예외
     * @throws IOException Object -> XML 변환 시 발생 가능한 예외
     */
    public static String marshal(Object obj, String encoding, String[] schemaLocations, NamespacePrefixMapper mapper) throws JAXBException, IOException {
        return marshal(obj, encoding, null, schemaLocations, mapper);
    }

    /**
     * <pre>
     * 주어진 인코딩과 Namespace 및 schemaLocation을 지정하여 Object를 XML로 변환한다.
     * </pre>
     * @param obj Source Object
     * @param encoding 변환할 encoding 타입
     * @param schema schema 
     * @param schemaLocations 프로퍼티에 추가할 schemaLocation 목록
     * @return xml 변환 문자열
     * @throws JAXBException Object -> XML 변환 시  발생 가능한 예외
     * @throws IOException Object -> XML 변환 시 발생 가능한 예외
     */
    public static String marshal(Object obj, String encoding, Schema schema, String[] schemaLocations, NamespacePrefixMapper mapper) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshal = context.createMarshaller();
        
        if(encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
        
        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshal.setProperty(Marshaller.JAXB_ENCODING, encoding);
        
        ByteArrayOutputStream btArrOutPutStream = new ByteArrayOutputStream();
        
        if(schemaLocations != null && schemaLocations.length > 0) {
            String schemaLocation = new String();
            
            for(int i = 0; i < schemaLocations.length; i++) {
                if(i > 0) {
                    schemaLocation += " ";
                }
                schemaLocation += schemaLocations[i];
            }
            
            marshal.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schemaLocation);
        }
        
        if(mapper == null || !(mapper instanceof NamespacePrefixMapper)) {
            mapper = new NamespacePrefixMapper() {
                @Override
                public String[] getPreDeclaredNamespaceUris() {
                    return new String[] {WellKnownNamespace.XML_SCHEMA_INSTANCE};
                }

                @Override
                public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
                    if (namespaceUri.equals(WellKnownNamespace.XML_SCHEMA_INSTANCE)) {
                        return "xsi";
                    } else if (namespaceUri.equals(WellKnownNamespace.XML_SCHEMA)) {
                        return "xs";
                    } else if (namespaceUri.equals(WellKnownNamespace.XML_MIME_URI)) {
                        return "xmime";
                    }
                    
                    return suggestion;
                }
            };
        }
        
        marshal.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);

        if (schema != null){
            marshal.setSchema(schema);
        }

        marshal.marshal(obj, btArrOutPutStream); 
        
        btArrOutPutStream.close();
        return btArrOutPutStream.toString();
    }//end of marshal()
    
    /**
     * <pre>
     * XML 문자열을 Object로 변환한다.
     * </pre>
     * @param xml Source XML 문자열
     * @param clazz 변환될 Target Object Class
     * @return 변환된 Object
     * @throws JAXBException XML -> Object binding 시 발생 가능한 예외
     */
    public static <E> E unmarshal(String xml, Class<E> clazz) throws JAXBException {
        Schema schema = null;       
        return unmarshal(xml, clazz, schema);
    }//end of unmarshal()
    
    /**
     * <pre>
     * Source XML File을 Object로 변환한다.
     * </pre>
     * @param file Source XML File
     * @param clazz 변환될 Target Object Class
     * @return 변환된 Object
     * @throws JAXBException XML -> Object binding 시 발생 가능한 예외
     */
    public static <E> E unmarshal(File file, Class<E> clazz) throws JAXBException {
        Schema schema = null;       
        return unmarshal(file, clazz, schema);
    }//end of unmarshal()
    
    /**
     * <pre>
     * Schema File을 이용하여 XML 문자열을 Object로 변환한다.
     * </pre>
     * @param xml Source XML 문자열
     * @param clazz 변환될 Target Object Class
     * @param schemaFile 변환시 사용될 XSD Schema File
     * @return 변환된 Object
     * @throws SAXException XML -> Object binding 시 발생 가능한 예외
     * @throws JAXBException XML -> Object binding 시 발생 가능한 예외
     */
    public static <E> E unmarshal(String xml, Class<E> clazz, File schemaFile) throws SAXException, JAXBException {
        Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(schemaFile);        
        return unmarshal(xml, clazz, schema);
    }//end of unmarshal()
    
    /**
     * <pre>
     * XML 문자열을 Object로 변환한다.
     * </pre>
     * @param xml Source XML 문자열
     * @param clazz 변환될 Target Object Class
     * @param schema 변환시 사용될 XSD Schema
     * @return 변환된 Object
     * @throws JAXBException XML -> Object binding 시 발생 가능한 예외
     */
    public static <E> E unmarshal(String xml, Class<E> clazz, Schema schema) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);
        Object obj = unmarshaller.unmarshal(new StringReader(xml));        
        return clazz.cast(obj);
    }//end of unmarshal()
    
    /**
     * <pre>
     * Source XML File을 Object로 변환한다.
     * </pre>
     * @param file Source XML File
     * @param clazz 변환될 Target Object Class
     * @param schema 변환시 사용될 XSD Schema
     * @return 변환된 Object
     * @throws JAXBException XML -> Object binding 시 발생 가능한 예외
     */
    public static <E> E unmarshal(File file, Class<E> clazz, Schema schema) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);
        Object obj = unmarshaller.unmarshal(file);  
        
        System.out.println(obj.getClass().getCanonicalName());
        
        return clazz.cast(obj);
    }//end of unmarshal()
}//end of SimpleOxmUtils.java