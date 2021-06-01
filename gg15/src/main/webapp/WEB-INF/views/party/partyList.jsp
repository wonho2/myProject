<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 파티게시판 : boardList 시작 --> 
<p class="align-right">
	<input type="button" value="글쓰기" onclick="location.href='write.do'">
</p>

<!-- 중앙 켄텐츠 시작 -->
<div class="page-main-style">
	<h2>게시판 목록</h2>
	<div class="align-right">
		<c:if test="${!empty par_num}">
		<input type="button" value="글쓰기" onclick="location.href='write.do'">
		</c:if>
	</div>
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="partyVO" items="${partyList}">
		<tr>
			<td>${partyVO.par_num}</td>
			<td>
			<c:if test="${partyVO.par_type == 1}">랭크 팀원 모집</c:if>
			<c:if test="${partyVO.par_type == 2}">칼바람 팀원 모집</c:if>
			<c:if test="${partyVO.par_type == 3}">(격전)팀원 모집</c:if>
			</td>
			<td><a href="detail.do?par_num=${partyVO.par_num}">${partyVO.par_title}</a></td>
			<td>${partyVO.mem_nick}</td>
			<td>${partyVO.par_date}</td>
			<td>${partyVO.par_hit}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div> 
<!-- 중앙 컨텐츠 끝 -->
<!-- 파티게시판  목록 끝 --> 