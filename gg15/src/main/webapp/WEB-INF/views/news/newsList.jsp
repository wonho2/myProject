<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main-style">
	<h2>뉴스</h2>
	<div class="align-right">
		<!-- 테스트용 -->
		<%-- <c:if test="${!empty user_num}"> --%>
		<input type="button" value="작성" onclick="location.href='newsWrite.do'">
		<%-- </c:if> --%>
	</div>
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>최근수정일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="news" items="${list}">
		<tr>
			<td>${news.new_num}</td>
			<td><a href="newsDetail.do?new_num=${news.new_num}">${news.title}</a></td>
			<td>${news.id}</td>
			<td>${news.hit}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>
<!-- 중앙 컨텐츠 끝 -->
