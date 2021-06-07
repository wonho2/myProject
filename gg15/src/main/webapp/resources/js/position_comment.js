$(document).ready(function()
{

/*
 * 전역변수, 초기화
 */
	var currentPage;
	var count;
	var rowCount;
	var sort_type;
	var sort_url;
	defaultCommentListSort();
	
/*
 * 상수
 */
	const sort_POPULAR = 0;
	const sort_RECENT = 1;
	const sortUrl_POPULAR = 'commentList_popular.do';
	const sortUrl_RECENT = 'commentList_recent.do';
	
/*
 * default로 설정한 댓글 리스트 호출 방법
 */
	function defaultCommentListSort()
	{
		// 임시 : 나중에 데이터베이스 수정하고 SORT.POPULAR로 바꿔줄 것
		sort_type = sort_RECENT;
		sort_url = sortUrl_RECENT;
		selectCommentList(1, $("#pos_num").val(), sort_type, sort_url);
	}
	
/*
 * 댓글 리스트 가져오기 (함수 호출이 안됌 이거)
 * 미구현 부분 : 추천, 비추천 버튼 이미지, 추천수
 * 교훈 : 페이징 처리 하자
 */
	function selectCommentList(pageNum, pos_num, sort_type, sort_url)
	{
		// 현재 페이지 저장
		currentPage = pageNum;
		// 1페이지 호출 시, 해당 id 내부 내용물 제거
		if(pageNum == 1)
		{
			$("#output_commentList").empty();
		}
		
		$.ajax({
			type:'get',
			data:{pageNum:pageNum, pos_num:pos_num, sort_type:sort_type},
			url:sort_url,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				// 댓글 수 출력
				$(".commentCount").html(data.commentCount);
				// 한 페이지에 보여질 댓글 수 출력
				rowCount = data.rowCount;
				// 댓글 리스트
				var list = data.commentList;
				$(list).each(function(index,item){
					// 댓글 목록 출력
					var output = '<div class="comment">';
					output += 	'<span>';
					output += 		item.mem_nick + " | " + item.poc_date;
					output += 	'</span>';
					output += 	'<p>';
					output += 		item.poc_content.replace(/</gi,'&lt;').replace(/>/gi,'&gt;')
					output += 	'</p>';
					// 로그인된 회원 번호와 댓글 작성자 번호가 같은 경우 수정,삭제 버튼 추가
					if($("mem_num").val() == item.mem_num)
					{
						output += '<span>';
						output += 	'<input type="button" data-num="' + item.poc_num + '" data-mem="' + item.mem_num + '" value="수정" id="btn_modifyComment">';
						output += 	'<input type="button" data-num="' + item.poc_num + '" data-mem="' + item.mem_num + '" value="삭제" id="btn_deleteComment">';
						output += '</span>';
					}
					// 추천 버튼과 추천 수
					output += 	'<div>';
					if(item.click_num==0 || $("#mem_num").val()!=item.click_num)
					{
						output += ' <img class="btn_commentFav" src="../resources/images/heart01.png" data-num="'+item.poc_num+'"> <span class="commentFavCount">'+poc_fav+'</span>';
					}
					else
					{
						output += ' <img class="btn_commentFav" src="../resources/images/heart02.png" data-num="'+item.poc_num+'"> <span class="commentFavCount">'+poc_fav+'</span>';
					}
					output += 	'</div>';
					output += '</div>';
					output += '<hr size="1" noshade="noshade">';
					// 댓글 리스트 불러와서 html에 표시
					$('#output_commentList').append(output);
				});
				// paging Button 처리
				if(currentPage >= Math.ceil(count/rowCount))
				{
					$("#btn_paging").hide();
				}
				else
				{
					$("#btn_paging").show();
				}
				
			},
			error:function(){
				alert("네트워크 오류");
			}
		});
	}
	
/*
 * 다음 댓글 보기 버튼 클릭시 데이터 추가
 */
	$("#btn_paging input").click(function(){
		var pageNum = currentPage + 1;
		selectCommentList(pageNum, $("#pos_num").val(), sort_type, sort_url);
	});
	
/*
 * 댓글 쓰기 제출 (동적 요소 : #form_writeComment)
 * 동적 요소일 때 써야하는 함수 : $(document).on(이벤트종류, 속성의 아이디 또는 클래스명, 함수)
 */
	$("#form_writeComment").submit(function(event)
	{
		// 데이터 직렬화
		var data = $(this).serialize();
		// ajax 통신
		$.ajax({
			type:'post',
			data:data,
			url:'writeComment.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == "needLogin"){
					alert("로그인이 필요한 서비스입니다.");
					// location.replace('$(pageContext.request.contextPath)/WEB-INF/views/member/login.do');
				}
				else if($.trim($("#poc_content").val()) == ""){
					alert("댓글 내용을 입력하세요");
					$("#poc_content").focus();
				}
				else if(data.result == "success"){
					alert("댓글 입력 성공");
					defaultCommentListSort();
					// 최신순으로 보여주기로 고치기
					$("#poc_content").val("");
				}
				else{
					alert("댓글 등록 오류 발생");
				}
			},
			error:function(){
				alert("네트워크 오류 발생");
			}
		});
		// 기본 이벤트 제거
		event.preventDefault();
	});	

