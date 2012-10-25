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
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/jquery/layout/layout-default-latest.css'/>" />
		<link type="text/css" rel="stylesheet" href="<c:url value='/css/jquery/selectbox/jquery.selectBox.css'/>" />
		
		<tiles:insertAttribute name="js" />
		<script type="text/javascript" src="<c:url value='/js/jquery/jquery-latest.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery/jquery-ui-latest.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery/jquery.layout-latest.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery/jquery.selectBox.js'/>"></script>
	</head>
	<style type="text/css">
	
		p {
			font-size:		1em;
			margin:			1ex 0;
		}
		p.buttons {
			text-align:		center;
			line-height:	2.5em;
		}
		button {
			line-height:	normal;
		}
		.hidden {
			display:		none;
		}
	
		/*
		 *	Rules for simulated drop-down/pop-up lists
		 */
		ul {
			/* rules common to BOTH inner and outer UL */
			z-index:	100000;
			margin:		1ex 0;
			padding:	0;
			list-style:	none;
			cursor:		pointer;
			border:		1px solid Black;
			/* rules for outer UL only */
			width:		15ex;
			position:	relative;
		}
		ul li {
			background-color: #ffffff;
			padding: 0.15em 1em 0.3em 5px;
		}
		ul ul {
			display:	none;
			position:	absolute;
			width:		100%;
			left:		-1px;
		}
		
		#container {
			background:	#999;
			min-height:	300px;
			min-width:	600px;
  			position:	absolute; 
 			top:		80px;	/* margins in pixels */ 
 			bottom:		50px;	/* could also use a percent */ 
			left:		20px;
			right:		20px;
		}
		
		.inner{border: 0px;}
	</style>
	<script>
		$(document).ready(function(){

			$('#menuS').selectBox();
			
			$("#container").layout({ 
					center__childOptions: {
 					center__paneSelector:	"#detailForm"	// tab-panels-wrapper
 						,	inset: {
	 							top:	0
	 							,	bottom:	0
	 							,	left:	0
	 							,	right:	0
	 							}

				}
			});


			resizeFooter();
			
		});


		$(window).resize(function() {resizeFooter();});
		
		function resizeFooter(){
			$('#footer').css({'top':'0'});
			
			var maskHeight = $(document).height();
			$('#footer').css({'top':maskHeight-50});
		}
	</script>
			
	<body> 
		<div class="toplogo">
			<h1>
				<a href="<c:url value='/'/>"><img src="<c:url value='/sample/images/h1_toplogo.png'/>" alt="osc" /></a>
			</h1> 
		</div>
		<div id="container" > 
			<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
			<div class="ui-layout-west">
				<tiles:insertAttribute name="left" />
			</div>
			
<!-- 			<iframe id="mainFrame" name="mainFrame" class="ui-layout-center" -->
<!-- 				frameborder="0" scrolling="auto" -->
<!-- 				src="http://plugins.jquery.com/project/Layout"></iframe> -->
					
			<div class="ui-layout-center">
				<div id="detailForm" class="inner">
					<tiles:insertAttribute name="contents" />
				</div>
			</div> 
		</div>
		<div id="footer" style="position: absolute;">
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>