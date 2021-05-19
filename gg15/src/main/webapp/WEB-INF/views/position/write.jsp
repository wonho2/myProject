<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 포지션게시판 : 글쓰기 시작 -->
<c:if test="${empty mem_num}">
	<%
		// 로그인 페이지로 이동
	%>
</c:if>

<c:if test="${!empty mem_num}">
	<form:form action="write.do" commandName="positionVO" enctype="multipart/form-data">
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title"/>
				<form:errors path="title"/>
			</li>
			<li>
				<label for="uploadfile">파일 업로드</label>
				<input type="file" name="uploadfile" accept="image/gif, image/png, image/jpeg">
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="content"/>
				<form:errors path="content"/>
			</li>
		</ul>
		
		<div>
			<input type="submit" value="등록">
		</div>
	</form:form>
</c:if>
<!-- 포지션게시판 : 글쓰기  끝-->