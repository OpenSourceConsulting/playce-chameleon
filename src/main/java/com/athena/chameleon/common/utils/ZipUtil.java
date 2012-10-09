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
 * Sang-cheon Park	2012. 9. 22.		First Draft.
 */
package com.athena.chameleon.common.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.ManifestException;
import org.apache.tools.ant.types.FileSet;
import org.springframework.util.Assert;

import com.athena.chameleon.engine.entity.pdf.ArchiveType;

/**
 * <pre>
 * zip, ear, war, jar 파일에 대한 압축해제 및 재압축 기능을 지원하는 유틸리트 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ZipUtil {
	
	/**
	 * <pre>
	 * 지정된 디렉토리를 "디렉토리명.zip" 으로 압축한다.
	 * </pre>
	 * @param baseDir
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws ManifestException 
	 */
	public static boolean compress(String baseDir, ArchiveType type) throws IOException, ManifestException {
		return compress(baseDir, null, type);
	}//end of compress()

	/**
	 * <pre>
	 * 지정된 디렉토리를 지정된 이름으로 압축한다.
	 * </pre>
	 * @param baseDir
	 * @param destFile
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws ManifestException 
	 */
	public static boolean compress(String baseDir, String destFile, ArchiveType type) throws IOException, ManifestException {
		Assert.notNull(baseDir, "baseDir cannot be null.");
		
		Project project = new Project();
		
		File filesetDir = new File(baseDir);
		File archiveFile = null;
		
		Assert.isTrue(filesetDir.exists(), baseDir + " does not exist.");
		Assert.isTrue(filesetDir.isDirectory(), baseDir + " is not a directory.");
		
		if(StringUtils.isEmpty(destFile)) {
			archiveFile = new File(filesetDir.getParent(), new StringBuilder(filesetDir.getName()).append(".").append(type.value()).toString());
		} else {
			archiveFile = new File(destFile);
		}
		
		Jar jar = new Jar();
		jar.setProject(project);
		
		// Set fileset information
		jar.setDestFile(archiveFile);
		
		FileSet fileSet = new FileSet();
		fileSet.setDir(filesetDir);
		jar.addFileset(fileSet);
		
		// Create manifest if archive type is one of ear, war and jar.
		if(type.equals(ArchiveType.EAR) || type.equals(ArchiveType.WAR) || type.equals(ArchiveType.JAR)) {
			File maniFile = new File(baseDir, "META-INF/manifest.mf");
			
			if(!maniFile.exists()) {
				maniFile = new File(baseDir, "META-INF/MANIFEST.MF");
			}
			
			if(maniFile.exists()) {
				Manifest manifest = new Manifest(new FileReader(maniFile));
		        jar.addConfiguredManifest(manifest);
			}
		}
		
		jar.execute();
		
		return true;
	}//end of compress()
	
	/**
	 * <pre>
	 * 지정된 압축파일을 같은 디렉토리에 압축파일 명으로 압축 해제한다. 
	 * </pre>
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	public static boolean decompress(String sourceFile) throws IOException {
        return decompress(sourceFile, null);
    }//end of decompress()

    /**
     * <pre>
     * 지정된 압축파일을 지정된 경로에 압축 해제한다.
     * </pre>
     * @param sourceFile
     * @param destDir
     * @return
     * @throws IOException
     */
    public static boolean decompress(String sourceFile, String destDir) throws IOException {
		Assert.notNull(sourceFile, "sourceFile cannot be null.");
		
		Project project = new Project();
		
		File src = new File(sourceFile);
		File dest = null;

		Assert.isTrue(src.exists(), src + " does not exist.");
		Assert.isTrue(src.isFile(), sourceFile + " is not a file.");
		
		if(StringUtils.isEmpty(destDir)) {
			dest = new File(src.getParent(), src.getName().substring(0, src.getName().lastIndexOf(".")));
		} else {
			dest = new File(destDir);
		}
		
		Expand zip = new Expand();
		zip.setProject(project);
		
		// Set fileset information
		zip.setSrc(src);
		zip.setDest(dest);
		
		zip.execute();
		
		return true;
    }//end of decompress()
    
    public static void main(String[] args) {
    	try {
			ZipUtil.decompress("/Users/nices96/Desktop/ziptest/physicial.ear", "/Users/nices96/Desktop/ziptest/physicial");
			ZipUtil.compress("/Users/nices96/Desktop/ziptest/physicial", "/Users/nices96/Desktop/ziptest/physicial2.ear", ArchiveType.EAR);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ManifestException e) {
			e.printStackTrace();
		}
    }
    
}// end of ZipUtil.java