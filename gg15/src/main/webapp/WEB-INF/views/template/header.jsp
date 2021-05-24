<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<h2 class="align-center">GG15</h2>
<div class="align-right">
	<c:if test="${!empty user_num}">
		[<span class="user_name">${user_id}</span>]
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	</c:if>
	<c:if test="${empty user_num}">
		<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
</div>

<!-- 전예원 : 뷰 테스트 해보느라 메뉴 추가했습니다 -->
<div>
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/position/list.do">포지션게시판</a>
		</li>
	</ul>
</div>
<!-- 상단 끝 -->



