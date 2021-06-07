<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main-style">
	<h2>글쓰기</h2>
	<form:form action="write.do" commandName="newsVO"
	               enctype="multipart/form-data">
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
				<label for="new_upload">이미지 파일 업로드</label>
				<input type="file" name="new_upload" id="new_upload"
				                  accept="image/gif,image/png,image/jpeg">
			</li> 
		</ul>
		<div class="align-center">
			<input type="submit" value="작성">
			<input type="button" value="취소"
			               onclick="location.href='list.do'">
		</div>
	</form:form>
</div>
<!-- 중앙 컨텐츠 끝 -->