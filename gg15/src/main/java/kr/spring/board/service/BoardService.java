package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardVO;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReplyVO;

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
	public int selectRowCount() {
		return boardMapper.selectRowCount();
	}

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
	public void updateFavUp(Integer boa_num) {
		//boardMapper.updateFavUp(boa_num);
	}

	//좋아요 감소
	public void updateFavDown(Integer boa_num) {
		//boardMapper.updateFavDown(boa_num);
	}

	//댓글
	public List<BoardReplyVO> selectBoardComment(Map<String, Object> map) {
		return boardMapper.selectBoardComment(map);
	}

	public int selectBoardCommentCount(Map<String, Object> map) {
		return boardMapper.selectBoardCommentCount(map);
	}

	public void insertBoardComment(BoardReplyVO board_reply) {
		boardMapper.insertBoardComment(board_reply);
	}

	public void updateBoardComment(BoardReplyVO board_reply) {
		boardMapper.updateBoardComment(board_reply);
	}
  
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
}