/*
 * 댓글 수정
 */
// 댓글 수정 클릭 (동적 요소 : selectCommentList().#comment.#btn_modifyComment)
	$(document).on("click", "#btn_modifyComment", function()
	{
		var poc_num = $(this).attr('data-num');
		var mem_num = $(this).attr('data-mem');
		// <p> 태그로 감싸진 #poc_content 내용물
		var poc_content = $(this).parent().parent().find('p').html().replace(/<br>/gi,'\n'); //g:지정문자열 모두, i:대소문자 무시
		
		// 댓글 수정폼 UI
		var output = '<div>';
		output += 	'<form id="form_modifyComment">';
		output += 		'<input type="hidden" id="mpos_num" value="' + poc_num + '">';
		output += 		'<input type="hidden" id="mmem_num" value="' + mem_num + '">';
		output += 		'<textarea id="poc_content" style="resize:none; width:400px; height:100px"></textarea>';
		output += 		'<div>';
		output += 			'<input type="submit" value="수정">';
		output += 			'<input type="button" value="취소" id="btn_modifyCancel">';		
		output += 		'</div>';
		output += 	'</form>';
		output += '</div>';
		// 이전에 이미 수정하던 댓글 폼은 삭제
		setRemoveModifyForm();
		// 수정 폼을 수정하고자 하는 댓글이 있는 id에 노출
		$(this).parents("#comment").append(output);
	});
	
// 댓글 수정 처리 (동적 요소 : #form_modifyComment)
	$(document).on("submit", "#form_modifyComment", function(event)
	{
		var data = $(this).serialize();
		
		$.ajax({
			url:'modifyComment.do',
			type:'post',
			data:data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == "needLogin")
				{
					alert("로그인이 필요한 서비스입니다");
					// location.replace("${pageContext.request.contextPath}/WEB-INF/views/member/login.do");	
				}
				else if($.trim($("#mpoc_content").val()) == "")
				{
					alert("댓글 내용을 입력하세요");
					$('#mpoc_content').focus();
				}
				else if(data.result == "success")
				{
					alert("댓글 수정 성공");
					// $("#form_modifyComment").parent() == 댓글 리스트의 #comment 개별 html 요소
					$('#mpoc_content').val().replace(/</g,'&lt;').replace(/>/g,'&gt;');
					setRemoveModifyForm();
				}
				else if(data.result == "notMatchUser")
				{
					alert("다른 회원이 작성한 댓글은 수정할 수 없습니다");
				}
				else
				{
					alert("댓글 수정 오류 발생");
				}
			},
			error:function(){
				alert("네트워크 오류 발생");
			}
		});
		// 기본 이벤트 제거
		event.preventDefault();
	});
	
// 댓글 수정 취소 (동적 요소 : #btn_modifyCancel)
	$(document).on("click", "#btn_modifyCancel", function()
	{
		setRemoveModifyForm();
	});
	
 // 기존에 수정하던 댓글 수정 폼 삭제
	function setRemoveModifyForm()
	{
		$('#form_modifyComment').remove();
	}
	
/*
 * 댓글 삭제
 */	
// (동적 요소 : #commentList.#comment.#btn_deleteComment)
	$(document).on("click", "#btn_deleteComment", function()
	{

		var poc_num = $(this).attr('data-num');
		var mem_num = $(this).attr('data-mem');

		$.ajax({
			type:'get',
			url:'deleteComment.do',
			data:{poc_num:poc_num, mem_num:mem_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == "needLogin")
				{
					alert("로그인이 필요한 서비스입니다");
					// location.replace($(pageContext.request.contextPath) + "/WEB-INF/views/member/login.do");	
				}
				else if(data.result == "success")
				{
					alert("댓글 삭제 완료");
					defaultCommentListSort();
					// 최신순으로 보여주는걸로 고치기
				}
				else if(data.result == "notMatchUser")
				{
					alert("다른 회원이 작성한 댓글은 삭제할 수 없습니다");
				}
				else
				{
					alert("댓글 삭제 오류 발생");
				}
			},
			error:function(){
				alert("네트워크 오류 발생");
			}
		});
	});
	
/*
 * 댓글 추천/비추천
 */
// (동적 요소 : #commentList..btn_commentFav)
	$(document).on("click", ".btn_commentFav", function()
	{
		var btn_fav = $(this);
		
		$.ajax({
			type:'get',
			url:'commentFav.do',
			data:{poc_num:btn_fav.attr("data-num")},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(){
				if(data.result == "needLogin"){
					alert("로그인이 필요한 서비스 입니다");
					// 로그인 페이지로 이동
				}
				else if(data.result == "success"){
					alert("댓글 추천 완료");
					displayFav(data, btn_fav);
				}
				else{
					alert("댓글 추천 오류 발생");
				}
			},
			error:function(){
				alert("네트워크 오류 발생");
			}
		});
	});
	
	// 추천 버튼 아이콘 표시
	function displayFav(data, btn_fav)
	{
		var status = data.status;
		var count = data.count;
		var output;
		if(status == "noFav")
		{
			output = "../resources/images/heart01.png";
		}
		else
		{
			output = "../resources/images/heart02.png";
		}
		btn_fav.attr("src", output);
		btn_fav.parent().find(".commentFavCount").text(count);
	}
});


