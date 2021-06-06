<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!-- 공략 게시판 메인화면 시작 -->
<div class="page-main-style">
<hr size="1" noshade="noshade" width="100%">
<h2 onclick="location.href='list.do'">LoL 챔피언 공략 게시판</h2>
<hr size="1" noshade="noshade" width="100%">
<h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/resources/images/rift.png" width="25" height="25">&nbsp;&nbsp;챔피언 선택</h6>
<div style="overflow:auto;height:260px;">
	<ul class="icon">
		<li class="item"><a href="list.do?keyword=가렌"><img src="${pageContext.request.contextPath}/resources/images/가렌.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=갈리오"><img src="${pageContext.request.contextPath}/resources/images/갈리오.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=갱플랭크"><img src="${pageContext.request.contextPath}/resources/images/갱플랭크.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=그라가스"><img src="${pageContext.request.contextPath}/resources/images/그라가스.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=그레이브즈"><img src="${pageContext.request.contextPath}/resources/images/그레이브즈.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=그웬"><img src="${pageContext.request.contextPath}/resources/images/그웬.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=나르"><img src="${pageContext.request.contextPath}/resources/images/나르.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=나미"><img src="${pageContext.request.contextPath}/resources/images/나미.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=나서스"><img src="${pageContext.request.contextPath}/resources/images/나서스.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=노틸러스"><img src="${pageContext.request.contextPath}/resources/images/노틸러스.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=녹턴"><img src="${pageContext.request.contextPath}/resources/images/녹턴.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=누누와 윌럼프"><img src="${pageContext.request.contextPath}/resources/images/누누와 윌럼프.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=니달리"><img src="${pageContext.request.contextPath}/resources/images/니달리.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=니코"><img src="${pageContext.request.contextPath}/resources/images/니코.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=다리우스"><img src="${pageContext.request.contextPath}/resources/images/다리우스.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=다이애나"><img src="${pageContext.request.contextPath}/resources/images/다이애나.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=드레이븐"><img src="${pageContext.request.contextPath}/resources/images/드레이븐.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=라이즈"><img src="${pageContext.request.contextPath}/resources/images/라이즈.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=라칸"><img src="${pageContext.request.contextPath}/resources/images/라칸.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=람머스"><img src="${pageContext.request.contextPath}/resources/images/람머스.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=럭스"><img src="${pageContext.request.contextPath}/resources/images/럭스.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=럼블"><img src="${pageContext.request.contextPath}/resources/images/럼블.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=레넥톤"><img src="${pageContext.request.contextPath}/resources/images/레넥톤.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=레오나"><img src="${pageContext.request.contextPath}/resources/images/레오나.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=렉사이"><img src="${pageContext.request.contextPath}/resources/images/렉사이.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=렐"><img src="${pageContext.request.contextPath}/resources/images/렐.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=렝가"><img src="${pageContext.request.contextPath}/resources/images/렝가.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=루시안"><img src="${pageContext.request.contextPath}/resources/images/루시안.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=룰루"><img src="${pageContext.request.contextPath}/resources/images/룰루.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=르블랑"><img src="${pageContext.request.contextPath}/resources/images/르블랑.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=리븐"><img src="${pageContext.request.contextPath}/resources/images/리븐.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=리산드라"><img src="${pageContext.request.contextPath}/resources/images/리산드라.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=리 신"><img src="${pageContext.request.contextPath}/resources/images/리 신.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=릴리아"><img src="${pageContext.request.contextPath}/resources/images/릴리아.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=마스터 이"><img src="${pageContext.request.contextPath}/resources/images/마스터 이.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=마오카이"><img src="${pageContext.request.contextPath}/resources/images/마오카이.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=말자하"><img src="${pageContext.request.contextPath}/resources/images/말자하.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=말파이트"><img src="${pageContext.request.contextPath}/resources/images/말파이트.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=모데카이저"><img src="${pageContext.request.contextPath}/resources/images/모데카이저.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=모르가나"><img src="${pageContext.request.contextPath}/resources/images/모르가나.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=문도 박사"><img src="${pageContext.request.contextPath}/resources/images/문도 박사.png" width="100" height="100"></a></li>
		<li class="item"><a href="list.do?keyword=미스 포츈"><img src="${pageContext.request.contextPath}/resources/images/미스 포츈.png" width="100" height="100"></a></li>
	</ul>
</div>
<p class="align-right">
	<!-- <input type="button" value="글쓰기" onclick="location.href='write.do'"> -->
	<a href="write.do"><img src="${pageContext.request.contextPath}/resources/images/write.png" width="50" height="50"></a>
</p>

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
			<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;<a href="detail.do?man_num=${manualtoolVO.man_num}">${manualtoolVO.man_title}</a> <span style="color:orange;font-size:13px;">[${manualtoolVO.man_comment}]</span>
			<!--
			첨부파일 있으면 제목 옆에 표시? 하지 말까 그냥
			<c:choose>
				<c:when test="${!empty manualtoolVO.man_filename}">
					<img src="${pageContext.request.contextPath}/resources/images/icon.png" width="50">
				</c:when>
			</c:choose>
			-->
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
	<hr size="1" noshade="noshade" width="100%">
</div>
<!-- 공략 게시판 메인화면 끝 -->