<%@ page isErrorPage="true"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
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
    <title>OSC Athena Chameleon</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/sample/css/common.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/sample/css/layout.css'/>" />
	<script type="text/javascript" src="<c:url value='/sample/common/javascript/CommonScript.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/sample/common/javascript/InputCalendar.js'/>"></script>
</head>
<body> 
<div class="wrap">  
    <div id="div_gnb">
        <div class="skipnavigation">
			<a href="#contents">Jump up to the contents</a>
		</div>
		<hr />
		
		<div id="header">
			<div class="toplogo">
				<h1>
					<a href="<c:url value='/'/>"><img src="<c:url value='/sample/images/h1_toplogo.png'/>" alt="osc" /></a>
				</h1>
			</div>
			<div class="location">
				<a href="<c:url value='/coreMovieFinder.do?method=list'/>">Sample</a>
			</div>
		</div>
		<hr />
    </div>
    <div class="container">
        <div class="cont_top">
        	<h2>Fail Message</h2>
        </div>
    	<div class="failmessagebox">
    		<p>
    			<c:if test="${not empty exception.message}">
		        	<c:choose>
						<c:when test="${exception.message == 'Occurred Error'}">
							<spring:message code='${exception.cause.message}'/> 
						</c:when>
						<c:otherwise>
							<spring:message code='${exception.message}'/> 
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${empty exception.message}">Occurred Error</c:if>
			</p>
        </div>
		<hr />
    </div>
    <div id="div_footer">
        <div id="footer">
		   	Copyright 2012 Open Source Consulting.
		</div>
    </div>
</div>
</body>
</html>