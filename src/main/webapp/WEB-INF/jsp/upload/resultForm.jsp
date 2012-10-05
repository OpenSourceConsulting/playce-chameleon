<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
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

<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

<body>
<table border="0">
	<tr width="1024" >
		<td align="left" width="80"><img src="<c:url value='/images/chameleon-icon.png'/>" width="80" height="80"></td>
		<td align="left" width="400"><div class="title">Chameleon</div></td>
		<td align="right" width="524" valign="bottom">Login ID: admin@osci.kr  Last Log In: 2011-12-08 13:00:00 GMT +00:00</td>
	</tr>
</table>
    <div id="navigation">
			<ul>
            	<li><a href="<c:url value='/main.do'/>">Home</a></li>
                <li id="active"><a href="<c:url value='/upload/form.do'/>">Migration</a></li>
                <li><a href="navigation.html">Provisioning</a></li>
				<li><a href="navigation.html">System</a></li>
				<li><a href="navigation.html">Contact Us</a></li>
			</ul>
	</div>    

<br/>

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
	
	<br/>
	<table border="0" width="1024">
		<tr>
			<td align="center">COPYRIGHT 2012 OPEN SOURCE CONSULTING, INC. ALL RIGHT RESERVED. SUPPORTED BY NIPA</td>
		</tr>
	</table>
	
	
	<div class="demo-description" style="display: none; ">
	<p>Examples of the markup that can be used for buttons: A button element, an input of type submit and an anchor.</p>
	</div><!-- End demo-description -->
</body>
</html>
