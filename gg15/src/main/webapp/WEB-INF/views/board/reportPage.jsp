<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="${pageContext.request.contextPath}/resources/js/videoAdapter.js"></script>
<!-- 자유게시판 reportPage 시작 -->
<div class="page-main-style"> 
	<div>${board.boa_cate}</div>
	<h1>${board.boa_title}</h1>
	<div>작성자 : ${board.mem_nick}</div>
	<div>신고자 : ${board_report.mem_nick}</div>
	<div>신고일 ${boa_report.bop_date} </div>
	
	<div>
		<p>	${board_report.bop_content} </p>
	</div>
	
	본문 게시글
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