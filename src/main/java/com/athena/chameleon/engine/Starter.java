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
 * Sang-cheon Park     2012. 8. 28.     First Draft.
 */
package com.athena.chameleon.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <pre>
 * CLI(Command Line Interface) 환경에서 Athena Chameleon WAS Migration 실행을 위한 Main 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @since 1.0
 */
public class Starter {
	
    private static Logger logger = LoggerFactory.getLogger(Starter.class);
    
    private static final String[] SUPPORT_ARCHIVE_FORMAT = {"zip"}; 

	/**
	 * <pre>
	 * WAS Migration 실행을 위한 main()는 다음과 같은 작업을 수행한다.
	 *	<ul>
	 *		<li>Migration 실행을 위한 압축파일의 Full Qualified File Name을 인자로 전달하거나 대화식으로 입력받는다.</li>
	 *		<li>전달받은 파일에 대한 유효성 검증을 수행한다.</li>
	 *		<li>Spring Context 파일을 loading 하여 spring 모듈을 초기화 한다.</li>
	 *		<li>Migration engine module을 실행한다.</li>
	 *	</ul>
	 * </pre>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.debug("Starting of Athena Chameleon WAS Migration tool.");
		
		String fqfn = null;
		if(args.length == 0) {
			fqfn = getFileName();
		} else if(args.length == 1) {
			fqfn = args[0].replaceAll("\\\\", "/");
			if(!isExists(fqfn) || !isValidExtension(fqfn)) {
            	System.out.println(fqfn + "은(는) 존재하지 않는 파일이거나 지원하지 않는 파일 형식입니다.");
				fqfn = getFileName();
			}
		} else {
			System.out.println("[Usage] : java -jar athena-chameleon.jar ${file}");
			System.exit(-1);
		}
		
		logger.debug("File => [{}]", fqfn);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-*.xml");
		
		//MigrationComponent component = (MigrationComponent)context.getBean("migrationComponent");
		//component.migrate(fqfn);
	}//end of main()
	
	/**
	 * @return
	 */
	private static String getFileName() {
		String fqfn = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
            System.out.println("+:+:+:+: 파일 경로를 포함한 파일명을 입력해주세요. +:+:+:+:");
            
            do {
                System.out.print("('q' or 'quit' to terminate) => ");
                
                fqfn = br.readLine();
                
                if(fqfn.equals("")) {
                    continue;
                }
                
                if(fqfn.toLowerCase().equals("q") || fqfn.toLowerCase().equals("quit")) {
                    System.exit(1);
                }

                fqfn = fqfn.replaceAll("\\\\", "/");
                
                if(!isValidExtension(fqfn)) {
                	System.out.println("지원하지 않는 파일 형식입니다. 다시 입력해 주십시오.");
                	continue;
                }
                
                if(!isExists(fqfn)) {
                	System.out.println("존재하지 않는 파일입니다. 다시 입력해 주십시오.");
                	continue;
                }
                
                break;
            } while(true);
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다.");
            logger.error("Unhandled exception has occurred.", e);
            System.exit(1);
        }
		
		return fqfn;
	}// end of getFileName()
	
	/**
	 * <pre>
	 * 파일 존재 여부 체크
	 * </pre>
	 * 
	 * @param fqfn
	 * @return
	 */
	private static boolean isExists(String fqfn) {
        return new File(fqfn).exists();
	}//end of isExists()
	
	/**
	 * <pre>
	 * 확장자를 이용한 압축 포맷의 지원 여부
	 * </pre>
	 * 
	 * @param fqfn
	 * @return
	 */
	private static boolean isValidExtension(String fqfn) {
		return ArrayUtils.contains(SUPPORT_ARCHIVE_FORMAT, fqfn.substring(fqfn.lastIndexOf(".") + 1));
	}//end of isValidExtension()
}//end of Starter.java