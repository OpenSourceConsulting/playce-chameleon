<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script>
	
	function clickMenuSelectBox(obj) {
		$('#leftMenuSelectNm').text($(obj).text());
	}
	
	function setMenuView(idx) {
		$('#menuDiv'+idx).toggle();
	}
	
	function goPage(pageUrl) {
		$.ajax({
			type: "post",
			url: pageUrl,
			dataType: "html",			  
			success: function(html) {
				var $container = $("#detailForm");
	            var layout = $container.data("layout");

	            if ( layout )
	                layout.destroy();

	            $container.html(html);
			}
		});
	}
</script>

<div style="padding: 10px; 10px; 10px; 10px;">
	<p><b>Navigation</b></p>
	<br/>
	<p>Region :</p>
	<select id="menuS" name="menuS" class="custom-class1 custom-class2" >
		<option value="1">Asia</option>
		<option value="2">Us East</option>
		<option value="3">Us West</option>
		<option value="4">...</option>
	</select> 
	<br/>
	<br/>
	<a href="javascript:goPage('<c:url value='/sample/ajax/dashBoard.do' />');"><p>EC2 Dashboard</p></a>
	<p>Event</p>
	<a href="javascript:setMenuView('1');"><p><i>INSTANCE</i></p></a>
	<div id="menuDiv1" style="padding: 0px 1em 3px 10px;">
		<a href="javascript:goPage('<c:url value='/sample/gridForm.do' />');"><p>Instance</p></a>
		<p>Spot Requests</p>
		<p>Reserved Instances</p>
	</div>
	<a href="javascript:setMenuView('2');"><p><i>IMAGES</i></p></a>
	<div id="menuDiv2" style="padding: 0px 1em 3px 10px;">
		<p>Instance</p>
		<p>Spot Requests</p>
		<p>Reserved Instances</p>
	</div>
</div>