<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!-- 포지션게시판 : 게시물 디테일 댓글 시작 -->
<!-- 자바스크립트 -->
<script type="text/javascript">
	// document.ready()
	$(document).ready(function(){
		updateCommentList();
	});
	// 댓글 목록 불러오기
	function updateCommentList()
	{
		
	}
	// 댓글 쓰기
	function writeComment()
	{
		// 로그인이 되어있지 않은 경우, 로그인 페이지로 이동 (내일 강사님과 만들기)
		
		// 댓글 내용이 빈 경우
		if($("#poc_content").val().trim() == ''){
			alert("댓글 내용을 입력하세요");
			$("#poc_content").focus();
			return;
		}
		// 정상
		$.ajax({
			type:"post",
			url:"writeComment.do",
			data:{poc_content:$('#poc_content').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				// textarea 초기화
				$("#poc_content").text('');
				// 데이터베이스 처리 결과
				if(param.result == "successed"){
					alert("댓글 등록 성공");
					updateCommentList();
				}
				else if(param.result == "failed"){
					alert("데이터베이스 오류");
				}
				else{
					alert("댓글쓰기 오류");
				}
			},
			error:function(){
				alert("네트워크 오류 발생");
			}
		});
	}
</script>

<!-- 헤더 -->
<div>
	<p>
		<span>댓글</span>
		${positionVO.pos_comment} 개
	</p>
	<input type="button" value="인기순"> <!-- default 정렬-->
	<input type="button" value="최신순">
</div>

<!-- 댓글 쓰기 -->
<p>
	<textarea name="poc_content" id="poc_content" style="resize:none; width:700px; height:100px;"></textarea>
	<input type="button" value="댓글쓰기" onclick="writeComment();">
</p>

<!-- 댓글 목록 -->
<c:forEach var="postionCommentVO" items="${commentList}">
	<nav>
		<input type="button" id="btn_favUp" value="추천">
		<div>
			${positionCommentVO.poc_fav}
		</div>
		<input type="button" id="btn_favDown" value="비추천">
	</nav>
	<div>
		${positionCommentVO.mem_nick} | ${positionCommentVO.poc_date}
	</div>
	<div>
		${positionCommentVO.poc_content}
	</div>
	<c:if test="${mem_num == postionCommentVO.mem_num}">
		<div>
			<input type="button" value="수정">
			<input type="button" value="삭제">
		</div>
	</c:if>
</c:forEach>
<!-- 포지션게시판 : 게시물 디테일 댓글 끝 -->