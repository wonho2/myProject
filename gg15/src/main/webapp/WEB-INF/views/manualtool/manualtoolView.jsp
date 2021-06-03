<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/man.reply.js"></script>
<!-- 공략 게시판 디테일 시작 -->
<!-- 게시물 상세 -->

<div class="page-main-style">
	<div>[${manualtoolVO.man_champion}] | ${manualtoolVO.man_season}</div>
		<h1>${manualtoolVO.man_title}</h1>
	<div>
	<!-- 사용자 닉네임처리 해야함 -->
	<!-- <a href="/member/memberDetail.do?man_num=${manualtoolVO.man_num}">${memberVO.mem_nick}</a>  -->
	${manualtoolVO.mem_nick}
	</div>
	<div>
		${manualtoolVO.man_update}
	</div>
	<hr size="1" noshade="noshade" width="100%">
	<c:if test="${fn:endsWith(manualtoolVO.man_filename,'.jpg') || 
	              fn:endsWith(manualtoolVO.man_filename,'.JPG') ||
	              fn:endsWith(manualtoolVO.man_filename,'.gif') ||
	              fn:endsWith(manualtoolVO.man_filename,'.GIF') ||
	              fn:endsWith(manualtoolVO.man_filename,'.png') ||
	              fn:endsWith(manualtoolVO.man_filename,'.PNG')}">
	<div>
		<img src="imageView.do?man_num=${manualtoolVO.man_num}" style="max-width:500px">
	</div>
	</c:if>
	
<!-- <div>
	<h1>${manualtoolVO.man_title}</h1>
	<section>
		[${manualtoolVO.man_champion}] | ${manualtoolVO.man_season} | ${manualtoolVO.man_update} | ${manualtoolVO.mem_nick}
	</section>
	<section>
		조회수 ${manualtoolVO.man_hit} | 추천수 ${manualtoolVO.man_fav} | 댓글수 ${manualtoolVO.man_comment}
	</section>
	
	<hr size="1" noshade="noshade" width="100%">
	
	<c:if test="${fn:endsWith(manualtoolVO.man_filename,'.jpg') || 
	              fn:endsWith(manualtoolVO.man_filename,'.JPG') ||
	              fn:endsWith(manualtoolVO.man_filename,'.gif') ||
	              fn:endsWith(manualtoolVO.man_filename,'.GIF') ||
	              fn:endsWith(manualtoolVO.man_filename,'.png') ||
	              fn:endsWith(manualtoolVO.man_filename,'.PNG')}">
		<div>
			<img src="imageView.do?man_num=${manualtoolVO.man_num}">
		</div>
	</c:if>
 -->
	<hr size="1" noshade="noshade" width="100%">
	<p>
		${manualtoolVO.man_content}
	</p>
	<hr size="1" noshade="noshade" width="100%">
	 <!-- 추천 버튼 -->
	<div>  
		<img id="output_fav" src="../resources/images/heart01.png">
		<span id="output_fcount"></span> 
		<span id="output_rcount"></span>
	</div>
	<hr size="1" width="100%" noshade="noshade">
</div>

<!-- 수정, 삭제 버튼 -->
<c:if test="${user_num == manualtoolVO.mem_num}">
	<div>
		<input type="button" value="수정" onclick="location.href='update.do?man_num=${manualtoolVO.man_num}'">
		<input type="button" value="삭제" onclick="man_delete();">
		<script type="text/javascript">
			function man_delete(){
				var choice = window.confirm("해당 게시물을 삭제하시겠습니까?");
				if(choice){
					location.replace('delete.do?man_num=${manualtoolVO.man_num}');
				}
			}
		</script>
	</div>
</c:if>

<!--  댓글 시작 -->
<hr size="1" width="100%">
	<div id="reply_div">
		<span class="reply-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="man_num"
			       value="${manualtoolVO.man_num}" id="man_num">
			<input type="hidden" name="mem_num"
			       value="${user_num}" id="mem_num">
			<textarea rows="3" cols="50"
			  name="mar_content" id="mar_content"
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
<!-- 공략 게시판 디테일 끝 -->