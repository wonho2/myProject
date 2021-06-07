<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!-- 중앙 컨텐츠 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/news.reply.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var newsDelete_btn = document.getElementById('newsDelete_btn');
		//이벤트 연결
		newsDelete_btn.onclick=function(){
			var choice = window.confirm('삭제하시겠습니까?');
			if(choice){
				location.replace('delete.do?new_num=${news.new_num}');
			}
		};
	};   
</script> 
<div class="page-main-style">
	<h2>${news.new_title}</h2>
	<ul>  
		<li>작성자 : ${news.mem_nick}</li>
		<li>조회수 : ${news.new_hit}</li>
		<li>작성일 : ${news.new_date}</li>
		    
	</ul>
	<hr size="1" width="100%">
	<c:if test="${fn:endsWith(news.new_filename,'.jpg') || 
	              fn:endsWith(news.new_filename,'.JPG') ||
	              fn:endsWith(news.new_filename,'.gif') ||
	              fn:endsWith(news.new_filename,'.GIF') ||
	              fn:endsWith(news.new_filename,'.png') ||
	              fn:endsWith(news.new_filename,'.PNG')}">
	<div class="align-center">
		<img src="imageView.do?new_num=${news.new_num}"
		                           style="max-width:400px">
	</div>
	</c:if>
	<p>
		${news.new_content}
	</p>
	
	<hr size="1" width="100%">
		 <!-- 추천 버튼 -->
	<div>  
		<img id="output_fav" src="../resources/images/heart01.png">
		<span id="output_fcount"></span> 
		<span id="output_rcount"></span>
	</div>
	
	<div class="align-right">
	 <c:if test="${!empty user_num && user_num == news.mem_num}"> 
		<input type="button" value="수정"
		 onclick="location.href='update.do?new_num=${news.new_num}'">
		<input type="button" value="삭제" id="newsDelete_btn">
	</c:if> 
		<input type="button" value="목록"
		                        onclick="location.href='list.do'">
	</div>
</div>
<!--  댓글 시작 -->
<hr size="1" width="100%">
	<div id="reply_div">
		<span class="reply-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="new_num"
			       value="${news.new_num}" id="new_num">
			<input type="hidden" name="mem_num"
			       value="${user_num}" id="mem_num">
			<textarea rows="3" cols="50"
			  name="ner_content" id="ner_content"
			  class="rep-content"
			  <c:if test="${empty user_num}">disabled="disabled"</c:if>
			  ><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>              
			<c:if test="${!empty user_num}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second" class="align-right">
				<input type="submit" value="작성">
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
<!-- 중앙 컨텐츠 끝 -->