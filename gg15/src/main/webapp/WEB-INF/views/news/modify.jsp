<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main-style">
	<h2>글 수정</h2>
	<form:form action="update.do" commandName="newsVO" 
	                                enctype="multipart/form-data">
		<form:hidden path="new_num"/>
		<ul>
			<li>
				<label for="new_title">제목</label>
				<form:input path="new_title"/>
				<form:errors path="new_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="new_content">내용</label>
				<form:textarea path="new_content"/>
				<form:errors path="new_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="new_upload">파일 업로드</label>
				<input type="file" name="new_upload" id="new_upload">
				<c:if test="${!empty newsVO.new_filename}">
				<br>
				<span>(${newsVO.new_filename})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>    
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="목록"
			                     onclick="location.href='list.do'">
		</div>                            
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->




