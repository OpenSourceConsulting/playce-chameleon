<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR" />
<title>Athena Chameleon - WAS Migration Tool</title>

<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />

</head>

<body>
<table border="0">
	<tr width="1024" >
		<td align="left" width="80"><img src="<c:url value='/images/chameleon-icon.png'/>" width="80" height="80"></td>
		<td align="left" width="400"><div class="title">Chameleon</div></td>
		<td align="right" width="524" valign="bottom">Login ID: admin@osci.kr</td>
	</tr>
</table>
    <div id="navigation">
			<ul>
            	<li id="active"><a href="<c:url value='/main.do'/>">Home</a></li>
                <li><a href="<c:url value='/upload/form.do'/>">Migration</a></li>
                <li><a href="navigation.html">Provisioning</a></li>
				<li><a href="navigation.html">System</a></li>
				<li><a href="navigation.html">Contact Us</a></li>
			</ul>
	</div>    
<br />

<table border="0">
	<tr>
		<td width="50">&nbsp;</td>
		<td width="150"><img src="<c:url value='/images/extract-icon.png'/>"></td>
		<td width="150"><div class="title">Anaysis</div></td>
		<td width="500">본 마이그레이션 도구는 WebLogic/Jeus 기반의 상용 애플리케이션 서버 상의 애플리케이션 및 프로젝트를 분석하여 오픈소스 기반의 웹 애플리케이션 서버로 전환을 돕는 도구입니다. 추출할 수 있는 애플리케이션으로는 EAR, WAR, JAR와 프로젝트 소스에 대한 분석을 진행합니다. </td>
	</tr>
	<tr>
		<td width="50">&nbsp;</td>
		<td width="150"><img src="<c:url value='/images/pdf-icon.png'/>"></td>
		<td width="150"><div class="title">Report</div></td>
		<td width="500">EAR, WAR, JAR 및 프로젝트 소스 입력의 결과는 모두 분석되어 PDF 결과 보고서로 만들어집니다. 결과 보고서를 활용하여 향후 오픈소스 웹 애플리케이션 서버로 마이그레이션을 수행할 때 상용 애플리케이션 서버에 의존성이 있는 코드들을 모두 명시해 줌으로써 효과적인 마이그레이션이 되도록 도와줍니다. 예외사항으로는 EJB 중 엔티티빈에 대한 마이그레이션은 지원하지 않습니다.</td>
	</tr>
	<tr>
		<td width="50">&nbsp;</td>
		<td width="150"><img src="<c:url value='/images/linux-icon.png'/>"></td>
		<td width="150"><div class="title">Provision</div></td>
		<td width="500">EAR, WAR, JAR 및 프로젝트 소스 입력의 결과는 모두 분석되어 PDF 결과 보고서로 만들어집니다. 결과 보고서를 활용하여 향후 오픈소스 웹 애플리케이션 서버로 마이그레이션을 수행할 때 상용 애플리케이션 서버에 의존성이 있는 코드들을 모두 명시해 줌으로써 효과적인 마이그레이션이 되도록 도와줍니다. 예외사항으로는 EJB 중 엔티티빈에 대한 마이그레이션은 지원하지 않습니다.</td>
	</tr>
</table>
<br/>
<br/>
<table border="0" width="1024">
	<tr>
		<td align="center">COPYRIGHT 2012 OPEN SOURCE CONSULTING, INC. ALL RIGHT RESERVED. SUPPORTED BY NIPA</td>
	</tr>
</table>
</body>
</html>
