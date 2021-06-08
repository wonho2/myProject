<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mymy.css">    
<!-- 중앙 컨텐츠 시작 -->
<div class="page-love">
	<form:form id="border-love" action="login.do" commandName="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<fieldset>
		<legend><a href="${pageContext.request.contextPath}/main/main.do"><img class="omm" src="${pageContext.request.contextPath}/resources/template_res/assets/img/gg15-Logo.png" alt="..." /></a></legend><br>
		<ul class="text-line">
		<br>
			 <li>
<!-- 			<label for="id">아이디</label>
 -->			<form:input placeholder="아이디" path="mem_id"/>
				<form:errors path="mem_id" cssClass="error-color"/>
			</li>
			<br>
			<li>
<!-- 			<label for="passwd">비밀번호</label>
 -->			<form:password placeholder="비밀번호" path="mem_pw"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
			</li> 
		</ul>
		<br>
		<div>
			15gg가 처음이신가요? <span><a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></span><br>
			<form:button class="button-love">로그인</form:button>
			<br>
			<br>
			<%-- <input type="button" value="홈으로"
			onclick="location.href='${pageContext.request.contextPath}/main/main.do'"> --%>
		</div>
		</fieldset>
	</form:form>
	<%-- <div class="align-center">
		<form:input path="mem_id" placeholder="아이디" class="text-line"/>
		<form:errors path="mem_id" cssClass="error-color"/>
		<form:input path="mem_pw" placeholder="비밀번호" class="text-line"/>
		<form:errors path="mem_pw" cssClass="error-color"/>
		
	</div> --%>
	
</div>
<!-- 중앙 컨텐츠 끝 -->