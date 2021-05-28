/*package kr.spring.board.service;

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
		boardMapper.deleteReplyByBoardNum(boa_num);
		
		boardMapper.deleteBoard(boa_num);
	}

	//조회수 증가
	//public void updateHit(Integer board_num) {
	//	boardMapper.updateHit(board_num);
	//}

	//댓글
		public List<board_replyVO> selectListReply(Map<String, Object> map) {
			return boardMapper.selectListReply(map);
		}

		public int selectRowCountReply(Map<String, Object> map) {
			return boardMapper.selectRowCountReply(map);
		}

		public void insertReply(board_replyVO board_reply) {
			boardMapper.insertReply(board_reply);
		}

		public void updateReply(board_replyVO board_reply) {
			boardMapper.updateReply(board_reply);
		}

		public void deleteReply(Integer bor_num) {
			//(*******주의)댓글 좋아요가 있을 경우
			//newsMapper.deleteReFavByRe_num(ner_num);
			boardMapper.deleteReply(bor_num);
		}	
	
	
	}*/