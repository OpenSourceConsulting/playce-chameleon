<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery-1.6.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery-ui-1.8.16.custom.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.ui.datepicker-ko.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery/jquery.filestyle.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/validator.js'/>"></script>
<!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<!--[if IE 6]><script type="text/javascript" src="/js/xxx.js" /><![endif]-->
<!--[if IE 7]><script type="text/javascript" src="/js/xxx.js" /><![endif]-->
<script type="text/javascript">
$(function() {
});

//진행중 메시지 처리
function wrapWindowByMask(){
	
	//화면의 높이와 너비를 구한다.        
	var maskHeight = $(document).height();          
	var maskWidth = $(window).width();           
		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		$('#mask').css({'width':maskWidth,'height':maskHeight});

	//애니메이션 효과        
	$('#hideDiv').fadeIn(1000);    
}

</script>