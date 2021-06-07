<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 파티게시판 : 글쓰기 시작 -->
<div class="page-main-style">
	<form:form action="write.do" commandName="partyVO" enctype="multipart/form-data">
		<ul>
			<li>
				<label for="par_type">파티</label>
				<form:select path="par_type" name="모집 종류">
					<option value="1">랭크 팀원 모집</option>
					<option value="2">칼바람 팀원 모집</option>
					<option value="3">격전 팀원 모집</option>
				</form:select>
			</li>
			<li>
				<label for="par_title">제목</label>
				<form:input path="par_title"/>
				<form:errors path="par_title"/>
			</li>
			<li>
				<label for="par_upload">파일 업로드</label>
				<input type="file" name="par_upload" accept="image/gif, image/png, image/jpeg">
			</li>
			<li>
				<label for="par_content">내용</label>
				<form:textarea path="par_content" cols="40" rows="10" placeholder="여기에 내용을 입력하세요"/>
				<form:errors path="par_content"/>
			</li>
		</ul>
		
		<div>
			<input type="submit" value="등록">
		</div>
	</form:form>
</div>	
<!-- 파티게시판 : 글쓰기  끝-->  