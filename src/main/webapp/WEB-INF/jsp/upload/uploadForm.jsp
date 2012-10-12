<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
	onMenu(1);

	$(document).ready(function (){
		
		$('#applyBtn').click(function() { 
			if(!$('#projectNm').val()) {
				alert('Project 명을 입력하세요.');
				$('#projectNm').focus();
				return false;
			} else if(!$('#person').val()) {
				alert('담당자를 입력하세요.');
				$('#person').focus();
				return false;
			} else if((!$('#projectSrc').val()) && (!$('#deploySrc').val())) {
				alert('Project Source 나 Deploy Source 중 한 항목 이상 업로드하세요.');
				$('#projectSrc').focus();
				return false;
			} else {
				var form = $("#uploadForm").get(0);
				form.action = "<c:url value='/upload/upload.do' />";
				form.submit();
			}
		});
	});
</script>
<form:form modelAttribute="upload" method="post" id="uploadForm" name="uploadForm" enctype="multipart/form-data">
	<table border="0">
		<tr>
			<td colspan="2"><div class="title">Input Project</div></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr height="30">
			<td width="150"><b>프로젝트명: </b></td>
			<td width="500"><input type="text" size="40" id="projectNm" name="projectNm" title="Project명 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>담당자: </b></td>
			<td width="500"><input type="text" id="person" name="person" title="담당자 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>프로젝트 소스: </b></td>
			<td width="500"><input type="file" name="projectSrc" id="projectSrc" title="찾아보기" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>디플로이 애플리케이션 </b></td>
			<td width="500"><input type="file" name="deploySrc" id="deploySrc" title="찾아보기" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>기존 WAS </b></td>
			<td width="500">
				WebLogic<input type="radio" name="beforeWas" id="beforeWas" title="WEBLOGIC" checked="checked" value="W"/>
				Jeus<input type="radio" name="beforeWas" id="beforeWas" title="JEUS" value="J"/>
			</td>
		</tr>
		<tr height="30">
			<td width="150"><b>대상 WAS </b></td>
			<td width="500">
				JBoss<input type="radio" name="afterWas" id="afterWas" title="JBoss" checked="checked" value="B" border="0"/>
				Tomcat<input type="radio" name="afterWas" id="afterWas" title="Tomcat" value="T"/>
			</td>
		</tr>
		<tr height="30">
			<td colspan="2"><a href="javascript:void(0);" id="applyBtn"><input type="submit" id="applyBtn" value="Start Analyze"></a></td>
		</tr>
	</table>
 </form:form>
