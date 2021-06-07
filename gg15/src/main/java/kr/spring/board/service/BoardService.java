package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardVO;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReplyVO;
import kr.spring.board.vo.BoardReportVO;
import kr.spring.board.vo.BoardStatusVO;

@Service("boardService")
public class BoardService {
 
	//의존 관계 설정
	@Resource
	private BoardMapper boardMapper;


	//글 목록
	public List<BoardVO> selectList(Map<String,Object> map){
		return boardMapper.selectList(map);
	}

	//총 게시물 수
	public int selectRowCount(Map<String,Object> map) {
		return boardMapper.selectRowCount(map);
	}
	//카테고리별  게시물 수
/*		public int selectCateCount(BoardCate cate)
		{
			switch(cate)
			{
				case All :
					return BoardMapper.selectRowCount();
				case gaming_machine :
					return BoardMapper.selectCateCount("게이밍 기기");
				case game_talk :
					return BoardMapper.selectCateCount("게임 이야기");
				case Discode :
					return BoardMapper.selectCateCount("디스코드 홍보");
				case Tier :
					return BoardMapper.selectCateCount("티어별 게시판");
				case Champion :
					return BoardMapper.selectCateCount("챔피언 게시판");
				case Humor :
					return BoardMapper.selectCateCount("유머 게시판");
				case img_video :
					return BoardMapper.selectCateCount("사진/비디오");
				case art :
					return BoardMapper.selectCateCount("팬아트");
				default :
					return -1;
			}
		}
	*/	
	//글쓰기
	public void insertBoard(BoardVO vo) {
		boardMapper.insertBoard(vo);
	}

	//글 상세 페이지
	public BoardVO selectBoard(Integer boardNum) {
		return boardMapper.selectBoard(boardNum);
	}

	//글 수정
	public void updateBoard(BoardVO boardVO) {
		boardMapper.updateBoard(boardVO);
	}

	//글 삭제
	public void deleteBoard(Integer boa_num) {
		//게시글 좋아요를 먼저 삭제
		boardMapper.deleteFavByBoaNum(boa_num);
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		boardMapper.deleteBoardCommentByBoardNum(boa_num);
		boardMapper.deleteBoard(boa_num);
	}

	//조회수 증가
	public void updateHit(Integer boa_num) {
		boardMapper.updateHit(boa_num);
	}

	//좋아요 증가
	//public void updateFavUp(Integer boa_num) {
	//	//boardMapper.updateFavUp(boa_num);
	//}

	//좋아요 감소
	//public void updateFavDown(Integer boa_num) {
	//	//boardMapper.updateFavDown(boa_num);
	//}
	
	//댓글
	public List<BoardReplyVO> selectBoardComment(Map<String, Object> map) {
		return boardMapper.selectBoardComment(map);
	}
	//댓글 갯수
	public int selectBoardCommentCount(Map<String, Object> map) {
		return boardMapper.selectBoardCommentCount(map);
	}
	//댓글 쓰기
	public void insertBoardComment(BoardReplyVO board_reply) {
		boardMapper.insertBoardComment(board_reply);
	}
	//댓글 수정
	public void updateBoardComment(BoardReplyVO board_reply) {
		boardMapper.updateBoardComment(board_reply);
	}
	//댓글 삭제
	public void deleteBoardComment(Integer bor_num) {
		boardMapper.deleteBoardComment(bor_num);
	}	
	//=============게시글 좋아요==================//
	public BoardFavVO selectFav(BoardFavVO fav) {
		return boardMapper.selectFav(fav);
	}
	public int selectFavCount(Integer boa_num) {
		return boardMapper.selectFavCount(boa_num);
	}
	public void insertFav(BoardFavVO boardFav) {
		boardMapper.insertFav(boardFav);
	}
	public void deleteFav(Integer bof_num) {
		boardMapper.deleteFav(bof_num);
	}
	
//====================================================================//
	
	//신고글 목록
	public List<BoardReportVO> reportList(Map<String,Object> map){
			return boardMapper.reportList(map);
	}

	//총 게시물 수
	public int selectRowreport() {
		return boardMapper.selectRowreport();
	}
	
	//신고 상세 페이지
	public BoardReportVO selectReport(Integer report) {
		return boardMapper.selectReport(report);
	}
	
	//신고글 쓰기
	public void insertreport(BoardReportVO Report) {
		boardMapper.insertReport(Report);
	}

	//=============게시글 차단==================//
	//보통 게시글
	public void updateState1(Integer boa_num) {
		boardMapper.updateState1(boa_num);
	}
	//차단 게시글
	public void updateState2(Integer boa_num) {
		boardMapper.updateState2(boa_num);
	}
	
	
	
/*	public BoardStatusVO selectSta(BoardStatusVO Sta) {
		return boardMapper.selectSta(Sta);
	}
	public void insertSta(BoardStatusVO boardSta) {
		boardMapper.insertSta(boardSta);
	}
	public void deleteSta(Integer boa_num) {
		boardMapper.deleteSta(boa_num);
	}
	*/
}