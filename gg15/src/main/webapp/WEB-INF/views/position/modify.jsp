<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 포지션게시판 : 게시물 수정 시작 -->
<form:form action="modify.do" commandName="positionVO" enctype="multipart/form-data">
	<ul>
		<li>
			<label for="title">제목</label>
			<form:input path="title"/>
			<form:errors path="title"/>
		</li>
		<li>
			<label for="uploadfile">파일 업로드</label>
			<input type="file" name="uploadfile" id="uploadfile" accept="image/gif, image/png, image/jpeg">
			<c:if test="${!empty positionVO.uploadfile}">
					<br>
					<span>${positionVO.uploadfile} 파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
			</c:if>
		</li>
		<li>
			<label for="content">내용</label>
			<form:textarea path="content"/>
			<form:errors path="content"/>
		</li>
	</ul>
		
	<div>
		<input type="submit" value="수정">
	</div>
</form:form>
<!-- 포지션게시판 : 게시물 수정 끝-->