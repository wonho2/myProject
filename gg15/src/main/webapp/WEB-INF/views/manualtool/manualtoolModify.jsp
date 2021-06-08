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
<hr size="1" noshade="noshade" width="100%">
	<h2>글 수정</h2>
	<form:form action="update.do" commandName="manualtoolVO" enctype="multipart/form-data">
		<form:hidden path="man_num"/>
		<ul>
			<li>
			<hr size="1" width="100%" noshade="noshade">
				<label>챔피언 선택</label>
				<form:select path="man_champion">
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
					<form:option value="럭스">럭스</form:option>
					<form:option value="럼블">럼블</form:option>
					<form:option value="레넥톤">레넥톤</form:option>
					<form:option value="레오나">레오나</form:option>
					<form:option value="렉사이">렉사이</form:option>
					<form:option value="렐">렐</form:option>
					<form:option value="렝가">렝가</form:option>
					<form:option value="루시안">루시안</form:option>
					<form:option value="룰루">룰루</form:option>
					<form:option value="르블랑">르블랑</form:option>
					<form:option value="리븐">리븐</form:option>
					<form:option value="리산드라">리산드라</form:option>
					<form:option value="리 신">리 신</form:option>
					<form:option value="릴리아">릴리아</form:option>
					<form:option value="마스터 이">마스터 이</form:option>
					<form:option value="마오카이">마오카이</form:option>
					<form:option value="말자하">말자하</form:option>
					<form:option value="말파이트">말파이트</form:option>
					<form:option value="모데카이저">모데카이저</form:option>
					<form:option value="모르가나">모르가나</form:option>
					<form:option value="문도 박사">문도 박사</form:option>
					<form:option value="미스 포츈">미스 포츈</form:option>
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
				<form:textarea cols="40" rows="15" path="man_content"/>
				<form:errors path="man_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="man_upload">파일 업로드</label>
				<input type="file" name="man_upload" id="man_upload" accept="image/gif, image/png, image/jpeg">
				<c:if test="${!empty manualtoolVO.man_filename}">
					<br>
					<span>이미 ${manualtoolVO.man_filename} 파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>    
		<div class="align-center">
			<input type="submit" value="수정" id="btn_submit">
			<hr size="1" width="100%" noshade="noshade">
		</div>                            
	</form:form>
	<hr size="1" noshade="noshade" width="100%">
</div>
<!-- 공략 게시판 글 수정 끝 -->




