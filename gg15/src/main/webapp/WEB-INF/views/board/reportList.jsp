<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 자유게시판 boardMain 시작 -->
<div class="page-main-style">
	<h2>자유게시판 목록</h2>
		<div>
				<input type="button" value="일반 글 보기" onclick="location.href='boardList.do'">
		</div>	
	<table>	
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="boa_report" items="${list}">	
		<tr>
			<td>${boa_report.bop_num}</td>
			<td><a href="reportPage.do?bop_num=${boa_report.bop_num}">${boa_report.boa_title}</a></td>
			<td>${boa_report.mem_nick}</td>
			<td>${boa_report.bop_date}</td>
		</tr>
		</c:forEach>	
	</table>	
	
	<c:if test="${count == 0}"> 
		<section class="align-center">등록된 게시물이 없습니다.</section>
		</c:if>		
	<c:if test="${count ne 0}"> 
	<div class="align-center">${pagingHtml}</div>
	</c:if>
	
</div>			
	