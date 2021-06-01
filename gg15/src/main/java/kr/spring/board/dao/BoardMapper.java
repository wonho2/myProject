package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;
import kr.spring.board.vo.board_replyVO;

public interface BoardMapper {
	
	//총 게시물 수
	public int selectRowCount();
	
	//자유게시판 글 목록
	public List<BoardVO> selectList(Map<String,Object> map);	

	//글쓰기
	@Insert("INSERT INTO board (boa_num,boa_cate,boa_title,boa_uploadfile,boa_filename,boa_content,boa_mode,mem_num,boa_date) "
		+ "VALUES (board_seq.nextval,#{boa_cate},#{boa_title},#{boa_uploadfile},#{boa_filename},#{boa_content},#{boa_mode},#{mem_num},SYSDATE)")
	public void insertBoard(BoardVO vo);
	
	//조회수 증가
	@Update("UPDATE board SET boa_hit=boa_hit+1 WHERE boa_num=#{boa_num}")
	public void updateHit(Integer boa_num);
	
	//글 상세 페이지
	@Select("SELECT * FROM board b JOIN member m ON b.mem_num=m.mem_num WHERE b.boa_num=#{boa_num}")
	public BoardVO selectBoard(Integer boardNum);
	 
	//글 수정
	public void updateBoard(BoardVO boardVO);
		
	//글 삭제
	@Delete("DELETE FROM board WHERE boa_num=#{boa_num}")
	public void deleteBoard(Integer boa_num);	
	
	
	//=================댓글================//
	public List<board_replyVO> selectBoardComment(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM board_reply WHERE boa_num=#{boa_num}")
	public int selectBoardCommentCount(Map<String,Object> map);
	@Insert("INSERT INTO board_reply (bor_num,bor_content,boa_num,mem_num) VALUES (board_reply_seq.nextval,#{bor_content},#{boa_num},#{mem_num})")
	public void insertBoardComment(board_replyVO boardReply);
	@Update("UPDATE board_reply SET bor_content=#{bor_content} WHERE bor_num=#{bor_num}")
	public void updateBoardComment(board_replyVO boardReply);
	@Delete("DELETE FROM board_reply WHERE bor_num=#{bor_num}")
	public void deleteBoardComment(Integer bor_num);
	//부모글 삭제시 댓글이 존재하s면 부모글 삭제전 댓글 삭제 
	@Delete("DELETE FROM board_reply WHERE boa_num=#{boa_num}")
	public void deleteBoardCommentByBoardNum(Integer boa_num);

}