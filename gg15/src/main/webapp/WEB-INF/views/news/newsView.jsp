<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!-- 중앙 컨텐츠 시작 -->
<script type="text/javascript">
	window.onload=function(){
		var newsDelete_btn = document.getElementById('newsDelete_btn');
		//이벤트 연결
		newsDelete_btn.onclick=function(){
			var choice = window.confirm('삭제하시겠습니까?');
			if(choice){
				location.replace('newsDelete.do?new_num=${news.new_num}');
			}
		};
	};
</script>
<div class="page-main-style">
	<h2>${news.title}</h2>
	<ul>
		<li>번호 : ${news.new_num}</li>
		<li>작성자 : ${news.id}</li>
		<li>조회수 : ${news.new_hit}</li>
		<li>작성일 : ${news.reg_date}</li>
		
	</ul>
	<hr size="1" width="100%">
	<c:if test="${fn:endsWith(news.filename,'.jpg') || 
	              fn:endsWith(news.filename,'.JPG') ||
	              fn:endsWith(news.filename,'.gif') ||
	              fn:endsWith(news.filename,'.GIF') ||
	              fn:endsWith(news.filename,'.png') ||
	              fn:endsWith(news.filename,'.PNG')}">
	<div class="align-center">
		<img src="imageView.do?new_num=${news.new_num}"
		                           style="max-width:500px">
	</div>
	</c:if>
	<p>
		${news.content}
	</p>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user_num && user_num == news.mem_num}">
		<input type="button" value="수정"
		 onclick="location.href='newsUpdate.do?new_num=${news.new_num}'">
		<input type="button" value="삭제" id="newsDelete_btn">
		</c:if>
		<input type="button" value="목록으로"
		                        onclick="location.href='list.do'">
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->