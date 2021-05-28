<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 포지션게시판 : 게시물 디테일 댓글 시작 -->
<!-- 자바스크립트 include -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/position_comment.js"></script>

<!-- 헤더 -->
<p>
	<!-- 미구현 : 정렬 -->
	<b>댓글 수 </b> <span id="commentCount"></span>
	<input type="button" value="인기순"> <!-- default 정렬-->
	<input type="button" value="최신순">
</p>

<!-- 댓글 쓰기 -->
<div id="wrapper_writeComment">
	<form id="form_writeComment">
		<input type="hidden" name="pos_num" value="${positionVO.pos_num}" id="pos_num">
		<input type="hidden" name="mem_num" value="${user_num}" id="mem_num">
		<textarea name="poc_content" id="poc_content" style="resize:none; width:400px; height:100px"></textarea>
		<input type="submit" value="댓글등록">
	</form>

</div>

<!-- 댓글 목록 -->
<p id="commentList"></p>

<!-- 포지션게시판 : 게시물 디테일 댓글 끝 -->