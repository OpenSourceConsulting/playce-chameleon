<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
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
				var form = $("#uploadForm").get(0);
				form.action = "<c:url value='/upload.do?method=upload' />";
				form.submit();
			}
		});
	});
</script>

  	<div id="container">
  	<form:form modelAttribute="upload" method="post" id="uploadForm" name="uploadForm" enctype="multipart/form-data">
    	<div>
			<p>
				<label for="projectNm">Project 명</label>
				<input type="text" id="projectNm" name="projectNm" title="Project명 입력" value="${projectNm}" />
			</p>
			<p>
				<label for="person">담당자</label>
				<input type="text" id="person" name="person" title="담당자 입력" value="${person}" />
			</p>
			<p>
				<label for="projectSrc">Project Source</label>
				<input type="file" name="projectSrc" id="projectSrc" title="찾아보기" />
			</p>
			<p>
				<label for="deploySrc">Deploy Source</label>
				<input type="file" name="deploySrc" id="deploySrc" title="찾아보기" />
			</p>
			<p>
				<label for="beforeWas">기존 WAS</label>
				<input type="radio" name="beforeWas" id="beforeWas" title="WEB LOGIC" checked="checked" value="W"/>
				<label for="weblogic">WEB LOGIC</label>
				<input type="radio" name="beforeWas" id="beforeWas" title="JEUS" value="J"/>
				<label for="jeus">JEUS</label>
			</p>
			<p>
				<label for="afterWas">대상 WAS</label>
				<input type="radio" name="afterWas" id="afterWas" title="JBoss" checked="checked" value="B"/>
				<label for="jboss">JBoss</label>
				<input type="radio" name="afterWas" id="afterWas" title="Tomcat" value="T"/>
				<label for="tomcat">Tomcat</label>
			</p>
		</div>
		<div>
			<a href="javascript:void(0);" id="applyBtn">Apply</a>
		</div>
     </form:form>
	</div>
    <hr />