<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 포지션게시판 : 게시물 수정 시작 -->
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

<form:form action="modify.do" commandName="positionVO" enctype="multipart/form-data">
	<!-- 게시물 번호 전달용 -->
	<form:hidden path="pos_num"/>
	<!-- 수정 폼 -->
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
			<c:if test="${!empty positionVO.pos_filename}">
					<br>
					<span>이미 ${positionVO.pos_filename} 파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
			</c:if>
		</li>
		<li>
			<label>내용</label>
			<form:textarea path="pos_content" id="pos_content"/>
		</li>
	</ul>	
	<div>
		<input type="submit" value="수정" id="btn_submit">
	</div>
</form:form>
<!-- 포지션게시판 : 게시물 수정 끝-->