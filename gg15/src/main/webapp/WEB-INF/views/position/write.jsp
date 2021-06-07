<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/position.css">

<!-- 포지션게시판 : 글쓰기 시작 -->
<!-- 자바스크립트 -->
<script type="text/javascript">
	window.onload = function(){
		var pos_title = document.getElementById("pos_title");
		var pos_content = document.getElementById("pos_content");
		var btn_submit = document.getElementById("btn_submit");
		// 등록 이벤트 처리
		btn_submit.onclick = function(){
			if(pos_title.value.trim() == ''){
				alert("게시물 제목을 입력하세요");
				pos_title.focus();
				return false;
			}
			else if(pos_content.value.trim() == ''){
				alert("게시물 내용을 입력하세요");
				pos_content.focus();
				return false;
			}
			return true;
		};
	};
</script>

<!-- 로그인 상태는 LoginInterceptor 클래스에서 처리 -->
<!-- 글쓰기 폼 -->
<div>
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
			<form:input path="pos_title" id="pos_title"/>
		</li>
		<li>
			<label for="pos_upload">파일 업로드</label>
			<input type="file" name="pos_upload" id="pos_upload" accept="image/gif, image/png, image/jpeg">
		</li>
		<li>
			<form:textarea path="pos_content" id="pos_content"/>
		</li>
	</ul>	
	<p class="align-center">
		<input type="submit" value="등록" id="btn_submit">
	</p>
</form:form>
</div>
<!-- 포지션게시판 : 글쓰기  끝-->