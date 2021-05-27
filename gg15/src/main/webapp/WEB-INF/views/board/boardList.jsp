<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 자유게시판 boardMain 시작 -->
<div class="page-main-style">
	<h2>자유게시판 목록</h2>
	<div class="align-right">
		<c:if test="${!empty user_num}">
		<input type="button" value="글쓰기" onclick="location.href='boardWrite.do'">
		</c:if>
	</div>
	
	<table>
		<tr>
			<select name="boa_cate">
				<optgroup label="카테고리">
					<option value="All">전체</option>
					<option value="gaming_machine">게이밍 기기</option>
					<option value="game_talk">게임 이야기</option>
					<option value="Discode">디스코드 홍보</option>
					<option value="Tier">티어별 게시판</option>
					<option value="Champion">챔피언 게시판</option>
					<option value="Humor">유머 게시판</option>
					<option value="img_video">사진/비디오</option>
					<option value="art">팬아트</option>
				</optgroup>
			</select>
		</tr>
				
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
			<c:if test="${!empty board.boa_filename}">
			<img src="${pageContext.request.contextPath}/resources/images/icon.png"
				 width="50" >
			</c:if>
			<c:if test="${empty board.boa_filename}">
			X
			</c:if>
			</td>
			<!-- 첨부파일 첨부 시 아이콘 표시 if문 끝-->
			
			<td><a href="boardDetail.do?boa_num=${board.boa_num}">${board.boa_title}</a></td>
			<td>${member.mem_id}</td>
			<td>${board.boa_date}</td>
			<td>${board.boa_hit}</td>
		</tr>
		</c:forEach>
		<c:if test="${count == 0}">
		<div >등록된 게시물이 없습니다.</div>
		</c:if>
	</table>
	<div class="align-center">${pagingHtml}</div>

</div>
<!-- 자유게시판 boardMain 끝 -->
