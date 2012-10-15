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
<input type="hidden" id="serverIp" 		name="jbossInstance.serverIp" 		value="${provisioning.jbossInstance.serverIp}">
<input type="hidden" id="serverPort" 	name="jbossInstance.serverPort" 	value="${provisioning.jbossInstance.serverPort}">
<input type="hidden" id="engineHome" 	name="jbossInstance.engineHome" 	value="${provisioning.jbossInstance.engineHome}">
<input type="hidden" id="instanceHome" 	name="jbossInstance.instanceHome" 	value="${provisioning.jbossInstance.instanceHome}">
<input type="hidden" id="instanceName" 	name="jbossInstance.instanceName" 	value="${provisioning.jbossInstance.instanceName}">
<input type="hidden" id="targetWas" 	name="targetWas" 					value="${provisioning.targetWas}">

	<table border="0">
		<tr>
			<td colspan="2"><div class="title">JBoss 데이터소스 정보입력</div></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr height="30">
			<td width="150"><b>Database 유형 : </b></td>
			<td width="500">
				MySQL<input type="radio" name="dataSource.databaseType" id="databaseType" title="MySQL" checked="checked" value="mysql" border="0"/>
				Oracle<input type="radio" name="dataSource.databaseType" id="databaseType" title="Oracle" value="oracle" border="0"/>
				Cubrid<input type="radio" name="dataSource.databaseType" id="databaseType" title="Cubrid" value="cubrid" border="0"/>
			</td>
		</tr>
		<tr height="30">
			<td width="150"><b>JNDI 이름 : </b></td>
			<td width="500"><input type="text" id="jndiName" name="dataSource.jndiName" title="JNDI 이름 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>Connection URL : </b></td>
			<td width="500"><input type="text" id="connectionUrl" name="dataSource.connectionUrl" title="Connection URL 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>User Name : </b></td>
			<td width="500"><input type="text" id="userName" name="dataSource.userName" title="User Name 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>Password : </b></td>
			<td width="500"><input type="text" id="password" name="dataSource.password" title="Password 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>Min Pool Size : </b></td>
			<td width="500"><input type="text" id="minPoolSize" name="dataSource.minPoolSize" title="Min Pool Size 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>Max Pool Size : </b></td>
			<td width="500"><input type="text" id="maxPoolSize" name="dataSource.maxPoolSize" title="Max Pool Size 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td colspan="2"><a href="javascript:void(0);" id="installBtn"><input type="submit" value="Install"></a></td>
		</tr>
	</table>
 </form:form>
