<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script>
onMenu(0);
</script>
	<div class="bg_formbox" style="background-color:white; height: 580px; top: 40px;">

    	<div class="intro_box">
        	<div class="intro_box-img"><img src="<c:url value='/images/intro/icon_01.png'/>" /></div>
            <div class="intro_box-tit">Analysis</div>
            <div class="intro_box-text">
		            본 마이그레이션 도구는 WebLogic/Jeus 기반의 상용 애플리케이션 서버 상의 애플리케이션 및 프로젝트를
			분석하여 오픈소스 기반의 웹 애플리케이션 서버로 전환을 돕는 도구입니다.
			추출할 수 있는 애플리케이션으로는 EAR, WAR, JAR와 프로젝트 소스에 대한 분석을 진행합니다.
            </div>
        </div>
        <br /><br /><br />
        <div class="intro_box">
        	<div class="intro_box-img"><img src="<c:url value='/images/intro/icon_02.png'/>" /></div>
            <div class="intro_box-tit">Report</div>
            <div class="intro_box-text">
	          EAR, WAR, JAR 및  프로젝트 소스 입력의 결과는 모두 분석되며 PDF 결과 보고서로 만들어 집니다.
			결과 보고서를 활용하여 향후 오픈소스 웹 애플리케이션 서버로 마이그레이션을 수행할 때 상용 
			애플리케이션 서버에 의존성이 있는 코드들을 모두 명시해 줌으로써 효과적인 마이그레이션이 되도록
			도와줍니다. 예외사항으로는 EJB 중 엔티티빈에 대한 마이그레이션은 지원하지 않습니다.
            </div>
        </div>
        <br /><br /><br />
        <div class="intro_box">
        	<div class="intro_box-img" style="margin-bottom: 100px;"><img src="<c:url value='/images/intro/icon_03.png'/>" /></div>
            <div class="intro_box-tit">Provision</div>
            <div class="intro_box-text">
		            사전적인 의미로 준비, 예비라는 용어로써 IT에서의 프로비저닝은 자원을 준비해 놓은 상태에서 정의된 정책 및 서비스를 사용자에게 내려주는 것을 말합니다.  즉 IT 인프라 자원을 사용자 또는 비즈니스의 요구사항에 맞게 할당, 배치, 배포하여 시스템을 즉시 사용가능하도록 만드는 것을 가리킵니다.<br/><br/>
			  본 프로비저닝 도구는 미리 구성된 JBoss/Tomcat을 설치, 배포함으로써 사용자가 설정에 대한 부분을 손쉽게 구성할 수 있도록 돕는 역할을 수행합니다. 웹 애플리케이션 서버를 모르는 사용자의 경우 설치부터 어려울지 있지만 이러한 프로비저닝을 사용하면 관리를 쉽게 할 수 있습니다.
            </div>
        </div>
    </div>
