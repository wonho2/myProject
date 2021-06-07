<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 파티게시판 : 게시물 수정 시작 -->
<div class="page-main-style">
<form:form action="update.do" commandName="partyVO" 
									enctype="multipart/form-data">
	<form:hidden path="par_num"/>								
	<ul>
		<li>
			<label for="par_title">제목</label>
			<form:input path="par_title"/>
			<form:errors path="par_title" cssClass="error-color"/>
		</li>
		<li>
			<label for="par_upload">파일 업로드</label>
			<input type="file" name="par_upload" id="par_upload" accept="image/gif, image/png, image/jpeg">
			<c:if test="${!empty partyVO.par_filename}">
					<br>
					<span>이미 ${partyVO.par_filename} 파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
			</c:if>
		</li>
		<li> 
				<label for="par_content">내용</label>
				<form:textarea path="par_content" cols="40" rows="10"/>
				<form:errors path="par_content" cssClass="error-color"/>
			</li>
	</ul> 
		
	<div class="align-center">
		<input type="submit" value="수정">
		<input type="button" value="목록"
			                     onclick="location.href='list.do'">
	</div>
</form:form>
</div>
<!-- 파티게시판 : 게시물 수정 끝-->