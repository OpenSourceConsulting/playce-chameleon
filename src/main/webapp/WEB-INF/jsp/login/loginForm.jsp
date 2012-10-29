<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR" />
<title>Athena Chameleon - WAS Migration Tool</title>

<link href="<c:url value='/css/login-box.css'/>" rel="stylesheet" type="text/css" />
<style>
body {
	background-image: url(<c:url value='/images/login/bg001.jpg' />);
}
</style>
<script type="text/javascript">
	$(document).ready(function (){
		$('#loginBtn').click(function() { 
			if(!$('#loginId').val()) {
				alert('ID를 입력하세요.');
				$('#loginId').focus();
				return false;
			} else if(!$('#password').val()) {
				alert('Password를 입력하세요.');
				$('#password').focus();
				return false;
			} else {
				var param = "loginId="+$('#loginId').val()
							+ "&password="+$('#password').val();
				
				$.ajax({
					type: "post",
					url: "<c:url value='/login/login.do' />",
					data: param,
					dataType: "json",			  
					success: function(data) {
						if(data.result) {
							document.location = "<c:url value='/main.do' />";
						} else {
							alert('입력한 ID나 Password 정보가 일치하지 않습니다.');
							return false;
						}
					}
				});
			}
		});
		
		$('#loginForm').keydown(function(event) {
			// enter key 입력시 로그인 요청.
			if (event.keyCode == '13') {
				$('#loginBtn').click();
				return false;
			}
		});
	});
</script>


</head>

<body>
	<div class="loginbox_bg">
	<form:form modelAttribute="login" method="post" id="loginForm" name="loginForm">
		<div class="login_formbox">
	    	<div class="login_id01"> <p style="padding-top:8px"> ID </p> </div>
	        <div class="login_id02">
	        	<input id="loginId" name="loginId" type="text" maxlength="20" title="Username" value="admin@nipa.kr" class="input_form01" /> <!-- 글자 입력수 20자로 제한 -->
	        </div>
	        <div class="login_pw01"> <p style="padding-top:8px"> Password </p> </div>
	        <div class="login_pw02">
	        	<input id="password" name="password" type="password" class="input_form01" size="30" />
	        </div>
	        <div class="login_btn">
	        	<a href="javascript:void(0);" id="loginBtn"><img src="<c:url value='/images/login/bt_login01.png'/>" /></a>
	        </div>
	    </div>
	</form:form>
	</div>
</body>
</html>
