<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script>
onMenu(2);
</script>
<div style="position:relative; width:618; height:180px; top:130px; left:180px;">
    <div style="float:left;"><a href="<c:url value='/provisioning/wasInstanceForm.do?targetWas=B'/>" ><img src="<c:url value='/images/common/bt_jboss01.png'/>" /></a></div>
    <div><a href="<c:url value='/provisioning/wasInstanceForm.do?targetWas=T'/>"><img src="<c:url value='/images/common/bt_tomcat01.png' />" /></a></div>
    <div class="textbox_sub" style="position:relative; color:#697676!important; font-weight: bold;">
    	<p style="padding:0px 0px 3px 15px;">자동 배포를 선택할 웹 애플리케이션 서버를 선택합니다.</p>
    	<p style="padding:0px 0px 3px 15px;">JBoss: 레드햇에서 제공하는 EAP 5.1.2버전이며, 공개SW 기반의 웹 애플리케이션 서버, 커뮤니티 버전과 상용버전 2가지가 존재합니다.</p>
    	<p style="padding:0px 0px 3px 15px;">Tomcat: 아파치 재단에서 만드는 서블릿 컨테이너이며, 현재 6.0.35버전을 자동으로 설치합니다.</p>
    </div>          
   	
</div>
