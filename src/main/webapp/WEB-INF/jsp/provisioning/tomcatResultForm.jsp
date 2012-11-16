<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
onMenu(2);

$(document).ready(function (){
	
	if('${provisioningResult.succeed}' == 'false') {
		alert('마이그레이션 도중 에러가 발생하였습니다.');
		location.href = "<c:url value='/provisioning/wasSelectForm.do'/>"; 
	}
	
});

</script>

	<div class="tit_box">Provisioning 결과</div>

	<div class="bg_formbox" style="height: 950px;">
    	<div class="formbox_subtit"><p style="padding-left:12px;">Tomcat 인스턴스 입력 정보</p></div>
    	
    	<div class="formbox_txt01"><p style="padding-top:4px; padding-left:15px;">Server IP</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:4px;">${provisioning.tomcatInstance.serverIp}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">JAVA_HOME</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.tomcatInstance.javaHome}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">SERVER_NAME</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.tomcatInstance.serverName}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">CATALINA_HOME</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.tomcatInstance.catalinaHome}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">CATALINA_BASE</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.tomcatInstance.catalinaBase}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:4px; padding-left:15px;">HTTP_PORT</p></div>
        <div class="formbox_txt01-2"><p style="padding-top:4px;">${provisioning.tomcatInstance.httpPort}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:4px; padding-left:15px;">AJP_PORT</p></div>
        <div class="formbox_txt01-3"><p style="padding-top:4px;">${provisioning.tomcatInstance.ajpPort}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:4px; padding-left:15px;">SSL_PORT</p></div>
        <div class="formbox_txt01-2"><p style="padding-top:4px;">${provisioning.tomcatInstance.sslPort}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:4px; padding-left:15px;">SHUTDOWN_PORT</p></div>
        <div class="formbox_txt01-3"><p style="padding-top:4px;">${provisioning.tomcatInstance.shutdownPort}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:4px; padding-left:15px;">USER_ACCOUNT</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:4px;">${provisioning.tomcatInstance.userAccount}</div>
        
        
        <div class="formbox_subtit"><p style="padding-left:12px; margin-top:20px;">Provisioning 처리 순서</p></div>
        
        <c:forEach var="proc" items="${provisioningResult.processSequence}">
        	<div class="formbox_txt03"><p style="padding-top:25px; padding-left:15px;">${proc}</p></div>
        </c:forEach>
        
        
        <div class="formbox_subtit"><p style="padding-left:12px; margin-top:40px;">SSH 실행 결과</p></div>
        
        <div style="position:relative; left:60px; top:55px; padding-left:12px; ">
            <textarea class="style_textarea" style="height:120px; margin-top:40px;" readonly >${provisioningResult.sshExecuteResult}</textarea>
        </div>
        
       </div>