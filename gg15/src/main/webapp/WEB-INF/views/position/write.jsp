<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 포지션게시판 : 글쓰기 시작 -->
<!-- 로그인 상태는 LoginInterceptor 클래스에서 처리 -->
<form:form action="write.do" commandName="positionVO" enctype="multipart/form-data">
	<ul>
		<li>
			<label for="type">포지션</label>
			<form:select path="type">
				<form:option value="탑">탑</form:option>
				<form:option value="정글">정글</form:option>
				<form:option value="미드">미드</form:option>
				<form:option value="원딜">원딜</form:option>
				<form:option value="서포터">서포터</form:option>
			</form:select>
		</li>
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
<!-- 포지션게시판 : 글쓰기  끝-->