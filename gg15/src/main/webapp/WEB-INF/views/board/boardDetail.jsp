<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="${pageContext.request.contextPath}/resources/js/board.reply.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/videoAdapter.js"></script>
<!-- 자유게시판 boardList 시작 -->
<!-- 게시물 상세 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#siren_btn').click(function(){
			var boa_num = $('#boa_num').val();
			var reportWrite = window.prompt("신고 내용을 입력하세요 :<","");
			
			//AJAX 통신
			$.ajax({
				//url:"/views/board/reportPage",//전송할 주소
				url:'reportWrite.do',
				type:'post',//전송방식
				//data:$('reportWrite'), //전송할 데이터
				data:{mem_num:$('#mem_num').val(),boa_num:$('#boa_num').val(),bop_content:reportWrite},
				dataType:'json',//데이터 받을 형식
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result=='logout'){
						alert('로그인해야 작성할 수 있습니다.');
					}else if(data.result=='success'){
						alert('신고가 완료되었습니다.');
					}else{
						alert('등록시 오류 발생!');
					}
	            },//성공시
	            error: function(){
	                alert("네트워크 에러 :( ");
				}//실패시
			});
		});	
	});		
</script> 
<div class="page-main-style">
	<div>			
			<c:if test="${board.boa_cate == 'freeTalk'}">자유토론</c:if>
			<c:if test="${board.boa_cate == 'gaming_machine'}">게이밍 기기</c:if>
			<c:if test="${board.boa_cate == 'game_talk'}">게임 이야기</c:if>
			<c:if test="${board.boa_cate == 'Discode'}">디스코드 홍보</c:if>
			<c:if test="${board.boa_cate == 'Tier'}">티어별 게시판</c:if>
			<c:if test="${board.boa_cate == 'Champion'}">챔피언별 게시판</c:if>
			<c:if test="${board.boa_cate == 'Humor'}">유머 게시판</c:if>
			<c:if test="${board.boa_cate == 'img/video'}">사진/비디오</c:if>
			<c:if test="${board.boa_cate == 'art'}">팬아트</c:if>
	</div>
		<h1>${board.boa_title}</h1>
	<div>
	<!-- 사용자 닉네임처리 해야함 -->
	<a href="/member/memberDetail.do?board_num=${board.boa_num}">${board.mem_nick}</a> 
	</div>
	<div> 
		작성일 ${board.boa_date} 
	</div>


<!-- 신고내용 시작 -->
	<c:if test="${user_auth == 3}">
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>
	</c:if>
<!-- 신고 내용 끝 -->


	
	<!-- 이미지 오찌하지 ?? -->
	<c:if test="${fn:endsWith(board.boa_filename,'.jpg') || 
	              fn:endsWith(board.boa_filename,'.JPG') ||
	              fn:endsWith(board.boa_filename,'.gif') ||
	              fn:endsWith(board.boa_filename,'.GIF') ||
	              fn:endsWith(board.boa_filename,'.png') ||
	              fn:endsWith(board.boa_filename,'.PNG')}"> 
	<div >
		<img src="imageView.do?board_num=${board.boa_num}"
		                           style="max-width:500px">
	</div>
	</c:if>
	<!-- 동영상 오찌하지 ?? 안하기러 함! 다음 기약 -->   
<%-- 	<c:if test="${fn:endsWith(board.boa_filename,'.mp4') || 
	              fn:endsWith(board.boa_filename,'.avi') ||
	              fn:endsWith(board.boa_filename,'.MOV') ||
	              fn:endsWith(board.boa_filename,'.H.264') ||
	              fn:endsWith(board.boa_filename,'.WMV')}">
	
	<div class="align-center">
		<video src="videoView.do?board_num=${board.boa_num}" controls="controls" autoplay="autoplay" muted="muted" >
		</video>
	                          
	</div>
	</c:if>
		 --%> 
	<p>
		${board.boa_content}
	</p>
	
	<div>  
		<img id="output_fav" src="../resources/images/heart01.png">
		<span id="output_fcount"></span> 
		<span id="output_rcount"></span>
	</div>
	
	<!-- 신고 활성화 -->
	<div class="align-right">
	<img src="../resources/images/siren.png" width=22px id="siren_btn" style="cursor:pointer;">	
	</div>
	
	
	<hr size="1" width="100%" noshade="noshade">
	
<!-- 수정, 삭제 버튼 -->
<c:if test="${user_num == board.mem_num}">
	
		<input type="button" value="수정" onclick="location.href='boardModify.do?boa_num=${board.boa_num}'" id="btn_modify">
		<input type="button" value="삭제" onclick="location.href='boardDelete.do?boa_num=${board.boa_num}'" id="btn_delete">
		<script type="text/javascript">
			var btn_delete = document.getElementById('btn_delete');
			//이벤트 연결
			btn_delete.onclick = function(){
				var choice = window.confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?boa_num=${board.boa_num}');
				} 
			};
		</script> 
		<input type="button" value="목록" onclick="location.href='list.do'"  id="btn_list">

</c:if>	
</div>
<!-- 본문 끝 -->

<!--  댓글 시작 -->
<hr size="1" width="100%">
	<div id="reply_div">
		<span class="reply-title">댓글</span>
		<form id="re_form">
			<input type="hidden" name="boa_num"
			       value="${board.boa_num}" id="boa_num">
			<input type="hidden" name="mem_num"
			       value="${user_num}" id="mem_num">
			<textarea rows="3" cols="50"
			  name="bor_content" id="bor_content"
			  class="rep-content"
			  <c:if test="${empty user_num}">disabled="disabled"</c:if>
			  ><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>              
			<c:if test="${!empty user_num}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second" class="align-right">
				<input type="submit" value="전송">
			</div>
			</c:if>
		</form>
	</div>

	<!-- 댓글 목록 출력 -->
		
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>

	
<!-- 댓글 끝 -->
<!--  자유게시판 boardList 끝 --> 