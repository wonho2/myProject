package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardVO;
import kr.spring.board.vo.board_replyVO;

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
		//댓글이 존재하면 댓글을 우선 삭제하고 부모글을 삭제
		boardMapper.deleteBoardCommentByBoardNum(boa_num);
		boardMapper.deleteBoard(boa_num);
	}

	//조회수 증가
	public void updateHit(Integer boa_num) {
		boardMapper.updateHit(boa_num);
	}

	//댓글
		public List<board_replyVO> selectBoardComment(Map<String, Object> map) {
			return boardMapper.selectBoardComment(map);
		}

		public int selectBoardCommentCount(Map<String, Object> map) {
			return boardMapper.selectBoardCommentCount(map);
		}

		public void insertBoardComment(board_replyVO board_reply) {
			boardMapper.insertBoardComment(board_reply);
		}

		public void updateBoardComment(board_replyVO board_reply) {
			boardMapper.updateBoardComment(board_reply);
		}

		public void deleteBoardComment(Integer bor_num) {
			//(*******주의)댓글 좋아요가 있을 경우
			//newsMapper.deleteReFavByRe_num(bor_num);
			boardMapper.deleteBoardComment(bor_num);
		}	
	}