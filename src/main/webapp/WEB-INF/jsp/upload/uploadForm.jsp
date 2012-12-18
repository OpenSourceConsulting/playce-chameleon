<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
	onMenu(1);
	
	var checkFlag = false;
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
					wrapWindowByMask1();
					
					$('#percentageLayer').html(0);
					$('#statusLayer').html('마이그레이션을 시작합니다.');
					
					setInterval(checkProgressStatus,1000);
					
					var form = $("#uploadForm").get(0);
					form.action = "<c:url value='/upload/upload.do' />";
					form.submit();
				}
			}

		});
	});
	
	function checkProgressStatus(){
		if(!checkFlag) {
			
			checkFlag = true;
			
			$.ajax({
				type: "post",
				url: "<c:url value='/upload/getProgressStatus.do' />",
				data: '',
				dataType: "json",			  
				success: function(data) {
					checkFlag = false;
					
					$('#percentageLayer').html(data.percentage);
					
					if(data.currentStatus)
						$('#statusLayer').html(data.currentStatus);
					
				}
			});
			
		}
	}
	
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
	
	function showHelp(obj) {
		$(obj).parents('.formbox_txt02').find('.formbox_help').show();
	}
	
	function hideHelp(obj) {
		$(obj).parents('.formbox_txt02').find('.formbox_help').hide();
	}

	//진행중 메시지 처리
	function wrapWindowByMask1(){
		
		//화면의 높이와 너비를 구한다.        
		var maskHeight = $(document).height();          
		var maskWidth = $(window).width();           
			//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
			$('#mask1').css({'width':maskWidth,'height':maskHeight});

		//애니메이션 효과        
		$('#hideDiv01').fadeIn(1000);    
	}

