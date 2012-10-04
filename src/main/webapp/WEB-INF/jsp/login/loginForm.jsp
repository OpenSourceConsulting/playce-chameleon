<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR" />
<title>Login Box HTML Code - www.PSDGraphics.com</title>

<link href="/chameleon/css/login-box.css" rel="stylesheet" type="text/css" />

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
							document.location = "<c:url value='/upload.do?method=show' />";
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


<div style=" auto;">


<div id="login-box">

<H2>Login</H2>
WAS Migration Tool for NCIA and Supported by Open Source Consulting.
<br />
<br />
<form:form modelAttribute="login" method="post" id="loginForm" name="loginForm">
	<div id="login-box-name" style="margin-top:20px;">Email:</div><div id="login-box-field" style="margin-top:20px;"><input id="loginId" name="loginId" class="form-login" title="Username" value="" size="30" maxlength="2048" /></div>
		<div id="login-box-name">Password:</div><div id="login-box-field"><input id="password" name="password" type="password" class="form-login" title="Password" value="" size="30" maxlength="2048" /></div>
			<br />
			<span class="login-box-options"><input type="checkbox" name="1" value="1"> Remember Me <a href="#" style="margin-left:30px;">Forgot password?</a></span>
			<br />
			<br />
			<a href="#" id="loginBtn"><img src="/chameleon/images/login-btn.png" width="103" height="42" style="margin-left:90px;" /></a>
		</div>
	</div>
 </form:form>
</body>
</html>
