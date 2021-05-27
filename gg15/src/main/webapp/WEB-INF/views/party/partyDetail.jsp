<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!-- 중앙 컨텐츠 시작 -->
<script type="text/javascript">
	window.onload=function(){
		var partyDelete_btn = document.getElementById('partyDelete_btn');
		//이벤트 연결
		partyDelete_btn.onclick=function(){
			var choice = window.confirm('삭제하시겠습니까?');
			if(choice){
				location.replace('delete.do?par_num=${partyVO.par_num}');
			}
		};
	};   
</script>
<div class="page-main-style">
	<h2>${party.par_title}</h2>
	<ul>  
		<li>번호 : ${partyVO.par_num}</li>
		<li>작성자 : ${partyVO.mem_nick}</li>
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
	<hr size="1" width="100%">
	<div class="align-right">
	 <c:if test="${!empty user_num && user_num == partyVO.mem_num}"> 
		<input type="button" value="수정"
		 onclick="location.href='update.do?par_num=${partyVO.par_num}'">
		<input type="button" value="삭제" id="partyDelete_btn">
	</c:if> 
		<input type="button" value="목록으로"
		                        onclick="location.href='list.do'">
	</div>
</div>
<!-- 중앙 컨텐츠 끝 -->