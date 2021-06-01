<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 공략 게시판 글 수정 시작 -->
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

<div class="page-main-style">
	<h2>글 수정</h2>
	<form:form action="update.do" commandName="manualtoolVO" enctype="multipart/form-data">
		<form:hidden path="man_num"/>
		<ul>
			<li>
				<label>챔피언 선택</label>
				<form:select path="man_champion">
					<!-- 아이콘 선택이 아니라 글자로 선택하는 경우 (몇개나 넣을지 모르겠어요 다 넣으면 너무 많기도 하고) -->
					<form:option value="가렌">가렌</form:option>
					<form:option value="갈리오">갈리오</form:option>
					<form:option value="갱플랭크">갱플랭크</form:option>
					<form:option value="그라가스">그라가스</form:option>
					<form:option value="그레이브즈">그레이브즈</form:option>
					<form:option value="그웬">그웬</form:option>
					<form:option value="나르">나르</form:option>
					<form:option value="나미">나미</form:option>
					<form:option value="나서스">나서스</form:option>
					<form:option value="노틸러스">노틸러스</form:option>
					<form:option value="녹턴">녹턴</form:option>
					<form:option value="누누와 윌럼프">누누와 윌럼프</form:option>
					<form:option value="니달리">니달리</form:option>
					<form:option value="니코">니코</form:option>
					<form:option value="다리우스">다리우스</form:option>
					<form:option value="다이애나">다이애나</form:option>
					<form:option value="드레이븐">드레이븐</form:option>
					<form:option value="라이즈">라이즈</form:option>
					<form:option value="라칸">라칸</form:option>
					<form:option value="람머스">람머스</form:option>
				</form:select>
			</li>
			<li>
				<label>시즌 선택</label>
				<form:select path="man_season">
					<form:option value="S11">시즌11</form:option>
					<form:option value="S10">시즌10</form:option>
				</form:select>
			</li>
			<li>
				<label for="man_title">제목</label>
				<form:input path="man_title"/>
				<form:errors path="man_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="man_content">내용</label>
				<form:textarea cols="50" rows="20" path="man_content"/>
				<form:errors path="man_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="man_uploadfile">파일 업로드</label>
				<input type="file" name="man_uploadfile" id="man_uploadfile" accept="image/gif, image/png, image/jpeg">
				<c:if test="${!empty manualtoolVO.man_filename}">
					<br>
					<span>이미 ${manualtoolVO.man_filename} 파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>    
		<div class="align-center">
			<input type="submit" value="수정" id="btn_submit">
		</div>                            
	</form:form>
</div>
<!-- 공략 게시판 글 수정 끝 -->




