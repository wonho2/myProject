<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 자유게시판 boardList 시작 -->

<script type="text/javascript">
	window.onload=function(){
		var btn_delete = document.getElementById('btn_delete');
		//이벤트 연결
		btn_delete.onclick = function(){
			var choice = window.confirm('삭제하시겠습니까?');
			if(choice){
				location.replace('delete.do?boa_num=${board.boa_num}');
			}
		};
	};
</script> 

<!-- 게시물 상세 -->

<!-- 비밀글 설정 확인-->
<c:if test=" ${boa_mode = 1} || ${boa_mode = 2} && ${!empty user_num}">
{
<div>
	<section>${board.boa_cate}</section>
		<h1>${board.boa_title}</h1>
	<section>
	<!-- 사용자 닉네임처리 해야함 -->
	<a href="/member/memberDetail.do?board_num=${board.boa_num}">${memberVO.mem_nick}</a> 
	
	</section>
	<section>
		작성일 ${board.boa_date} 
		
	</section>
	<!-- 이미지 오찌하지... -->
	<c:if test="${fn:endsWith(board.boa_filename,'.jpg') || 
	              fn:endsWith(board.boa_filename,'.JPG') ||
	              fn:endsWith(board.boa_filename,'.gif') ||
	              fn:endsWith(board.boa_filename,'.GIF') ||
	              fn:endsWith(board.boa_filename,'.png') ||
	              fn:endsWith(board.boa_filename,'.PNG')}">
	<div class="align-center">
		<img src="imageView.do?board_num=${board.boa_num}"
		                           style="max-width:500px">
	</div>
	</c:if>
	
	<section>
		${board.boa_content}
	</section>
	<ul>
		<li>
		<input type="button" value="신고" id="btn_report">
		</li>
	</ul>
<!-- 수정, 삭제 버튼 -->
<c:if test="${user_num == board.mem_num}">
	
		<input type="button" value="수정" onclick="location.href='boardModify.do?boa_num=${board.boa_num}'" id="btn_modify">
		<input type="button" value="삭제" onclick="location.href='boardDelete.do?boa_num=${board.boa_num}'" id="btn_delete">
		<input type="button" value="목록" onclick="location.href='list.do'"  id="btn_list">

</c:if>
	 
<c:if test="${!empty user_num}">
		<input type="button" value="좋아요" id="btn_like">
</c:if>
			
</div>
}
</c:if>

<c:if test="${boa_mode = 2} && ${empty user_num}">
	location.href='errorPage.do'
</c:if>
<!--  자유게시판 boardList 끝 -->