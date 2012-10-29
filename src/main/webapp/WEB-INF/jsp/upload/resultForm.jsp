<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
onMenu(1);

$(document).ready(function (){
	
	if('${result}' == 'false') {
		alert('마이그레이션 도중 에러가 발생하였습니다.');
		location.href = "<c:url value='/upload/form.do'/>"; 
	}
	
	$('.down').click(function() {
		if($(this).attr('val')) {
			downloadFile($(this).attr('val'));
		}
	});

});

function downloadFile(fileName){
	location.href="<c:url value='/file/download.do?path="+fileName+"'/>";
}

</script>

	<div class="tit_box">기본정보 입력</div>

	<div class="bg_formbox">
    	<div class="formbox_subtit"><p style="padding-left:12px;">입력 정보</p></div>
    	<div class="formbox_txt01"><p style="padding-top:4px; padding-left:15px;">Project명</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:4px;">${upload.projectNm}</div>
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">담당자</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${upload.person}</div>
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">기존 WAS</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${upload.beforeWas}</div>
        <div class="formbox_txt01"><p style="padding-top:2px; padding-left:15px;">대상 WAS</p></div>
        <div class="formbox_txt01-1"><p style="padding-top:2px;">${upload.afterWas}</div>
        
        <div class="formbox_subtit"><p style="padding-left:12px; margin-top:20px;">변환 결과</p></div>
        
        <c:if test="${!empty metaData.pdfFile}">
	    	<div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">변환 PDF Report</p></div>
	        <div class="formbox_txt01-1">
	        	<div class="formbox_sub01a"><p style="padding-top:25px;">${metaData.pdfFileName}</p></div>
	        	<div class="formbox_sub01"><p style="padding-top:23px; padding-left:10px;"><a href="javascript:void(0);" val="${metaData.pdfFile}" class="down"><img src="<c:url value='/images/common/bt_download.png'/>" /></a></p></div>
	        </div>
	    </c:if>
	    <c:if test="${!empty metaData.migrateSourceFile}">
	        <div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">변환 Source File</p></div>
	        <div class="formbox_txt01-1">
	        	<div class="formbox_sub01a"><p style="padding-top:25px;">${metaData.migrateSourceFileName}</p></div>
	        	<div class="formbox_sub01"><p style="padding-top:23px; padding-left:10px;"><a href="javascript:void(0);" val="${metaData.migrateSourceFile}" class="down"><img src="<c:url value='/images/common/bt_download.png'/>" /></a></p></div>
	        </div>
	    </c:if>
	    <c:if test="${!empty metaData.migrateDeployFile}">
	        <div class="formbox_txt01"><p style="padding-top:25px; padding-left:15px;">변환 Deploy File</p></div>
	        <div class="formbox_txt01-1">
	        	<div class="formbox_sub01a"><p style="padding-top:25px;">${metaData.migrateDeployFileName}</p></div>
	        	<div class="formbox_sub01"><p style="padding-top:23px; padding-left:10px;"><a href="javascript:void(0);" val="${metaData.migrateDeployFile}" class="down"><img src="<c:url value='/images/common/bt_download.png'/>" /></a></p></div>
	        </div>
	    </c:if>
       </div>