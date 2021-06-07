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
		
 		<c:if test="${user_auth == 3}">
		<input type="button" value="신고 글 보기" onclick="location.href='reportList.do'">
			<script type="text/javascript">
			
			</script>
		</c:if> 

	</div>
	
	 		<form action="list.do" id="choice_cate" method="get" style="border:none;margin-left:-28px;">
			<select name="boa_cate">
				<optgroup label="카테고리" >
					<option value="freeTalk" >자유토론</option>
					<option value="gaming_machine">게이밍 기기</option>
					<option value="game_talk">게임 이야기</option>
					<option value="Discode">디스코드 홍보</option>
					<option value="Tier">티어별 게시판</option>
					<option value="Champion">챔피언별 게시판</option>
					<option value="Humor">유머 게시판</option>
					<option value="img/video">사진/비디오</option>
					<option value="art">팬아트</option>
				</optgroup>
			</select>
	 	<input type="submit" value="조회">
	 	<input type="button" value="게시판 목록" onclick="location.href='list.do'">
		</form> 
	<table>	
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>첨부파일</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
				
		<!-- 목록별 보기 -->
		<c:forEach var="board" items="${list}">
		<tr>
			<td>${board.boa_num}</td>
			<td>
			<c:if test="${board.boa_cate == 'freeTalk'}">자유토론</c:if>
			<c:if test="${board.boa_cate == 'gaming_machine'}">게이밍 기기</c:if>
			<c:if test="${board.boa_cate == 'game_talk'}">게임 이야기</c:if>
			<c:if test="${board.boa_cate == 'Discode'}">디스코드 홍보</c:if>
			<c:if test="${board.boa_cate == 'Tier'}">티어별 게시판</c:if>
			<c:if test="${board.boa_cate == 'Champion'}">챔피언별 게시판</c:if>
			<c:if test="${board.boa_cate == 'Humor'}">유머 게시판</c:if>
			<c:if test="${board.boa_cate == 'img/video'}">사진/비디오</c:if>
			<c:if test="${board.boa_cate == 'art'}">팬아트</c:if>
			</td>
			 
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
