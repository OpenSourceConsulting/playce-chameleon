<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script>
onMenu(2);
</script>
<div style="position:relative; width:618; height:180px; top:130px; left:180px;">
    <div style="float:left;"><a href="<c:url value='/provisioning/wasInstanceForm.do?targetWas=B'/>" ><img src="<c:url value='/images/common/bt_jboss01.png'/>" /></a></div>
    <div><a href="<c:url value='/provisioning/wasInstanceForm.do?targetWas=T'/>"><img src="<c:url value='/images/common/bt_tomcat01.png' />" /></a></div>
    <div class="textbox_sub" style="position:relative; color:#697676!important; font-weight: bold;">
    	<p style="padding:0px 0px 3px 15px;">JBoss/Tomcat  지원 버전 표시 항목입니다.</p>
    	<p style="padding:0px 0px 3px 15px;">지원 항목 : 2.x</p>
    </div>          
   	
</div>
