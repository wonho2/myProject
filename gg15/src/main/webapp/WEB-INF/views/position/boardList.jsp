<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/position.css">

<!-- 포지션게시판 : boardList 시작 -->
<!-- 게시물 카테고리 -->
<div id="pos_type">
	<div class="title">
		<span>포지션 선택</span>
	</div>
	<ul>
		<li>
			<a href="list.do">전체보기</a> <!-- default -->
		</li>
		<li>
			<a href="list_top.do">탑</a>
		</li>
		<li>
			<a href="list_jungle.do">정글</a>
		</li>
		<li>
			<a href="list_mid.do">미드</a>
		</li>
		<li>
			<a href="list_ad.do">원딜</a>
		</li>
		<li>
			<a href="list_support.do">서포터</a>
		</li>
	</ul>
</div>
	
<!-- 게시물 정렬 방법 -->
<div id="pos_sort">
	<ul>
		<c:if test = "${pos_type == '전체보기'}">
			<li>
				<img src="../resources/images/icon_recent.png">
				<a href="list.do">최신순</a> <!-- default -->
			</li>
			<li>
				<img src="../resources/images/icon_popular.png">
				<a href="list_popular.do">인기순</a>
			</li>
		</c:if>
		<c:if test = "${pos_type == '탑'}">
			<li>
				<img src="../resources/images/icon_recent.png">
				<a href="list_top.do">최신순</a> <!-- default -->
			</li>
			<li>
				<img src="../resources/images/icon_popular.png">
				<a href="list_top_popular.do">인기순</a>
			</li>
		</c:if>
		<c:if test = "${pos_type == '정글'}">
			<li>
				<img src="../resources/images/icon_recent.png">
				<a href="list_jungle.do">최신순</a> <!-- default -->
			</li>
			<li>
				<img src="../resources/images/icon_popular.png">
				<a href="list_jungle_popular.do">인기순</a>
			</li>
		</c:if>
		<c:if test = "${pos_type == '미드'}">
			<li>
				<img src="../resources/images/icon_recent.png">
				<a href="list_mid.do">최신순</a> <!-- default -->
			</li>
			<li>
				<img src="../resources/images/icon_popular.png">
				<a href="list_mid_popular.do">인기순</a>
			</li>
		</c:if>
		<c:if test = "${pos_type == '원딜'}">
			<li>
				<img src="../resources/images/icon_recent.png">
				<a href="list_ad.do">최신순</a> <!-- default -->
			</li>
			<li>
				<img src="../resources/images/icon_popular.png">
				<a href="list_ad_popular.do">인기순</a>
			</li>
		</c:if>
		<c:if test = "${pos_type == '서포터'}">
			<li>
				<img src="../resources/images/icon_recent.png">
				<a href="list_support.do">최신순</a> <!-- default -->
			</li>
			<li>
				<img src="../resources/images/icon_popular.png">
				<a href="list_support_popular.do">인기순</a>
			</li>
		</c:if>
	</ul>
</div>
	
<!-- 글쓰기 버튼 -->
<span id="pos_btnWrite">
	<img src="../resources/images/icon_write.png" onclick="location.href='write.do'">
</span>
	
<!-- 게시물 목록 -->
<c:if test="${boardCount == 0}">
	<div id="pos_noBoard">
		게시물이 없습니다.
	</div>
</c:if>

<c:if test="${boardCount > 0}">
	<div id="pos_boardList">
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
	</div>
</c:if>
	
<!-- 페이징 처리 -->
<div id="pos_paging">
	${pagingHtml}
</div>
<!-- 포지션게시판 : boardList 끝 -->