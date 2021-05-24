<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main-style">
	<h2>회원 로그인</h2>
	<form:form action="login.do" commandName="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="mem_id"/>
				<form:errors path="mem_id" cssClass="error-color"/>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<form:button>로그인</form:button>
			<input type="button" value="홈으로"
onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->