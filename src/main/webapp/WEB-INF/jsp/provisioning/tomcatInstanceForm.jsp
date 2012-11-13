<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
	onMenu(2);

	$(document).ready(function (){
		
		$('#installBtn').click(function() { 
			if(!$('#serverIp').val()) {
				alert('Server IP가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#serverIp').focus();
				return false;
			} else if(!$('#serverPort').val()) {
				alert('Server Port가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#serverPort').focus();
				return false;
			} else if(!$('#tomcatEngine').val()) {
				alert('TOMCAT ENGINE이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#tomcatEngine').focus();
				return false;
			} else if(!$('#javaHome').val()) {
				alert('JAVA_HOME이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#javaHome').focus();
				return false;
			} else if(!$('#catalinaHome').val()) {
				alert('CATALINA_HOME이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#catalinaHome').focus();
				return false;
			} else if(!$('#serverName').val()) {
				alert('SERVER_NAME이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#serverName').focus();
				return false;
			} else if(!$('#httpPort').val()) {
				alert('HTTP_PORT가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#httpPort').focus();
				return false;
			} else if(!$('#ajppPort').val()) {
				alert('AJPP_PORT가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#ajppPort').focus();
				return false;
			} else if(!$('#sslPort').val()) {
				alert('SSL_PORT가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#sslPort').focus();
				return false;
			} else if(!$('#shutdownPort').val()) {
				alert('SHUTDOWN_PORT가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#shutdownPort').focus();
				return false;
			} else if(!$('#sshLoginId').val()) {
				alert('Ssh-login-id가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#sshLoginId').focus();
				return false;
			} else if(!$('#sshLoginPassword').val()) {
				alert('Ssh password가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#sshLoginPassword').focus();
				return false;
			} else {
				var form = $("#instanceForm").get(0);
				form.action = "<c:url value='/provisioning/install.do' />";
				form.submit();
			}
		});
	});
</script>
<form:form modelAttribute="provisioning" method="post" id="instanceForm" name="instanceForm">
	<input type="hidden" id="targetWas" name="targetWas" value="${provisioning.targetWas}">
	
	<div class="tit_box">Tomcat 인스턴스 정보입력</div>
    <div class="bt_tab_box02">
    	<div class="bt_tab_txt01" style="color:#858686"><a href="<c:url value='/provisioning/wasInstanceForm.do?targetWas=B' />">JBoss</a></div>
    	<div class="bt_tab_txt01">TomCat</div>
    </div>
	<div class="bg_formbox" style="height:780px;"> <!-- form배경 박스 높이를 670로 늘임 -->
    	<div class="formbox_txt01"><p style="padding-top:11px; padding-left:15px;">Server IP</p></div>
        <div class="formbox_form01"><input type="text" id="serverIp" name="tomcatInstance.serverIp" title="Server IP 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Server Port</p></div>
        <div class="formbox_form02"><input type="text" id="serverPort" name="tomcatInstance.serverPort" title="Server Port 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">TOMCAT ENGINE</p></div>
        <div class="formbox_form02">
        	<div class="formbox_sub01"><input type="text" id="tomcatEngine" name="tomcatInstance.tomcatEngine" title="TOMCAT ENGINE 입력" class="input_form02" /></div> <!-- Inputbox 가로폭을 250으로 줄임 -->
<!--         	<div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">신규설치 :</p></div> -->
<!--             <div class="formbox_radioform" style="margin-left:10px"><input type="radio" id="newInstallY" name="tomcatInstance.newInstallYn" value="Y" class="regular-radio" checked /><label for="newInstallY"></label></div> -->
<!-- 	        <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">Yes</p></div> -->
<!-- 	        <div class="formbox_radioform"><input type="radio" id="newInstallN" name="tomcatInstance.newInstallYn" value="N" class="regular-radio" /><label for="newInstallN"></label></div> -->
<!-- 	        <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">No</p></div> -->
        </div>
           
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">JAVA_HOME</p></div>
        <div class="formbox_form02"><input type="text" id="javaHome" name="tomcatInstance.javaHome" title="JAVA_HOME 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">CATALINA_HOME</p></div>
        <div class="formbox_form02"><input type="text" id="catalinaHome" name="tomcatInstance.catalinaHome" title="CATALINA_HOME 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">SERVER_NAME</p></div>
        <div class="formbox_form02"><input type="text" id="serverName" name="tomcatInstance.serverName" title="SERVER_NAME 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">HTTP_PORT</p></div>
        <div class="formbox_form02"><input type="text" id="httpPort" name="tomcatInstance.httpPort" title="HTTP_PORT 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">AJPP_PORT</p></div>
        <div class="formbox_form02"><input type="text" id="ajppPort" name="tomcatInstance.ajppPort" title="AJPP_PORT 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">SSL_PORT</p></div>
        <div class="formbox_form02"><input type="text" id="sslPort" name="tomcatInstance.sslPort" title="SSL_PORT 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">SHUTDOWN_PORT</p></div>
        <div class="formbox_form02"><input type="text" id="shutdownPort" name="tomcatInstance.shutdownPort" title="SHUTDOWN_PORT 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Ssh-login-id</p></div>
        <div class="formbox_form02"><input type="text" id="sshLoginId" name="tomcatInstance.sshLoginId" title="Ssh-login-id 입력" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Ssh password</p></div>
        <div class="formbox_form02"><input type="text" id="sshLoginPassword" name="tomcatInstance.sshLoginPassword" title="Ssh password 입력" class="input_form02" /></div>
        
        <div class="common_btn"><a href="javascript:void(0);" id="installBtn"><img src="<c:url value='/images/common/bt_install01.png'/>" /></a></div>        
    </div>        
 </form:form>
