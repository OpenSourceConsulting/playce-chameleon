<%@ page language="java" errorPage="/sample/common/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<script type="text/javascript">
	onMenu(3);
</script>
<div class="tit_box">사용자 매뉴얼</div>
    <div class="bt_tab_box01">
   	<div class="bt_tab_txt01">사용자매뉴얼</div>
       <div class="bt_tab_txt01" style="color:#858686"><a href="<c:url value='/system/codeForm.do' />">코드관리</a></div>
   	</div>
	<div class="bg_formbox" style="height:517px; "> <!-- form배경 박스 높이를 517로 늘임 -->
		<div style="width:840px;height:500px;overflow-x:hidden;overflow-y:scroll; margin-top:10px;">
			
			<div class="textbox">
				<div class="textbox_tit">1. Login</div>
	        	<div class="textbox_sub2" >
	        		<p>로그인이란 시스템에 정의된 아이디와 비밀번호를 이용하여 Atena Chameleon 시스템에 접속하는 과정을 의미한다.</p>
	        		<br/>
	        		<p>1.	웹 브라우저를 열어 Atena Chameleon 에 접속한다.</p>
	        		<img src="<c:url value='/images/manual/login_1.jpg' />" />
	        		
	        		<p>2.	시스템에 정의된 아이디 및 비밀번호를 입력하고 Login 버튼을 클릭한다. 아이디와 비밀번호가 일치하지 않을 경우 에러 경고창이 실행된다.</p>
	        		<img src="<c:url value='/images/manual/login_2.jpg' />" />
	        		
	        		<p> -	로그인 실패시 에러 경고창 표시</p>
	        		<img src="<c:url value='/images/manual/login_3.jpg' />" />
	        		
	        		<p>3.	로그인이 성공하면 메인화면으로 이동된다.</p>
	        		<img src="<c:url value='/images/manual/login_4.jpg' />" />
	        		
	        	</div>
	        </div> 
	        
	        <div class="textbox">
	        	<div class="textbox_tit">2. Migration</div>
	        	<div class="textbox_sub2">
	        		<p>Migartion 도구는 WebLogic/Jeus 기반의 상용 애플리케이션 서버 상의 애플리케이션 및 프로젝트를 분석하여 오픈소스 기반의 웹 애플리케이션 서버로 전환을 돕는 도구이다. 추출할 수 있는 애플리케이션으로는 EAR, WAR, JAR와 프로젝트 소스에 대한 분석을 진행하며 Athena Chameleon 시스템에서 마이그레이션을 진행시 다음 절차를 따른다.</p>
	        		<br/>
	        		
	        		<p class="subtit">2.1	기본정보 입력</p>
	        		<p>마이그레이션을 진행할 프로젝트의 기본정보를 입력하는 화면이다.</p>
	        		<br/>
	        		
	        		<p>1.	화면 상단에 Migration 메뉴를 클릭한다.</p>
	        		<img src="<c:url value='/images/manual/migration_1.jpg' />" />
	        		
	        		<p>2.	기본정보 화면에서 마이그레이션을 진행할 프로젝트의 기본정보를 입력한다. </p>
	        		<img src="<c:url value='/images/manual/migration_2.jpg' />" />
	        		
	        		<p>3.	기본정보를 모두 입력한 후에 Apply 버튼을 클릭한다. 입력이 누락된 항목이 존재할 경우 입력확인 경고창이 표시된다.</p>
	        		<br/>
	        		<p>-	Project Source는 zip 형태의 파일을 입력받으며 Deploy Source는 ear, war, jar 형태의 파일을 입력받는다.</p>
	        		<p>-	Project Source와 Deploy Source의 경우 둘 중 하나의 파일만 입력되어도 마이그레이션 진행이 가능하다.</p>
	        		<img src="<c:url value='/images/manual/migration_3.jpg' />" />
	        		
	        		<p>-	입력이 누락된 항목이 존재할 경우 입력확인 경고창 표시</p>
	        		<img src="<c:url value='/images/manual/migration_4.jpg' />" />
	        		
	        		<p>4.	모든 정보가 정상적으로 입력된 경우 마이그레이션이 진행되며 진행중임을 알리는 레이어 팝업이 노출되고 정상적으로 완료되면 결과 확인 화면이 노출된다.</p>
	        		<br/>
	        		<p>-	진행중임을 알리는 레이어 팝업</p>
	        		<img src="<c:url value='/images/manual/migration_5.jpg' />" />
	        		<p>-	결과 확인 화면</p>
	        		<img src="<c:url value='/images/manual/migration_6.jpg' />" />
	        		
	        		<p>5.	결과 확인 화면에서는 기본정보에서 입력한 정보를 확인 할 수 있으며 Download 버튼을 눌러 마이그레이션이 완료된 수정된 파일 및 결과 PDF를  다운로드 하여 확인할 수 있다.</p>
	        		<img src="<c:url value='/images/manual/migration_7.jpg' />" />
	        	</div>          
	        </div>
	         
	        <div class="textbox">
	        	<div class="textbox_tit">3.	Provisioning</div>
	        	<div class="textbox_sub2">
	        		<p>사전적인 의미로 준비, 예비라는 용어로써 IT에서의 프로비저닝은 자원을 준비해 놓은 상태에서 정의된 정책 및 서비스를 사용자에게 내려주는 것을 말한다.  즉 IT 인프라 자원을 사용자 또는 비즈니스의 요구사항에 맞게 할당, 배치, 배포하여 시스템을 즉시 사용가능하도록 만드는 것을 가리킨다.<br/><br/>
			  		 본 프로비저닝 도구는 미리 구성된 JBoss/Tomcat을 설치, 배포함으로써 사용자가 설정에 대한 부분을 손쉽게 구성할 수 있도록 돕는 역할을 수행한다. 웹 애플리케이션 서버를 모르는 사용자의 경우 설치부터 어려울지 있지만 이러한 프로비저닝을 사용하면 관리를 쉽게 할 수 있다. <br/><br/>
			  		 Athena Chameleon 시스템에서 프로비저닝을 진행시 다음 절차를 따른다.</p>
	        		<br/>
	        		
	        		<p class="subtit">3.1	Provisioning 대상 Was 선택 </p>
	        		<p>Provisioning을 진행할 Was를 선택하는 화면이며 선택 가능한 Was는 JBoss, Tomcat이 존재한다. </p>
	        		<br/>
	        		
	        		<p>1.	화면 상단에 Provisioning 메뉴를 클릭한다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_1.jpg' />" />	        		
	        		
	        		<p>2.	화면에서 Provisioning을 진행할 Was를 선택한다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_2.jpg' />" />
	        		
	        		<p>3.	Was를 선택하면 선택한 Was의 인스턴스 정보 입력받는 화면으로 이동된다.</p>
	        		<br/>
	        		
	        		<p class="subtit">3.2	JBoss 인스턴스 정보 입력</p>
	        		<p>Was 선택화면에서 JBoss 를 선택하면 노출되는 화면으로 Provisioning시 필요한 JBoss 의 인스턴스 정보를 입력받는 화면이다.</p>
	        		<br/>
	        		
	        		<p>1.	Was 선택 화면에서 JBoss를 선택한다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_3.jpg' />" />
	        		
	        		<p>2.	JBoss 인스턴스 정보 입력 화면에서 필요한 정보를 모두 입력한다. JBoss 가 아닌 Tomcat의 Provisioning을 원할 경우 상단 메뉴 밑의 Tomcat 탭을 클릭하여 Tomcat 인스턴스 정보 입력화면으로 이동 가능하다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_4.jpg' />" />
	        		<p>-	Tomcat 탭 클릭</p>
	        		<img src="<c:url value='/images/manual/provisioning_5.jpg' />" />
	        		
	        		<p>3.	정보를 모두 입력한 후에 Next 버튼을 클릭한다. 입력이 누락된 항목이 존재할 경우 입력확인 경고창이 표시된다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_6.jpg' />" />
	        		<p>-	입력이 누락된 항목이 존재할 경우 입력확인 경고창 표시</p>
	        		<img src="<c:url value='/images/manual/provisioning_7.jpg' />" />
	        		
	        		<p>4.	모든 정보가 정상적으로 입력된 경우 JBoss 데이터소스 정보입력 화면이 노출된다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_8.jpg' />" />
	        		
	        		<p>5.	데이터소스 정보를 모두 입력한 후에 Install 버튼을 클릭한다. 입력이 누락된 항목이 존재할 경우 입력확인 경고창이 표시된다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_9.jpg' />" />
	        		<p>-	입력이 누락된 항목이 존재할 경우 입력확인 경고창 표시</p>
	        		<img src="<c:url value='/images/manual/provisioning_10.jpg' />" />
	        		
	        		<p>6.	모든 정보가 정상적으로 입력된 경우 프로비저닝이 진행되며 진행중임을 알리는 레이어 팝업이 노출되고 정상적으로 완료되면 결과 확인 화면이 노출된다.</p>
	        		<br/>
	        		<p>-	진행중임을 알리는 레이어 팝업</p>
	        		<img src="<c:url value='/images/manual/provisioning_11.jpg' />" />
	        		<p>-	결과 확인 화면</p>
	        		<img src="<c:url value='/images/manual/provisioning_12.jpg' />" />
	        		
	        		<p>7.	결과 확인 화면에서는 입력한 JBoss 인스턴스 정보, 데이터 소스 정보 및 결과 정보를 확인할 수 있다.</p>
	        		<br/>

	        		<p class="subtit">3.3	Tomcat 인스턴스 정보 입력</p>
	        		<p>Was 선택화면에서 Tomcat을 선택하면 노출되는 화면으로 Provisioning시 필요한 Tomcat 인스턴트 정보를 입력받는 화면이다.</p>
	        		<br/>
	        		
	        		<p>1.	Was선택 화면에서 Tomcat을 선택한다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_13.jpg' />" />	        		
	        		
	        		<p>2.	Tomcat 인스턴스 정보 입력 화면에서 필요한 정보를 모두 입력한다. Tomcat이 아닌 JBoss의 Provisioning을 원할 경우 상단 메뉴 밑의 JBoss 탭을 클릭하여 JBoss 인스턴스 정보 입력화면으로 이동 가능하다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_14.jpg' />" />	        		
	        		<p>-	JBoss 탭 클릭</p>
	        		<img src="<c:url value='/images/manual/provisioning_15.jpg' />" />	        		
	        		
	        		<p>3.	정보를 모두 입력한 후에 Install 버튼을 클릭한다. 입력이 누락된 항목이 존재할 경우 입력확인 경고창이 표시된다.</p>
	        		<img src="<c:url value='/images/manual/provisioning_16.jpg' />" />	        		
	        		<p>-	입력이 누락된 항목이 존재할 경우 입력확인 경고창 표시</p>
	        		<img src="<c:url value='/images/manual/provisioning_17.jpg' />" />	        		
	        		
	        		<p>4.	모든 정보가 정상적으로 입력된 경우 프로비저닝이 진행되며 진행중임을 알리는 레이어 팝업이 노출되고 정상적으로 완료되면 결과 확인 화면이 노출된다.</p>
	        		<br/>
	        		<p>-	진행중임을 알리는 레이어 팝업</p>
	        		<img src="<c:url value='/images/manual/provisioning_18.jpg' />" />	        		
	        		<p>-	결과 확인 화면</p>
	        		<img src="<c:url value='/images/manual/provisioning_19.jpg' />" />	        		
	        		
	        		<p>5.	결과 확인 화면에서는 입력한 Tomcat 인스턴스 정보 및 결과 정보를 확인할 수 있다.</p>
	        	</div>
	        </div>
	        
			<div class="textbox">
				<div class="textbox_tit">4.	System</div>
	        	<div class="textbox_sub2" >
	        		<p>System 메뉴는  본 Athena Chameleon 시스템을 좀 더 편리하게 사용할 수 있도록 관리하는 메뉴로 코드관리 및 사용자 편이를 위한 사용자 메뉴얼을 확인 할 수 있다.</p>
	        		<br/>
	        		
	        		<p class="subtit">4.1	사용자 매뉴얼</p>
	        		<p>해당화면은 Athena Chameleon 시스템의 사용자들을 위한 매뉴얼 화면이다.</p>
	        		<br/>
	        		
	        		<p>1.	화면 상단에 System 메뉴를 클릭한다.</p>
	        		<img src="<c:url value='/images/manual/system_1.jpg' />" />
	        		
	        		<p>2.	사용자 매뉴얼을 확인한다.</p>
	        		<img src="<c:url value='/images/manual/system_2.jpg' />" />
	        		
	        		<p class="subtit">4.2	코드 관리</p>
	        		<p>Athena Chameleon 시스템을 사용하기 위한 코드정보를 관리하는 화면이다. 해당 화면에서는 filtering.properties에 정의된 항목이 노출되는 화면으로 로그인 아이디, 패스워드 및 각종 설정등을 변경 할 수 있다.</p>
	        		<br/>
	        		
	        		<p>1.	화면 상단에 System 메뉴를 클릭한다.</p>
	        		<img src="<c:url value='/images/manual/system_3.jpg' />" />
	        		
	        		<p>2.	상단 메뉴 밑에 코드 관리 탭을 클릭한다.</p>
	        		<img src="<c:url value='/images/manual/system_4.jpg' />" />
	        		
	        		<p>3.	변경할 코드정보를 수정한 후 Save 버튼을 클릭한다.</p>
	        		<img src="<c:url value='/images/manual/system_5.jpg' />" />
	        		
	        		<p>4.	정상적으로 변경되었을 경우 저장확인 창이 노출되는 것을 확인한다.</p>
	        		<img src="<c:url value='/images/manual/system_6.jpg' />" />
	        		
	        	</div>
	        </div> 
	        
	    </div>      
    </div>
