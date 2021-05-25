package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardVO;

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
	//public int selectRowCount() {
	//	return boardMapper.selectRowCount();
	//}

	//글쓰기
	public void insertBoard(BoardVO vo) {
		boardMapper.insertBoard(vo);
	}


		
	}