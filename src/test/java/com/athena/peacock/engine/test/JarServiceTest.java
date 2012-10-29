package com.athena.peacock.engine.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.Manifest.Attribute;
import org.apache.tools.ant.taskdefs.ManifestException;
import org.apache.tools.ant.types.FileSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JarServiceTest {
	@Before
	public void setUp() throws Exception {
		
	}

	public void compressDirectory(String jarName, String directoryName, Map<String, String> attributes) throws ManifestException {
		// Ant Project Property
		Project project = new Project();

		File resultJarFile = new File(jarName);
		File filesetDir = new File(directoryName);
		
		Jar jar = new Jar();
		jar.setProject(project);
		jar.setDestFile(resultJarFile);
		
		// Setting filset dir
		FileSet fileSet = new FileSet();
		fileSet.setDir(filesetDir);
		jar.addFileset(fileSet);
		
		// Create manifest
		Manifest manifest = new Manifest();

        for( Map.Entry<String, String> entry : attributes.entrySet() ){
        	Attribute attribute = new Attribute();
        	attribute.setName(entry.getKey());
        	attribute.setValue(entry.getValue());
        	manifest.addConfiguredAttribute(attribute);
        }
        jar.addConfiguredManifest(manifest);
		
		jar.execute();
	}
	
	
	public void decompressAsi(String asiName, String targetDir) {
		Project project = new Project();
		
		
		File src = new File(asiName);
		File dest = new File(targetDir);
		
		Expand zip = new Expand();
		zip.setProject(project);
		
		// Set fileset information
		zip.setSrc(src);
		zip.setDest(dest);
		
		zip.execute();
	}
	

	@Test
	public void testCompressDirectory() throws ManifestException {
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("ASI Name", "Athena");
		attributes.put("Owner", "Open Source Consulting Inc.");
		attributes.put("Default Install Location", "/opt/java1.6");
		attributes.put("Version", "0.0.1");
		
		compressDirectory("D:/hello.jar", "C:/Temp/jdk-1.6.0_29-linux-x86_64", attributes);
		File file = new File("D:/hello.jar");
		Assert.assertTrue(file.exists());
		
		decompressAsi("D:/hello.jar", "C:/Temp/myasi");
		
	}
	
}
