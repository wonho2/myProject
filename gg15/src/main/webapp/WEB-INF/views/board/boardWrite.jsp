<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
.ck-editor__editable_inline {
    min-height: 250px;
}
</style>
<!-- include ckeditor js -->
<!-- <script src="https://cdn.ckeditor.com/ckeditor5/24.0.0/classic/ckeditor.js"></script> -->
<script src="${pageContext.request.contextPath}/resources/js/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/uploadAdapter.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/videoAdapter.js"></script>
<!-- 자유 게시판 board write 시작 -->
<div class="page-main-style">
	<h2>자유 게시판 글쓰기</h2>
	<form:form action="boardWrite.do" commandName="boardVO"
	               enctype="multipart/form-data" style="width:700px;">
		<ul> 
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
				 <script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#boa_content' ),{
		            	extraPlugins: [MyCustomUploadAdapterPlugin]
		            })
		            .then( editor => {
						window.editor = editor;
					} )
		            .catch( error => {
		                console.error( error );
		            } );
			    </script>          
			</li>
			
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
			<!-- 공개범위 설정 -->
			<span style="margin-left:220px;"><b>비밀글 설정</b></span>
			<input type="checkbox" name="boa_mode" value="1">비밀글
			</li>
		</ul>
		<div class="align-center">
			<!-- 목록 -->
			<input type="button" id="list" value="목록" onclick="location.href='list.do'">
			<!-- 미리보기 -->
			<input type="button" id="e-show" value="미리보기" >
			<!-- 임시 저장 -->
			<input type="button" id="e-save" value="임시저장">
			<!-- 저장 -->
			<input type="submit" id="save" value="등록">
		</div>
	</form:form>
</div>
<!-- 자유 게시판  board write  끝 -->