<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/position.css">

<!-- 포지션게시판 : 게시물 디테일 시작 -->
<!-- 자바스크립트 include -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/position_fav.js"></script>

<!-- 게시물 상세 -->
<div id="pos_boardDetail">
	<ul>
		<li>
			<h3>${positionVO.pos_title}</h3>
		</li>
		
		<li>
			[${positionVO.pos_type}] | ${positionVO.pos_date} | ${positionVO.mem_nick}
		</li>
		
		<li>
			조회수 ${positionVO.pos_view} | 추천수 <span class="favCount">${favCount}</span> | 댓글수 <span class="commentCount"></span>
		</li>
		
		<!-- 수정, 삭제 버튼 -->
		<c:if test="${user_num == positionVO.mem_num}">
			<li>
				<input type="button" value="수정" onclick="location.href='mo
				dify.do?pos_num=${positionVO.pos_num}'">
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
			</li>
		</c:if>
		
		<li>
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
			<div>
		${positionVO.pos_content}
			</div>
		</li>
	</ul>
			
	<!-- 추천 버튼 -->
	<div>
		<input type="button" value="추천/비추천(원버튼)" id="btn_fav">
		<div class="favCount">${favCount}</div>
	</div>
</div>
	
<hr size="1" width="100%">
		
<!-- 댓글 페이지 -->
<p>
	<%@ include file="/WEB-INF/views/position/comment.jsp" %>
</p>
<!-- 포지션게시판 : 게시물 디테일 끝 -->