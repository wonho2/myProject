<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

<div class="page-main-style">
	<h2>회원 가입</h2>
	<form:form action="registerUser.do" id="register_form" commandName="memberVO">
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="mem_id"/>
				<input type="button" id="confirmId" value="ID중복체크">
				<span id="message_id"></span>
				<form:errors path="mem_id" cssClass="error-color"/>
			</li>
			<li>
				<label for="name">이름</label>
				<form:input path="mem_name"/>
				<form:errors path="mem_name" cssClass="error-color"/>
			</li>
			<li>
				<label for="nick">닉네임</label>
				<form:input path="mem_nick"/>
				<form:errors path="mem_nick" cssClass="error-color"/>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
			</li>
			<li>
				<label for="phone">전화번호</label>
				<form:input path="mem_phone"/>
				<form:errors path="mem_phone" cssClass="error-color"/>
			</li>
			<li>
				<label for="email">이메일</label>
				<form:input path="mem_email"/>
				<form:errors path="mem_email" cssClass="error-color"/>
			</li>
		</ul> 
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="홈으로"
			 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>                                   
	</form:form>
</div>


