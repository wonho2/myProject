<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 자유게시판 reportPage 시작 -->

<div class="page-main-style"> 
	<div>			
			<c:if test="${report.boa_cate == 'freeTalk'}">자유토론</c:if>
			<c:if test="${report.boa_cate == 'gaming_machine'}">게이밍 기기</c:if>
			<c:if test="${report.boa_cate == 'game_talk'}">게임 이야기</c:if>
			<c:if test="${report.boa_cate == 'Discode'}">디스코드 홍보</c:if>
			<c:if test="${report.boa_cate == 'Tier'}">티어별 게시판</c:if>
			<c:if test="${report.boa_cate == 'Champion'}">챔피언별 게시판</c:if>
			<c:if test="${report.boa_cate == 'Humor'}">유머 게시판</c:if>
			<c:if test="${report.boa_cate == 'img/video'}">사진/비디오</c:if>
			<c:if test="${report.boa_cate == 'art'}">팬아트</c:if>
	</div>
	
	<h3> 게시물 [" ${report.boa_title} "] 신고 </h3>
	<div>신고자 : <a href="/member/memberDetail.do?board_num=${board.boa_num}">${report.mem_nick}</a> </div>
	<div>신고일 : ${report.bop_date} </div>
	
	<div>
		<h4>신고 내용</h4>
		<p>	${report.bop_content} </p>
	</div>
	
	<h2> 본문 게시글 </h2>
	<div style="border:1px solid black;">
		<c:if test="${fn:endsWith(report.boa_filename,'.jpg') || 
		              fn:endsWith(report.boa_filename,'.JPG') ||
		              fn:endsWith(report.boa_filename,'.gif') ||
		              fn:endsWith(report.boa_filename,'.GIF') ||
		              fn:endsWith(report.boa_filename,'.png') ||
		              fn:endsWith(report.boa_filename,'.PNG')}"> 
		<div class="align-center">
			<img src="imageView.do?board_num=${report.boa_num}"
			                           style="max-width:500px">
		</div>
		</c:if>
		<a href="boardDetail.do?boa_num=${report.boa_num}"> 본문가기</a>
		<p>${report.boa_content}</p>
	</div>
	
	<!-- 차단 게시물 설정 버튼 -->
 	<div>  
	<!-- <input type="button" id="output_status" value="게시물 차단 하기"/> -->
	<!--<a href="Sta2.do?boa_num=${report.boa_num}" id="btn_State2"> 차단 선택  v</a>
		<a href="Sta1.do?boa_num=${report.boa_num}" id="updateState1"> 차단 해제  v</a>	
	-->
	<input type="button" value="차단"  id="btn_Sta2">
	<script type="text/javascript">
			var btn_Sta2 = document.getElementById('btn_Sta2');
			//이벤트 연결
			btn_Sta2.onclick = function(){
				//onclick="location.href='Sta2.do?boa_num=${report.boa_num}'"
				 
				var choice = window.confirm('차단하시겠습니까');
				if(choice){
					location.replace('Sta2.do?boa_num=${report.boa_num}');
					alert('차단 완료');
					//location.replace('Sta2.do?boa_num=${board.boa_num}'); 
				} 
			};
		</script>
	
	<input type="button" value="차단해제"  id="btn_Sta1">
	<script type="text/javascript">
			var btn_Sta1 = document.getElementById('btn_Sta1');
			//이벤트 연결
			btn_Sta1.onclick = function(){
				//onclick="location.href='Sta1.do?boa_num=${report.boa_num}'"
				 
				var choice = window.confirm('차단해제하시겠습니까?');
				if(choice){
					location.replace('Sta1.do?boa_num=${report.boa_num}');
					alert('해제 완료');
					//location.replace('Sta2.do?boa_num=${board.boa_num}'); 
				} 
			};
		</script>	
		
	</div>
</div>
<!-- 자유게시판 reportPage 끝 -->