package kr.spring.position.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.position.vo.PositionVO;

public interface PositionDAO
{
	// 총 게시물 수
	@Select("SELECT COUNT(*) FROM position")
	public int selectBoardCount();
	
	// 게시물 목록
	public List<PositionVO> selectBoardList(Map<String, Object> map);
	
	// 글쓰기
	@Insert("INSERT INTO position(pos_num, pos_type, mem_num, pos_title, pos_content, pos_uploadfile) "
			+ "VALUES(position_seq.nextval, #{pos_type}, #{mem_num}, #{pos_title}, #{pos_content}, #{pos_uploadfile})")
	public void insertBoard(PositionVO vo);
	
	// 게시물 상세 페이지
	@Select("SELECT * FROM WHERE pos_num = #{boardNum}")
	public PositionVO selectBoard(int boardNum);
	
	// 게시물 수정
	public void updateBoard(PositionVO vo);
	
	// 게시물 삭제
	@Delete("DELETE FROM position WHERE pos_num = #{boardNum}")
	public void deleteBoard(int boardNum);
	
	// 해당 게시물의 조회수 증가
	@Update("UPDATE position SET pos_view = pos_view+1 WHERE pos_num = #{boardNum}")
	public void updateView(int boardNum);
	
	// 해당 게시물의 추천수 증가
	@Update("UPDATE position SET pos_fav = pos_fav+1 WHERE pos_num = #{boardNum}")
	public void updateFavUp(int boardNum);
	
	// 해당 게시물의 추천수 감소
	@Update("UPDATE position SET pos_fav = pos_fav-1 WHERE pos_num = #{boardNum}")
	public void updateFavDown(int boardNum);
	
	// 해당 게시물의 댓글수 증가
	@Update("UPDATE position SET pos_comment = pos_comment+1 WHERE pos_num = #{boardNum}")
	public void updateCommentUp(int boardNum);
	
	// 해당 게시물의 댓글수 감소
	@Update("UPDATE position SET pos_comment = pos_comment-1 WHERE pos_num = #{boardNum}")
	public void updateCommentDown(int boardNum);
}
