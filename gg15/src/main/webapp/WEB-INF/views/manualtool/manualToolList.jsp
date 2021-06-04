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
			<th width="10%" style="background-color:#FFC341;"><a>챔피언</a></th>
			<th width="10%" style="background-color:#FFC341;"><a>시즌</a></th>
			<th width="35%" style="background-color:#FFC341;"><a>제목</a></th>
			<th width="15%" style="background-color:#FFC341;"><a>작성자</a></th>
			<th width="10%" style="background-color:#FFC341;"><a>갱신일</a></th>
			<th width="10%" style="background-color:#FFC341;"><a>조회수</a></th>
			<th width="10%" style="background-color:#FFC341;"><a>추천수</a></th>
		</tr>
		<c:forEach var="manualtoolVO" items="${manualtoolList}">
		<tr>
			<td><img src="${pageContext.request.contextPath}/resources/images/${manualtoolVO.man_champion}.png" width="25" height="25"></td> 
			<td>${manualtoolVO.man_season}</td>
			<td align="left">&nbsp;&nbsp;<a href="detail.do?man_num=${manualtoolVO.man_num}">${manualtoolVO.man_title}</a> <span style="color:orange;font-size:13px;">[${manualtoolVO.man_comment}]</span></td>
			<td>${manualtoolVO.mem_nick}</td>
			<td>${manualtoolVO.man_update}</td>
			<td>${manualtoolVO.man_hit}</td>
			<td>${manualtoolVO.man_fav}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
</c:if>

<!-- 일단 넣어둔 검색창 ... (가짜) -->
	<p class="align-right">
		<select name="man_searchOption">
			<option value="man_title">제목</option>
			<option value="man_content">내용</option>
			<option value="mem_nick">작성자</option>
		</select>
		<input type="text" id="man_search">
		<input type="button" value="검색">
	</p>
</div>
<!-- 공략 게시판 메인화면 끝 -->