</script>
<form:form modelAttribute="upload" method="post" id="uploadForm" name="uploadForm" enctype="multipart/form-data">
	<div class="tit_box">기본정보 입력</div>
	
	<div class="bg_formbox">
    	<div class="formbox_txt01"><p style="padding-top:11px; padding-left:15px;">Project명</p></div>
        <div class="formbox_form01"><input type="text" size="40" id="projectNm" name="projectNm" title="Project명 입력" value="" class="input_form02"/></div>
        
        <div class="formbox_txt02"><p style="padding-top:11px; padding-left:15px;">담당자</p></div>
        <div class="formbox_form02"><input type="text" id="person" name="person" title="담당자 입력" value="" class="input_form02" /></div>
        
        <div class="formbox_txt02">
        	<span style="position:absolute;padding-top:8px;padding-left:120px;"><img src="<c:url value='/images/btn_help.gif' />" style="cursor:pointer;" onmouseover="showHelp(this);" onmouseout="hideHelp(this);" /></span>
        	<p style="padding-top:11px; padding-left:15px;">Project Source</p>
        	<div class="formbox_help" style="left:120px;">
        		<p>본 항목은 프로젝트 소스를 입력받는 곳입니다.</p>
        		<p>프로젝트의 소스는 확장자 zip파일 형태로 입력을 받습니다. zip 포맷형태로 생성하여 입력하셔야 합니다.</p>
        		<p>압축 파일내의 디렉토리 구조는 다양한 형태여도 상관이 없습니다.</p>
        		<p>다른 포맷의 압축 파일일 경우 분석이 진행되지 않으니 주의하십시오.</p>
        	</div> 
        </div>
        <div class="formbox_form02">
        	<div class="formbox_sub01"><input type="file" name="projectSrc" id="projectSrc" title="찾아보기" class="input_form02"/></div>
        </div>
        
        <div class="formbox_txt02">
        	<span style="position:absolute;padding-top:8px;padding-left:120px;"><img src="<c:url value='/images/btn_help.gif' />" style="cursor:pointer;" onmouseover="showHelp(this);" onmouseout="hideHelp(this);" /></span>
        	<p style="padding-top:11px; padding-left:15px;">Deploy Source</p>
        	<div class="formbox_help" style="left:120px;">
        		<p>본 항목은 기존 WAS에 디플로이된 애플리케이션을 입력받는 곳입니다.</p>
        		<p>엔터프라이즈 아카이브: 확장자 ear 형태를 가져야 하며, EJB 및 웹 애플리케이션의 압축파일입니다. APP-INF 디렉토리와 디스크립터 정보를 포함하는 META-INF 디렉토리를 가져야 하며 jar명령을 통해 압축된 파일이어야 합니다.</p>
        		<p>웹 아카이브: 확장자 war 형태를 가져야 하며, 웹 애플리케이션의 압축파일입니다. WEB-INF 디렉토리와 디스크립터 정보를 포함하는 web.xml 및 벤더 디스크립터를 가져야 하며 jar명령을 통해 압축된 파일이어야 합니다.</p>
        		<p>EJB 아카이브: 확장자 jar 형태를 가져야 하며, EJB 애플리케이션의 압축파일입니다. META-INF 디렉토리에 EJB관련 디스크립터 정보가 포함되어 있어야 합니다.  jar명령을 통해 압축된 파일이어야 합니다.</p></p>
        	</div>
        </div>
        <div class="formbox_form02">
        	<div class="formbox_sub01"><input type="file" name="deploySrc" id="deploySrc" title="찾아보기" class="input_form02" /></div>
        </div>
        
        <div class="formbox_txt02">
        	<span style="position:absolute;padding-top:8px;padding-left:90px;"><img src="<c:url value='/images/btn_help.gif' />" style="cursor:pointer;" onmouseover="showHelp(this);" onmouseout="hideHelp(this);" /></span>
        	<p style="padding-top:11px; padding-left:15px;">기존 WAS</p>
         	<div class="formbox_help">
        		<p>기존에 사용하셨던 WAS를 선택합니다. </p>
        		<p>&nbsp;</p>
        	</div>
        </div>
        <div class="formbox_form02">
        	<div class="formbox_radioform" style="margin-left:0"><input type="radio" name="beforeWas" id="beforeWas1" class="regular-radio" value="W" checked /><label for="beforeWas1"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">WEB LOGIC</p></div>
            <div class="formbox_radioform"><input type="radio" name="beforeWas" id="beforeWas2" title="JEUS" class="regular-radio" value="J" /><label for="beforeWas2"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">JEUS</p></div>  	
            <div class="formbox_radioform"><input type="radio" name="beforeWas" id="beforeWas3" title="WEBSPHERE" class="regular-radio" value="S" /><label for="beforeWas3"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">WEBSPHERE</p></div>  	
        </div>
				
        <div class="formbox_txt02">
        	<span style="position:absolute;padding-top:8px;padding-left:90px;"><img src="<c:url value='/images/btn_help.gif' />" style="cursor:pointer;" onmouseover="showHelp(this);" onmouseout="hideHelp(this);" /></span>
        	<p style="padding-top:11px; padding-left:15px;">대상 WAS</p>
        	<div class="formbox_help">
        		<p>대상 WAS를 선택합니다.</p>
        		<p>JBoss: Red Hat에서 만드는 표준 JEE의 엔터프라이즈 애플리케이션 서버입니다.</p>
        		<p>Tomcat: 아파치 재단에서 만드는 웹(서블릿) 컨테이너이며, EAR, EJB는 지원하지 않습니다.</p>
        	</div>
        </div>
        <div class="formbox_form02">
        	<div class="formbox_radioform" style="margin-left:0"><input type="radio" name="afterWas" id="afterWas1" title="JBoss" value="B" class="regular-radio" checked /><label for="afterWas1"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">JBoss</p></div>
            <div class="formbox_radioform"><input type="radio" name="afterWas" id="afterWas2" title="Tomcat" value="T" class="regular-radio" /><label for="afterWas2"></label></div>
            <div class="formbox_radiotxt"><p style="padding-top:11px; padding-left:0;">Tomcat</p></div>  	
        </div>
        
        <div class="common_btn"><a href="javascript:void(0);" id="applyBtn"><img src="<c:url value='/images/common/bt_apply01.png' />" /></a></div>        
    </div>        

 </form:form>
