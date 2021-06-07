<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!-- 공략 게시판 메인화면 시작 -->
<div class="page-main-style">
<hr size="1" noshade="noshade" width="100%">
<h2 onclick="location.href='list.do'">내 챔피언공략 글 목록</h2>
<hr size="1" noshade="noshade" width="100%">
<%-- <h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/resources/images/rift.png" width="25" height="25">&nbsp;&nbsp;챔피언 선택</h6>

<hr size="1" noshade="noshade" width="100%">
<p class="align-right">
	<!-- <input type="button" value="글쓰기" onclick="location.href='write.do'"> -->
	<a href="write.do"><img src="${pageContext.request.contextPath}/resources/images/write.png" width="50" height="50"></a>
</p> --%>

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
		<c:forEach var="manualtoolVO" items="${myManualList}">
		<tr>
			<td><img src="${pageContext.request.contextPath}/resources/images/${manualtoolVO.man_champion}.png" width="25" height="25"></td> 
			<td>${manualtoolVO.man_season}</td>
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<a href="detail.do?man_num=${manualtoolVO.man_num}">${manualtoolVO.man_title}</a> <span style="color:orange;font-size:13px;">[${manualtoolVO.man_comment}]</span>
			<c:if test="${!empty manualtoolVO.man_filename}">
				<img src="${pageContext.request.contextPath}/resources/images/icon.png" width="50">
			</c:if>
			</td>
			<td>${manualtoolVO.mem_nick}</td>
			<td>${manualtoolVO.man_update}</td>
			<td>${manualtoolVO.man_hit}</td>
			<td>${manualtoolVO.man_fav}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
</c:if>

<!-- 검색창 -->
	<p>
	    <form action="list.do" method="get" style="margin:0;border:none;">
		<select name="keyfield">
			<option value="man_title">제목</option>
			<option value="man_content">내용</option>
			<option value="mem_nick">작성자</option>
		</select>
		<input type="text" id="keyword" name="keyword">
		<input type="submit" value="검색">
		</form>
	</p>
	<hr size="1" noshade="noshade" width="100%">
</div>
<!-- 공략 게시판 메인화면 끝 -->