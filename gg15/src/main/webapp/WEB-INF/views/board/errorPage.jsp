<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 자유게시판 errorPage 시작 -->
<div> 

	<!-- 회원 전용 게시글 -->
	<c:if test="${empty user_num}">
	<section class="align-center">
		<h4> NOSHOW </h4><br>
		<a>회원 전용 게시글 입니다.</a><br>
	<input type="button" id="login" value="로그인" onclick="location.href='/member/memberLogin.do'">
	<input type="button" id="list" value="목록" onclick="location.href='list.do'">	
	</section>
	</c:if> 

	<!-- (신고) 차단된 게시글 -->
	<c:if test="${boa_status eq 2}">
	<section class="align-center">
		<h4> BLOCK </h4><br>
		<a>차단 조치된 게시글 입니다.<br>
		관리자에게 문의 하세요.</a><br><br>
	<input type="button" id="list" value="목록" onclick="location.href='list.do'">			
	</section>
	</c:if>

	<!-- 관리자 권한 게시글 -->
<!-- 	<c:if test="${mem_auth != 3}">
	<section class="align-center">
		<h4> BLOCK </h4><br>
		<a>관리자 전용 게시글 입니다.</a><br><br>
	<input type="button" id="list" value="목록" onclick="location.href='list.do'">			
	</section>
	</c:if>
 -->	
</div>
<!-- 자유게시판 errorPage 끝 -->