<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="packageName" value="com.isacts.jeeip"/>
<c:set var="datePattern"><fmt:message key="date.format"></fmt:message></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!--HTTP 1.1-->
    <meta http-equiv="Cache-Control" content="no-store"/>
    <!--HTTP 1.0-->
    <meta http-equiv="Pragma" content="no-cache"/>
    <!--Prevents caching at the Proxy Server-->
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>OSC Athena Chameleon</title>
	<tiles:insertAttribute name="css" />
	<tiles:insertAttribute name="js" />
</head>
<body>
	<div id="emptyContents">
		<tiles:insertAttribute name="contents" />
	</div>
</body>
</html>