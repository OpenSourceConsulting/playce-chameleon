package com.athena.peacock.engine.ant;

import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.ssh.SSHExec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SshExecService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void executeCommand(List<String> commands) {
		
	}
	
	public void executeCommand(String host, int port, String username, String password, String keyfile, boolean trust, String command) {
		SSHExec exec = new SSHExec();
		
		logger.debug("[ssh exec] " + command);
		Project project = new Project();
		
		exec.setProject(project);
		
		exec.setHost(host);
		exec.setPort(port);
		exec.setUsername(username);
		exec.setPassword(password);
		if( keyfile != null ) exec.setKeyfile(keyfile);
		exec.setTrust(trust);
		exec.setCommand(command);
		
		exec.execute();
	}
}
