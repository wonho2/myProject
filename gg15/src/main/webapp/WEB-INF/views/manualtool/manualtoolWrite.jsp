<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 공략 게시판 글쓰기 시작 -->
<div class="page-main-style">
<hr size="1" width="100%" noshade="noshade">
<h2>글 작성</h2>
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

	<form:form action="write.do" commandName="manualtoolVO" enctype="multipart/form-data">
		<ul>
			<li>
			<hr size="1" width="100%" noshade="noshade">
				<label for="man_champion">챔피언</label>
				<form:select path="man_champion" name="챔피언 선택">
					<option value="가렌">가렌</option>
					<option value="갈리오">갈리오</option>
					<option value="갱플랭크">갱플랭크</option>
					<option value="그라가스">그라가스</option>
					<option value="그레이브즈">그레이브즈</option>
					<option value="그웬">그웬</option>
					<option value="나르">나르</option>
					<option value="나미">나미</option>
					<option value="나서스">나서스</option>
					<option value="노틸러스">노틸러스</option>
					<option value="녹턴">녹턴</option>
					<option value="누누와 윌럼프">누누와 윌럼프</option>
					<option value="니달리">니달리</option>
					<option value="니코">니코</option>
					<option value="다리우스">다리우스</option>
					<option value="다이애나">다이애나</option>
					<option value="드레이븐">드레이븐</option>
					<option value="라이즈">라이즈</option>
					<option value="라칸">라칸</option>
					<option value="람머스">람머스</option>
					<option value="럭스">럭스</option>
					<option value="럼블">럼블</option>
					<option value="레넥톤">레넥톤</option>
					<option value="레오나">레오나</option>
					<option value="렉사이">렉사이</option>
					<option value="렐">렐</option>
					<option value="렝가">렝가</option>
					<option value="루시안">루시안</option>
					<option value="룰루">룰루</option>
					<option value="르블랑">르블랑</option>
					<option value="리븐">리븐</option>
					<option value="리산드라">리산드라</option>
					<option value="리 신">리 신</option>
					<option value="릴리아">릴리아</option>
					<option value="마스터 이">마스터 이</option>
					<option value="마오카이">마오카이</option>
					<option value="말자하">말자하</option>
					<option value="말파이트">말파이트</option>
					<option value="모데카이저">모데카이저</option>
					<option value="모르가나">모르가나</option>
					<option value="문도 박사">문도 박사</option>
					<option value="미스 포츈">미스 포츈</option>
				</form:select>
			</li>
			 <li>
				<label for="man_season">시즌</label>
				<form:select path="man_season" name="시즌 선택">
					<option value="S11">시즌11</option>
					<option value="S10">시즌10</option>
				</form:select>
			</li>
			<li>
				<label for="man_title">제목</label>
				<form:input path="man_title"/>
				<form:errors path="man_title"/>
			</li>
			<li>
				<label for="man_upload">파일 업로드</label>
				<input type="file" name="man_upload" id="man_upload" accept="image/gif, image/png, image/jpeg">
			</li>
			<li>
				<label for="man_content">내용</label>
				<form:textarea cols="40" rows="15" path="man_content"/>
				<form:errors path="man_content"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록" id="btn_submit">
			<hr size="1" width="100%" noshade="noshade">
		</div>
	</form:form>
	<hr size="1" width="100%" noshade="noshade">
</div>
<!-- 공략 게시판 글쓰기  끝-->  