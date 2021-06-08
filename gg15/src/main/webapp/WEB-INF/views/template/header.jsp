<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
#button1 {
  background:#1AAB8A;
  color:#fff;
  border:none;
  position:relative;
  height:50px;
  padding:0 0.5em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
  font-size:20px;
}
#button1:hover{
  background:#fff;
  color:#1AAB8A;
}
#button1:before,#button1:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #1AAB8A;
  transition:400ms ease all; 
}
#button1:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
#button1:hover:before,#button1:hover:after{
  width:100%;
  transition:800ms ease all;
}
#user_name{
	font-size:20px;
	height:50px;
	width:400px;
}
</style>
<!-- 상단 시작 -->
<!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/main/main.do"><img src="${pageContext.request.contextPath}/resources/template_res/assets/img/gg15-Logo.png" alt="..." /></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>   
                
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/manualTool/list.do">챔피언공략</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/position/list.do">포지션정보</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/party/list.do">파티모집</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/list.do">자유게시판</a></li>
                    	<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/news/list.do">유저뉴스</a></li>
                        <c:if test="${!empty user_num && user_auth == 2}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/logout.do">[<span class="user_name">${user_id}</span>]로그아웃</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a></li>
                        </c:if>
                        <c:if test="${empty user_num}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
                        <%-- <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/adLogin.do">관리자로그인</a></li> --%>
                        </c:if>
                        <c:if test="${!empty user_num && user_auth == 3}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/adLogin.do">회원관리</a></li>
                        </c:if>
                      
                    </ul>
                </div>
            </div> 
        </nav>
 		<!-- Masthead-->
        <header class="masthead">
            <div class="container">
            <!--     <div class="masthead-subheading">Welcome To Our Studio!</div> -->
               <form action="${pageContext.request.contextPath}/search/searchResult.do" method="GET" style="border:none;width:1400px; text-align:center;">
			<div class="masthead-subheading">
				<input type="text" placeholder="소환사명을 입력하세요" name="userName" id="user_name">
				<button id="button1" type="submit">검색</button>
			</div>
		</form>
               <!--  <a class="btn btn-primary btn-xl text-uppercase" href="#services">Tell Me More</a>  -->
            </div> 
        </header>
<!-- 상단 끝 -->





