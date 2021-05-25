<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 자유게시판 boardList 시작 -->
<!-- 게시물 상세 -->
<div>
	<section>
		[${boardVO.boa_cate}] 
	</section>
		<h1>${boardVO.boa_title}</h1>
	<section>
		<a href="/member/memberDetail.do?board_num=${board.board_num}">${boardVO.mem_nick}</a>
	</section>
	<section>
		${boardVO.boa_date} 
		조회수 ${boardVO.boa_view}		
		추천수 ${boardVO.bof_num} 댓글수 ${boardVO.bor_num}
	</section>
	<!-- 이미지 넣기 -->
	<section>
		${boardVO.boa_content}
	</section>
	<hr size="1" noshade="noshade" width="100%">
	
	<input type="button" value="신고" id="report">
	
<!-- 수정, 삭제 버튼 -->
<c:if test="${user_num == BoardVO.mem_num}">
	<div>
		<input type="button" value="수정" onclick="location.href='boardModify.do?num=${boardVO.boas_num}'">
		<input type="button" value="삭제" onclick="location.href='boardDelete.do?num=${boardVO.boa_num}'" id="btn_delete">
	</div>  
</c:if>
	 
	<div>
		<input type="button" value="좋아요" id="like">
	</div>
</div>
<!--  자유게시판 boardList 끝 -->