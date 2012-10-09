package com.athena.peacock.engine.ant;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.Manifest.Attribute;
import org.apache.tools.ant.taskdefs.ManifestException;
import org.apache.tools.ant.types.FileSet;

import com.athena.peacock.engine.common.SystemConstants;
import com.athena.peacock.engine.common.SystemProperties;

@Service
public class JarService {
	
    private @Autowired SystemProperties prop;
    
	/**
	 * 입력된 디렉토리를 이용하여 압축 파일을 만든다.
	 * @param jarName
	 * @param directoryName
	 * @throws ManifestException 
	 */
	public void compressDirectory(String jarName, String directoryName, Map<String, String> attributes) throws ManifestException {
		Project project = new Project();
		
		String repositoryDir = prop.get(SystemConstants.REPOSITORY_URL);
		File resultJarFile = new File(repositoryDir, jarName);
		File filesetDir = new File(directoryName);
		
		Jar jar = new Jar();
		jar.setProject(project);
		
		// Set fileset information
		jar.setDestFile(resultJarFile);
		
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
	
	/**
	 * 입력된 파일의 압축을 푼다.
	 * @param asiName
	 * @param targetDir
	 */
	public void decompressAsi(String asiName, String targetDir) {
		Project project = new Project();
		
		String repositoryDir = prop.get(SystemConstants.REPOSITORY_URL);
		File src = new File(repositoryDir, asiName);
		File dest = new File(targetDir);
		
		Expand zip = new Expand();
		zip.setProject(project);
		
		// Set fileset information
		zip.setSrc(src);
		zip.setDest(dest);
		
		zip.execute();
	}
}
