$(document).ready(function()
{

/*
 * 전역변수, 초기화
 */
	var count; // 댓글 수
	defaultCommentListSort();
	
/*
 * 댓글 정렬 enum (0:인기순, 1:최신순)
 */
	var sort = Object.freeze({POPULAR:0, RECENT:1});
	var sortUrl = Object.freeze({POPULAR:'commentList_popular.do', RECENT:'commentList_recent.do'}); 
	
// 댓글 쓰기 제출 (동적 요소 : showWriteForm().#btn_writeComment)
// 동적 요소일 때 써야하는 함수 : $(document).on(이벤트종류, 속성의 아이디 또는 클래스명, 함수)
	$(document).on("click", "#btn_writeComment", function()
	{
		var pos_num = $("#pos_num").val();
		var poc_content = $("#poc_content").val();
		
		$.ajax({
			type:'post',
			data:{pos_num:pos_num, poc_content:poc_content},
			url:'writeComment.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == "needLogin"){
					alert("로그인이 필요한 서비스입니다.");
					setEmptyWriteForm();
					// location.replace('$(pageContext.request.contextPath)/WEB-INF/views/member/login.do');
				}
				else if($('#poc_content').val() == ""){
					alert("댓글 내용을 입력하세요");
					$("#poc_content").focus();
				}
				else if(data.result == "success"){
					alert("댓글 입력 성공");
					setEmptyWriteForm();
					defaultCommentListSort();
				}
				else{
					alert("댓글 등록 오류 발생");
				}
			},
			error:function(){
				alert("네트워크 오류 발생");
			}
		});
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
		var output = '';
		output += 	'<input type="hidden" name="poc_num" id="mpoc_num" value="' + poc_num + '">';
		output += 	'<input type="hidden" name="mem_num" id="mmem_num" value="' + mem_num + '">';
		output += '<div>';
		output += 	'<textarea id="mpoc_content" style="resize:none; width:400px; height:100px">' + poc_content + '</textarea>';
		output += '</div>';
		output += '<div>';
		output += 	'<input type="button" value="수정" id="btn_modifyOK">';
		output += 	'<input type="button" value="취소" id="btn_modifyCancel">';
		output += '</div>';
		// 이전에 이미 수정하던 댓글 폼은 삭제
		setRemoveModifyForm();
		// 수정 폼을 수정하고자 하는 댓글이 있는 id에 노출
		$(this).parents("#comment").append(output);
	});
	
// 댓글 수정 처리 (동적 요소 : #btn_modifyOK)
	$(document).on("click", "#btn_modifyOK", function()
	{
		var poc_num = $("#mpoc_num").val();
		var mem_num = $("#mmem_num").val();
		var poc_content = $("mpoc_content").val();
		
		$.ajax({
			url:'modifyComment.do',
			type:'post',
			data:{poc_num:poc_num, mem_num:mem_num, poc_content:poc_content},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == "needLogin")
				{
					alert("로그인이 필요한 서비스입니다");
					// location.replace("${pageContext.request.contextPath}/WEB-INF/views/member/login.do");	
				}
				else if($('#mpoc_content').val() == "")
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
	});
	
// 댓글 수정 취소 (동적 요소 : #btn_modifyCancel)
	$(document).on("click", "#btn_modifyCancel", function()
	{
		setRemoveModifyForm();
	});
	
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
});

/*
 * default로 설정한 댓글 리스트 호출 방법
 */
	function defaultCommentListSort()
	{
		// 임시 : 나중에 데이터베이스 수정하고 SORT.POPULAR로 바꿔줄 것
		selectCommentList($("#pos_num").val(), sort.RECENT, sortUrl.RECENT);
	}
	
/*
 * 댓글 쓰기 폼 숨기기
 */
	function setEmptyWriteForm()
	{
		$("#output_writeComment").empty();
	}
	
/*
 * 기존에 수정하던 댓글 수정 폼 삭제
 */
	function setRemoveModifyForm()
	{
		$('#form_modifyComment').remove();
	}
	
/*
 * 댓글 리스트 가져오기
 * 미구현 부분 : 추천, 비추천 버튼 이미지
 * 교훈 : 페이징 처리 하자
 */
	function selectCommentList(pos_num, sort_type, url)
	{
		$.ajax({
			type:'get',
			data:{pos_num:pos_num, sort_type:sort_type},
			url:url,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				// 댓글 수 출력
				$(".pos_comment").html(data.commentCount);
				// 댓글 리스트
				var list = data.commentList;
				$(list).each(function(index,item){
					// 댓글 목록 출력
					var output = '';
					output += '<div id="comment">';
					output += 	'<span>';
					output += 		item.mem_nick + " | " + item.poc_date;
					output += 	'</span>';
					output += 	'<p>';
					output += 		item.poc_content.replace(/</gi,'&lt;').replace(/>/gi,'&gt;')
					output += 	'</p>';
					// 로그인된 회원 번호와 댓글 작성자 번호가 같은 경우 수정,삭제 버튼 추가
					if(data.mem_num == item.mem_num)
					{
						output += '<span>';
						output += 	'<input type="button" data-num="' + item.poc_num + '" data-mem="' + item.mem_num + '" value="수정" id="btn_modifyComment">';
						output += 	'<input type="button" data-num="' + item.poc_num + '" data-mem="' + item.mem_num + '" value="삭제" id="btn_deleteComment">';
						output += '</span>';
					}
					output += '</div>';
					output += '<hr size="1" noshade="noshade">';
				});			
			// 기존의 댓글 리스트 html 삭제 (댓글이 많아지면 처리해야 할 게 많아지므로, 다른 방법 찾아보기)
			$('#output_commentList').empty();
			// 댓글 리스트 불러와서 html에 표시
			$('#output_commentList').append(output);
			},
			error:function(){
				alert("네트워크 오류");
			}
		});
	}
	
/*
 * <댓글 쓰기> 버튼 클릭하면, 작성 폼 노출 
 */
	function showWriteForm()
	{
		var output = '';
		output += '<textarea id="poc_content" style="resize:none; width:400px; height:100px"></textarea>';
		output += '<input type="button" id="btn_writeComment" value="등록">'
		$("#output_writeComment").append(output);
	}
