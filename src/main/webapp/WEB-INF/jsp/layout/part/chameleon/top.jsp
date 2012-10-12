<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script>
function onMenu(idx) {
	$('#menuUi').find('li').each(function (num){
		if(num == idx)
			$(this).attr('id', 'active');
		else
			$(this).attr('id', '');
	});
}
</script>
<table border="0">
	<tr width="1024" >
		<td align="left" width="80"><img src="<c:url value='/images/chameleon-icon.png'/>" width="80" height="80"></td>
		<td align="left" width="400"><div class="title">Chameleon</div></td>
		<td align="right" width="524" valign="bottom">Login ID: admin@osci.kr</td>
	</tr>
</table>
<div id="navigation">
	<ul id="menuUi">
    	<li><a href="<c:url value='/main.do'/>">Home</a></li>
        <li><a href="<c:url value='/upload/form.do'/>">Migration</a></li>
        <li><a href="<c:url value='/provisioning/selectForm.do'/>">Provisioning</a></li>
		<li><a href="navigation.html">System</a></li>
		<li><a href="navigation.html">Contact Us</a></li>
	</ul>
</div>    

<br/>
