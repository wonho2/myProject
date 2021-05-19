<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 포지션게시판 : boardList 시작 -->
<input type="button" value="글쓰기" onclick="location.href='/position/write.do'">

<c:if test="${count == 0}">
	게시물이 없습니다.
</c:if>

<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th>포지션</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.num}</td>
				<td>${board.type}</td>
				<td><a href="/position/detail.do?num=${board.num}">${board.title}</a></td>
				<td>${board.writer}</td>
				<td>${board.date}</td>
				<td>${board.view}</td>
				<td>${board.fav}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
<!-- 포지션게시판 : boardList 끝 -->