package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardVO;
import kr.spring.board.vo.BoardReplyVO;
import kr.spring.board.vo.BoardReportVO;
import kr.spring.board.vo.BoardStatusVO;

public interface BoardMapper {

	//총 게시물 수
	public int selectRowCount(Map<String,Object> map);

	//자유게시판 글 목록
	public List<BoardVO> selectList(Map<String,Object> map);	

	//글쓰기
	@Insert("INSERT INTO board (boa_num,boa_cate,boa_title,boa_uploadfile,boa_filename,boa_content,boa_mode,mem_num,boa_date) "
			+ "VALUES (board_seq.nextval,#{boa_cate},#{boa_title},#{boa_uploadfile},#{boa_filename},#{boa_content},#{boa_mode},#{mem_num},SYSDATE)")
	public void insertBoard(BoardVO vo);

	//조회수 증가
	@Update("UPDATE board SET boa_hit=boa_hit+1 WHERE boa_num=#{boa_num}")
	public void updateHit(int boa_num);

	//글 상세 페이지
	@Select("SELECT * FROM board b JOIN member_detail m ON b.mem_num=m.mem_num WHERE b.boa_num=#{boa_num}")
	public BoardVO selectBoard(Integer boardNum);

	//글 수정
	public void updateBoard(BoardVO boardVO);

	//글 삭제
	@Delete("DELETE FROM board WHERE boa_num=#{boa_num}")
	public void deleteBoard(Integer boa_num);	

	//=================댓글================//
	//댓글 리스트
	public List<BoardReplyVO> selectBoardComment(Map<String,Object> map);
	//댓글 갯수
	@Select("SELECT COUNT(*) FROM board_reply WHERE boa_num=#{boa_num}")
	public int selectBoardCommentCount(Map<String,Object> map);
	//댓글 작성
	@Insert("INSERT INTO board_reply (bor_num,bor_content,boa_num,mem_num) VALUES (board_reply_seq.nextval,#{bor_content},#{boa_num},#{mem_num})")
	public void insertBoardComment(BoardReplyVO boardReply);
	//댓글 수정
	@Update("UPDATE board_reply SET bor_content=#{bor_content} WHERE bor_num=#{bor_num}")
	public void updateBoardComment(BoardReplyVO boardReply);
	//댓글 삭제
	@Delete("DELETE FROM board_reply WHERE bor_num=#{bor_num}")
	public void deleteBoardComment(Integer bor_num);
	//부모글 삭제시 댓글이 존재하면 부모글 삭제전 댓글 삭제 
	@Delete("DELETE FROM board_reply WHERE boa_num=#{boa_num}")
	public void deleteBoardCommentByBoardNum(Integer boa_num);
 
	//=================게시글 좋아요=================//
	@Select("SELECT * from boa_fav where boa_num=#{boa_num} and mem_num=#{mem_num}")
	public BoardFavVO selectFav(BoardFavVO fav);
	@Select("SELECT COUNT(*) from boa_fav where boa_num=#{boa_num}")
	public int selectFavCount(Integer boa_num);
	@Insert("INSERT INTO boa_fav (bof_num,boa_num,mem_num) VALUES (board_fav_seq.nextval,#{boa_num},#{mem_num})")
	public void insertFav(BoardFavVO boardFav);
	@Delete("DELETE FROM boa_fav WHERE bof_num=#{bof_num}")
	public void deleteFav(Integer bof_num);
	@Delete("DELETE FROM boa_fav WHERE boa_num=#{boa_num}")
	public void deleteFavByBoaNum(Integer boa_num);
	
	
	
	//=================신고글================//
	//글쓰기
	@Insert("INSERT INTO boa_report (bop_num,boa_num,bop_content,mem_num,bop_date) "
			+ "VALUES (board_report_seq.nextval,#{boa_num},#{bop_content},#{mem_num},SYSDATE)")
	public void insertReport(BoardReportVO vo);
	
	//신고 글 목록
	public List<BoardReportVO> reportList(Map<String,Object> map);	
	
	//신고 글 갯수
	public int selectRowreport();

	//글 상세 페이지
	public BoardReportVO selectReport(Integer report);
	
	//부모글 삭제시 신고글이 존재하면 부모글 삭제전 신고글 삭제 
	@Delete("DELETE FROM boa_report WHERE boa_num=#{boa_num}")
	public void deleteReportByBoardNum(Integer boa_num);
	
//==================================================================//
	
	//게시글 상태 값 보통
	@Update("UPDATE board SET boa_status=1 WHERE boa_num=#{boa_num}")
	public void updateState1(Integer boa_num);
	//게시글 상태 값 차단
	@Update("UPDATE board SET boa_status=2 WHERE boa_num=#{boa_num}")
	public void updateState2(Integer boa_num);
	
	
/*	
	//=================게시글 차단=================// 좋아요 따라해보자
	@Select("SELECT * from boa_sta where boa_num=#{boa_num} and mem_num=#{mem_num}")
	public BoardStatusVO selectSta(BoardStatusVO sta);
	@Insert("INSERT INTO boa_sta (bos_num,boa_num,mem_num) VALUES (board_sta_seq.nextval,#{boa_num},#{mem_num})")
	public void insertSta(BoardStatusVO boardSta);
	@Delete("DELETE FROM boa_sta WHERE bos_num=#{bos_num}")
	public void deleteSta(Integer bos_num);
	@Delete("DELETE FROM boa_sta WHERE boa_num=#{boa_num}")
	public void deleteStaByBoaNum(Integer boa_num);
	*/

	
	/*
	//=================신고글================// 댓글 따라 해보자
	//신고 리스트
	public List<BoardReportVO> selectReport(Map<String,Object> map);
	//신고글 갯수
	@Select("SELECT COUNT(*) FROM boa_report WHERE boa_num=#{boa_num}")
	public int selectReportCount(Map<String,Object> map);
	//신고글 작성
	@Insert("INSERT INTO boa_report (bop_num,bop_content,boa_num,mem_num) VALUES (boa_report_seq.nextval,#{bop_content},#{boa_num},#{mem_num})")
	public void insertReport(BoardReportVO boardReply);
	//부모글 삭제시 신고글이 존재하면 부모글 삭제전 신고글 삭제 
	@Delete("DELETE FROM boa_report WHERE boa_num=#{boa_num}")
	public void deleteReportByBoardNum(Integer boa_num);
	 
	//=================게시글 차단=================// 좋아요 따라해보자
	@Select("SELECT * from boa_sta where boa_num=#{boa_num} and mem_num=#{mem_num}")
	public BoardStatusVO selectSta(BoardStatusVO sta);
	@Insert("INSERT INTO boa_sta (bos_num,boa_num,mem_num) VALUES (board_sta_seq.nextval,#{boa_num},#{mem_num})")
	public void insertSta(BoardStatusVO boardSta);
	@Delete("DELETE FROM boa_sta WHERE bos_num=#{bos_num}")
	public void deleteSta(Integer bos_num);
	@Delete("DELETE FROM boa_sta WHERE boa_num=#{boa_num}")
	public void deleteStaByBoaNum(Integer boa_num);
	 */
}