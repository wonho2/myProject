<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 포지션게시판 : 게시물 디테일 시작 -->
<!-- 수정, 삭제 버튼 -->
<c:if test="${user_num == positionVO.mem_num}">
	<div>
		<input type="button" value="수정" onclick="location.href='modify.do?pos_num=${positionVO.pos_num}'">
		<input type="button" value="삭제" onclick="pos_delete();">
		<!-- 여기에 자바스크립트를 작성하는 이유는 c:if의 조건이 만족하지 않으면, 코드가 보여지지 않게 하기 위해서라고 강사님이 말씀하셨습니다 -->
		<script type="text/javascript">
			function pos_delete(){
				var choice = window.confirm("해당 게시물을 삭제하시겠습니까?");
				if(choice){
					location.replace('delete.do?pos_num=${positionVO.pos_num}');
				}
			}
		</script>
	</div>
</c:if>

<!-- 게시물 상세 -->
<div>
	<h1>${positionVO.pos_title}</h1>
	<section>
		[${positionVO.pos_type}] | ${positionVO.pos_date} | ${positionVO.mem_nick}
	</section>
	<section>
		조회수 ${positionVO.pos_view} | 추천수 ${positionVO.pos_fav} | 댓글수 ${positionVO.pos_comment}
	</section>
	
	<hr size="1" noshade="noshade" width="100%">
	
	<c:if test="${fn:endsWith(positionVO.pos_filename,'.jpg') || 
	              fn:endsWith(positionVO.pos_filename,'.JPG') ||
	              fn:endsWith(positionVO.pos_filename,'.gif') ||
	              fn:endsWith(positionVO.pos_filename,'.GIF') ||
	              fn:endsWith(positionVO.pos_filename,'.png') ||
	              fn:endsWith(positionVO.pos_filename,'.PNG')}">
		<div class="align-center">
			<img src="imageView.do?pos_num=${positionVO.pos_num}">
		</div>
	</c:if>
	<p>
		${positionVO.pos_content}
	</p>
	 
	 <!-- 추천 버튼 -->
	<p class="align-center">
		<input type="button" value="추천" id="btn_fav">
	</p>
</div>

<hr size="1" noshade="noshade" width="100%">
 
<!-- 댓글 -->
<p>
	<%@ include file="/WEB-INF/views/position/comment.jsp" %>
</p>

<!-- 포지션게시판 : 게시물 디테일 끝 -->