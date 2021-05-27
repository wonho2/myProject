<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 자유게시판 errorPage 시작 -->
<div>

	<!-- 회원 전용 게시글 -->
	<c:if test="${empty user_num}">
		<h4> NOSHOW </h4>
		<a>회원 전용 게시글 입니다.</a>
	<input type="button" id="login" value="로그인" onclick="location.href='/member/memberLogin.do'">
	<input type="button" id="list" value="목록" onclick="location.href='list.do'">	
	</c:if>
	
	<!-- (신고) 차단된 게시글 -->
	<%-- 
	<c:if test="${boa_report=2}">
		<h4> BLOCK </h4>
		<a>차단 조치된 게시글 입니다.</a>
	<input type="button" id="list" value="목록" onclick="location.href='list.do'">			
	</c:if>
    --%>
</div>
<!-- 자유게시판 errorPage 끝 -->