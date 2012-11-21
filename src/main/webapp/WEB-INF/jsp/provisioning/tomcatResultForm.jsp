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

function setToggle(num) {
	$('.bg_formbox').find('.toggleTab').each(function(idx) {
		if(idx == num)
			$(this).toggle();
	});
}

</script>

	<div class="tit_box">Provisioning 결과</div>

	<div class="bg_formbox" style="height:517px;">
		<div style="width:840px;height:500px;overflow-x:hidden;overflow-y:scroll; margin-top:10px;">
	    	<div class="formbox_subtit"><a href="javascript:setToggle(0);"><p style="padding-left:12px;">Tomcat 인스턴스 입력 정보</p></a></div>
	    	
	    	<div class="toggleTab">
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
		        
		    	<div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">HTTP_PORT</p></div>
		        <div class="formbox_txt01-2"><p style="padding-top:2px;">${provisioning.tomcatInstance.httpPort}</div>
		        
		    	<div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">AJP_PORT</p></div>
		        <div class="formbox_txt01-3"><p style="padding-top:2px;">${provisioning.tomcatInstance.ajpPort}</div>
		        
		    	<div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">SSL_PORT</p></div>
		        <div class="formbox_txt01-2"><p style="padding-top:2px;">${provisioning.tomcatInstance.sslPort}</div>
		        
		    	<div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">SHUTDOWN_PORT</p></div>
		        <div class="formbox_txt01-3"><p style="padding-top:2px;">${provisioning.tomcatInstance.shutdownPort}</div>
		        
		    	<div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">USER_ACCOUNT</p></div>
		        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.tomcatInstance.userAccount}</div>
	        </div>
	        
	        <div class="formbox_subtit" style="padding-bottom: 20px;"><a href="javascript:setToggle(1);"><p style="padding-left:12px; margin-top:20px;">Provisioning 처리 순서</p></a></div>
	        
	        <div class="toggleTab">
		        <c:forEach var="proc" items="${provisioningResult.processSequence}">
		        	<div class="formbox_txt03"><p style="padding-top:2px; padding-left:15px;">${proc}</p></div>
		        </c:forEach>
		    </div>
	        
	        
	        <div class="formbox_subtit" style="padding-bottom: 20px;"><a href="javascript:setToggle(2);"><p style="padding-left:12px; margin-top:20px;">SSH 실행 결과</p></a></div>
	        
	        <div class="toggleTab">
		        <div class="formbox_txt03" style="padding-left:12px; padding-top: 2px; height: 120px;">
		            <textarea class="style_textarea" style="height:120px; margin-top:2px;" readonly >${provisioningResult.sshExecuteResult}</textarea>
		        </div>
		    </div>
	        
	        <div style="height:100px; width:730px; float: left;"></div>
	     </div>
       </div>