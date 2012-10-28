<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<style>

</style>
<script>  
	$(document).ready(function (){
		$("#tabs_div").tabs();
		
	});
</script>
<div id="tabs_div">
	<div id="tabTitle" style="text-align:center;"></div>
	<ul>
		<li><a href="#tab_1"><span>Tab 1</span></a></li>
		<li><a href="#tab_2"><span>Tab 2</span></a></li>
		<li><a href="#tab_3"><span>Tab 3</span></a></li>
	</ul>
	<div class="ui-layout-content ui-widget-content ui-corner-bottom" style="border-top: 0; padding-bottom: 1em;">
		<div id="tab_1">
		  	tab1
		</div>
		<div id="tab_2">
		  	tab2
		</div>
		<div id="tab_3">
		  	tab3
		</div>
	</div>
</div>