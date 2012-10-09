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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * zip, ear, war, jar 파일에 대한 압축해제 및 재압축 기능을 지원하는 유틸리트 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ZipUtil_old {
	
	private static final Logger logger = LoggerFactory.getLogger(ZipUtil_old.class);
	
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * <pre>
	 * 지정된 디렉토리를 "디렉토리명.zip" 으로 압축한다.
	 * </pre>
	 * @param targetDir
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	public static boolean compress(String targetDir) throws IOException {
		return compress(targetDir, null, DEFAULT_ENCODING);
	}//end of compress()

	/**
	 * <pre>
	 * 지정된 디렉토리를 지정된 이름으로 압축한다.
	 * </pre>
	 * @param targetDir
	 * @param destArchiveStr
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	public static boolean compress(String targetDir, String destArchiveStr) throws IOException {
		return compress(targetDir, destArchiveStr, DEFAULT_ENCODING);
	}//end of compress()

	/**
	 * <pre>
	 * 지정된 디렉토리를 지정된 이름 및 지정된 인코딩으로 압축한다.
	 * </pre>
	 * @param targetDir
	 * @param destArchiveStr
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	public static boolean compress(String targetDir, String destArchiveStr, String encoding) throws IOException {
		return processCompress(targetDir, destArchiveStr, encoding, "zip");
	}//end of compress()
	
	/**
	 * <pre>
	 * 지정된 압축파일을 같은 디렉토리에 압축파일 명으로 압축 해제한다. 
	 * </pre>
	 * @param targetZipFileStr
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	public static boolean decompress(String targetZipFileStr) throws IOException {
        return decompress(targetZipFileStr, null, DEFAULT_ENCODING);
    }//end of decompress()

    /**
     * <pre>
     * 지정된 압축파일을 지정된 경로에 압축 해제한다.
     * </pre>
     * @param targetZipFileStr
     * @param destDirStr
     * @return
     * @throws IOException
     */
	@Deprecated
    public static boolean decompress(String targetZipFileStr, String destDirStr) throws IOException {
        return decompress(targetZipFileStr, destDirStr, DEFAULT_ENCODING);
    	//return FileUtil.extract2(targetZipFileStr, destDirStr);
    }//end of decompress()

    /**
     * <pre>
     * 지정된 압축파일을 지정된 경로 및 지정된 인코딩으로 압축 해제한다.
     * </pre>
     * @param targetZipFileStr
     * @param destDirStr
     * @param encoding
     * @return
     * @throws IOException
     */
	@Deprecated
    public static boolean decompress(String targetZipFileStr, String destDirStr, String encoding) throws IOException {
    	return processDecompress(targetZipFileStr, destDirStr, encoding);
    }//end of decompress()

	/**
	 * <pre>
	 * 지정된 디렉토리를 지정된 이름으로 압축한다.
	 * </pre>
	 * @param targetDir
	 * @param destArchiveStr
	 * @param encoding
	 * @param archiveSuffix
	 * @return
	 * @throws IOException
	 */
	private static boolean processCompress(String targetDir, String destArchiveStr, String encoding, String archiveSuffix) throws IOException {
		File targetDirFile = new File(targetDir);
        if (!targetDirFile.exists() || !targetDirFile.canRead()) {
            logger.error("could not compress. {} does not exists or can not read.", targetDirFile.getAbsolutePath());
            return false;
        }
        
        File destArchiveFile = makeDestArchiveFile(targetDirFile, destArchiveStr, archiveSuffix);
        Iterator<File> iter = null;
        if (targetDirFile.isDirectory()) {
            iter = getAllFileAndDir(targetDirFile, new ArrayList<File>()).iterator();
        } else {
            iter = Arrays.asList(new File[]{targetDirFile}).iterator();
        }
        
        FileOutputStream fos = new FileOutputStream(destArchiveFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ZipArchiveOutputStream zos = new ZipArchiveOutputStream(bos);
		zos.setEncoding(encoding);
		
		FileInputStream entryFis = null;
		BufferedInputStream entryBis = null;
		ZipArchiveEntry entry = null;
        while(iter.hasNext())  {
            File entryFile = (File)iter.next();
            
            if (entryFile.isFile()) {
                entryFis = new FileInputStream(entryFile);
                entryBis = new BufferedInputStream(entryFis);
                
        		entry = new ZipArchiveEntry(toRelativePath(targetDirFile, entryFile));
        		entry.setSize(entryFile.length());
                
                zos.putArchiveEntry(entry);
                IOUtils.copy(entryBis, zos);
                zos.closeArchiveEntry();
                
                IOUtils.closeQuietly(entryBis);
                IOUtils.closeQuietly(entryFis);
            } else {
        		entry = new ZipArchiveEntry(toRelativePath(targetDirFile, entryFile));
        		entry.setSize(entryFile.length());
        		
                zos.putArchiveEntry(entry);
                zos.closeArchiveEntry();
            }
        }
        
        if (zos != null) {
            zos.flush();
        }
        
        IOUtils.closeQuietly(zos);
        IOUtils.closeQuietly(bos);
        IOUtils.closeQuietly(fos);

        return true;
    }//end of processCompress()
	
	/**
	 * <pre>
	 * 압축파일을 지정된 디렉토리에 압축 해제한다.
	 * </pre>
	 * @param targetZipFileStr
	 * @param destDirStr
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	private static boolean processDecompress(String targetZipFileStr, String destDirStr, String encoding) throws IOException {
        File file = new File(targetZipFileStr);
        if (!file.exists() || !file.canRead()) {
            logger.error("could not decompress. {} does not exists or can not read.", file.getAbsolutePath());
            return false;
        }
        
        File destDir = destDirStr != null ? new File(destDirStr) : file.getParentFile();
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        
        // war, jar 파일 압축 해제시 /META-INF 디렉토리 생성 전 /META-INF/MANIFEST.MF가 먼처 처리가 되어 FileNotFoundException이 발생한다.
        // 따라서 해당하는 확장자를 가진 파일이 입력될 경우 META-INF 디렉토리를 먼저 생성한다.
        if (targetZipFileStr.endsWith("ear") || targetZipFileStr.endsWith("war") || targetZipFileStr.endsWith("jar")) {
        	FileUtils.forceMkdir(new File(destDir, "META-INF"));
        }
        
        ZipFile zipFile = new ZipFile(file, encoding, true);
        Enumeration<ZipArchiveEntry> entries = zipFile.getEntriesInPhysicalOrder();
        ZipArchiveEntry entry = null;
        InputStream content;
        
        while (entries.hasMoreElements()) {
            entry = (ZipArchiveEntry)entries.nextElement();
            content = zipFile.getInputStream(entry);
            
            processEachContent(destDir, content, entry.isDirectory(), entry.getName());
            
            IOUtils.closeQuietly(content);
        }

        ZipFile.closeQuietly(zipFile);
        
        return true;
    }//end of processDecompress()
	
	/**
	 * <pre>
	 * 압축 파일 내에 포함된 디렉토리 및 파일을 Local에 저장한다.
	 * </pre>
	 * @param destDir
	 * @param ais
	 * @param isDirectory
	 * @param entryName
	 * @throws IOException
	 */
	private static void processEachContent(File destDir, InputStream ais, boolean isDirectory, String entryName) throws IOException {
		File itemFile;
		itemFile = new File(destDir, entryName);

		if (isDirectory) {
			itemFile.mkdirs();
		} else {
			FileOutputStream fos = new FileOutputStream(itemFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			IOUtils.copy(ais, bos);

			if (bos != null) {
				bos.flush();
			}
			
			IOUtils.closeQuietly(ais);
			IOUtils.closeQuietly(bos);
			IOUtils.closeQuietly(fos);
		}
	}//end of processEachContent()
	
	/**
	 * <pre>
	 * 비어있는 압축 파일을 생성한다.
	 * </pre>
	 * @param targetDirFile
	 * @param destArchiveStr
	 * @param archiveSuffix
	 * @return
	 * @throws IOException
	 */
	private static File makeDestArchiveFile(File targetDirFile, String destArchiveStr, String archiveSuffix) throws IOException {
		File destArchiveFile = destArchiveStr != null ? 
					new File(destArchiveStr) : 
						new File(targetDirFile.getParent(), (new StringBuilder())
								.append(targetDirFile.getName()).append(".")
								.append(archiveSuffix).toString());
		
		if (!destArchiveFile.exists()) {
			File parentDir = (new File(destArchiveFile.getAbsolutePath())).getParentFile();
			
			if (!parentDir.exists()) {
				FileUtils.forceMkdir(parentDir);
			}
		}
		
		return destArchiveFile;
	}//end of makeDestArchiveFile()

	/**
	 * <pre>
	 * 지정된 경로에 포함된 모든 파일 및 디렉토리 목록을 조회한다.
	 * </pre>
	 * @param targetDir
	 * @param files
	 * @return
	 */
	private static List<File> getAllFileAndDir(File targetDir, List<File> files) {
        File arr[] = targetDir.listFiles();
        int len = arr.length;
        for(int i = 0; i < len; i++) {
            File dirOrFile = arr[i];
            
            if (dirOrFile.isDirectory() && dirOrFile.canRead()) {
                files.add(dirOrFile);
                getAllFileAndDir(dirOrFile, files);
            } else {
                files.add(dirOrFile);
            }
        }

        return files;
    }//end of getNestedFileAndDirListAll()
	
	/**
	 * <pre>
	 * 압축 파일 생성을 위한 상대 경로를 구한다.
	 * </pre>
	 * @param file
	 * @param entryFile
	 * @return
	 */
	private static String toRelativePath(File file, File entryFile) {
		String relativePath = entryFile.getAbsolutePath().substring(file.getAbsolutePath().length());
		
		if (relativePath.length() == 0) {
			relativePath = entryFile.getName();
		}
		if (entryFile.isDirectory() && !relativePath.endsWith("/")) {
			relativePath = (new StringBuilder()).append(relativePath).append("/").toString();
		}
		
		return relativePath.replace(File.separatorChar, '/').replaceFirst("^/", "");
	}//end of toRelativePath()
}// end of ZipUtil.java