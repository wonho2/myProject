$(document).ready(function()
{
/*
 * 추천 버튼 클릭
 */
	$('#btn_fav').click(function()
	{
		var pos_num = $("#pos_num").val();
		
		$.ajax({
			type:'get',
			data:{pos_num:pos_num},
			url:'clickFav.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == "needLogin"){
					alert("로그인이 필요한 서비스 입니다");
					// 로그인 페이지로 이동
				}
				else if(data.result == "success - favUp"){
					alert("추천 완료");
					updatePos_fav(data.favCount);
					// 추천된 버튼으로 이미지 바꾸기
				}
				else if(data.result == "success - favCancel"){
					alert("추천을 취소했습니다");
					updatePos_fav(data.favCount);
					// 추천되지 않은 버튼으로 이미지 바꾸기
				}
				else{
					alert("게시물 추천 오류 발생");
				}
			},
			error:function(){
				alert("네트워크 오류");
			}
		});
	});
});

/*
 * 추천수 변경
 */
function updatePos_fav(favCount)
{
	$(".favCount").html(favCount);
}