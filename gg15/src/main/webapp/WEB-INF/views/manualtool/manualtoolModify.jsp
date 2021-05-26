<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 공략 게시판 글 수정 시작 -->
<div class="page-main-style">
	<h2>글 수정</h2>
	<form:form action="update.do" commandName="manualtoolVO" enctype="multipart/form-data">
		<form:hidden path="man_num"/>
		<ul>
			<li>
				<label for="man_title">제목</label>
				<form:input path="man_title"/>
				<form:errors path="man_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="man_content">내용</label>
				<form:textarea path="man_content"/>
				<form:errors path="man_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="man_uploadfile">파일 업로드</label>
				<input type="file" name="man_uploadfile" id="man_uploadfile">
				<c:if test="${!empty manualtoolVO.man_uploadfile}">
					<br>
					<span>이미 파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>    
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>                            
	</form:form>
</div>
<!-- 공략 게시판 글 수정 끝 -->




