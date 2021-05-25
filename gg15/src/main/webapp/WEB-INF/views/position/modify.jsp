<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 포지션게시판 : 게시물 수정 시작 -->
<form:form action="modify.do" commandName="positionVO" enctype="multipart/form-data">

	<form:hidden path="pos_num"/>
	
	<ul>
		<li>
			<label>제목</label>
			<form:input path="pos_title"/>
			<form:errors path="pos_title"/>
		</li>
		<li>
			<label for="pos_upload">파일 업로드</label>
			<input type="file" name="pos_upload" id="pos_upload" accept="image/gif, image/png, image/jpeg">
			<c:if test="${!empty positionVO.pos_filename}">
					<br>
					<span>이미 ${positionVO.pos_filename} 파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
			</c:if>
		</li>
		<li>
			<label>내용</label>
			<form:textarea path="pos_content"/>
			<form:errors path="pos_content"/>
		</li>
	</ul>
		
	<div>
		<input type="submit" value="수정">
	</div>
</form:form>
<!-- 포지션게시판 : 게시물 수정 끝-->