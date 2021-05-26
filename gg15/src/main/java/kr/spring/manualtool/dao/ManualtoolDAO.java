package kr.spring.manualtool.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.manualtool.vo.ManualtoolVO;
import kr.spring.manualtool.vo.ManualtoolCommentVO;
import kr.spring.manualtool.vo.ManualtoolVO;

public interface ManualtoolDAO {
	//글쓰기
	@Insert("INSERT INTO manualtool (man_num, mem_num, man_champion, man_season, man_title, man_content, man_uploadfile) "
			+ "VALUES (manualtool_seq.nextval, #{man_num}, #{mem_num}, #{man_champion}, #{man_season}, #{man_title}, #{man_content} , #{man_uploadfile})")
	public void insertBoard(ManualtoolVO vo);

	//총 레코드 수
	public int selectBoardCount();

	//글 목록
	public List<ManualtoolVO> selectBoardList(Map<String, Object> map);

	//글 상세 페이지
	@Select("SELECT * FROM WHERE man_num = #{boardNum}")
	public ManualtoolVO selectBoard(int boardNum);
	
	//조회수 증가
	@Update("UPDATE manualtool SET man_hit = man_hit+1 WHERE man_num = #{boardNum}")
	public void updateHit(int boardNum);

	//추천수 증가
	@Update("UPDATE manualtool SET man_fav = man_fav+1 WHERE man_num = #{boardNum}")
	public void updateFavUp(int boardNum);

	//추천수 감소
	@Update("UPDATE manualtool SET man_fav = man_fav-1 WHERE man_num = #{boardNum}")
	public void updateFavDown(int boardNum);

	//댓글수 증가
	@Update("UPDATE manualtool SET man_comment = man_comment+1 WHERE man_num = #{boardNum}")
	public void updateCommentUp(int boardNum);

	//댓글수 감소
	@Update("UPDATE manualtool SET man_comment = man_comment-1 WHERE man_num = #{boardNum}")
	public void updateCommentDown(int boardNum);

	//글 수정
	public void updateBoard(ManualtoolVO vo);

	//글 삭제
	@Delete("DELETE FROM manualtool WHERE man_num=#{man_num}")
	public void deleteBoard(int boardNum);
	
	//=====댓글=====
	public List<ManualtoolCommentVO> selectListReply(Map<String, Object> map);
	@Select("SELECT COUNT(*) FROM manualtool_reply WHERE man_num=#{man_num}")
	public int selectRowCountReply(Map<String, Object> map);
	@Insert("INSERT INTO manualtool_reply (mar_num, mar_content, man_num, mem_num) VALUES (manualtool_reply_seq.nextval, #{mar_content}, #{man_num}, #{mem_num})")
	public void insertReply(ManualtoolCommentVO boardReply);
	@Update("UPDATE manualtool_reply SET mar_content=#{mar_content} WHERE mar_num=#{mar_num}")
	public void updateReply(ManualtoolCommentVO boardReply);
	@Delete("DELETE FROM manualtool_reply WHERE mar_num=#{mar_num}")
	public void deleteReply(Integer mar_num);

	
}
