<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 자유 게시판 board write 시작 -->
<div class="page-main-style">
	<h2>자유 게시판 글쓰기</h2>
	<form:form action="write.do" commandName="boardVO"
	               enctype="multipart/form-data">
		<ul>
			<!-- 말머리 선택 --> 
			<li>
			<label for="boa_cate">카테고리</label>
			<select name="boa_cate">
			<optgroup label="카테고리">
				<option value="All">전체</option>
				<option value="gaming_machine">게이밍 기기</option>
				<option value="game_talk">게임 이야기</option>
				<option value="Discode">디스코드 홍보</option>
				<option value="Tier">티어별 게시판</option>
				<option value="Champion">챔피언 게시판</option>
				<option value="Humor">유머 게시판</option>
				<option value="img/video">사진/비디오</option>
				<option value="art">팬아트</option>
			</optgroup>
			</select>
			</li>
			<!-- 제목 -->
			<li>
				<label for="boa_title">제목</label>
				<form:input path="boa_title"/>
				<form:errors path="boa_title" cssClass="error-color"/>
			</li>
			<!-- 첨부파일 -->
			<li>
				<label for="boa_upload">첨부파일</label>
				<input type="file" name="boa_upload" id="boa_upload"
				                  accept="image/gif,image/png,image/jpeg,video/mp4,video/avi">
			</li>
			<!-- 내용 -->
			<li>
				<label for="boa_content">내용</label>
				<form:textarea path="boa_content"/>
				<form:errors path="boa_content" cssClass="error-color"/>
			</li>
			<!-- 공개범위 설정 -->
			<li>
			    <label for="boa_mode">비밀글 설정</label>
				<input type="checkbox" name="boa_mode" value="전체공개">비밀글
			</li>
		</ul>
		<div class="align-center">
			<!-- 목록 -->
			<input type="button" value="목록" onclick="location.href='list.do'">
			<!-- 미리보기 -->
			<input type="button" value="미리보기" >
			<!-- 임시 저장 -->
			<input type="button" value="임시저장">
			<!-- 저장 -->
			<input type="submit" value="등록">
		</div>
	</form:form>
</div>
<!-- 자유 게시판  board write  끝 -->