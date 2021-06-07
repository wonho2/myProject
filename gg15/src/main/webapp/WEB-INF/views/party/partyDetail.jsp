<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/party.reply.js"></script>    
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main-style">
	<h2>${party.par_title}</h2>
	<ul>  
		<li>번호 : ${partyVO.par_num}</li>
		<li>작성자 : ${partyVO.mem_nick}</li>
		<li>추천수 : ${partyVO.par_fav}</li>
		<li>조회수 : ${partyVO.par_hit}</li>
		<li>작성일 : ${partyVO.par_date}</li>
		    
	</ul>
	<hr size="1" width="100%">
	<c:if test="${fn:endsWith(partyVO.par_filename,'.jpg') || 
	              fn:endsWith(partyVO.par_filename,'.JPG') ||
	              fn:endsWith(partyVO.par_filename,'.gif') ||
	              fn:endsWith(partyVO.par_filename,'.GIF') ||
	              fn:endsWith(partyVO.par_filename,'.png') ||
	              fn:endsWith(partyVO.par_filename,'.PNG')}">
	<div class="align-center">
		<img src="imageView.do?par_num=${partyVO.par_num}"
		                           style="max-width:500px">
	</div>
	</c:if>
	<p>
		${partyVO.par_content}
	</p>
	<div>
		<img id="output_fav" src="../resources/images/heart01.png">
		<span id="output_fcount"></span> <span id="output_rcount"></span>
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
	 <c:if test="${!empty user_num && user_num == partyVO.mem_num}"> 
		<input type="button" value="수정"
		 onclick="location.href='update.do?par_num=${partyVO.par_num}'">
		<input type="button" value="삭제" id="partyDelete_btn">
		<script type="text/javascript">
			var partyDelete_btn = document.getElementById('partyDelete_btn');
			//이벤트 연결
			partyDelete_btn.onclick=function(){
				var choice = window.confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?par_num=${partyVO.par_num}');
				}
			};
		</script>
		</c:if> 
		<input type="button" value="목록으로"
		                        onclick="location.href='list.do'">
	</div>
</div>

<!-- 댓글시작 -->
<hr size="1" width="100%">
	<div id="reply_div">
		<span class="reply-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="par_num"
			       value="${partyVO.par_num}" id="par_num">
			<input type="hidden" name="mem_num"
			       value="${user_num}" id="mem_num">
			<textarea rows="3" cols="50"
			  name="pop_content" id="pop_content"
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
<!-- 댓글끝 -->

<!-- 중앙 컨텐츠 끝 -->