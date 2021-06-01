<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!-- 공략 게시판 메인화면 시작 -->
<div class="page-main-style">
<h2>공략 게시판</h2>
<p class="align-right">
	<input type="button" value="글쓰기" onclick="location.href='write.do'">
</p>

<c:if test="${count == 0}">
	게시물이 없습니다.
</c:if>
  
<c:if test="${count > 0}">
	<table style="text-align:center;">
		<tr>
			<!-- <th>번호</th> -->
			<th width="40">챔피언</th>
			<th>시즌</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>갱신일</th>
			<th>조회수</th>
			<!-- <th>추천수</th> -->
		</tr>
		<c:forEach var="manualtoolVO" items="${manualtoolList}">
		<tr>
			<!-- <td>${manualtoolVO.man_num}</td> -->
			<!-- 이건 임시고 원래 챔피언 이름 말고 아이콘(이미지)을 띄워야 되는데 어케,,하죠 -->
			<td><img src="${pageContext.request.contextPath}/resources/images/${manualtoolVO.man_champion}.png" width="25" height="25"></td> 
			<td>${manualtoolVO.man_season}</td>
			<td align="left"><a href="detail.do?man_num=${manualtoolVO.man_num}">${manualtoolVO.man_title}</a></td>
			<!-- 제목 옆에 작게 댓글 수 띄우기 어케 하지.. 암튼 추가 -->
			<td>${manualtoolVO.mem_nick}</td>
			<td>${manualtoolVO.man_update}</td>
			<td>${manualtoolVO.man_hit}</td>
			<!-- <td>${manualtoolVO.man_fav}</td> -->
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
</c:if>
</div>
<!-- 공략 게시판 메인화면 끝 -->