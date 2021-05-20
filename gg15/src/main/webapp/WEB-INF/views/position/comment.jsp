<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!-- 포지션게시판 : 게시물 디테일 댓글 시작 -->
<div>
	<p>댓글</p>
	<input type="button" value="인기순" id="btn_popular"> <!-- default 정-->
	<input type="button" value="최신순" id="btn_recent">
</div>

<c:forEach var="comment" items="${commentList}">
	<nav>
		<input type="button" id="btn_favUp">
		<!-- 미구현 : ${추천수} -->
		<input type="button" id="btn_favDown">
	</nav>
	<div>
		${comment.mem_id}
		${comment.date}
	</div>
	<div>
		${comment.content}
	</div>
	<c:if test="${mem_num == comment.mem_num}">
		<div>
			<input type="button" value="수정" id="btn_modifyComment">
			<input type="button" value="삭제" id="btn_deleteComment">
		</div>
	</c:if>
</c:forEach>
<!-- 포지션게시판 : 게시물 디테일 댓글 끝 -->