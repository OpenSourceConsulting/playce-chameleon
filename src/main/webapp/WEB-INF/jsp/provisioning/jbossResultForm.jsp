<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
onMenu(2);

$(document).ready(function (){
	
	if('${result}' == 'false') {
		alert('마이그레이션 도중 에러가 발생하였습니다.');
		location.href = "<c:url value='/provisioning/wasSelectForm.do'/>"; 
	}
	
});

</script>

	<div class="tit_box">Provisioning 결과</div>

	<div class="bg_formbox" style="height: 900px;">
    	<div class="formbox_subtit"><p style="padding-left:12px;">JBoss 인스턴스 입력 정보</p></div>
    	
    	<div class="formbox_txt01"><p style="padding-top:4px; padding-left:15px;">Server IP</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:4px;">${provisioning.jbossInstance.serverIp}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">Server Port</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.serverPort}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">SSH Login ID</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.sshLoginId}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">SSH Login Password</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.sshLoginPassword}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">JBoss Home</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.jbossHome}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">JBoss Server Home</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.serverHome}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">JBoss Server Name</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.serverName}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">Partition Name</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.partitionName}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">Bind Address</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.bindAddress}</div>
        
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">Bind Port</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${provisioning.jbossInstance.bindPort}</div>
        
        <div class="formbox_subtit"><p style="padding-left:12px; margin-top:20px;">JBoss 데이터소스 입력 정보</p></div>
        
    	<div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">Database 유형</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:25px;">${provisioning.dataSource.databaseType}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">JNDI 이름</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:25px;">${provisioning.dataSource.jndiName}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">Connection URL</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:25px;">${provisioning.dataSource.connectionUrl}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">User Name</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:25px;">${provisioning.dataSource.userName}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">Password</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:25px;">${provisioning.dataSource.password}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">Min Pool Size</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:25px;">${provisioning.dataSource.minPoolSize}</div>
        
    	<div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">Max Pool Size</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:25px;">${provisioning.dataSource.maxPoolSize}</div>
        
       </div>