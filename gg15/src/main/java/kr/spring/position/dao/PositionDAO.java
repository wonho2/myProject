package kr.spring.position.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.position.vo.PositionCommentVO;
import kr.spring.position.vo.PositionVO;

public interface PositionDAO
{
	// 총 게시물 수
	@Select("SELECT COUNT(*) FROM position")
	public int selectBoardCount();

	// 게시물 목록
	public List<PositionVO> selectBoardList(Map<String, Object> map);

	// 글쓰기
	@Insert("INSERT INTO position(pos_num, pos_type, mem_num, pos_title, pos_content, pos_uploadfile, pos_filename) "
			+ "VALUES(position_seq.nextval, #{pos_type}, #{mem_num}, #{pos_title}, #{pos_content}, #{pos_uploadfile}, #{pos_filename})")
	public void insertBoard(PositionVO vo);

	// 게시물 상세 페이지
	public PositionVO selectBoard(int boardNum);

	// 게시물 수정
	public void updateBoard(PositionVO vo);

	// 게시물 삭제
	@Delete("DELETE FROM position WHERE pos_num = #{boardNum}")
	public void deleteBoard(int boardNum);

	// 해당 게시물의 조회수 증가
	@Update("UPDATE position SET pos_view = pos_view+1 WHERE pos_num = #{boardNum}")
	public void updateView(int boardNum);

	//=================댓글================//
	public List<PositionCommentVO> selectListReply(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM position_comment WHERE pos_num=#{pos_num}")
	public int selectRowCountReply(Map<String,Object> map);
	@Insert("INSERT INTO position_comment (poc_num,poc_content,pos_num,mem_num) VALUES (position_comment_seq.nextval,#{poc_content},#{pos_num},#{mem_num})")
	public void insertReply(PositionCommentVO boardReply);
	@Update("UPDATE position_comment SET poc_content=#{poc_content} WHERE poc_num=#{poc_num}")
	public void updateReply(PositionCommentVO boardReply);
	@Delete("DELETE FROM position_comment WHERE poc_num=#{poc_num}")
	public void deleteReply(Integer poc_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제
	@Delete("DELETE FROM position_comment WHERE pos_num=#{pos_num}")
	public void deleteReplyByBoardNum(Integer pos_num);


}
