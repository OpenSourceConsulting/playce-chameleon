<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
	onMenu(2);

	$(document).ready(function (){
		
		$('#installBtn').click(function() { 
			if(!$('#jndiName').val()) {
				alert('JNDI 이름이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#jndiName').focus();
				return false;
			} else if(!$('#connectionUrl').val()) {
				alert('Connection URL이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#connectionUrl').focus();
				return false;
			} else if(!$('#userName').val()) {
				alert('User Name이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#userName').focus();
				return false;
			} else if(!$('#password').val()) {
				alert('Password가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#password').focus();
				return false;
			} else if(!$('#minPoolSize').val()) {
				alert('Min Pool Size가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#minPoolSize').focus();
				return false;
			} else if(!$('#maxPoolSize').val()) {
				alert('Max Pool Size가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#maxPoolSize').focus();
				return false;
			} else {
				var form = $("#sourceForm").get(0);
				form.action = "<c:url value='/provisioning/install.do' />";
				form.submit();
			}
		});
	});
</script>
<form:form modelAttribute="provisioning" method="post" id="sourceForm" name="sourceForm">
<input type="hidden" id="targetWas" 	name="targetWas" 					value="${provisioning.targetWas}">
<c:if test="${provisioning.targetWas == 'B'}">
	<input type="hidden" id="serverIp" 		name="jbossInstance.serverIp" 		value="${provisioning.jbossInstance.serverIp}">
	<input type="hidden" id="serverPort" 	name="jbossInstance.serverPort" 	value="${provisioning.jbossInstance.serverPort}">
	<input type="hidden" id="engineHome" 	name="jbossInstance.engineHome" 	value="${provisioning.jbossInstance.engineHome}">
	<input type="hidden" id="newInstallYn" 	name="jbossInstance.newInstallYn" 	value="${provisioning.jbossInstance.newInstallYn}">
	<input type="hidden" id="instanceHome" 	name="jbossInstance.instanceHome" 	value="${provisioning.jbossInstance.instanceHome}">
	<input type="hidden" id="instanceName" 	name="jbossInstance.engineName" 	value="${provisioning.jbossInstance.engineName}">
	<input type="hidden" id="portGroup" 	name="jbossInstance.portGroup" 		value="${provisioning.jbossInstance.portGroup}">
</c:if>
<c:if test="${provisioning.targetWas == 'T'}">
	<input type="hidden" id="serverIp" 		name="tomcatInstance.serverIp" 		value="${provisioning.tomcatInstance.serverIp}">
	<input type="hidden" id="serverPort" 	name="tomcatInstance.serverPort" 	value="${provisioning.tomcatInstance.serverPort}">
	<input type="hidden" id="tomcatEngine" 	name="tomcatInstance.tomcatEngine" 	value="${provisioning.tomcatInstance.tomcatEngine}">
	<input type="hidden" id="newInstallYn" 	name="tomcatInstance.newInstallYn" 	value="${provisioning.tomcatInstance.newInstallYn}">
	<input type="hidden" id="javaHome" 		name="tomcatInstance.javaHome" 		value="${provisioning.tomcatInstance.javaHome}">
	<input type="hidden" id="catalinaHome" 	name="tomcatInstance.catalinaHome" 	value="${provisioning.tomcatInstance.catalinaHome}">
	<input type="hidden" id="serverName" 	name="tomcatInstance.serverName" 	value="${provisioning.tomcatInstance.serverName}">
	<input type="hidden" id="httpPort" 		name="tomcatInstance.httpPort" 		value="${provisioning.tomcatInstance.httpPort}">
	<input type="hidden" id="ajppPort" 		name="tomcatInstance.ajppPort" 		value="${provisioning.tomcatInstance.ajppPort}">
	<input type="hidden" id="sslPort" 		name="tomcatInstance.sslPort" 		value="${provisioning.tomcatInstance.sslPort}">
	<input type="hidden" id="shutdownPort" 	name="tomcatInstance.shutdownPort" 	value="${provisioning.tomcatInstance.shutdownPort}">
</c:if>

   <c:if test="${provisioning.targetWas == 'B'}">
		<div class="tit_box">JBoss 데이터소스 정보입력</div>
	    <div class="bt_tab_box01">
	    	<div class="bt_tab_txt01">JBoss</div>
	        <div class="bt_tab_txt01" style="color:#858686"><a href="<c:url value='/provisioning/wasInstanceForm.do?targetWas=T' />">TomCat</a></div>
	    </div>
   </c:if>
   <c:if test="${provisioning.targetWas == 'T'}">
	    <div class="tit_box">Tomcat 데이터소스 정보입력</div>
	    <div class="bt_tab_box02">
	    	<div class="bt_tab_txt01" style="color:#858686"><a href="<c:url value='/provisioning/wasInstanceForm.do?targetWas=B' />">JBoss</a></div>
	    	<div class="bt_tab_txt01">TomCat</div>
	    </div>
   </c:if>
	<div class="bg_formbox" style="height:517px;"> <!-- form배경 박스 높이를 517로 늘임 -->
   	    <div class="formbox_txt01"><p style="padding-top:11px; padding-left:15px;">Database 유형</p></div>
        <div class="formbox_form01">
       	<div class="formbox_radioform" style="margin-left:0"><input type="radio" name="dataSource.databaseType" id="databaseType1" value="mysql" title="MySQL" class="regular-radio" checked /><label for="databaseType1"></label></div>
           <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">MySQL</p></div>
           <div class="formbox_radioform"><input type="radio" name="dataSource.databaseType" id="databaseType2" title="Oracle" value="oracle" class="regular-radio" /><label for="databaseType2"></label></div>
           <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">Oracle</p></div>
           <div class="formbox_radioform"><input type="radio" name="dataSource.databaseType" id="databaseType3" title="Cubrid" value="cubrid" class="regular-radio" /><label for="databaseType3"></label></div>
           <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">Cubrid</p></div>      	
       </div>
       
       <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">JNDI 이름</p></div>
       <div class="formbox_form02"><input type="text" id="jndiName" name="dataSource.jndiName" title="JNDI 이름 입력" class="input_form02" /></div>
       		
       <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Connection URL</p></div>
       <div class="formbox_form02"><input type="text" id="connectionUrl" name="dataSource.connectionUrl" title="Connection URL 입력" class="input_form02" /></div>
       
       <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">User Name</p></div>
       <div class="formbox_form02"><input type="text" id="userName" name="dataSource.userName" title="User Name 입력" class="input_form02" /></div>
       
       <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Password</p></div>
       <div class="formbox_form02"><input type="text" id="password" name="dataSource.password" title="Password 입력" class="input_form02" /></div>
       
       <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Min Pool Size</p></div>
       <div class="formbox_form02"><input type="text" id="minPoolSize" name="dataSource.minPoolSize" title="Min Pool Size 입력" class="input_form02" /></div>
       
       <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Max Pool Size</p></div>
       <div class="formbox_form02"><input type="text" id="maxPoolSize" name="dataSource.maxPoolSize" title="Max Pool Size 입력" class="input_form02" /></div>
	
       <div class="common_btn"><a href="javascript:void(0);" id="installBtn"><img src="<c:url value='/images/common/bt_install01.png'/>" /></a></div>        
 </div>        
 </form:form>
