<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 자유게시판 boardMain 시작 -->
<div class="page-main-style">
	<h2>자유게시판 목록</h2>
	<div class="align-right">
		 <c:if test="${user_auth == 3}">
		<input type="button" value="일반 글 보기" onclick="location.href='boardlist.do'">
		<script type="text/javascript">
			
		</script>
		</c:if>
	</div>
	
	<table>	
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
				
		<tr>
			<td><a href="reportPage.do?boa_num=${board_report.bop_num}">${board.boa_title}</a></td>
			<td>${board_report.mem_nick}</td>
			<td>${board_report.bop_date}</td>
		</tr>
	</table>
	
	<!--  등록된 차단 글이 없을 경우 -->
	<c:if test="${count == 0}"> 
		<section class="align-center">등록된 신고가 없습니다.</section>
	</c:if>		
	<c:if test="${count ne 0}"> 
	<div class="align-center">${pagingHtml}</div>
	</c:if>
	
</div>			
	