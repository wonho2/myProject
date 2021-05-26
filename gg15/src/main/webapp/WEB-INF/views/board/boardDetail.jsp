<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 자유게시판 boardList 시작 -->
<!-- 게시물 상세 -->
<div>
	<section>${boardVO.boa_cate}</section>
		<h1>${boardVO.boa_title}</h1>
	<section>
	<!-- 사용자 닉네임처리 해야함 -->
	<a href="/member/memberDetail.do?board_num=${board.boa_num}">${boardVO.mem_num}</a> 
	
	</section>
	<section>
		${boardVO.boa_date} 
		조회수 ${boardVO.boa_view}		
		추천수 ${boardVO.bof_num} 댓글수 ${boardVO.bor_num}
	</section>
	<!-- 이미지 오찌하지... -->
	<section>
		${boardVO.boa_content}
	</section>
	
	<input type="button" value="신고" id="report">
	
<!-- 수정, 삭제 버튼 -->
<c:if test="${user_num == BoardVO.mem_num}">
	
		<input type="button" value="수정" onclick="location.href='boardModify.do?boa_num=${boardVO.boa_num}'">
		<input type="button" value="삭제" onclick="location.href='boardDelete.do?boa_num=${boardVO.boa_num}'" id="delete">
	
</c:if>
	 
	
		<input type="button" value="좋아요" id="like">
	
</div>
<!--  자유게시판 boardList 끝 -->