<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="packageName" value="sample"/>
<c:set var="datePattern"><fmt:message key="date.format"></fmt:message></c:set>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <!--HTTP 1.1-->
	    <meta http-equiv="Cache-Control" content="no-store"/>
	    <!--HTTP 1.0-->
	    <meta http-equiv="Pragma" content="no-cache"/>
	    <!--Prevents caching at the Proxy Server-->
	    <meta http-equiv="Expires" content="0"/>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	    <title>Athena Chameleon - WAS Migration Tool</title>
		<tiles:insertAttribute name="css" />
		<tiles:insertAttribute name="js" />
	</head>
	<body> 
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="contents" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>