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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.athena.chameleon.engine.core.MigrationComponent;
import com.athena.chameleon.engine.entity.upload.Upload;

/**
 * <pre>
 * CLI(Command Line Interface) 환경에서 Athena Chameleon WAS Migration 실행을 위한 Main 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @since 1.0
 */
public class Starter {
	
    private static final Logger logger = LoggerFactory.getLogger(Starter.class);

    private static final String SUPPORT_SOURCE_FORMAT = "zip";
    private static final String[] SUPPORT_DEPLOY_FORMAT = {"ear", "war", "jar"}; 
    
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
		
		String sourceFile = null;
		String deployFile = null;
		if(args.length == 0) {
			sourceFile = getSourceFileName();
			deployFile = getApplicationFileName();
		} else if(args.length == 1) {
			sourceFile = args[0].replaceAll("\\\\", "/");
			if(!isExists(sourceFile) || !isValidSourceExtension(sourceFile)) {
            	System.out.println(sourceFile + "은(는) 존재하지 않는 파일이거나 지원하지 않는 파일 형식입니다.");
				//sourceFile = getSourceFileName();
			}
			deployFile = getApplicationFileName();
		} else if(args.length == 2) {
			sourceFile = args[0].replaceAll("\\\\", "/");
			if(!isExists(sourceFile) || !isValidSourceExtension(sourceFile)) {
            	System.out.println(sourceFile + "은(는) 존재하지 않는 파일이거나 지원하지 않는 파일 형식입니다.");
				//sourceFile = getSourceFileName();
			}

			deployFile = args[1].replaceAll("\\\\", "/");
			if(!isExists(deployFile) || !isValidApplicationExtension(deployFile)) {
            	System.out.println(deployFile + "은(는) 존재하지 않는 파일이거나 지원하지 않는 파일 형식입니다.");
            	//deployFile = getApplicationFileName();
			}
		} else {
			System.out.println("[Usage] : java -jar athena-chameleon.jar ${Project Source Archive File} ${Application Archive File}");
			System.exit(-1);
		}
		
		if(StringUtils.isEmpty(sourceFile) && StringUtils.isEmpty(deployFile)) {
			System.out.println("[Error] 입력된 파일 정보가 없기 때문에 프로그램을 종료합니다.");
			System.exit(-1);
		}
		
		Upload upload = new Upload();
		upload.setProjectNm(getProjectName());
		upload.setAfterWas(getTargetWas());
		upload.setDepartment(getDepartment());
		upload.setPerson(getManagerName());
		
		logger.debug("Project Source File => [{}]", sourceFile);
		logger.debug("Application Archive File => [{}]", deployFile);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/context-*.xml");
		
