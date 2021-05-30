<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 포지션게시판 : 게시물 디테일 댓글 시작 -->
<!-- 자바스크립트 include -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/position_comment.js"></script>

<!-- 헤더 -->
<p>
	<!-- 미구현 : 정렬 -->
	<b>댓글</b> <span id="output_commentCount"></span>
	<input type="button" value="인기순"> <!-- default 정렬-->
	<input type="button" value="최신순">
</p>

<!-- 댓글 쓰기 -->
<div class="align-right">
	<input type="button" value="댓글쓰기" id="btn_writeComment">
</div>
<p id="output_writeComment"></p>

<!-- 댓글 목록 -->
<p id="output_commentList"></p>

<!-- 포지션게시판 : 게시물 디테일 댓글 끝 -->