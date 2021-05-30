<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 포지션게시판 : boardList 시작 -->
<!-- 게시물 카테고리 -->
<div>
	<ul>
		<li>
			<a href="boardList.do">전체보기</a> <!-- default -->
		</li>
		<li>
			<a href="boardList_top.do">탑</a>
		</li>
		<li>
			<a href="boardList_jungle.do">정글</a>
		</li>
		<li>
			<a href="boardList_mid.do">미드</a>
		</li>
		<li>
			<a href="boardList_ad.do">원딜</a>
		</li>
		<li>
			<a href="boardList_support.do">서포터</a>
		</li>
	</ul>
</div>

<!-- 글쓰기 버튼 -->
<p class="align-right">
	<input type="button" value="글쓰기" onclick="location.href='write.do'">
</p>

<!-- 게시물 목록 -->
<c:if test="${boardCount == 0}">
	게시물이 없습니다.
</c:if>
<c:if test="${boardCount > 0}">
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
				<td><a href="detail.do?pos_num=${positionVO.pos_num}">${positionVO.pos_title}</a></td>
				<td>${positionVO.mem_nick}</td>
				<td>${positionVO.pos_date}</td>
				<td>${positionVO.pos_view}</td>
				<td>${positionVO.pos_fav}</td>
			</tr>
		</c:forEach>
	</table>
	
<!-- 페이징 처리 -->
	<div>
		${pagingHtml}
	</div>
	
<!-- 미구현 : 게시물 검색창 -->
	<p class="align-right">
		<select name="pos_searchOption">
			<option value="pos_title">제목</option>
			<option value="pos_content">내용</option>
			<option value="mem_nick">작성자</option>
		</select>
		<input type="text" id="pos_search">
		<input type="button" value="검색">
	</p>
	
</c:if>
<!-- 포지션게시판 : boardList 끝 -->