<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/main/main.do"><img src="${pageContext.request.contextPath}/resources/template_res/assets/img/navbar-logo.svg" alt="..." /></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/manualTool/list.do">챔피온분석</a></li>
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
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/adLogin.do">관리자로그인</a></li>
                        </c:if>
                        <c:if test="${!empty user_num && user_auth == 3}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/adLogin.do">회원관리</a></li>
                        </c:if>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/main/main.do">홈으로</a></li>
                    </ul>
                </div>
            </div>
        </nav>
 		<!-- Masthead-->
        <header class="masthead">
            <div class="container">
                <div class="masthead-subheading">Welcome To Our Studio!</div>
                <div class="masthead-heading text-uppercase">It's Nice To Meet You</div>
                <a class="btn btn-primary btn-xl text-uppercase" href="#services">Tell Me More</a>
            </div>
        </header>
<!-- 상단 끝 -->




