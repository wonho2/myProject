<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mymy.css">    
<!-- 중앙 컨텐츠 시작 -->
<div class="page-main-style">
	<h2>회원 상세 정보</h2>
	<ul>
		<li>이름 : ${member.mem_name}</li>
		<li>닉네임 : ${member.mem_nick}</li>
		<li>전화번호 : ${member.mem_phone}</li>
		<li>이메일 : ${member.mem_email}</li>
		<li>가입일 : ${member.mem_date}</li1>
	</ul>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="회원정보수정"
		       onclick="location.href='update.do'">
		<input type="button" value="비밀번호변경"
		       onclick="location.href='changePassword.do'">  
		<input type="button" value="회원탈퇴"
		       onclick="location.href='delete.do'">            
	</p>
</div>
<div id="candy" class="page-main-style close">
	<h2>내가 쓴 글</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/party/myList.do">파티모집</a></li>
		<li><a href="${pageContext.request.contextPath}/member/myBoard.do">자유게시판</a></li>
		<li><a href="${pageContext.request.contextPath}/member/myManual.do">챔피언공략</a></li>
		<li><a href="${pageContext.request.contextPath}/member/myNews.do">유저뉴스</a></li>
		<li><a id="rodi" href="${pageContext.request.contextPath}/member/myPosition.do">포지션정보</a></li>
	</ul>
</div>	

<!-- 중앙 컨텐츠 끝 -->



