<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!-- 공략 게시판 메인화면 시작 -->
<div class="page-main-style">
<h2>공략 게시판</h2>
<p class="align-right">
	<input type="button" value="글쓰기" onclick="location.href='write.do'">
</p>

<!-- 
???????????? ㅠㅠㅠ?????????
<div>
	<ul>
		<li>
			<a href="list.do?keyword=람머스><img src="${pageContext.request.contextPath}/resources/images/${manualtoolVO.man_champion}.png" width="25" height="25"></a>
		</li>
	</ul>
</div>
 -->
 

<c:if test="${count == 0}">
	게시물이 없습니다.
</c:if>
  
<c:if test="${count > 0}">
	<table style="text-align:center;">
		<tr>
			<!-- <th>번호</th> -->
			<th width="10%">챔피언</th>
			<th width="10%">시즌</th>
			<th width="35%">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">갱신일</th>
			<th width="10%">조회수</th>
			<th width="10%">추천수</th>
		</tr>
		<c:forEach var="manualtoolVO" items="${manualtoolList}">
		<tr>
			<td><img src="${pageContext.request.contextPath}/resources/images/${manualtoolVO.man_champion}.png" width="25" height="25"></td> 
			<td>${manualtoolVO.man_season}</td>
			<td align="left"><a href="detail.do?man_num=${manualtoolVO.man_num}">${manualtoolVO.man_title}</a> <span style="color:orange;font-size:13px;">[${manualtoolVO.man_comment}]</span></td>
			<td>${manualtoolVO.mem_nick}</td>
			<td>${manualtoolVO.man_update}</td>
			<td>${manualtoolVO.man_hit}</td>
			<td>${manualtoolVO.man_fav}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
</c:if>
</div>
<!-- 공략 게시판 메인화면 끝 -->