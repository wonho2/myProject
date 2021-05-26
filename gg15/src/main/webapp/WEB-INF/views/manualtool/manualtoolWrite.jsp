<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 공략 게시판 글쓰기 시작 -->
<!-- js -->
<script type="text/javascript">
	window.onload = function() {
		var man_title = document.getElementById("man_title");
		var man_content = document.getElementById("man_content");
		var btn_submit = document.getElementById("btn_submit");
		//등록 이벤트 처리
		btn_submit.onclick = function() {
			if(man_title.value.trim() == '') {
				alert("제목을 입력하세요");
				man_title.focus();
				return false;
			}
			else if(man_content.value.trim() == '') {
				alert("내용을 입력하세요");
				man_content.focus();
				return false;
			}
			return true;
		};
	};
</script>

<!-- 로그인 확인 -->

	<form:form action="write.do" commandName="manualtoolVO" enctype="multipart/form-data">
		<ul>
			<!-- 
			<li>
				<label for="man_champion">챔피언</label>
				<form:select path="man_champion" name="챔피언 선택">
					 아이콘 선택 or 이름 선택으로,,? 
				</form:select>
			</li>
			 -->
			 <li>
				<label for="man_season">시즌</label>
				<form:select path="man_season" name="시즌 선택">
					<option value="S10">시즌10</option>
					<option value="S11">시즌11</option>
				</form:select>
			</li>
			<li>
				<label for="man_title">제목</label>
				<form:input path="man_title"/>
				<form:errors path="man_title"/>
			</li>
			<li>
				<label for="man_uploadfile">파일 업로드</label>
				<input type="file" name="man_uploadfile" accept="image/gif, image/png, image/jpeg">
			</li>
			<li>
				<label for="man_content">내용</label>
				<form:textarea path="man_content"/>
				<form:errors path="man_content"/>
			</li>
		</ul>
		<div>
			<input type="submit" value="등록">
		</div>
	</form:form>
<!-- 공략 게시판 글쓰기  끝-->  