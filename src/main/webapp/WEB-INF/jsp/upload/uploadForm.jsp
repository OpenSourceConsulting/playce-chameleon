<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
	onMenu(1);

	$(document).ready(function (){
		
		$('#applyBtn').click(function() { 
			if(!$('#projectNm').val()) {
				alert('Project 명이 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#projectNm').focus();
				return false;
			} else if(!$('#person').val()) {
				alert('담당자가 입력되지 않았습니다. 정보를 입력하여 주십시오.');
				$('#person').focus();
				return false;
			} else if((!$('#projectSrc').val()) && (!$('#deploySrc').val())) {
				alert('Project Source 나 Deploy Source 중 한 항목 이상 업로드하여 주십시오.');
				$('#projectSrc').focus();
				return false;
			} else if(!fileExpCheck($('#projectSrc').val(), 'project')) {
				return false;
			} else if(!fileExpCheck($('#deploySrc').val(), 'deploy')) {
				return false;
			} else {
				if(confirm('마이그레이션을 진행하시겠습니까?')) {
					wrapWindowByMask();
					
					var form = $("#uploadForm").get(0);
					form.action = "<c:url value='/upload/upload.do' />";
					form.submit();
				}
			}

		});
	});
	
	//확장자 체크
	function fileExpCheck(fileName, type) {
		var fieldName = 'Project Source';
		if(type == 'deploy')
			fieldName=  'Deploy Source';
		
		if(fileName == '') {
			return true;
		}
		
		if(fileName.lastIndexOf('.') < 0) {
			alert(fieldName + '에 잘못된 파일이 첨부되었습니다.');
			return false;
		} else {
			var fileExp = fileName.substring(fileName.lastIndexOf('.')+1,fileName.length).toUpperCase();
			if(type == 'project' && fileExp != 'ZIP') {
				alert(fieldName + '는 ZIP 파일만 업로드 가능합니다.');
				$('#projectSrc').parents('.formbox_sub01').find('input').val('');
				$('#projectSrc').select();
				document.selection.clear();
				
				$('#projectSrc').focus();
				return false;
			} else if(type == 'deploy' && fileExp != 'WAR' && fileExp != 'JAR' && fileExp != 'EAR') {
				alert(fieldName + '는 WAR, JAR, EAR 파일만 업로드 가능합니다.');
				$('#deploySrc').parents('.formbox_sub01').find('input').val('');
				$('#deploySrc').select();
				document.selection.clear();
				
				$('#deploySrc').focus(); 
				return false;
			}
		}
		
		return true;
	}
	
</script>
<form:form modelAttribute="upload" method="post" id="uploadForm" name="uploadForm" enctype="multipart/form-data">
	<div class="tit_box">기본정보 입력</div>
	
	<div class="bg_formbox">
    	<div class="formbox_txt01"><p style="padding-top:11px; padding-left:15px;">Project명</p></div>
        <div class="formbox_form01"><input type="text" size="40" id="projectNm" name="projectNm" title="Project명 입력" value="" class="input_form02"/></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">담당자</p></div>
        <div class="formbox_form02"><input type="text" id="person" name="person" title="담당자 입력" value="" class="input_form02" /></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Project Source</p></div>
        <div class="formbox_form02">
        	<div class="formbox_sub01"><input type="file" name="projectSrc" id="projectSrc" title="찾아보기" class="input_form02"/></div>
        </div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">Deploy Source</p></div>
        <div class="formbox_form02">
        	<div class="formbox_sub01"><input type="file" name="deploySrc" id="deploySrc" title="찾아보기" class="input_form02" /></div>
        </div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">기존 WAS</p></div>
        <div class="formbox_form02">
        	<div class="formbox_radioform" style="margin-left:0"><input type="radio" name="beforeWas" id="beforeWas1" class="regular-radio" value="W" checked /><label for="beforeWas1"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">WEB LOGIC</p></div>
            <div class="formbox_radioform"><input type="radio" name="beforeWas" id="beforeWas2" title="JEUS" class="regular-radio" value="J" /><label for="beforeWas2"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">JEUS</p></div>  	
        </div>
				
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">대상 WAS</p></div>
        <div class="formbox_form02">
        	<div class="formbox_radioform" style="margin-left:0"><input type="radio" name="afterWas" id="afterWas1" title="JBoss" value="B" class="regular-radio" checked /><label for="afterWas1"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">JBoss</p></div>
            <div class="formbox_radioform"><input type="radio" name="afterWas" id="afterWas2" title="Tomcat" value="T" class="regular-radio" /><label for="afterWas2"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">Tomcat</p></div>  	
        </div>
        
        <div class="common_btn"><a href="javascript:void(0);" id="applyBtn"><img src="<c:url value='/images/common/bt_apply01.png' />" /></a></div>        
    </div>        
    
 </form:form>
