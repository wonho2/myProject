<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 자유게시판 reportPage 시작 -->

<div class="page-main-style"> 
	<div>			
			<c:if test="${report.boa_cate == 'freeTalk'}">자유토론</c:if>
			<c:if test="${report.boa_cate == 'gaming_machine'}">게이밍 기기</c:if>
			<c:if test="${report.boa_cate == 'game_talk'}">게임 이야기</c:if>
			<c:if test="${report.boa_cate == 'Discode'}">디스코드 홍보</c:if>
			<c:if test="${report.boa_cate == 'Tier'}">티어별 게시판</c:if>
			<c:if test="${report.boa_cate == 'Champion'}">챔피언별 게시판</c:if>
			<c:if test="${report.boa_cate == 'Humor'}">유머 게시판</c:if>
			<c:if test="${report.boa_cate == 'img/video'}">사진/비디오</c:if>
			<c:if test="${report.boa_cate == 'art'}">팬아트</c:if>
	</div>
	
	<h1> 게시물 [" ${report.boa_title} "] 신고 </h1>
	<div><a href="/member/memberDetail.do?board_num=${board.boa_num}">${board.mem_nick}</a> </div>
	<div>신고자 : ${report.mem_nick}</div>
	<div>신고일 ${report.bop_date} </div>
	
	<div>
		<h4>신고 내용</h4>
		<p>	${board_report.bop_content} </p>
	</div>
	
	<h2> 본문 게시글 </h2>
	<div style="border:1px solid black;">
		<c:if test="${fn:endsWith(board.boa_filename,'.jpg') || 
		              fn:endsWith(board.boa_filename,'.JPG') ||
		              fn:endsWith(board.boa_filename,'.gif') ||
		              fn:endsWith(board.boa_filename,'.GIF') ||
		              fn:endsWith(board.boa_filename,'.png') ||
		              fn:endsWith(board.boa_filename,'.PNG')}"> 
		<div class="align-center">
			<img src="imageView.do?board_num=${board.boa_num}"
			                           style="max-width:500px">
		</div>
		</c:if>
		<p>	${board.boa_content}</p>
	</div>
	
	<!-- 차단 게시물 설정 버튼 -->
	<div>  
		<input type="button" id="output_status" value="게시물 차단 하기"/> 
	</div>
</div>
<!-- 자유게시판 reportPage 끝 -->