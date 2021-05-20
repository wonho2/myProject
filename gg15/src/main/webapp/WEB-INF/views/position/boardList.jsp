<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 포지션게시판 : boardList 시작 -->
<input type="button" value="글쓰기" onclick="location.href='write.do'">

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
		<c:forEach var="positionVO" items="${boardList}">
			<tr>
				<td>${positionVO.num}</td>
				<td>${positionVO.type}</td>
				<td><a href="detail.do?num=${positionVO.num}">${positionVO.title}</a></td>
				<td><!-- 미구현 : ${작성자 아이디} --></td>
				<td>${positionVO.date}</td>
				<td>${positionVO.view}</td>
				<td>${positionVO.fav}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		${pagingHtml}
	</div>
</c:if>
<!-- 포지션게시판 : boardList 끝 -->