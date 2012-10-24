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
		.ui-layout-west ul ul {
			/* Drop-Down */
			bottom:		auto; 
			margin:		0;
 			margin-top:	2em;
		}
		ul ul li		{ padding: 1px 1em 1px 1px; }
		ul ul li:hover	{ background-color: #FF9; }
		ul li:hover ul	{ display:	block; background-color: #EEE; }
	
	</style>
	<script>
		
		function toggleLiveResizing () {
			$.each( $.layout.config.borderPanes, function (i, pane) {
				var o = myLayout.options[ pane ];
				o.livePaneResizing = !o.livePaneResizing;
			});
		};
		
		function toggleStateManagement ( skipAlert, mode ) {
			if (!$.layout.plugins.stateManagement) return;
		
			var options	= myLayout.options.stateManagement
			,	enabled	= options.enabled // current setting
			;
			if ($.type( mode ) === "boolean") {
				if (enabled === mode) return; // already correct
				enabled	= options.enabled = mode
			}
			else
				enabled	= options.enabled = !enabled; // toggle option
		
			if (!enabled) { // if disabling state management...
				myLayout.deleteCookie(); // ...clear cookie so will NOT be found on next refresh
				if (!skipAlert)
					alert( 'This layout will reload as the options specify \nwhen the page is refreshed.' );
			}
			else if (!skipAlert)
				alert( 'This layout will save & restore its last state \nwhen the page is refreshed.' );
		
			// update text on button
			var $Btn = $('#btnToggleState'), text = $Btn.html();
			if (enabled)
				$Btn.html( text.replace(/Enable/i, "Disable") );
			else
				$Btn.html( text.replace(/Disable/i, "Enable") );
		};
		
		// set EVERY 'state' here so will undo ALL layout changes
		// used by the 'Reset State' button: myLayout.loadState( stateResetSettings )
		var stateResetSettings = {
			west__size:			200
		,	west__initClosed:	false
		,	west__initHidden:	false
		};
		
		var myLayout; 
		
		$(document).ready(function () {
			
			$('#menuS').selectBox();

			resizePanel();
			// this layout could be created with NO OPTIONS - but showing some here just as a sample...
			// myLayout = $('body').layout(); -- syntax with No Options
		
			myLayout = $('#test').layout({
		
			//	reference only - these options are NOT required because 'true' is the default
				closable:					true	// pane can open & close
			,	resizable:					true	// when open, pane can be resized 
			,	slidable:					true	// when closed, pane can 'slide' open over other panes - closes on mouse-out
			,	livePaneResizing:			true
		
			//	some pane-size settings
			,	west__minSize:				100
			,	center__minWidth:			100
		
			//	some pane animation settings
			,	west__animatePaneSizing:	false
			,	west__fxSpeed_size:			"fast"	// 'fast' animation when resizing west-pane
			,	west__fxSpeed_open:			1000	// 1-second animation when opening west-pane
			,	west__fxSettings_open:		{ easing: "easeOutBounce" } // 'bounce' effect when opening
			,	west__fxName_close:			"none"	// NO animation when closing west-pane
		
			//	enable showOverflow on west-pane so CSS popups will overlap north pane
			,	west__showOverflowOnHover:	true
		
			//	enable state management
			,	stateManagement__enabled:	true // automatic cookie load & save enabled by default
		
			,	showDebugMessages:			true // log and/or display messages from debugging & testing code
			});
		
			// if there is no state-cookie, then DISABLE state management initially
			var cookieExists = !$.isEmptyObject( myLayout.readCookie() );
			if (!cookieExists) toggleStateManagement( true, false );
		
			});
		
			$(window).resize(function() {resizePanel();});
			
			function resizePanel(){
				var maskHeight = $(window).height();          
				var maskWidth = $(window).width();
				
		 		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다. 
		 		$('#contentsPanel').css({'width':maskWidth-65,'height':maskHeight-130});
			}
		</script>
			
	<body> 
		<div class="toplogo">
			<h1>
				<a href="<c:url value='/'/>"><img src="<c:url value='/sample/images/h1_toplogo.png'/>" alt="osc" /></a>
			</h1> 
		</div>
		<div id="contentsPanel" style="padding: 0px; 20px; 20px; 0px;"> 
			<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
			<div class="ui-layout-west">
				<tiles:insertAttribute name="left" />
			</div>
			
			<div class="ui-layout-center">
				<tiles:insertAttribute name="contents" />
			</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</body>
</html>