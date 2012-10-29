package com.athena.peacock.engine.test;


import org.junit.Before;
import org.junit.Test;

import com.athena.peacock.engine.ant.SshExecService;

public class SshExecServiceTest {
	@Before
	public void setUp() throws Exception {
		
	}

	
	@Test
	public void testSshExec() {
		SshExecService service = new SshExecService();
		service.executeCommand("192.168.56.101", 22, "jboss", "jboss123", null, true, "touch athena.txt");
	}
	
}
