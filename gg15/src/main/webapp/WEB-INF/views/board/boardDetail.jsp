<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 자유게시판 boardList 시작 -->
<!-- 수정, 삭제 버튼 -->
<c:if test="${user_num == BoardVO.mem_num}">
	<div>
		<input type="button" value="수정" onclick="location.href='boardModify.do?num=${boardVO.boas_num}'">
		<input type="button" value="삭제" onclick="location.href='boardDelete.do?num=${boardVO.boa_num}'" id="btn_delete">
	</div>  
</c:if>

<!-- 게시물 상세 -->
<div>
	<h1>${boardVO.boa_title}</h1>
	<section>
		[${boardVO._type}] | ${boardVO.pos_date} | ${boardVO.mem_nick}
	</section>
	<section>
		조회수 ${boardVO.pos_view} | 추천수 ${boardVO.bof_fav} | 댓글수 ${boardVO.boa_comment}
	</section>
	
	<hr size="1" noshade="noshade" width="100%">
	<div>
		<!-- 미구현 : 업로드 파일 보여주기 -->
		${boardVO.boa_content}
	</div>
	 
	<div>
		<input type="button" value="추천" id="btn_fav">
	</div>
</div>
<!--  자유게시판 boardList 끝 -->