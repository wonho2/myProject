function pos_delete()
{
	var choice = window.confirm("해당 게시물을 삭제하시겠습니까?");
	if(choice)
	{
		location.replace('delete.do?num=${positionVO.pos_num}');
	}
}