package com.athena.peacock.engine.ant;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.ssh.Scp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScpService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Repository 위치의 파일을 서버로 업로드한다.
	 * 
	 */
	
	public void upload(String host, int port, String username, String password, String localFile, String todir, String keyfile, boolean trust) {
		String destination = username + "@" + host + ":" + todir;
		
		logger.debug("[scp upload] " + localFile + " - " + destination);
		Project project = new Project();
		
		Scp scp = new Scp();
		// Ant Project Property
		scp.setProject(project);
		scp.setVerbose(true);

		// Set Scp properties 
		scp.setPort(port);
		scp.setPassword(password);
		scp.setFile(localFile);
		scp.setTodir(destination);
		scp.setTrust(trust);
		
		if( keyfile != null) scp.setKeyfile(keyfile);
		
		scp.execute();
	}
}
