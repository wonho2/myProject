<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!-- 공략 게시판 메인화면 시작 -->
<div class="page-main-style">
	<h2>공략 게시판</h2>
	<div class="align-right">
		<input type="button" value="글쓰기" onclick="location.href='write.do'">
	</div>
	
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th>챔피언</th>
			<th>시즌</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>갱신일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		<c:forEach var="manualtoolVO" items="${boardList}">
		<tr>
			<td>$manualtoolVO.man_num}</td>
			<!-- <td>$manualtoolVO.man_champion}</td> 
				챔피언 아이콘(이미지)을 띄워야 되는데 어케,,하죠
			-->
			<td>$manualtoolVO.man_season}</td>
			<td><a href="manualtoolDetail.do?num=${manualtoolVO.man_num}">${manualtoolVO.man_title}</a></td>
			<!-- 제목 옆에 작게 댓글 수 띄우기 어케 하지.. 암튼 추가 -->
			<td>$manualtoolVO.mem_nick}</td>
			<td>$manualtoolVO.man_update}</td>
			<td>$manualtoolVO.man_hit}</td>
			<td>$manualtoolVO.man_fav}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>
<!-- 공략 게시판 메인화면 끝 -->