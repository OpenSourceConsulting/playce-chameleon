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
		<style>
			body {
				background-image: url(<c:url value='/images/common/bg_pattern.gif' />);
			}
			#mask {    position:absolute;    left:0;  top:0;  z-index:9000;    background-color:#ffffff;   filter:alpha(opacity=70); opacity:0.7; -moz-opacity:0.7 }
			#mask1{    position:absolute;    left:0;  top:0;  z-index:9000;    background-color:#ffffff;   filter:alpha(opacity=70); opacity:0.7; -moz-opacity:0.7 }
		</style>
		<script>
			$(document).ready(function (){
				 $("input[type=file]").filestyle({ 
				     image: "<c:url value='/images/common/bt_browse.png'/>",
				     imageheight : 41,
				     imagewidth : 107,
				     width : 250
				 });
			});
		</script>
	</head>
	<body> 
		<tiles:insertAttribute name="header" />
		<div class="body_box">
			<tiles:insertAttribute name="contents" />
		</div>
		<div id="hideDiv" style="display:none;">
			<div id="mask"></div>
			<div style="z-index:9100;" class="pop_box00">
				<div class="pop_box00-img"><img src="<c:url value="/images/common/ani_chameleon.gif"/>" width="67" height="69" /></div>
			    <div class="pop_box00-text">작업 진행 중입니다.</div>
			</div>
		</div> 
		<div id="hideDiv01" style="display:none;">
			<div id="mask1"></div>
			<div style="z-index:9100;height:170px;" class="pop_box00">
				<div class="pop_box00-img"><img src="<c:url value="/images/common/ani_chameleon.gif"/>" width="67" height="69" /></div>
				<div class="pop_box00-text">작업 진행율 : <span id="percentageLayer" style="color:#d54153!important;">0</span> %</div>
			    <div class="pop_box00-text1" id="statusLayer">마이그레이션을 시작합니다.</div>
			</div>
		</div>            
	</body>
</html>