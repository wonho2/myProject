<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 포지션게시판 : 게시물 디테일 시작 -->
<!-- 수정, 삭제 버튼 -->
<c:if test="${user_num == positionVO.mem_num}">
	<div>
		<input type="button" value="수정" onclick="location.href='modify.do?num=${num}'">
		<input type="button" value="삭제" onclick="location.href='delete.do?num=${num}'" id="btn_delete">
	</div>
</c:if>

<!-- 게시물 상세 -->
<div>
	<h1>${positionVO.pos_title}</h1>
	<section>
		[${positionVO.pos_type}] | ${positionVO.pos_date} | ${positionVO.mem_nick}
	</section>
	<section>
		조회수 ${positionVO.pos_view} | 추천수 ${positionVO.pos_fav} | 댓글수 ${positionVO.pos_comment}
	</section>
	
	<hr size="1" noshade="noshade" width="100%">
	<div>
		<!-- 미구현 : 업로드 파일 보여주기 -->
		${positionVO.pos_content}
	</div>
	 
	<div>
		<input type="button" value="추천" id="btn_fav">
	</div>
</div>
<!-- 포지션게시판 : 게시물 디테일 끝 -->