<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 자유게시판 boardMain 시작 -->
<script type="text/javascript">
	function cate(){
		alert('test');
	} 
</script>
<div class="page-main-style">
	<h2>자유게시판 목록</h2>
	<div class="align-right">
		<c:if test="${!empty user_num}">
		<input type="button" value="글쓰기" onclick="location.href='boardWrite.do'">
		</c:if>
		
		<c:if test="${user_auth == 3}">
		<input type="button" value="관리자 PAGE" onclick="location.href='reportPage.do'">
		</c:if>
	</div>
	 
			<select name="boa_cate">
				<optgroup label="카테고리" >
					<option value="All" >전체</option>
					<option value="gaming_machine">게이밍 기기</option>
					<option value="game_talk">게임 이야기</option>
					<option value="Discode">디스코드 홍보</option>
					<option value="Tier">티어별 게시판</option>
					<option value="Champion">챔피언별 게시판</option>
					<option value="Humor">유머 게시판</option>
					<option value="img_video">사진/비디오</option>
					<option value="art">팬아트</option>
					
					<c:if test="board.mem_auth == 3">
					<option value="report">신고내용 보기</option>
					</c:if >
					
				</optgroup>
			</select>
	 	<input type="button" value="조회" onclick='cate()'>
		 
	<table>	
		<tr>
			<th>번호</th>
			<th>첨부파일</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
				
		
		<c:forEach var="board" items="${list}">
		<tr>
			<td>${board.boa_num}</td>
			 
			<!-- 첨부파일 첨부 시 아이콘 표시 if문 시작-->
			<td>
			<c:choose>
				<c:when test="${!empty board.boa_filename}">
					<img src="${pageContext.request.contextPath}/resources/images/icon.png"
				 width="50" >
				</c:when>
				<c:when test="${board.boa_cate == 'img/video'}">
					<img src="${pageContext.request.contextPath}/resources/images/icon2.png"
				 width="50" >
				</c:when>
				<c:when test="${empty board.boa_filename}">
					X
				</c:when>
			</c:choose>
			</td>
			<!-- 첨부파일 첨부 시 아이콘 표시 if문 끝-->
			
			<td><a href="boardDetail.do?boa_num=${board.boa_num}">${board.boa_title}</a></td>
			<td>${board.mem_nick}</td>
			<td>${board.boa_date}</td>
			<td>${board.boa_hit}</td>
		</tr>
		</c:forEach> 
	</table>
	<c:if test="${count == 0}"> 
		<section class="align-center">등록된 게시물이 없습니다.</section>
		</c:if>		
	<c:if test="${count ne 0}"> 
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>
<!-- 자유게시판 boardMain 끝 -->
