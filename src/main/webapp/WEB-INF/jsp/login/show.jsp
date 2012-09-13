<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
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
					url: "<c:url value='/login.do?method=login' />",
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

  	<div id="container">
  	<form:form modelAttribute="login" method="post" id="loginForm" name="loginForm">
    	<div>
			<p>
				<label for="id">ID</label>
				<input type="text" id="loginId" name="loginId" title="아이디입력" />
			</p>
			<p>
				<label for="password">Password</label>
				<input type="password"  id="password" name="password" title="비밀번호입력" />
			</p>
		</div>
		<div>
			<a href="javascript:void(0);" id="loginBtn">Login</a>
		</div>
     </form:form>
	</div>
    <hr />