<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!-- 포지션게시판 : 게시물 디테일 댓글 시작 -->
<div>
	<p>
		<span>댓글</span>
		${positionVO.comment} 개
	</p>
	<input type="button" value="인기순" id="btn_popular"> <!-- default 정렬-->
	<input type="button" value="최신순" id="btn_recent">
	<span class="align-right">
		<input type="button" value="댓글쓰기" id="btn_writeComment">
	</span>
</div>

<c:forEach var="postionCommentVO" items="${commentList}">
	<nav>
		<input type="button" id="btn_favUp">
		<div>
			${postionCommentVO.fav}
		</div>
		<input type="button" id="btn_favDown">
	</nav>
	<div>
		<!-- 미구현 : ${작성자 아이디} -->
		${postionCommentVO.date}
	</div>
	<div>
		${postionCommentVO.content}
	</div>
	<c:if test="${mem_num == postionCommentVO.mem_num}">
		<div>
			<input type="button" value="수정" id="btn_modifyComment">
			<input type="button" value="삭제" id="btn_deleteComment">
		</div>
	</c:if>
</c:forEach>
<!-- 포지션게시판 : 게시물 디테일 댓글 끝 -->