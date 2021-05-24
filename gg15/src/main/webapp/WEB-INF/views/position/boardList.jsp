<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 포지션게시판 : boardList 시작 -->
<p class="align-right">
	<input type="button" value="글쓰기" onclick="location.href='write.do'">
</p>

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
				<td>${positionVO.pos_num}</td>
				<td>${positionVO.pos_type}</td>
				<td><a href="detail.do?num=${positionVO.pos_num}">${positionVO.pos_title}</a></td>
				<td>${positionVO.mem_nick}</td>
				<td>${positionVO.pos_date}</td>
				<td>${positionVO.pos_view}</td>
				<td>${positionVO.pos_fav}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		${pagingHtml}
	</div>
	
</c:if>
<!-- 포지션게시판 : boardList 끝 -->