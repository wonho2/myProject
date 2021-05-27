<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 자유게시판 boardModify 시작 -->
<h2>글 수정</h2>
<form:form action="update.do" commandName="boardVO" enctype="multipart/form-data">

	<form:hidden path="boa_num"/>
	 
			<ul>
			<!-- 카테고리는 공지 --> 
			<section>카테고리 : ${board.boa_cate}</section>
			
			<!-- 제목 -->
			<li>
				<label for="boa_title">제목</label>
				<form:input path="boa_title"/>
				<form:errors path="boa_title" cssClass="error-color"/>
			</li>
			<!-- 첨부파일 -->
			<li>
				<label for="boa_upload">첨부파일</label>
				<input type="file" name="boa_upload" id="boa_upload"
			accept="image/gif,image/png,image/jpeg,video/mp4,video/avi">
			<c:if test="${!empty board.boa_filename}">
					<br>
					<span>이미 ${board.boa_filename} 파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
			</c:if>
				                  
			</li>
			<!-- 내용 -->
			<li>
				<label for="boa_content">내용</label>
				<form:textarea path="boa_content"/>
				<form:errors path="boa_content" cssClass="error-color"/>
			</li>
			<!-- 공개범위 설정 -->
			<li>
			    <label for="boa_mode">비밀글 설정</label>
				<input type="checkbox" name="boa_mode" value="전체공개">비밀글
			</li>
		</ul>
		
	<div>
		<input type="button" value="목록" onclick="location.href='list.do'">
		<input type="submit" value="수정">
	</div>
</form:form>
<!-- 자유게시판 boardModify 끝-->