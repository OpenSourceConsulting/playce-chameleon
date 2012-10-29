<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script>
function onMenu(idx) {
	$('#menuDiv').find('div').each(function (num){ 
		if($(this).attr('class').indexOf('bt0') == 0) {
			var img = $(this).find('a').find('img');
			var newSrc = img.attr('src').substring(0, img.attr('src').lastIndexOf('-'));
			
			if($(this).attr('class') == 'bt0'+idx) {
				img.attr('src', newSrc+'-03.jpg');
			} else {
				img.attr('src', newSrc+'-01.jpg');
			}
		}
	});
}
</script>
<div id="menuDiv" class="top_pattern">
	<div class="logo"> <a href="<c:url value='/main.do'/>"><img src="<c:url value='/images/common/logo_top.jpg'/>" /></a></div>
    <div class="bt01"> <a href="<c:url value='/upload/form.do'/>"> <img src="<c:url value='/images/common/bt_01-01.jpg'/>" /></a></div> <!-- Migration -->
    <div class="bt02"> <a href="<c:url value='/provisioning/wasSelectForm.do'/>"><img src="<c:url value='/images/common/bt_02-01.jpg'/>" /></a></div> <!-- Provisioning -->
    <div class="bt03"> <a href="#"> <img src="<c:url value='/images/common/bt_03-01.jpg'/>" /></a></div> <!-- System -->
    <div class="top_text01"> WAS Migration Tool</div> 
    <div class="top_text02">Log In ID : admin@osci.kr</div>
    <div class="top_text03">Last Log In : 2011-12-08 13:00:00 GMT +00:00</div>
</div>

<div class="bt_logout"> <a href="#"> <img src="<c:url value='/images/common/bt_logout-01.png'/>" /></a></div>
