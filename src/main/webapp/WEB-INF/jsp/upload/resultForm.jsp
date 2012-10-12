<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
onMenu(1);

$(document).ready(function (){
	
	if('${result}' == 'false') {
		alert('마이그레이션 도중 에러가 발생하였습니다.');
		location.href = "<c:url value='/upload/form.do'/>"; 
	}
	
	$('.down').click(function() {
		if($(this).attr('val')) {
			downloadFile($(this).attr('val'));
		}
	});

});

function downloadFile(fileName){
	location.href="<c:url value='/file/download.do?path="+fileName+"'/>";
}

</script>

<table border="0">
	<tr>
		<td colspan="2"><div class="title">입력 정보</div></td>
	</tr>
	<tr height="30">
		<td width="150"><b>프로젝트명: </b></td>
		<td width="500">${upload.projectNm}</td>
	</tr>
	<tr height="30">
		<td width="150"><b>담당자: </b></td>
		<td width="500">${upload.person}</td>
	</tr>
	<tr height="30">
		<td width="150"><b>기존 WAS </b></td>
		<td width="500">${upload.beforeWas}</td>
	</tr>
	<tr height="30">
		<td width="150"><b>대상 WAS </b></td>
		<td width="500">${upload.afterWas}</td>
	</tr>
</table>
<br/>
<br/>
<table border="0">
	<tr>
		<td colspan="2"><div class="title">변환 결과</div></td>
	</tr>
	<c:if test="${!empty metaData.pdfFile}">
		<tr height="30">
			<td width="200"><b>변환 PDF Report: </b></td>
			<td width="500">${metaData.pdfFileName} <a href="javascript:void(0);" val="${metaData.pdfFile}" class="down"><input type="submit" val="${metaData.pdfFile}" class="down" value="다운로드" /></a></td>
		</tr>
	</c:if>
	<c:if test="${!empty metaData.migrateSourceFile}">
		<tr height="30">
			<td width="200"><b>변환 프로젝트 소스: </b></td>
			
			<td width="500">${metaData.migrateSourceFileName} <a href="javascript:void(0);" val="${metaData.migrateSourceFile}" class="down"><input type="submit" value="다운로드" /></a></td>
		</tr>
	</c:if>
	<c:if test="${!empty metaData.migrateDeployFile}">
		<tr height="30">
			<td width="200"><b>변환 디플로이 애플리케이션: </b></td>
			<td width="500">${metaData.migrateDeployFileName} <a href="javascript:void(0);" val="${metaData.migrateDeployFile}" class="down"><input type="submit" value="다운로드" /></a></td>
		</tr>
	</c:if>
</table>