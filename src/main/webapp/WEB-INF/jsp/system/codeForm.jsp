<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
	onMenu(3);

	function saveCode(){
		var param = "code="+$('#code').val();

		$.ajax({
			type: "post",
			url: "<c:url value='/system/saveCode.do' />",
			data: param,
			dataType: "json",			  
			success: function(data) {
				if(data.result) {
					alert('저장되었습니다.');
					document.location = "<c:url value='/system/codeForm.do' />";
				} else {
					alert('저장도중 에러가 발생하였습니다.');
					return false;
				}
			}
		});
		
	}
</script>
<form:form modelAttribute="code" method="post" id="codeForm" name="codeForm" >
	<div class="tit_box">코드관리</div>
    <div class="bt_tab_box02">
    	<div class="bt_tab_txt01" style="color:#858686"><a href="#">사용자매뉴얼</a></div>
        <div class="bt_tab_txt01">코드관리</div>
    </div>
	<div class="bg_formbox" style="height:517px;"> <!-- form배경 박스 높이를 517로 늘임 -->
    	<div class="textbox" style="margin-top:-30px; margin-left:-15px;">          
          <div class="textbox_sub">아래의 설정은 시스템 프로퍼티로써 마이그레이션에 대한 임시디렉토리, 설정 등을 변경할 수 있습니다.</div>          
        </div> 
        <div style="position:relative; left:60px; top:55px;">
            <textarea name="code" id="code" class="style_textarea">${code}</textarea>
        </div>
        <div class="common_btn" style="margin-top:70px;"><a href="javascript:saveCode();"><img src="<c:url value='/images/common/bt_save01.png'/>" /></a></div>
    </div>
 </form:form>
