<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<style>
#mask {    position:absolute;    left:0;  top:0;  z-index:9000;    background-color:#ffffff;    display:none; filter:alpha(opacity=70); opacity:0.7; -moz-opacity:0.7 }
#maskContents { position:absolute; text-align:center; width:100%; font: bold 15px Arial; z-index:9999; }
</style>
<script type="text/javascript">
	onMenu(1);

	$(document).ready(function (){
		
		$('#applyBtn').click(function() { 
			if(!$('#projectNm').val()) {
				alert('Project 명을 입력하세요.');
				$('#projectNm').focus();
				return false;
			} else if(!$('#person').val()) {
				alert('담당자를 입력하세요.');
				$('#person').focus();
				return false;
			} else if((!$('#projectSrc').val()) && (!$('#deploySrc').val())) {
				alert('Project Source 나 Deploy Source 중 한 항목 이상 업로드하세요.');
				$('#projectSrc').focus();
				return false;
			} else {
				wrapWindowByMask();
				
				var form = $("#uploadForm").get(0);
				form.action = "<c:url value='/upload/upload.do' />";
				form.submit();
			}
		});
	});
	
	//진행중 메시지 처리
	function wrapWindowByMask(){
		
		var div = $('	<div id="mask" style="display:none; ">' 
				+ '			<div id="maskContents"> '
				+ '				마이그레이션 진행중입니다..'
				+ '				<br/><br/>' 
				+ '				<img src="<c:url value="/images/spinner.gif"/>"/>' 
				+ '			</div>'
				+ ' 	</div> ');
		
		$('body').append(div);
		
		//화면의 높이와 너비를 구한다.        
		var maskHeight = $(document).height();          
		var maskWidth = $(window).width();           
 		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
 		$('#mask').css({'width':maskWidth,'height':maskHeight});

 		//text를 화면 중앙에 구현.
 		var top = (maskHeight / 2) - 70;          
		$('#maskContents').css('top', top);
 		
		//애니메이션 효과        
		$('#mask').fadeIn(1000);    
	}
	
</script>
<form:form modelAttribute="upload" method="post" id="uploadForm" name="uploadForm" enctype="multipart/form-data">
	<table border="0">
		<tr>
			<td colspan="2"><div class="title">Input Project</div></td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr height="30">
			<td width="150"><b>프로젝트명: </b></td>
			<td width="500"><input type="text" size="40" id="projectNm" name="projectNm" title="Project명 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>담당자: </b></td>
			<td width="500"><input type="text" id="person" name="person" title="담당자 입력" value="" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>프로젝트 소스: </b></td>
			<td width="500"><input type="file" name="projectSrc" id="projectSrc" title="찾아보기" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>디플로이 애플리케이션 </b></td>
			<td width="500"><input type="file" name="deploySrc" id="deploySrc" title="찾아보기" /></td>
		</tr>
		<tr height="30">
			<td width="150"><b>기존 WAS </b></td>
			<td width="500">
				WebLogic<input type="radio" name="beforeWas" id="beforeWas" title="WEBLOGIC" checked="checked" value="W"/>
				Jeus<input type="radio" name="beforeWas" id="beforeWas" title="JEUS" value="J"/>
			</td>
		</tr>
		<tr height="30">
			<td width="150"><b>대상 WAS </b></td>
			<td width="500">
				JBoss<input type="radio" name="afterWas" id="afterWas" title="JBoss" checked="checked" value="B" border="0"/>
				Tomcat<input type="radio" name="afterWas" id="afterWas" title="Tomcat" value="T"/>
			</td>
		</tr>
		<tr height="30">
			<td colspan="2"><a href="javascript:void(0);" id="applyBtn"><input type="submit" id="applyBtn" value="Start Analyze"></a></td>
		</tr>
	</table>
	
 </form:form>