		MigrationComponent component = (MigrationComponent)context.getBean("migrationComponent");
		component.migrate(sourceFile, deployFile, upload);
	}//end of main()
	
	/**
	 * @return
	 */
	private static String getSourceFileName() {
		String fqfn = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
            System.out.println("+:+:+:+: [프로젝트 소스(zip)] 파일 경로를 포함한 파일명을 입력해주세요. +:+:+:+:");
            
            do {
                System.out.print("('q' or 'quit' to terminate and press ENTER to skip) => ");
                
                fqfn = br.readLine();
                
                if(fqfn.equals("")) {
                	return null;
                	/*
                	System.out.print("입력된 파일 정보가 없습니다. 애플리케이션 파일 입력으로 이동하시겠습니까? (y/n - default 'n') => ");
                	
                	String skip = br.readLine();
                	
                	if(skip.toLowerCase().equals("y") || skip.toLowerCase().equals("yes")) {
                		return null;
                	} else {
                		continue;
                	}
                	*/
                }
                
                if(fqfn.toLowerCase().equals("q") || fqfn.toLowerCase().equals("quit")) {
                    System.exit(1);
                }

                fqfn = fqfn.replaceAll("\\\\", "/");
                
                if(!isValidSourceExtension(fqfn)) {
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
	}// end of getSourceFileName()
	
	/**
	 * @return
	 */
	private static String getApplicationFileName() {
		String fqfn = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
            System.out.println("+:+:+:+: [Deploy 애플리케이션(ear, war, jar)] 파일 경로를 포함한 파일명을 입력해주세요. +:+:+:+:");
            
            do {
                System.out.print("('q' or 'quit' to terminate and press ENTER to skip) => ");
                
                fqfn = br.readLine();
                
                if(fqfn.equals("")) {
                	return null;
                	/*
                	System.out.print("입력된 파일 정보가 없습니다. 계속하시겠습니까? (y/n - default 'y') => ");
                	
                	String skip = br.readLine();
                	
                	if(skip.toLowerCase().equals("n") || skip.toLowerCase().equals("no")) {
                		return null;
                	} else {
                		continue;
                	}
                	*/
                }
                
                if(fqfn.toLowerCase().equals("q") || fqfn.toLowerCase().equals("quit")) {
                    System.exit(1);
                }

                fqfn = fqfn.replaceAll("\\\\", "/");
                
                if(!isValidApplicationExtension(fqfn)) {
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
	}// end of getApplicationFileName()
	
	/**
	 * @return
	 */
	private static String getProjectName() {
		String name = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
            System.out.println("+:+:+:+: 프로젝트명을 입력해주세요. +:+:+:+:");
            System.out.print("('q' or 'quit' to terminate and press ENTER to skip) => ");
            
            name = br.readLine();
            
            if(name.equals("")) {
            	return null;
            }
            
            if(name.toLowerCase().equals("q") || name.toLowerCase().equals("quit")) {
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다.");
            logger.error("Unhandled exception has occurred.", e);
            System.exit(1);
        }
		
		return name;
	}// end of getProjectName()
	
	/**
	 * @return
	 */
	private static String getTargetWas() {
		String was = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
            System.out.println("+:+:+:+: 변환 대상 WAS를 선택해주세요.(1 : Tomcat, 2 : JBoss) +:+:+:+:");

            do {
	            System.out.print("('q' or 'quit' to terminate and press ENTER to skip) => ");
	            
	            was = br.readLine();
	            
	            if(was.equals("")) {
	            	continue;
	            }
	            
	            if(was.toLowerCase().equals("q") || was.toLowerCase().equals("quit")) {
	                System.exit(1);
	            }
	            
	            if(!was.equals("1") && !was.equals("2")) {
	            	System.out.println("잘 못 입력하였습니다. 다시 입력해 주십시오.");
	            	System.out.println("+:+:+:+: 변환 대상 WAS를 선택해주세요.(1 : Tomcat, 2 : JBoss) +:+:+:+:");
	            	continue;
	            }
	            break;
	        } while(true);
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다.");
            logger.error("Unhandled exception has occurred.", e);
            System.exit(1);
        }
		
		if(was.equals("1")) {
			return "T";
		} else {
			return "B";
		}
	}// end of getTargetWas()
	
	/**
	 * @return
	 */
	private static String getDepartment() {
		String name = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
            System.out.println("+:+:+:+: 부서명을 입력해주세요. +:+:+:+:");
            System.out.print("('q' or 'quit' to terminate and press ENTER to skip) => ");
            
            name = br.readLine();
            
            if(name.equals("")) {
            	return null;
            }
            
            if(name.toLowerCase().equals("q") || name.toLowerCase().equals("quit")) {
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다.");
            logger.error("Unhandled exception has occurred.", e);
            System.exit(1);
        }
		
		return name;
	}// end of getDepartment()
	
	/**
	 * @return
	 */
	private static String getManagerName() {
		String name = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
            System.out.println("+:+:+:+: 담당자명을 입력해주세요. +:+:+:+:");
            System.out.print("('q' or 'quit' to terminate and press ENTER to skip) => ");
            
            name = br.readLine();
            
            if(name.equals("")) {
            	return null;
            }
            
            if(name.toLowerCase().equals("q") || name.toLowerCase().equals("quit")) {
                System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("알 수 없는 오류가 발생했습니다.");
            logger.error("Unhandled exception has occurred.", e);
            System.exit(1);
        }
		
		return name;
	}// end of getManagerName()
	
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
	 * 확장자를 이용한 프로젝트 소스 압축 포맷의 지원 여부
	 * </pre>
	 * 
	 * @param fqfn
	 * @return
	 */
	private static boolean isValidSourceExtension(String fqfn) {
		return SUPPORT_SOURCE_FORMAT.equals(fqfn.substring(fqfn.lastIndexOf(".") + 1).toLowerCase());
	}//end of isValidExtension()
	
	/**
	 * <pre>
	 * 확장자를 이용한 애플리케이션 압축 포맷의 지원 여부
	 * </pre>
	 * 
	 * @param fqfn
	 * @return
	 */
	private static boolean isValidApplicationExtension(String fqfn) {
		return ArrayUtils.contains(SUPPORT_DEPLOY_FORMAT, fqfn.substring(fqfn.lastIndexOf(".") + 1).toLowerCase());
	}//end of isValidExtension()
}//end of Starter.java