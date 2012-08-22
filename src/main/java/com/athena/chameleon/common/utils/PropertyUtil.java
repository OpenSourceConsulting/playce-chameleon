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
 * Sang-cheon Park	2012. 8. 20.		First Draft.
 */
package com.athena.chameleon.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * context.property 파일을 로드하여 주어진 key에 해당하는 value 값을 조회하기 위해 사용하는 유틸 클래스.
 * - 호출 빈도가 높은 메소드는 PropertyUtil 에서 구현하고, 그 이외의 메소드는 PropertyUtil.getProperties() 후 사용
 * - context.property 이외의 다른 Property 파일이 추가될 경우 추가 구현 필요.
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class PropertyUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    
    private static Properties properties;
    private static Exception exception;
    
    static {
        properties = new Properties();
        
        try {
            properties.load(PropertyUtil.class.getClass().getResourceAsStream("/config/context.properties"));
        } catch (FileNotFoundException e) {
            logger.error("context.properties 파일이 존재하지 않습니다.", e);
            exception = e;
        } catch (IOException e) {
            logger.error("context.properties 파일을 읽을 수 없습니다.", e);
            exception = e;
        } catch (Exception e) {
            logger.error("context.properties 파일을 읽을 수 없습니다.", e);
            exception = e;
        }
    }
    
    /**
     * @return the properties
     */
    public static Properties getProperties() {
        return properties;
    }

    /**
     * <pre>
     * 주어진 key에 해당하는 property 값을 반환한다.
     * </pre>
     *
     * @param key
     * @return value
     * @throws Exception context.property 파일을 정상적으로 load 하지 못한 상태
     */
    public static String getProperty(String key) throws Exception {
        if(exception != null) {
            throw exception;
        }
        
        return properties.getProperty(key);
    }//end of getProperty()
    
    // 호출 빈도가 높은 메소드는 PropertyUtil에서 구현, 그 이외의 메소드는 
    
}//end of PropertyUtil.java