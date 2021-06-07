<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main-style">
	<h2>뉴스</h2>
	<div class="align-right">
	 <c:if test="${!empty user_num}"> 
		<input type="button" value="작성" onclick="location.href='write.do'">
	</c:if> 
	</div>
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table  style="width:80%">
		<tr class="align-center">
			<th width="250px">이미지</th>
			<th width="900px">뉴스</th>
			<th width="200px">작성자</th>
			<th width="320px">작성일</th>
			<th width="150px">조회수</th> 
		</tr>  
		<c:forEach var="news" items="${newsList}" >
		<tr>
		<td align="center">
		<img src="imageView.do?new_num=${news.new_num}"
		                           style="width:70%;height:50px">
		</td>
			<td align="center"><a href="detail.do?new_num=${news.new_num}">${news.new_title}</a></td>
			 <td align="center">${news.mem_nick}</td>
			<td align="center">${news.new_date}</td>
			<td align="center" style="width:70px">${news.new_hit}</td>
		</tr>
		</c:forEach> 
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>
<!-- 중앙 컨텐츠 끝 -->

