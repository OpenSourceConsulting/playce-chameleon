<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<style>

</style>
<script>  
	$(document).ready(function (){
		
		$('#detailForm').layout({
			resizerClass: 'ui-state-default',
			center__onresize: function (pane, $Pane) {
	            jQuery("#center-grid").jqGrid('setGridWidth',$Pane.innerWidth()-2);
			},
			south__size:	150,
			inset: {
					top:	0
					,	bottom:	0
					,	left:	0
					,	right:	0
					}
		});
		 
		
		$("#center-grid").jqGrid({
	        url: "<c:url value='/sample/gridData.do' />",
	        datatype: "json",
	        height: "auto",
	        colNames:['Inv No','Name', 'Amount','Tax'],
	        colModel:[
	             		{name:'id',index:'id', width:60, sorttype:"int"},
	             		{name:'name',index:'name', width:100},
	             		{name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},
	             		{name:'tax',index:'tax', width:80, align:"right",sorttype:"float"}		
	             	],
	        autowidth: true,
	        caption:"Grid Test",
	        width: "auto",
	        rowNum: 10,
	        rowList:[10,20,30],
	        pager: "#paging",
	        ExpandColClick: true,
            jsonReader : { 
				page: "jsonData.page", 
				total: "jsonData.total", 
				root: "jsonData.rows", 
				records: function(obj){return obj.length;},
				repeatitems: false
			},
	        onSelectRow: function(rowid) {
	        	var data = $("#center-grid").jqGrid('getRowData',rowid);
	        	
	        	$.ajax({
	    			type: "post",
	    			url: "<c:url value='/sample/detailForm.do' />",
	    			dataType: "html",			  
	    			success: function(html) {
	    				var $container = $("#detailDiv");
	    	            var layout = $container.data("layout");

	    	            if ( layout )
	    	                layout.destroy();

	    	            $container.html(html);
	    	            $('#tabTitle').html(data.name);
	    			}
	    		});
	        }
		});
		$("#center-grid").jqGrid('navGrid','#paging',{edit:false,add:false,del:false});
	});
</script>
<div class="ui-layout-center inner">
	<table id="center-grid"></table>
	<div id="paging"></div>
</div>

<div class="ui-layout-south inner" id="detailDiv">
	No Data
</div>