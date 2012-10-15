<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
	onMenu(2);

	$(document).ready(function (){
		
		$('#nextBtn').click(function() { 
			if(!$('#serverIp').val()) {
				alert('Server IP가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#serverIp').focus();
				return false;
			} else if(!$('#serverPort').val()) {
				alert('Server Port가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#serverPort').focus();
				return false;
			} else if(!$('#engineHome').val()) {
				alert('JBoss Engine Home이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#engineHome').focus();
				return false;
			} else if(!$('#instanceHome').val()) {
				alert('JBoss Instance Home이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#instanceHome').focus();
				return false;
			} else if(!$('#instanceName').val()) {
				alert('JBoss Instance Name이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#instanceName').focus();
				return false;
			} else {
				var form = $("#instanceForm").get(0);
				form.action = "<c:url value='/provisioning/dataSourceForm.do' />";
				form.submit();
			}
		});
	});
</script>
<form:form modelAttribute="provisioning" method="post" id="instanceForm" name="instanceForm">
<input type="hidden" id="targetWas" name="targetWas" value="${provisioning.targetWas}">
	<table border="0">
		<tr>
			<td colspan="2"><div class="title">JBoss 인스턴스 정보 입력</div></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr height="30">
			<td width="150"><b>Server IP : </b></td>
			<td width="500"><input type="text" id="serverIp" name="jbossInstance.serverIp" title="Server IP 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>Server Port : </b></td>
			<td width="500"><input type="text" id="serverPort" name="jbossInstance.serverPort" title="Server Port 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>JBoss Engine Home : </b></td>
			<td width="500"><input type="text" id="engineHome" name="jbossInstance.engineHome" title="JBoss Engine Home 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>JBoss Instance Home : </b></td>
			<td width="500"><input type="text" id="instanceHome" name="jbossInstance.instanceHome" title="JBoss Instance Home 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>JBoss Instance Name : </b></td>
			<td width="500"><input type="text" id="instanceName" name="jbossInstance.instanceName" title="JBoss Instance Name 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td colspan="2"><a href="javascript:void(0);" id="nextBtn"><input type="submit" value="Next"></a></td>
		</tr>
	</table>
 </form:form>
