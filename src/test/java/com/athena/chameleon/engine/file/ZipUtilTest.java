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
 * Hyo-jeong Lee	2012. 10. 11.		First Draft.
 */
package com.athena.chameleon.engine.file;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;
import org.springframework.util.Assert;

import com.athena.chameleon.common.utils.PropertyUtil;
import com.athena.chameleon.common.utils.ZipUtil;

/**
 * Source File 압축 해제 관련 Junit Test 클래스
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

public class ZipUtilTest {

	@Test
	public void zipDecompress() {
		
		try {
			String zipFilePath = this.getClass().getResource("/files/zipTest.zip").getFile();
			String decompressPath = PropertyUtil.getProperty("chameleon.migration.policy.unzip.dir") + File.separator + System.currentTimeMillis();
	        
			Assert.isTrue(new File(zipFilePath).exists(), zipFilePath + " does not exist.");
			
			ZipUtil.decompress(zipFilePath, decompressPath);
			
			System.out.println("zip decompress Dir Path : " + decompressPath);
			
		} catch (Exception e) {
			fail("zip file decompress error");
		}
	}

	@Test
	public void jarDecompress() {
		
		try {
			String jarFilePath = this.getClass().getResource("/files/jarTest.jar").getFile();
			String decompressPath = PropertyUtil.getProperty("chameleon.migration.policy.unzip.dir") + File.separator + System.currentTimeMillis();
	        
			Assert.isTrue(new File(jarFilePath).exists(), jarFilePath + " does not exist.");
			
			ZipUtil.decompress(jarFilePath, decompressPath);

			System.out.println("jar decompress Dir Path : " + decompressPath);
			
		} catch (Exception e) {
			fail("jar file decompress error");
		}
	}

	@Test
	public void earDecompress() {
		
		try {
			String earFilePath = this.getClass().getResource("/files/earTest.war").getFile();
			String decompressPath = PropertyUtil.getProperty("chameleon.migration.policy.unzip.dir") + File.separator + System.currentTimeMillis();
			
			Assert.isTrue(new File(earFilePath).exists(), earFilePath + " does not exist.");
			
			ZipUtil.decompress(earFilePath, decompressPath);

			System.out.println("ear decompress Dir Path : " + decompressPath);
			
		} catch (Exception e) {
			fail("ear file decompress error");
		}
	}

	@Test
	public void warDecompress() {
		
		try {
			String warFilePath = this.getClass().getResource("/files/warTest.war").getFile();
			String decompressPath = PropertyUtil.getProperty("chameleon.migration.policy.unzip.dir") + File.separator + System.currentTimeMillis();
	        
			Assert.isTrue(new File(warFilePath).exists(), warFilePath + " does not exist.");
			
			ZipUtil.decompress(warFilePath, decompressPath);

			System.out.println("war decompress Dir Path : " + decompressPath);
			
		} catch (Exception e) {
			fail("war file decompress error");
		}
	}

}
