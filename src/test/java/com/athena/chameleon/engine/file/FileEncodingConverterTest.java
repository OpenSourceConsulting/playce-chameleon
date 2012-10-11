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

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.athena.chameleon.common.utils.PropertyUtil;
import com.athena.chameleon.common.utils.ZipUtil;
import com.athena.chameleon.engine.core.converter.FileEncodingConverter;
import com.athena.chameleon.engine.entity.pdf.AnalyzeDefinition;

/**
 * Source File 압축 해제 관련 Junit Test 클래스
 * 
 * @author Hyo-jeong Lee
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./src/main/resources/spring/context-*.xml" })
public class FileEncodingConverterTest {

    @Inject
    @Named("fileEncodingConverter")
    private FileEncodingConverter converter;

	@Test
	public void convert() {
		
		try {

			String zipFilePath = this.getClass().getResource("/files/zipTest.zip").getFile();
			String decompressPath = PropertyUtil.getProperty("chameleon.migration.policy.unzip.dir") + File.separator + System.currentTimeMillis();

			ZipUtil.decompress(zipFilePath, decompressPath);
			
			// 인코딩 변경
			converter.convert(new File(decompressPath), new AnalyzeDefinition());
						
		} catch (Exception e) {
			fail("file convert error");
		}
		
	}

}
