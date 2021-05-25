<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 포지션게시판 : 글쓰기 시작 -->
<!-- 로그인 상태는 LoginInterceptor 클래스에서 처리 -->
<form:form action="write.do" commandName="positionVO" enctype="multipart/form-data">
	<ul>
		<li>
			<label>포지션</label>
			<form:select path="pos_type">
				<form:option value="탑">탑</form:option>
				<form:option value="정글">정글</form:option>
				<form:option value="미드">미드</form:option>
				<form:option value="원딜">원딜</form:option>
				<form:option value="서포터">서포터</form:option>
			</form:select>
		</li>
		<li>
			<label>제목</label>
			<form:input path="pos_title"/>
			<form:errors path="pos_title"/>
		</li>
		<li>
			<label for="pos_upload">파일 업로드</label>
			<input type="file" name="pos_upload" id="pos_upload" accept="image/gif, image/png, image/jpeg">
		</li>
		<li>
			<label>내용</label>
			<form:textarea path="pos_content"/>
			<form:errors path="pos_content"/>
		</li>
	</ul>
		
	<div>
		<input type="submit" value="등록">
	</div>
	</form:form>
<!-- 포지션게시판 : 글쓰기  끝-->