$(document).ready(function()
{
/*
 * 전역변수
 */
	var count;
	
/*
 * 초기 데이터 호출
 */
	selectCommentList($('#pos_num').val());
	
/*
 * 댓글 쓰기 폼 숨기기
 */
	function setDisplayWriteForm()
	{
		$("wrapper_writeComment").hide();
	}

/*
 * 댓글 폼 초기화
 */
	function initForm()
	{
		$("#poc_content").val("");
	}
	
/*
 * 댓글 수정 폼 초기화
 */
	function initModifyForm()
	{
		// $('.sub-item').show();
		$('#form_modifyComment').remove();
	}
	
/*
 * 댓글 수 가져오기
 */
	function selectCommentCount(data)
	{
		var count = data.count;
		var output;
		if(count==0)
		{
			output = "0개";
		}
		else
		{
			output = count + "개";
		}			
		//문서 객체에 추가
		$('#commentCount').text(output);
	}
	
/*
 * 댓글 리스트 가져오기
 * 미구현 부분 : 추천, 비추천 버튼 이미지
 * 교훈 : 페이징 처리 하자
 */
	function selectCommentList(pos_num)
	{
		$.ajax({
			type:'post',
			data:{pos_num:pos_num},
			url:'commentList.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var list = data.commentList;
				var output = ''; // '' 안쓰면 undefined 라고 뜸
				//댓글수 읽어 오기
				selectCommentCount(data);
				//댓글 목록 작업
				$(list).each(function(index,item){
					// 댓글의 추천 갯수
					var poc_fav = 0;
					if(item.poc_fav != 0){
						poc_fav = item.poc_fav;
					}
					// 댓글 목록 출력
					output += '<div id="comment">';
					output += 	'<span>';
					output += 		item.mem_nick + " | " + item.poc_date;
					output += 	'</span>';
					output += 	'<p>';
					output += 		item.poc_content.replace(/</gi,'&lt;').replace(/>/gi,'&gt;')
					output += 	'</p>';
					// 로그인된 회원 번호와 댓글 작성자 번호가 같은 경우
					if($("#mem_num").val() == item.mem_num){
						output += '<span>';
						output += 	'<input type="button" data-num="' + item.poc_num + '" data-mem="' + item.mem_num + '" value="수정" id="btn_modifyComment">';
						output += 	'<input type="button" data-num="' + item.poc_num + '" data-mem="' + item.mem_num + '" value="삭제" id="btn_deleteComment">';
						output += '</span>';
					}
					output += '</div>';
					output += '<hr size="1" noshade="noshade">';
					/*
					output += 	'<span>';
					if($("#mem_num").val() != item.click_favUp){
						output += '<img src="../resources/images/upArrow.png">';
					}
					else{
						// 추천수 올리기 이미지 (누른 상태)
					}
					output += item.poc_fav;
					if($("#mem_num").val() != item.click_favDown){
						output += '<img src="../resources/images/downArrow.png">';
					}
					else{
						// 추천수 내리기 이미지 (누른 상태)
					}
					output += 	'</span>';
					*/
					//문서 객체에 추가
				});
				
				$('#commentList').empty();
				$('#commentList').append(output);
				// alert("댓글 리스트 가져오기 완료 " + $(list).length + "개");
			},
			error:function(){
				alert("네트워크 오류");
			}
		});
	}
	
/*
 * 댓글 쓰기
 * 오류부분 : 로그인 리다이렉트 안됌
 */
	$("#form_writeComment").submit(function(event)
	{
		// form 데이터 직렬화
		var data = $(this).serialize();
		// 통신
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
				else if($('#poc_content').val() == ""){
					alert("댓글 내용을 입력하세요");
					$("#poc_content").focus();
				}
				else if(data.result == "success"){
					alert("댓글 입력 성공");
					initForm();
					selectCommentList($('#pos_num').val());
				}
				else{
					alert("댓글 등록 오류 발생");
				}
			},
			error:function(){
				alert("네트워크 오류 발생");
			}
		});
		// submit 이벤트 제거
		event.preventDefault();
	});	

/*
 * 댓글 수정
 */
// 댓글 수정 클릭 (동적 요소 : #commentList.#comment.#btn_modifyComment)
// 동적 요소일 때 써야하는 함수 : $(document).on(이벤트종류, 속성의 아이디 또는 클래스명, 함수)
	$(document).on("click", "#btn_modifyComment", function()
	{
		var poc_num = $(this).attr('data-num');
		var mem_num = $(this).attr('data-mem');
		// <p> 태그로 감싸진 #poc_content 내용물
		var poc_content = $(this).parent().parent().find('p').html().replace(/<br>/gi,'\n'); //g:지정문자열 모두, i:대소문자 무시
		// 댓글 수정폼 UI
		var output = '';
		output += '<form id="form_modifyComment">';
		output += 	'<input type="hidden" name="poc_num" id="mpoc_num" value="' + poc_num + '">';
		output += 	'<input type="hidden" name="mem_num" id="mmem_num" value="' + mem_num + '">';
		output += 	'<textarea name="poc_content" id="mpoc_content" style="resize:none; width:400px; height:100px">' + poc_content + '</textarea>'
		output += 	'<div>';
		output += 		'<input type="submit" value="수정">';
		output += 		'<input type="button" value="취소" id="btn_modifyCancel">';
		output += 	'</div>';
		output += '</form>';
		// 댓글쓰기 폼 숨기기
		// setDisplayWriteForm()
		// 이전에 이미 수정하던 댓글이 있을 경우, 수정버튼을 클릭했을 때
		initModifyForm();
		// 수정 폼을 수정하고자 하는 댓글이 있는 id에 노출
		$(this).parents("#comment").append(output);
	});
	
// 댓글 수정 처리 (동적 요소 : #form_modifyComment.submit 버튼)
	$(document).on("submit", "#form_modifyComment", function(event)
	{
		// 수정 form 데이터 직렬화
		var data = $(this).serialize();
		// 수정 처리
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
					location.replace("${pageContext.request.contextPath}/WEB-INF/views/member/login.do");	
				}
				else if($('#mpoc_content').val() == "")
				{
					alert("댓글 내용을 입력하세요");
					$('#mpoc_content').focus();
				}
				else if(data.result == "success")
				{
					$("#form_modifyComment").parent().find("p").html($('#mpoc_content').val().replace(/</g,'&lt;').replace(/>/g,'&gt;'));
					// initModifyForm();
				}
				else if(data.result == "notMatchUser")
				{
					alert("다른 회원이 작성한 글은 수정할 수 없습니다");
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
		// submit 이벤트 제거
		event.preventDefault();
	});
	
	// 댓글 수정 취소 (동적 요소 : #form_modifyComment.#btn_modifyCancel)
	$(document).on("click", "#btn_modifyCancel", function()
	{
		// initModifyForm();
	});
	
/*
 * 댓글 삭제
 */	
// (동적 요소 : #commentList.#comment.#btn_deleteComment)
	$(document).on("click", "#btn_deleteComment", function()
	{
		// 댓글 번호, 작성자 회원 번호 가져오기
		var poc_num = $(this).attr('data-num');
		var mem_num = $(this).attr('data-mem');
		// 삭제 처리
		$.ajax({
			type:'post',
			url:'deleteComment.do',
			data:{poc_num:poc_num, mem_num:mem_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == "needLogin")
				{
					alert("로그인이 필요한 서비스입니다");
					location.replace($(pageContext.request.contextPath) + "/WEB-INF/views/member/login.do");	
				}
				else if(data.result == "success")
				{
					alert("댓글 삭제 완료");
					selectCommentList($('#pos_num').val());
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